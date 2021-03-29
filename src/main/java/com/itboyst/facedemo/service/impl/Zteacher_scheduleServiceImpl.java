package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_schedule;
import com.itboyst.facedemo.mapper.Zteacher_scheduleMapper;
import com.itboyst.facedemo.service.Zteacher_scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zteacher_scheduleServiceImpl implements Zteacher_scheduleService {

    @Autowired
    Zteacher_scheduleMapper zteacher_scheduleMapper;

    @Override
    public int insert(Zteacher_schedule zteacher_schedule) {
        return zteacher_scheduleMapper.insert(zteacher_schedule);
    }

    @Override
    public Zteacher_schedule findzteaschedule(String zscheduleID, String zteacherID) {
        return zteacher_scheduleMapper.findzteaschedule(zscheduleID,zteacherID);
    }
}
