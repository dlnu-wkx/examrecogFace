package com.itboyst.facedemo.dto;

public class ztraining_room {
    private String zid;
    private String zname;
    private String zlocation;
    private String znote;

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

    public String getZlocation() {
        return zlocation;
    }

    public void setZlocation(String zlocation) {
        this.zlocation = zlocation;
    }

    public String getZnote() {
        return znote;
    }

    public void setZnote(String znote) {
        this.znote = znote;
    }

    @Override
    public String toString() {
        return "ztraining_room{" +
                "zid='" + zid + '\'' +
                ", zname='" + zname + '\'' +
                ", zlocation='" + zlocation + '\'' +
                ", znote='" + znote + '\'' +
                '}';
    }
}
