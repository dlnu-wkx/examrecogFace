package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.ztraining_room;
import com.itboyst.facedemo.mapper.Ztraining_roomMapper;
import com.itboyst.facedemo.service.Ztraining_roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_roomServiceImpl implements Ztraining_roomService {

    @Autowired
    Ztraining_roomMapper zrm;

    @Override
    public ztraining_room findbyip(String zid){
        return zrm.findbyrid(zid);

    }

    @Override
    public List<ztraining_room> findallztrainroom(){
        return zrm.findallztrainroom();
    }

    @Override
    public String findztrainroomNamebyfacilityID(String cameraID) {
        return zrm.findztrainroomNamebyfacilityID(cameraID);
    }
}
