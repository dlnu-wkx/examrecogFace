package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zauthority;
import com.itboyst.facedemo.dto.Ztempuser;
import com.itboyst.facedemo.service.ZauthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ZauthorityController {
    public final static Logger logger = LoggerFactory.getLogger(ZauthorityController.class);

    @Autowired
    ZauthorityService zauthorityService;


    @RequestMapping(value = "/findallauthority", method = RequestMethod.POST)
    @ResponseBody
    public List<Zauthority> findallauthority(HttpSession session) throws IOException {
        List<Zauthority>  zauthorityList = zauthorityService.findall();
        return zauthorityList;
    }
}
