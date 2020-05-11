package com.zlylib.titlebarlib;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public final class ActionTextView extends AppCompatTextView {

    public ActionTextView(Context context) {
        this(context, null);
    }

    public ActionTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
