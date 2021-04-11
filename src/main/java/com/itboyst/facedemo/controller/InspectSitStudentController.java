package com.itboyst.facedemo.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.ZstrangeService;
import com.itboyst.facedemo.service.ZsysconfigService;
import com.itboyst.facedemo.service.impl.InspectSitStudentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class InspectSitStudentController {
    public final static Logger logger = LoggerFactory.getLogger(InspectSitStudentController.class);
    @Autowired
    InspectSitStudentService inspectSitStudentService;

    @Autowired
    ZsysconfigService zsysconfigService;

    @Autowired
    ZstrangeService zstrangeService;


    /**
     * 人脸识别的数据处理方法
     * @param session
     * @param mytime
     * @param zcheck
     * @param renliandata
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/InspectSitStudent", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> InspectSitStudent(HttpSession session,String mytime,String zcheck,@RequestParam(value = "renliandata") String renliandata) throws IOException, ParseException {


        JSONArray json = JSONArray.fromObject(renliandata);
        //System.out.println("InspectSitStudent 的json : "+json);

        /*List<InspectSitStudent> list = (List<InspectSitStudent>) JSONArray.toCollection(json,InspectSitStudent.class);*/
        /*List<InspectSitStudent> list =  JSONArray.toList(json,new InspectSitStudent(),new JsonConfig());*/

        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID ="";
        if(zteacher_cookie !=null){
            ztrainingroomID = zteacher_cookie.getZtrainingroomid();
        }
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(mytime!=null){
            Long time =Long.parseLong(mytime);
            timestamp.setTime(time);
        }

        if(zcheck.equals("人脸识别")) {
            Zsysconfig zsysconfig = zsysconfigService.findIPByZname("杰视服务器IP地址");
            String jieshiip = zsysconfig.getZvalue();
            /*ceshisocket ceshisocket = new ceshisocket();
            ceshisocket.getClient("ws://" + jieshiip + ":8080/webapi/websocket");*/
            List<InspectSitStudent> list = null;
            if(null != json){
                list = new ArrayList<>();
                for(int i=0;i<json.size();i++){
                    JSONObject jsonObject = (JSONObject) json.get(i);
                    InspectSitStudent iss= new InspectSitStudent();
                    String zstudentID = (String) jsonObject.get("zstudentID");
                    String zgradeName = (String) jsonObject.get("zgradeName");
                    Timestamp timestampsssss = dealDateFormat((String)jsonObject.get("zrecognizetime"));

                    String ztrainingroomID11 = "1111111111111";
                    if(!jsonObject.get("ztrainingroomID").equals(null)){
                        ztrainingroomID11 = (String)jsonObject.get("ztrainingroomID");
                    }
                    String originalPictureUrl = (String) jsonObject.get("originalPictureUrl");
                    String zname = (String) jsonObject.get("zname");
                    iss.setZstudentID(zstudentID);
                    iss.setZgradeName(zgradeName);
                    iss.setZrecognizetime(timestampsssss);
                    iss.setZtrainingroomID(ztrainingroomID11);
                    iss.setOriginalPictureUrl(originalPictureUrl);
                    iss.setZName(zname);
                    list.add(iss);
                }
            }



            List<InspectSitStudent>  zstudentList = new ArrayList<>();
            zstudentList =inspectSitStudentService.findStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);
            //教师的信息
            List<InspectSitTeacher> zteacherList = new ArrayList<>();
             zteacherList =  inspectSitStudentService.findTeacherByDateAndTrainingIDdistinct(jieshiip,timestamp,zcheck);
            if(CollectionUtil.isNotEmpty(zteacherList)){

                for(InspectSitTeacher a:zteacherList){
                    InspectSitStudent inspectSitStudent =new InspectSitStudent();
                    inspectSitStudent.setZgradeName("教师");
                    inspectSitStudent.setZName(a.getZName());
                    inspectSitStudent.setZstudentID(a.getZteacherID());
                    inspectSitStudent.setZrecognizetime(a.getZrecognizetime());
                    inspectSitStudent.setOriginalPictureUrl(a.getOriginalPictureUrl());
                    //把老师的信息变形添加到学生数组中
                    zstudentList.add(inspectSitStudent);
                }
            }

            List<InspectSitStudent> relist = new ArrayList<>();
                relist.addAll(zstudentList);
            //10分钟后相同和新增的人员也要显示出来
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0;i<zstudentList.size();i++){

                    for(int j=0;j<list.size();j++){
                        String sid =zstudentList.get(i).getZstudentID();
                        Long time1 = zstudentList.get(i).getZrecognizetime().getTime();
                        if(sid.equals(list.get(j).getZstudentID())){
                            Long time2 = list.get(j).getZrecognizetime().getTime();
                            int a = (int) ((time1-time2)/60000);
                            if(a>=10){//大于10分钟则保留在relist中
                                relist.add(list.get(j));
                            }else{//否则先从relist中移除，然后加上页面上的
                                relist.remove(zstudentList.get(i));
                                relist.add(list.get(j));
                            }
                        }
                    }

                }
            }

            //把所有的人员进行降序处理
            if(relist.size()>1){
                Collections.sort(relist, new Comparator<InspectSitStudent>() {
                    @Override
                    public int compare(InspectSitStudent o1, InspectSitStudent o2) {
                        long aim =o1.getZrecognizetime().getTime()-o2.getZrecognizetime().getTime();
                        if(aim<0){
                            return 1;
                        }else if(aim>0){
                            return -1;
                        }
                        return 0;
                    }
                });
            }

            /*for(InspectSitStudent is:zstudentList){
                System.out.println("is : "+is);
            }*/

            return relist;
        }

        List<InspectSitStudent>  zstudentList = new ArrayList<>();

        zstudentList =inspectSitStudentService.inspectfindStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck);

        return zstudentList;
    }

    /**
     * 签到的数据处理方法
     * @param session
     * @param mytime
     * @param zcheck
     * @param zid
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "/signInInspectSitStudent", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> signInInspectSitStudent(HttpSession session,String mytime,String zcheck,String zid) throws IOException, ParseException {

        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID ="";
        if(zteacher_cookie !=null){
            ztrainingroomID = zteacher_cookie.getZtrainingroomid();
        }
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(mytime!=null){
            Long time =Long.parseLong(mytime);
            timestamp.setTime(time);
        }

        List<InspectSitStudent>  zstudentList = new ArrayList<>();

        zstudentList =inspectSitStudentService.signIninspectfindStudentByDateAndTrainingId(ztrainingroomID,timestamp,zcheck,zid);

        return zstudentList;
    }




        /**
         * 人脸识别和查岗显示界面中左下侧显示的全部识别人员
         * @param session
         * @param mytime
         * @param zcheck
         * @return
         * @throws IOException
         * @throws ParseException
         */

    @RequestMapping(value = "/InspectSitStudentandTeacher", method = RequestMethod.POST)
    @ResponseBody
    public List<InspectSitStudent> InspectSitStudentandTeacher(HttpSession session,String mytime,String zcheck) throws IOException, ParseException {

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(mytime!=null){
            Long time =Long.parseLong(mytime);
            timestamp.setTime(time);
        }
        List<InspectSitStudent>  zstudentList = new ArrayList<>();
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        if(zcheck.equals("人脸识别")){
            zstudentList =inspectSitStudentService.findStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp,zcheck);
        }
        if(zcheck.equals("查岗")){
            zstudentList = inspectSitStudentService.inspectfindStudentByDateAndTrainingIdASC(ztrainingroomID,timestamp,zcheck);
            return zstudentList;
        }
        //查找摄像头识别成功的教师
        Zsysconfig zsysconfig =zsysconfigService.findIPByZname("杰视服务器IP地址");
        String jieshiip =zsysconfig.getZvalue();
        List<InspectSitTeacher> zteacherList =inspectSitStudentService.findTeacherByDateAndTrainingIdASC(jieshiip,timestamp,zcheck);
        if(CollectionUtil.isNotEmpty(zteacherList)){

            for(int i =0;i<zteacherList.size();i++){
                InspectSitStudent inspectSitStudent =new InspectSitStudent();
                inspectSitStudent.setZgradeName("教师");
                inspectSitStudent.setZName(zteacherList.get(i).getZName());
                inspectSitStudent.setZstudentID(zteacherList.get(i).getZteacherID());
                inspectSitStudent.setZrecognizetime(zteacherList.get(i).getZrecognizetime());
                inspectSitStudent.setOriginalPictureUrl(zteacherList.get(i).getOriginalPictureUrl());
                //把老师的信息变形添加到学生数组中
                zstudentList.add(inspectSitStudent);
            }
        }
        //把陌生人的信息也添加进来
        List<Zstrange> zstrangeslist = zstrangeService.findAll(jieshiip,timestamp,zcheck);
        if(CollectionUtil.isNotEmpty(zstrangeslist)){
            for(Zstrange a:zstrangeslist){
                InspectSitStudent inspectSitStudent =new InspectSitStudent();
                inspectSitStudent.setZgradeName("陌生人");
                inspectSitStudent.setZName(a.getZname());
                inspectSitStudent.setZrecognizetime(a.getZrecognizetime());
                inspectSitStudent.setOriginalPictureUrl(a.getOriginalPictureUrl());
                zstudentList.add(inspectSitStudent);
            }
        }
                //最后把学生的信息和变形后的教师信息一起排序
        if(zstudentList.size()>1){
            Collections.sort(zstudentList, new Comparator<InspectSitStudent>() {
                @Override
                public int compare(InspectSitStudent o1, InspectSitStudent o2) {
                    long aim =o1.getZrecognizetime().getTime()-o2.getZrecognizetime().getTime();
                    if(aim<0){
                        return 1;
                    }else if(aim>0){
                        return -1;
                    }
                    return 0;
                }
            });
        }

        return zstudentList;
    }

    public static Timestamp dealDateFormat(String oldDate) {
        Timestamp timeStamp = null;
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(date);
            timeStamp = Timestamp.valueOf(str);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return timeStamp;
    }

}
