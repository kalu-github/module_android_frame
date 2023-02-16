package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.StringRes;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

@Keep
public interface BaseViewContext {

    default Context getContext() {

        // DialogFragment
        if (this instanceof DialogFragment) {
            DialogFragment dialogFragment = (DialogFragment) this;
            Context context = dialogFragment.getContext().getApplicationContext();
            return context;
        }
        // Dialog
        else if (this instanceof Dialog) {
            Dialog dialog = (Dialog) this;
            Context context = dialog.getContext().getApplicationContext();
            return context;
        }
        // service
        else if (this instanceof Service) {
            Service service = (Service) this;
            Context context = service.getApplicationContext();
            return context;
        }
        // fragment
        else if (this instanceof Fragment) {
            Fragment fragment = (Fragment) this;
            Context context = fragment.getContext().getApplicationContext();
            return context;
        }
        // activity
        else if (this instanceof Activity) {
            Activity activity = (Activity) this;
            Context context = activity.getApplicationContext();
            return context;
        }
        // fail
        else {
            return null;
        }
    }

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
}
