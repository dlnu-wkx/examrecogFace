package com.itboyst.facedemo.controller;

import cn.hutool.core.codec.Base64;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.itboyst.facedemo.base.Fileutil;
import com.itboyst.facedemo.base.Powerutil;
import com.itboyst.facedemo.base.ScreenUtil;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.service.TestscreenService;
import com.itboyst.facedemo.service.Zteacher_commandService;
import com.itboyst.facedemo.service.Zteacher_command_screenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.itboyst.facedemo.controller.ZcommandController.logger;


@Controller
public class ScreenController {

    @Autowired
    TestscreenService testscreenService;

    @Autowired
    Zteacher_commandService zteacher_commandService;

    @Autowired
    Zteacher_command_screenService zteacher_command_screenService;

    Fileutil fileutil=new Fileutil();


    /**
     * 当前已有文件要新插入文件时
     * @param file
     * @param comscreenid
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertscreennums")
    @ResponseBody
    public int insertscreennums(String file,String comscreenid) throws Exception{
        String filename=fileutil.image64Input2Local("D:/SchoolTrainFiles/Curriculum/taskcontent",file);

        String zcontent=zteacher_command_screenService.findscreencommandById(comscreenid).getZcontent();

        StringBuffer sb =new StringBuffer();
        sb.append(zcontent+",");
        sb.append(filename);

        return zteacher_command_screenService.updatecsstbyid(comscreenid,sb.toString());
    }



    @RequestMapping("/findclosescreen")
    @ResponseBody
    public Timestamp findclosescreen(HttpSession session) {

        try {
            Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie") ;

            int i=zteacher_commandService.findscrenclosefuncount(zteacher_cookie.getZtrainingroomid(),zteacher_cookie.getZscheduleID());

            if (i==0){
                return new Timestamp(System.currentTimeMillis());
            }
            else {
                Zteacher_command zteacher_command=zteacher_commandService.findscrenclosefun(zteacher_cookie.getZtrainingroomid(),zteacher_cookie.getZscheduleID());
                // System.out.println(zteacher_command.getZpublishtime());
                return zteacher_command.getZpublishtime();
            }
        }catch(Exception e) {
            return null;
        }

    }


    //同屏
    @GetMapping("/testscreen")
    @ResponseBody
    public String sse() {
        // 获取屏幕截图，每个1秒获取一次，返回前端界面进行图片展示
        String capture = ScreenUtil.capture();
        SseEmitter emitter = new SseEmitter(0L) ;
        try {
            emitter.send(SseEmitter.event()
                    .reconnectTime(0)
                    .data(capture));
        } catch (IOException e) {
            logger.error("服务端推送服务发生异常", e);
        }
        return capture;
    }

    /**
     * zcommand已经存在
     * @param file
     * @param commmandid
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertfrscreen")
    @ResponseBody
    public int insertfrscreen(String file,String commmandid,String insertfrscreen) throws Exception {
        String content =zteacher_commandService.findcommandById(commmandid).getZcontent();

        Zteacher_command_screen zteacher_command_screen=new Zteacher_command_screen();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String filename=fileutil.image64Input2Local("D:/SchoolTrainFiles/Curriculum/taskcontent",file);
        zteacher_command_screen.setZid(uuid);
        zteacher_command_screen.setZcontent(filename);
        zteacher_command_screen.setZtype(insertfrscreen);

        StringBuffer sb=new StringBuffer();
        sb.append(content+",");
        sb.append(uuid);

        int i=zteacher_command_screenService.insertscreencommand(zteacher_command_screen);
        int j=zteacher_commandService.updatecontentbyid(commmandid,sb.toString());
        if(i>0&&j>0)
            return 1;
        else
            return 0;
    }



    /**
     * 分屏插入命令及文件(当前没有命令也没有文件时)第一个分屏的第一张图片
     * @param file
     * @param session
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/insertpicturescreen")
    @ResponseBody
    public int insertscreen(String file, HttpSession session,String type,String filetype) throws Exception {

        //Fileutil.base64StringToPDF(file);
       // Fileutil.base64ToFile(file,System.currentTimeMillis()+".mp4","D:/SchoolTrainFiles/Curriculum/taskcontent");

        //System.out.println(1);
        String filename=fileutil.image64Input2Local("D:/SchoolTrainFiles/Curriculum/taskcontent",file);

        System.out.println(filename);
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        String ztrainingroomID=zteacher_cookie.getZtrainingroomid();
        String zscheduleID=zteacher_cookie.getZscheduleID();


        Zteacher_command zteacher_command=new Zteacher_command();
        String uuid1 = UUID.randomUUID().toString().replaceAll("-","");
        zteacher_command.setZid(uuid1);
        zteacher_command.setZtrainingroomID(ztrainingroomID);
        zteacher_command.setZscheduleID(zscheduleID);
        zteacher_command.setZtype("分屏信息");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zteacher_command.setZpublishtime(timestamp);
        zteacher_command.setZstatus("失效");


        Zteacher_command_screen zteacher_command_screen=new Zteacher_command_screen();
        String uuid2= UUID.randomUUID().toString().replaceAll("-","");
        zteacher_command_screen.setZid(uuid2);
        zteacher_command_screen.setZtype(filetype);
        zteacher_command_screen.setZcontent(filename);


        StringBuffer sb=new StringBuffer();
        sb.append(type+";");
        sb.append(uuid2);
        zteacher_command.setZcontent(sb.toString());

        //System.out.println(zteacher_command);
        //System.out.println(zteacher_command_screen);
        int i=zteacher_command_screenService.insertscreencommand(zteacher_command_screen);
        int j=zteacher_commandService.insertcommand(zteacher_command);

        if (i>0&&j>0)
            return 1;
        return 0;
    }

    @RequestMapping("/findscreencontenbyid")
    @ResponseBody
    public Zteacher_command_screen findscreencontenbyid(String id) {
       return zteacher_command_screenService.findscreencommandById(id);
    }


    @RequestMapping("/findscreencommand")
    @ResponseBody
    public Zteacher_command findscreencommand(HttpSession session) {
        Zstudent_cookie zstudent_cookie=(Zstudent_cookie) session.getAttribute("zstudent_cookie") ;

        return zteacher_commandService.findscreencommand(zstudent_cookie.getZtrainingroomID(),zstudent_cookie.getZscheduleID());
    }



    /**
     * 找到最近的分屏
     * @param session
     * @return
     */
    @RequestMapping("/findcomscreenclose")
    @ResponseBody
    public Zteacher_command findcomscreenclose(HttpSession session) {
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        //System.out.println(zteacher_commandService.findscrenclose(zteacher_cookie.getZtrainingroomid(),zteacher_cookie.getZscheduleID()));
        return zteacher_commandService.findscrenclose(zteacher_cookie.getZtrainingroomid(),zteacher_cookie.getZscheduleID());
    }



    @RequestMapping("/updatecomscreens")
    @ResponseBody
    public int updatecomscreens(@RequestParam(value = "commmandid")String commmandid, @RequestParam(value = "time")int time,@RequestParam(value = "commscreenid[]")String [] commscreenid) throws Exception {

        int i=0;
        //将时间加入进分屏中去
        //每个分屏单独给予时间
        for (int k=0;k<commscreenid.length;k++) {
            System.out.println(commscreenid[k]);
            StringBuffer sb = new StringBuffer();
            sb.append(time + ";");
            String zcontent = zteacher_command_screenService.findscreencommandById(commscreenid[k]).getZcontent();
            sb.append(zcontent);
            i= zteacher_command_screenService.updatecsstbyid(commscreenid[k], sb.toString());
        }

        int j=zteacher_commandService.updateCommandByid(commmandid,"有效");
        if (i>0&&j>0)
            return 1;
        else
            return 0;

    }


    @RequestMapping("/findleftimg")
    @ResponseBody
    public List<TestScreen> findleftimg() {
        return testscreenService.findleftscreen();
    }


    @RequestMapping("/findlefcount")
    @ResponseBody
    public int findlefcount() {
        return testscreenService.findleftscreencount();
    }

}
