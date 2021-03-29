package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztempuser;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ZtempuserMapper {
    int insertoneztempuser(Ztempuser ztempuser);

    List<Ztempuser> findAlltempuserbytime();

    int update(String zid,String zname);

    Ztempuser findByzid(String zid);

    int upzstatusbyoriginalPictureUrl(String originalPictureUrl);

    int updatestatustostudent(String originalPictureUrl);

    Timestamp findmaxtime(String studorteach);
}
