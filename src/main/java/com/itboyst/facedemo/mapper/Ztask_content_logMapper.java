package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztask_content_log;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface Ztask_content_logMapper {
    public int updatestarttime(String zassignscheduleID,String ztrainingtaskcontentID,String zstarttime);

    public int findnumcontentlog(String zassignscheduleID,String ztrainingtaskcontentID);

    public  int insertcontentlog(Ztask_content_log ztask_content_log);

    public int updatecontentlogstart(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zstarttime);

    public int updatecontentlogend(String zassignscheduleID, String ztrainingtaskcontentID, Timestamp zendtime);

}
