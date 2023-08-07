package lib.kalu.frame.mvvm.util;

import android.view.View;
import android.view.ViewGroup;

public final class ViewUtil {

    public static ViewGroup findDecorView(View view) {
        try {
            View parent = (View) view.getParent();
            if (null == parent) {
                return (ViewGroup) view;
            } else {
                return findDecorView(parent);
            }
        } catch (Exception e) {
            MvpUtil.logE("ViewUtil => findDecorView => " + e.getMessage());
            return (ViewGroup) view;
        }
    }
}
