package lib.kalu.frame.mvp.impl;

import android.view.View;
import android.widget.EditText;

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
}
