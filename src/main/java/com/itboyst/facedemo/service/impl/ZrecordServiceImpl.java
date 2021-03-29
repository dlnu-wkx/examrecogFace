package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zrecord;
import com.itboyst.facedemo.mapper.ZrecordMapper;
import com.itboyst.facedemo.service.ZrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZrecordServiceImpl implements ZrecordService {
    @Autowired
    ZrecordMapper zrecordMapper;
    @Override
    public int insertZrecord(Zrecord zrecord) {
        return zrecordMapper.insertZrecord(zrecord);
    }
}
