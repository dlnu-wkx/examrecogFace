package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zteacher_journal;

import java.util.List;

public interface Zteacher_journalService {
    public int inserteacherjournal(Zteacher_journal zteacher_journal);

    List<Zteacher_journal> findteacherlogin(String zteacherID);
}
