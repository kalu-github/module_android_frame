package lib.kalu.frame.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.fragment.app.Fragment;

public class WrapperUtil {

    public static Activity getWrapperActivity(Context context) {
        try {
            if (context instanceof Activity) {
                return (Activity) context;
            } else if (context instanceof ContextWrapper) {
                return getWrapperActivity(((ContextWrapper) context).getBaseContext());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
