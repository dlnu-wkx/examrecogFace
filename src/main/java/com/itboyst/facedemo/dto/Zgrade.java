package com.itboyst.facedemo.dto;

public class Zgrade {
    private String zid;
    private String zmajorID;
    private String zidentity;
    private String zname;
    private String zassistant;

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

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZassistant() {
        return zassistant;
    }

    public void setZassistant(String zassistant) {
        this.zassistant = zassistant;
    }

    @Override
    public String toString() {
        return "Zgrade{" +
                "zid='" + zid + '\'' +
                ", zmajorID='" + zmajorID + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zname='" + zname + '\'' +
                ", zassistant='" + zassistant + '\'' +
                '}';
    }
}
