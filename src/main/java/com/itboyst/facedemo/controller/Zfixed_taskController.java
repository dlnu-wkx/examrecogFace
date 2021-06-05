package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;

@Controller
public class Zfixed_taskController {
    public final static Logger logger = LoggerFactory.getLogger(Zfixed_taskController.class);

    @Autowired
    Zfixed_taskService zfixed_taskService;

    @Autowired
    Zassign_scheduleService zassign_scheduleService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    Ztask_content_logService ztask_content_logService;

    @Autowired
    Zname_facilityService zname_facilityService;

    @Autowired
    Ztraining_taskService ztraining_taskService;

    @Autowired
    Ztraining_task_assessService ztraining_task_assessService;

    @Autowired
    Ztask_inputService ztask_inputService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZscheuleService zscheuleService;


    @RequestMapping("/sendclassmessage")
    @ResponseBody
    public void sendclassmessage(HttpSession session,String zname,String taskid,int kindid,String assid,int pages){
        Zclassmessession zclassmessession=new Zclassmessession();
        zclassmessession.setAssid(assid);
        zclassmessession.setTaskid(taskid);
        zclassmessession.setKindid(kindid);
        zclassmessession.setZname(zname);
        zclassmessession.setPages(pages);
       // System.out.println(pages);

        session.setAttribute("zclassmessession",zclassmessession);

    }

    @RequestMapping("/getclassmesssion")
    @ResponseBody
    public Zclassmessession getclassmesssion(HttpSession session){
       // System.out.println(session.getAttribute("zclassmessession"));
        return   (Zclassmessession) session.getAttribute("zclassmessession");

    }




    @RequestMapping("/findallfixedtasks")
    @ResponseBody
    public List<Zfixed_task> findallfixedtasks(HttpSession session, HttpServletRequest request){

        String ip4= Iputil.getClientIpAddress(request);
        int a=ztraining_facilityService.updatezprogressbyip(ip4,"实训");

        session.setAttribute("zprogress","实训");

        Zstudent_cookie zstudent_cookie =(Zstudent_cookie)session.getAttribute("zstudent_cookie");


        return zfixed_taskService.findallfixedtask(zstudent_cookie.getZstudent_scheduleid());

    }


    @RequestMapping("/updatetasktime")
    @ResponseBody
    public int updatetasktime(String taskid,HttpSession session,String zassign_scheduleid,String task_contentid){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();

        String zscheduleID=zstudent_cookie.getZscheduleID();

        String studentid=zstudent_cookie.getZstudentID();

        int w=0,v=0;
        //更新上课学生表state状态为在上课
        int j= zstudent_scheduleService.updatestate("在上课",zscheduleID,studentid);

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        //System.out.println(timestamp);

        int i= zassign_scheduleService.updatechecktime(timestamp,zstudentscheduleID,taskid);

        int k= ztask_content_logService.findnumcontentlog(zassign_scheduleid,task_contentid);

        if(k==0){
            Ztask_content_log ztask_content_log =new Ztask_content_log();
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            ztask_content_log.setZid(zid);
            ztask_content_log.setZassignscheduleID(zassign_scheduleid);
            ztask_content_log.setZid(zid);
            ztask_content_log.setZstarttime(timestamp);
            ztask_content_log.setZtrainingtaskcontentID(task_contentid);
            w=ztask_content_logService.insertcontentlog(ztask_content_log);
        }else {
            v=ztask_content_logService.updatecontentlogstart(zassign_scheduleid,task_contentid,timestamp);
        }

        if(i==1&&(w==1||v==1)){
            return 1;
        }


       return 0;
    }



    @RequestMapping("/findallnandf")
    @ResponseBody
    public List<Zstudent_facility> findallnandf(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        String ztrainingroomid =zteacher_cookie.getZtrainingroomid();

        List<Ztraining_facility> data =ztraining_facilityService.findfacilitybyrid(ztrainingroomid);

        List<Zstudent_facility> data2=new ArrayList<>();

        for(int i=0;i<data.size();i++){
            Zstudent_facility zstudent_facility =new Zstudent_facility();

            zstudent_facility=zname_facilityService.findnameandfaclity(data.get(i).getZid());

            if (zstudent_facility != null)
                data2.add(zstudent_facility);
        }
       // System.out.println(data2);
       return data2;
    }


    @RequestMapping("/findalltask")
    @ResponseBody
    public List<Ztraining_task> findalltask(){

        return ztraining_taskService.findalltask();
    }


    @RequestMapping("/findtaskbyid")
    @ResponseBody
    public Ztraining_task findtaskbyid(String zid){

        return ztraining_taskService.findtaskbyid(zid);
    }


    @RequestMapping("/findtasklikename")
    @ResponseBody
    public List<Ztraining_task> findtasklikename(String zname){
        return ztraining_taskService.findtasklike(zname);
    }

