package lib.kalu.frame.mvvm.impl;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
public interface BaseViewEnable extends BaseViewFindViewById {

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


    default boolean isEnabled(@NonNull View parent,
                              @IdRes int id) {

        try {
            if (null == parent)
                throw new Exception("parent error: null");
            View viewById = findViewById(parent, id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            return isEnabled(viewById);
        } catch (Exception e) {
            return false;
        }
    }

    default boolean isEnabled(@IdRes int id) {

        try {
            View viewById = findViewById(id);
            if (null == viewById)
                throw new Exception("viewById error: null");
            return isEnabled(viewById);
        } catch (Exception e) {
            return false;
        }
    }

    default boolean isEnabled(@NonNull View view) {
        try {
            if (null == view)
                throw new Exception("view error: null");
            boolean viewEnabled = view.isEnabled();
            if (!viewEnabled)
                throw new Exception("viewEnabled warning: false");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
