package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_cookie;

import java.sql.Timestamp;
import java.util.List;

public interface Zstudent_cooikeService {

    public List<Zstudent_cookie> findscookiemes(String ztrainingroomID, Timestamp zrecongnizetime, String zstudentID);
}
