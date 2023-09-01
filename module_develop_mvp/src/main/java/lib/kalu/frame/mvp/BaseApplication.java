package lib.kalu.frame.mvp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

import lib.kalu.frame.mvp.context.FrameContext;
import lib.kalu.frame.mvp.util.MvpUtil;

public class BaseApplication extends Application {

    public static final Context getContext() {
        return FrameContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initWebViewProcess();
    }

    private void initWebViewProcess() {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P)
                throw new Exception("SDK_INT warning: not need");
            String processName = getProcessName();
            WebView.setDataDirectorySuffix(processName);
        } catch (Exception e) {
            MvpUtil.logE("BaseApplication => initWebViewProcess => " + e.getMessage());
        }
    }
}
