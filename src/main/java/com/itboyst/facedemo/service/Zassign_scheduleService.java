package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zassign_schedule;

import java.sql.Timestamp;

public interface Zassign_scheduleService {
    public int updatechecktime(Timestamp zchecktime, String zstudentscheduleID, String ztrainingtaskID);

    public int findtaskisin(String ztrainingtaskID,String zstudentID,String zscheduleID);

    public int insertfixedtask(String zid,String zscheduleID,String zstudentID,String ztrainingtaskID,Timestamp zpublishtime);

    public int updatecompletetime(Zassign_schedule zassign_schedule);

    int insertzassignzschedule(Zassign_schedule zassign_schedule);
}
