package com.itboyst.facedemo.dto;

public class Ztraining_task {
    private String zid;
    private String zcourseID;
    private int zorder;
    private String zname;
    private String ztype;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZcourseID() {
        return zcourseID;
    }

    public void setZcourseID(String zcourseID) {
        this.zcourseID = zcourseID;
    }

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    @Override
    public String toString() {
        return "Ztraining_task{" +
                "zid='" + zid + '\'' +
                ", zcourseID='" + zcourseID + '\'' +
                ", zorder=" + zorder +
                ", zname='" + zname + '\'' +
                ", ztype='" + ztype + '\'' +
                '}';
    }
}
