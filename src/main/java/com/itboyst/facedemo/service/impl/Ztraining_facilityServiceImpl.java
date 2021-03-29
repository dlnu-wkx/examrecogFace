package com.itboyst.facedemo.service.impl;

import com.itboyst.facedemo.dto.Ztraining_facility;
import com.itboyst.facedemo.mapper.Ztraining_facilityMapper;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ztraining_facilityServiceImpl implements Ztraining_facilityService {

    @Autowired
    Ztraining_facilityMapper ztran_facmap;

    @Override
    public Ztraining_facility findbyip(String zstudentPCIP){

      return   ztran_facmap.findtrain_fac(zstudentPCIP);
    }

    @Override
    public List<Ztraining_facility> findfacilitybyrid(String ztrainingroomID){
        return ztran_facmap.findfactibyrid(ztrainingroomID);
    }

    @Override
    public int updateallfacility(String ztrainingroomID,String zpowerstatus){
        return ztran_facmap.updateallfacility(ztrainingroomID, zpowerstatus);
    }

    @Override
    public String findOpenPower(String zid) {
        return ztran_facmap.findOpenPower(zid);
    }

    @Override
    public List<Ztraining_facility> findfactibyztrainingroomID(String ztrainingroomID) {
        return ztran_facmap.findfactibyztrainingroomID(ztrainingroomID);
    }

    @Override
    public int updateallfacilitybyzid(String zid,String zpowerstatus){
        return ztran_facmap.updateallfacilitybyzid(zid, zpowerstatus);
    }

    @Override
    public List<Ztraining_facility> findcontrollerbyroomid(String ztrainingroomID){
        return ztran_facmap.findcontrollerbyroomid(ztrainingroomID);
    }


    public int findfactsixportbyzid(String IP){return ztran_facmap.findfactsixportbyzid(IP);}

    @Override
    public Ztraining_facility findcontrollerbyid(String zid){return ztran_facmap.findcontrollerbyid(zid);}

    @Override
    public String findstunamebyfacid(String zid){return ztran_facmap.findstunamebyfacid(zid);}

    public int findfactsixportbyzid2(String zid){return ztran_facmap.findfactsixportbyzid2(zid);}

    public int updatezprogressbyip(String ip,String zprogress){return ztran_facmap.updatezprogressbyip(ip, zprogress);}

    @Override
    public String findzprogressByip(String zid) {
        return ztran_facmap.findzprogressByip(zid);
    }


    public int updatefatestbyroomid(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyroomid(ztraining_facility);}

    public int updatefatestbyid(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyid(ztraining_facility);}


    public int updatesixportbyid(Ztraining_facility ztraining_facility){return  ztran_facmap.updatesixportbyid(ztraining_facility);}

    public Ztraining_facility findfacilitybyid(String zid){return ztran_facmap.findfacilitybyid(zid);}

    public int updatefatestbyid2(Ztraining_facility ztraining_facility){return ztran_facmap.updatefatestbyid2(ztraining_facility);}

    public int updatesixportbyroomid(String ztrainingroomID,int zpowerStatus6){return ztran_facmap.updatesixportbyroomid(ztrainingroomID, zpowerStatus6);}

    public int updateoneportbyip(String zstudentPCIP,int zpowerStatus1){return ztran_facmap.updateoneportbyip(zstudentPCIP, zpowerStatus1);}

    public int updattwoportbyip(String zstudentPCIP,int zpowerStatus2){return ztran_facmap.updattwoportbyip(zstudentPCIP, zpowerStatus2);}

    public int updatesevenportbyip(String zstudentPCIP,int zpowerStatus7){return ztran_facmap.updatesevenportbyip(zstudentPCIP, zpowerStatus7);}

    public int updateportbyid(Ztraining_facility ztraining_facility){return ztran_facmap.updateportbyid(ztraining_facility);}

    public int overclass(String ztrainingroomID,String zprogress){return ztran_facmap.overclass(ztrainingroomID, zprogress);}

    public String  findzprogressbyid(String zid){return ztran_facmap.findzprogressbyid(zid);}

    public int updatefiveportbyzid(String zid,int zpowerStatus5){return ztran_facmap.updatefiveportbyzid(zid,zpowerStatus5);}

    public int updateoatportbyrid(int zpowerStatus2,int zpowerStatus1,String ztrainingroomID){return ztran_facmap.updateoatportbyrid(zpowerStatus2, zpowerStatus1, ztrainingroomID);}

    public int updateoneatwobyrid(Ztraining_facility ztraining_facility){return ztran_facmap.updateoneatwobyrid(ztraining_facility);}

    public List<Ztraining_facility> findfactibyrid2(String ztrainingroomID ){return ztran_facmap.findfactibyrid2(ztrainingroomID);}

}
