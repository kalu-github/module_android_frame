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
            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
                throw new Exception("loading error: not find 001");
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("loading error: not find 002");
            View viewById = rootView.findViewById(initLoadingIdRes());
            if (null == viewById) {
                LayoutInflater.from(getContext()).inflate(initLoadingLayoutRes(), rootView);
                viewById = rootView.findViewById(initLoadingIdRes());
            }
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup parentGroup = (ViewGroup) viewById.getParent();
            if (null == parentGroup)
                throw new Exception("parentGroup error: null");
            parentGroup.bringToFront();
            parentGroup.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewLoading => showLoading => " + e.getMessage());
        }
    }

    default void hideLoading() {
        try {
            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
                throw new Exception("loading error: not find 001");
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("loading error: not find 002");
            View viewById = rootView.findViewById(initLoadingIdRes());
            if (null == viewById)
                throw new Exception("viewById error: null");
            ViewGroup parentGroup = (ViewGroup) viewById.getParent();
            if (null == parentGroup)
                throw new Exception("parentGroup error: null");
            parentGroup.setVisibility(View.GONE);
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
