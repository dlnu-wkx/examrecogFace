package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.service.ZteacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZteacherController {

    @Autowired
    ZteacherService zteacherService;

    @RequestMapping("/findallbyteachername")
    @ResponseBody
    public Zteacher findallbyteachername(String name){
        return zteacherService.selectteacherbyname(name);
    }

    @RequestMapping("/findteachernamelike")
    @ResponseBody
    public List<String> findteachernamelike(String name){
        return zteacherService.findteachernamelike(name);
    }

}
