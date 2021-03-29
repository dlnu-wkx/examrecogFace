package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zteacher_journal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zteacher_journalMapper {
    public int insertteacherjournal(Zteacher_journal zteacher_journal);

    List<Zteacher_journal> findteacherlogin(String zteacherID);
}
