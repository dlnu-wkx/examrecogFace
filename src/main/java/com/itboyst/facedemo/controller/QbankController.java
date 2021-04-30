package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.itboyst.facedemo.base.UUIDutil.getUUID;

@Controller
public class QbankController {
    public final static Logger logger = LoggerFactory.getLogger(QbankController.class);

    @Autowired
    QbankService Qservice;

    @Autowired
    Ztesting_inputService ztesting_inputService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    ZscheuleService zscheuleService;

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    Zstudent_loginService zstudent_loginService;

    /*
   勾选开启安全测试
  */
    @RequestMapping("/updatetesttime")
    @ResponseBody
    public int updatetesttime(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        return zstudent_loginService.updatetesttime(zstudent_cookie.getZstudentID(),zstudent_cookie.getZscheduleID());
    }


    /**
     * 老师名字
     * @param session
     * @return
     */
    @RequestMapping("/getteachername")
    @ResponseBody
    public String getteachername(HttpSession session){
       Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
       return zteacher_cookie.getZname();
    }



    /*
    勾选开启安全测试
   */
    @RequestMapping("/updatetestbychose")
    @ResponseBody
    public int updatetestbychose(HttpSession session,@RequestParam(value = "zpassingscore")int zpassingscore,@RequestParam(value = "zid[]")String [] zid){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        int j,k=0;
        for(int i=0;i<zid.length;i++){
            j=zstudent_scheduleService.updatetestbychose("是",zpassingscore,zteacher_cookie.getZscheduleID(),zid[i]);
            if(j==1)k++;
        }

        return k;
    }

    /*
        勾选不开启安全测试
     */
    @RequestMapping("/updatenotestbychose")
    @ResponseBody
    public int updatenotestbychose(HttpSession session,@RequestParam(value = "zid[]")String [] zid){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        int j,k=0;
        for(int i=0;i<zid.length;i++){
            j=zstudent_scheduleService.updatetestbychose("否",0,zteacher_cookie.getZscheduleID(),zid[i]);
            if(j==1)k+=1;
        }

        return k;
    }


    @RequestMapping("/findtenumbytype")
    @ResponseBody
    public int findtenumbytype(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility) session.getAttribute("ztraining_facility");

