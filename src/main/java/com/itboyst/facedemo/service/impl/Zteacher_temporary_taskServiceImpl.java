package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_temporary_task;
import com.itboyst.facedemo.mapper.Zteacher_temporary_taskMapper;
import com.itboyst.facedemo.service.Zteacher_temporary_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_temporary_taskServiceImpl implements Zteacher_temporary_taskService {

    @Autowired
    Zteacher_temporary_taskMapper zteacher_temporary_taskMapper;

    @Override
    public List<Zteacher_temporary_task> findtaskname (String id,String zscheduleID){
        return zteacher_temporary_taskMapper.findtitle(id,zscheduleID);
    }

    @Override
    public int findisintemp(String zcontentID,String zstudentID){
        return zteacher_temporary_taskMapper.findisintemp(zcontentID,zstudentID);
    }

    @Override
    public int inserttemptask(Zteacher_temporary_task zteacher_temporary_task){
        return zteacher_temporary_taskMapper.inserttemptask(zteacher_temporary_task);
    }

    public int temporarybydid(String zstudentID,String zscheduleID){return zteacher_temporary_taskMapper.detemporarybydid(zstudentID, zscheduleID);}

    public Zteacher_temporary_task findisintemp2(String zcontentID,String zstudentID){return zteacher_temporary_taskMapper.findisintemp2(zcontentID, zstudentID);}

}
