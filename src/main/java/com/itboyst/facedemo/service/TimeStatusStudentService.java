package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.TimeStatusStudent;

import java.util.List;

public interface TimeStatusStudentService {
    List<TimeStatusStudent> findByZtrainingroomID(String ztrainingroomID);
}
