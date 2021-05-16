package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.service.ZechartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZechartsController {
    public final static Logger logger = LoggerFactory.getLogger(ZechartsController.class);

    @Autowired
    ZechartsService zechartsService;

    /**
     * 今年的数据
     * @return
     */
    @RequestMapping(value = "/yeardata", method = RequestMethod.POST)
    @ResponseBody
    public int yearStatistics(){
       int yeardata =  zechartsService.yearStatistics();
        return yeardata;
    }

    /**
     * 这个月的数据
     * @return
     */
    @RequestMapping(value = "/monthdata", method = RequestMethod.POST)
    @ResponseBody
    public int monthStatistics(){
        int monthdata =  zechartsService.monthStatistics();
        return monthdata;
    }

    /**
     * 本周的数据
     * @return
     */
    @RequestMapping(value = "/weekdata", method = RequestMethod.POST)
    @ResponseBody
    public int weekStatistics(){
        int weekdata =  zechartsService.weekStatistics();
        return weekdata;
    }

    /**
     * 今天的数据
     * @return
     */
    @RequestMapping(value = "/todaydata", method = RequestMethod.POST)
    @ResponseBody
    public int todayStatistics(){
        int todaydata =  zechartsService.todayStatistics();
        return todaydata;
    }
}
