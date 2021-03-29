package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.base.Powerutil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.Zstudent_eventService;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;

@Controller
public class ZeventController {
    public final static Logger logger = LoggerFactory.getLogger(ZeventController.class);


    @Autowired
    Zstudent_eventService zstudent_eventService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @RequestMapping("/insertevent")
    @ResponseBody
    public int insertevent(HttpSession session, String ztype, String zcontent, HttpServletRequest request){

        //字符替换
        zcontent =ReplaceSQLChar(zcontent);

        //System.out.println(ztype+zcontent);
        Zstudent_event zstudent_event =new Zstudent_event();

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zstudent_event.setZid(uuid);

        //将请假的id存入session中去
        session.setAttribute("zstudent_eventid",uuid);


        Zstudent zstudent=(Zstudent)session.getAttribute("zstudent") ;
        String zstudentid=zstudent.getZid();

        zstudent_event.setZstudentID(zstudentid);

        zstudent_event.setZstatus("取消");
        zstudent_event.setZtype(ztype);
        if (zcontent!=null && zcontent!=""){
            zstudent_event.setZcontent(zcontent);
        }

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zstudent_event.setZapplicationtime(timestamp);

        //System.out.println(zstudent_event);
        //插入之前将之前的事件状态更改
        zstudent_eventService.updateeventstatus(zstudent_event);

        zstudent_event.setZstatus("申请中");

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        zstudent_event.setZscheduleID(zstudent_cookie.getZscheduleID());
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility") ;

        zstudent_event.setZrecognizeIP(ztraining_facility.getZstudentPCIP());

        //更改设备表二端口的数据
        ztraining_facilityService.updattwoportbyip(ztraining_facility.getZstudentPCIP(),1);
        ztraining_facilityService.updateoneportbyip(ztraining_facility.getZstudentPCIP(),0);

        //另开一个线程，更改继电器1端口的数据
        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    if (ztraining_facility.getZpowerIP()!=null)
                        if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"21");
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"12");
                }catch(Exception e) {

                }
            }});
        t.start();

        //插入新的学生事件信息
        return zstudent_eventService.insertevent(zstudent_event);
    }

    /**
     * 取消举手
     * @param session
     * @return
     */
    @RequestMapping("/removeup")
    @ResponseBody
    public int removeup(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("举手");
        zstudent_event.setZstatus("取消");

        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility") ;
        //更改设备表二端口的数据
        ztraining_facilityService.updattwoportbyip(ztraining_facility.getZstudentPCIP(),0);

        ztraining_facilityService.updateoneportbyip(ztraining_facility.getZstudentPCIP(),1);

        //另开一个线程，更改继电器1端口的数据
        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    if (ztraining_facility.getZpowerIP()!=null)
                        if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"22");
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"11");
                }catch(Exception e) {

                }
            }});
        t.start();


        return zstudent_eventService.updateeventstatus(zstudent_event);
    }

    @RequestMapping("/deleteleave")
    @ResponseBody
    public int deleteleave(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("请假");
        zstudent_event.setZstatus("取消");

        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility") ;
        //更改设备表二端口的数据
        ztraining_facilityService.updattwoportbyip(ztraining_facility.getZstudentPCIP(),0);
        ztraining_facilityService.updateoneportbyip(ztraining_facility.getZstudentPCIP(),1);

        //另开一个线程，更改继电器1端口的数据
        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    if (ztraining_facility.getZpowerIP()!=null)
                        if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"22");
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"11");
                }catch(Exception e) {

                }
            }});
        t.start();

        return zstudent_eventService.updateeventstatus(zstudent_event);

    }

    @RequestMapping("/outsystem")
    @ResponseBody
    public int outsystem(String ztype,HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZid(uuid);
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype(ztype);
        zstudent_event.setZstatus("申请中");
        zstudent_event.setZapplicationtime(timestamp);

        return zstudent_eventService.insertevent(zstudent_event);
    }

    @RequestMapping("/removeout")
    @ResponseBody
    public int removeout(HttpSession session){
        Zstudent zstudent=(Zstudent) session.getAttribute("zstudent");
        Zstudent_event zstudent_event=new Zstudent_event();
        zstudent_event.setZstudentID(zstudent.getZid());
        zstudent_event.setZtype("退出系统");
        zstudent_event.setZstatus("取消");

        return zstudent_eventService.updateeventstatus(zstudent_event);
    }


    @RequestMapping("/findisevent")
    @ResponseBody
    public List<Zstudent_event> findisevent(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        //System.out.println(zstudent_cookie.getZstudentID()+"    "+zstudent_cookie.getZscheduleID());
        //System.out.println(zstudent_eventService.findisevent3(zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID()));
        return zstudent_eventService.findisevent3(zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID());
    }


    @RequestMapping("/updatealleventbystu")
    @ResponseBody
    public int updatealleventbystu(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        //System.out.println(zstudent_eventService.updatealleventbystu("取消",zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID()));
        return zstudent_eventService.updatealleventbystu2("取消",zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID());
    }


    @RequestMapping("/updatealleventbyrid")
    @ResponseBody
    public int updatealleventbyrid(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility") ;

        Zteacher_cookie zteacher_cookie=(Zteacher_cookie) session.getAttribute("zteacher_cookie") ;

        List<Ztraining_facility>  data=ztraining_facilityService.findfacilitybyrid(ztraining_facility.getZtrainingroomID());

        int j=0,k=0;
        for (int i=0;i<data.size();i++){
            j=zstudent_eventService.updatealleventbystu("取消",data.get(i).getZstudentPCIP(),zteacher_cookie.getZscheduleID());

            if (j>0)
                k++;
        }

        return k;

    }


}
