package com.itboyst.facedemo.dto;

import lombok.Data;

@Data
public class TimeStatusStudent {
    private  String zstudentID;
    private  String zname;
    private  String zcameraIP;
    private  String zpowerIP;
    private  String zstudentPCIP;
    private  String zidentity;
    private  String zlocation;
    private  String ztype;
    private  String zstatus;
    private  String zcontent;



    /*public void setZtype(String ztype) {
        this.ztype = ztype;
    }

    public String getZstatus() {
        return zstatus;
    }

    public void setZstatus(String zstatus) {
        this.zstatus = zstatus;
    }

    public String getZcontent() {
        return zcontent;
    }

    public void setZcontent(String zcontent) {
        this.zcontent = zcontent;
    }*/
}
