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

        if(CollectionUtil.isEmpty(zscheduleList)){//首先根据开始时间判断该段时间内有无上课表
            zschedule.setZid(uuid);
            zschedule.setZcourseID(courseID);
            zschedule.setZtrainingroomID(trainingroomID);
            zschedule.setZstartdate(startdate);
            zschedule.setZenddate(enddate);
            int a = zscheuleService.addzschedule(zschedule);

            //给每个学生和教师插入新的课表
            if(a>0){

                //为教师新建一个教师上课表
                Zteacher_schedule zteacher_schedule = new Zteacher_schedule();
                zteacher_schedule.setZscheduleID(uuid);
                int o =0;
                for(int y =0;y<scheduleteacherid.length;y++){
                    String tsuuid = UUID.randomUUID().toString().replaceAll("-","");
                    zteacher_schedule.setZid(tsuuid);
                    zteacher_schedule.setZteacherID(scheduleteacherid[y]);
                    o = zteacher_scheduleService.insert(zteacher_schedule);
                }
                //为每个学生增加一个学生上课表
                for(int i=0;i<zid.length;i++){
                    //当根据学生id和上课表的ID为空时则为该位学生新建一个学生上课表
                        Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                        String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                        zstudent_schedule.setZid(uuid1);
                        zstudent_schedule.setZstudentID(zid[i]);
                        zstudent_schedule.setZscheduleID(uuid);
                        zstudent_schedule.setZstate("未上课");
                        int c = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                        //为每位学生添加上课学生任务表
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
            return "新建上课表失败" ;
        }else {//如果有课比对实训室是否相同
            int j =0;
            Zschedule existschedule = null;
            for(Zschedule a :zscheduleList) {
                //时间相同时比对实训室是否是同一间
                if (a.getZtrainingroomID().equals(trainingroomID)) {
                    existschedule = a;
                    j++;
                }
            }
            if(j>0) {//说明该实训室有课，不同班级也可以添加课程

                //为教师新建一个教师上课表
                Zteacher_schedule zteacher_schedule = new Zteacher_schedule();
                zteacher_schedule.setZscheduleID(existschedule.getZid());
                for(int y =0;y<scheduleteacherid.length;y++){
                    String tsuuid = UUID.randomUUID().toString().replaceAll("-","");
                    zteacher_schedule.setZid(tsuuid);
                    zteacher_schedule.setZteacherID(scheduleteacherid[y]);
                    //如果该教师没有课程则给该教师添加一节课否则不添加
                    Zteacher_schedule zs = zteacher_scheduleService.findzteaschedule(existschedule.getZid(),scheduleteacherid[y]);
                    if(null == zs){
                        int q = zteacher_scheduleService.insert(zteacher_schedule);
                    }

                }
                //为学生添加课程
                for(int i=0;i<zid.length;i++){
                    //根据学生id和上课表的ID为空时则为该位学生新建一个学生上课表
                    String studentschedule = zstudent_scheduleService.findstudentscheduleid(existschedule.getZid(),zid[i]);
                    System.out.println("studentschedule : "+studentschedule);
                    int c = 0;
                    String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                    if(null == studentschedule){//如果上课表中没课则添加课程
                        Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                        zstudent_schedule.setZid(uuid1);
                        zstudent_schedule.setZstudentID(zid[i]);
                        zstudent_schedule.setZscheduleID(existschedule.getZid());
                        zstudent_schedule.setZstate("未上课");
                        c = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                    }

                    if(c>0){
                        Zassign_schedule zassign_schedule = new Zassign_schedule();
                        if(null ==studentschedule){//如果没有课程直接添加新建的上课学生表的id,否则添加查询到的id
                            zassign_schedule.setZstudentscheduleID(uuid1);
                        }else{
                            zassign_schedule.setZstudentscheduleID(studentschedule);
                        }
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
                            return "success";
                        }
                    }

                }

                  return "新建上课表失败";
              }else {//如果实训室不同则新建一个上课表
                    zschedule.setZid(uuid);
                    zschedule.setZcourseID(courseID);
                    zschedule.setZtrainingroomID(trainingroomID);
                    zschedule.setZstartdate(startdate);
                    zschedule.setZenddate(enddate);
                    int b = zscheuleService.addzschedule(zschedule);
                    //返回刚才插入记录的id值
                    if(b>0){//已经新建了一张上课表

                        //为教师新建一个教师上课表
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
                                //根据学生id和上课表的ID为空时则为该位学生新建一个学生上课表
                                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                                String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
                                zstudent_schedule.setZid(uuid1);
                                zstudent_schedule.setZstudentID(zid[i]);
                                zstudent_schedule.setZscheduleID(uuid);
                                zstudent_schedule.setZstate("未上课");
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
                    return "新建上课表失败" ;
                }
            }


    }
}
