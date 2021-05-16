package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.ZechartsMapper;
import com.itboyst.facedemo.service.ZechartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZechartsServiceImpl implements ZechartsService {

    @Autowired
    ZechartsMapper zechartsMapper;

    @Override
    public int yearStatistics() {
        return zechartsMapper.yearStatistics();
    }

    @Override
    public int monthStatistics() {
        return zechartsMapper.monthStatistics();
    }

    @Override
    public int weekStatistics() {
        return zechartsMapper.weekStatistics();
    }

    @Override
    public int todayStatistics() {
        return zechartsMapper.todayStatistics();
    }
}
