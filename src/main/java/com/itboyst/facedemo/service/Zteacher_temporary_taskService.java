package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_temporary_task;

import java.util.List;

public interface Zteacher_temporary_taskService {
    public List<Zteacher_temporary_task> findtaskname (String id,String zscheduleID);

    public int findisintemp(String zcontentID,String zstudentID);

    public int inserttemptask(Zteacher_temporary_task zteacher_temporary_task);

    public int temporarybydid(String zstudentID,String zscheduleID);

    public Zteacher_temporary_task findisintemp2(String zcontentID,String zstudentID);
}
