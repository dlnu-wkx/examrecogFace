package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zmajor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZmajorMapper {

    public List<Zmajor> findallmajor();


    String findzidbyzname(String zname);
}
