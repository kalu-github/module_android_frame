# 检测并移除没有用到的类，变量，方法和属性；
-dontshrink
# 优化代码，非入口节点类会加上private/static/final, 没有用到的参数会被删除，一些方法可能会变成内联代码
-dontoptimize

# 指定method模糊字典
-obfuscationdictionary proguard-rules-dict-method.txt
# 指定class模糊字典
-classobfuscationdictionary proguard-rules-dict-class.txt
# 指定package模糊字典
-packageobfuscationdictionary proguard-rules-dict-package.txt

-dontwarn org.conscrypt.Conscrypt$ProviderBuilder
-dontwarn org.conscrypt.Conscrypt

-keep class com.google.gson.**{*;}
-keep class org.reactivestreams.**{*;}
-keep class retrofit2.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-keep class io.reactivex.**{*;}