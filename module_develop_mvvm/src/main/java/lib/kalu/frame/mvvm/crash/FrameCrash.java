package lib.kalu.frame.mvvm.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import lib.kalu.frame.mvvm.context.FrameContext;

public class FrameCrash implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler mDefalutCrashHandler;

    private static class Singleton {
        public static final FrameCrash INSTNCE = new FrameCrash();
    }

    public FrameCrash() {
    }

    public static FrameCrash getInstance() {
        return Singleton.INSTNCE;
    }

    public void init() {
        mDefalutCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {

            // step1
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(FrameContext.getApplicationContext(), "程序出现异常, 很抱歉即将退出", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();

            // step2
            File file = saveException(FrameContext.getApplicationContext(), e, t);

            // step3 => 上传服务器
            uploadFile(file);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            //交给系统默认程序处理
            SystemClock.sleep(3000);
            // 退出程序
            if (mDefalutCrashHandler != null) {
                mDefalutCrashHandler.uncaughtException(t, e);
            }
        }
    }

    /**
     * 异常处理消息到SD卡
     *
     * @param e
     * @param t
     * @return
     */
    private final File saveException(Context context, Throwable e, Thread t) {

        File parent = FrameContext.getApplicationContext().getFilesDir();
        File file = new File(parent, "crash");
        if (!file.exists()) {
            file.mkdirs();
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = format.format(date);
        File crashFile = new File(file, time);

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(crashFile)));
            printWriter.println("时间: " + time);
            printWriter.println("线程: " + t.getName());
            // 版本
            try {
                PackageManager pm = context.getPackageManager();
                PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
                printWriter.println("版本: " + packageInfo.versionName + "_" + packageInfo.versionCode);
            } catch (Exception e1) {
                printWriter.println("版本: null");
            }
            // 系统
            printWriter.println("系统: " + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);
            // 机型
            printWriter.println("机型: " + Build.BOARD + "_" + Build.MANUFACTURER);
            // 型号
            printWriter.println("型号: " + Build.MODEL);
            // 错误
            printWriter.println("错误: " + e.getMessage());
            // 异常
            printWriter.println("\n");
            e.printStackTrace(printWriter);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
        return crashFile;
    }

    public void uploadFile(File file) {

    }
}