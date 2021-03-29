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

}
