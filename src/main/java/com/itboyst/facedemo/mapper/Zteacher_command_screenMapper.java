package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_command_screen;

public interface Zteacher_command_screenMapper {

    public int insertscreencommand(Zteacher_command_screen zteacher_command_screen);

    public Zteacher_command_screen findscreencommandById(String zid);

    public int updatecsstbyid(String zid,String zcontent);
}
