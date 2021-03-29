package com.itboyst.facedemo.dto;

public class Zsafe_testingDto {
    private String zid;
    private String ztestingtype;
    private String zlevel;
    private String ztitletype;
    private String ztitlecontent;
    private String zoptionA;
    private String zoptionB;
    private String zoptionC;
    private String zoptionD;
    private String zresult;
    private int zscore;

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZtestingtype() {
        return ztestingtype;
    }

    public void setZtestingtype(String ztestingtype) {
        this.ztestingtype = ztestingtype;
    }

    public String getZlevel() {
        return zlevel;
    }

    public void setZlevel(String zlevel) {
        this.zlevel = zlevel;
    }

    public String getZtitletype() {
        return ztitletype;
    }

    public void setZtitletype(String ztitletype) {
        this.ztitletype = ztitletype;
    }

    public String getZtitlecontent() {
        return ztitlecontent;
    }

    public void setZtitlecontent(String ztitlecontent) {
        this.ztitlecontent = ztitlecontent;
    }

    public String getZoptionA() {
        return zoptionA;
    }

    public void setZoptionA(String zoptionA) {
        this.zoptionA = zoptionA;
    }

    public String getZoptionB() {
        return zoptionB;
    }

    public void setZoptionB(String zoptionB) {
        this.zoptionB = zoptionB;
    }

    public String getZoptionC() {
        return zoptionC;
    }

    public void setZoptionC(String zoptionC) {
        this.zoptionC = zoptionC;
    }

    public String getZoptionD() {
        return zoptionD;
    }

    public void setZoptionD(String zoptionD) {
        this.zoptionD = zoptionD;
    }

    public String getZresult() {
        return zresult;
    }

    public void setZresult(String zresult) {
        this.zresult = zresult;
    }

    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    @Override
    public String toString() {
        return "Zsafe_testingDto{" +
                "zid='" + zid + '\'' +
                ", ztestingtype='" + ztestingtype + '\'' +
                ", zlevel='" + zlevel + '\'' +
                ", ztitletype='" + ztitletype + '\'' +
                ", ztitlecontent='" + ztitlecontent + '\'' +
                ", zoptionA='" + zoptionA + '\'' +
                ", zoptionB='" + zoptionB + '\'' +
                ", zoptionC='" + zoptionC + '\'' +
                ", zoptionD='" + zoptionD + '\'' +
                ", zresult='" + zresult + '\'' +
                ", zscore=" + zscore +
                '}';
    }
}
