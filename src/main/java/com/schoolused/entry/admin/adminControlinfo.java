package com.schoolused.entry.admin;

public class adminControlinfo {
    private int controlid;
    private int useid;
    private String info;
    private String controlType;

    public int getControlid() {
        return controlid;
    }

    public void setControlid(int controlid) {
        this.controlid = controlid;
    }

    public int getUseid() {
        return useid;
    }

    public void setUseid(int useid) {
        this.useid = useid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    @Override
    public String toString() {
        return "adminControlinfo{" +
                "controlid=" + controlid +
                ", useid=" + useid +
                ", info='" + info + '\'' +
                ", controlType='" + controlType + '\'' +
                '}';
    }
}
