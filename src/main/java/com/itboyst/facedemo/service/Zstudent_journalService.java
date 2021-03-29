package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_journal;

import java.util.List;

public interface Zstudent_journalService {

    public int insertstujournal(Zstudent_journal zstudentJournal);

    List<Zstudent_journal> findstudentjournaltime(String zstudentID);
}
