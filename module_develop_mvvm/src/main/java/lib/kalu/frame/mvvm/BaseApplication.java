package lib.kalu.frame.mvvm;

import android.app.Application;
import android.content.Context;

import lib.kalu.frame.mvp.context.FrameContext;

public class BaseApplication extends Application {

    public static final Context getContext() {
        return FrameContext.getApplicationContext();
    }
}
