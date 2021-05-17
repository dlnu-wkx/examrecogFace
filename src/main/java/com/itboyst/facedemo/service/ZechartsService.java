package com.itboyst.facedemo.service;

public interface ZechartsService {
    int yearStatistics();
    int monthStatistics();
    int weekStatistics();
    int todayStatistics();

    int trainingNumber(String ip4);
    int leaveNumber(String ip4);
    int exitNumber(String ip4);

    int yearTestPassNumber();
    int monthTestPassNumber();
    int weekTestPassNumber();
    int yearTestTotalNumber();
    int monthTestTotalNumber();
    int weekTestTotalNumber();
}
