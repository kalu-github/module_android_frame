package lib.kalu.frame.mvvm.impl;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewSelected extends BaseViewFindViewById {

    default void setSelected(@NonNull View view, boolean selected) {
        try {
            if (null == view)
                throw new Exception("view is null");
            view.setSelected(selected);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@IdRes int id, boolean selected) {
        try {
            View viewById = findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }

    default void setSelected(@NonNull View view, @IdRes int id, boolean selected) {
        try {
            View viewById = view.findViewById(id);
            setSelected(viewById, selected);
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewSelected => setSelected => " + e.getMessage());
        }
    }
}
