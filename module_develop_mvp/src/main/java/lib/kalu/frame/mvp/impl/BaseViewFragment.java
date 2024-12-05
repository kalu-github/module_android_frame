
package lib.kalu.frame.mvp.impl;

import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lib.kalu.frame.mvp.BaseFragment;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewFragment extends BaseViewContext {

    default boolean isFragmentVisible(@Nullable Fragment fragment) {
        try {
            if (null == fragment)
                throw new Exception("error: fragment null");
            return fragment.isVisible();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragment => isFragmentVisible => " + e.getMessage());
            return false;
        }
    }

    default boolean setFragmentVisibility(@Nullable Fragment fragment, int visibility) {
        try {
            if (null == fragment)
                throw new Exception("error: fragment null");
            View fragmentView = fragment.getView();
            if (null == fragmentView)
                throw new Exception("error: fragmentView null");
            fragmentView.setVisibility(visibility);
            return true;
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragment => setFragmentVisibility => " + e.getMessage());
            return false;
        }
    }
}
