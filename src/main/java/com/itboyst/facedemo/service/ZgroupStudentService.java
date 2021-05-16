package com.itboyst.facedemo.service;

public interface ZgroupStudentService {
    int add(String zid,String zgradeID,String zstudentID);

    int deleteZgroupStudentByZid(String zgradeID);

    int deleteZgroupStudentByStudentID(String zstudentID);
}
