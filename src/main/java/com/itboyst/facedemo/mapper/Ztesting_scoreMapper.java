package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Ztesting_score;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Ztesting_scoreMapper {

    public int insertscore(Ztesting_score ztesting_score);
}
