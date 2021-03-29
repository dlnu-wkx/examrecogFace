package com.itboyst.facedemo.mapper;


import com.itboyst.facedemo.dto.Zgrade;

import java.util.List;

public interface ZgradeMapper {

    public List<Zgrade> findallgrade();

    public List<String> findgradebynamelike(String zname);

    List<Zgrade> findgradebymajorid(String id);

    String findzidbyzname(String zname);
}
