package lib.kalu.frame.mvvm.util;

import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Keep
public final class MvvmUtil {

    private static final String TAG = "module_okhttp";
    private static boolean PRINT = false;

    public static final boolean isLogger() {
        return PRINT;
    }

    public static final void setLogger(@NonNull boolean print) {
        PRINT = print;
    }

    public static void logE(@NonNull String message) {
        logE(TAG, message, null);
    }

    public static void logE(@NonNull String message, @Nullable Throwable throwable) {

        logE(TAG, message, throwable);
    }

    public static void logE(@NonNull String tag, @NonNull String message, @Nullable Throwable throwable) {

        if (!PRINT)
            return;

        if (null == message || message.length() <= 0)
            return;

        Log.e(tag, message, throwable);
    }
}
