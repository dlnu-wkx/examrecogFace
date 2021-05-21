package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zteacher_cookie {
    private String zteacherid;
    private String zmajorID;
    private String zauthorityID;//权限ID
    private String zidentity;//工号
    private String zpass;
    private String zname;
    private String zsex;
    private String zphone;
    private int zfaceinfoID;
    private String zphoto;
    private String zteacher_scheduleid; //教师上课表
    private String zscheduleID;  //上课表id
    private String zteacherID;
    private String ztrainingroomid;
    private String zroomname;
    private String zlocation;
    private String znote;
    private String zgradeid;

    public String getZteacherid() {
        return zteacherid;
    }

    public void setZteacherid(String zteacherid) {
        this.zteacherid = zteacherid;
    }

    public String getZmajorID() {
        return zmajorID;
    }

    public void setZmajorID(String zmajorID) {
        this.zmajorID = zmajorID;
    }

    public String getZauthorityID() {
        return zauthorityID;
    }

    public void setZauthorityID(String zauthorityID) {
        this.zauthorityID = zauthorityID;
    }

    public String getZidentity() {
        return zidentity;
    }

    public void setZidentity(String zidentity) {
        this.zidentity = zidentity;
    }

    public String getZpass() {
        return zpass;
    }

    public void setZpass(String zpass) {
        this.zpass = zpass;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getZsex() {
        return zsex;
    }

    public void setZsex(String zsex) {
        this.zsex = zsex;
    }

    public String getZphone() {
        return zphone;
    }

    public void setZphone(String zphone) {
        this.zphone = zphone;
    }

    public int getZfaceinfoID() {
        return zfaceinfoID;
    }

    public void setZfaceinfoID(int zfaceinfoID) {
        this.zfaceinfoID = zfaceinfoID;
    }

    public String getZphoto() {
        return zphoto;
    }

    public void setZphoto(String zphoto) {
        this.zphoto = zphoto;
    }

    public String getZteacher_scheduleid() {
        return zteacher_scheduleid;
    }

    public void setZteacher_scheduleid(String zteacher_scheduleid) {
        this.zteacher_scheduleid = zteacher_scheduleid;
    }

    public String getZscheduleID() {
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

    public String getZtrainingroomid() {
        return ztrainingroomid;
    }

    public void setZtrainingroomid(String ztrainingroomid) {
        this.ztrainingroomid = ztrainingroomid;
    }

    public String getZroomname() {
        return zroomname;
    }

    public void setZroomname(String zroomname) {
        this.zroomname = zroomname;
    }

    public String getZlocation() {
        return zlocation;
    }

    public void setZlocation(String zlocation) {
        this.zlocation = zlocation;
    }

    public String getZnote() {
        return znote;
    }

    public void setZnote(String znote) {
        this.znote = znote;
    }

    public String getZgradeid() {
        return zgradeid;
    }

    public void setZgradeid(String zgradeid) {
        this.zgradeid = zgradeid;
    }

    @Override
    public String toString() {
        return "Zteacher_cookie{" +
                "zteacherid='" + zteacherid + '\'' +
                ", zmajorID='" + zmajorID + '\'' +
                ", zauthorityID='" + zauthorityID + '\'' +
                ", zidentity='" + zidentity + '\'' +
                ", zpass='" + zpass + '\'' +
                ", zname='" + zname + '\'' +
                ", zsex='" + zsex + '\'' +
                ", zphone='" + zphone + '\'' +
                ", zfaceinfoID=" + zfaceinfoID +
                ", zphoto='" + zphoto + '\'' +
                ", zteacher_scheduleid='" + zteacher_scheduleid + '\'' +
                ", zscheduleID='" + zscheduleID + '\'' +
                ", zteacherID='" + zteacherID + '\'' +
                ", ztrainingroomid='" + ztrainingroomid + '\'' +
                ", zroomname='" + zroomname + '\'' +
                ", zlocation='" + zlocation + '\'' +
                ", znote='" + znote + '\'' +
                ", zgradeid='" + zgradeid + '\'' +
                '}';
    }
}
