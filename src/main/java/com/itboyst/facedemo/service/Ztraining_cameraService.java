package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Ztraining_camera;

import java.util.List;

public interface Ztraining_cameraService {

    List<Ztraining_camera> findAllByZtrainingroomID(String ZtrainingroomID,String type);

    int insertCamera(Ztraining_camera ztraining_camera);



}
