

package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import lib.kalu.frame.mvp.util.MvpUtil;

@Keep
public interface BaseViewToast extends BaseViewContext {

    default void showToast(@NonNull java.lang.Throwable throwable) {
        try {
            String message = throwable.getMessage();
            showToast(message);
        } catch (Exception e) {
            MvpUtil.logE("BaseViewToast => showToast => " + e.getMessage());
        }
    }

    default void showToast(@NonNull String s) {
        try {
            if (null == s || s.length() >= 0)
                throw new Exception("message is null");
            Context context = getContext();
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            MvpUtil.logE("BaseViewToast => showToast => " + e.getMessage());
        }
    }
}
