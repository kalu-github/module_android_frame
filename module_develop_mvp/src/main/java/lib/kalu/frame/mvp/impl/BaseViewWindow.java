package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

@Keep
public interface BaseViewWindow extends BaseViewContext {

    default Activity getActivity(Context context) {
        try {
            if (context instanceof Activity) {
                return (Activity) context;
            } else if (context instanceof ContextThemeWrapper) {
                return getActivity(((ContextThemeWrapper) context).getBaseContext());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    default Activity getActivity() {
        try {
            Context context = getContext();
            if (context instanceof Activity) {
                return (Activity) context;
            } else if (context instanceof ContextThemeWrapper) {
                return getActivity(((ContextThemeWrapper) context).getBaseContext());
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    default Window getWindow() {
        try {
            return getActivity().getWindow();
        } catch (Exception e) {
            return null;
        }
    }

    default ViewGroup getRootView() {
        try {
            return (ViewGroup) getWindow().getDecorView().getRootView();
        } catch (Exception e) {
            return null;
        }
    }

    default boolean setWindowBackgroundDrawable(@Nullable Drawable drawable) {
        try {
            ViewGroup rootView = getRootView();
            if (null == rootView)
                throw new Exception("rootView is null");
            rootView.setBackground(drawable);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
