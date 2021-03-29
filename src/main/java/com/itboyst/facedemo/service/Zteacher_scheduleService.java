package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_schedule;

public interface Zteacher_scheduleService {
    int insert(Zteacher_schedule zteacher_schedule);

    Zteacher_schedule findzteaschedule(String zscheduleID,String zteacherID);
}
