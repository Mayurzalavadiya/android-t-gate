package com.tgate.gate.model;

public class getTimeList {

    String time;
    boolean isUnelect=true;
    boolean isSelect=false;

    public getTimeList() {}

    public String getTime() {
        return time;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isUnelect() {
        return isUnelect;
    }

    public void setUnelect(boolean unelect) {
        isUnelect = unelect;
    }
}
