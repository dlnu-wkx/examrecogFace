package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Zstrange {
    private String zid;
    private String zname;
    private String zcheck;
    private String ztype;
    private String zrecognizeIP;
    private Timestamp zrecognizetime;
    private String originalPictureUrl;
    private String zstatus;
}
