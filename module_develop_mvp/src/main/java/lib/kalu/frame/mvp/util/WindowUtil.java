package lib.kalu.frame.mvp.util;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;

@Keep
public final class WindowUtil {

    public static ViewGroup findDecorView(View view) {
        try {
            View parent = (View) view.getParent();
            if (null == parent) {
                return (ViewGroup) view;
            } else {
                return findDecorView(parent);
            }
        } catch (Exception e) {
            return (ViewGroup) view;
        }
    }
}
