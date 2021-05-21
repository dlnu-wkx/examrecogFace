package com.itboyst.facedemo.mapper;


import java.util.ArrayList;
import java.util.List;

public interface ZechartsMapper {
    //分别获取年、月、周、日的实训人数
    int yearTrainNumber(String trainRoomID);
    int monthTrainNumber(String trainRoomID);
    int weekTrainNumber(String trainRoomID);
    int todayTrainNumber(String trainRoomID);

    //分别获取当前教师所在车间的实训、离开和退出系统的人数
    int loginNumber(String zLocation);
    int trainingNumber(String zLocation);
    int freeNumber(String zLocation);

    //获取测试表中年月周的通过人数和通人数
    int yearTestPassNumber();
    int monthTestPassNumber();
    int weekTestPassNumber();
    int yearTestTotalNumber();
    int monthTestTotalNumber();
    int weekTestTotalNumber();

    //获取当前教师上课的班级信息
    //获取当前上课班级的班级名称
    String getClassName(String zGradeId);
    //应到人数
    int getTotalNumber(String zGradeId);
    //实到人数
    int getArrivedNumber(String zScheduleId, String zGradeId);
    //未签到姓名
    List<String> getNotArrivedStudents(String zScheduleId, String zGradeId);

}
