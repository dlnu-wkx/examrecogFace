package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent_event;
import com.itboyst.facedemo.mapper.Zstudent_eventMapper;
import com.itboyst.facedemo.service.Zstudent_eventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zstudent_eventServiceImpl implements Zstudent_eventService {

    @Autowired
    Zstudent_eventMapper zstudent_eventMapper;

    @Override
    public  int insertevent(Zstudent_event zstudent_event){
        return  zstudent_eventMapper.insertevent(zstudent_event);
    }


    @Override
    public  int delteup(String zid){
        return zstudent_eventMapper.deleteupmes(zid);
    }

    @Override
    public int findisevent(String zstudentid){
        return  zstudent_eventMapper.findisevent(zstudentid);
    }

    @Override
    public int deleteleave(String zstudentID,String ztype){
        return zstudent_eventMapper.deleteleave(zstudentID,ztype);
    }

    @Override
    public Zstudent_event  findRaiseHandByFacility(String zid) {
        return zstudent_eventMapper.findRaiseHandByFacility(zid);
    }

    @Override
    public int updateeventstatus(Zstudent_event zstudent_event){
        return zstudent_eventMapper.updateeventstatus(zstudent_event);
    }

    @Override
    public int updateTeacherIDandStatus(Zstudent_event zstudent_event) {
        return zstudent_eventMapper.updateTeacherIDandStatus(zstudent_event);
    }

    @Override
    public Zstudent_event findupnumberbyfai(String zid,String ztype){
        return zstudent_eventMapper.findupnumberbyfai(zid,ztype);
    }

    @Override
    public Zstudent_event findupbyfacilityid(String zid){
        return zstudent_eventMapper.findupbyfacilityid(zid);
    }

    @Override
    public int updatealleventbystu2(String zstatus,String zstudentID,String zscheduleID){return zstudent_eventMapper.updatealleventbystu2(zstatus, zstudentID, zscheduleID);}

    @Override
    public int updatealleventbystu(String zstatus,String zrecognizeIP,String zscheduleID){return zstudent_eventMapper.updatealleventbystu(zstatus, zrecognizeIP, zscheduleID);}


    public int updatebeforebyip(Zstudent_event zstudent_event){return zstudent_eventMapper.updatebeforebyip(zstudent_event);}


    public int findisevent2(String zstudentID,String zrecognizeIP){return zstudent_eventMapper.findisevent2(zstudentID, zrecognizeIP);}

    public List<Zstudent_event> findisevent3(String zstudentID, String zscheduleID){return zstudent_eventMapper.findisevent3(zstudentID, zscheduleID);}

}
