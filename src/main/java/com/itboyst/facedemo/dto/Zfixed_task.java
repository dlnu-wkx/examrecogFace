package com.itboyst.facedemo.dto;

public class Zfixed_task {

    private String zassign_scheduleid;
    private String zstudent_scheduleid;
    private String zname;

    @Override
    public String toString() {
        return "Zfixed_task{" +
                "zassign_scheduleid='" + zassign_scheduleid + '\'' +
                ", zstudent_scheduleid='" + zstudent_scheduleid + '\'' +
                ", zname='" + zname + '\'' +
                '}';
    }


    public String getZassign_scheduleid() {
        return zassign_scheduleid;
    }

    public void setZassign_scheduleid(String zassign_scheduleid) {
        this.zassign_scheduleid = zassign_scheduleid;
    }

    public String getZstudent_scheduleid() {
        return zstudent_scheduleid;
    }

    public void setZstudent_scheduleid(String zstudent_scheduleid) {
        this.zstudent_scheduleid = zstudent_scheduleid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }
}
