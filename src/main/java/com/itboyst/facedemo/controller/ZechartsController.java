package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.service.ZechartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ZechartsController {
    public final static Logger logger = LoggerFactory.getLogger(ZechartsController.class);

    @Autowired
    ZechartsService zechartsService;

    /**
     * 今年的数据
     *
     * @return
     */
    @RequestMapping(value = "/yeardata", method = RequestMethod.POST)
    @ResponseBody
    public int yearStatistics() {
        int yeardata = zechartsService.yearStatistics();

        return yeardata;
    }

    /**
     * 这个月的数据
     *
     * @return
     */
    @RequestMapping(value = "/monthdata", method = RequestMethod.POST)
    @ResponseBody
    public int monthStatistics() {
        int monthdata = zechartsService.monthStatistics();
        return monthdata;
    }

    /**
     * 本周的数据
     *
     * @return
     */
    @RequestMapping(value = "/weekdata", method = RequestMethod.POST)
    @ResponseBody
    public int weekStatistics() {
        int weekdata = zechartsService.weekStatistics();
        return weekdata;
    }

    /**
     * 今天的数据
     *
     * @return
     */
    @RequestMapping(value = "/todaydata", method = RequestMethod.POST)
    @ResponseBody
    public int todayStatistics() {
        int todaydata = zechartsService.todayStatistics();
        return todaydata;
    }

    /**
     * 获取当前所在教室的车床使用比例
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/useRate", method = RequestMethod.POST)
    @ResponseBody
    public double[] workshopUseRate(HttpServletRequest request) {
        String ip4 = Iputil.getClientIpAddress(request);
        double[] useRateArr = new double[3];
        int trainingData = zechartsService.trainingNumber(ip4);
        int leaveData = zechartsService.leaveNumber(ip4);
        int exitData = zechartsService.exitNumber(ip4);

        //实训中的占比
        double trainingRate = ((double) trainingData / (trainingData + leaveData + exitData)) * 100;
        //闲置中的占比
        double leaveRate = ((double) leaveData / (trainingData + leaveData + exitData)) * 100;
        //退出系统的占比
        double exitRate = ((double) exitData / (trainingData + leaveData + exitData)) * 100;

        useRateArr[0] = trainingRate;
        useRateArr[1] = leaveRate;
        useRateArr[2] = exitRate;
        return useRateArr;
    }

    /**
     * 年、月、周的通过率
     * @return
     */
    @RequestMapping(value = "/passRate", method = RequestMethod.POST)
    @ResponseBody
    public double[] testPassRate() {
        double[] rateData = new double[3];
        //年月周的通过的人数
        int yearPassData = zechartsService.yearTestPassNumber();
        int monthPassData = zechartsService.monthTestPassNumber();
        int weekPassData = zechartsService.weekTestPassNumber();

        //年月周的参加测试的总人数
        int yearTotalData = zechartsService.yearTestTotalNumber();
        int monthTotalData = zechartsService.monthTestTotalNumber();
        int weekTotalData = zechartsService.weekTestTotalNumber();

        //年月日的测试的通过率
        double yearPassRate = 0.0;
        double monthPassRate = 0.0;
        double weekPassRate = 0.0;


        if (yearTotalData != 0) {
            yearPassRate = ((double) yearPassData / yearTotalData) * 100;
        }
        if (monthTotalData != 0) {
            monthPassRate = ((double) monthPassData / monthTotalData) * 100;
        }

        if (weekTotalData != 0) {
            weekPassRate = ((double) weekPassData / weekTotalData) * 100;
        }

        //数组中第一个表示年通过率，第二个表示月通过率，第三个表示周通过率
        rateData[0] = yearPassRate;
        rateData[1] = monthPassRate;
        rateData[2] = weekPassRate;

        return rateData;
    }
}
