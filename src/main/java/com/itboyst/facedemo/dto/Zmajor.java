package com.itboyst.facedemo.dto;

public class Zmajor {
    private String zid;
    private String zinstituteID;
    private int zorder;
    private String zidentity;
    private String zname;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZinstituteID() {
        return zinstituteID;
    }

    public void setZinstituteID(String zinstituteID) {
        this.zinstituteID = zinstituteID;
    }

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
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

    @Override
    public String toString() {
        return "Zmajor{" +
                "zid='" + zid + '\'' +
                ", zinstituteID='" + zinstituteID + '\'' +
                ", zorder=" + zorder +
                ", zidentity='" + zidentity + '\'' +
                ", zname='" + zname + '\'' +
                '}';
    }
}
