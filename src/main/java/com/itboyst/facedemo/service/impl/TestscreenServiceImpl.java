package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.TestScreen;
import com.itboyst.facedemo.mapper.TestscreenMapper;
import com.itboyst.facedemo.service.TestscreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestscreenServiceImpl implements TestscreenService {

    @Autowired
    TestscreenMapper testscreenMapper;

    @Override
    public int insertscreen(TestScreen testScreen){return testscreenMapper.insertscreen(testScreen);}

    @Override
    public List<TestScreen> findleftscreen(){return testscreenMapper.findleftscreen2();}


    public int findleftscreencount(){return testscreenMapper.findleftscreencount();}

}
