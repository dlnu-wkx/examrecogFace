package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztesting_input;
import com.itboyst.facedemo.mapper.Ztesting_inputMapper;
import com.itboyst.facedemo.service.Ztesting_inputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zstesting_inputServiceImpl implements Ztesting_inputService {

    @Autowired
    private Ztesting_inputMapper ztestingInputMapper;

    @Override
    public  int deletebystscheid(String zstudentscheduleID){
        return ztestingInputMapper.deleteftestinput(zstudentscheduleID);
    }

    @Override
    public int addtestinput(List<Ztesting_input> data){
        return ztestingInputMapper.addlist(data);
    }

    @Override
    public int updatelist(Ztesting_input ztesting_input){
        return ztestingInputMapper.updatelist(ztesting_input);
    }
}
