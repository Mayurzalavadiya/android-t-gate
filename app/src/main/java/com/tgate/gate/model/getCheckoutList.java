package com.tgate.gate.model;

import android.graphics.drawable.Drawable;

public class getCheckoutList {
    String name;
//    String Image;
    int image;
    boolean isSelect=false;

    public getCheckoutList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
