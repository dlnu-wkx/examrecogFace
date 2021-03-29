package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class TimeStatusController {

    public final static Logger logger = LoggerFactory.getLogger(TimeStatusController.class);

    @Autowired
    Ztraining_facilityService ztraining_facilityService;

    @Autowired
    Zstudent_eventService zstudent_eventService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    Ztask_content_logService ztask_content_logService;

    @Autowired
    Ztraining_task_contentService ztraining_task_contentService;

    @Autowired
    Ztraining_cameraService ztraining_cameraService;

    @Autowired
    Ztraining_roomService ztraining_roomService;

    /**
     * 根据老师的教室查找该教室所有的机床
     * 魏凯旋 2020-11-16
     * @param session
     * @param ztraining_room
     * @return
     */
    @RequestMapping("/findfacilitybytrainingroom")
    @ResponseBody
    public List<Ztraining_facility> findfacilitybytrainingroom(HttpSession session, String ztraining_room){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        //**同130行一样做判空处理，异常处理
        String ztrainingroomID ="";
        try {
            ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        }catch (Exception e){
            System.out.println("TimeStatusController中/findfacilitybytrainingroom中的zteacher_cookie失活");
        }

        return ztraining_facilityService.findfactibyztrainingroomID(ztrainingroomID);

    }

    /**
     * 根据id查找用该机床的同学是否有请假举手的状态
     * 魏凯旋 2020-11-16
     * @param zid
     * @return
     */
    @RequestMapping("/findRaiseHand")
    @ResponseBody
    public Zstudent_event  findRaiseHand( String zid){
        Zstudent_event zstudent_event =zstudent_eventService.findRaiseHandByFacility(zid);

        return zstudent_event;

    }

    /**
     * 根据id查找机床是否是开机状态
     * 魏凯旋 2020-11-17
     * @param zid
     * @return
     */
    @RequestMapping("/findOpenPower")
    @ResponseBody
    public String   findOpenPower( String zid){
        String  zstudentPCIP  =ztraining_facilityService.findOpenPower(zid);

        return zstudentPCIP ;
    }

    /**
     * 根据机床的id找出使用学生的姓名
     * 魏凯旋2020-11-17
     * @param zid
     * @return
     */
    @RequestMapping("/findStudentName")
    @ResponseBody
    public String   findStudentName( String zid){
        String name =zstudentService.findStudentNameByfacilityID(zid);

        return name ;
    }

    /**
     *      * 根据设备的id找到相应学生的当前做的内容
     *      * 魏凯旋 2020-11-17
     *      * @param zid
     *      * @return
     */
    @RequestMapping("/presentProgess")
    @ResponseBody
    public String  presentProgess(String zid) {
        //System.err.println("zid ："+zid);
        /*List<Ztraining_task_content> ztraining_task_content = ztraining_task_contentService.findpresentProgessByfacilityID(zid);
        if(ztraining_task_content.size()>0){
            return ztraining_task_content.get(0);
        }*/
        String zprogress = ztraining_facilityService.findzprogressByip(zid);
        return zprogress;
    }

    /**
     * 魏凯旋 2020-12-12
     * @param session
     * @return
     */
    @RequestMapping("/findAllCameras")
    @ResponseBody
    public List<Ztraining_camera> findAllCameras(HttpSession session,String type){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        //System.out.println(zteacher_cookie);
        //**这个地方对于zteacher_cookie为空要做一些处理，异常处理
        String ztrainingroomID ="";
        try {
            ztrainingroomID =zteacher_cookie.getZtrainingroomid();
        }catch(Exception e){
            System.out.println("zteacher_cookie.getZtrainingroomid()为空，TimeStatusController中的/findAllCameras");
        }

        //把数据库中用","形式写的摄像头遍历出来并且组装成一个新的摄像头
        List<Ztraining_camera> ztrainingCameraList =ztraining_cameraService.findAllByZtrainingroomID(ztrainingroomID,type);
        if(ztrainingCameraList.size()>0){
            for(int j=0;j<ztrainingCameraList.size(); j++){
                Ztraining_camera a=ztrainingCameraList.get(j);
                //System.out.println(a);
                if(null !=a.getZcameraName()||a.getZcameraName().contains(",")){
                    String[] str = a.getZcameraName().split(",");
                    for(int i=0;i<str.length;i++){
                        Ztraining_camera  ztraining_camera = new Ztraining_camera();
                        ztraining_camera.setZid(a.getZid());
                        ztraining_camera.setZtrainingroomID(a.getZtrainingroomID());
                        ztraining_camera.setZidentity(a.getZidentity());
                        ztraining_camera.setZcameraIP(a.getZcameraIP());
                        ztraining_camera.setZcameraName(str[i]);
                        ztraining_camera.setZstatus(a.getZstatus());
                        ztraining_camera.setZtitle(a.getZtitle());
                        ztraining_camera.setZwebaddress(a.getZwebaddress());
                        ztrainingCameraList.add(ztraining_camera);
                    }
                    ztrainingCameraList.remove(j);
                }
            }
        }
       /* for(Ztraining_camera b :ztrainingCameraList){
            System.out.println(b);
        }*/
        //如果有大于一个摄像头则对摄像头的名字进行排序
        if(ztrainingCameraList.size()>1){
            Collections.sort(ztrainingCameraList, new Comparator<Ztraining_camera>() {
                @Override
                public int compare(Ztraining_camera o1, Ztraining_camera o2) {
                    int aim =Integer.parseInt(o1.getZcameraName())-Integer.parseInt(o2.getZcameraName());
                    if(aim>0){
                        return 1;
                    }else if(aim<0){
                        return -1;
                    }
                    return 0;
                }
            });
        }
        for(Ztraining_camera b:ztrainingCameraList){
            String name =ztraining_roomService.findztrainroomNamebyfacilityID(b.getZid());
            b.setZtrainingroomID(name);
        }

        return ztrainingCameraList;
    }

    /**
     *
     * @param zid
     * @param session
     * @return
     */
    @RequestMapping("/disagree")
    @ResponseBody
        public int disagree(HttpSession session,String zid,String content){
        Zstudent_event zstudent_event =zstudent_eventService.findRaiseHandByFacility(zid);
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String zteacherid ="";
        try {
            zteacherid =zteacher_cookie.getZteacherid();
        }catch(Exception e){
            System.out.println("zteacher_cookie.getZteacherid()为空，TimeStatusController中的/disagree");
        }
        if(zstudent_event !=null){
            zstudent_event.setZteacherID(zteacherid);
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            zstudent_event.setZhandletime(timestamp);
            zstudent_event.setZhandlecontent(content);
            System.out.println("disagree"+zstudent_event);
            int j =zstudent_eventService.updateTeacherIDandStatus(zstudent_event);
            return j;
        }
        return 0;
        }


    /**
     *
     * @param zid
     * @param session
     * @return
     */
    @RequestMapping("/agree")
    @ResponseBody
    public int agree(HttpSession session,String zid,String content){
        Zstudent_event zstudent_event =zstudent_eventService.findRaiseHandByFacility(zid);
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String zteacherid ="";
        try {
            zteacherid =zteacher_cookie.getZteacherid();
        }catch(Exception e){
            System.out.println("zteacher_cookie.getZteacherid()为空，TimeStatusController中的/agree");
        }
        if(zstudent_event !=null){
            zstudent_event.setZteacherID(zteacherid);
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            zstudent_event.setZhandletime(timestamp);
            zstudent_event.setZhandlecontent(content);
            System.out.println("agree :"+zstudent_event);
            int i =zstudent_eventService.updateTeacherIDandStatus(zstudent_event);
            return i;
        }
        return 0;
    }

    /**
     *
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("/cancelRaisehand")
    @ResponseBody
    public int cancelRaisehand(HttpSession session,String id){
        Zstudent_event zstudent_event =zstudent_eventService.findRaiseHandByFacility(id);
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String zteacherid ="";
        try {
            zteacherid =zteacher_cookie.getZteacherid();
        }catch(Exception e){
            System.out.println("zteacher_cookie.getZteacherid()为空，TimeStatusController中的/cancelRaisehand");
        }
        if(zstudent_event !=null){
            zstudent_event.setZteacherID(zteacherid);
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            zstudent_event.setZhandletime(timestamp);
            zstudent_event.setZhandlecontent("已知晓");
            System.out.println("取消举手 :"+zstudent_event);
            int i =zstudent_eventService.updateTeacherIDandStatus(zstudent_event);
            return i;
        }
        return 0;
    }

}
