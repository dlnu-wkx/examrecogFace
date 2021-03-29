package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Ztempuser {
    private String zid;
    private String zname;
    private Timestamp zlogintime;
    private String ztype;
    private String zrecognizeIP;
    private String originalPictureUrl;
    private String zstatus;

}
