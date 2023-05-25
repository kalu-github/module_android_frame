package lib.kalu.frame.mvp.impl;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewVisibility extends BaseViewFindViewById {

    default boolean isVisibility(@IdRes int viewId) {

        try {
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            return viewById.getVisibility() == View.VISIBLE;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => isVisibility => " + e.getMessage());
            return false;
        }
    }

    default boolean isVisibility(@NonNull View root,
                                 @IdRes int viewId) {

        try {
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            return viewById.getVisibility() == View.VISIBLE;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => isVisibility => " + e.getMessage());
            return false;
        }
    }

    /*******/

    default void setVisibility(@NonNull int visibility,
                               @IdRes int... viewIds) {

        try {
            if (null == viewIds || viewIds.length <= 0)
                throw new Exception("viewIds error: " + viewIds);
            for (int id : viewIds) {
                setVisibility(id, visibility);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => setVisibility => " + e.getMessage());
        }
    }

    default void setVisibility(@IdRes int viewId,
                               @NonNull int visibility) {

        try {
            if (visibility != View.VISIBLE && visibility != View.GONE && visibility != View.INVISIBLE)
                throw new Exception("visibility error: " + visibility);
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            viewById.setVisibility(visibility);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => setVisibility => " + e.getMessage());
        }
    }

    default void setVisibility(
            @NonNull View root,
            @NonNull int visibility,
            @IdRes int... viewIds) {

        try {
            if (null == viewIds || viewIds.length <= 0)
                throw new Exception("viewIds error: " + viewIds);
            for (int id : viewIds) {
                setVisibility(root, id, visibility);
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => setVisibility => " + e.getMessage());
        }
    }

    default void setVisibility(@NonNull View root,
                               @IdRes int viewId,
                               @NonNull int visibility) {

        try {
            if (visibility != View.VISIBLE && visibility != View.GONE && visibility != View.INVISIBLE)
                throw new Exception("visibility error: " + visibility);
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            viewById.setVisibility(visibility);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewVisibility => setVisibility => " + e.getMessage());
        }
    }
}
