package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_event {

    private String zid;
    private String zstudentID;
    private String zscheduleID;
    private String zrecognizeIP;
    private String ztype;
    private String zcontent;
    private Timestamp zapplicationtime;
    private String zteacherID;
    private Timestamp zhandletime;
    private String zhandlecontent;
    private String zstatus;

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

    public String getZrecognizeIP() {
        return zrecognizeIP;
    }

    public void setZrecognizeIP(String zrecognizeIP) {
        this.zrecognizeIP = zrecognizeIP;
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

    public Timestamp getZapplicationtime() {
        return zapplicationtime;
    }

    public void setZapplicationtime(Timestamp zapplicationtime) {
        this.zapplicationtime = zapplicationtime;
    }

    public String getZteacherID() {
        return zteacherID;
    }

    public void setZteacherID(String zteacherID) {
        this.zteacherID = zteacherID;
    }

    public Timestamp getZhandletime() {
        return zhandletime;
    }

    public void setZhandletime(Timestamp zhandletime) {
        this.zhandletime = zhandletime;
    }

    public String getZhandlecontent() {
        return zhandlecontent;
    }

    public void setZhandlecontent(String zhandlecontent) {
        this.zhandlecontent = zhandlecontent;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    @Override
    public String toString() {
        return "Zstudent_event{" +
                "zid='" + zid + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zrecognizeIP='" + zrecognizeIP + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zcontent='" + zcontent + '\'' +
                ", zapplicationtime=" + zapplicationtime +
                ", zteacherID='" + zteacherID + '\'' +
                ", zhandletime=" + zhandletime +
                ", zhandlecontent='" + zhandlecontent + '\'' +
                ", zstatus='" + zstatus + '\'' +
                '}';
    }
}
