package lib.kalu.frame.mvvm.impl;

import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewFocusable extends BaseViewFindViewById {

    default void setFocusable(@IdRes int id, boolean hasFocus) {
        setFocusable(id, hasFocus, false);
    }

    default void setFocusable(@IdRes int id, boolean hasFocus, boolean isFindViewByIdToActivity) {
        try {
            if (isFindViewByIdToActivity) {
                // activity
                if (this instanceof Activity) {
                    Activity activity = (Activity) this;
                    View view = activity.findViewById(id);
                    view.setFocusable(hasFocus);
                }
                // fragment
                else if (this instanceof Fragment && null != ((Fragment) this).getView()) {
                    Fragment fragment = (Fragment) this;
                    View view = fragment.getActivity().findViewById(id);
                    view.setFocusable(hasFocus);
                }
                // fail
                else {
                    throw new RuntimeException("findViewById => error");
                }
            } else {
                View view = findViewById(id);
                view.setFocusable(hasFocus);
            }
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFocusable => setFocusable => " + e.getMessage());
        }
    }

    default boolean hasFocus(@IdRes int id) {
        try {
            View view = findViewById(id);
            return view.hasFocus();
        } catch (Exception e) {
            return false;
        }
    }

    default boolean hasFocusable(@IdRes int id) {
        try {
            View view = findViewById(id);
            return view.hasFocusable();
        } catch (Exception e) {
            return false;
        }
    }

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

    /*********/

    default void requestFocus(@IdRes int viewId) {
        requestFocus(viewId, true);
    }

    default void requestFocus(@IdRes int viewId,
                              @NonNull boolean checkFocusable) {
        try {
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (checkFocusable) {
                viewById.setFocusable(true);
            }
            viewById.requestFocus();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFocusable => requestFocus => " + e.getMessage());
        }
    }

    default void requestFocus(@NonNull View root,
                              @IdRes int viewId) {
        requestFocus(root, viewId, true);
    }

    default void requestFocus(@NonNull View root,
                              @IdRes int viewId,
                              @NonNull boolean checkFocusable) {
        try {
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (checkFocusable) {
                viewById.setFocusable(true);
            }
            viewById.requestFocus();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFocusable => requestFocus => " + e.getMessage());
        }
    }

    /*********/

    default void clearFocus(@IdRes int viewId) {
        clearFocus(viewId, false);
    }

    default void clearFocus(@IdRes int viewId,
                            @NonNull boolean checkFocusable) {
        try {
            View viewById = findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (checkFocusable) {
                viewById.setFocusable(false);
            }
            viewById.clearFocus();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFocusable => cleanFocus => " + e.getMessage());
        }
    }

    default void clearFocus(@NonNull View root,
                            @IdRes int viewId) {
        clearFocus(root, viewId, false);
    }

    default void clearFocus(@NonNull View root,
                            @IdRes int viewId,
                            @NonNull boolean checkFocusable) {
        try {
            if (null == root)
                throw new Exception("root error: null");
            View viewById = root.findViewById(viewId);
            if (null == viewById)
                throw new Exception("viewById error: null");
            if (checkFocusable) {
                viewById.setFocusable(false);
            }
            viewById.clearFocus();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFocusable => requestFocus => " + e.getMessage());
        }
    }

    /******************/
}
