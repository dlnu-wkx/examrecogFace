package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.ZgradeService;
import com.itboyst.facedemo.service.ZgroupStudentService;
import com.itboyst.facedemo.service.ZmajorService;
import com.itboyst.facedemo.service.ZstudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
public class ZgradeController {
    @Autowired
    ZgradeService zgradeService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZmajorService zmajorService;

    @Autowired
    ZgroupStudentService zgroupStudentService;

    @RequestMapping("/findallgrade")
    @ResponseBody
    public List<Zgrade> findallgrade(){

        return zgradeService.findallgrade();
    }


    @RequestMapping("/findgradeandnamelike")
    @ResponseBody
    public List<String> findgradeandnamelike(String zname){
        List<String> listone=zgradeService.findgradebynamelike(zname);

        List<String> listtwo=zstudentService.findastudentbynamelike(zname);

        listone.addAll(listtwo);

        //System.out.println(listone);

        return listone;

    }

    @RequestMapping("/findAllgradebyzmajorid")
    @ResponseBody
    public List<Zgrade> findAllgradebyzmajorid(String zid){

        List<Zgrade> listone =zgradeService.findgradebymajorid(zid);
        //System.out.println(listone);

        return listone;

    }

    @RequestMapping("/findAllgrade")
    @ResponseBody
    public List<Zgrade> findAllgrade(){
        List<Zgrade> listone =zgradeService.findgrade();

        return listone;

    }



    @RequestMapping("/findAllstudentbygradeid")
    @ResponseBody
    public List<Zstudent> findAllstudentbygradeid(String zid){

        List<Zstudent> zstudentList =zstudentService.findAllstudentbygradeid(zid);

        return zstudentList;

    }


    @RequestMapping("/findallgradebytrainroom")
    @ResponseBody
    public List<Zgrade> findallgradebytrainroom(HttpSession session){
       /* Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();*/
        List<Zgrade> list = null;
        /*if(ztrainingroomID !=null){
            list = zgradeService.findallgradebytrainroom(ztrainingroomID);
        }*/
        list = zgradeService.findgrade();
        return list;

    }

    @RequestMapping("/findgradestudentnumbbyID")
    @ResponseBody
    public List<Zstudent>  findallgradebyID(String zid){
       List<Zstudent>  list  = zstudentService.findAllstudentbygradeid(zid);
        return list;
    }

    @RequestMapping("/addGroupAndzid")
    @ResponseBody
    public int  addGroupAndzid(String zname,@RequestParam(value = "zid[]")String[] zid,String gradeID){
        String gradeid = "";

        if(gradeID.equals("")){
            Zgrade zgrade = new Zgrade();
            gradeid = UUID.randomUUID().toString().replaceAll("-", "");
            String zmajorid = zmajorService.findzidbyzname("群组");
            zgrade.setZmajorID(zmajorid);
            zgrade.setZid(gradeid);
            zgrade.setZname(zname);
            int  a  = zgradeService.addgroup(zgrade);
        }
        if(!gradeID.equals("")){
            gradeid = gradeID;
        }
        int c = 0;
        if(zid != null){
            for(int i = 0;i<zid.length;i++){
             String   uuzid = UUID.randomUUID().toString().replaceAll("-", "");
                c  = zgroupStudentService.add(uuzid,gradeid,zid[i]);
            }
        }


        return c ;
    }


    @RequestMapping("/deletgroup")
    @ResponseBody
    public int  deletgroup(String zid){
       int b =  zgradeService.deletgroup(zid);
        int a = zgroupStudentService.deleteZgroupStudentByZid(zid);
        return b;
    }


    @RequestMapping("/findAllStudents")
    @ResponseBody
    public List<Zstudent>  findAllStudents(){

        List<Zstudent> list = zstudentService.findAllStudents();
        return list;
    }

    @RequestMapping("/deleteStudentbyzid")
    @ResponseBody
    public int  deleteStudentbyzid(@RequestParam(value = "zid[]")String[] zid) {
        int c = 0;
        if(zid != null){
            for(int i = 0;i<zid.length;i++){
                c  = zgroupStudentService.deleteZgroupStudentByStudentID(zid[i]);
            }
        }
        return c;
    }
}
