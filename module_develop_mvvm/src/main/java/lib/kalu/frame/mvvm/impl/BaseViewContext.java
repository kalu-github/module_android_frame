package lib.kalu.frame.mvvm.impl;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;

import androidx.annotation.Keep;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import lib.kalu.frame.mvvm.util.WrapperUtil;

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

    default Activity getWrapperActivity(Context context) {
        return WrapperUtil.getWrapperActivity(context);
    }

    default Activity getWrapperActivity() {
        Context context = getContext();
        return WrapperUtil.getWrapperActivity(context);
    }
}
