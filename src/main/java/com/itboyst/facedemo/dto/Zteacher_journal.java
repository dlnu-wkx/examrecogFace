package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zteacher_journal {
    private String zid;
    private String zteacherID;
    private String ztype;
    private Timestamp zoperatedate;
    private String zoperatecontent;

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

    public String getZtype() {
        return ztype;
    }

    public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public Timestamp getZoperatedate() {
        return zoperatedate;
    }

    public void setZoperatedate(Timestamp zoperatedate) {
        this.zoperatedate = zoperatedate;
    }

    public String getZoperatecontent() {
        return zoperatecontent;
    }

    public void setZoperatecontent(String zoperatecontent) {
        this.zoperatecontent = zoperatecontent;
    }

    @Override
    public String toString() {
        return "Zteacher_journal{" +
                "zid='" + zid + '\'' +
                ", zteacherID='" + zteacherID + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zoperatedate=" + zoperatedate +
                ", zoperatecontent='" + zoperatecontent + '\'' +
                '}';
    }
}
