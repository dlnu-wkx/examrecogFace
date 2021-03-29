package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InspectSitStudent {
    private String zstudentID;
    private String zName;
    private String zgradeName;
    private Timestamp zrecognizetime;
    private String ztrainingroomID;
    private String originalPictureUrl;
}
