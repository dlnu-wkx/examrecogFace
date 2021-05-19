package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface Zstudent_loginMapper {
    int deletefront(String zstudentID);

    int insertnowmessage(Zstudent_login zstlog);

    List<Zstudent_login> findfront(String zstudentID);

    List<Zstudent_login> findAllStudentLogin();

    int updateloginstate(String zstatus,String ztrainingroomID);

    int deleteStudentLoginByzidentity(String zidentity);

    List<String> findScheduleBytimeandzstudentID(String zstudentID, Timestamp timestamp);

    Zstudent_login findtatbyip(String zid);

    int updatetesttime(String zstudentID,String zscheduleID,int ztesttime);

    int updateznowtaskname(String zstudentID,String zscheduleID,String znowtaskname);

    //根据路径查找相同的数据删除
    int deletebyoriginalPictureUrl(String originalPictureUrl);

    int removetat(Zstudent_login zstudent_login);

    int updatetanbyipasch(Zstudent_login zstudent_login);

    int updatetatbyip2(Zstudent_login zstudent_login);

    public Zstudent_login findnowinbystuid(String zstudentID);

    int updateloginbyzid(String zid);

    Zstudent_login findloginbyss(String zstudentID,String zscheduleID);
}
