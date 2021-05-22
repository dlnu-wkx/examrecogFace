package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Zteacher_login {
    private String zid;
    private String zteacherID;
    private String zscheduleID;
    private Timestamp zrecognizetime;
    private String zcheck;
    private String ztype;
    private String  zrecognizeIP;
    private String originalPictureUrl;
    private String cameraname;

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname;
    }

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZteacherID() {
        return zteacherID;
    }

    public void setZteacherID(String zteacherID) {
        this.zteacherID = zteacherID;
    }

    public String getZscheduleID() {
        return zscheduleID;
    }

    public void setZscheduleID(String zscheduleID) {
        this.zscheduleID = zscheduleID;
    }

    public Timestamp getZrecognizetime() {
        return zrecognizetime;
    }

    public void setZrecognizetime(Timestamp zrecognizetime) {
        this.zrecognizetime = zrecognizetime;
    }

    public String getZcheck() {
        return zcheck;
    }

    public void setZcheck(String zcheck) {
        this.zcheck = zcheck;
    }

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZrecognizeIP() {
        return zrecognizeIP;
    }

    public void setZrecognizeIP(String zrecognizeIP) {
        this.zrecognizeIP = zrecognizeIP;
    }

    public String getOriginalPictureUrl() {
        return originalPictureUrl;
    }

    public void setOriginalPictureUrl(String originalPictureUrl) {
        this.originalPictureUrl = originalPictureUrl;
    }


}
