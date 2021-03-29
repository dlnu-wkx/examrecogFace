package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztask_input;
import com.itboyst.facedemo.mapper.Ztask_inputMapper;
import com.itboyst.facedemo.service.Ztask_inputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ztask_inputServiceImpl implements Ztask_inputService {
    @Autowired
    Ztask_inputMapper ztask_inputMapper;


    @Override
    public int isnerttaskinput(Ztask_input ztask_input){
        return ztask_inputMapper.isnerttaskinput(ztask_input);
    }
    @Override
    public int findistaskinput(Ztask_input ztask_input){
        return ztask_inputMapper.findistaskinput(ztask_input);
    }

    @Override
    public int updatetaskselfcheck(Ztask_input ztask_input){
        return ztask_inputMapper.updatetaskselfcheck(ztask_input);
    }


}