    @RequestMapping("/insertfixedtask")
    @ResponseBody
    public int  insertfixedtask(String studentid,String taskid,HttpSession session){
        String zid = UUID.randomUUID().toString().replaceAll("-","");

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        String zscheduleid=zteacher_cookie.getZscheduleID();

       //System.out.println(zid+zscheduleid+studentid+taskid+timestamp);


        Zstudent zstudent=zstudentService.findStudentById(studentid);
        if (zstudent.getZidentity().contains("L")){
            zscheduleid=zscheuleService.findidbycourename("临时课程",zteacher_cookie.getZtrainingroomid());
        }

        return zassign_scheduleService.insertfixedtask(zid,zscheduleid,studentid,taskid,timestamp);
    }


    @RequestMapping("/updatetaskendtime")
    @ResponseBody
    public int updatetaskendtime(String zassign_scheduleid,String task_contentid){

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        return ztask_content_logService.updatecontentlogend(zassign_scheduleid,task_contentid,timestamp);
    }

    @RequestMapping("/updatealltaskend")
    @ResponseBody
    public int updatealltaskend(String ztrainingtaskID,HttpSession session,String task_contentid,String zassign_scheduleid){
        Zassign_schedule zassign_schedule =new Zassign_schedule();
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        zassign_schedule.setZcompletetime(timestamp);
        zassign_schedule.setZstudentscheduleID(zstudent_cookie.getZstudent_scheduleid());
        zassign_schedule.setZtrainingtaskID(ztrainingtaskID);

        int i=ztask_content_logService.updatecontentlogend(zassign_scheduleid,task_contentid,timestamp);

        return zassign_scheduleService.updatecompletetime(zassign_schedule);

    }


    @RequestMapping("/findtaskassessbytrainingid")
    @ResponseBody
    public List<Ztraining_task_assess> findtaskassessbytrainingid(String ztraining_taskID){

        return ztraining_task_assessService.findallbyztrainingtaskID(ztraining_taskID);
    }


    @RequestMapping("/findsatcheckbytid")
    @ResponseBody
    public Ztask_input findsatcheckbytid(String ztrainingtaskassessID,String ztraining_taskID,HttpSession session){
        //System.out.println(ztrainingtaskassessID+ztraining_taskID);
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        String zstudent_scheduleid=zstudent_cookie.getZstudent_scheduleid();

       /* System.out.println("ztrainingtaskassessID:"+ztrainingtaskassessID+"      ztraining_taskID:"+ztraining_taskID+"    zstudent_scheduleid:"+zstudent_scheduleid);
        System.out.println(ztask_inputService.findsatcheckbyid(ztrainingtaskassessID,ztraining_taskID,zstudent_scheduleid));*/

        return ztask_inputService.findsatcheckbyid(ztrainingtaskassessID,ztraining_taskID,zstudent_scheduleid);

    }


    @RequestMapping("/updatetcheckbyid")
    @ResponseBody
    public void updatetcheckbyid(String zid,String zteachercheck){

        ztask_inputService.updatetasktcheckbyid(zid,zteachercheck);

    }



    @RequestMapping("/inserttaskoneinput")
    @ResponseBody
    public int inserttaskoneinput(String zassign_scheduleid,String  zselfcheck,String  ztrainingtaskassessID){
       // System.out.println(zselfcheck);
        //System.out.println("ztrainingtaskassessID:  "+ztrainingtaskassessID+"        zassign_scheduleid: "+zassign_scheduleid);
        int i=0;
        Ztask_input ztask_input=new Ztask_input();
        ztask_input.setZselfcheck(zselfcheck);
        ztask_input.setZtrainingtaskassessID(ztrainingtaskassessID);
        ztask_input.setZassignscheduleID(zassign_scheduleid);

        int w=ztask_inputService.findistaskinput(ztask_input);
        //System.out.println(w);
        if(w==0){
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            ztask_input.setZid(zid);
            i=ztask_inputService.isnerttaskinput(ztask_input);
        }
        else {
            i=ztask_inputService.updatetaskselfcheck(ztask_input);
        }
        return i;
    }




    @RequestMapping("/inserttaskinput")
    @ResponseBody
    public int inserttaskinput(@RequestParam(value = "zassign_scheduleid")String zassign_scheduleid,@RequestParam(value = "zselfcheck[]")String [] zselfcheck,@RequestParam(value = "ztrainingtaskassessID[]")String [] ztrainingtaskassessID){
        int j,k=0;
        int w=0;
        for (int i=0;i<zselfcheck.length;i++){
            //字符替换
            zselfcheck[i] =ReplaceSQLChar(zselfcheck[i]);

            Ztask_input ztask_input=new Ztask_input();
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            ztask_input.setZid(zid);
            ztask_input.setZselfcheck(zselfcheck[i]);
            ztask_input.setZtrainingtaskassessID(ztrainingtaskassessID[i]);
            ztask_input.setZassignscheduleID(zassign_scheduleid);

            w=ztask_inputService.findistaskinput(ztask_input);
            //System.out.println(w);
            if(w==0){
                j=ztask_inputService.isnerttaskinput(ztask_input);
            }else {
                 j=ztask_inputService.updatetaskselfcheck(ztask_input);
            }
            if(j>0)k++;
        }

        return k;
    }






}
