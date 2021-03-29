package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztask_content_log;

import java.sql.Timestamp;

import com.itboyst.facedemo.dto.Ztask_content_log;

public interface Ztask_content_logService {

    public int updatestarttime(String zassignscheduleID,String ztrainingtaskcontentID,String zstarttime);

    public int findnumcontentlog(String zassignscheduleID,String ztrainingtaskcontentID);

    public  int insertcontentlog(Ztask_content_log ztask_content_log);

    public int updatecontentlogstart(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zstarttime);

    public int updatecontentlogend(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zendtime);


}
