package lib.kalu.frame.mvp.impl;

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
}
