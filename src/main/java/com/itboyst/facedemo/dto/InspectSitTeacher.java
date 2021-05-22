package com.itboyst.facedemo.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class InspectSitTeacher {
    private String zteacherID;
    private String zName;
    private String zgradeName;
    private Timestamp zrecognizetime;
    private String originalPictureUrl;
    private String cameraname;
}
