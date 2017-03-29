package com.test.testsinatabview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by admin on 2017/3/27.
 */

public class CustomTabView extends View {

    private static final String TAG = "CustomTabView";

    private int startX = 36;
    private int endX = 144;
    private int height;

    private Paint mPaint;
    private int mTabColor = 0xFFFF9101;

    public CustomTabView(Context context) {
        super(context);
        init();
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mTabColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        mPaint.setStrokeWidth(height / 2);
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(this.startX, height / 2, this.endX, height / 2, mPaint);
    }
}
