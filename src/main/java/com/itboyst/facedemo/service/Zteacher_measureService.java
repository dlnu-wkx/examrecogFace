package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_measure;

import java.util.List;

public interface Zteacher_measureService {

    public List<Zteacher_measure> findmeasurebygat(String zgradeID, String ztraining_taskID);

    public int findmeasurebygatgroupid(String zgradeID,String ztraining_taskID);
}
