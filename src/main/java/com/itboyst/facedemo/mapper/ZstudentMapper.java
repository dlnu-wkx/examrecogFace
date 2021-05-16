package com.itboyst.facedemo.mapper;

import com.itboyst.facedemo.dto.Zstudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZstudentMapper {
    public int registerstu(Zstudent zstu);

    public Zstudent findadoptstudent(int zfaceinfoID);

    public List<String> findnamebyzscheduleID(String zscheduleID);

    Zstudent findStudentById(String zstudentID);

    String findStudentNameByfacilityID(String facilityID);

    Zstudent findstudentByZidentity(String zidentity);

    public int findcountbyname(String zname);

    public Zstudent findallbystudentname(String zname);

    public int updatestudent(Zstudent zstudent);

    public List<String> findstudentnamelike(String name);

    public List<String> findastudentbynamelike(String zname);

    public int findcountbystuname(String zname);

    public Zstudent findstudentbyfaceid(String zfaceinfoID);

    List<Zstudent> findAllstudentbygradeid(String zgradeID);

    List<Zstudent> findstudentbyzgradid(String zgradeID);

    List<Zstudent> findAllStudents();

}
