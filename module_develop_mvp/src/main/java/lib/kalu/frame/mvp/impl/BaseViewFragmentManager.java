package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewFragmentManager extends BaseViewContext {

    /****** base  ******/

    default androidx.fragment.app.FragmentManager getSupportFragmentManager() {
        try {
            Activity activity = getWrapperActivity();
            if (null == activity)
                throw new Exception("activity is null");
            if (!(activity instanceof FragmentActivity))
                throw new Exception("activity is not AppCompatActivity");
            return ((FragmentActivity) activity).getSupportFragmentManager();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => getSupportFragmentManager => " + e.getMessage());
            return null;
        }
    }

    default androidx.fragment.app.FragmentTransaction getSupportFragmentManagerTransaction() {
        try {
            androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
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

    /****** remove  ******/

    default boolean removeFragmentsByTag(@Nullable List<String> tags) {
        try {
            if (null == tags)
                throw new Exception("error: tags null");
            for (String tag : tags) {
                if (null == tag || tag.length() == 0)
                    continue;
                removeFragmentByTag(tag);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragmentsByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean removeFragmentByTag(@Nullable String tag) {
        try {
            if (null == tag || tag.length() == 0)
                throw new Exception("error: tag null");
            Fragment fragmentByTag = findFragmentByTag(tag);
            if (null == fragmentByTag)
                throw new Exception("error: fragmentByTag null");
            return removeFragment(fragmentByTag);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragmentByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean removeFragments(@Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                removeFragment(fragment);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragments => " + e.getMessage());
            return false;
        }
    }

    default boolean removeFragment(@Nullable androidx.fragment.app.Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => removeFragment => " + e.getMessage());
            return false;
        }
    }

    /****** show  ******/

    default boolean showFragmentsByTag(@Nullable List<String> tags) {
        try {
            if (null == tags)
                throw new Exception("error: tags null");
            for (String tag : tags) {
                if (null == tag || tag.length() == 0)
                    continue;
                showFragmentByTag(tag);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => showFragmentsByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean showFragmentByTag(@Nullable String tag) {
        try {
            if (null == tag || tag.length() == 0)
                throw new Exception("tag is null");
            Fragment fragmentByTag = findFragmentByTag(tag);
            if (null == fragmentByTag)
                throw new Exception("error: fragmentByTag null");
            return showFragment(fragmentByTag);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => showFragmentByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean showFragments(@Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                showFragment(fragment);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => showFragments => " + e.getMessage());
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
            fragmentTransaction.commit();
            View view = fragment.getView();
            if (null == view)
                throw new Exception("warning: view null");
            view.setVisibility(View.VISIBLE);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => showFragment => " + e.getMessage());
            return false;
        }
    }

    /****** hide  ******/

    default boolean hideFragmentsByTag(@Nullable List<String> tags) {
        try {
            if (null == tags)
                throw new Exception("error: tags null");
            for (String tag : tags) {
                if (null == tag || tag.length() == 0)
                    continue;
                hideFragmentByTag(tag);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragmentsByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean hideFragmentByTag(@Nullable String tag) {
        try {
            if (null == tag || tag.length() == 0)
                throw new Exception("tag is null");
            Fragment fragmentByTag = findFragmentByTag(tag);
            if (null == fragmentByTag)
                throw new Exception("error: fragmentByTag null");
            return hideFragment(fragmentByTag);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragmentByTag => " + e.getMessage());
            return false;
        }
    }

    default boolean hideFragments(@Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                hideFragment(fragment);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragments => " + e.getMessage());
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
            View view = fragment.getView();
            if (null == view)
                throw new Exception("warning: view null");
            view.setVisibility(View.INVISIBLE);
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragment => " + e.getMessage());
            return false;
        }
    }

    /****** replace  ******/

    default boolean replaceFragments(@IdRes int containerViewId, @Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                replaceFragment(containerViewId, fragment);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => hideFragments => " + e.getMessage());
            return false;
        }
    }

    default boolean replaceFragment(@IdRes int containerViewId, @Nullable androidx.fragment.app.Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.replace(containerViewId, fragment);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => replaceFragment => " + e.getMessage());
            return false;
        }
    }

    /****** add  ******/

    default boolean addFragments(@IdRes int containerViewId, @Nullable List<androidx.fragment.app.Fragment> fragments, @Nullable List<String> tags) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            if (null == tags)
                throw new Exception("error: tags null");
            int tagSize = tags.size();
            int fragmentSize = fragments.size();
            if (tagSize != fragmentSize)
                throw new Exception("error: tagSize != fragmentSize");
            for (int i = 0; i < fragmentSize; i++) {
                String tag = tags.get(i);
                if (null == tag)
                    continue;
                Fragment fragment = fragments.get(i);
                if (null == fragment)
                    continue;
                addFragment(containerViewId, fragment, tag);

            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => addFragments => " + e.getMessage());
            return false;
        }
    }

    default boolean addFragments(@IdRes int containerViewId, @Nullable List<androidx.fragment.app.Fragment> fragments) {
        try {
            if (null == fragments)
                throw new Exception("error: fragment null");
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (null == fragment)
                    continue;
                addFragment(containerViewId, fragment);
            }
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => addFragments => " + e.getMessage());
            return false;
        }
    }

    default boolean addFragment(@IdRes int containerViewId, @Nullable androidx.fragment.app.Fragment fragment) {
        return addFragment(containerViewId, fragment, null);
    }

    default boolean addFragment(@IdRes int containerViewId, @Nullable androidx.fragment.app.Fragment fragment, @NonNull String tag) {
        try {
            if (null == fragment)
                throw new Exception("fragment is null");
            androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManagerTransaction();
            if (null == fragmentTransaction)
                throw new Exception("fragmentTransaction is null");
            fragmentTransaction.add(containerViewId, fragment, tag);
            fragmentTransaction.commit();
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragmentManger => addFragment => " + e.getMessage());
            return false;
        }
    }
}
