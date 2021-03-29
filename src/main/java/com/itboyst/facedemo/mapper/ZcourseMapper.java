package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zcourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ZcourseMapper {

    int insertCourse(Zcourse zcourse);

    List<Zcourse> findAllcourse();
}
