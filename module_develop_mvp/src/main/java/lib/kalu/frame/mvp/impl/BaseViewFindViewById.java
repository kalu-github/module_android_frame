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

    /*****************************************/

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

    /*****************************************/

    default void setVisibility(@IdRes int id,
                               boolean flag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setVisibility(viewById, flag);
    }

    default void setVisibility(@NonNull View parent,
                               @IdRes int id,
                               boolean flag) {

        if (null == parent)
            return;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return;

        setVisibility(viewById, flag);
    }

    default void setVisibility(@NonNull View view,
                               boolean flag) {

        if (null == view)
            return;

        view.setVisibility(flag ? View.VISIBLE : View.GONE);
    }

    /*****************************************/

    default boolean isVisibility(@IdRes int id) {

        View viewById = findViewById(id);
        if (null == viewById)
            return false;

        return isVisibility(viewById, id);
    }

    default boolean isVisibility(@NonNull View parent,
                                 @IdRes int id) {

        if (null == parent)
            return false;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return false;

        return isVisibility(viewById);
    }

    default boolean isVisibility(@NonNull View view) {

        if (null == view)
            return false;

        return (view.getVisibility() == View.VISIBLE);
    }

    /*****************************************/

    default String getTag(@IdRes int id) {

        View viewById = findViewById(id);
        if (null == viewById)
            return null;

        return getTag(viewById, id);
    }

    default String getTag(@IdRes int id,
                          @IdRes int key) {

        View viewById = findViewById(id);
        if (null == viewById)
            return null;

        return getTag(viewById, key);
    }

    default String getTag(@NonNull View view) {
        return getTag(view, -1);
    }

    default String getTag(@NonNull View view,
                          @IdRes int key) {

        Object viewTag;

        try {
            viewTag = view.getTag(key);
        } catch (Exception e) {
            viewTag = view.getTag();
        }

        return null == viewTag ? null : viewTag.toString();
    }

    /*****************************************/

    default void getTag(@IdRes int id,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, tag);
    }

    default void getTag(@IdRes int id,
                        @IdRes int key,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, key, tag);
    }

    default void setTag(@IdRes int id,
                        @NonNull CharSequence tag) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setTag(viewById, -1, tag);
    }

    default void setTag(@NonNull View view,
                        @NonNull CharSequence tag) {
        setTag(view, -1, tag);
    }

    default void setTag(@NonNull View view,
                        @IdRes int key,
                        @NonNull CharSequence tag) {

        if (null == view)
            return;

        try {
            view.setTag(key, tag);
        } catch (Exception e) {
            view.setTag(tag);
        }
    }

    /*****************************************/

    default void setEnable(@IdRes int id,
                           boolean enable) {

        View viewById = findViewById(id);
        if (null == viewById)
            return;

        setEnable(viewById, enable);
    }

    default void setEnable(@NonNull View parent,
                           @IdRes int id,
                           boolean enable) {
        if (null == parent)
            return;

        View viewById = findViewById(parent, id);
        if (null == viewById)
            return;

        setEnable(viewById, enable);
    }

    default void setEnable(@NonNull View view,
                           boolean enable) {

        if (null == view)
            return;

        view.setEnabled(enable);

        if (!(view instanceof EditText))
            return;

        EditText editText = (EditText) view;
        editText.setFocusable(enable);
        editText.setFocusableInTouchMode(enable);
    }

    /*****************************************/

    default void setSelected(@IdRes int id, boolean selected) {
        try {
            View viewById = findViewById(id);
            viewById.setSelected(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
