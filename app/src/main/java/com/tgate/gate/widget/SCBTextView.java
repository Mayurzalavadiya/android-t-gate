package com.tgate.gate.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class SCBTextView extends AppCompatTextView {

    Context context;

    public SCBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        setTypeFace();
    }

    public SCBTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;
        setTypeFace();
    }

    public SCBTextView(Context context) {
        super(context);

        this.context = context;
        setTypeFace();
    }

    private void setTypeFace() {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Poppins_Medium.ttf");
        setTypeface(tf);
    }

}
