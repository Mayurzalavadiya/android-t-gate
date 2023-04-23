package com.tgate.gate.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class SCBEditText extends EditText {

    Context context;

    public SCBEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        setTypeFace();
    }

    public SCBEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;
        setTypeFace();
    }

    public SCBEditText(Context context) {
        super(context);

        this.context = context;
        setTypeFace();
    }

    private void setTypeFace() {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Poppins_Light.ttf");
        setTypeface(tf);
    }

}
