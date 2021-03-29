package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztempuser;
import com.itboyst.facedemo.mapper.ZtempuserMapper;
import com.itboyst.facedemo.service.ZtempuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ZtempuserServiceImpl implements ZtempuserService {

    @Autowired
    ZtempuserMapper ztempuserMapper;

    @Override
    public int insertoneztempuser(Ztempuser ztempuser) {
        return ztempuserMapper.insertoneztempuser(ztempuser);
    }

    @Override
    public List<Ztempuser> findAlltempuserbytime() {
        return ztempuserMapper.findAlltempuserbytime();
    }

    @Override
    public int update(String zid, String zname) {
        return ztempuserMapper.update(zid,zname);
    }

    @Override
    public Ztempuser findByzid(String zid) {
        return ztempuserMapper.findByzid(zid);
    }

    @Override
    public int upzstatusbyoriginalPictureUrl(String originalPictureUrl) {
        return ztempuserMapper.upzstatusbyoriginalPictureUrl(originalPictureUrl);
    }

    @Override
    public int updatestatustostudent(String originalPictureUrl) {
        return ztempuserMapper.updatestatustostudent(originalPictureUrl);
    }

    @Override
    public Timestamp findmaxtime(String studorteach) {
        return ztempuserMapper.findmaxtime(studorteach);
    }
}
