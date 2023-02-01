package com.example.example_7;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout;

public class AdaptRelativeLayout extends RelativeLayout {
    private static final String TAG = "AdaptRelativeLayout";

    private boolean flag = true;

    public AdaptRelativeLayout(Context context) {
        super(context);
    }

    public AdaptRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (flag) { // just measure once
            float scaleWidth = ScreenPixels.getInstance(getContext()).getScaleWidth();
            float scaleHeight = ScreenPixels.getInstance(getContext()).getScaleHeight();
            final int count = getChildCount();
            LayoutParams params;
            View childView;
            for (int i = 0; i < count; i++) {
                childView = getChildAt(i);
                params = (LayoutParams) childView.getLayoutParams();
                params.width = (int) (params.width * scaleWidth);
                params.height = (int) (params.height * scaleHeight);
                params.leftMargin = (int) (params.leftMargin * scaleWidth);
                params.rightMargin = (int) (params.rightMargin * scaleWidth);
                params.topMargin = (int) (params.topMargin * scaleHeight);
                params.bottomMargin = (int) (params.bottomMargin * scaleHeight);
            }
            flag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
