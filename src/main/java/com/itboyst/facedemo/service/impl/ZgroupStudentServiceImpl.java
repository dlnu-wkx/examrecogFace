package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.ZgroupStudentMapper;
import com.itboyst.facedemo.service.ZgroupStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZgroupStudentServiceImpl implements ZgroupStudentService {

    @Autowired
    ZgroupStudentMapper zgroupStudentMapper;

    @Override
    public int add(String zid,String zgradeID, String zstudentID) {
        return zgroupStudentMapper.add(zid,zgradeID,zstudentID);
    }

    @Override
    public int deleteZgroupStudentByStudentID(String zstudentID) {
        return zgroupStudentMapper.deleteZgroupStudentByStudentID(zstudentID);
    }

    @Override
    public int deleteZgroupStudentByZid(String zgradeID) {
        return zgroupStudentMapper.deleteZgroupStudentByZid(zgradeID);
    }


}
