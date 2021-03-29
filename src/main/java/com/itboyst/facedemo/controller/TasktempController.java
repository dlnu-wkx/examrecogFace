package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TasktempController {

    @Autowired
    Zteacher_temporary_taskService zteacher_temporary_taskService;

    @Autowired
    Zassign_scheduleService zassign_scheduleService;

    @Autowired
    Zstudent_loginService zstudent_loginService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZscheuleService zscheuleService;

    @RequestMapping("/findalltemporarytask")
    @ResponseBody
    public List<Zteacher_temporary_task> findalltemporarytask(HttpSession session){

        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent");
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");

        Zstudent_login zstudent_login=(Zstudent_login)session.getAttribute("zstudent_login") ;

        Timestamp logintime=zstudent_login.getZrecongnizetime();

        String zid=zstudent.getZid();

        List<Zteacher_temporary_task> data=zteacher_temporary_taskService.findtaskname(zid,zstudent_cookie.getZscheduleID());
        List<Zteacher_temporary_task> data2=new ArrayList<>();

       /* System.out.println(logintime);
        System.out.println(data);*/

        for(int i=0;i<data.size();i++)
            if (logintime.compareTo(data.get(i).getZpublishtime())<0)
                data2.add(data.get(i));

        return data2;

    }

    @RequestMapping("/findisinassigan")
    @ResponseBody
    public int findisinassigan(String studentid,String taskid,HttpSession session){
        //System.out.println(studentid+taskid);
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zassign_scheduleService.findtaskisin(taskid,studentid,zteacher_cookie.getZscheduleID());

    }

    @RequestMapping("/findisintemp")
    @ResponseBody
    public int findisintemp(String studentid,String taskid){
       // System.out.println(studentid+taskid);

        return zteacher_temporary_taskService.findisintemp(taskid,studentid);

    }


    @RequestMapping("/findisintemp2")
    @ResponseBody
    public int findisintemp2(String studentid,String taskid){

        //System.out.println(studentid+taskid);
        if (zteacher_temporary_taskService.findisintemp(taskid,studentid)>0){
            Zteacher_temporary_task zteacher_temporary_task= zteacher_temporary_taskService.findisintemp2(taskid,studentid);

            Zstudent_login zstudent_login=zstudent_loginService.findnowinbystuid(studentid);

            //Timestamp timestamp =new Timestamp(System.currentTimeMillis());

            //System.out.println(zteacher_temporary_task.getZpublishtime());
           // System.out.println(zstudent_login);

            if (zteacher_temporary_task!=null && zteacher_temporary_task.getZpublishtime().compareTo(zstudent_login.getZrecongnizetime())>0)
                return 1;
        }

        return 0;
    }


    @RequestMapping("/inserttemptask")
    @ResponseBody
    public int inserttemptask(String studentid,String taskid,HttpSession session){

        //System.out.println(studentid);
        String zid = UUID.randomUUID().toString().replaceAll("-","");

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        Zteacher_cookie zteacher_cookie=(Zteacher_cookie) session.getAttribute("zteacher_cookie") ;

        Zteacher_temporary_task zteacher_temporary_task=new Zteacher_temporary_task();
        zteacher_temporary_task.setZid(zid);
        zteacher_temporary_task.setZcontentID(taskid);
        zteacher_temporary_task.setZpublishtime(timestamp);
        zteacher_temporary_task.setZstudentID(studentid);
        zteacher_temporary_task.setZscheduleID(zteacher_cookie.getZscheduleID());

        //System.out.println(zteacher_temporary_task);
        Zstudent zstudent=zstudentService.findStudentById(studentid);
        if (zstudent.getZidentity().contains("L")){
           String zscheduleid=zscheuleService.findidbycourename("临时课程",zteacher_cookie.getZtrainingroomid());
           zteacher_temporary_task.setZscheduleID(zscheduleid);
        }


        return zteacher_temporary_taskService.inserttemptask(zteacher_temporary_task);
    }


    @RequestMapping("/deletemes")
    @ResponseBody
    public int deletemes(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        return zteacher_temporary_taskService.temporarybydid(zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID());
    }




}
