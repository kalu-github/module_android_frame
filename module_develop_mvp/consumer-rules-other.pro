
# 保护butterknife
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# 保护Fastjson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn com.alibaba.fastjson.**

# 保护Gson
-dontwarn sun.misc.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken
-keepattributes AnnotationDefault,RuntimeVisibleAnnotations


# 保护glide
-dontwarn com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# 保护Fresco
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip
-keep,allowobfuscation @interface com.facebook.soloader.DoNotOptimize
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}
-keep @com.facebook.soloader.DoNotOptimize class *
-keepclassmembers class * {
    @com.facebook.soloader.DoNotOptimize *;
}
-keepclassmembers class * {
    native <methods>;
}

# 保护okio
-dontwarn okio.**
-dontwarn com.squareup.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

# 保护okhttp
-dontwarn com.squareup.okhttp.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.* { *; }
-keep interface com.squareup.okhttp.** { *; }

# 保护okhttp3
-dontwarn okhttp3.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# 保护Retrofit
-dontwarn retrofit.**
-keepattributes Signature
-keepattributes Exceptions
-keep class retrofit.** { *; }

# 保护Retrofit2
-dontwarn retrofit2.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepattributes Signature
-keepattributes Exceptions
-keep class retrofit2.** { *; }
-keep public class * extends retrofit2.Converter {*;}

# 保护Rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontnote rx.internal.util.PlatformDependent

# 保护Rxandroid
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

# 保护Guava
-keep class com.google.common.io.Resources {
    public static <methods>;
}
-keep class com.google.common.collect.Lists {
    public static ** reverse(**);
}
-keep class com.google.common.base.Charsets {
    public static <fields>;
}

-keep class com.google.common.base.Joiner {
    public static com.google.common.base.Joiner on(java.lang.String);
    public ** join(...);
}
-keep class com.google.common.collect.MapMakerInternalMap$ReferenceEntry
-keep class com.google.common.cache.LocalCache$ReferenceEntry
# 25.0-android
-dontwarn afu.org.checkerframework.**
-dontwarn org.checkerframework.**
-dontwarn com.google.errorprone.**
-dontwarn sun.misc.Unsafe
-dontwarn java.lang.ClassValue
-dontwarn javax.inject.**
# Guava 19.0
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement