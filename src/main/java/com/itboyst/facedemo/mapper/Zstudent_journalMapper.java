package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent_journal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Zstudent_journalMapper {
    public int insertstujounal(Zstudent_journal zstudentJournal);

    List<Zstudent_journal> findstudentjournaltime(String zstudentID);
}
