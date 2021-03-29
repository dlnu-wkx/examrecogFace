package com.itboyst.facedemo.controller;

import cn.hutool.core.codec.Base64;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.ZrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class ZrecordController {
    public final static Logger logger = LoggerFactory.getLogger(ZrecordController.class);
    @Autowired
    ZrecordService zrecordService;



    @RequestMapping(value = "/zrecord", method = RequestMethod.POST)
    @ResponseBody
    public int insertZrecord(HttpSession session,String ztype,String zcontent) throws IOException {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        Zrecord zrecord = new Zrecord();
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        zrecord.setZid(uuid);
        zrecord.setZscheduleID(zteacher_cookie.getZscheduleID());
        zrecord.setZteacherID(zteacher_cookie.getZteacherid());
        zrecord.setZdate(timestamp);
        zrecord.setZtype(ztype);
        zrecord.setZcontent(zcontent);
        int i =zrecordService.insertZrecord(zrecord);
        if(i==1){
            return 1;
        }
        return 0;
    }
}
