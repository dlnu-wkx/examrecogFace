package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zmanager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZmanagerMapper {   

    public int insertmanager(Zmanager zmanager);

    public Zmanager findallbymanagername(String name);

    public int updatemanager(Zmanager zmanager);

    public List<String> findmanagernamelike(String zname);
}
