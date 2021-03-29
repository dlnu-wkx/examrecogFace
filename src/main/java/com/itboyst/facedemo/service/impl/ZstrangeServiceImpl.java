package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstrange;
import com.itboyst.facedemo.mapper.ZstrangeMapper;
import com.itboyst.facedemo.service.ZstrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class ZstrangeServiceImpl implements ZstrangeService {

    @Autowired
    ZstrangeMapper zstrangeMapper;

    @Override
    public int insertZstrange(Zstrange zstrange) {
        //首先删除图片访问地址相同的记录然后添加新的的记录
        int i = zstrangeMapper.deleteOriginalPictureUrl(zstrange.getOriginalPictureUrl());
        return zstrangeMapper.insertZstrange(zstrange);
    }

    @Override
    public List<Zstrange> findAll(String zrecognizeIP, Timestamp timestamp, String zcheck) {
        return zstrangeMapper.findAll(zrecognizeIP,timestamp,zcheck);
    }
}
