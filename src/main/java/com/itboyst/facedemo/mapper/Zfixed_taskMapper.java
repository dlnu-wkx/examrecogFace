package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zfixed_task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zfixed_taskMapper {

    public List<Zfixed_task> findallfixed(String zstudentscheduleID);
}
