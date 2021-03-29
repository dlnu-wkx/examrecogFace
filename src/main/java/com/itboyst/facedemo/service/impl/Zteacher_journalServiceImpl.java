package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zteacher_journal;
import com.itboyst.facedemo.mapper.Zteacher_journalMapper;
import com.itboyst.facedemo.service.Zteacher_journalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zteacher_journalServiceImpl implements Zteacher_journalService {
    @Autowired
    Zteacher_journalMapper zteacher_journalMapper;
    @Override
    public int inserteacherjournal(Zteacher_journal zteacher_journal){
        return zteacher_journalMapper.insertteacherjournal(zteacher_journal);
    }

    @Override
    public List<Zteacher_journal> findteacherlogin(String zteacherID) {
        return zteacher_journalMapper.findteacherlogin(zteacherID);
    }

}
