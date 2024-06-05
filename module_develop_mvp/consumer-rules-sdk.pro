
# 保护核心
-dontwarn lib.kalu.frame.mvp.**

# 保护BaseClient
-keep class lib.kalu.frame.mvp.http.BaseClient{
    public <fields>;
    public <methods>;
}

# 保护bean
-keep class lib.kalu.frame.mvp.bean.** {*;}
-keep class * extends lib.kalu.frame.mvp.bean {*;}

# 保护FrameContext
-keep class lib.kalu.frame.mvp.context.FrameContext {
    public <fields>;
    public <methods>;
}

# 保护util
-keep class lib.kalu.frame.mvp.util.** {*;}

# 保护crash
-keep class lib.kalu.frame.mvp.crash.CrashHandler {
    public <fields>;
    public <methods>;
}

# 保护OkhttpGlide
-keep class lib.kalu.frame.mvp.glide.OkhttpGlideInterceptor {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.glide.OkhttpGlideModule {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.glide.OkhttpGlideProgressListener {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.glide.OkhttpGlideUtil {
    public <fields>;
    public <methods>;
}

# 保护interceptor
-keep class lib.kalu.frame.mvp.interceptor.OkhttpImpl {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.interceptor.OkhttpInterceptor {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.interceptor.OkhttpInterceptorStandard {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.listener.OnRequestChangeListener {
    public <fields>;
    public <methods>;
}

# 保护 ComposeSchedulers
-keep class lib.kalu.frame.mvp.transformer.ComposeSchedulers {
    public <fields>;
    public <methods>;
}

# 保护Base**
-keep class lib.kalu.frame.mvp.BaseActivity {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseActivityKillProcess {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseApplication {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseApplicationKillProcess {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseDialogFragment {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseFragment {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseFragmentPagerAdapter {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseFragmentStatePagerAdapter {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseModel {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BasePresenter {
    public <init>(...);
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseView {
    public <fields>;
    public <methods>;
}
#-keep public class * extends BaseActivity
#-keep public class * extends BaseActivityKillProcess
#-keep public class * extends BaseApplication
#-keep public class * extends BaseApplicationKillProcess
#-keep public class * extends BaseDialogFragment
#-keep public class * extends BaseFragment
#-keep public class * extends BaseFragmentPagerAdapter
#-keep public class * extends BaseFragmentStatePagerAdapter
##-keep public class * extends BaseModel
##-keep public class * extends BasePresenter
##-keep public class * extends BaseView
#-keep class * extends lib.kalu.frame.mvp.BasePresenter  {
#      public <init>(...);
#}
#-keep class lib.kalu.frame.mvp.BaseActivity {*;}
#-keep class lib.kalu.frame.mvp.BaseActivityKillProcess {*;}
#-keep class lib.kalu.frame.mvp.BaseApplication {*;}
#-keep class lib.kalu.frame.mvp.BaseApplicationKillProcess {*;}
#-keep class lib.kalu.frame.mvp.BaseDialogFragment {*;}
#-keep class lib.kalu.frame.mvp.BaseFragment {*;}
#-keep class lib.kalu.frame.mvp.BaseFragmentPagerAdapter {*;}
#-keep class lib.kalu.frame.mvp.BaseFragmentStatePagerAdapter {*;}
#-keep class lib.kalu.frame.mvp.BaseModel {*;}
#-keep class lib.kalu.frame.mvp.BasePresenter {*;}
#-keep class lib.kalu.frame.mvp.BaseView {*;}