package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_task_assess;

import java.util.List;

public interface Ztraining_task_assessService {

    public List<Ztraining_task_assess> findallbyztrainingtaskID(String ztrainingtaskID);
}
