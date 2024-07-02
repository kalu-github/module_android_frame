package lib.kalu.frame.mvp.logcat;


import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import lib.kalu.frame.mvp.context.FrameContext;

final class LogcatHelper {

    private LogDumper mLogDumper;

    private static class Holder {
        private static final LogcatHelper M = new LogcatHelper();
    }

    public static LogcatHelper getInstance() {
        return Holder.M;
    }

    private LogcatHelper() {
        release();
        check();
    }

    private void check() {
        mLogDumper = new LogDumper();
    }

    public void start() {
        try {
            boolean running = isRunning();
            if (running)
                throw new Exception("warning: running false");
            boolean alive = isAlive();
            if (alive)
                throw new Exception("warning: alive true");
            mLogDumper.start();
        } catch (Exception e) {
        }
    }

    public void stop() {
        try {
            boolean running = isRunning();
            if (!running)
                throw new Exception("warning: running false");
            boolean alive = isAlive();
            if (!alive)
                throw new Exception("warning: alive false");
            mLogDumper.stopLogs();
        } catch (Exception e) {
        }
    }

    public void release() {
        stop();
        if (null != mLogDumper) {
            mLogDumper = null;
        }
    }

    public boolean isRunning() {
        try {
            if (null == mLogDumper)
                throw new Exception("error: mLogDumper null");
            return mLogDumper.isRunning();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAlive() {
        try {
            if (null == mLogDumper)
                throw new Exception("error: mLogDumper null");
            return mLogDumper.isAlive();
        } catch (Exception e) {
            return false;
        }
    }

    public void upolod() {
        try {
        } catch (Exception e) {
        }
    }

    public void deleteAllLog() {
        try {
            Context context = FrameContext.getApplicationContext();
            File dir = context.getFilesDir();
            if (!dir.exists())
                throw new Exception("");
            File logger = new File(dir, "logger");
            if (!logger.exists())
                throw new Exception();
            new Thread() {
                @Override
                public void run() {
                    logger.delete();
                }
            }.start();
        } catch (Exception e) {
        }
    }

    public void deleteTodayLog() {
        try {
            Context context = FrameContext.getApplicationContext();
            File dir = context.getFilesDir();
            if (!dir.exists())
                throw new Exception("");
            File logger = new File(dir, "logger");
            if (!logger.exists())
                throw new Exception();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(new Date());
            File today = new File(logger, format);
            if (!today.exists())
                throw new Exception();
            new Thread() {
                @Override
                public void run() {
                    today.delete();
                }
            }.start();
        } catch (Exception e) {
        }
    }

    private final class LogDumper extends Thread {
        private Process logcatProc;
        private BufferedReader mReader = null;
        private boolean mRunning = true;
        private OutputStreamWriter out = null;

        public LogDumper() {
            try {

                Context context = FrameContext.getApplicationContext();
                File dir = context.getFilesDir();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File logger = new File(dir, "logger");
                if (!logger.exists()) {
                    logger.mkdirs();
                }

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String fileDir = sdf1.format(new Date());
                String fileName = sdf2.format(new Date());

                File file1 = new File(logger, fileDir);
                if (!file1.exists()) {
                    file1.mkdirs();
                }

                StringBuilder builder = new StringBuilder();
                builder.append(fileName);
                builder.append(".log");

                String toString = builder.toString();
                File file2 = new File(file1, toString);

                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                out = new OutputStreamWriter(fileOutputStream, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void stopLogs() {
            mRunning = false;
        }

        public boolean isRunning() {
            return mRunning;
        }

        @Override
        public void run() {
            try {
                String cmd = "logcat | grep \"(" + android.os.Process.myPid() + ")\"";
                logcatProc = Runtime.getRuntime().exec(cmd);
                InputStream inputStream = logcatProc.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");

                mReader = new BufferedReader(streamReader, 1024);

                String line = null;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

                while (mRunning && (line = mReader.readLine()) != null) {
                    if (!mRunning) {
                        break;
                    }
                    if (line.length() == 0) {
                        continue;
                    }
                    if (out != null) {
                        String format = sdf.format(new Date());
                        out.write(format + " -> " + line + "  \n");
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (logcatProc != null) {
                        logcatProc.destroy();
                        logcatProc = null;
                    }
                    if (mReader != null) {
                        mReader.close();
                        mReader = null;
                    }
                    if (out != null) {
                        out.close();
                        out = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
