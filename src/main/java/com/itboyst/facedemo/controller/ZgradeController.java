package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zgrade;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.ZgradeService;
import com.itboyst.facedemo.service.ZstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ZgradeController {
    @Autowired
    ZgradeService zgradeService;

    @Autowired
    ZstudentService zstudentService;

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

    @RequestMapping("/findAllstudentbygradeid")
    @ResponseBody
    public List<Zstudent> findAllstudentbygradeid(String zid){

        List<Zstudent> zstudentList =zstudentService.findAllstudentbygradeid(zid);

        return zstudentList;

    }


    @RequestMapping("/findallgradebytrainroom")
    @ResponseBody
    public List<Zgrade> findallgradebytrainroom(HttpSession session){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        List<Zgrade> list = null;
        if(ztrainingroomID !=null){
            list = zgradeService.findallgradebytrainroom(ztrainingroomID);
        }

        return list;

    }

    @RequestMapping("/findgradestudentnumbbyID")
    @ResponseBody
    public List<Zstudent>  findallgradebyID(String zid){
       List<Zstudent>  list  = zstudentService.findAllstudentbygradeid(zid);
        return list;
    }


}
