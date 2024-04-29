# 不要压缩(这个必须，因为开启混淆的时候 默认 会把没有被调用的代码 全都排除掉)
# -dontshrink
# 忽略警告 不忽略可能打包不成功
-ignorewarnings
# 保护泛型 如果混淆报错建议关掉
-keepattributes Signature
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 优化时允许访问并修改有修饰符的类和类的成员，这可以提高优化步骤的结果,比如，当内联一个公共的getter方法时，这也可能需要外地公共访问。虽然java二进制规范不需要这个，要不然有的虚拟机处理这些代码会有问题。当有优化和使用-repackageclasses时才适用。指示语：不能用这个指令处理库中的代码，因为有的类和类成员没有设计成public ,而在api中可能变成public
-allowaccessmodification
# 当有优化和使用-repackageclasses时才适用。
#-repackageclasses com.test
# 这句话能够使我们的项目混淆后产生映射文件, 包含有类名->混淆后类名的映射关系
-verbose
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 指定混淆是采用的算法，后面的参数是一个过滤器, 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*

##### android #####
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
-keep class com.google.android.material.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-keep interface androidx.** {*;}
-keep class androidx.** {*;}
-dontwarn androidx.**
-dontnote androidx.**
-keep class androidx.core.app.CoreComponentFactory{*;}
-keep public class * extends androidx.**
-dontwarn androidx.**
-keep class **.R$* {*;}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers class * extends androidx.appcompat.app.AppCompatActivity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
-dontwarn java.lang.invoke.*
-keep class androidx.annotation.Keep
-keep class android.support.annotation.Keep
-keep @androidx.annotation.Keep class * {*;}
-keep @android.support.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <init>(...);
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}

##### retrofit #####
-keepattributes Signature
-keepattributes Exceptions
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep public class * extends retrofit2.Converter {*;}

##### rxjava|rxanroid #####
-keep class io.reactivex.Observable
-keepclassmembers class * implements io.reactivex.Observer {
    public void onSubscribe(io.reactivex.disposables.Disposable);
    public void onNext(**);
    public void onError(java.lang.Throwable);
    public void onComplete();
}
-keepclassmembers class * implements io.reactivex.SingleObserver {
    public void onSubscribe(io.reactivex.disposables.Disposable);
    public void onSuccess(**);
    public void onError(java.lang.Throwable);
}
-keepclassmembers class * implements io.reactivex.CompletableObserver {
    public void onSubscribe(io.reactivex.disposables.Disposable);
    public void onComplete();
    public void onError(java.lang.Throwable);
}
-keepclassmembers class * implements io.reactivex.MaybeObserver {
    public void onSubscribe(io.reactivex.disposables.Disposable);
    public void onSuccess(**);
    public void onError(java.lang.Throwable);
    public void onComplete();
}
-keepclassmembers class * implements io.reactivex.FlowableSubscriber {
    public void onSubscribe(io.reactivex.subscribers.Subscription);
    public void onNext(**);
    public void onError(java.lang.Throwable);
    public void onComplete();
}
-keepclassmembers class * implements io.reactivex.ObservableEmitter {
    public void setDisposable(io.reactivex.disposables.Disposable);
    public boolean isDisposed();
    public void onNext(**);
    public void onError(java.lang.Throwable);
    public void onComplete();
}
-keepclassmembers class * implements io.reactivex.SingleEmitter {
    public void setDisposable(io.reactivex.disposables.Disposable);
    public boolean isDisposed();
    public void onSuccess(**);
    public void onError(java.lang.Throwable);
}
-keepclassmembers class * implements io.reactivex.CompletableEmitter {
    public void setDisposable(io.reactivex.disposables.Disposable);
    public boolean isDisposed();
    public void onComplete();
    public void onError(java.lang.Throwable);
}
-keepclassmembers class * implements io.reactivex.MaybeEmitter {
    public void setDisposable(io.reactivex.disposables.Disposable);
    public boolean isDisposed();
    public void onSuccess();
}

