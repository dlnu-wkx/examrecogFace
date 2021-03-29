package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_cookie {
    private String zid;
    private String zidentity;
    private String zname;
    private String ztype;
    private String zsafetestingType;
    private String zscheduleid;
    private String zcourseID;
    private String ztrainingroomID;
    private Timestamp zstartdate;
    private Timestamp zenddate;
    private int zsafetestingNum;
    private int zsafetestingTotal;
    private String zpowerstart;
    private String zstudent_scheduleid;
    private String zscheduleID;
    private String zstudentID;
    private String zselecttest;
    private int zpassingscore;
    private String zstate;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
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

    public String getZsafetestingType() {
        return zsafetestingType;
    }

    public void setZsafetestingType(String zsafetestingType) {
        this.zsafetestingType = zsafetestingType;
    }

    public String getZscheduleid() {
        return zscheduleid;
    }

    public void setZscheduleid(String zscheduleid) {
        this.zscheduleid = zscheduleid;
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

    public int getZsafetestingNum() {
        return zsafetestingNum;
    }

    public void setZsafetestingNum(int zsafetestingNum) {
        this.zsafetestingNum = zsafetestingNum;
    }

    public int getZsafetestingTotal() {
        return zsafetestingTotal;
    }

    public void setZsafetestingTotal(int zsafetestingTotal) {
        this.zsafetestingTotal = zsafetestingTotal;
    }

    public String getZpowerstart() {
        return zpowerstart;
    }

    public void setZpowerstart(String zpowerstart) {
        this.zpowerstart = zpowerstart;
    }

    public String getZstudent_scheduleid() {
        return zstudent_scheduleid;
    }

    public void setZstudent_scheduleid(String zstudent_scheduleid) {
        this.zstudent_scheduleid = zstudent_scheduleid;
    }

    public String getZscheduleID() {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public String getZstudentID() {
        return zstudentID;
    }

    public void setZstudentID(String zstudentID) {
        this.zstudentID = zstudentID;
    }

    public String getZselecttest() {
        return zselecttest;
    }

    public void setZselecttest(String zselecttest) {
        this.zselecttest = zselecttest;
    }

    public int getZpassingscore() {
        return zpassingscore;
    }

    public void setZpassingscore(int zpassingscore) {
        this.zpassingscore = zpassingscore;
    }

    public String getZstate() {
        return zstate;
    }

    public void setZstate(String zstate) {
        this.zstate = zstate;
    }


    @Override
    public String toString() {
        return "Zstudent_cookie{" +
                "zid='" + zid + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zname='" + zname + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zsafetestingType='" + zsafetestingType + '\'' +
                ", zscheduleid='" + zscheduleid + '\'' +
                ", zcourseID='" + zcourseID + '\'' +
                ", ztrainingroomID='" + ztrainingroomID + '\'' +
                ", zstartdate=" + zstartdate +
                ", zenddate=" + zenddate +
                ", zsafetestingNum=" + zsafetestingNum +
                ", zsafetestingTotal=" + zsafetestingTotal +
                ", zpowerstart='" + zpowerstart + '\'' +
                ", zstudent_scheduleid='" + zstudent_scheduleid + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", zselecttest='" + zselecttest + '\'' +
                ", zpassingscore=" + zpassingscore +
                ", zstate='" + zstate + '\'' +
                '}';
    }


}
