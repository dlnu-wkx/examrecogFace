package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zstudent_schedule;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.Zstudent_loginService;
import com.itboyst.facedemo.service.Zstudent_scheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ZpowerController {

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    Zstudent_loginService zstudent_loginService;


    @RequestMapping("/overclass")
    @ResponseBody
    public int overclass(String ztrainroomid, HttpSession session){
        int i=  zstudent_loginService.updateloginstate("强退",ztrainroomid);

        Zstudent_schedule zstudent_schedule =new Zstudent_schedule();
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        zstudent_schedule.setZscheduleID(zteacher_cookie.getZscheduleID());
        zstudent_schedule.setZstate("已结束");

        int j=zstudent_scheduleService.updatestatebyscheduleid(zstudent_schedule);
        if(i>0&&j>0) return 1;
        return 0;

    }

}
