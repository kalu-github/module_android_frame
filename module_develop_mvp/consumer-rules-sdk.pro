
# 保护核心
-dontwarn lib.kalu.frame.mvp.**

# logcat
-keep class lib.kalu.frame.mvp.logcat.LogcatDumper {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.logcat.CrashDumper {
    public <fields>;
    public <methods>;
}

# 保护BaseClient
-keep class lib.kalu.frame.mvp.http.BaseClient{
    public <fields>;
    public <methods>;
    protected int initMaxRequests();
    protected int initMaxRequestsPerHost();
    protected int initReadTimeout();
    protected int initWriteTimeout();
    protected java.lang.String initBaseUrl();
    protected *** initInterceptor();
    protected boolean initProxy();
    public *** getApiService(***);
}

# 保护bean
-keep class lib.kalu.frame.mvp.bean.** {*;}
-keep class * extends lib.kalu.frame.mvp.bean.** {*;}

# 保护FrameContext
-keep class lib.kalu.frame.mvp.context.FrameContext {
    public <fields>;
    public <methods>;
}

# 保护util
-keep class lib.kalu.frame.mvp.util.** {
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
-keep class lib.kalu.frame.mvp.BaseActivity {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseActivityKillProcess {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.BaseModel {
    public <init>(...);
    public <fields>;
    public <methods>;
}
-keep class * extends lib.kalu.frame.mvp.BaseModel  {
      public <init>(...);
}
-keep class lib.kalu.frame.mvp.BasePresenter {
    public <init>(...);
    public <fields>;
    public <methods>;
}
-keep class * extends lib.kalu.frame.mvp.BasePresenter  {
      public <init>(...);
}
-keep class lib.kalu.frame.mvp.BaseView {
    public <fields>;
    public <methods>;
}
-keep class lib.kalu.frame.mvp.impl.** {
    public <fields>;
    public <methods>;
}