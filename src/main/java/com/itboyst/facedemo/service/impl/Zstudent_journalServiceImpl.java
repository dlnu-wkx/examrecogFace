package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Zstudent_journal;
import com.itboyst.facedemo.mapper.Zstudent_journalMapper;
import com.itboyst.facedemo.service.Zstudent_journalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Zstudent_journalServiceImpl implements Zstudent_journalService {

    @Autowired
    Zstudent_journalMapper zstudentJournalMapper;

    @Override
    public int insertstujournal(Zstudent_journal zstudentJournal){
        int i=0;
        i=zstudentJournalMapper.insertstujounal(zstudentJournal);

        return i;
    }

    @Override
    public List<Zstudent_journal> findstudentjournaltime(String zstudentID) {
        return zstudentJournalMapper.findstudentjournaltime(zstudentID);
    }
}
