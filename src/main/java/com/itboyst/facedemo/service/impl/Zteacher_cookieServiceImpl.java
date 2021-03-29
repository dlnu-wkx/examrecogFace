package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.mapper.Zteacher_cookieMapper;
import com.itboyst.facedemo.service.Zteacher_cookieSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_cookieServiceImpl implements Zteacher_cookieSerice {
    @Autowired
    Zteacher_cookieMapper zteacher_cookieMapper;

    @Override
    public List<Zteacher_cookie> findbyfaceid(int faceinfoID){
        return zteacher_cookieMapper.findbyfacid(faceinfoID);
    }
}
