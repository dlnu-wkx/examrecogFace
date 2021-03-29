package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zselect_message;
import com.itboyst.facedemo.service.Zselect_messageService;
import com.itboyst.facedemo.service.ZteacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class Zslect_messageController {

    @Autowired
    Zselect_messageService zselect_messageService;

    @Autowired
    ZteacherService zteacherService;

    @RequestMapping("/selectattandancemes")
    @ResponseBody
    public List<Zselect_message> selectattandancemes(String zname, String starttime, String endtime,String zcheck){
        //将starttime与endtime的String类型转化为Timestamp
        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findattandance(zname, time1, time2,zcheck);
    }

    @RequestMapping("/selectattandancemes2")
    @ResponseBody
    public List<Zselect_message> selectattandancemes2(String zname, String starttime, String endtime,String zcheck){
        //将starttime与endtime的String类型转化为Timestamp
        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findattandance2(zname, time1, time2,zcheck);
    }


    @RequestMapping("/selectinandout")
    @ResponseBody
    public List<Zselect_message> selectinandout(String zname, String starttime, String endtime){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findinandout(zname,time1,time2);
    }

    @RequestMapping("/selectheadsup")
    @ResponseBody
    public List<Zselect_message> selectheadsup(String zname, String starttime, String endtime,String ztype){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findupheads(zname,time1,time2,ztype);
    }


    @RequestMapping("/selectleave")
    @ResponseBody
    public List<Zselect_message> selectleave(String zname, String starttime, String endtime){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findallleave(zname,time1,time2);
    }


    @RequestMapping("/selectteachernamebyid")
    @ResponseBody
    public String selectteachernamebyid(String zid){
        return zteacherService.selectteachernamebyid(zid);
    }


    @RequestMapping("/selectscore")
    @ResponseBody
    public List<Zselect_message> selectscore(String zname, String starttime, String endtime){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findscorebyname(zname,time1,time2);
    }

    @RequestMapping("/findztaskinput")
    @ResponseBody
    public List<Zselect_message> findztaskinput(String zname, String starttime, String endtime){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findztaskinput(zname,time1,time2);
    }

    @RequestMapping("/findztasktime")
    @ResponseBody
    public List<Zselect_message> findztasktime(String zname, String starttime, String endtime){

        Timestamp time1 = Timestamp.valueOf(starttime);
        Timestamp time2 = Timestamp.valueOf(endtime);

        return zselect_messageService.findtasktime(zname,time1,time2);
    }



}
