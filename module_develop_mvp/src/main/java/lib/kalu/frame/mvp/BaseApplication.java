package lib.kalu.frame.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedList;

import lib.kalu.frame.mvp.context.FrameContext;
import lib.kalu.frame.mvp.util.MvpUtil;

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static final LinkedList<Activity> mActivitys = new LinkedList<>();

    public static final void clearActivitys() {
        for (Activity activity : mActivitys) {
            MvpUtil.logE("BaseApplication => clearActivitys => activity = " + activity);
            if (null == activity)
                continue;
            activity.onBackPressed();
        }
    }

    public static final Context getContext() {
        return FrameContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivitys.remove(activity);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        mActivitys.add(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }
}
