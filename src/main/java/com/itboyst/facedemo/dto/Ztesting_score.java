package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Ztesting_score {
    private String zid;
    private String zstudentscheduleID;
    private String zrecognizeIP;
    private int zscore;
    private Timestamp zchecktime;

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

    public String getZrecognizeIP() {
        return zrecognizeIP;
    }

    public void setZrecognizeIP(String zrecognizeIP) {
        this.zrecognizeIP = zrecognizeIP;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    public Timestamp getZchecktime() {
        return zchecktime;
    }

    public void setZchecktime(Timestamp zchecktime) {
        this.zchecktime = zchecktime;
    }

    @Override
    public String toString() {
        return "Ztesting_score{" +
                "zid='" + zid + '\'' +
                ", zstudentscheduleID='" + zstudentscheduleID + '\'' +
                ", zrecognizeIP='" + zrecognizeIP + '\'' +
                ", zscore=" + zscore +
                ", zchecktime=" + zchecktime +
                '}';
    }
}
