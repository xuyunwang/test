package com.example.example_7;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenPixels {
    private static ScreenPixels INSTANCE;
    private Context context;

    // design pixels on a prototype diagram（must float）
    private static final float DESIGN_WIDTH = 1080f;
    private static final float DESIGN_HEIGHT = 1920f;

    private int screenWidth;
    private int screenHeight;

    private ScreenPixels(Context context) {
        this.context = context;

        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);

            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                screenWidth = displayMetrics.heightPixels;
                screenHeight = displayMetrics.widthPixels;
            } else {
                screenWidth = displayMetrics.widthPixels;
                screenHeight = displayMetrics.heightPixels;
            }
        }
    }

    public static ScreenPixels getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ScreenPixels(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private int getStatusBarHeight() {
        Resources resources = context.getResources();
        if (resources != null) {
            int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(resId);
        }
        return 0;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public float getScaleWidth() {
        return getScreenWidth() / DESIGN_WIDTH;
    }

    public float getScaleHeight() {
        return getScreenHeight() / DESIGN_HEIGHT;
    }

}
