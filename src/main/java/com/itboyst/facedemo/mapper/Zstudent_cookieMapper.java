package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_cookie;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zstudent_cookieMapper {
    public List<Zstudent_cookie> findscookiemes(String ztrainingroomID, Timestamp zrecongnizetime, String zstudentID);
}
