package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import lib.kalu.frame.mvp.util.ImageUtil;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewWindow extends BaseViewContext {

    default Window getWindow() {
        try {
            return getWrapperActivity().getWindow();
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

    default boolean setWindowColorRes(@ColorRes int color) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            Context context = rootView.getContext();
            if (null == context)
                throw new Exception("context is null");
            ColorDrawable drawable = new ColorDrawable(context.getResources().getColor(color));
            setWindowBackground(drawable);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowColorRes => " + e.getMessage());
            return false;
        }
    }

    default boolean setWindowColor(@ColorInt int color) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            Context context = rootView.getContext();
            if (null == context)
                throw new Exception("context is null");
            ColorDrawable drawable = new ColorDrawable(color);
            setWindowBackground(drawable);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowColor => " + e.getMessage());
            return false;
        }
    }

    default boolean setWindowBackground(@Nullable Drawable drawable) {
        return setWindowBackground(drawable, false);
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

    default boolean setWindowBackground(@Nullable Drawable drawable, boolean blur) {
        try {
            if (null == drawable)
                throw new Exception("drawable is null");
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            if (blur) {
                Bitmap drawable2Bitmap = ImageUtil.drawable2Bitmap(drawable);
                Bitmap bitmap = ImageUtil.stackBlur(drawable2Bitmap, 25);
                rootView.setBackground(new BitmapDrawable(bitmap));
            } else {
                rootView.setBackground(drawable);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewWindow => setWindowBackground => " + e.getMessage());
            return false;
        }
    }
}
