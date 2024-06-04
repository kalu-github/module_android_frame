# 指定外部模糊字典
-obfuscationdictionary proguard-rules-dict-mini.txt
# 指定class模糊字典
-classobfuscationdictionary proguard-rules-dict-mini.txt
# 指定package模糊字典
-packageobfuscationdictionary proguard-rules-dict-mini.txt

-dontwarn org.conscrypt.Conscrypt$ProviderBuilder
-dontwarn org.conscrypt.Conscrypt

-keep class com.google.gson.**{*;}
-keep class org.reactivestreams.**{*;}
-keep class retrofit2.**{*;}
-keep class okhttp3.**{*;}
-keep class okio.**{*;}
-keep class io.reactivex.**{*;}