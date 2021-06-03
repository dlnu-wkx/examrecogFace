package com.itboyst.facedemo.controller;


import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Zstudent_loginController {
    @Autowired
    Zstudent_loginService zstudent_loginService;

    @RequestMapping("/updatestatusbout")
    @ResponseBody
    public int updatestatusbout(HttpSession session){
        Zstudent_login zstudent_login=(Zstudent_login)session.getAttribute("zstudent_login");
        return zstudent_loginService.updateloginbyzid(zstudent_login.getZid(),"退出");
    }


    @RequestMapping("/updatestatusbout2")
    @ResponseBody
    public int updatestatusbout2(HttpSession session){
        Zstudent_login zstudent_login=(Zstudent_login)session.getAttribute("zstudent_login");
        return zstudent_loginService.updateloginbyzid(zstudent_login.getZid(),"正常");
    }


}
