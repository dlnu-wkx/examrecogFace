package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_login;
import com.itboyst.facedemo.mapper.Zteacher_loginMapper;
import com.itboyst.facedemo.service.Zteacher_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zteacher_loginServiceImpl implements Zteacher_loginService {

    @Autowired
    Zteacher_loginMapper zteacher_loginMapper;

    @Override
    public synchronized int delAndinsertteacher(Zteacher_login zteacher_login) {//添加同步防止产生脏数据
       //先删除所有的login记录
        int i =zteacher_loginMapper.delteacher(zteacher_login.getOriginalPictureUrl());
        //增加一条登录记录
        int j =zteacher_loginMapper.insertteacher(zteacher_login);
        return j;
    }
}
