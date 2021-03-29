package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zmajor;
import com.itboyst.facedemo.mapper.ZmajorMapper;
import com.itboyst.facedemo.service.ZmajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZmajorServiceImpl implements ZmajorService {

    @Autowired
    ZmajorMapper zmajorMapper;

    @Override
    public List<Zmajor> findallmajor(){
        return zmajorMapper.findallmajor();
    }

    @Override
    public String findzidbyzname(String zname) {
        return zmajorMapper.findzidbyzname(zname);
    }
}
