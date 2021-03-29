package com.itboyst.facedemo.dto;

public class Zmanager {
    private String zid;
    private String zauthorityID;
    private String zidentity;
    private String zpass;
    private String zname;
    private String zphone;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZauthorityID() {
        return zauthorityID;
    }

    public void setZauthorityID(String zauthorityID) {
        this.zauthorityID = zauthorityID;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    public String getZpass() {
        return zpass;
    }

    public void setZpass(String zpass) {
        this.zpass = zpass;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZphone() {
        return zphone;
    }

    public void setZphone(String zphone) {
        this.zphone = zphone;
    }

    @Override
    public String toString() {
        return "Zmanager{" +
                "zid='" + zid + '\'' +
                ", zauthorityID='" + zauthorityID + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zpass='" + zpass + '\'' +
                ", zname='" + zname + '\'' +
                ", zphone='" + zphone + '\'' +
                '}';
    }
}
