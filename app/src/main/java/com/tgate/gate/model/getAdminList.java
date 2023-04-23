package com.tgate.gate.model;

public class getAdminList {

    String visitorName, visitorposs, visitorexp;
    int visitorImage;
    boolean isSelect=false;

    public getAdminList() {}

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorposs() {
        return visitorposs;
    }

    public void setVisitorposs(String visitorposs) {
        this.visitorposs = visitorposs;
    }

    public String getVisitorexp() {
        return visitorexp;
    }

    public void setVisitorexp(String visitorexp) {
        this.visitorexp = visitorexp;
    }

    public int getVisitorImage() {
        return visitorImage;
    }

    public void setVisitorImage(int visitorImage) {
        this.visitorImage = visitorImage;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
