package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztesting_score;
import com.itboyst.facedemo.mapper.Ztesting_scoreMapper;
import com.itboyst.facedemo.service.Ztesting_scoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ztestng_scoreServiceImpl implements Ztesting_scoreService {

    @Autowired
    Ztesting_scoreMapper ztesting_scoreMapper;

    @Override
    public int insertscore(Ztesting_score ztesting_score){return ztesting_scoreMapper.insertscore(ztesting_score);}


}
