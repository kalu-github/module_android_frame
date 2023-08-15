
package lib.kalu.frame.mvvm.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import lib.kalu.frame.mvvm.util.MvpUtil;

public interface BaseViewLoading extends BaseViewWindow {

    default void showLoading() {
        try {
            if (initLoadingLayoutRes() == 0)
                throw new Exception("initLoadingLayoutRes error: " + initLoadingLayoutRes());
            if (initLoadingIdRes() == 0)
                throw new Exception("initLoadingIdRes error: " + initLoadingIdRes());
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView error: null");
            View loadingView = rootView.findViewById(initLoadingIdRes());
            if (null == loadingView) {
                LayoutInflater.from(getContext()).inflate(initLoadingLayoutRes(), rootView);
                loadingView = rootView.findViewById(initLoadingIdRes());
            }
            if (null == loadingView)
                throw new Exception("loadingView error: null");
            loadingView.bringToFront();
            loadingView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewLoading => showLoading => " + e.getMessage());
        }
    }

    default void hideLoading() {
        try {
            if (initLoadingLayoutRes() == 0)
                throw new Exception("initLoadingLayoutRes error: " + initLoadingLayoutRes());
            if (initLoadingIdRes() == 0)
                throw new Exception("initLoadingIdRes error: " + initLoadingIdRes());
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView error: null");
            View loadingView = rootView.findViewById(initLoadingIdRes());
            if (null == loadingView)
                throw new Exception("viewById error: null");
            loadingView.setVisibility(View.GONE);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewLoading => hideLoading => " + e.getMessage());
        }
    }

    @LayoutRes
    default int initLoadingLayoutRes() {
        return 0;
    }

    @IdRes
    default int initLoadingIdRes() {
        return 0;
    }
}
