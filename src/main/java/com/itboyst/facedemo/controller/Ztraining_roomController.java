package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.base.Powerutil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.TimeStatusStudentService;
import com.itboyst.facedemo.service.Ztraining_facilityService;
import com.itboyst.facedemo.service.Ztraining_roomService;
import com.itboyst.facedemo.service.Ztraining_taskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class Ztraining_roomController {
    public final static Logger logger = LoggerFactory.getLogger(Ztraining_roomController.class);

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    TimeStatusStudentService timeStatusStudentService;

    @Autowired
    Zstudent_eventService zstudent_eventService;



    @Autowired
    Zstudent_loginService zstudent_loginService;

    @RequestMapping("/findtatbyip")
    @ResponseBody
    public Zstudent_login findtatbyip(String zid){
        return zstudent_loginService.findtatbyip(zid);
    }

    @Autowired
    Ztraining_taskService ztraining_taskService;

    @Autowired
    QbankService qbankService;


    @RequestMapping("/findtnumberbyfaid")
    @ResponseBody
    public int findtnumberbyfaid(String zid){
        Ztraining_facility ztraining_facility=ztraining_facilityService.findfacilitybyid(zid);

        return qbankService.findnumberbytype(ztraining_facility.getZsafetestingType());

    }

    /*
   勾选开启安全测试
  */
    @RequestMapping("/updateftestbych")
    @ResponseBody
    public int updateftestbych(HttpSession session,@RequestParam(value = "zpassingscore")int zpassingscore,@RequestParam(value = "zid[]")String [] zid,@RequestParam(value = "zsafetestingNum")int zsafetestingNum,@RequestParam(value = "zsafetestingType")String zsafetestingType){
        int j=0;
        for (int i=0;i<zid.length;i++){
                Ztraining_facility ztraining_facility=new Ztraining_facility();
                ztraining_facility.setZid(zid[i]);
                ztraining_facility.setZpassingscore(zpassingscore);
                ztraining_facility.setZsafetestingNum(zsafetestingNum);
                ztraining_facility.setZsafetestingType(zsafetestingType);
                j=ztraining_facilityService.updatefatestbyid(ztraining_facility);
               // System.out.println(ztraining_facility);
            }
        return j;
    }





    @RequestMapping("/updatefatestbyroomid")
    @ResponseBody
    public int updatefatestbyroomid(Ztraining_facility ztraining_facility){
        //System.out.println(ztraining_facility);
        return ztraining_facilityService.updatefatestbyroomid(ztraining_facility);
    }


    @RequestMapping("/findstunamebyfacid")
    @ResponseBody
    public String findstunamebyfacid(String zid){
        //System.out.println(zid);
        return ztraining_facilityService.findstunamebyfacid(zid);
    }



    @RequestMapping("/findalltrainroom")
    @ResponseBody
    public List<ztraining_room> findalltrainroom(){
        return ztraining_roomService.findallztrainroom();
    }

    @RequestMapping("/findalltraingtask")
    @ResponseBody
    public List<Ztraining_task> findalltraingtask(){
        return ztraining_taskService.findalltask();
    }


    @RequestMapping("/findalltraingtaskbyzcourseID")
    @ResponseBody
    public List<Ztraining_task> findalltraingtaskbyzcourseID(String zid){
        return ztraining_taskService.findalltaskbyzcourseID(zid);
    }

    @RequestMapping("/findfacilitybyrid")
    @ResponseBody
    public List<Ztraining_facility> findfacilitybyrid(String id,int isonline){
        if(isonline==0){
           // System.out.println(ztraining_facilityService.findfacilitybyrid(id));
            return ztraining_facilityService.findfacilitybyrid(id);
        }else{
          //  System.out.println(ztraining_facilityService.findfacilitybyrid(id));
            return ztraining_facilityService.findfactibyrid2(id);
        }

    }


    @RequestMapping("/findteststatebyfid")
    @ResponseBody
    public int findteststatebyfid(String id){
        //System.out.println(ztraining_facilityService.findfactsixportbyzid2(id));
        return ztraining_facilityService.findfactsixportbyzid2(id);

    }

    @RequestMapping("/findfiveport")
    @ResponseBody
    public void findfiveport(HttpSession session){

        Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility");

        try {
            if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
               ztraining_facilityService.updatefiveportbyzid(ztraining_facility.getZid(),2);
        }catch(Exception e) {

        }


    }




    @RequestMapping("/findteststatebyIP")
    @ResponseBody
    public String findteststatebyIP(HttpSession session, HttpServletRequest request){
        String ip=Iputil.getClientIpAddress(request);
        Ztraining_facility ztraining_facility=ztraining_facilityService.findbyip(ip);

        //数据库这边
        if (ztraining_facility.getZpowerStatus8()==1)
            ztraining_facility.setZpowerStatus6(1);
        else
            ztraining_facility.setZpowerStatus6(0);


        Thread t = new Thread(new Runnable(){

            public void run(){
                try{
                    if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                        if (ztraining_facility.getZpowerStatus8()==1)
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"16");
                        else
                            Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"26");


                    ztraining_facilityService.updatesixportbyid(ztraining_facility);

                }catch(Exception e) {

                }

            }});
        t.start();



        return ztraining_facility.getZselecttest();
    }


    @RequestMapping("/updatesixstateaftertest")
    @ResponseBody
    public void updatesixstateaftertest(HttpSession session){

        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

        String IP=ztraining_facility.getZpowerIP();

        ztraining_facilityService.updatesevenportbyip(ztraining_facility.getZstudentPCIP(),1);

        if (ztraining_facility.getZpowerStatus8()==1) {
            ztraining_facility.setZpowerStatus6(0);
            ztraining_facilityService.updatesixportbyid(ztraining_facility);

        }

       Thread t = new Thread(new Runnable(){

            public void run(){
                try {
                    if (Powerutil.pingIp(IP)){
                        Powerutil.powercontroller(IP,"17");
                        if (ztraining_facility.getZpowerStatus8()==1)
                            Powerutil.powercontroller(IP,"26");
                    }

                }catch(Exception e) {

                }

            }});
        t.start();


    }


    @RequestMapping("/reloadgreenlight")
    @ResponseBody
    public void reloadgreenlight(HttpSession session){
        //System.out.println(555555555);
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

        String IP=ztraining_facility.getZpowerIP();

        int i=ztraining_facilityService.updateoneportbyip(IP,1);

        Thread t = new Thread(new Runnable(){

            public void run(){
                try {
                    if (Powerutil.pingIp(IP)){
                        Powerutil.powercontroller(IP,"11");
                    }

                }catch(Exception e) {
                    //打印输出异常
                    e.printStackTrace();
                }

            }});
        t.start();

      //  System.out.println(111111111);
    }




    @RequestMapping("/usixout")
    @ResponseBody
    public void usixout(HttpSession session){
            //System.out.println(555555555);
            Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

            String IP=ztraining_facility.getZpowerIP();
            ztraining_facility.setZpowerStatus6(0);
            ztraining_facility.setZpowerStatus1(0);
            ztraining_facility.setZpowerStatus2(0);
            ztraining_facility.setZpowerStatus7(0);
            int i=ztraining_facilityService.updateportbyid(ztraining_facility);

            Thread t = new Thread(new Runnable(){

                public void run(){
                    try {
                        if (Powerutil.pingIp(IP)){
                            Powerutil.powercontroller(IP,"21");
                            Powerutil.powercontroller(IP,"22");
                            Powerutil.powercontroller(IP,"26");
                            Powerutil.powercontroller(IP,"27");
                        }

                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();
    }


    @RequestMapping("/findloginnamebyfaid")
    @ResponseBody
    public String findloginnamebyfaid(String id){
        return ztraining_facilityService.findstunamebyfacid(id);
    }

    @RequestMapping("/overclass2")
    @ResponseBody
    public int overclass(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        return ztraining_facilityService.overclass(zteacher_cookie.getZtrainingroomid(),"下课");
    }


    @RequestMapping("/submittac")
    @ResponseBody
    public int submittac(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility") ;
        String zprogress=ztraining_facilityService.findzprogressbyid(ztraining_facility.getZid());

        if (zprogress=="安全测试")
            return 1;
        else if(zprogress=="实训")
            return 2;
        else if(zprogress=="下课")
            return 3;
        else
            return 4;
    }


    @RequestMapping("/findsessionprogress")
    @ResponseBody
    public String findsessionprogress(HttpSession session){
       // System.out.println(session.getAttribute("zprogress"));
       return (String) session.getAttribute("zprogress");
    }


    @RequestMapping("/findisleave")
    @ResponseBody
    public int findisleave(HttpSession session){
        int i=0;
        Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility");
        String zprogress=ztraining_facilityService.findzprogressbyid(ztraining_facility.getZid());

        //System.out.println(zprogress);
        if(zprogress.equals("下课"))
            i++;
        return i;

    }

    /**
     * 清除测试次数，当前任务名称，实训设备的zprogress
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/updateprogress")
    @ResponseBody
    public int updateprogress(HttpServletRequest request,HttpSession session){

        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        Zstudent_login zstudent_login=new Zstudent_login();

        zstudent_login.setZnowtaskname("");
        zstudent_login.setZtesttime(0);
        zstudent_login.setZrecognizeIP(ztraining_facility.getZstudentPCIP());
        zstudent_login.setZcheck("登陆");

        return zstudent_loginService.removetat(zstudent_login);

    }



    @RequestMapping("/updateprogressbyroomid")
    @ResponseBody
    public int updateprogressbyroomid(HttpServletRequest request,HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");

        System.out.println(ztraining_facility);
        List<Ztraining_facility>  data =ztraining_facilityService.findfacilitybyrid(ztraining_facility.getZtrainingroomID());

        int j,k=0;
        for(int i=0;i<data.size();i++){
            Zstudent_login zstudent_login=new Zstudent_login();
            zstudent_login.setZnowtaskname("");
            zstudent_login.setZtesttime(0);
            zstudent_login.setZrecognizeIP(data.get(i).getZstudentPCIP());
            zstudent_login.setZcheck("登陆");

            j= zstudent_loginService.removetat(zstudent_login);
            if (j>0)
                k++;
        }
        return k;
    }

    @RequestMapping("/updateoatportbyrid")
    @ResponseBody
    public void updateoatportbyrid(HttpSession session){
       Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility");

       List<Ztraining_facility> data=ztraining_facilityService.findfacilitybyrid(ztraining_facility.getZtrainingroomID());

       int i=ztraining_facilityService.updateoatportbyrid(0,0,ztraining_facility.getZtrainingroomID());

        Thread t = new Thread(new Runnable(){

            public void run(){
                try {
                    for (int j=0;j<data.size();j++){
                        if (Powerutil.pingIp(data.get(j).getZpowerIP())){
                            Powerutil.powercontroller(data.get(j).getZpowerIP(),"21");
                            Powerutil.powercontroller(data.get(j).getZpowerIP(),"22");
                        }
                    }

                }catch(Exception e) {
                    //打印输出异常
                    e.printStackTrace();
                }

            }});
        t.start();

    }




    @RequestMapping("/updateallfacility")
    @ResponseBody
    public int updateallfacility(String ztrainroomid,String zpowerstatus) throws  Exception{
       // System.out.println(ztrainroomid+zpowerstatus);

        List<Ztraining_facility> data =ztraining_facilityService.findfactibyztrainingroomID(ztrainroomid);

       // System.out.println(data);
        int j=0;int k=0;
       // String powerid="";
        //想要关机
        if (zpowerstatus.equals("未开机")){
            int a=ztraining_facilityService.updatesixportbyroomid(ztrainroomid,0);

            Thread t = new Thread(new Runnable(){

                public void run(){
                    try {
                        for (int i=0;i<data.size();i++) {
                            //  powerid="2"+data.get(i).getZpowerPort();
                            // System.out.println(powerid);
                            if (data.get(i).getZpowerIP() != null)
                                if (Powerutil.pingIp(data.get(i).getZpowerIP()))
                                    Powerutil.powercontroller(data.get(i).getZpowerIP(), "26");

                        }
                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();

            //想要开机
        }else{
            int b=ztraining_facilityService.updatesixportbyroomid(ztrainroomid,1);
            Thread t = new Thread(new Runnable(){
                public void run(){
                    try {
                        for (int u=0;u<data.size();u++){
                            // powerid="1"+data.get(u).getZpowerPort();
                            //System.out.println(powerid);
                            if (data.get(u).getZpowerIP()!=null)
                                if (Powerutil.pingIp(data.get(u).getZpowerIP()))
                                    Powerutil.powercontroller(data.get(u).getZpowerIP(),"16");

                        }
                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();
        }

        return ztraining_facilityService.updateallfacility(ztrainroomid,zpowerstatus);

    }

    @RequestMapping("/updateoneatwobyzrid2")
    @ResponseBody
    public int updateoneatwobyzrid2(HttpSession session) throws  Exception{
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String rid=zteacher_cookie.getZtrainingroomid();
        Ztraining_facility ztraining_facility=new Ztraining_facility();
        ztraining_facility.setZtrainingroomID(rid);
        ztraining_facility.setZpowerStatus1(1);
        ztraining_facility.setZpowerStatus2(0);

        int i=ztraining_facilityService.updateoneatwobyrid(ztraining_facility);

        List<Ztraining_facility> data=ztraining_facilityService.findfacilitybyrid(rid);

        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    for (int u=0;u<data.size();u++){
                        if (data.get(u).getZpowerIP()!=null)
                            if (Powerutil.pingIp(data.get(u).getZpowerIP()))
                                Powerutil.powercontroller(data.get(u).getZpowerIP(),"12");
                        Powerutil.powercontroller(data.get(u).getZpowerIP(),"21");

                    }
                }catch(Exception e) {
                    //打印输出异常
                    e.printStackTrace();
                }

            }});
        t.start();

        return i;
    }

    @RequestMapping("/updateoragetogreen")
    @ResponseBody
    public void updateoragetogreen(HttpSession session,HttpServletRequest request) throws  Exception{
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie") ;
        Ztraining_facility ztraining_facility=ztraining_facilityService.findbyip(Iputil.getClientIpAddress(request));

        if (ztraining_facility.getZpowerStatus2()==0){
            ztraining_facilityService.updattwoportbyip(ztraining_facility.getZstudentPCIP(),1);
            ztraining_facilityService.updateoneportbyip(ztraining_facility.getZstudentPCIP(),0);

            Thread t = new Thread(new Runnable(){
                public void run(){
                    try {
                            if (ztraining_facility.getZpowerIP()!=null)
                                if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                                    Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"12");
                                    Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"21");

                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();
        }

    }

    @RequestMapping("/updategreentoorge")
    @ResponseBody
    public void updategreentoorge(HttpSession session,HttpServletRequest request) throws  Exception{
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie") ;
        Ztraining_facility ztraining_facility=ztraining_facilityService.findbyip(Iputil.getClientIpAddress(request));

        int i=zstudent_eventService.findisevent2(zstudent_cookie.getZstudentID(),Iputil.getClientIpAddress(request));

        if (ztraining_facility.getZpowerStatus2()==1 && i==0){
            ztraining_facilityService.updattwoportbyip(ztraining_facility.getZstudentPCIP(),0);
            ztraining_facilityService.updateoneportbyip(ztraining_facility.getZstudentPCIP(),1);

            Thread t = new Thread(new Runnable(){
                public void run(){
                    try {
                        if (ztraining_facility.getZpowerIP()!=null)
                            if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                                Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"22");
                                Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"11");

                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();
        }

    }





    @RequestMapping("/updateoneatwobyzrid")
    @ResponseBody
    public int updateoneatwobyzrid(HttpSession session) throws  Exception{
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String rid=zteacher_cookie.getZtrainingroomid();
        Ztraining_facility ztraining_facility=new Ztraining_facility();
        ztraining_facility.setZtrainingroomID(rid);
        ztraining_facility.setZpowerStatus1(0);
        ztraining_facility.setZpowerStatus2(1);

        int i=ztraining_facilityService.updateoneatwobyrid(ztraining_facility);

        List<Ztraining_facility> data=ztraining_facilityService.findfacilitybyrid(rid);

        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    for (int u=0;u<data.size();u++){
                        if (data.get(u).getZpowerIP()!=null)
                            if (Powerutil.pingIp(data.get(u).getZpowerIP()))
                                Powerutil.powercontroller(data.get(u).getZpowerIP(),"21");
                                Powerutil.powercontroller(data.get(u).getZpowerIP(),"12");

                    }
                }catch(Exception e) {
                    //打印输出异常
                    e.printStackTrace();
                }

            }});
        t.start();

        return i;
    }



    @RequestMapping("/updatefaclitybychose2")
    @ResponseBody
    public int updatefaclitybychose2(@RequestParam(value = "zid[]")String [] zid, @RequestParam(value = "p_passcode")int  p_passcode, @RequestParam(value = "p_testnum")int  p_testnum, @RequestParam(value = "p_testtype")String  p_testtype, @RequestParam(value = "istest")int  istest,@RequestParam(value = "isconnected")int  isconnected) throws  Exception{
        int j,k=0;

        for (int i=0;i<zid.length;i++){

            Ztraining_facility ztraining_facility=new Ztraining_facility();
            ztraining_facility.setZid(zid[i]);
            ztraining_facility.setZpassingscore(p_passcode);
            ztraining_facility.setZsafetestingNum(p_testnum);
            ztraining_facility.setZsafetestingType(p_testtype);

            //ztraining_facility.setZpowerStatus6(istest);


            if (istest==0)
                ztraining_facility.setZselecttest("否");
            else
                ztraining_facility.setZselecttest("是");

            ztraining_facility.setZpowerStatus8(isconnected);

            //System.out.println(p_testnum);
            j=ztraining_facilityService.updatefatestbyid2(ztraining_facility);

            if (j>0)k++;

            //电源联动为0时，将第六端口的值更改为0
            if(isconnected==0){
                Ztraining_facility ztraining_facility1=ztraining_facilityService.findfacilitybyid(zid[i]);
                ztraining_facility1.setZpowerStatus6(0);

                ztraining_facilityService.updatesixportbyid(ztraining_facility1);

                Thread t = new Thread(new Runnable(){
                    public void run(){
                        try {
                            if (ztraining_facility1.getZpowerIP()!=null)
                                if (Powerutil.pingIp(ztraining_facility1.getZpowerIP()))
                                    Powerutil.powercontroller(ztraining_facility1.getZpowerIP(),"26");

                        }catch(Exception e) {
                            //打印输出异常
                            e.printStackTrace();
                        }

                    }});
                t.start();
            }


            //教师端这边不用管继电器
            /*Ztraining_facility ztraining_facility1=ztraining_facilityService.findfacilitybyid(zid[i]);

            //开另一个线程去操作继电器
            Thread t = new Thread(new Runnable(){
                public void run(){
                    try {
                        if (ztraining_facility1.getZpowerIP()!=null)
                            if (Powerutil.pingIp(ztraining_facility1.getZpowerIP()))
                                if (istest==1)
                                    Powerutil.powercontroller(ztraining_facility1.getZpowerIP(),"16");
                                else
                                    Powerutil.powercontroller(ztraining_facility1.getZpowerIP(),"26");
                                if (isconnected==1)
                                    Powerutil.powercontroller(ztraining_facility1.getZpowerIP(),"18");
                                else
                                    Powerutil.powercontroller(ztraining_facility1.getZpowerIP(),"28");
                    }catch(Exception e) {
                        //打印输出异常
                        e.printStackTrace();
                    }

                }});
            t.start();*/

        }

        return k;
    }



    @RequestMapping("/getteacherroom")
    @ResponseBody
    public String getteacherroom(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zteacher_cookie.getZtrainingroomid();
    }




    @RequestMapping("/getishandsup")
    @ResponseBody
    public Zstudent_event getishandsup(String zid,String ztype){
       return zstudent_eventService.findupnumberbyfai(zid,ztype);
    }


    @RequestMapping("/findfaclitybyzid")
    @ResponseBody
    public Ztraining_facility findfaclitybyzid(String zid){
       return ztraining_facilityService.findfacilitybyid(zid);
    }



    @RequestMapping("/updatecheckvaluestuid")
    @ResponseBody
    public void updatecheckvaluestuid(@RequestParam(value = "id[]")String [] id,HttpSession session){
        System.out.println(id[0]);
        session.setAttribute("ckeckstudentid",id);
    }



    //电源管理更改
    @RequestMapping("/updateallfacilitybyzid")
    @ResponseBody
    public int updateallfacilitybyzid(@RequestParam(value = "zid[]")String [] zid, @RequestParam(value = "zpowerstatus")String  zpowerstatus, @RequestParam(value = "kind")String  kind) throws Exception{
        //System.out.println(zid+zpowerstatus+kind);

        int j,k=0;
        int q,p=0;

        for (int i=0;i<zid.length;i++){

            Ztraining_facility ztraining_facility2=new Ztraining_facility();
            ztraining_facility2.setZid(zid[i]);

           j= ztraining_facilityService.updateallfacilitybyzid(zid[i], zpowerstatus);
           Ztraining_facility ztraining_facility=ztraining_facilityService.findcontrollerbyid(zid[i]);

           // System.out.println(ztraining_facility);
           if (kind.equals("关闭")){
               Thread t = new Thread(new Runnable(){
                   public void run(){
                       try {
                           if (ztraining_facility.getZpowerIP()!=null)
                               if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                                   Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"26");
                       }catch(Exception e) {
                           //打印输出异常
                           e.printStackTrace();
                       }

                   }});
               t.start();

               ztraining_facility2.setZpowerStatus6(0);
           }else if(kind.equals("开启")){

               Thread t = new Thread(new Runnable(){
                   public void run(){
                       try {
                           if (ztraining_facility.getZpowerIP()!=null)
                               if (Powerutil.pingIp(ztraining_facility.getZpowerIP()))
                                   Powerutil.powercontroller(ztraining_facility.getZpowerIP(),"16");
                       }catch(Exception e) {
                           //打印输出异常
                           e.printStackTrace();
                       }

                   }});
               t.start();

               ztraining_facility2.setZpowerStatus6(1);
           }
           //更新数据库继电器6端口的值
            p=ztraining_facilityService.updatesixportbyid(ztraining_facility2);

           if(j==1)k++;

        }
        return p;
    }

    @RequestMapping("/exitsystem")
    @ResponseBody
    public void exitsystem(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        String zstudentPCIP="";
        if(null!=ztraining_facility){
            zstudentPCIP =ztraining_facility.getZstudentPCIP();
        }

        int i =ztraining_facilityService.updatezprogressbyip(zstudentPCIP,"退出系统");

    }

    @RequestMapping("/noexitsystem")
    @ResponseBody
    public void noexitsystem(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        String zstudentPCIP="";
        if(null!=ztraining_facility){
            zstudentPCIP =ztraining_facility.getZstudentPCIP();
        }

        int i =ztraining_facilityService.updatezprogressbyip(zstudentPCIP,"登陆");

    }


    @RequestMapping("/noexitsystem2")
    @ResponseBody
    public void noexitsystem2(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        String zstudentPCIP="";
        if(null!=ztraining_facility){
            zstudentPCIP =ztraining_facility.getZstudentPCIP();
        }

        int i =ztraining_facilityService.updatezprogressbyip(zstudentPCIP,"测试中");

    }

    @RequestMapping("/noexitsystem3")
    @ResponseBody
    public void noexitsystem3(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        String zstudentPCIP="";
        if(null!=ztraining_facility){
            zstudentPCIP =ztraining_facility.getZstudentPCIP();
        }

        int i =ztraining_facilityService.updatezprogressbyip(zstudentPCIP,"实训中");

    }


}
