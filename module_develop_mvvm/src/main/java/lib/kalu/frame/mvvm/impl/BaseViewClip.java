package lib.kalu.frame.mvvm.impl;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvpUtil;

@Keep
public interface BaseViewClip extends BaseViewFindViewById {

    default void setClip(@IdRes int id,
                         @NonNull boolean enable) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (!(viewById instanceof ViewGroup))
                throw new Exception("viewById error: not instanceof ViewGroup");
            ((ViewGroup) viewById).setClipChildren(enable);
            ((ViewGroup) viewById).setClipToPadding(enable);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewClip => setClip => " + e.getMessage());
        }
    }

    default void setClipChildren(@IdRes int id,
                                 @NonNull boolean enable) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (!(viewById instanceof ViewGroup))
                throw new Exception("viewById error: not instanceof ViewGroup");
            ((ViewGroup) viewById).setClipChildren(enable);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewClip => setClipChildren => " + e.getMessage());
        }
    }

    default void setClipToPadding(@IdRes int id,
                                  @NonNull boolean enable) {
        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (!(viewById instanceof ViewGroup))
                throw new Exception("viewById error: not instanceof ViewGroup");
            ((ViewGroup) viewById).setClipToPadding(enable);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewClip => setClipChildren => " + e.getMessage());
        }
    }
}
