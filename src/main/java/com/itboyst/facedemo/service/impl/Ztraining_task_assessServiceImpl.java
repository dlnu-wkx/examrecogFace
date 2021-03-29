package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_task_assess;
import com.itboyst.facedemo.mapper.Ztraining_task_assessMapper;
import com.itboyst.facedemo.service.Ztraining_task_assessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_task_assessServiceImpl implements Ztraining_task_assessService {

    @Autowired
    Ztraining_task_assessMapper ztraining_task_assessMapper;

    @Override
    public List<Ztraining_task_assess> findallbyztrainingtaskID(String ztrainingtaskID){
        return  ztraining_task_assessMapper.findallbyztrainingtaskID(ztrainingtaskID);
    }
}
