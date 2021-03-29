package com.itboyst.facedemo.service.impl;


import com.itboyst.facedemo.dto.Zteacher_command;
import com.itboyst.facedemo.mapper.Zteacher_commandMapper;
import com.itboyst.facedemo.service.Zteacher_commandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_commandServiceImpl implements Zteacher_commandService {
    @Autowired
    Zteacher_commandMapper zteacher_commandMapper;

    @Override
    public List<Zteacher_command> selectcommand(String ztrainingroomID){

       return zteacher_commandMapper.findcommand(ztrainingroomID);
    }

    @Override
    public int insertcommand(Zteacher_command zteacher_command){
        return zteacher_commandMapper.insertcommand(zteacher_command);
    }

    @Override
    public int deltebefore(String ztraining_roomid,String ztype){
        return  zteacher_commandMapper.deletebefore(ztraining_roomid,ztype);
    }

    public int updatestate(Zteacher_command zteacher_command){
        return zteacher_commandMapper.updatestate(zteacher_command);
    }

    @Override
    public Zteacher_command findcommandById(String zid) {
        return zteacher_commandMapper.findcommandById(zid);
    }

    @Override
    public int updateCommandByroomandZtype(String ztype, String ztrainingroomID) {
        return zteacher_commandMapper.updateCommandByroomandZtype(ztype,ztrainingroomID);
    }


    public int findcountgp(Zteacher_command zteacher_command){return zteacher_commandMapper.findcountgp(zteacher_command);}


    public int updateCommandByid(String zid,String zstatus){return zteacher_commandMapper.updateCommandByid(zid, zstatus);}

    public int updatecontentbyid(String zid,String zcontent){return zteacher_commandMapper.updatecontentbyid(zid,zcontent);}

    public Zteacher_command findscreencommand(String ztrainingroomID,String zscheduleID){return zteacher_commandMapper.findscreencommand(ztrainingroomID, zscheduleID);}

    public Zteacher_command findscrenclose(String ztrainingroomID,String zscheduleID){return zteacher_commandMapper.findscrenclose(ztrainingroomID, zscheduleID);}

    public int updatestatbyclose(Zteacher_command zteacher_command){return zteacher_commandMapper.updatestatbyclose(zteacher_command);}

    public int findscrenclosefuncount(String ztrainingroomID,String zscheduleID){return zteacher_commandMapper.findscrenclosefuncount(ztrainingroomID, zscheduleID);}

    @Override
    public List<Zteacher_command>findinspect(String ztrainingroomID) {
        return zteacher_commandMapper.findinspect(ztrainingroomID);
    }

    public Zteacher_command findscrenclosefun(String ztrainingroomID,String zscheduleID){return zteacher_commandMapper.findscrenclosefun(ztrainingroomID, zscheduleID);}
}
