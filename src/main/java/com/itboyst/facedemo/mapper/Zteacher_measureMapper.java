package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_measure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zteacher_measureMapper {
    public List<Zteacher_measure> findmeasurebygat(String zgradeID,String ztraining_taskID,String zscheduleid);

    public int findmeasurebygatgroupid(String zgradeID,String ztraining_taskID,String zscheduleid);
}
