package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztask_content_log;
import com.itboyst.facedemo.mapper.Ztask_content_logMapper;
import com.itboyst.facedemo.service.Ztask_content_logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class Ztask_content_logServiceImpl implements Ztask_content_logService {

    @Autowired
    Ztask_content_logMapper ztask_content_logMapper;

    @Override
    public int updatestarttime(String zassignscheduleID,String ztrainingtaskcontentID,String zstarttime){
        return ztask_content_logMapper.updatestarttime(zassignscheduleID,ztrainingtaskcontentID,zstarttime);
    }

    @Override
    public int findnumcontentlog(String zassignscheduleID,String ztrainingtaskcontentID){
        return ztask_content_logMapper.findnumcontentlog(zassignscheduleID, ztrainingtaskcontentID);
    }

    @Override
    public  int insertcontentlog(Ztask_content_log ztask_content_log){
        return ztask_content_logMapper.insertcontentlog(ztask_content_log);
    }

    @Override
    public int updatecontentlogstart(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zstarttime){
        return ztask_content_logMapper.updatecontentlogstart(zassignscheduleID, ztrainingtaskcontentID, zstarttime);
    }

    @Override
    public int updatecontentlogend(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zendtime){
        return ztask_content_logMapper.updatecontentlogend(zassignscheduleID, ztrainingtaskcontentID, zendtime);
    }


}
