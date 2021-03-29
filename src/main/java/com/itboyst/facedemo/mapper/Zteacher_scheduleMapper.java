package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_schedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Zteacher_scheduleMapper {
    int insert(Zteacher_schedule zteacher_schedule);

    Zteacher_schedule findzteaschedule(String zscheduleID,String zteacherID);
}
