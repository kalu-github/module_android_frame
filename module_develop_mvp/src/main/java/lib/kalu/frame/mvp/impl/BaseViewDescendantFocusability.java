package lib.kalu.frame.mvp.impl;

import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;

@Keep
public interface BaseViewDescendantFocusability extends BaseViewFindViewById {

    default void setDescendantFocusability(@IdRes int id, int focusability) {
        try {
            ViewGroup viewGroup = findViewById(id);
            viewGroup.setDescendantFocusability(focusability);
        } catch (Exception e) {
        }
    }
}
