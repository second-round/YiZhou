package com.example.yizhou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TextAttr extends TextView {
    public TextAttr(Context context) {
        super(context);
    }

    public TextAttr(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextAttr);
        int color = typedArray.getColor(R.styleable.TextAttr_textcolor, Color.YELLOW);
        float dimension = typedArray.getDimension(R.styleable.TextAttr_textsize, 20);
        setTextColor(color);
        setTextSize(dimension);
    }
}
