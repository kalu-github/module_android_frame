package com.kalu.develop.frame;

import android.app.Application;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import lib.kalu.frame.mvvm.BaseViewModel;

public class LoadingViewModel extends BaseViewModel<Disposable> {

    @Keep
    public LoadingViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onRelease(@NonNull Disposable disposable) {
        if (null != disposable) {
            disposable.dispose();
        }
    }
}