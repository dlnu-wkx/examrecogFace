package com.itboyst.facedemo.mapper;


import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.dto.Zstudent;

import java.sql.Timestamp;
import java.util.List;

public interface ZgradeMapper {

    public List<Zgrade> findallgrade();

    public List<String> findgradebynamelike(String zname);

    List<Zgrade> findgradebymajorid(String id);

    String findzidbyzname(String zname);

    List<Zgrade> findallgradebytrainroom(String ztrainingroomID);

    //查找所有的群组
    List<Zgrade> findgroup();
    //查找所有的班级
    List<Zgrade> findgrade();

    int addgroup(Zgrade zgrade);

    int deletgroup(String zid);

}
