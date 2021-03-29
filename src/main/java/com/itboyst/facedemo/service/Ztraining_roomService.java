package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_facility;
import com.itboyst.facedemo.dto.ztraining_room;

import java.util.List;

public interface Ztraining_roomService {

    public ztraining_room findbyip(String zid);

    public List<ztraining_room> findallztrainroom();

    String findztrainroomNamebyfacilityID(String cameraID);
}
