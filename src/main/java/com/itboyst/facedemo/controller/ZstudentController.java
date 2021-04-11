package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.FaceUserInfo;
import com.itboyst.facedemo.dto.Zschedule;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.service.ZstudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ZstudentController {
    public final static Logger logger = LoggerFactory.getLogger(ZstudentController.class);


    @Autowired
    ZstudentService zstudentService;

    @RequestMapping("/findallsname")
    @ResponseBody
    public List<String> findallsaname(HttpSession session){

        Zschedule zschedule =(Zschedule)session.getAttribute("zschedule");

        return zstudentService.findnamebyzscheduleID(zschedule.getZid());
    }

    @RequestMapping("/findallbystudentname")
    @ResponseBody
    public Zstudent findallbystudentname(String name){
        //System.out.println(name);
        //System.out.println(zstudentService.findallbystudentname(name));
        return zstudentService.findallbystudentname(name);
    }

    @RequestMapping("/findAllstudentbyg")
    @ResponseBody
    public List<Zstudent> findAllstudentbyg(String zgradeID){
        return zstudentService.findstudentbyzgradid(zgradeID);
    }


    @RequestMapping("/findstudentnamelike")
    @ResponseBody
    public List<String> findstudentnamelike(String name){
        return zstudentService.findstudentnamelike(name);
    }

    @RequestMapping("/findcountbystudentname")
    @ResponseBody
    public int findcountbystudentname(String zname){
        return zstudentService.findcountbystuname(zname);
    }




    @RequestMapping("/getnameandpath")
    @ResponseBody
    public FaceUserInfo getnameandpath(HttpSession session){
        FaceUserInfo faceUserInfo=(FaceUserInfo) session.getAttribute("faceUserInfo");

        Zstudent zstudent=zstudentService.findstudentbyfaceid(faceUserInfo.getFaceId());

        faceUserInfo.setName(zstudent.getZname());

        return faceUserInfo;
    }

}
