package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_camera;
import com.itboyst.facedemo.mapper.Ztraining_cameraMapper;
import com.itboyst.facedemo.service.Ztraining_cameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Ztraining_cameraServiceImpl implements Ztraining_cameraService {

    @Autowired
    Ztraining_cameraMapper ztraining_cameraMapper;
    @Override
    public List<Ztraining_camera> findAllByZtrainingroomID(String ZtrainingroomID,String type) {
        return ztraining_cameraMapper.findAllByZtrainingroomID(ZtrainingroomID,type);
    }

    @Override
    public synchronized int insertCamera(Ztraining_camera ztraining_camera) {
        String zcamerIP = "";
        String ztitle = "";
        String ztrainingroomID = "";
        if(ztraining_camera != null){

            zcamerIP = ztraining_camera.getZcameraIP();
            ztitle = ztraining_camera.getZtitle();
            ztrainingroomID = ztraining_camera.getZtrainingroomID();
        }
        //把相同zcameraIP的摄像头名字合并
        Ztraining_camera zc =ztraining_cameraMapper.findAllByZcameraIP(zcamerIP,ztitle,ztrainingroomID);

        if(zc != null){
            //更新这条记录
            String newName = ztraining_camera.getZcameraName();
            String oldName = zc.getZcameraName();
            String[] str =oldName.split(",");
            boolean aim =useLoop(str,newName);
            if(aim ==false){
                zc.setZcameraName(oldName+","+newName);
                return  ztraining_cameraMapper.updateecamera(zc);
            }

            return 0;
        }


        return ztraining_cameraMapper.insertCamera(ztraining_camera);
    }


    //判断字符串是否包含特定的字符
    public static boolean useLoop(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }

}
