package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zfixed_task;
import com.itboyst.facedemo.mapper.Zfixed_taskMapper;
import com.itboyst.facedemo.service.Zfixed_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zfixed_taskServiceImpl implements Zfixed_taskService {

    @Autowired
    Zfixed_taskMapper zfixed_taskMapper;


    public List<Zfixed_task> findallfixedtask(String zstudentscheduleID){
        return zfixed_taskMapper.findallfixed(zstudentscheduleID);
    }

}
