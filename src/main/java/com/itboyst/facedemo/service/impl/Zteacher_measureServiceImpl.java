package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_measure;
import com.itboyst.facedemo.mapper.Zteacher_measureMapper;
import com.itboyst.facedemo.service.Zteacher_measureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_measureServiceImpl implements Zteacher_measureService {
    @Autowired
    Zteacher_measureMapper zteacher_measureMapper;

    @Override
    public int findmeasurebygatgroupid(String zgradeID,String ztraining_taskID,String zscheduleid){return zteacher_measureMapper.findmeasurebygatgroupid(zgradeID, ztraining_taskID,zscheduleid);}

    @Override
    public List<Zteacher_measure> findmeasurebygat(String zgradeID, String ztraining_taskID,String zscheduleid){return zteacher_measureMapper.findmeasurebygat(zgradeID, ztraining_taskID,zscheduleid);}
}
