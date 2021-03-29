package com.itboyst.facedemo.controller;

import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.itboyst.facedemo.domain.UserFaceInfo;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.core.codec.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ZtempuserController {
    public final static Logger logger = LoggerFactory.getLogger(ZtempuserController.class);

    @Autowired
    ZtempuserService ztempuserService;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    ZstudentService zstudentService;

    @Autowired
    ZmajorService zmajorService;

    @Autowired
    ZgradeService zgradeService;

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    ZscheuleService zscheuleService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;

    @Autowired
    Ztraining_facilityService ztrinfser;

    @Autowired
    FaceEngineService faceEngineService;

    @RequestMapping(value = "/findAllztempuser", method = RequestMethod.POST)
    @ResponseBody
    public List<Ztempuser> findAllztempuser(HttpSession session) throws IOException {
        List<Ztempuser>  ztempuserList = ztempuserService.findAlltempuserbytime();
        return ztempuserList;
    }


    @RequestMapping(value = "/addtempuser", method = RequestMethod.POST)
    @ResponseBody
    public int  addtempuser(String zid,String authorityID,String zname) throws IOException, InterruptedException {

        //存储临时人员的信息到人脸数据库中
        Ztempuser ztempuser = ztempuserService.findByzid(zid);
        //如果包含ztempuser则存到人脸数据库中
        if(ztempuser.getOriginalPictureUrl().contains("ztempuser")){
            String file = GetImageStr(ztempuser.getOriginalPictureUrl());
            byte[] decode = Base64.decode(base64Process(file));
            BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
            ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
            //人脸特征获取
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
            UserFaceInfo userFaceInfo = new UserFaceInfo();
            userFaceInfo.setGroupId(101);
            userFaceInfo.setFaceFeature(bytes);
            //从人脸表中查找自增键的值作为face_id
            int num = userFaceInfoService.findAll();
            int number = num;
            String face_id = "L" + number;
            //用学号或工号与face表相连
            userFaceInfo.setFaceId(face_id);
            userFaceInfo.setPath(ztempuser.getOriginalPictureUrl());
            int e = userFaceInfoService.findcountfaceid(face_id);
            //通过工号查找到没有记录则添加一条记录;
            if (e <= 0) {
                userFaceInfoService.insertSelective(userFaceInfo);
            }
        }

        String faceid=userFaceInfoService.selectfaceidbyfpath(ztempuser.getOriginalPictureUrl());
        int faceinfoid = userFaceInfoService.findidbyfaceid(faceid);
        String IP = ztempuser.getZrecognizeIP();
        Ztraining_facility ztrfac = ztrinfser.findbyip(IP);
        //当有权限信息是存储教师的相关信息
        if(""!=authorityID){
            Zteacher zteacher = new Zteacher();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            zteacher.setZid(uuid);
            //设置临时的专业
            String majorzid = zmajorService.findzidbyzname("临时专业");
            zteacher.setZmajorID(majorzid);
            zteacher.setZidentity(faceid);
            zteacher.setZauthorityID(authorityID);
            zteacher.setZpass("666");
            zteacher.setZname(zname);
            zteacher.setZphone("13888888888");
            zteacher.setZsex("男");
            zteacher.setZphoto(ztempuser.getOriginalPictureUrl());
            zteacher.setZfaceinfoID(faceinfoid);
            Zteacher  old = zteacherService.findteacherByzidentity(faceid);
            //当表中已经存在该老师的信息时则无法添加
            if(null == old){
                int b =zteacherService.addtempteacher(zteacher);
                //更新临时人员信息
                int a =ztempuserService.update(zid,zname);
                return b;
            }

        }else{//没有权限的就为学生,分两种情况讨论一种是已经注册但是没课的
            Zstudent oldstu= zstudentService.findstudentByZidentity(faceid);
            if(null == oldstu){//如果不存在则新建一条学生记录
                Zstudent zstudent = new Zstudent();
                String stuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent.setZid(stuuuid);
                String gradezid = zgradeService.findzidbyzname("临时班级");
                zstudent.setZgradeID(gradezid);
                zstudent.setZidentity(faceid);
                zstudent.setZpass("888");
                zstudent.setZname(zname);
                zstudent.setZphone("12345678911");
                zstudent.setZsex("男");
                zstudent.setZfaceinfoID(faceinfoid);
                zstudent.setZphoto(ztempuser.getOriginalPictureUrl());
                zstudent.setZstatus("审核通过");

                String zscheduleid =zscheuleService.findidbycourename("临时课程",ztrfac.getZtrainingroomID());
                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                String zstudent_schedulestuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent_schedule.setZid(zstudent_schedulestuuuid);
                zstudent_schedule.setZscheduleID(zscheduleid);
                zstudent_schedule.setZstudentID(stuuuid);
                zstudent_schedule.setZstate("未上课");
                int c = zstudentService.registerstud(zstudent);
                int n = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);

               //更新临时人员信息
                int a =ztempuserService.update(zid,zname);
               return c;
            }else{//不是临时人员则只添加课程
                String zscheduleid =zscheuleService.findidbycourename("临时课程",ztrfac.getZtrainingroomID());
                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                String zstudent_schedulestuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent_schedule.setZid(zstudent_schedulestuuuid);
                zstudent_schedule.setZscheduleID(zscheduleid);
                zstudent_schedule.setZstudentID(oldstu.getZid());
                zstudent_schedule.setZstate("未上课");
                int i = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                return i;
            }


        }
        return 0;
    }


    /**
     * 将图片解析成字节数组
     * @param imgFile
     * @return
     */
    public static String GetImageStr(String imgFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        return Base64.encode(data);//返回Base64编码过的字节数组字符串
    }

    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }

            return base64Str;
        } else {
            return "";
        }
    }


}
