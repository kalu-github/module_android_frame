package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/**
 * description: findViewById
 * created by kalu on 2021-03-12
 */
@Keep
public interface BaseViewFindViewById {

    /*****************************************/

    default <T extends View> T findViewById(@IdRes int id) {

        if (null == this)
            throw new RuntimeException("findViewById => error");

        // activity
        if (this instanceof Activity) {
            Activity activity = (Activity) this;
            T t = activity.findViewById(id);
            return t;
        }
        // fragment
        else if (this instanceof Fragment && null != ((Fragment) this).getView()) {
            Fragment fragment = (Fragment) this;
            T t = fragment.getView().findViewById(id);
            return t;
        }
        // dialogfragment
        else if (this instanceof DialogFragment && null != ((DialogFragment) this).getDialog()) {
            DialogFragment dialogFragment = (DialogFragment) this;
            Dialog dialog = dialogFragment.getDialog();
            T t = dialog.findViewById(id);
            return t;
        }
        // fail
        else {
            throw new RuntimeException("findViewById => error");
        }
    }

    default <T extends View> T findViewById(@NonNull View parent,
                                            @IdRes int id) {

        if (null == parent)
            throw new RuntimeException("findViewById => error");

        T t = parent.findViewById(id);
        if (null == t)
            throw new RuntimeException("findViewById => error");

        return t;
    }
}
