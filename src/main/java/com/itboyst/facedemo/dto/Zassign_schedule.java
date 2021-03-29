package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zassign_schedule {

    private String zid;
    private String zstudentscheduleID;
    private String ztrainingtaskID;
    private Timestamp zpublishtime;
    private Timestamp zchecktime;
    private Timestamp zcompletetime;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZstudentscheduleID() {
        return zstudentscheduleID;
    }

    public void setZstudentscheduleID(String zstudentscheduleID) {
        this.zstudentscheduleID = zstudentscheduleID;
    }

    public String getZtrainingtaskID() {
        return ztrainingtaskID;
    }

    public void setZtrainingtaskID(String ztrainingtaskID) {
        this.ztrainingtaskID = ztrainingtaskID;
    }

    public Timestamp getZpublishtime() {
        return zpublishtime;
    }

    public void setZpublishtime(Timestamp zpublishtime) {
        this.zpublishtime = zpublishtime;
    }

    public Timestamp getZchecktime() {
        return zchecktime;
    }

    public void setZchecktime(Timestamp zchecktime) {
        this.zchecktime = zchecktime;
    }

    public Timestamp getZcompletetime() {
        return zcompletetime;
    }

    public void setZcompletetime(Timestamp zcompletetime) {
        this.zcompletetime = zcompletetime;
    }

    @Override
    public String toString() {
        return "Zassign_schedule{" +
                "zid='" + zid + '\'' +
                ", zstudentscheduleID='" + zstudentscheduleID + '\'' +
                ", ztrainingtaskID='" + ztrainingtaskID + '\'' +
                ", zpublishtime=" + zpublishtime +
                ", zchecktime=" + zchecktime +
                ", zcompletetime=" + zcompletetime +
                '}';
    }
}
