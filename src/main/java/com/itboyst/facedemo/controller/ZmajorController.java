package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zmajor;
import com.itboyst.facedemo.service.ZmajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ZmajorController {
    @Autowired
    ZmajorService zmajorService;

    @RequestMapping("/findallmajor")
    @ResponseBody
    public List<Zmajor> findallmajor(){
        return zmajorService.findallmajor();
    }

}
