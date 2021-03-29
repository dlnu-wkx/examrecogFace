package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztraining_task_assess;

import java.util.List;

public interface Ztraining_task_assessMapper {

    public List<Ztraining_task_assess> findallbyztrainingtaskID(String ztrainingtaskID);
}
