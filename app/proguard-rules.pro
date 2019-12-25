# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Sdk\platforms\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#2.默认保留区
-dontskipnonpubliclibraryclasses # 不忽略非公共的库类
-optimizationpasses 5            # 指定代码的压缩级别
-dontusemixedcaseclassnames      # 是否使用大小写混合
-dontpreverify                   # 混淆时是否做预校验
-verbose                         # 混淆时是否记录日志
-keepattributes *Annotation*     # 保持注解
-ignorewarning                   # 忽略警告
-dontoptimize                    # 优化不优化输入的类文件
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

-keep public com.iruiyou.common.utils.SharePreferenceUtils
-keep public com.iruiyou.pet.rongyun.ConversationActivity
-keep public com.iruiyou.pet.rongyun.BaseActivity
-keep public com.iruiyou.pet.utils.Constants
-keep class android.support.** {*;}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes Exceptions,InnerClasses

-keepattributes Signature

# RongCloud SDK
-keep class io.rong.** {*;}
-keep class cn.rongcloud.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**

# VoIP
-keep class io.agora.rtc.** {*;}

# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}

# 红包
-keep class com.google.gson.** { *; }
-keep class com.uuhelper.Application.** {*;}
-keep class net.sourceforge.zbar.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.alipay.** {*;}
-keep class com.jrmf360.rylib.** {*;}

-ignorewarnings
# 实体类
-keep class [com.iruiyou.pet.bean].** { *; }
-keep class [com.iruiyou.pet.rongyun].** { *; }
-keep class [com.iruiyou.pet.base].** { *; }
-keep class [com.iruiyou.pet].** { *; }
-keep class [com.iruiyou.pet.utils].** { *; }
-keep class [com.iruiyou.common.utils].** { *; }
-keep class [com.iruiyou.http.retrofit_rx.Api].** { *; }


## ----------------------------------
##      自定义类不能混淆
## ----------------------------------
-keep class com.iruiyou.pet.rongyun.**{*;}
-keep class com.iruiyou.pet.base.**{*;}
-keep class com.iruiyou.pet.**{*;}
-keep class com.iruiyou.pet.utils.**{*;}
-keep class com.iruiyou.common.utils.**{*;}
-keep class com.iruiyou.http.retrofit_rx.Api.**{*;}
-keep class com.iruiyou.http.retrofit_rx.http.**{*;}
-keep class com.iruiyou.common.http.**{*;}
-keep class com.iruiyou.common.**{*;}

#-keep class io.rong.app.DemoNotificationReceiver {*;}
## ----------------------------------
##     Gson 相关的混淆配置
## ----------------------------------
-keepattributes Signature
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*; }
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

## ----------------------------------
##     butterknife 相关的混淆配置
## ----------------------------------
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

## ----------------------------------
##     EventBus 相关的混淆配置
## ----------------------------------
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
-keepclassmembers class ** {
    public void onEvent*(**);
}

## ----------------------------------
##    v4 包的混淆
## ----------------------------------
#-libraryjars ./libs/android-support-v4.jar
#-dontwarn android.support.**
#-dontwarn android.support.v4.**
-dontwarn **CompatHoneycomb
-dontwarn **CompatHoneycombMR2
-dontwarn **CompatCreatorHoneycombMR2
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment


# Youzan SDK
-dontwarn com.youzan.**
-keep class com.youzan.jsbridge.** { *; }
-keep class com.youzan.spiderman.** { *; }
-keep class com.youzan.androidsdk.** { *; }
-keep class com.youzan.x5web.** { *; }
-keep class com.youzan.androidsdkx5.** { *; }
-keep class dalvik.system.VMStack { *; }
-keep class com.tencent.smtt.** { *; }
-dontwarn  com.tencent.smtt.**

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class okio.**{*;}
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

# IM
-keep class com.youzan.mobile.zanim.model.** { *; }

-dontwarn java.nio.file.*
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#takephoto
-keep class com.jph.takephoto.** { *; }
-dontwarn com.jph.takephoto.**

-keep class com.darsh.multipleimageselect.** { *; }
-dontwarn com.darsh.multipleimageselect.**

-keep class com.soundcloud.android.crop.** { *; }
-dontwarn com.soundcloud.android.crop.**

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}