package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztask_input;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Ztask_inputMapper {
    public int isnerttaskinput(Ztask_input ztask_input);

    public int findistaskinput(Ztask_input ztask_input);

    public int updatetaskselfcheck(Ztask_input ztask_input);

    public int updatetasktcheckbyid(String zid,String zteachercheck);

    public Ztask_input findsatcheckbyid(String ztrainingtaskassessID,String ztrainingtaskID,String zstudentscheduleID);
}
