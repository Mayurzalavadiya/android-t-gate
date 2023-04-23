package com.tgate.gate.model;

public class getCheckinList {

    String visitorName;
    int visImg;
    boolean isSelect=false;

    public getCheckinList(){}

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public int getVisImg() {
        return visImg;
    }

    public void setVisImg(int visImg) {
        this.visImg = visImg;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
