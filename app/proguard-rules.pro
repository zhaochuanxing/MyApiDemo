# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/zhao/Android/Sdk/tools/proguard/proguard-android.txt
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
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**

-keep class com.bumptech.glide.** {*;}

#重要，取消一些第三方包内可能无用引用的错误警报
-ignorewarnings
#重要，取消行号混淆。
-keepattributes SourceFile , LineNumberTable
-keepattributes *Annotation*
-keepattributes JavascriptInterface
-keep class com.hisense.hitv.logging.**
-dontnote com.lidroid.**
-dontnote com.google.**

#重要，过滤掉v4 v7包，否则会报错
-keep class android.support.** {
 *;
}
-keep class com.lidroid.** {
 *;
}

#用到google的包，一般用到了gson，zxing都加进去
-keep class com.google.** {
 *;
}
#glide包基本都用到。
-keep class com.bumptech.glide.** {*;}
#重要pinyin jar包，聚好用聚好玩都用上了
-keep class demo.Pinyin4jAppletDemo.** {
 *;
}

#一些Android底层的库不要混淆
-keep class android.net.compatibility.**{*;}
-keep class android.net.http.**{*;}
-keep class com.android.internal.http.multipart.**{*;}
-keep class org.apache.commons.**{*;}
-keep class org.apache.http.**{*;}
-keep class android.content.pm.** {*; }
-keep class * extends java.lang.annotation.Annotation { *; }
-keep class java.lang.annotation.Annotation { *; }
-keep class java.awt.** {*;}
-keep class it.sephiroth.android.library.widget.** {*;}
-keep class com.nostra13.universalimageloader.** {*;}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#基本规则，native不混淆，view相关方法不混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#序列化对象不要混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#R文件对象不要混淆
-keepclassmembers class **.R$* {
    public static <fields>;
}

#针对某个聚系列的，可能需要修改下包名
-keep class com.hisense.tv.VideoChat.R {*;}

-keep class com.hisense.hitv.** {*; }
#保证appstore SDK中的bean不被混淆
-keep class com.hisense.appstore.sdk.bean.**{*;}
-keep class com.hisense.tvui.** { *;}
