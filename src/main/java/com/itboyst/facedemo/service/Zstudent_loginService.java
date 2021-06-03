package com.itboyst.facedemo.service;

import com.itboyst.facedemo.dto.Zstudent_login;

import java.sql.Timestamp;
import java.util.List;

public interface Zstudent_loginService {
    public int updateloginmessage( Zstudent_login zstl);

    public int updateloginstate(String status,String ztrainingroomID);

    List<Zstudent_login> findAllStudentLogin();

    int deleteStudentLoginByzidentity(String zidentity);

    public int insertnowmessage(Zstudent_login zstudent_login);

    //查找学生的课程
    List<String> findScheduleBytimeandzstudentID(String zstudentID, Timestamp timestamp);

    Zstudent_login findtatbyip(String zid);

    int updatetesttime(String zstudentID,String zscheduleID,int ztesttime);

    int updateznowtaskname(String zstudentID,String zscheduleID,String znowtaskname);

    int removetat(Zstudent_login zstudent_login);

    int updatetanbyipasch(Zstudent_login zstudent_login);

    int updatetatbyip2(Zstudent_login zstudent_login);

    public Zstudent_login findnowinbystuid(String zstudentID);

    int updateloginbyzid(String zid,String zstatus);

    Zstudent_login findloginbyss(String zstudentID,String zscheduleID);
}
