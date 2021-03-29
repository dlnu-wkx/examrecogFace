package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zmanager;

import java.util.List;

public interface ZmanagerService {
    public int insertmanager(Zmanager zmanager);

    public Zmanager findallbymanagername(String name);

    public int updatemanager(Zmanager zmanager);

    public List<String> findmanagernamelike(String name);
}
