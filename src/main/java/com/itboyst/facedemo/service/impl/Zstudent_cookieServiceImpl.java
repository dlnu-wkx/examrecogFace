package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.mapper.Zstudent_cookieMapper;
import com.itboyst.facedemo.service.Zstudent_cooikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class Zstudent_cookieServiceImpl implements Zstudent_cooikeService {

    @Autowired
    Zstudent_cookieMapper zstudent_cookieMapper;

    @Override
    public List<Zstudent_cookie> findscookiemes(String ztrainingroomID, Timestamp zrecongnizetime, String zstudentID){

        return zstudent_cookieMapper.findscookiemes(ztrainingroomID,zrecongnizetime,zstudentID);

    }
}
