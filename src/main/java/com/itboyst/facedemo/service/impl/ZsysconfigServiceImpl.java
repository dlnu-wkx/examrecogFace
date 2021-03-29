package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zsysconfig;
import com.itboyst.facedemo.mapper.ZsysconfigMapper;
import com.itboyst.facedemo.service.ZsysconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZsysconfigServiceImpl implements ZsysconfigService {

    @Autowired
    private ZsysconfigMapper zsysconfigMapper;
    @Override
    public Zsysconfig findIPByZname(String zname) {
        return zsysconfigMapper.findIPByZname(zname);
    }
}
