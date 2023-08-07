package lib.kalu.frame.mvvm.impl;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvpUtil;

@Keep
public interface BaseViewTag extends BaseViewFindViewById {

    default <T extends Object> T getTag(@IdRes int viewId,
                                        @IdRes int tagId,
                                        @NonNull T def) {
        try {
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            Object tag = viewById.getTag(tagId);
            if (null == tag)
                throw new Exception("tag error: null");
            return (T) tag;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTag => getTag => " + e.getMessage());
            return def;
        }
    }

    default <T extends Object> T getTag(@NonNull View root,
                                        @IdRes int viewId,
                                        @IdRes int tagId,
                                        @NonNull T def) {

        try {
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            Object tag = viewById.getTag(tagId);
            if (null == tag)
                throw new Exception("tag error: null");
            return (T) tag;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTag => getTag => " + e.getMessage());
            return def;
        }
    }

    default <T extends Object> void setTag(@IdRes int viewId,
                                           @IdRes int tagId,
                                           @NonNull T o) {

        try {
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            viewById.setTag(tagId, o);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTag => setTag => " + e.getMessage());
        }
    }

    default <T extends Object> void setTag(@NonNull View root,
                                           @IdRes int viewId,
                                           @IdRes int tagId,
                                           @NonNull T o) {

        try {
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            viewById.setTag(tagId, o);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewTag => setTag => " + e.getMessage());
        }
    }
}
