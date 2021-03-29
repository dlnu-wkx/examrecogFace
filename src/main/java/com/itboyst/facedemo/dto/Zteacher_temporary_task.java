package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zteacher_temporary_task {
    private String zid;
    private String zstudentID;
    private String zscheduleID;
    private String ztype;
    private String ztitle;
    private Timestamp zpublishtime;
    private String zcontentID;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZstudentID() {
        return zstudentID;
    }

    public void setZstudentID(String zstudentID) {
        this.zstudentID = zstudentID;
    }

    public String getZscheduleID() {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZtitle() {
        return ztitle;
    }

    public void setZtitle(String ztitle) {
        this.ztitle = ztitle;
    }

    public Timestamp getZpublishtime() {
        return zpublishtime;
    }

    public void setZpublishtime(Timestamp zpublishtime) {
        this.zpublishtime = zpublishtime;
    }

    public String getZcontentID() {
        return zcontentID;
    }

    public void setZcontentID(String zcontentID) {
        this.zcontentID = zcontentID;
    }

    @Override
    public String toString() {
        return "Zteacher_temporary_task{" +
                "zid='" + zid + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", ztype='" + ztype + '\'' +
                ", ztitle='" + ztitle + '\'' +
                ", zpublishtime=" + zpublishtime +
                ", zcontentID='" + zcontentID + '\'' +
                '}';
    }
}
