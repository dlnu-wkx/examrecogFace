package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.InspectSitTeacher;
import com.itboyst.facedemo.mapper.InspectSitStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class InspectSitStudentServiceImpl implements InspectSitStudentService{

    @Autowired
    InspectSitStudentMapper inspectSitStudentMapper;

    @Override
    public List<InspectSitStudent> findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.findStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);
        }
        return inspectSitStudentMapper.findStudentByDateAndTrainingIdHavecamera(ztrainingroomID,timestamp,zcheck,cameraname);
    }

    @Override
    public List<InspectSitStudent> findStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.findStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp,zcheck);
        }
        return inspectSitStudentMapper.findStudentByDateAndTrainingIdASCHavecamera(ztrainingroomID,timestamp,zcheck,cameraname);
    }

    @Override
    public List<InspectSitTeacher> findTeacherByDateAndTrainingIdASC(String camerasIP, Timestamp timestamp,String zcheck,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.findTeacherByDateAndTrainingIdASC(camerasIP,timestamp,zcheck);
        }
        return inspectSitStudentMapper.findTeacherByDateAndTrainingIdASCHavecamera(camerasIP,timestamp,zcheck,cameraname);
    }

    @Override
    public List<InspectSitTeacher> findTeacherByDateAndTrainingIDdistinct(String camerasIP, Timestamp timestamp, String zcheck,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.findTeacherByDateAndTrainingIDdistinct(camerasIP,timestamp,zcheck);
        }
        return inspectSitStudentMapper.findTeacherByDateAndTrainingIDdistinctHavecamera(camerasIP,timestamp,zcheck,cameraname);
    }

    @Override
    public List<InspectSitStudent> inspectfindStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp, String zcheck,String cameraname) {
        if(cameraname.equals("")){
            inspectSitStudentMapper.inspectfindStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp,zcheck);
        }
        return inspectSitStudentMapper.inspectfindStudentByDateAndTrainingIdHavecamera(ztrainingroomID,timestamp,zcheck,cameraname);
    }


    @Override
    public List<InspectSitStudent> signIninspectfindStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp, String zcheck, String zid,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.signIninspectfindStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck,zid);
        }
        return inspectSitStudentMapper.signIninspectfindStudentByDateAndTrainingIdHavecamera(ztrainingroomID,timestamp,zcheck,zid,cameraname);
    }

    @Override
    public List<InspectSitStudent> inspectfindStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp, String zcheck,String cameraname) {
        if(cameraname.equals("")){
            return inspectSitStudentMapper.inspectfindStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);
        }
        return inspectSitStudentMapper.inspectfindStudentByDateAndTrainingIdHavecamera(ztrainingroomID,timestamp,zcheck,cameraname);
    }


}
