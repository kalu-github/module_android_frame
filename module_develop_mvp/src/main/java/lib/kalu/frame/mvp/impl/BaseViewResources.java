package lib.kalu.frame.mvp.impl;

import android.content.Context;

import androidx.annotation.ArrayRes;
import androidx.annotation.Keep;
import androidx.annotation.StringRes;

@Keep
public interface BaseViewResources extends BaseViewContext {

    default String getStringValue(@StringRes int id) {
        try {
            Context context = getContext();
            return context.getResources().getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    default String getStringValue(@StringRes int id, Object... formatArgs) {
        try {
            Context context = getContext();
            return context.getResources().getString(id, formatArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    default String[] getStringArrayValue(@ArrayRes int id) {
        try {
            Context context = getContext();
            return context.getResources().getStringArray(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
