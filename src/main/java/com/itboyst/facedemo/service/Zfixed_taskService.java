package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zfixed_task;

import java.util.List;

public interface Zfixed_taskService {

    public List<Zfixed_task> findallfixedtask(String zstudentscheduleID);
}
