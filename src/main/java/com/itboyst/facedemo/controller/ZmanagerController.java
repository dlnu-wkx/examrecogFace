package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zmanager;
import com.itboyst.facedemo.service.ZmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZmanagerController {
    @Autowired
    ZmanagerService zmanagerService;

    @RequestMapping("/findallbymeangername")
    @ResponseBody
    public Zmanager findallbymeangername(String name){
        return zmanagerService.findallbymanagername(name);
    }


    @RequestMapping("/findmanagernamelike")
    @ResponseBody
    public List<String> findmanagernamelike(String name){return zmanagerService.findmanagernamelike(name);}

}
