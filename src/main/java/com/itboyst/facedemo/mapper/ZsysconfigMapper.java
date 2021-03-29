package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zsysconfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZsysconfigMapper {
    Zsysconfig findIPByZname(String zname);
}
