package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.mapper.ZgradeMapper;
import com.itboyst.facedemo.service.ZgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZgradeServiceImpl implements ZgradeService {
    @Autowired
    ZgradeMapper zgradeMapper;

    @Override
    public List<Zgrade> findallgrade(){
        return zgradeMapper.findallgrade();
    }


    @Override
    public List<String> findgradebynamelike(String zname){return zgradeMapper.findgradebynamelike(zname);}

    @Override
    public List<Zgrade> findgradebymajorid(String id) {
        return zgradeMapper.findgradebymajorid(id);
    }

    @Override
    public String findzidbyzname(String zname) {
        return zgradeMapper.findzidbyzname(zname);
    }

    @Override
    public List<Zgrade> findallgradebytrainroom(String ztrainingroomID) {
        return zgradeMapper.findallgradebytrainroom(ztrainingroomID);
    }

    @Override
    public List<Zgrade> findgroup() {
        return zgradeMapper.findgroup();
    }

    @Override
    public List<Zgrade> findgrade() {
        return zgradeMapper.findgrade();
    }

    @Override
    public int addgroup(Zgrade zgrade) {
        return zgradeMapper.addgroup(zgrade);
    }

    @Override
    public int deletgroup(String zid) {
        return zgradeMapper.deletgroup(zid);
    }

}
