package com.itboyst.facedemo.service.impl;



import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.mapper.ZstudentMapper;
import com.itboyst.facedemo.service.ZstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZstudentServiceImpl implements ZstudentService {



    @Autowired
    private ZstudentMapper zstu;

    @Override
    public int registerstud(Zstudent zstud){
        int i =0;
        i =zstu.registerstu(zstud);   
        return i;
    }

    @Override
    public Zstudent findadoptstudent(int zfaceinfoID){
       Zstudent zs =new Zstudent();

       zs=zstu.findadoptstudent(zfaceinfoID);


       return zs;
    }
    @Override
    public List<String> findnamebyzscheduleID(String zscheduleID){
        return zstu.findnamebyzscheduleID(zscheduleID);
    }



    @Override
    public Zstudent findStudentById(String zstudentID) {
        return zstu.findStudentById(zstudentID);
    }


    @Override
    public String findStudentNameByfacilityID(String facilityID) {
        return zstu.findStudentNameByfacilityID(facilityID);
    }

    @Override
    public Zstudent findstudentByZidentity(String zidentity) {
        return zstu.findstudentByZidentity(zidentity);
    }

    @Override
    public int findcountbyname(String zname){return zstu.findcountbyname(zname);}

    @Override
    public Zstudent findallbystudentname(String zname){return  zstu.findallbystudentname(zname);}

    @Override
    public int updatestudent(Zstudent zstudent){return zstu.updatestudent(zstudent);}

    @Override
    public List<String> findstudentnamelike(String name){return zstu.findstudentnamelike(name);}

    @Override
    public List<String> findastudentbynamelike(String zname){return zstu.findastudentbynamelike(zname);}

    @Override
    public int findcountbystuname(String zname){return zstu.findcountbystuname(zname);}

    @Override
    public List<Zstudent> findAllstudentbygradeid(String zgradeID) {
        return zstu.findAllstudentbygradeid(zgradeID);
    }

    @Override
    public Zstudent findstudentbyfaceid(String zfaceinfoID){return zstu.findstudentbyfaceid(zfaceinfoID);}

    @Override
    public List<Zstudent> findstudentbyzgradid(String zgradeID){return zstu.findstudentbyzgradid(zgradeID);};
}
