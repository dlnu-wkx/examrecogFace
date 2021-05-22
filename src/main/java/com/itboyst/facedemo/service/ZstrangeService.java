package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstrange;

import java.sql.Timestamp;
import java.util.List;

public interface ZstrangeService {
    int insertZstrange(Zstrange zstrange);

    List<Zstrange> findAll(String zrecognizeIP, Timestamp timestamp, String zcheck,String cameraname);


}
