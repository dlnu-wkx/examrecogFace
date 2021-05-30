package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.service.ZechartsService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class ZechartsController {
    public final static Logger logger = LoggerFactory.getLogger(ZechartsController.class);

    @Autowired
    ZechartsService zechartsService;

    /**
     * 获取当前老师所在车间的ID
     *
     * @param session
     * @return
     */
    private static String getTrainRoomID(HttpSession session) {
        Zteacher_cookie zteacher_cookie = (Zteacher_cookie) session.getAttribute("zteacher_cookie");
        return zteacher_cookie.getZscheduleID();
    }

    /**
     * 获取当前的教师所在的教室名
     * @param session
     * @return
     */
    private static String getLocation(HttpSession session) {
        Zteacher_cookie zteacher_cookie = (Zteacher_cookie) session.getAttribute("zteacher_cookie");
        return zteacher_cookie.getZlocation();
    }

    /**
     * 获取当前教师的班级的班级id
     *
     * @param session
     * @return Zteacher_cookie中zGradeids属性
     */
    private static String getGradeId(HttpSession session) {
        Zteacher_cookie zteacher_cookie = (Zteacher_cookie) session.getAttribute("zteacher_cookie");
        return zteacher_cookie.getZgradeid();
    }


    /**
     * 获取当前教师的上课表id
     *
     * @param session
     * @return
     */
    private static String getScheduleId(HttpSession session) {
        Zteacher_cookie zteacher_cookie = (Zteacher_cookie) session.getAttribute("zteacher_cookie");
        return zteacher_cookie.getZscheduleID();
    }


    /**
     * 今年的数据
     *
     * @return
     */
    @RequestMapping(value = "/yeardata", method = RequestMethod.POST)
    @ResponseBody
    public int yearStatistics(HttpSession session) {
        int yeardata = zechartsService.yearTrainNumber(getTrainRoomID(session));
        return yeardata;
    }

    /**
     * 这个月的数据
     *
     * @return
     */
    @RequestMapping(value = "/monthdata", method = RequestMethod.POST)
    @ResponseBody
    public int monthStatistics(HttpSession session) {
        int monthdata = zechartsService.monthTrainNumber(getTrainRoomID(session));
        return monthdata;
    }

    /**
     * 本周的数据
     *
     * @return
     */
    @RequestMapping(value = "/weekdata", method = RequestMethod.POST)
    @ResponseBody
    public int weekStatistics(HttpSession session) {
        int weekdata = zechartsService.weekTrainNumber(getTrainRoomID(session));
        return weekdata;
    }

    /**
     * 今天的数据
     *
     * @return
     */
    @RequestMapping(value = "/todaydata", method = RequestMethod.POST)
    @ResponseBody
    public int todayStatistics(HttpSession session) {
        System.out.println(getTrainRoomID(session));
        int todaydata = zechartsService.todayTrainNumber(getTrainRoomID(session));
        return todaydata;
    }

    /**
     * 获取当前所在教室的车床使用比例
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/useNumber", method = RequestMethod.POST)
    @ResponseBody
    public int[] workshopUseRate(HttpSession session) {
        //数组里边分别存储实训中的人数，
        int[] useNumberArr = new int[3];

        //已登录
        int loginNumber = zechartsService.loginNumber(getLocation(session));
        //实训中
        int traingNumber = zechartsService.trainingNumber(getLocation(session));
        //空闲中
        int freeNumber = zechartsService.freeNumber(getLocation(session));

        useNumberArr[0] = loginNumber;
        useNumberArr[1] = traingNumber;
        useNumberArr[2] = freeNumber;

        return useNumberArr;
    }

    /**
     * 年、月、周的通过率
     *
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

        //数组中第一个表示年通过率，第二个表示月通过率，第三个表示周通过率，并将获取到的数据保留两位小数
        DecimalFormat df = new DecimalFormat("#######0.00");

        rateData[0] = Double.parseDouble(df.format(yearPassRate));
        rateData[1] = Double.parseDouble(df.format(monthPassRate));
        rateData[2] = Double.parseDouble(df.format(weekPassRate));

        return rateData;
    }

    //    获取当前上课教室的班级信息
    @RequestMapping(value = "/className", method = RequestMethod.POST)
    @ResponseBody
    public String getClassName(HttpSession session) {
        return zechartsService.getClassName(getGradeId(session));
    }

    //获取当前上课教室的班级的总人数
    @RequestMapping(value = "/classTotalNumber", method = RequestMethod.POST)
    @ResponseBody
    public int getTotalNumber(HttpSession session) {
        return zechartsService.getTotalNumber(getGradeId(session));
    }

    //获取当前上课教师的所到人数
    @RequestMapping(value = "/arrivedNumber", method = RequestMethod.POST)
    @ResponseBody
    public int getArrivedNumber(HttpSession session) {
        return zechartsService.getArrivedNumber(getScheduleId(session), getGradeId(session));
    }

    //计算学生签到率
    @RequestMapping(value = "/arrivedRate", method = RequestMethod.POST)
    @ResponseBody
    public double getArrivedRate(HttpSession session) {
        if (getTotalNumber(session) == 0 ){
            return 0;
        }
        DecimalFormat df = new DecimalFormat("#######0.0");
        double rate = Double.parseDouble(df.format(((double) getArrivedNumber(session) / getTotalNumber(session)) * 100));
        return rate;
    }

    //未签到的学生的姓名
    @RequestMapping(value = "/notArrivedStudents", method = RequestMethod.POST)
    @ResponseBody
    public String getNotArrivedStudents(HttpSession session) {
        //获得从查找到的未签到的学生姓名的列表
        List<String> notArrived = zechartsService.getNotArrivedStudents(getScheduleId(session), getGradeId(session));
        //将未签到的学生的姓名存储为一个字符串
        String notArrivedStudents = "";
        for (String item : notArrived) {
            notArrivedStudents += item + " ";
        }
        return notArrivedStudents;
    }
}
