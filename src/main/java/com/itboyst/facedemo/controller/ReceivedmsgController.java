package com.itboyst.facedemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.itboyst.facedemo.dto.*;

import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ReceivedmsgController {
    public final static Logger logger = LoggerFactory.getLogger(ReceivedmsgController.class);

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    ZsysconfigService zsysconfigService;

    @Autowired
    Zstudent_loginService zstudentLoginService;

    @Autowired
    Zteacher_loginService zteacher_loginService;

    @Autowired
    ZstrangeService zstrangeService;

    @RequestMapping(value = "/insertrecognitionFace", method = RequestMethod.POST)
    @ResponseBody
    public void receivedMsg( String receivedMsg){
        JSONObject jsonObject = JSONObject.parseObject(receivedMsg);
        System.out.println("json :"+jsonObject);
        System.out.println("json的尺寸 :"+jsonObject.size());
        JSONObject dongtaipayload = jsonObject.getJSONObject("payload");
        String xuehao = dongtaipayload.getString("personIdCard");
        //相似的程度，1代表很相似2代表比较相似3代表有点相似
        String typestr= dongtaipayload.getString("type");
        Integer type =Integer.parseInt(typestr);
        String originalPictureUrl =dongtaipayload.getString("originalPictureUrl");
        String strangername =dongtaipayload.getString("personName");
        Zsysconfig zsysconfig = zsysconfigService.findIPByZname("杰视服务器IP地址");
        String jieshiip = zsysconfig.getZvalue();
        //如果type的类型为1则进入允许执行写入登录日志
        if(1==type){

            String cameraName =dongtaipayload.getString("cameraName");

            System.out.println("识别:" + xuehao + " , " + dongtaipayload.getString("personName"));

            if ((xuehao != null) && (xuehao.length() > 0)) {

                Zstudent_login zsl=new Zstudent_login();
                String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                zsl.setZid(uuid2);
                zsl.setZrecognizeIP(jieshiip);
                zsl.setZcheck("人脸识别");
                Zstudent abc =zstudentService.findstudentByZidentity(xuehao);

                //**这个地方的判空处理有问题
                Zteacher zteacher =zteacherService.findteacherByzidentity(xuehao);

                if(null!=abc){
                    zsl.setZstudentID(abc.getZid());
                    zsl.setZtype("入口签到");
                    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                    zsl.setZrecongnizetime(timestamp);
                    /*String fpath =faceEngineService.findfopathByfaceid(abc.getZfaceinfoID());*/
                    zsl.setOriginalPictureUrl(originalPictureUrl);
                    //设置课程信息
                    List<String> list =zstudentLoginService.findScheduleBytimeandzstudentID(abc.getZid(),timestamp);

                    if(!list.isEmpty()){
                        zsl.setZscheduleID(list.get(0));
                    }

                    int a =zstudentLoginService.updateloginmessage(zsl);
                }
                //把识别的老师信息加入到登录日志中
                if(null!=zteacher){
                    Zteacher_login zteacher_login = new Zteacher_login();
                    String uuid = UUID.randomUUID().toString().replaceAll("-","");
                    zteacher_login.setZid(uuid);
                    zteacher_login.setZteacherID(zteacher.getZid());
                    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                    zteacher_login.setZrecognizetime(timestamp);
                    zteacher_login.setZtype("入口签到");
                    zteacher_login.setZcheck("人脸识别");
                    zteacher_login.setZrecognizeIP(jieshiip);
                    /* String teacherpath =faceEngineService.findfopathByfaceid(zteacher.getZfaceinfoID());*/
                    zteacher_login.setOriginalPictureUrl(originalPictureUrl);
                    int b = zteacher_loginService.delAndinsertteacher(zteacher_login);
                }

            }
        }
        if(2==type || 3==type){
            Zstrange zstrange = new Zstrange();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            zstrange.setZid(uuid);
            zstrange.setZname("陌生人");
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            zstrange.setZrecognizetime(timestamp);
            zstrange.setZtype("入口签到");
            zstrange.setZcheck("人脸识别");
            zstrange.setZrecognizeIP(jieshiip);
            zstrange.setOriginalPictureUrl(originalPictureUrl);
            zstrange.setZstatus("陌生人");
            int c = zstrangeService.insertZstrange(zstrange);
        }




     }


    @RequestMapping(value = "/handlewebsocket", method = RequestMethod.POST)
    @ResponseBody
    public void receive(String arg,String startorend){
        System.out.println("startorend : "+startorend);
        Zsysconfig zsysconfig = zsysconfigService.findIPByZname("杰视服务器IP地址");
        String jieshiip = zsysconfig.getZvalue();
        /*ceshisocket ceshisocket = new ceshisocket();
        ceshisocket.getClient("ws://" + jieshiip + ":8080/webapi/websocket");*/
        Renlian renlian = new Renlian();
        if(startorend.equals("开始")) {
            renlian.setJieshiip(jieshiip);
            renlian.setUrl("ws://" + jieshiip + ":8080/webapi/websocket");
            System.err.println("ws://" + jieshiip + ":8080/webapi/websocket");
            renlian.renlianwinCreate();
            try {
                renlian.startBtnClick();
            } catch (RuntimeException e) {
                System.out.println("远程没有连接上");
            }
        }
        if(startorend.equals("结束")){
            renlian.stopBtnClick();
        }

}

}
