package lib.kalu.frame.mvp;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import lib.kalu.frame.mvp.util.MvpUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivityMonitorKeycodeHome<V extends BaseView, P extends BasePresenter> extends FragmentActivity implements BaseView {

    private final String INTENT_KEYCODE_BACK = "intent_keycode_back";

    private P mP = null;

    @Override
    public void onBackPressed() {
        try {
            mP.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => onBackPressed => " + e.getMessage());
        }
        super.onBackPressed();
    }

    @Override
    public void finish() {
        try {
            mP.cleanDisposable();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => finish => " + e.getMessage());
        }
        super.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
        } catch (Exception e) {
        }
        putBooleanExtra(INTENT_KEYCODE_BACK, false);
        try {
            initWindow();
            setContentView(initLayout());
            mP = initPresenter();
            initData();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => onCreate => " + e.getMessage());
        }
    }

    @Override
    public final P getPresenter() {
        try {
            if (null == mP)
                throw new IllegalArgumentException("mP error: null");
            return mP;
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => getPresenter => " + e.getMessage());
            throw e;
        }
    }

    private P initPresenter() {
        try {
            Class<V> clazzV = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Class<P> clazzP = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Constructor constructorP = clazzP.getDeclaredConstructor(new Class[]{clazzV});
            constructorP.setAccessible(true);
            return (P) constructorP.newInstance(this);
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => initPresenter => " + e.getMessage());
            try {
                throw e;
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void initWindow() {
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
//        window.setAttributes(params);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        try {
            return super.getDefaultViewModelCreationExtras();
        } catch (Exception e) {
            MvpUtil.logE("BaseActivity => getDefaultViewModelCreationExtras => " + e.getMessage());
            return new MutableCreationExtras();
        }
    }

    /**************/

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
        MvpUtil.logE("BaseActivityMonitorKeycodeHome => onPause =>");
        try {
            boolean keycodeBack = getBooleanExtra(INTENT_KEYCODE_BACK, false);
            if (!keycodeBack)
                throw new Exception("not press down keycode_back");
        } catch (Exception e) {
            MvpUtil.logE("BaseActivityMonitorKeycodeHome => onPause => " + e.getMessage());
            killProcess();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MvpUtil.logE("BaseActivityMonitorKeycodeHome => onRestart =>");
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