package com.tgate.gate.model;

public class getHostList {
    String hostName;
    String hostposs;
//    String hostImg;
    int hostImage;
    boolean isSelect=false;

    public getHostList() {
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostposs() {
        return hostposs;
    }

    public void setHostposs(String hostposs) {
        this.hostposs = hostposs;
    }

    public int getHostImage() {
        return hostImage;
    }

    public void setHostImage(int hostImage) {
        this.hostImage = hostImage;
    }


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
