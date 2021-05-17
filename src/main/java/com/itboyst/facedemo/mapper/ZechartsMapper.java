package com.itboyst.facedemo.mapper;


public interface ZechartsMapper {
    //分别获取年、月、周、日的实训人数
    int yearStatistics();
    int monthStatistics();
    int weekStatistics();
    int todayStatistics();

    //分别获取当前教师所在车间的实训、离开和退出系统的人数
    int trainingNumber(String ip4);
    int leaveNumber(String ip4);
    int exitNumber(String ip4);

    //获取测试表中年月周的通过人数和通人数
    int yearTestPassNumber();
    int monthTestPassNumber();
    int weekTestPassNumber();
    int yearTestTotalNumber();
    int monthTestTotalNumber();
    int weekTestTotalNumber();
}
