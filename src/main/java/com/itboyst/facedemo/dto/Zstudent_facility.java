package com.itboyst.facedemo.dto;

public class Zstudent_facility {
    private String zid;
    private String zname;
    private String zidentity;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    @Override
    public String toString() {
        return "Zstudent_facility{" +
                "zid='" + zid + '\'' +
                ", zname='" + zname + '\'' +
                ", zidentity='" + zidentity + '\'' +
                '}';
    }
}
