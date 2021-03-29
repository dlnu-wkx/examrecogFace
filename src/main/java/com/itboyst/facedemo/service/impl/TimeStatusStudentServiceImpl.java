package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.TimeStatusStudent;
import com.itboyst.facedemo.mapper.TimeStatusStudentMapper;
import com.itboyst.facedemo.service.TimeStatusStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TimeStatusStudentServiceImpl implements TimeStatusStudentService {
    @Autowired
    TimeStatusStudentMapper timeStatusStudentMapper;
    @Override
    public List<TimeStatusStudent> findByZtrainingroomID(String ztrainingroomID) {
        return timeStatusStudentMapper.findByZtrainingroomID(ztrainingroomID);
    }
}
