package com.itboyst.facedemo.dto;

import java.io.Serializable;

public class Zteacher implements Serializable {
    private String zid;
    private String zmajorID;
    private String zauthorityID;
    private String zidentity;
    private String zpass;
    private String zname;
    private String zsex;
    private String zphone;
    private int zfaceinfoID;
    private String zphoto;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZmajorID() {
        return zmajorID;
    }

    public void setZmajorID(String zmajorID) {
        this.zmajorID = zmajorID;
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

    public String getZsex() {
        return zsex;
    }

    public void setZsex(String zsex) {
        this.zsex = zsex;
    }

    public String getZphone() {
        return zphone;
    }

    public void setZphone(String zphone) {
        this.zphone = zphone;
    }

    public int getZfaceinfoID() {
        return zfaceinfoID;
    }

    public void setZfaceinfoID(int zfaceinfoID) {
        this.zfaceinfoID = zfaceinfoID;
    }

    public String getZphoto() {
        return zphoto;
    }

    public void setZphoto(String zphoto) {
        this.zphoto = zphoto;
    }

    @Override
    public String toString() {
        return "Zteacher{" +
                "zid='" + zid + '\'' +
                ", zmajorID='" + zmajorID + '\'' +
                ", zauthorityID='" + zauthorityID + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zpass='" + zpass + '\'' +
                ", zname='" + zname + '\'' +
                ", zsex='" + zsex + '\'' +
                ", zphone='" + zphone + '\'' +
                ", zfaceinfoID=" + zfaceinfoID +
                ", zphoto='" + zphoto + '\'' +
                '}';
    }
}
