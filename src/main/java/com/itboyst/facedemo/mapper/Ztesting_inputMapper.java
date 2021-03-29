package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztesting_input;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Ztesting_inputMapper {

    public int deleteftestinput(String zstudentscheduleID);

    public int addlist(@Param("list") List<Ztesting_input> list);

    public int updatelist(Ztesting_input ztesting_input);
}
