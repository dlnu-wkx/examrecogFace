package com.itboyst.facedemo.service;



import com.itboyst.facedemo.dto.Zstudent_schedule;

import java.util.List;

public interface Zstudent_scheduleService {
    public int updatestate(String zstate,String zscheduleID,String zstudentID);

    public int updatetestbyscheduleid(String zscheduleID,String zselecttest,int zpassingscore);

    public int updatetestbychose(String zselecttest,int zpassingscore,String zscheduleID,String zid);

    public int updatestatebyscheduleid(Zstudent_schedule zstudent_schedule);

    public List<String> findstudentidbyscheduleid(String zscheduleID);

    int addzstudentSchedule(Zstudent_schedule zstudent_schedule);

    Zstudent_schedule findbyzstudentIDandzscheduleID(String zscheduleID,String zstudentID);

    int deletelinshi(String zscheduleID,String zstudentID);

    String  findstudentscheduleid(String zscheduleID,String zstudentID);
}
