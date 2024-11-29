package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import lib.kalu.frame.mvp.context.FrameContext;

public final class ToastUtil {

    private static Toast mToast = null;

    public static void showToast(Context context, @StringRes int res) {
        try {
            String string = context.getResources().getString(res);
            if (null == string || string.length() == 0)
                throw new Exception("string error: " + string);
            showToast(context, string);
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }

    public static void showToast(Context context, String msg) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            if (null == msg || msg.length() == 0)
                throw new Exception("msg error: " + msg);
//            Looper looper = Looper.myLooper();
//            if (null == looper) {
//                Looper.prepare();
//            }
            if (null == mToast) {
                mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(msg);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
//            if (null == looper) {
//                Looper.loop();
//            }
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }
}
