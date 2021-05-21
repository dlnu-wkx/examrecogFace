package com.itboyst.facedemo.service;

import java.util.List;

public interface ZechartsService {
    int yearTrainNumber(String trainRoomID);
    int monthTrainNumber(String trainRoomID);
    int weekTrainNumber(String trainRoomID);
    int todayTrainNumber(String trainRoomID);

    int loginNumber(String zLocation);
    int trainingNumber(String zLocation);
    int freeNumber(String zLocation);

    int yearTestPassNumber();
    int monthTestPassNumber();
    int weekTestPassNumber();
    int yearTestTotalNumber();
    int monthTestTotalNumber();
    int weekTestTotalNumber();

    String getClassName(String zGradeId);
    int getTotalNumber(String zGradeId);
    int getArrivedNumber(String zScheduleId, String zGradeId);
    List<String> getNotArrivedStudents(String zScheduleId, String zGradeId);
}
