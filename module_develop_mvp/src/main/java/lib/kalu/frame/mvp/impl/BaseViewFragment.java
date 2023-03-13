
package lib.kalu.frame.mvp.impl;

import androidx.annotation.Keep;

import lib.kalu.frame.mvp.BaseFragment;
import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewFragment {

    default boolean isFragmentVisible() {
        try {
            return ((BaseFragment) this).isVisible();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewFragment => isFragmentVisible => " + e.getMessage());
            return false;
        }
    }
}
