package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.ZgradeService;
import com.itboyst.facedemo.service.Zteacher_measureService;
import com.itboyst.facedemo.service.Ztraining_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ZmeasureController {
    @Autowired
    ZgradeService zgradeService;

    @Autowired
    Ztraining_taskService ztraining_taskService;

    @Autowired
    Zteacher_measureService zteacher_measureService;

    @RequestMapping("/getallgrade")
    @ResponseBody
    public List<Zgrade> getallgrade(){
        //System.out.println(zgradeService.findallgrade());
        return zgradeService.findallgrade();
    }

    @RequestMapping("/getalltask")
    @ResponseBody
    public List<Ztraining_task> getalltask(){
        return ztraining_taskService.findalltask();
    }


    @RequestMapping("/getmeasurebygt")
    @ResponseBody
    public List<Zteacher_measure> getmeasurebygt(String zgradeID, String ztrainingtaskID,HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
       /* System.out.println(zgradeID+ztrainingtaskID);
        System.out.println(zteacher_measureService.findmeasurebygat(zgradeID,ztrainingtaskID));*/
        return zteacher_measureService.findmeasurebygat(zgradeID,ztrainingtaskID,zteacher_cookie.getZscheduleID());
    }

    @RequestMapping("/getmeasuremaxbyg")
    @ResponseBody
    public int getmeasuremaxbyg(String zgradeID, String ztrainingtaskID,HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");
        return zteacher_measureService.findmeasurebygatgroupid(zgradeID, ztrainingtaskID,zteacher_cookie.getZscheduleID());
    }

}