##### okhttp #####
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.* { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

##### okhttp3 #####
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn com.squareup.okhttp3.**

##### okio #####
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

##### gson #####
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

##### glide #####
-dontwarn com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

##### butterknife #####
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector{ *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

##### fastjson #####
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*;}

##### umeng #####
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


##### wechat #####
-keep class com.tencent.mm.opensdk.** { *; }
-keep class com.tencent.wxop.** { *; }
-keep class com.tencent.mm.sdk.** { *; }

##### jpush #####
-dontoptimize
-dontpreverify
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }
-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

##### tencent #####
-keep class com.tencent.smtt.export.external.**{
    *;
}
-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
    *;
}
-keep class com.tencent.smtt.sdk.CacheManager {
    public *;
}
-keep class com.tencent.smtt.sdk.CookieManager {
    public *;
}
-keep class com.tencent.smtt.sdk.WebHistoryItem {
    public *;
}
-keep class com.tencent.smtt.sdk.WebViewDatabase {
    public *;
}
-keep class com.tencent.smtt.sdk.WebBackForwardList {
    public *;
}
-keep public class com.tencent.smtt.sdk.WebView {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
    public static final <fields>;
    public java.lang.String getExtra();
    public int getType();
}
-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
    public <fields>;
    public <methods>;
}
-keepattributes InnerClasses
-keep public enum com.tencent.smtt.sdk.WebSettings$** {
    *;
}
-keep public enum com.tencent.smtt.sdk.QbSdk$** {
    *;
}
-keep public class com.tencent.smtt.sdk.WebSettings {
    public *;
}
-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebViewClient {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
    public <fields>;
    public <methods>;
}
-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
    public *;
}
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
    public protected *;
}
-keep public class com.tencent.smtt.export.external.interfaces.* {
    public protected *;
}
-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
    public protected *;
}
-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebIconDatabase {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.WebStorage {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.DownloadListener {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.QbSdk {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.Tbs* {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.utils.LogFileUtils {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLog {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLogClient {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
    public <fields>;
    public <methods>;
}
-keep public class com.tencent.smtt.utils.Apn {
    public <fields>;
    public <methods>;
}
-keep class com.tencent.smtt.** {
    *;
}
-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
    public <fields>;
    public <methods>;
}
-keep class MTT.ThirdAppInfoNew {
    *;
}
-keep class com.tencent.mtt.MttTraceEvent {
    *;
}
-keep public class com.tencent.smtt.gamesdk.* {
    public protected *;
}
-keep public class com.tencent.smtt.sdk.TBSGameBooter {
        public <fields>;
        public <methods>;
}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
    public protected *;
}
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
    public protected *;
}
-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
    public *;
}

##### mvp #####
-dontwarn lib.kalu.frame.mvp.**
-keep class * extends lib.kalu.frame.mvp.BasePresenter  {
      public <init>(...);
}
#-keep class lib.kalu.frame.mvp.bean.** {*;}
#-keep class lib.kalu.frame.mvp.util.** {*;}
#-keep class lib.kalu.frame.mvp.context.FrameContext {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.crash.CrashHandler {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.glide.OkhttpGlideInterceptor {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.glide.OkhttpGlideModule {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.glide.OkhttpGlideProgressListener {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.glide.OkhttpGlideUtil {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.http.BaseClient {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.interceptor.OkhttpImpl {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.interceptor.OkhttpInterceptorStandard {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.listener.OnRequestChangeListener {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.transformer.ComposeSchedulers {
#    public <fields>;
#    public <methods>;
#}
#
#-keep class lib.kalu.frame.mvp.BaseActivity {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseActivityKillProcess {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseApplication {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseApplicationKillProcess {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseDialogFragment {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseFragment {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseFragmentPagerAdapter {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseFragmentStatePagerAdapter {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseModel {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BasePresenter {
#    public <fields>;
#    public <methods>;
#}
#-keep class lib.kalu.frame.mvp.BaseView {
#    public <fields>;
#    public <methods>;
#}