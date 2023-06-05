# mvp
-keep class * extends lib.kalu.frame.mvp.BasePresenter
-keep class * extends lib.kalu.frame.mvp.BasePresenter  {
      public <init>(...);
 }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Retrolambda
-dontwarn java.lang.invoke.*

# RxAndroid
-dontwarn io.reactivex.android.**
-keep class io.reactivex.android.** { *; }

# Rxjava
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }

# OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken