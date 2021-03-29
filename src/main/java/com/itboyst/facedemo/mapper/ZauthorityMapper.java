package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zauthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZauthorityMapper {
    List<Zauthority> findall();
}
