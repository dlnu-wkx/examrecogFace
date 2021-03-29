package com.itboyst.facedemo.dto;

import java.sql.Timestamp;

public class Zstudent_journal {
    private String zid;
    private String zstudentID;
    private String ztype;
    private Timestamp zoperatedate;
    private String zoperatecontent;

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
        return "Zstudent_journal{" +
                "zid='" + zid + '\'' +
                ", zstudentID='" + zstudentID + '\'' +
                ", ztype='" + ztype + '\'' +
                ", zoperatedate=" + zoperatedate +
                ", zoperatecontent='" + zoperatecontent + '\'' +
                '}';
    }     
}
