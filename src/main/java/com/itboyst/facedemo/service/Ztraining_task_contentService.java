package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_task_content;

import java.util.List;

public interface Ztraining_task_contentService {
    public Ztraining_task_content selectcontbypage(int zorder,String ztrainingtaskID);

    public int findendpages(String ztrainingtaskID);

    List<Ztraining_task_content> findpresentProgessByfacilityID(String zid);
}
