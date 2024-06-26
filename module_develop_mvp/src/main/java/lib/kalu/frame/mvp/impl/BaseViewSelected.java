package lib.kalu.frame.mvp.impl;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewSelected extends BaseViewFindViewById {

    default void setSelected(@NonNull View view, boolean selected) {
        try {
            if (null == view)
                throw new Exception("view is null");
            view.setSelected(selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@IdRes int id, boolean selected) {
        try {
            View viewById = findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@NonNull View view, @IdRes int id, boolean selected) {
        try {
            View viewById = view.findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }

    default boolean isSelected(@NonNull View view) {
        try {
            if (null == view)
                throw new Exception("view is null");
            return view.isSelected();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => isSelected => " + e.getMessage());
            return false;
        }
    }

    default boolean isSelected(@IdRes int id) {
        try {
            View viewById = findViewById(id);
            return isSelected(viewById);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => isSelected => " + e.getMessage());
            return false;
        }
    }

    default boolean isSelected(@NonNull View view, @IdRes int id) {
        try {
            View viewById = view.findViewById(id);
            return isSelected(viewById);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
            return false;
        }
    }
}
