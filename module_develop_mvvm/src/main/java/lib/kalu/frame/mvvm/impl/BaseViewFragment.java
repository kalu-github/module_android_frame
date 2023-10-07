
package lib.kalu.frame.mvvm.impl;

import androidx.annotation.Keep;

import lib.kalu.frame.mvvm.BaseFragment;
import lib.kalu.frame.mvvm.util.MvvmUtil;

@Keep
public interface BaseViewFragment extends BaseViewContext {

    default boolean isFragmentVisible() {
        try {
            return ((BaseFragment) this).isVisible();
        } catch (Exception e) {
            MvvmUtil.logE("BaseViewFragment => isFragmentVisible => " + e.getMessage());
            return false;
        }
    }
}
