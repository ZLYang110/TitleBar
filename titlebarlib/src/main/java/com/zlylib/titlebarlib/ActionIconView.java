package com.zlylib.titlebarlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

public final class ActionIconView extends AppCompatImageView {

    public ActionIconView(Context context) {
        this(context, null);
    }

    public ActionIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(heightSize, heightSize);
    }
}
