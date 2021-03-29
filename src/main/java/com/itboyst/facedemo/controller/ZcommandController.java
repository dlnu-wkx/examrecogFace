package com.itboyst.facedemo.controller;

import com.itboyst.facedemo.dto.Zteacher;
import com.itboyst.facedemo.dto.Zteacher_command;
import com.itboyst.facedemo.dto.Zteacher_cookie;
import com.itboyst.facedemo.dto.ztraining_room;
import com.itboyst.facedemo.service.Zteacher_commandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.zhtml.S;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ZcommandController {
    public final static Logger logger = LoggerFactory.getLogger(ZcommandController.class);

    @Autowired
    Zteacher_commandService zteacher_commandService;



    @RequestMapping("/findcommand")
    @ResponseBody
    public List<Zteacher_command> findcommand(HttpSession session,String chagangID,String gundongID){

       // System.out.println(chagangID+gundongID);

        ztraining_room ztraining_room=(ztraining_room)session.getAttribute("ztraining_room");
        //**这个为空的情况要处理主要是session失活
        String zid="";
        try{
         zid=ztraining_room.getZid();
        }catch (Exception e){
            System.out.println("ztraining_room的session失活,请重新登录");
            return null;
        }
        List<Zteacher_command> data=zteacher_commandService.selectcommand(zid);
       // System.err.println("befor data :"+data.size());
        //System.out.println(data);
        for(int i=0;i<data.size();i++){
            if(data.get(i).getZid().equals(chagangID)|| data.get(i).getZid().equals(gundongID)){
                data.remove(i);
            }
        }
       // System.err.println(data.size());
        return data;
    }



    @RequestMapping("/insertcommand")
    @ResponseBody
    public int insertcommand(String zcontent,HttpSession session,int time){

        Zteacher_cookie zteacher_cookie=( Zteacher_cookie)session.getAttribute("zteacher_cookie");

        Zteacher_command zteacher_command=new Zteacher_command();
        zteacher_command.setZcontent(zcontent);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zteacher_command.setZid(uuid);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zteacher_command.setZpublishtime(timestamp);
        zteacher_command.setZstatus("有效");
        zteacher_command.setZtype("滚屏信息");
        zteacher_command.setZduration(time);
        zteacher_command.setZscheduleID(zteacher_cookie.getZscheduleID());
        zteacher_command.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());

        Zteacher_command zteacher_command1=new Zteacher_command();
       // zteacher_command1.setZscheduleID(zteacher_cookie.getZscheduleID());
        zteacher_command1.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());
        zteacher_command1.setZstatus("失效");
        zteacher_command1.setZtype("滚屏信息");


        //先将原先的滚屏信息的状态更改为失效
        int i=zteacher_commandService.updatestate(zteacher_command1);
        //插入现在的滚屏信息
        int j=zteacher_commandService.insertcommand(zteacher_command);
        if(i>0&&j>0)return 1;

        return 0;
    }



   /* @RequestMapping("/insertcommandbychose")
    @ResponseBody
    public int insertcommandbychose(String zcontent,HttpSession session){

        Zteacher_cookie zteacher_cookie=( Zteacher_cookie)session.getAttribute("zteacher_cookie");

        Zteacher_command zteacher_command=new Zteacher_command();
        zteacher_command.setZcontent(zcontent);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zteacher_command.setZid(uuid);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zteacher_command.setZpublishtime(timestamp);
        zteacher_command.setZstatus("有效");
        zteacher_command.setZtype("滚屏信息");
        zteacher_command.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());

        //先删除原先的滚屏信息
        int i=zteacher_commandService.deltebefore(zteacher_cookie.getZtrainingroomid(),"滚屏信息");

        //插入现在的滚屏信息
        return zteacher_commandService.insertcommand(zteacher_command);
    }*/



    @RequestMapping("/stopaction")
    @ResponseBody
    public int stopaction(String commscreenid,HttpSession session){
        Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

        int i= zteacher_commandService.updateCommandByid(commscreenid,"失效");

        Zteacher_command zteacher_command=new Zteacher_command();
        zteacher_command.setZstatus("失效");
        zteacher_command.setZscheduleID(zteacher_cookie.getZscheduleID());
        zteacher_command.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());

        int j= zteacher_commandService.updatestatbyclose(zteacher_command);

        if (i>0||j>0)
            return 1;
        else
            return 0;
    }



    @RequestMapping("/upadtestates")
    @ResponseBody
    public int upadtestates(HttpSession session){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        Zteacher_command zteacher_command=new Zteacher_command();
        zteacher_command.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());
        zteacher_command.setZtype("滚屏信息");
        zteacher_command.setZstatus("失效");
        zteacher_command.setZscheduleID(zteacher_cookie.getZscheduleID());

        return zteacher_commandService.updatestate(zteacher_command);
    }

    /**
     * 增加查岗功能
     * 魏凯旋 2020-11-15
     * @param session 缓存值
     * @param zcontent 内容
     * @return
     */
    @RequestMapping("/addCheckPoint")
    @ResponseBody
    public int addCheckPoint(HttpSession session,String zcontent,String ztype){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomid =zteacher_cookie.getZtrainingroomid();
        //通过查岗和所在的房间id找出所以的对象
        int a =zteacher_commandService.updateCommandByroomandZtype(ztype,ztrainingroomid);
        //插入一条最新的更新
        Zteacher_command zteacher_command=new Zteacher_command();
        zteacher_command.setZtrainingroomID(ztrainingroomid);
        zteacher_command.setZcontent(zcontent);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zteacher_command.setZid(uuid);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zteacher_command.setZpublishtime(timestamp);
        zteacher_command.setZstatus("有效");
        zteacher_command.setZtype(ztype);
        return zteacher_commandService.insertcommand(zteacher_command);
    }


    @RequestMapping("/delCheckPoint")
    @ResponseBody
    public void delCheckPoint(HttpSession session){
        Zteacher_cookie zteacher_cookie =(Zteacher_cookie) session.getAttribute("zteacher_cookie");
        String ztrainingroomid =zteacher_cookie.getZtrainingroomid();
        //通过查岗和所在的房间id对所有的查岗进行更新
        int a =zteacher_commandService.updateCommandByroomandZtype("查岗",ztrainingroomid);
        //插入一条最新的更新


    }

    @RequestMapping("/findcountgp")
    @ResponseBody
    public int findcountgp(HttpSession session){
       Zteacher_cookie zteacher_cookie=(Zteacher_cookie)session.getAttribute("zteacher_cookie");

       Zteacher_command zteacher_command=new Zteacher_command();
       zteacher_command.setZscheduleID(zteacher_command.getZscheduleID());
       zteacher_command.setZtrainingroomID(zteacher_cookie.getZtrainingroomid());
       zteacher_command.setZtype("滚屏信息");
       return zteacher_commandService.findcountgp(zteacher_command);

    }



}
