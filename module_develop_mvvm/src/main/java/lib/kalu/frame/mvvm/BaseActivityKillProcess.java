package lib.kalu.frame.mvvm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lib.kalu.frame.mvvm.util.MvpUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivityKillProcess<M extends BaseModel, V extends BaseView, VM extends BaseViewModel> extends BaseActivity<M, V, VM> implements BaseView {

    private final String INTENT_KEYCODE_BACK = "intent_keycode_back";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
        } catch (Exception e) {
        }
        putBooleanExtra(INTENT_KEYCODE_BACK, false);
        super.onCreate(savedInstanceState);
    }

    /**************/

    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // action_down => keycode_back
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            putBooleanExtra(INTENT_KEYCODE_BACK, true);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            boolean keycodeBack = getBooleanExtra(INTENT_KEYCODE_BACK, false);
            if (!keycodeBack)
                throw new Exception("killProcess");
            MvpUtil.logE("BaseActivityKillProcess => onPause =>");
        } catch (Exception e) {
            MvpUtil.logE("BaseActivityKillProcess => onPause => " + e.getMessage());
            killProcess();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MvpUtil.logE("BaseActivityKillProcess => onRestart =>");
        putBooleanExtra(INTENT_KEYCODE_BACK, false);
    }

    /**********/

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        putBooleanExtra(INTENT_KEYCODE_BACK, true);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        putBooleanExtra(INTENT_KEYCODE_BACK, true);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        putBooleanExtra(INTENT_KEYCODE_BACK, true);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        putBooleanExtra(INTENT_KEYCODE_BACK, true);
    }
}