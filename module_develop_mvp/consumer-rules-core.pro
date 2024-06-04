# 禁止开启
# 检测并移除没有用到的类，变量，方法和属性；
# -dontshrink
# 优化代码，非入口节点类会加上private/static/final, 在字节码级别执行优化，让应用运行的更快。。
# -dontoptimize
# 增大反编译难度，类和类成员会被随机命名，除非用keep保护。
# -dontobfuscate
# 是否允许改变作用域(Android private、protected自动变public问题解决)
# -allowaccessmodification
# 当有优化和使用-repackageclasses时才适用。
# -repackageclasses com.test

# 默认开启
# 混淆算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 混淆时是否记录日志, 混淆后类名的映射关系
-verbose
# 预校验代码是否符合Java1.6或者更高的规范, Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 忽略警告 不忽略可能打包不成功
-ignorewarnings
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共的库类。
-dontskipnonpubliclibraryclasses
# 指定不去忽略包可见的库类的成员。
-dontskipnonpubliclibraryclassmembers

# 保护主动抛出异常
#-keepattributes Exceptions
# 保护主动抛出的异常代码行号
-keepattributes Exceptions,SourceFile,LineNumberTable

# 保护泛型
-keepattributes Signature

# 保护注解
-keepattributes *Annotation*,InnerClasses,EnclosingMethod
-keep @interface * {
    *;
}