package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.dto.Ztesting_score;
import com.itboyst.facedemo.service.Ztesting_scoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class Ztesting_scoreController {
    @Autowired
    Ztesting_scoreService  ztesting_scoreService;


    @RequestMapping("/insertscore")
    @ResponseBody
    public int insertscore(int zscore, HttpSession session, HttpServletRequest request){

        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie");
        Ztesting_score ztesting_score=new Ztesting_score();

        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        ztesting_score.setZid(uuid);
        ztesting_score.setZstudentscheduleID(zstudent_cookie.getZstudent_scheduleid());
        String ip= Iputil.getClientIpAddress(request);
        ztesting_score.setZrecognizeIP(ip);
        ztesting_score.setZscore(zscore);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        ztesting_score.setZchecktime(timestamp);

        return ztesting_scoreService.insertscore(ztesting_score);
    }
}
