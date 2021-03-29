package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zrecord;
import com.itboyst.facedemo.dto.Zstudent;
import com.itboyst.facedemo.dto.Zstudent_login;
import com.itboyst.facedemo.service.ZstudentService;
import com.itboyst.facedemo.service.Zstudent_loginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckStudentLogin {
    public final static Logger logger = LoggerFactory.getLogger(CheckStudentLogin.class);
    @Autowired
    Zstudent_loginService zstudentLoginService;
    @Autowired
    ZstudentService zstudentService;

    @RequestMapping(value = "/checkStudentLogin", method = RequestMethod.POST)
    @ResponseBody
    public List<Zstudent> checkStudentLogin(HttpSession session) throws IOException {
        List<Zstudent>  zstudentList = new ArrayList<>();
        List<Zstudent_login>  zstudentLoginList =zstudentLoginService.findAllStudentLogin();
        for(Zstudent_login  list : zstudentLoginList){
            String zstudentID = list.getZstudentID();
            Zstudent zstudent =zstudentService.findStudentById(zstudentID);
            zstudentList.add(zstudent);
        }
        return zstudentList;
    }
}
