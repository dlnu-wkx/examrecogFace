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

        //????????????????????????????????????????????????
        Ztempuser ztempuser = ztempuserService.findByzid(zid);
        //????????????ztempuser???????????????????????????
        if(ztempuser.getOriginalPictureUrl().contains("ztempuser")){
            String file = GetImageStr(ztempuser.getOriginalPictureUrl());
            byte[] decode = Base64.decode(base64Process(file));
            BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
            ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
            //??????????????????
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
            UserFaceInfo userFaceInfo = new UserFaceInfo();
            userFaceInfo.setGroupId(101);
            userFaceInfo.setFaceFeature(bytes);
            //??????????????????????????????????????????face_id
            int num = userFaceInfoService.findAll();
            int number = num+1;
            String face_id = "L" + number;
            //?????????????????????face?????????
            userFaceInfo.setFaceId(face_id);
            userFaceInfo.setPath(ztempuser.getOriginalPictureUrl());
            int e = userFaceInfoService.findcountfaceid(face_id);
            //??????????????????????????????????????????????????????;
            if (e <= 0) {
                userFaceInfoService.insertSelective(userFaceInfo);
            }
        }

        String faceid=userFaceInfoService.selectfaceidbyfpath(ztempuser.getOriginalPictureUrl());
        int faceinfoid = userFaceInfoService.findidbyfaceid(faceid);
        String IP = ztempuser.getZrecognizeIP();
        Ztraining_facility ztrfac = ztrinfser.findbyip(IP);
        //????????????????????????????????????????????????
        if(""!=authorityID){
            Zteacher zteacher = new Zteacher();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            zteacher.setZid(uuid);
            //?????????????????????
            String majorzid = zmajorService.findzidbyzname("????????????");
            zteacher.setZmajorID(majorzid);
            zteacher.setZidentity(faceid);
            zteacher.setZauthorityID(authorityID);
            zteacher.setZpass("666");
            zteacher.setZname(zname);
            zteacher.setZphone("13888888888");
            zteacher.setZsex("???");
            zteacher.setZphoto(ztempuser.getOriginalPictureUrl());
            zteacher.setZfaceinfoID(faceinfoid);
            Zteacher  old = zteacherService.findteacherByzidentity(faceid);
            //?????????????????????????????????????????????????????????
            if(null == old){
                int b =zteacherService.addtempteacher(zteacher);
                //????????????????????????
                int a =ztempuserService.update(zid,zname);
                return b;
            }

        }else{//???????????????????????????,?????????????????????????????????????????????????????????
            Zstudent oldstu= zstudentService.findstudentByZidentity(faceid);
            if(null == oldstu){//??????????????????????????????????????????
                Zstudent zstudent = new Zstudent();
                String stuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent.setZid(stuuuid);
                String gradezid = zgradeService.findzidbyzname("????????????");
                zstudent.setZgradeID(gradezid);
                zstudent.setZidentity(faceid);
                zstudent.setZpass("888");
                zstudent.setZname(zname);
                zstudent.setZphone("12345678911");
                zstudent.setZsex("???");
                zstudent.setZfaceinfoID(faceinfoid);
                zstudent.setZphoto(ztempuser.getOriginalPictureUrl());
                zstudent.setZstatus("????????????");

                String zscheduleid =zscheuleService.findidbycourename("????????????",ztrfac.getZtrainingroomID());
                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                String zstudent_schedulestuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent_schedule.setZid(zstudent_schedulestuuuid);
                zstudent_schedule.setZscheduleID(zscheduleid);
                zstudent_schedule.setZstudentID(stuuuid);
                zstudent_schedule.setZstate("?????????");
                int c = zstudentService.registerstud(zstudent);
                int n = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);

               //????????????????????????
                int a =ztempuserService.update(zid,zname);
               return c;
            }else{//????????????????????????????????????
                String zscheduleid =zscheuleService.findidbycourename("????????????",ztrfac.getZtrainingroomID());
                Zstudent_schedule zstudent_schedule = new Zstudent_schedule();
                String zstudent_schedulestuuuid = UUID.randomUUID().toString().replaceAll("-","");
                zstudent_schedule.setZid(zstudent_schedulestuuuid);
                zstudent_schedule.setZscheduleID(zscheduleid);
                zstudent_schedule.setZstudentID(oldstu.getZid());
                zstudent_schedule.setZstate("?????????");
                int i = zstudent_scheduleService.addzstudentSchedule(zstudent_schedule);
                return i;
            }


        }
        return 0;
    }


    /**
     * ??????????????????????????????
     * @param imgFile
     * @return
     */
    public static String GetImageStr(String imgFile)
    {//???????????????????????????????????????????????????????????????Base64????????????
        InputStream in = null;
        byte[] data = null;
        //????????????????????????
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
        //???????????????Base64??????
        return Base64.encode(data);//??????Base64?????????????????????????????????
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
