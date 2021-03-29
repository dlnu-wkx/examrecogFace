package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zmanager;
import com.itboyst.facedemo.mapper.ZmanagerMapper;
import com.itboyst.facedemo.service.ZmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZmanagerServiceImpl implements ZmanagerService {
    @Autowired
    ZmanagerMapper zmanagerMapper;

    @Override
    public int insertmanager(Zmanager zmanager){
        return zmanagerMapper.insertmanager(zmanager);
    }

    @Override
    public Zmanager findallbymanagername(String name){ return zmanagerMapper.findallbymanagername(name);}

    @Override
    public int updatemanager(Zmanager zmanager){return zmanagerMapper.updatemanager(zmanager);}

    @Override
    public List<String> findmanagernamelike(String name){return zmanagerMapper.findmanagernamelike(name);}
}
