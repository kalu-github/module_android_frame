package lib.kalu.frame.mvp.logcat;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public final class LogcatService extends Service {

    public static final String FLAG_LOGCAT = "FLAG_LOGCAT";
    public static final String FLAG_LOGCAT_START = "FLAG_LOGCAT_START";
    public static final String FLAG_LOGCAT_STOP = "FLAG_LOGCAT_STOP";
    public static final String FLAG_LOGCAT_RELEASE = "FLAG_LOGCAT_RELEASE";
    public static final String FLAG_LOGCAT_UPLOAD = "FLAG_LOGCAT_UPLOAD";
    public static final String FLAG_LOGCAT_DELETE_ALL = "FLAG_LOGCAT_DELETE_ALL";
    public static final String FLAG_LOGCAT_DELETE_TODAY = "FLAG_LOGCAT_DELETE_TODAY";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        LogcatHelper.getInstance().release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String logFlag = intent.getStringExtra(FLAG_LOGCAT);

            if (FLAG_LOGCAT_START.equals(logFlag)) {
                LogcatHelper.getInstance().start();
            }

            if (FLAG_LOGCAT_STOP.equals(logFlag)) {
                LogcatHelper.getInstance().stop();
            }

            if (FLAG_LOGCAT_RELEASE.equals(logFlag)) {
                LogcatHelper.getInstance().release();
            }

            if (FLAG_LOGCAT_UPLOAD.equals(logFlag)) {
                LogcatHelper.getInstance().upolod();
            }

            if (FLAG_LOGCAT_DELETE_ALL.equals(logFlag)) {
                LogcatHelper.getInstance().deleteLogAll();
            }

            if (FLAG_LOGCAT_DELETE_TODAY.equals(logFlag)) {
                LogcatHelper.getInstance().deleteLogToday();
            }
        }
        return START_NOT_STICKY;
    }
}
