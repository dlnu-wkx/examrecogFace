package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Zrecord {
    private String zid;
    private String zscheduleID;
    private String zteacherID;
    private Timestamp zdate;
    private String ztype;
    private String zcontent;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZscheduleID(String zid) {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public String getZteacherID() {
        return zteacherID;
    }

    public void setZteacherID(String zteacherID) {
        this.zteacherID = zteacherID;
    }

    public Timestamp getZdate() {
        return zdate;
    }

    public void setZdate(Timestamp zdate) {
        this.zdate = zdate;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZcontent() {
        return zcontent;
    }

    public void setZcontent(String zcontent) {
        this.zcontent = zcontent;
    }

    @Override
    public String toString() {
        return "Zrecord{" +
                "zid='" + zid + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zteacherID='" + zteacherID + '\'' +
                ", zdate=" + zdate +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                '}';
    }
}
