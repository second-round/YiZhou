package com.example.yizhou;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SearchView extends LinearLayout {
    private int mHighMax;
    private int hSpace=20;
    int index;
    private int vSpace=20;
    Context context;
    public SearchView(Context context) {
        super(context);
        this.context=context;
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        MaxSize();
        int left=20,top=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
                if (left!=0){
                    if (((left+childAt.getMeasuredWidth())>width)){
                        top+=vSpace+childAt.getMeasuredHeight();
                        left=0;
                    }
                }
            left+=vSpace+childAt.getMeasuredWidth();
        }
    setMeasuredDimension(width,(top+mHighMax)>height?height:top+mHighMax);
    }

    private void MaxSize() {
        mHighMax=0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
            if (childAt.getMeasuredHeight()>mHighMax){
                mHighMax=childAt.getMeasuredHeight();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        MaxSize();
        int left=20,top=0;

        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View childAt = getChildAt(i);
                if (left!=0){
                    if (((left+childAt.getMeasuredWidth())>getWidth())){
                        top+=vSpace+childAt.getMeasuredHeight();
                        left=hSpace;
                    }
            }
            childAt.layout(left,top,left+childAt.getMeasuredWidth(),top+mHighMax);
            left+=hSpace+childAt.getMeasuredWidth();
        }
//        setMeasuredDimension(getWidth(),(top+mHighMax)>getHeight()?getHeight():top+mHighMax);
    }

}
