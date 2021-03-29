package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztempuser;

import java.sql.Timestamp;
import java.util.List;


public interface ZtempuserService {
    int insertoneztempuser(Ztempuser ztempuser);

    List<Ztempuser> findAlltempuserbytime();

    int update(String zid,String zname);

    Ztempuser findByzid(String zid);

    int upzstatusbyoriginalPictureUrl(String originalPictureUrl);

    int updatestatustostudent(String originalPictureUrl);

    Timestamp findmaxtime(String studorteach);
}
