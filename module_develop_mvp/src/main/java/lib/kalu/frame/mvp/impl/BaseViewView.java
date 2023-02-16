package lib.kalu.frame.mvp.impl;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewView extends BaseViewFindViewById {

    default void setFocusable(@IdRes int id, boolean hasFocus) {
        try {
            View view = findViewById(id);
            view.setFocusable(hasFocus);
        } catch (Exception e) {
        }
    }

    default boolean hasFocus(@IdRes int id) {
        try {
            View view = findViewById(id);
            return view.hasFocus();
        } catch (Exception e) {
            return false;
        }
    }

    default boolean hasFocusable(@IdRes int id) {
        try {
            View view = findViewById(id);
            return view.hasFocusable();
        } catch (Exception e) {
            return false;
        }
    }

    default String getTag(@IdRes int id) {

        View viewById = findViewById(id);
        if (null == viewById)
            return null;

        return getTag(viewById, id);
    }

    default String getTag(@IdRes int id,
                          @IdRes int key) {

        View viewById = findViewById(id);
        if (null == viewById)
            return null;

        return getTag(viewById, key);
    }

    default String getTag(@NonNull View view) {
        return getTag(view, -1);
    }

    default String getTag(@NonNull View view,
                          @IdRes int key) {

        Object viewTag;

        try {
            viewTag = view.getTag(key);
        } catch (Exception e) {
            viewTag = view.getTag();
        }

        return null == viewTag ? null : viewTag.toString();
    }

    default void getTag(@IdRes int id,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, tag);
    }

    default void getTag(@IdRes int id,
                        @IdRes int key,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, key, tag);
    }

    default void setTag(@IdRes int id,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, -1, tag);
    }

    default void setTag(@NonNull View view,
                        @NonNull CharSequence tag) {
        setTag(view, -1, tag);
    }

    default void setTag(@NonNull View view,
                        @IdRes int key,
                        @NonNull CharSequence tag) {

        if (null == view)
            return;

        try {
            view.setTag(key, tag);
        } catch (Exception e) {
            view.setTag(tag);
        }
    }

    default void setEnable(@IdRes int id,
                           boolean enable) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setEnable(viewById, enable);
    }

    default void setEnable(@NonNull View parent,
                           @IdRes int id,
                           boolean enable) {
        if (null == parent)
            return;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return;

        setEnable(viewById, enable);
    }

    default void setEnable(@NonNull View view,
                           boolean enable) {

        if (null == view)
            return;

        view.setEnabled(enable);

        if (!(view instanceof EditText))
            return;

        EditText editText = (EditText) view;
        editText.setFocusable(enable);
        editText.setFocusableInTouchMode(enable);
    }

    default void setVisibility(@IdRes int id, int visibility) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setVisibility(viewById, visibility);
    }

    default void setVisibility(@NonNull View parent, @IdRes int id, int visibility) {

        if (null == parent)
            return;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return;

        setVisibility(viewById, visibility);
    }

    default void setVisibility(@NonNull View view, int visibility) {

        if (null == view)
            return;

        view.setVisibility(visibility);
    }

    default boolean isVisibility(@IdRes int id) {

        View viewById = findViewById(id);
        if (null == viewById)
            return false;

        return isVisibility(viewById, id);
    }

    default boolean isVisibility(@NonNull View parent,
                                 @IdRes int id) {

        if (null == parent)
            return false;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return false;

        return isVisibility(viewById);
    }

    default boolean isVisibility(@NonNull View view) {

        if (null == view)
            return false;

        return (view.getVisibility() == View.VISIBLE);
    }

    default void setSelected(@NonNull View view, boolean selected) {
        try {
            if (null == view)
                throw new Exception("view is null");
            view.setSelected(selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewView => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@IdRes int id, boolean selected) {
        try {
            View viewById = findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewView => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@NonNull View view, @IdRes int id, boolean selected) {
        try {
            View viewById = view.findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewView => setSelected => " + e.getMessage());
        }
    }
}
