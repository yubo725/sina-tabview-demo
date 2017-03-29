package com.test.testsinatabview;

import android.widget.TextView;

/**
 * Created by yubo on 2017/3/29.
 */

public class TextViewWrapper {

    private TextView textView;

    public TextViewWrapper(TextView textView) {
        this.textView = textView;
    }

    public void setWidth(int width) {
        this.textView.setWidth(width);
    }

    public int getWidth() {
        return this.textView.getWidth();
    }

}
