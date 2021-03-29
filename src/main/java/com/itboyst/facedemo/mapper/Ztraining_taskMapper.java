package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztraining_task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Ztraining_taskMapper {

    public List<Ztraining_task> findalltaskbyzcourseID(String zcourseID);

    public List<Ztraining_task> findtasknamelike(String zname);

    public List<Ztraining_task> findalltask();
}
