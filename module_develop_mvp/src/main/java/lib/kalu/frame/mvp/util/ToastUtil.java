package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public final class ToastUtil {

    private static Toast mToast = null;

    public static void showToast(Context context, String s) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            if (null == s || s.length() == 0)
                throw new Exception("message error: " + s);
            boolean isMainLooper = (Looper.myLooper() == Looper.getMainLooper());
            if (!isMainLooper) {
                Looper.prepare();
            }
            if (mToast == null) {
                mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(s);
            }
            mToast.show();
            if (!isMainLooper) {
                Looper.loop();
//                Looper.myLooper().quit();
            }
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }
}
