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
                showToast(FrameContext.getApplicationContext(), (String) msg.obj, false);
            }
        }
    };

    public static void showToast(Context context, @StringRes int res) {
        try {
            String string = context.getResources().getString(res);
            if (null == string || string.length() == 0)
                throw new Exception("string error: " + string);
            showToast(context, string, true);
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }

    public static void showToast(Context context, String s) {
        showToast(context, s, true);
    }

    private static void showToast(Context context, String msg, boolean isFromUser) {
        try {
            if (null == context)
                throw new Exception("context error: null");
            if (null == msg || msg.length() == 0)
                throw new Exception("msg error: " + msg);
            Thread currentThread = Thread.currentThread();
            if (null == currentThread)
                throw new Exception("currentThread error: null");
            Thread mainThread = Looper.getMainLooper().getThread();
            if (null == mainThread)
                throw new Exception("mainThread error: null");
            if (currentThread == mainThread || !isFromUser) {
                if (!isFromUser || null == mToast) {
                    mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(msg);
                }
                mToast.show();
            } else {
                Message message = new Message();
                message.what = 11011;
                message.obj = msg;
                mHandler.sendMessage(message);
            }
        } catch (Exception e) {
            MvpUtil.logE("ToastUtil => showToast => " + e.getMessage());
        }
    }
}
