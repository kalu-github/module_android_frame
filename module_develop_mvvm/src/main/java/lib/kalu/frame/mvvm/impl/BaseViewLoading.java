
package lib.kalu.frame.mvvm.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

public interface BaseViewLoading extends BaseViewWindow {

//    default void showLoading() {
//        try {
//            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
//                throw new Exception("loading error: not find 001");
//            ViewGroup rootView = getRootView();
//            if (null == rootView)
//                throw new Exception("loading error: not find 002");
//            View viewById = rootView.findViewById(initLoadingIdRes());
//            if (null == viewById) {
//                View inflate = LayoutInflater.from(getContext()).inflate(initLoadingLayoutRes(), null);
//                rootView.addView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                viewById = inflate;
//            }
//            if (null == viewById)
//                throw new Exception("viewById error: null");
//            ViewGroup parentGroup = (ViewGroup) viewById.getParent();
//            if (null == parentGroup)
//                throw new Exception("parentGroup error: null");
//            parentGroup.bringToFront();
//            parentGroup.setVisibility(View.VISIBLE);
//            viewById.setVisibility(View.VISIBLE);
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewLoading => showLoading => " + e.getMessage());
//        }
//    }
//
//    default void hideLoading() {
//        try {
//            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
//                throw new Exception("loading error: not find 001");
//            ViewGroup rootView = getRootView();
//            if (null == rootView)
//                throw new Exception("loading error: not find 002");
//            View viewById = rootView.findViewById(initLoadingIdRes());
//            if (null == viewById)
//                throw new Exception("viewById error: null");
//            ViewGroup parentGroup = (ViewGroup) viewById.getParent();
//            if (null == parentGroup)
//                throw new Exception("parentGroup error: null");
//            parentGroup.setVisibility(View.GONE);
//            viewById.setVisibility(View.GONE);
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewLoading => hideLoading => " + e.getMessage());
//        }
//    }
//
default void showLoading() {
    try {
        if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
            throw new Exception();
        ViewGroup rootView = getRootView();
        if (null == rootView)
            throw new Exception();
        View viewById = rootView.findViewById(initLoadingIdRes());
        if (null != viewById)
            throw new IllegalStateException();
        View inflate = LayoutInflater.from(getContext()).inflate(initLoadingLayoutRes(), null);
        inflate.setVisibility(View.GONE);
        rootView.addView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        inflate.bringToFront();
        inflate.setVisibility(View.VISIBLE);
    } catch (IllegalStateException e) {
        ViewGroup rootView = getRootView();
        View viewById = rootView.findViewById(initLoadingIdRes());
        viewById.bringToFront();
        viewById.setVisibility(View.VISIBLE);
    } catch (Exception e) {
    }
}

    default void hideLoading() {
        try {
            if (initLoadingLayoutRes() == 0 || initLoadingIdRes() == 0)
                throw new Exception();
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception();
            View viewById = rootView.findViewById(initLoadingIdRes());
            if (null == viewById)
                throw new Exception();
            viewById.setVisibility(View.GONE);
        } catch (Exception e) {
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
