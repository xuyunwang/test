package com.example.example_7;



import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;


public class ScreenDensity {

    // 设计稿的屏幕宽度 dp 值
    private static final float DESIGN_DENSITY = 360f;
    private static float appScaleDensity;

    public static void setDensity(Application application, Activity activity) {
        // 获取 application 的 DisplayMetrics
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        float appDensity = appDisplayMetrics.density;
        appScaleDensity = appDisplayMetrics.scaledDensity;

        //监听字体大小变化，重新获取变化后的 appScaleDensity，适配到应用中
        application.registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                if (newConfig != null && newConfig.fontScale > 0) {
                    appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                }
            }

            @Override
            public void onLowMemory() {
            }
        });

        // 获取 activity 的 DisplayMetrics
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        // 计算缩放比例（设备屏幕宽度 / 设计稿宽度）
        float targetDensity = appDisplayMetrics.widthPixels / DESIGN_DENSITY;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityApi = (int) (targetDensity * 160);

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityApi;
    }
}