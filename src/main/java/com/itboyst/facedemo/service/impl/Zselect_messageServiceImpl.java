package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zselect_message;
import com.itboyst.facedemo.mapper.Zselect_messageMapper;
import com.itboyst.facedemo.service.Zselect_messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class Zselect_messageServiceImpl implements Zselect_messageService {

    @Autowired
    Zselect_messageMapper zselect_messageMapper;

    @Override
    public List<Zselect_message> findattandance(String zname, Timestamp starttime, Timestamp endtime,String zcheck){return zselect_messageMapper.findattandance(zname, starttime, endtime,zcheck);}

    @Override
    public List<Zselect_message>findinandout(String zname,Timestamp starttime,Timestamp endtime){return zselect_messageMapper.findinandout(zname, starttime, endtime);}

    @Override
    public List<Zselect_message> findupheads(String zname, Timestamp starttime,Timestamp endtime,String ztype){return zselect_messageMapper.findupheads(zname, starttime, endtime, ztype);}

    @Override
    public List<Zselect_message> findallleave(String zname, Timestamp starttime, Timestamp endtime){return zselect_messageMapper.findallleave(zname, starttime, endtime);}

    @Override
    public List<Zselect_message> findscorebyname(String zname, Timestamp starttime, Timestamp endtime){return zselect_messageMapper.findscorebyname(zname, starttime, endtime);}

    @Override
    public List<Zselect_message>findattandance2(String zname, Timestamp starttime,Timestamp endtime,String zcheck){return zselect_messageMapper.findattandance2(zname, starttime, endtime, zcheck);}

    @Override
    public List<Zselect_message>findztaskinput(String zname,Timestamp starttime,Timestamp endtime){return zselect_messageMapper.findztaskinput(zname, starttime, endtime);}

    @Override
    public List<Zselect_message>findtasktime(String zname,Timestamp starttime,Timestamp endtime){return zselect_messageMapper.findtasktime(zname, starttime, endtime);}
}
