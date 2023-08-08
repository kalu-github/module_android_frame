package lib.kalu.frame.mvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import lib.kalu.frame.mvp.util.MvpUtil;

/**
 * @author zhanghang
 * @description:
 * @date :2022-01-17
 */
public abstract class BaseActivityKillProcess<V extends BaseView, P extends BasePresenter> extends BaseActivity<V, P> implements BaseView {

    private final String INTENT_KILL_PROCESS = "intent_kill_process";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setKillProcess(true);
        super.onCreate(savedInstanceState);
    }

    /**************/

    @SuppressLint("RestrictedApi")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // action_down => keycode_back
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            setKillProcess(false);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            boolean killProcess = getKillProcess();
            if (killProcess)
                throw new Exception("killProcess");
            MvpUtil.logE("BaseActivityKillProcess => onPause => activity = " + this);
        } catch (Exception e) {
            checkApplication();
            MvpUtil.logE("BaseActivityKillProcess => onPause => " + e.getMessage() + ", activity = " + this);
            killProcess(false);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MvpUtil.logE("BaseActivityKillProcess => onRestart => activity = " + this);
        setKillProcess(true);
    }

    private void checkApplication() {
        try {
            Application application = getApplication();
            MvpUtil.logE("BaseActivityKillProcess => checkApplication => application = " + application);
            if (null == application)
                throw new Exception("application error: null");
            if (!(application instanceof BaseApplicationKillProcess))
                throw new Exception("application error: not instanceof BaseApplicationKillProcess");
            List<Activity> activitys = ((BaseApplicationKillProcess) application).getActivitys();
            for (Activity activity : activitys) {
                MvpUtil.logE("BaseActivityKillProcess => checkApplication => activity = " + activity);
                if (null == activity)
                    continue;
                MvpUtil.logE("BaseActivityKillProcess => checkApplication => onBackPressed => activity = " + activity);
                activity.onBackPressed();
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseActivityKillProcess => checkApplication => " + e.getMessage());
        }
    }

    public void setKillProcess(boolean status) {
        putBooleanExtra(INTENT_KILL_PROCESS, status);
    }

    public boolean getKillProcess() {
        return getBooleanExtra(INTENT_KILL_PROCESS, true);
    }

    /**********/

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        setKillProcess(false);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        setKillProcess(false);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        setKillProcess(false);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        setKillProcess(false);
    }
}