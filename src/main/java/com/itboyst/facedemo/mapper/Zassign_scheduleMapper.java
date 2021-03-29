package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zassign_schedule;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface Zassign_scheduleMapper {
    public int updatechecktime(Timestamp zchecktime,String zstudentscheduleID,String ztrainingtaskID);

    public int findtaskisin(String ztrainingtaskID,String zstudentID,String zscheduleID);

    public int insertfixedtask(String zid,String zscheduleID,String zstudentID,String ztrainingtaskID,Timestamp zpublishtime);

    public int updatecompletetime(Zassign_schedule zassign_schedule);

    int insertzassignzschedule(Zassign_schedule zassign_schedule);
}
