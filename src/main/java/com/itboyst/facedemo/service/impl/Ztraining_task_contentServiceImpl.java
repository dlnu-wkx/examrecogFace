package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_task_content;
import com.itboyst.facedemo.mapper.Ztraining_task_contentMapper;
import com.itboyst.facedemo.service.Ztraining_task_contentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_task_contentServiceImpl implements Ztraining_task_contentService {

    @Autowired
    Ztraining_task_contentMapper ztraining_task_contentMapper;

    @Override
    public Ztraining_task_content selectcontbypage(int zorder, String ztrainingtaskID){
       return ztraining_task_contentMapper.selectcontentbypage(zorder,ztrainingtaskID);
    }

    @Override
    public List<Ztraining_task_content> findpresentProgessByfacilityID(String zid) {
        return ztraining_task_contentMapper.findpresentProgessByfacilityID(zid);
    }

    @Override
    public int findendpages(String ztraingingtaskID){
        //System.out.println(ztraingingtaskID);
        //**有空的情况需要处理


        return ztraining_task_contentMapper.findendpages(ztraingingtaskID);
    }

}
