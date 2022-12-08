# mvvm
-keep class * extends lib.kalu.frame.mvvm.BaseActivity { *;}
-keep class * extends lib.kalu.frame.mvvm.BaseActivity  {
      public <init>();
 }
-keep class * extends lib.kalu.frame.mvvm.BaseModel { *;}
-keep class * extends lib.kalu.frame.mvvm.BaseModel  {
      public <init>();
 }
 -keep class * extends lib.kalu.frame.mvvm.BaseViewModel { *;}
 -keep class * extends lib.kalu.frame.mvvm.BaseViewModel  {
       public <init>();
  }
-keep class * implements lib.kalu.frame.mvvm.BaseView { *;}
-keep class * implements lib.kalu.frame.mvvm.BaseView  {
      public <init>();
 }


# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions


# Retrolambda
-dontwarn java.lang.invoke.*

# Rxjava
-dontwarn rx.**

# RxJava RxAndroid
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