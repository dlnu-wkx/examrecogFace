package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_login;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zteacher_loginMapper {

    //插入从摄像头识别的老师

    int delteacher(String originalPictureUrl);

    int insertteacher(Zteacher_login zteacher_login);
}
