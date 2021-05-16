package com.itboyst.facedemo.mapper;

public interface ZgroupStudentMapper {
    int add(String zid ,String zgradeID,String zstudentID);

    int deleteZgroupStudentByZid(String zgradeID);

    int deleteZgroupStudentByStudentID(String zstudentID);
}
