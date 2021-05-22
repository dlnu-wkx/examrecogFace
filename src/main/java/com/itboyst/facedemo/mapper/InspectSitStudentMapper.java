package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.InspectSitStudent;
import com.itboyst.facedemo.dto.InspectSitTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;
@Mapper
public interface InspectSitStudentMapper {

    List<InspectSitStudent>  findStudentByDateAndTrainingIdHavecamera(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitStudent>  findStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck);


    List<InspectSitStudent>  findStudentByDateAndTrainingIdASCHavecamera(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitStudent>  findStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp,String zcheck);

    List<InspectSitTeacher>  findTeacherByDateAndTrainingIdASCHavecamera(String camerasIP, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitTeacher>  findTeacherByDateAndTrainingIdASC(String camerasIP, Timestamp timestamp,String zcheck);

    List<InspectSitTeacher> findTeacherByDateAndTrainingIDdistinctHavecamera(String camerasIP, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitTeacher> findTeacherByDateAndTrainingIDdistinct(String camerasIP, Timestamp timestamp,String zcheck);

    List<InspectSitStudent> inspectfindStudentByDateAndTrainingIdASCHavecamera(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitStudent> inspectfindStudentByDateAndTrainingIdASC(String ztrainingroomID, Timestamp timestamp,String zcheck);

    //带摄像头信息
    List<InspectSitStudent>  inspectfindStudentByDateAndTrainingIdHavecamera(String ztrainingroomID, Timestamp timestamp,String zcheck,String cameraname);
    List<InspectSitStudent>  inspectfindStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck);

    //查询特定摄像头的数据
    List<InspectSitStudent>  signIninspectfindStudentByDateAndTrainingIdHavecamera(String ztrainingroomID, Timestamp timestamp,String zcheck,String zid,String cameraname);
    List<InspectSitStudent>  signIninspectfindStudentByDateAndTrainingId(String ztrainingroomID, Timestamp timestamp,String zcheck,String zid);
}
