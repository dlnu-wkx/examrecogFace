package com.itboyst.facedemo.service.impl;


import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.mapper.Zstudent_loginMapper;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class Zstudent_loginServiceImpl implements Zstudent_loginService {

    @Autowired
    private Zstudent_loginMapper zstulogma;

    @Override
    public List<Zstudent_login> findAllStudentLogin() {
        return zstulogma.findAllStudentLogin();
    }

    @Override
    public int deleteStudentLoginByzidentity(String zidentity) {
        return zstulogma.deleteStudentLoginByzidentity(zidentity);
    }

    @Override
    public  synchronized int updateloginmessage(Zstudent_login zstl){
       /* List<Zstudent_login> zsl=zstulogma.findfront(zstl.getZstudentID());

        if(zsl.size()!=0){

            int i=zstulogma.deletefront(zstl.getZstudentID());
        }*/
        int i =zstulogma.deletebyoriginalPictureUrl(zstl.getOriginalPictureUrl());
        int j=0;
        j= zstulogma.insertnowmessage(zstl);

        return j;
    }

    @Override
    public int updateloginstate(String status,String ztrainingroomID){
        return zstulogma.updateloginstate(status,ztrainingroomID);
    }


    public int insertnowmessage(Zstudent_login zstudent_login){return zstulogma.insertnowmessage(zstudent_login);}

    @Override
    public List<String> findScheduleBytimeandzstudentID(String zstudentID, Timestamp timestamp) {
        return zstulogma.findScheduleBytimeandzstudentID(zstudentID,timestamp);
    }

   public Zstudent_login findtatbyip(String zid){
        return zstulogma.findtatbyip(zid);
    }


        public int updatetesttime(String zstudentID,String zscheduleID,int ztesttime){
        return zstulogma.updatetesttime(zstudentID,zscheduleID, ztesttime);
    }

        public int updateznowtaskname(String zstudentID,String zscheduleID,String znowtaskname){
        return zstulogma.updateznowtaskname(zstudentID, zscheduleID,znowtaskname);
    }

    public int removetat(Zstudent_login zstudent_login){
        return zstulogma.removetat(zstudent_login);
   }


    public int updatetanbyipasch(Zstudent_login zstudent_login){return zstulogma.updatetanbyipasch(zstudent_login);}

    public int updatetatbyip2(Zstudent_login zstudent_login){return zstulogma.updatetatbyip2(zstudent_login);};

    public Zstudent_login findnowinbystuid(String zstudentID){return zstulogma.findnowinbystuid(zstudentID);}

    public int updateloginbyzid(String zid){return zstulogma.updateloginbyzid(zid);}

    public Zstudent_login findloginbyss(String zstudentID,String zscheduleID){return zstulogma.findloginbyss(zstudentID, zscheduleID);};
}
