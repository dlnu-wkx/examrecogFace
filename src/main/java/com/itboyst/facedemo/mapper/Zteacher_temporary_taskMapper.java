package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_temporary_task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zteacher_temporary_taskMapper {

  List<Zteacher_temporary_task> findtitle(String zstudentID,String zscheduleID);

  public int findisintemp(String zcontentID,String zstudentID);

  public int inserttemptask(Zteacher_temporary_task zteacher_temporary_task);

  public int detemporarybydid(String zstudentID,String zscheduleID);

  public Zteacher_temporary_task findisintemp2(String zcontentID,String zstudentID);
}
