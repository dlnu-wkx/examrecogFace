package com.itboyst.facedemo.service;

import com.itboyst.facedemo.domain.UserFaceInfo;

import java.sql.Timestamp;
import java.util.List;


public interface UserFaceInfoService {

    void insertSelective(UserFaceInfo userFaceInfo);

    int findcountbyface(String name);

    List<String> findcountnamelike(String name);

    public int updateuserfopath(String path,String face_id,String name);

    public int findcountfaceid(String face_id);

    public int findidbyfaceid(String face_id);

    int findAll();

    String  selectfaceidbyfpath(String fpath);

    int deltempuser(String face_id);

    int updatefacefeature(String face_id);

    Timestamp findcreatimebyfaceid(String face_id);
}
