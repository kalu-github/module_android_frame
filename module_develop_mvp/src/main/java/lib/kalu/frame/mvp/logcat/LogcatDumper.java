package lib.kalu.frame.mvp.logcat;

import android.content.Context;
import android.content.Intent;

import lib.kalu.frame.mvp.context.FrameContext;

public final class LogcatDumper {

    public static void start() {
        Context context = FrameContext.getApplicationContext();
        Intent intent = new Intent(context, LogcatService.class);
        intent.putExtra(LogcatService.FLAG_LOGCAT, LogcatService.FLAG_LOGCAT_START);
        context.startService(intent);
    }

    public static void stop() {
        Context context = FrameContext.getApplicationContext();
        Intent intent = new Intent(context, LogcatService.class);
        intent.putExtra(LogcatService.FLAG_LOGCAT, LogcatService.FLAG_LOGCAT_STOP);
        context.stopService(intent);
    }
}
