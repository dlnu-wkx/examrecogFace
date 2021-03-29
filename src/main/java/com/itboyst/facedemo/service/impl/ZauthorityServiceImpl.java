package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zauthority;
import com.itboyst.facedemo.mapper.ZauthorityMapper;
import com.itboyst.facedemo.service.ZauthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZauthorityServiceImpl implements ZauthorityService {

    @Autowired
    ZauthorityMapper zauthorityMapper;

    @Override
    public List<Zauthority> findall() {
        return zauthorityMapper.findall();
    }
}
