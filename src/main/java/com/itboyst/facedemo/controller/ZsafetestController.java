package com.itboyst.facedemo.controller;


import com.itboyst.facedemo.dto.Zstudent_cookie;
import com.itboyst.facedemo.service.Ztesting_inputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ZsafetestController {
    public final static Logger logger = LoggerFactory.getLogger(ZsafetestController.class);


    @Autowired
    Ztesting_inputService ztesting_inputService;

    @RequestMapping("/starttest")
    @ResponseBody
    public  void starttest(Model model,HttpSession session){
     // System.out.println(session.getAttribute("zstudent_cookie"));

      Zstudent_cookie zstudentCookie =(Zstudent_cookie) session.getAttribute("zstudent_cookie");

      String zstudentscheduleID=zstudentCookie.getZstudent_scheduleid();

      int  i =ztesting_inputService.deletebystscheid(zstudentscheduleID);

    }

   /* @RequestMapping("/findtestnumber")
    @ResponseBody
    public int findtestnumber(HttpSession session){
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie)session.getAttribute("zstudent_cookie");
        System.err.println("zstudent_cookie"+zstudent_cookie);
        return zstudent_cookie.getZsafetestingNum();
    }*/

}
