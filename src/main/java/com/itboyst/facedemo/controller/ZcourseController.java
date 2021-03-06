package com.itboyst.facedemo.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class ZcourseController {
    public final static Logger logger = LoggerFactory.getLogger(ZcourseController.class);

    @Autowired
    ZcourseService zcourseService;

    @Autowired
    ZscheuleService zscheuleService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    Zassign_scheduleService zassign_scheduleService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    Zteacher_scheduleService zteacher_scheduleService;

    @RequestMapping("/addcourse")
    @ResponseBody
    public int  addcourse(String name,String type){
        Zcourse zcourse = new Zcourse();
        String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
        zcourse.setZid(uuid2);
        zcourse.setZidentity("2");
        zcourse.setZname(name);
        zcourse.setZtype(type);
       int a =zcourseService.insertCourse(zcourse);

        return a;

    }

    @RequestMapping("/findAllcourse")
    @ResponseBody
    public List<Zcourse>  findAllcourse(){

        List<Zcourse> zcourseList =zcourseService.findAllcourse();

        return zcourseList;

    }

    @RequestMapping("/findallteacher")
    @ResponseBody
    public List<Zteacher>  findallteacher(){

        List<Zteacher> zteacherList = zteacherService.findAllteacher();

        return zteacherList;

    }



    @RequestMapping("/addzscheduleAndzstudentscheduleAndassignschedule")
    @ResponseBody
    public String  addzschedule(String trainingroomID,String zstartdate,String zenddate,String courseID,@RequestParam(value = "zid[]")String [] zid,@RequestParam(value = "trainingtaskID[]")String [] trainingtaskID,@RequestParam(value = "scheduleteacherid[]")String [] scheduleteacherid){

        Zschedule zschedule = new Zschedule();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        Timestamp startdate = Timestamp.valueOf(zstartdate);
        Timestamp enddate = Timestamp.valueOf(zenddate);

        List<Zschedule>  zscheduleList = zscheuleService.findbytime(startdate);

        if(CollectionUtil.isEmpty(zscheduleList)){//????????????????????????????????????????????????????????????
            zschedule.setZid(uuid);
            zschedule.setZcourseID(courseID);
            zschedule.setZtrainingroomID(trainingroomID);
            zschedule.setZstartdate(startdate);
            zschedule.setZenddate(enddate);
            int a = zscheuleService.addzschedule(zschedule);

            //??????????????????????????????????????????
            if(a>0){

                //????????????????????????????????????
                Zteacher_schedule zteacher_schedule = new Zteacher_schedule();
                zteacher_schedule.setZscheduleID(uuid);
                int o =0;
                for(int y =0;y<scheduleteacherid.length;y++){
                    String tsuuid = UUID.randomUUID().toString().replaceAll("-","");
                    zteacher_schedule.setZid(tsuuid);
                    zteacher_schedule.setZteacherID(scheduleteacherid[y]);
                    o = zteacher_scheduleService.insert(zteacher_schedule);
                }
                //??????????????????????????????????????????
                for(int i=0;i<zid.length;i++){
                    //???????????????id???????????????ID??????????????????????????????????????????????????????
                        Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                        String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                        zstudent_schedule.setZid(uuid1);
                        zstudent_schedule.setZstudentID(zid[i]);
                        zstudent_schedule.setZscheduleID(uuid);
                        zstudent_schedule.setZstate("?????????");
                        int c = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                        //??????????????????????????????????????????
                        if(c>0){//
                            Zassign_schedule zassign_schedule = new Zassign_schedule();
                            zassign_schedule.setZstudentscheduleID(uuid1);
                            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                            zassign_schedule.setZpublishtime(timestamp);
                            int m = 0;
                            for(int s = 0;s<trainingtaskID.length;s++){
                                String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                                zassign_schedule.setZid(uuid2);
                                zassign_schedule.setZtrainingtaskID(trainingtaskID[s]);
                                m =zassign_scheduleService.insertzassignzschedule(zassign_schedule);
                            }

                        }
                }
                return "success";
            }
            return "?????????????????????" ;
        }else {//???????????????????????????????????????
            int j =0;
            Zschedule existschedule = null;
            for(Zschedule a :zscheduleList) {
                //????????????????????????????????????????????????
                if (a.getZtrainingroomID().equals(trainingroomID)) {
                    existschedule = a;
                    j++;
                }
            }
            if(j>0) {//????????????????????????????????????????????????????????????

                //????????????????????????????????????
                Zteacher_schedule zteacher_schedule = new Zteacher_schedule();
                zteacher_schedule.setZscheduleID(existschedule.getZid());
                for(int y =0;y<scheduleteacherid.length;y++){
                    String tsuuid = UUID.randomUUID().toString().replaceAll("-","");
                    zteacher_schedule.setZid(tsuuid);
                    zteacher_schedule.setZteacherID(scheduleteacherid[y]);
                    //????????????????????????????????????????????????????????????????????????
                    Zteacher_schedule zs = zteacher_scheduleService.findzteaschedule(existschedule.getZid(),scheduleteacherid[y]);
                    if(null == zs){
                        int q = zteacher_scheduleService.insert(zteacher_schedule);
                    }

                }
                //?????????????????????
                for(int i=0;i<zid.length;i++){
                    //????????????id???????????????ID??????????????????????????????????????????????????????
                    String studentschedule = zstudent_scheduleService.findstudentscheduleid(existschedule.getZid(),zid[i]);
                    System.out.println("studentschedule : "+studentschedule);
                    int c = 0;
                    String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                    if(null == studentschedule){//???????????????????????????????????????
                        Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                        zstudent_schedule.setZid(uuid1);
                        zstudent_schedule.setZstudentID(zid[i]);
                        zstudent_schedule.setZscheduleID(existschedule.getZid());
                        zstudent_schedule.setZstate("?????????");
                        c = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                    }

                    if(c>0){
                        Zassign_schedule zassign_schedule = new Zassign_schedule();
                        /*if(null ==studentschedule){//?????????????????????????????????????????????????????????id,????????????????????????id*/
                            zassign_schedule.setZstudentscheduleID(uuid1);
                       /* }else{
                            zassign_schedule.setZstudentscheduleID(studentschedule);
                        }*/
                        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                        zassign_schedule.setZpublishtime(timestamp);
                        int m = 0;
                        for(int v =0;v<trainingtaskID.length;v++){
                            String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                            zassign_schedule.setZid(uuid2);
                            zassign_schedule.setZtrainingtaskID(trainingtaskID[v]);
                            m =zassign_scheduleService.insertzassignzschedule(zassign_schedule);
                        }
                        if(m>0){
                            return "??????????????????";
                        }
                    }
                    //???????????????????????????????????????????????????
                    if(c ==0){
                        //studentschedule
                        Zassign_schedule zassign_schedule = new Zassign_schedule();
                        zassign_schedule.setZstudentscheduleID(studentschedule);
                        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                        zassign_schedule.setZpublishtime(timestamp);
                        int f = 0;
                        for(int v =0;v<trainingtaskID.length;v++){
                            String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                            zassign_schedule.setZid(uuid2);
                            zassign_schedule.setZtrainingtaskID(trainingtaskID[v]);
                            //???????????????????????????????????????????????????????????????
                            List<String> liststring = zassign_scheduleService.findtaskidbyscheduleid(studentschedule);
                            int z = 0;
                            for(int x =0;x<liststring.size();x++){
                                if(liststring.get(x).equals(trainingtaskID[v])){
                                    z++;
                                }
                            }
                            if(z == 0){
                                f =zassign_scheduleService.insertzassignzschedule(zassign_schedule);
                            }
                        }
                        if(f>0){
                            return "??????????????????";
                        }

                    }

                }

                  return "?????????????????????";
              }else {//?????????????????????????????????????????????
                    zschedule.setZid(uuid);
                    zschedule.setZcourseID(courseID);
                    zschedule.setZtrainingroomID(trainingroomID);
                    zschedule.setZstartdate(startdate);
                    zschedule.setZenddate(enddate);
                    int b = zscheuleService.addzschedule(zschedule);
                    //???????????????????????????id???
                    if(b>0){//??????????????????????????????

                        //????????????????????????????????????
                        Zteacher_schedule zteacher_schedule = new Zteacher_schedule();

                        zteacher_schedule.setZscheduleID(uuid);
                        int o =0;
                        for(int y =0;y<scheduleteacherid.length;y++){
                            String tsuuid = UUID.randomUUID().toString().replaceAll("-","");
                            zteacher_schedule.setZid(tsuuid);
                            zteacher_schedule.setZteacherID(scheduleteacherid[y]);
                            o = zteacher_scheduleService.insert(zteacher_schedule);
                        }

                        for(int i=0;i<zid.length;i++){
                                //????????????id???????????????ID??????????????????????????????????????????????????????
                                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                                String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                                zstudent_schedule.setZid(uuid1);
                                zstudent_schedule.setZstudentID(zid[i]);
                                zstudent_schedule.setZscheduleID(uuid);
                                zstudent_schedule.setZstate("?????????");
                                int c = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                                if(c>0){
                                    Zassign_schedule zassign_schedule = new Zassign_schedule();
                                    zassign_schedule.setZstudentscheduleID(uuid1);
                                    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
                                    zassign_schedule.setZpublishtime(timestamp);
                                    for(int v =0;v<trainingtaskID.length;v++){
                                        String uuid2 = UUID.randomUUID().toString().replaceAll("-","");
                                        zassign_schedule.setZid(uuid2);
                                        zassign_schedule.setZtrainingtaskID(trainingtaskID[v]);
                                        int m =zassign_scheduleService.insertzassignzschedule(zassign_schedule);
                                    }

                                }
                        }
                        return "success";
                    }
                    return "?????????????????????" ;
                }
            }


    }
}
