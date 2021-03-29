package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_cookie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zteacher_cookieMapper {

    public List<Zteacher_cookie> findbyfacid(int zfaceinfoID);
}
