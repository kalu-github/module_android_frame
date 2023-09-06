package lib.kalu.frame.mvp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import lib.kalu.frame.mvp.context.FrameContext;
import lib.kalu.frame.mvp.util.MvpUtil;

public class BaseApplication extends Application {

    public static final Context getContext() {
        return FrameContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRxJavaPlugins();
        initWebViewProcess();
    }

    private void initRxJavaPlugins() {
        try {
            RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) {
                    MvpUtil.logE("BaseApplication => initRxJavaPlugins => setErrorHandler => " + throwable.getMessage());
                }
            });
        } catch (Exception e) {
            MvpUtil.logE("BaseApplication => initRxJavaPlugins => " + e.getMessage());
        }
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
