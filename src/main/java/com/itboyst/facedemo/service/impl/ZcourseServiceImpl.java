package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zcourse;
import com.itboyst.facedemo.mapper.ZcourseMapper;
import com.itboyst.facedemo.service.ZcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZcourseServiceImpl implements ZcourseService {

    @Autowired
    ZcourseMapper zcourseMapper;

    @Override
    public int insertCourse(Zcourse zcourse) {
        return zcourseMapper.insertCourse(zcourse);
    }

    @Override
    public List<Zcourse> findAllcourse() {
        return zcourseMapper.findAllcourse();
    }
}
