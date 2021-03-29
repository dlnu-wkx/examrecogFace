package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zcourse;

import java.util.List;

public interface ZcourseService {
    int insertCourse(Zcourse zcourse);

    List<Zcourse> findAllcourse();
}
