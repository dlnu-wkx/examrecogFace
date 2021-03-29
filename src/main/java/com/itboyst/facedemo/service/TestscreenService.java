package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.TestScreen;

import java.util.List;

public interface TestscreenService {

    public int insertscreen(TestScreen testScreen);

    public List<TestScreen> findleftscreen();

    public int findleftscreencount();
}
