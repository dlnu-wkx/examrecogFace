package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_event;

import java.util.List;

public interface Zstudent_eventService {
    public  int insertevent(Zstudent_event zstudent_event);

    public  int delteup(String zid);

    public int findisevent(String zstudentid);

    public int deleteleave(String zstudentID,String ztype);

    public Zstudent_event  findRaiseHandByFacility(String zid);

    public int updateeventstatus(Zstudent_event zstudent_event);

    int updateTeacherIDandStatus(Zstudent_event zstudent_event);

    public Zstudent_event findupnumberbyfai(String zid,String ztype);

    public Zstudent_event findupbyfacilityid(String zid);

    public int updatealleventbystu(String zstatus,String zrecognizeIP,String zscheduleID);

    public int updatealleventbystu2(String zstatus,String zstudentID,String zscheduleID);

    public int updatebeforebyip(Zstudent_event zstudent_event);

    public int findisevent2(String zstudentID,String zrecognizeIP);

    public List<Zstudent_event> findisevent3(String zstudentID, String zscheduleID);
}
