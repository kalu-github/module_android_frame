package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewWindow extends BaseViewContext {

    default Window getWindow() {
        try {
            return getActivity().getWindow();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackgroundDrawable => " + e.getMessage());
            return null;
        }
    }

    default ViewGroup getRootView() {
        try {
            return (ViewGroup) getWindow().getDecorView().getRootView();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackgroundDrawable => " + e.getMessage());
            return null;
        }
    }

    default boolean setWindowBackgroundColorRes(@ColorRes int color) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            Context context = rootView.getContext();
            if (null == context)
                throw new Exception("context is null");
            int col = context.getResources().getColor(color);
            ColorDrawable drawable = new ColorDrawable(col);
            setWindowBackgroundDrawable(drawable);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackgroundColorRes => " + e.getMessage());
            return false;
        }
    }

    default boolean setWindowBackgroundColor(@ColorInt int color) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            Context context = rootView.getContext();
            if (null == context)
                throw new Exception("context is null");
            ColorDrawable drawable = new ColorDrawable(color);
            setWindowBackgroundDrawable(drawable);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackgroundColor => " + e.getMessage());
            return false;
        }
    }

    default boolean setWindowBackgroundDrawable(@Nullable Drawable drawable) {
        try {
            if (null == drawable)
                throw new Exception("drawable is null");
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            rootView.setBackground(drawable);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackgroundDrawable => " + e.getMessage());
            return false;
        }
    }

    default boolean setBackgroundResource(@DrawableRes int res) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            rootView.setBackgroundResource(res);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setBackgroundResource => " + e.getMessage());
            return false;
        }
    }
}
