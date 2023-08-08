package lib.kalu.frame.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lib.kalu.frame.mvp.context.FrameContext;
import lib.kalu.frame.mvp.util.MvpUtil;

public class BaseApplicationKillProcess extends BaseApplication implements Application.ActivityLifecycleCallbacks {

    private final LinkedList<Activity> mActivitys = new LinkedList<>();

    public final List<Activity> getActivitys() {
        LinkedList<Activity> activities = new LinkedList<>();
        activities.addAll(mActivitys);
        mActivitys.clear();
        return activities;
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
