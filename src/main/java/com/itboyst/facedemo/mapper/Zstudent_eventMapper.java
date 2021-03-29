package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zstudent_eventMapper {
    public  int insertevent(Zstudent_event zstudent_event);

    public int deleteupmes(String zid);

    public int findisevent(String zstudentid);

    public int deleteleave(String zstudentID,String ztype);

    public Zstudent_event findRaiseHandByFacility(String zid);

    int updateTeacherIDandStatus(Zstudent_event zstudent_event);

    public int updateeventstatus(Zstudent_event zstudent_event);

    public Zstudent_event findupnumberbyfai(String zid,String ztype);

    public Zstudent_event findupbyfacilityid(String zid);

    public int updatealleventbystu(String zstatus,String zrecognizeIP,String zscheduleID);

    public int updatealleventbystu2(String zstatus,String zstudentID,String zscheduleID);

    public int updatebeforebyip(Zstudent_event zstudent_event);

    public int findisevent2(String zstudentID,String zrecognizeIP);

    public List<Zstudent_event>findisevent3(String zstudentID,String zscheduleID);
}
