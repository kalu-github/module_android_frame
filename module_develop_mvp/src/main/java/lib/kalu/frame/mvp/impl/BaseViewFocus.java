package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

@Keep
public interface BaseViewFocus extends BaseViewFindViewById {

    @IdRes
    default int getCurrentFocusId() {
        try {
            return getCurrentFocus().getId();
        } catch (Exception e) {
            return 0;
        }
    }

    @IdRes
    default int getCurrentFocusId(@IdRes int id) {
        try {
            return getCurrentFocus(id).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    @IdRes
    default int getCurrentFocusId(@NonNull View view) {
        try {
            return getCurrentFocus(view).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    default View getCurrentFocus() {
        try {
            if (this instanceof Fragment) {
                return ((Fragment) this).getActivity().getCurrentFocus();
            }
            // activity
            else if (this instanceof Activity) {
                return ((Activity) this).getCurrentFocus();
            }
            // null;
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            return null;
        }
    }

    default View getCurrentFocus(@IdRes int id) {
        try {
            View view = findViewById(id);
            return getCurrentFocus(view);
        } catch (Exception e) {
            return null;
        }
    }

    default View getCurrentFocus(@NonNull View view) {
        try {
            return view.findFocus();
        } catch (Exception e) {
            return null;
        }
    }

    default void requestFocus(@IdRes int id) {
        try {
            View byId = findViewById(id);
            requestFocus(byId);
        } catch (Exception e) {
        }
    }

    default void requestFocus(@NonNull View view, @IdRes int id) {
        try {
            View byId = view.findViewById(id);
            requestFocus(byId);
        } catch (Exception e) {
        }
    }

    default void requestFocus(@NonNull View view) {
        try {
            view.requestFocus();
        } catch (Exception e) {
        }
    }
}
