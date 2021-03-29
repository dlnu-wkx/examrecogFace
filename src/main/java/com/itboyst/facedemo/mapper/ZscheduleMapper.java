package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zschedule;

import java.sql.Timestamp;
import java.util.List;

public interface ZscheduleMapper {

    public Zschedule selectbynt(String zname, Timestamp time);

    public int updatetestbyscheduleid(String zselecttest,int zpassingscore,String zid);

    List<Zschedule> findbytime(Timestamp zstartdate);

    int addzschedule(Zschedule zschedule);

    String findidbycourename(String coursename,String ztrainingroomID);

    List<String> findallzschedule(String coursename);

    Zschedule findzschedulebyzid(String zid);
}
