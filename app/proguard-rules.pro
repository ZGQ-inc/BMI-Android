-keep class com.zgqinc.bmi.MainActivity {
    public <init>(...);
    public void onCreate(...);
}

-keepclassmembers class * {
    public void *(android.view.View);
}

-keep class android.widget.** { *; }
-keep class android.view.** { *; }
