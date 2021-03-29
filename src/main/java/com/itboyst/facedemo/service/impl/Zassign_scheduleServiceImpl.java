package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zassign_schedule;
import com.itboyst.facedemo.mapper.Zassign_scheduleMapper;
import com.itboyst.facedemo.service.Zassign_scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class Zassign_scheduleServiceImpl implements Zassign_scheduleService {

    @Autowired
    Zassign_scheduleMapper zassign_scheduleMapper;

    @Override
    public int updatechecktime(Timestamp zchecktime, String zstudentscheduleID, String ztrainingtaskID){
      return   zassign_scheduleMapper.updatechecktime(zchecktime,zstudentscheduleID,ztrainingtaskID);
    }

    @Override
    public int findtaskisin(String ztrainingtaskID,String zstudentID,String zscheduleID){
        return zassign_scheduleMapper.findtaskisin(ztrainingtaskID, zstudentID, zscheduleID);
    }

    @Override
    public int insertfixedtask(String zid,String zscheduleID,String zstudentID,String ztrainingtaskID,Timestamp zpublishtime){
        return zassign_scheduleMapper.insertfixedtask(zid, zscheduleID, zstudentID, ztrainingtaskID, zpublishtime);
    }

    @Override
    public int updatecompletetime(Zassign_schedule zassign_schedule){
        return zassign_scheduleMapper.updatecompletetime(zassign_schedule);
    }

    @Override
    public int insertzassignzschedule(Zassign_schedule zassign_schedule) {
        return zassign_scheduleMapper.insertzassignzschedule(zassign_schedule);
    }

}
