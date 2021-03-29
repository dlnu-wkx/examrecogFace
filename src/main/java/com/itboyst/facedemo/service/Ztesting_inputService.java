package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztesting_input;

import java.util.List;

public interface Ztesting_inputService {
    public  int deletebystscheid(String zstudentscheduleID);

    public int addtestinput(List<Ztesting_input> data);

    public int updatelist(Ztesting_input ztesting_input);
}
