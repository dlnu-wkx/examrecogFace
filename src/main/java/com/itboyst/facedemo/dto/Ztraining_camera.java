package com.itboyst.facedemo.dto;

import lombok.Data;

@Data
public class Ztraining_camera {
    private String zid;
    private String ztrainingroomID;
    private int zidentity;
    private String ztitle;
    private String zcameraIP;
    private String zcameraName;
    private String zwebaddress;
    private String zstatus;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZtrainingroomID() {
        return ztrainingroomID;
    }

    public void setZtrainingroomID(String ztrainingroomID) {
        this.ztrainingroomID = ztrainingroomID;
    }

    public int getZidentity() {
        return zidentity;
    }

    public void setZidentity(int zidentity) {
        this.zidentity = zidentity;
    }

    public String getZtitle() {
        return ztitle;
    }

    public void setZtitle(String ztitle) {
        this.ztitle = ztitle;
    }

    public String getZcameraIP() {
        return zcameraIP;
    }

    public void setZcameraIP(String zcameraIP) {
        this.zcameraIP = zcameraIP;
    }

    public String getZcameraName() {
        return zcameraName;
    }

    public void setZcameraName(String zcameraName) {
        this.zcameraName = zcameraName;
    }

    public String getZwebaddress() {
        return zwebaddress;
    }

    public void setZwebaddress(String zwebaddress) {
        this.zwebaddress = zwebaddress;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Ztraining_camera{" +
                "zid='" + zid + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zidentity=" + zidentity +
                ", ztitle='" + ztitle + '\'' +
                ", zcameraIP='" + zcameraIP + '\'' +
                ", zcameraName='" + zcameraName + '\'' +
                ", zwebaddress='" + zwebaddress + '\'' +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
