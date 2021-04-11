package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztask_input;

import java.util.List;

public interface Ztask_inputService {
    public int isnerttaskinput(Ztask_input ztask_input);

    public int findistaskinput(Ztask_input ztask_input);

    public int updatetaskselfcheck(Ztask_input ztask_input);

    public int updatetasktcheckbyid(String zid,String zteachercheck);

    public Ztask_input findsatcheckbyid(String ztrainingtaskassessID, String ztrainingtaskID, String zstudentscheduleID);
}
