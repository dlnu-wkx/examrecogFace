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

    @Override
    public int trainingNumber(String ip4) {
        return zechartsMapper.trainingNumber(ip4);
    }

    @Override
    public int leaveNumber(String ip4){
        return zechartsMapper.leaveNumber(ip4);
    }

    @Override
    public int exitNumber(String ip4) {
        return zechartsMapper.exitNumber(ip4);
    }

    @Override
    public int yearTestPassNumber() {
        return zechartsMapper.yearTestPassNumber();
    }

    @Override
    public int monthTestPassNumber() {
        return zechartsMapper.monthTestPassNumber();
    }

    @Override
    public int weekTestPassNumber() {
        return zechartsMapper.weekTestPassNumber();
    }

    @Override
    public int yearTestTotalNumber() {
        return zechartsMapper.yearTestTotalNumber();
    }

    @Override
    public int monthTestTotalNumber() {
        return zechartsMapper.monthTestTotalNumber();
    }

    @Override
    public int weekTestTotalNumber() {
        return zechartsMapper.weekTestTotalNumber();
    }

}
