# 禁止开启
# 检测并移除没有用到的类，变量，方法和属性；
# -dontshrink
# 优化代码，非入口节点类会加上private/static/final, 没有用到的参数会被删除，一些方法可能会变成内联代码
# -dontoptimize
# 使用短又没有语义的名字重命名非入口类的类名，变量名，方法名。入口类的名字保持不变。
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
# 预校验代码是否符合Java1.6或者更高的规范(唯一一个与入口类不相关的步骤)
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
-keepattributes Exceptions,SourceFile,LineNumberTable

# 保护泛型
-keepattributes Signature

# 保护注解
-keepattributes *Annotation*,InnerClasses,EnclosingMethod
-keep @interface * {
    *;
}