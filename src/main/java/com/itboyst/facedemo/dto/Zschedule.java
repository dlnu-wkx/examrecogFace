package com.itboyst.facedemo.dto;


import java.sql.Timestamp;

public class Zschedule {
    private String zid;
    private String zcourseID;
    private String ztrainingroomID;
    private Timestamp zstartdate;
    private Timestamp zenddate;

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

    public String getZtrainingroomID() {
        return ztrainingroomID;
    }

    public void setZtrainingroomID(String ztrainingroomID) {
        this.ztrainingroomID = ztrainingroomID;
    }

    public Timestamp getZstartdate() {
        return zstartdate;
    }

    public void setZstartdate(Timestamp zstartdate) {
        this.zstartdate = zstartdate;
    }

    public Timestamp getZenddate() {
        return zenddate;
    }

    public void setZenddate(Timestamp zenddate) {
        this.zenddate = zenddate;
    }

    @Override
    public String toString() {
        return "Zschedule{" +
                "zid='" + zid + '\'' +
                ", zcourseID='" + zcourseID + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zstartdate=" + zstartdate +
                ", zenddate=" + zenddate +
                '}';
    }
}
