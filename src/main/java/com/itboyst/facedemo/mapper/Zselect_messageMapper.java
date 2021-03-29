package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zselect_message;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zselect_messageMapper {

    public List<Zselect_message>findattandance(String zname, Timestamp starttime,Timestamp endtime,String zcheck);

    public List<Zselect_message>findinandout(String zname,Timestamp starttime,Timestamp endtime);

    public List<Zselect_message> findupheads(String zname, Timestamp starttime,Timestamp endtime,String ztype);

    public List<Zselect_message> findallleave(String zname, Timestamp starttime, Timestamp endtime);

    public List<Zselect_message> findscorebyname(String zname,Timestamp starttime,Timestamp endtime);

    public List<Zselect_message>findattandance2(String zname, Timestamp starttime,Timestamp endtime,String zcheck);

    public List<Zselect_message>findztaskinput(String zname,Timestamp starttime,Timestamp endtime);

    public List<Zselect_message>findtasktime(String zname,Timestamp starttime,Timestamp endtime);
}
