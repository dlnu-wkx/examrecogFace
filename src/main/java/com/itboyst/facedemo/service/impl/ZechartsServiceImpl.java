package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.mapper.ZechartsMapper;
import com.itboyst.facedemo.service.ZechartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZechartsServiceImpl implements ZechartsService {

    @Autowired
    ZechartsMapper zechartsMapper;

    @Override
    public int yearTrainNumber(String trainRoomID) {
        return zechartsMapper.yearTrainNumber(trainRoomID);
    }

    @Override
    public int monthTrainNumber(String trainRoomID) {
        return zechartsMapper.monthTrainNumber(trainRoomID);
    }

    @Override
    public int weekTrainNumber(String trainRoomID) {

        return zechartsMapper.weekTrainNumber(trainRoomID);
    }

    @Override
    public int todayTrainNumber(String trainRoomID) {
        return zechartsMapper.todayTrainNumber(trainRoomID);
    }

    @Override
    public int loginNumber(String zLocation) {
        return zechartsMapper.loginNumber(zLocation);
    }

    @Override
    public int trainingNumber(String zLocation) {
        return zechartsMapper.trainingNumber(zLocation);
    }

    @Override
    public int freeNumber(String zLocation) {
        return zechartsMapper.freeNumber(zLocation);
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

    @Override
    public String getClassName(String zGradeId) {
        return zechartsMapper.getClassName(zGradeId);
    }

    @Override
    public int getTotalNumber(String zGradeId) {
        return zechartsMapper.getTotalNumber(zGradeId);
    }

    @Override
    public int getArrivedNumber(String zScheduleId, String zGradeId) {
        return zechartsMapper.getArrivedNumber(zScheduleId, zGradeId);
    }

    @Override
    public List<String> getNotArrivedStudents(String zScheduleId, String zGradeId) {
        return zechartsMapper.getNotArrivedStudents(zScheduleId,zGradeId);
    }
}
