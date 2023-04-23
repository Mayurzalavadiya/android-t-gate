package com.tgate.gate.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class SCBCheckBoxTextView extends TextView {

    Context context;

    public SCBCheckBoxTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        setTypeFace();
    }

    public SCBCheckBoxTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;
        setTypeFace();
    }

    public SCBCheckBoxTextView(Context context) {
        super(context);

        this.context = context;
        setTypeFace();
    }

    private void setTypeFace() {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Poppins_Medium.ttf");
        setTypeface(tf);
    }

}
