package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_facility;
import com.itboyst.facedemo.dto.Ztraining_facility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zname_facilityMapper {

   public Zstudent_facility findnameandf(String zid);
}
