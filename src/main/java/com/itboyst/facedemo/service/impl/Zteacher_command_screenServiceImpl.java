package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_command_screen;
import com.itboyst.facedemo.mapper.Zteacher_command_screenMapper;
import com.itboyst.facedemo.service.Zteacher_command_screenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zteacher_command_screenServiceImpl implements Zteacher_command_screenService {
    @Autowired
    Zteacher_command_screenMapper zteacher_command_screenMapper;

    public Zteacher_command_screen findscreencommandById(String zid){return zteacher_command_screenMapper.findscreencommandById(zid);}

    public int updatecsstbyid(String zid,String zcontent){return zteacher_command_screenMapper.updatecsstbyid(zid, zcontent);}


    public int insertscreencommand(Zteacher_command_screen zteacher_command_screen){return zteacher_command_screenMapper.insertscreencommand(zteacher_command_screen);}
}
