
package lib.kalu.frame.mvp.impl;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Keep;
import androidx.fragment.app.Fragment;

import lib.kalu.frame.mvp.util.MvpUtil;
import lib.kalu.frame.mvp.util.WrapperUtil;

@Keep
public interface BaseViewActivity extends BaseViewContext {

    default Activity getWrapperActivity(Context context) {
        return WrapperUtil.getWrapperActivity(context);
    }

    default Activity getWrapperActivity() {
        Context context = getContext();
        return WrapperUtil.getWrapperActivity(context);
    }

    default void callFinish() {
        try {
            if (this instanceof Activity) {
                ((Activity) this).finish();
            } else if (this instanceof Fragment) {
                ((Fragment) this).getActivity().finish();
            } else {
                throw new Exception("not activity or fragment");
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewActivity => callFinish => " + e.getMessage());
        }
    }

    default void callOnBackPressed() {
        try {
            if (this instanceof Activity) {
                ((Activity) this).onBackPressed();
            } else if (this instanceof Fragment) {
                ((Fragment) this).getActivity().onBackPressed();
            } else {
                throw new Exception("not activity or fragment");
            }
        } catch (Exception e) {
            MvpUtil.logE("BaseViewActivity => callOnBackPressed => " + e.getMessage());
        }
    }
}
