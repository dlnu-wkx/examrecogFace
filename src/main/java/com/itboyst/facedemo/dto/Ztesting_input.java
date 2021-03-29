package com.itboyst.facedemo.dto;

public class Ztesting_input {
    private String zid;
    private String zstudentscheduleID;
    private String zsafetestingID;
    private int zorder;
    private String zstate;
    private String zinput;
    private int zscore;

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

    public String getZsafetestingID() {
        return zsafetestingID;
    }

    public void setZsafetestingID(String zsafetestingID) {
        this.zsafetestingID = zsafetestingID;
    }

    public int getZorder() {
        return zorder;
    }

    public void setZorder(int zorder) {
        this.zorder = zorder;
    }

    public String getZstate() {
        return zstate;
    }

    public void setZstate(String zstate) {
        this.zstate = zstate;
    }

    public String getZinput() {
        return zinput;
    }

    public void setZinput(String zinput) {
        this.zinput = zinput;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    @Override
    public String toString() {
        return "Ztesting_input{" +
                "zid='" + zid + '\'' +
                ", zstudentscheduleID='" + zstudentscheduleID + '\'' +
                ", zsafetestingID='" + zsafetestingID + '\'' +
                ", zorder=" + zorder +
                ", zstate='" + zstate + '\'' +
                ", zinput='" + zinput + '\'' +
                ", zscore=" + zscore +
                '}';
    }
}
