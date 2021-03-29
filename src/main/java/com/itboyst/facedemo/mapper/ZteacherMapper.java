package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZteacherMapper {
    public Zteacher selectbyname(String name);

    public int registerteacher(Zteacher zteacher);

    public int updateteacher(Zteacher zteacher);

    public List<String> findteachernamelike(String zname);

    Zteacher findteacherByzidentity(String zidentity);

    public String selectteachernamebyid(String zid);

    List<Zteacher> findAllteacher();

    int addtempteacher(Zteacher zteacher);
}
