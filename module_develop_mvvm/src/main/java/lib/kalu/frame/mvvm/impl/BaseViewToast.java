

package lib.kalu.frame.mvvm.impl;

import android.content.Context;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import lib.kalu.frame.mvvm.util.MvpUtil;
import lib.kalu.frame.mvvm.util.ToastUtil;

@Keep
public interface BaseViewToast extends BaseViewContext {

    default void showToast(@NonNull Throwable throwable) {
        try {
            String message = throwable.getMessage();
            showToast(message);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewToast => showToast => " + e.getMessage());
        }
    }

    default void showToast(@StringRes int resId) {
        try {
            Context context = getContext();
            String string = context.getResources().getString(resId);
            showToast(string);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewToast => showToast => " + e.getMessage());
        }
    }

    default void showToast(@NonNull String s) {
        try {
            if (null == s || s.length() == 0)
                throw new Exception("message error: " + s);
            Context context = getContext();
            if (null == context)
                throw new Exception("context error: null");
            ToastUtil.showToast(context, s);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewToast => showToast => " + e.getMessage());
        }
    }
}
