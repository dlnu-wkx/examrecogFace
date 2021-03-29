package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent_facility;
import com.itboyst.facedemo.mapper.Zname_facilityMapper;
import com.itboyst.facedemo.service.Zname_facilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zname_facilityServiceImpl implements Zname_facilityService {
    @Autowired
    Zname_facilityMapper zname_facilityMapper;

    public Zstudent_facility findnameandfaclity(String ztrainingroomID){
        return zname_facilityMapper.findnameandf(ztrainingroomID);
    }    

}