        return Qservice.findnumberbytype(ztraining_facility.getZsafetestingType());
    }


    @RequestMapping("/findthetest")
    @ResponseBody
    public int findthetest(){

        return Qservice.findnumberbytype("理论测试");
    }

    @RequestMapping("/getsessionthid")
    @ResponseBody
    public List<String> getsessionthid(HttpSession session){
        List<String> data=(List<String>) session.getAttribute("thid");
        return data;
    }


    @RequestMapping("/findtheanws")
    @ResponseBody
    public List<String> findtheanws(HttpSession session){
        List<String> data=(List<String>) session.getAttribute("answer2");
        return data;
    }



    @RequestMapping("/findalltestbytypename")
    @ResponseBody
    public List<Zsafe_testingDto> findalltestbytype(HttpSession session,String name){

        List<Zsafe_testingDto> data2=Qservice.findalltestbytype(name);

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");

        List<Ztesting_input>data=new ArrayList<>();

        List<String> answer2 = new ArrayList<>();

        List<String> thid=new ArrayList<>();

        for(int i=0;i<data2.size();i++){

            Ztesting_input ztesting_input=new Ztesting_input();
            ztesting_input.setZstudentscheduleID(zstudent_cookie.getZstudent_scheduleid());
            String zid = UUID.randomUUID().toString().replaceAll("-","");
            thid.add(zid);
            ztesting_input.setZid(zid);
            ztesting_input.setZsafetestingID(data2.get(i).getZid());
            ztesting_input.setZstate("未做");
            ztesting_input.setZorder(i+1);
            data.add(ztesting_input);
            answer2.add(i,data2.get(i).getZresult());
        }

        int i=ztesting_inputService.addtestinput(data);
        session.setAttribute("answer2",answer2);
        session.setAttribute("thid",thid);

        return data2;
    }






    /*
    根据上课表id更新是否需要考试及考试分数
     */
    @RequestMapping("/updatetestbyscheduleid")
    @ResponseBody
    public int updatetestbyscheduleid(HttpSession session,int zpassingscore){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zscheuleService.updatetestbyscheduleid("是",zpassingscore,zteacher_cookie.getZscheduleID());
    }


    /*
   根据上课表id更新变成不需要测试，合格分数更为0
    */
    @RequestMapping("/updatenotestbyscheduleid")
    @ResponseBody
    public int updatenotestbyscheduleid(HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        //System.out.println(zteacher_cookie.getZscheduleID());
        return zscheuleService.updatetestbyscheduleid("否",0,zteacher_cookie.getZscheduleID());
    }



    /*
    查找合格分数
     */
    @RequestMapping("/findpassingcode")
    @ResponseBody
    public int findpassingcode(HttpSession session,HttpServletRequest request){

        String ip4= Iputil.getClientIpAddress(request);
        int a=ztraining_facilityService.updatezprogressbyip(ip4,"安全测试");

        session.setAttribute("zprogress","安全测试");

        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        //System.out.println(zstudent_cookie);
        return ztraining_facility.getZpassingscore();
    }

    @RequestMapping("/findtestnumber")
    @ResponseBody
    public int findtestnumber(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        //System.out.println(zstudent_cookie);
        return ztraining_facility.getZsafetestingNum();
    }

    @RequestMapping("/findtesttotal")
    @ResponseBody
    public int findtesttotal(HttpSession session){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility");
        //System.out.println(zstudent_cookie);
        return ztraining_facility.getZsafetestingTotal();
    }



    @RequestMapping("/findtestbynumber")
    @ResponseBody
    public Map<String,Object> findtestbynumber(HttpSession session,int number){
        Ztraining_facility ztraining_facility=(Ztraining_facility)session.getAttribute("ztraining_facility") ;

        //System.out.println(number);
        List<Zsafe_testingDto> data1=Qservice.findrandbynumber(number,ztraining_facility.getZsafetestingType());
        //用于排序
        List<Zsafe_testingDto> data3=new ArrayList<>();

        List<Ztesting_input> data2 =new ArrayList<>();


        //System.out.println(data1);
        //初始化定义选择题与判断题题集与题集答案
        List<Zsafe_testingDto> cbank = new ArrayList<>();
        List<Zsafe_testingDto> jbank = new ArrayList<>();
        List<String> answer = new ArrayList<>();
        //生成number个数的uuid
        String uuid[]=getUUID(number);

        /*for (int i=0;i<uuid.length;i++){
            System.out.println(uuid[i]);
        }*/

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        //System.out.println(zstudent_cookie);
        int order =0;

        //将所有题目从选择题到判断题进行排序，然后方便存入ztestinginput
        for (int i=0;i<data1.size();i++){
            if (data1.get(i).getZtitletype().equals("选择")){
               data3.add(data1.get(i));
            }
        }
        for (int i=0;i<data1.size();i++){
            if (data1.get(i).getZtitletype().equals("判断")){
                data3.add(data1.get(i));
            }
        }


        //将所有的题目分别放入选择题与判断中,并把加载的所有题都放入ztesting_input里面
        for (int i=0;i<data3.size();i++){
            Ztesting_input ztesting_input=new Ztesting_input();

            ztesting_input.setZstudentscheduleID(zstudent_cookie.getZstudent_scheduleid());
            ztesting_input.setZid(uuid[i]);
            ztesting_input.setZsafetestingID(data3.get(i).getZid());
            ztesting_input.setZstate("未做");
            ztesting_input.setZorder(i+1);
            data2.add(ztesting_input);

            Zsafe_testingDto zsafe_testingDto=data3.get(i);
            answer.add(i,data3.get(i).getZresult());

            if (data3.get(i).getZtitletype().equals("选择")){
                cbank.add(zsafe_testingDto);
            }else if(data3.get(i).getZtitletype().equals("判断")){
                jbank.add(zsafe_testingDto);
            }

        }


        Map<String, Object> safetest=new HashMap<>();
        safetest.put("cbank",cbank);
        safetest.put("jbank",jbank);
        safetest.put("testinputid",uuid);
        // System.out.println(data2);

        int q=ztesting_inputService.addtestinput(data2);
       // System.out.println(q);
        //System.out.println(answer);
        session.setAttribute("answer",answer);
        return safetest;

    }


    @RequestMapping("/getsafetestanswer")
    @ResponseBody
    public List<String> getsafetestanswer(HttpSession session){
        //System.out.println(session.getAttribute("answer"));
        return (List<String>) session.getAttribute("answer");
    }

    @RequestMapping("/findallsafetype")
    @ResponseBody
    public List<String> findallsafetype(){
       return Qservice.findallsafetype();
    }


    /*
    查找所有问题
     */
    @RequestMapping("/findallquestion")
    @ResponseBody
    public Map<String, Object> findall(HttpServletResponse response,HttpSession session) {
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");

        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();

        int i = ztesting_inputService.deletebystscheid(zstudentscheduleID);

        int j=0;

        List<Ztesting_input> data = new ArrayList<>();

        Map<String, Object> safetest=Qservice.findallQuestion(response);
        List<Zsafe_testingDto> cbank=(List<Zsafe_testingDto>) safetest.get("cbank");
        List<Zsafe_testingDto> jbank=(List<Zsafe_testingDto>) safetest.get("jbank");
        String zsafetestid=null;
        String uuid[]=getUUID(5);

        for (int w=0;w<5;w++){
            // System.out.println(uuid);
            Ztesting_input ztesting_input=new Ztesting_input();
            ztesting_input.setZstudentscheduleID(zstudentscheduleID);
            ztesting_input.setZstate("未做");

            ztesting_input.setZid(uuid[w]);
                if (w<3){
                    zsafetestid=cbank.get(w).getZid();
                }else {
                    zsafetestid =jbank.get(w-3).getZid();
                }
                ztesting_input.setZsafetestingID(zsafetestid);
                ztesting_input.setZorder(w+1);
                data.add(ztesting_input);

        }

        j=ztesting_inputService.addtestinput(data);

        return safetest;
    }

    /*
    更新安全测试表，每刷一次更新一次
     */
    @RequestMapping("/updatetestinput")
    @ResponseBody
    public int updatetestinput(@RequestParam(value = "id[]")String [] id,@RequestParam(value = "number")int number,@RequestParam(value = "questionid[]")String [] questionid,@RequestParam(value = "ananswer[]")String [] ananswer,@RequestParam(value = "answercode[]")String [] answercode,HttpSession session){

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        String zstudentscheduleID=zstudent_cookie.getZstudent_scheduleid();
        int j=0,k=0;


        for (int i=0;i<ananswer.length;i++){
            Ztesting_input ztesting_input=new Ztesting_input();

            ztesting_input.setZinput(ananswer[i]);
            ztesting_input.setZstate("已做");

            ztesting_input.setZscore(Integer.parseInt(answercode[i]));
            ztesting_input.setZid(id[i]);

            k=ztesting_inputService.updatelist(ztesting_input);

            if(k==1){
                j++;
            }
        }

        return j;
    }



    /**
     * 跳转到显示题目库的页面
     * weikaixuan 2020-10-13
     * @return
     */
    @RequestMapping(value = "/student_test",method = RequestMethod.GET)
    public String test(){

        return "studentTest";
    }

    /**
     * 跳转测试
     * @return
     */
    @RequestMapping(value = "/teacher_measure")
    public String teacher_measure(){return "teacher_measure";}

    @RequestMapping(value = "/power_controller")
    public String power_controller(){return "power_controller";}

    @RequestMapping(value = "/face_search")
    public String face_search(){return "face_search";}


    @RequestMapping(value = "/sodeom")
    public String sodeom(){return "sodeom";}


    @RequestMapping(value = "/student_LoginTest")
    public String student_LoginTest(){return "student_LoginTest";}



    @RequestMapping(value = "/time_status")
    public String time_status(){return "time_status";}


    @RequestMapping(value = "/information_service")
    public String information_service(){return "information_service";}


    @RequestMapping(value = "/face_registration")
    public String face_registration(){return "face_registration";}



    @RequestMapping(value = "/information_delivery")
    public String information_delivery(){return "information_delivery";}

    @RequestMapping(value = "/information_delivery2")
    public String information_delivery2(){return "information_delivery2";}

    @RequestMapping(value = "/test2")
    public String test2(){return "test2";}

    @RequestMapping(value = "/student_status")
    public String student_status(){return "student_status";}



    @RequestMapping(value = "/test1")
    public String test1(){return "test1";}


    @RequestMapping(value = "/fixed_task")
    public String fixed_task(){return "fixed_task";}


    @RequestMapping(value = "/temporary_task")
    public String temporary_task(){return "temporary_task";}


    @RequestMapping(value = "/screen_delivery")
    public String screen_delivery(){return "screen_delivery";}

    @RequestMapping(value = "/index")
    public String index(){return "index";}

    @RequestMapping(value = "/class_ppt")
    public String class_ppt(){return "class_ppt";}

    @RequestMapping(value = "/teacher")
    public String teacher(){return "TeacherLogin";}

    @RequestMapping(value = "/teacher_LoginTest")
    public String teacher_LoginTest(){return "teacher_LoginTest";}

    @RequestMapping(value = "/course")
    public String video(){return "course_management";}

    @RequestMapping(value = "/loginManagement")
    public String loginManagement(){return "login_management";}

    @RequestMapping(value = "/1")
    public String down(){return "down";}

    /**
     * 查十个题目，备用
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/findrand10")
    @ResponseBody
    public List<Zsafe_testingDto> findrand10(HttpServletResponse response, HttpSession session) {
        //从session中取出两个值
        Zstudent zstudent =(Zstudent)session.getAttribute("zstudent");
        String zstudentID=zstudent.getZid();

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        String zsafetestingType=zstudent_cookie.getZsafetestingType();

        System.out.println( Qservice.findrand10(zstudentID,zsafetestingType).size());
        for (Zsafe_testingDto string : Qservice.findrand10(zstudentID,zsafetestingType)) {
            System.out.println(string);
        }

        return Qservice.findrand10(zstudentID,zsafetestingType);
    }






}
