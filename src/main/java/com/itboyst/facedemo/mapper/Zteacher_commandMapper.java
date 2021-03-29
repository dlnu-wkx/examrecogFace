package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_command;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zteacher_commandMapper {

   public List<Zteacher_command> findcommand(String ztrainingroomID);

   public int insertcommand(Zteacher_command zteacher_command);

   public int deletebefore(String ztrainingroomID,String ztype);

   public int updatestate(Zteacher_command zteacher_command);

   Zteacher_command findcommandById(String zid);

   int updateCommandByroomandZtype(String ztype,String ztrainingroomID);

   public int findcountgp(Zteacher_command zteacher_command);

   public Zteacher_command findscrenclose(String ztrainingroomID,String zscheduleID);

   public int updateCommandByid(String zid,String zstatus);

   public int updatecontentbyid(String zid,String zcontent);

   public Zteacher_command findscreencommand(String ztrainingroomID,String zscheduleID);

   public int updatestatbyclose(Zteacher_command zteacher_command);

   public Zteacher_command findscrenclosefun(String ztrainingroomID,String zscheduleID);

   public int findscrenclosefuncount(String ztrainingroomID,String zscheduleID);

   List<Zteacher_command> findinspect(String ztrainingroomID);
}
