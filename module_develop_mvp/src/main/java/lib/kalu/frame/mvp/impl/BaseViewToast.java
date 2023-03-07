

package lib.kalu.frame.mvp.impl;

import android.content.Context;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
public interface BaseViewToast extends BaseViewContext {

    default void showToast(@NonNull String s) {
        try {
            if (null != s && s.length() > 0) {
                Context context = getContext();
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }
}
