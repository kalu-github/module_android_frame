package lib.kalu.frame.mvvm.impl;

import android.app.Activity;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import lib.kalu.frame.mvvm.util.MvpUtil;

@Keep
public interface BaseViewFragmentManager extends BaseViewContext {

//    default boolean showFragment(@Nullable android.app.Fragment fragment) {
//        try {
//            if (null == fragment)
//                throw new Exception("fragment is null");
//            android.app.FragmentTransaction fragmentTransaction = getFragmentManagerTransaction();
//            if (null == fragmentTransaction)
//                throw new Exception("fragmentTransaction is null");
//            fragmentTransaction.show(fragment);
//            return true;
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewFragmentManger => showFragment => " + e.getMessage());
//            return false;
//        }
//    }

    default boolean removeFragment(@Nullable androidx.fragment.app.Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commitNow();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragment => " + e.getMessage());
            return false;
        }
    }

    default boolean removeFragments(@Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments || fragments.size() == 0)
                throw new Exception("fragment error: " + fragments);
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                fragmentTransaction.remove(fragment);
            }
            fragmentTransaction.commitNow();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragments => " + e.getMessage());
            return false;
        }
    }


    default boolean showFragment(@Nullable androidx.fragment.app.Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.show(fragment);
            fragmentTransaction.commitNow();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => showFragment => " + e.getMessage());
            return false;
        }
    }

    default boolean hideFragment(@Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments || fragments.size() <= 0)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commitNow();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragment => " + e.getMessage());
            return false;
        }
    }

    default boolean hideFragment(@Nullable androidx.fragment.app.Fragment[] fragments) {
        try {
            if (null == fragments || fragments.length <= 0)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragment => " + e.getMessage());
            return false;
        }
    }

    default boolean hideFragment(@Nullable androidx.fragment.app.Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragment => " + e.getMessage());
            return false;
        }
    }

    default boolean addFragment(@Nullable androidx.fragment.app.Fragment fragment,
                                @IdRes int id,
                                @NonNull String tag) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            if (null == tag || tag.length() <= 0)
                throw new Exception("tag is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.add(id, fragment, tag);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragment => " + e.getMessage());
            return false;
        }
    }

//    default android.app.FragmentManager getFragmentManager() {
//        try {
//            Activity activity = getActivity();
//            if (null == activity)
//                throw new Exception("activity is null");
//            return activity.getFragmentManager();
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewFragmentManger => getFragmentManager => " + e.getMessage());
//            return null;
//        }
//    }

//    default android.app.FragmentTransaction getFragmentManagerTransaction() {
//        try {
//            FragmentManager fragmentManager = getFragmentManager();
//            if (null == fragmentManager)
//                throw new Exception("fragmentManager is null");
//            return fragmentManager.beginTransaction();
//        } catch (Exception e) {
//            MvpUtil.logE("BaseViewFragmentManger => getFragmentManager => " + e.getMessage());
//            return null;
//        }
//    }

    default FragmentManager getSupportFragmentManager() {
        try {
            Activity activity = getWrapperActivity();
            if (null == activity)
                throw new Exception("activity is null");
            if (!(activity instanceof FragmentActivity))
                throw new Exception("activity is not AppCompatActivity");
            return ((FragmentActivity) activity).getSupportFragmentManager();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => getFragmentManager => " + e.getMessage());
            return null;
        }
    }

    default androidx.fragment.app.FragmentTransaction getSupportFragmentManagerTransaction() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (null == fragmentManager)
                throw new Exception("fragmentManager is null");
            return fragmentManager.beginTransaction();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => getSupportFragmentManagerTransaction => " + e.getMessage());
            return null;
        }
    }

    default androidx.fragment.app.Fragment findFragmentByTag(String v) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (null == fragmentManager)
                throw new Exception("fragmentManager is null");
            return fragmentManager.findFragmentByTag(v);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => findFragmentByTag => " + e.getMessage());
            return null;
        }
    }
}