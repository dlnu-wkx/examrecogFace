package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zmajor;

import java.util.List;

public interface ZmajorService {
    public List<Zmajor> findallmajor();

    String findzidbyzname(String zname);
}
