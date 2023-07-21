package lib.kalu.frame.mvp.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import lib.kalu.frame.mvp.context.FrameContext;

public final class ToastUtil {

    private static Toast mToast = null;

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11011) {
                showToast(FrameContext.getApplicationContext(), (String) msg.obj);
            }
        }
    };

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

    public static void showToast(Context context, String s) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            if (null == s || s.length() == 0)
                throw new Exception("message error: " + s);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (mToast == null) {
                    mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(s);
                }
                mToast.show();
            } else {
                Message message = new Message();
                message.what = 11011;
                message.obj = s;
                mHandler.sendMessage(message);
            }
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }
}
