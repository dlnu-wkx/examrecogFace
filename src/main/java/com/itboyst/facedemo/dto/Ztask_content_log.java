package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Ztask_content_log {
    private String zid;
    private String zassignscheduleID;
    private String ztrainingtaskcontentID;
    private Timestamp zstarttime;
    private Timestamp zendtime;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZassignscheduleID() {
        return zassignscheduleID;
    }

    public void setZassignscheduleID(String zassignscheduleID) {
        this.zassignscheduleID = zassignscheduleID;
    }

    public String getZtrainingtaskcontentID() {
        return ztrainingtaskcontentID;
    }

    public void setZtrainingtaskcontentID(String ztrainingtaskcontentID) {
        this.ztrainingtaskcontentID = ztrainingtaskcontentID;
    }

    public Timestamp getZstarttime() {
        return zstarttime;
    }

    public void setZstarttime(Timestamp zstarttime) {
        this.zstarttime = zstarttime;
    }

    public Timestamp getZendtime() {
        return zendtime;
    }

    public void setZendtime(Timestamp zendtime) {
        this.zendtime = zendtime;
    }

    @Override
    public String toString() {
        return "Ztask_content_log{" +
                "zid='" + zid + '\'' +
                ", zassignscheduleID='" + zassignscheduleID + '\'' +
                ", ztrainingtaskcontentID='" + ztrainingtaskcontentID + '\'' +
                ", zstarttime=" + zstarttime +
                ", zendtime=" + zendtime +
                '}';
    }
}
