package com.itboyst.facedemo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.itboyst.facedemo.base.Iputil;
import com.itboyst.facedemo.base.Powerutil;
import com.itboyst.facedemo.base.Result;
import com.itboyst.facedemo.base.Results;
import com.itboyst.facedemo.domain.UserFaceInfo;
import com.itboyst.facedemo.dto.*;
import com.itboyst.facedemo.enums.ErrorCodeEnum;
import com.itboyst.facedemo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.itboyst.facedemo.base.UUIDutil.ReplaceSQLChar;


@Controller
public class FaceController {

    public final static Logger logger = LoggerFactory.getLogger(FaceController.class);


    @Autowired
    FaceEngineService faceEngineService;

    @Autowired
    UserFaceInfoService userFaceInfoService;

    @Autowired
    ZstudentService zstuservice;


    @Autowired
    FaceEngineService faceengine;

    @Autowired
    Ztraining_facilityService ztrinfser;

    @Autowired
    Ztraining_roomService ztraining_roomService;

    @Autowired
    Zstudent_cooikeService zstudent_cooikeService;

    @Autowired
    Zstudent_journalService zstudentJournalService;

    @Autowired
    Zteacher_journalService zteacher_journalService;

    @Autowired
    ZteacherService zteacherService;

    @Autowired
    ZscheuleService zscheuleService;

    @Autowired
    Zteacher_cookieSerice zteacher_cookieSerice;

    @Autowired
    ZmanagerService zmanagerService;

    @Autowired
    Zstudent_loginService zstudentLoginService;

    @Autowired
    Zstudent_loginService zstudent_loginService;

    @Autowired
    ZtempuserService ztempuserService;

    @Autowired
    Zstudent_eventService zstudent_eventService;

    @Autowired
    Zteacher_commandService zteacher_commandService;

    @Autowired
    Zstudent_scheduleService zstudent_scheduleService;
    /**
     * ????????????
     *
     * @return
     */
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {
        System.out.println("??????");
        return "demo3";
    }


    /**
     * ???????????????????????????????????????????????????????????????
     * ????????? 2020-11-04
     *
     * @return
     */
    @RequestMapping(value = "/field_management")
    public String field_management() {
        return "field_management";
    }

    /**
     * ????????????
     * @return
     */
    @RequestMapping(value = "/exam_field_management")
    public String exam_field_management() {
        return "exam_field_management";
    }

    /**
     * ????????????
     * @return
     */
    @RequestMapping(value = "/group_management")
    public String group_management() {
        return "group_management";
    }

    /**
     * ????????????
     * @return
     */
    @RequestMapping(value = "/informationPanel")
    public String informationPanel() {
        return "informationPanel";
    }


    @RequestMapping(value = "/findnamelike")
    @ResponseBody
    public List<String> findallface(String name) {
        return userFaceInfoService.findcountnamelike(name);
    }


    /**
     *????????????
     */
    @RequestMapping(value = "/faceAdd", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> faceAdd(String major, String classes, String userkind, String password, String zphone, String sex, @RequestParam("file") String file, @RequestParam("groupId") Integer groupId, @RequestParam("name") String name, @RequestParam("zidentity") String zidentity) {

        //????????????
        major = ReplaceSQLChar(major);
        classes = ReplaceSQLChar(classes);
        userkind = ReplaceSQLChar(userkind);
        password = ReplaceSQLChar(password);
        zphone = ReplaceSQLChar(zphone);
        sex = ReplaceSQLChar(sex);
        name = ReplaceSQLChar(name);
        zidentity = ReplaceSQLChar(zidentity);


        try {
            if (file == null) {
                return Results.newFailedResult("file is null");
            }
            if (groupId == null) {
                return Results.newFailedResult("groupId is null");
            }
            if (name == null) {
                return Results.newFailedResult("name is null");
            }
            if ("".equals(file)) {
                return Results.newFailedResult("picture is null");
            }
            byte[] decode = Base64.decode(base64Process(file));
            //String path = pictureOutStream(decode);//??????????????????????????????
            //String path = base64StringToImage(decode);

            ImageInfo imageInfo = ImageFactory.getRGBData(decode);

            //??????????????????
            byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
            if (bytes == null) {
                return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);
            }
            //System.err.println(path);
            String path = "";
            if (userkind.equals("??????")) {
                path = "D:\\SchoolTrainFiles\\FacePic\\student\\" + System.currentTimeMillis() + ".jpg";
            }
            if (userkind.equals("??????")) {
                path = "D:\\SchoolTrainFiles\\FacePic\\teacher\\" + System.currentTimeMillis() + ".jpg";
            }

            if ("".equals(path)) {
                return Results.newFailedResult("picture sava failure");
            }
            //System.out.println(path);

            GenerateImage(file, path);


            //???????????????????????????????????????
            int namenum, i, j, k, q, w, v, z = 0;


            UserFaceInfo userFaceInfo = new UserFaceInfo();
            userFaceInfo.setName(name);
            userFaceInfo.setGroupId(groupId);
            userFaceInfo.setFaceFeature(bytes);

            //?????????????????????face?????????
            userFaceInfo.setFaceId(zidentity);
            userFaceInfo.setPath(path);

            int e = userFaceInfoService.findcountfaceid(zidentity);

            //System.out.println(e);
            if (e > 0)
                userFaceInfoService.updateuserfopath(path, zidentity, name);
            else
                userFaceInfoService.insertSelective(userFaceInfo);

            String zid = UUID.randomUUID().toString().replaceAll("-", "");
            int id = 0;

            if (e > 0)
                id = userFaceInfoService.findidbyfaceid(zidentity);
            else
                id = faceEngineService.selectidbyname(path);

            if (userkind.equals("??????")) {
                // System.out.println(0);

                Zstudent zstu = new Zstudent();

                zstu.setZidentity(zidentity);
                zstu.setZname(name);
                zstu.setZphoto(path);
                zstu.setZpass(password);
                zstu.setZsex(sex);
                zstu.setZphone(zphone);
                zstu.setZgradeID(classes);
                zstu.setZfaceinfoID(id);
                zstu.setZstatus("?????????");
                namenum = zstuservice.findcountbyname(zidentity);

                //System.out.println(zstu);
                if (namenum != 0) {
                    w = zstuservice.updatestudent(zstu);
                    //????????????
                    if (w > 0) {
                        return Results.newSuccessResult("");
                    } else {
                        //??????????????????
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_STUDENT);
                    }
                } else {
                    zstu.setZid(zid);
                    i = zstuservice.registerstud(zstu);
                }

                // System.out.println(i);
                if (i == 0) {
                    return null;
                }
            } else if (userkind.equals("??????")) {
                //System.out.println(0);
                Zteacher zteacher = new Zteacher();
                Zteacher zteacher2 = new Zteacher();
                zteacher2 = zteacherService.selectteacherbyname(name);
                int a = 0;
                if (zteacher2 != null) a = 1;

                zteacher.setZfaceinfoID(id);
                zteacher.setZidentity(zidentity);
                zteacher.setZname(name);
                zteacher.setZpass(password);
                zteacher.setZphone(zphone);
                zteacher.setZphoto(path);
                zteacher.setZsex(sex);
                zteacher.setZmajorID(major);
                //System.out.println(zteacher);


                if (a != 0) {
                    v = zteacherService.updateteacher(zteacher);
                    //????????????
                    if (v > 0) {
                        return Results.newSuccessResult("");
                    } else {
                        //??????????????????
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_TEACHER);
                    }
                } else {
                    zteacher.setZid(zid);
                    j = zteacherService.registerteacher(zteacher);
                }

                if (j == 0) {
                    return null;
                }

            } else if (userkind.equals("?????????")) {
                //System.out.println(0);
                Zmanager zmanager = new Zmanager();
                Zmanager zmanager2 = new Zmanager();
                zmanager2 = zmanagerService.findallbymanagername(name);

                int b = 0;
                if (zmanager2 != null) b = 1;

                zmanager.setZidentity(zidentity);
                zmanager.setZname(name);
                zmanager.setZpass(password);
                zmanager.setZphone(zphone);

                if (b != 0) {
                    v = zmanagerService.updatemanager(zmanager);
                    //????????????
                    if (v > 0) {
                        return Results.newSuccessResult("");
                    } else {
                        //??????????????????
                        return Results.newFailedResult(ErrorCodeEnum.FAIL_UPDATE_MANAGER);
                    }
                } else {
                    zmanager.setZid(zid);
                    k = zmanagerService.insertmanager(zmanager);
                }

                if (k == 0) {
                    return null;
                }

            }

            logger.info("faceAdd:" + name);
            return Results.newSuccessResult("");
        } catch (Exception e) {
            logger.error("", e);
        }
        return Results.newFailedResult(ErrorCodeEnum.UNKNOWN);
    }

    /**
     * ???????????????????????????
     *
     * @param decode
     */
    private String pictureOutStream(byte[] decode) {

        File file = new File("F:\\recognitionFace\\src\\main\\resources\\static\\images\\" + System.currentTimeMillis() + ".jpg");//?????????????????????
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {//???????????????????????????????????????????????????
                file.mkdirs();
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(decode);
            }
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }

            }

        }
        return "";
    }

    /**
     * ??????????????????????????????
     *
     * @param bytes1
     */
    private String base64StringToImage(byte[] bytes1) {

        try {
            String path = "";
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("F:\\recognitionFace\\src\\main\\resources\\static\\images\\" + System.currentTimeMillis() + ".jpg");//?????????jpg,png??????
            ImageIO.write(bi1, "jpg", w2);//???????????????????????????????????????????????????
            path = w2.getPath();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ??????????????????????????????Base64?????????????????????
     * ????????? 2020-10-26
     *
     * @param imgStr ???????????????
     * @param
     * @return
     */
    private boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // ??????????????????
            return false;
        //???????????????data:image/png;base64,
        /*int start = imgStr.indexOf(',') + 1;
            imgStr=imgStr.substring(start);*/
        //BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64??????
            byte[] bytes = Base64.decode(base64Process(imgStr));
            //byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// ??????????????????
                    bytes[i] += 256;
                }
            }
            //?????????????????????????????????????????????
            File tempFile = new File(imgFilePath);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            // ??????jpg??????
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ?????????????????????????????????
     *
     * @param file
     * @param groupId
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/faceSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceSearch(HttpServletRequest request, String ztype, String ip, String file, Integer groupId, HttpServletResponse response, HttpSession session, Model model) throws Exception {

        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        ImageInfo imageInfoGray = ImageFactory.bufferedImage2GrayImageInfo(bufImage);
        //??????????????????
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//????????????????????????
        }
        //?????????????????????????????????
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        System.out.println("??????????????????????????????" + userFaceInfoList.size());
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            //??????????????????????????????????????????feature????????????
            for(int i =1;i<userFaceInfoList.size();i++){
                String updatfaceid = userFaceInfoList.get(i).getFaceId();
                /*String deloriginalPictureUrl = userFaceInfoList.get(i).getPath();*/
                if(updatfaceid.contains("L")){
                    //???????????????????????????
                    int m = userFaceInfoService.updatefacefeature(updatfaceid);
                  /*  int f = ztempuserService.upzstatusbyoriginalPictureUrl(deloriginalPictureUrl);*/
                }

            }
            //????????????????????????????????????????????????
            String fpath = userFaceInfoList.get(0).getPath();
            //?????????????????????????????????????????????????????????????????????
            if(!fpath.contains("teacher")){
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //????????????????????????1?????????????????????????????????????????????
                if(processInfoList.get(0).getLiveness() != 1){
                    return Results.newFailedResult(ErrorCodeEnum.NOT_CAN_PITURE_LOGIN);
                }
                //????????????
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//??????
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "???" : "???");

            }
            //session.setMaxInactiveInterval(60*60*24*30);

            //System.out.println(faceUserInfo.getPath());
            //student???????????????
            Zstudent zstudent = new Zstudent();

            //System.out.println(faceUserInfo.getPath());

            if (faceUserInfo.getPath() == null)
                return Results.newFailedResult(ErrorCodeEnum.NO_FACE_PATH);


            int faceid = faceengine.selectidbyname(faceUserInfo.getPath());

            System.out.println("faceid:"+faceid);
            //??????int????????????,????????????????????????1

            //???????????????????????????????????????????????????????????????????????????
            String face_id = userFaceInfoService.selectfaceidbyfpath(faceUserInfo.getPath());
            if (face_id.contains("L")){
                Timestamp createtimestamp = userFaceInfoService.findcreatimebyfaceid(face_id);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
                String str = sdf.format(date);
                Date date2 = sdf.parse(str);
                Timestamp timestamp2222 = new Timestamp(date2.getTime());
                int compare = createtimestamp.compareTo(timestamp2222);
                System.out.println("compare" + compare);
                System.err.println(face_id);
                if(compare<0){
                    int m = userFaceInfoService.updatefacefeature(face_id);
                    return Results.newFailedResult(ErrorCodeEnum.TEMPUSER_NOT_LOGIN_TWO);
                }

            }

            zstudent = zstuservice.findadoptstudent(faceid);
            //System.out.println(zstudent);

            if (zstudent == null) return Results.newFailedResult(ErrorCodeEnum.NO_STUDENT_FACEID);
            //??????????????????
            Zstudent_login zsl = new Zstudent_login();

            String uuid2 = UUID.randomUUID().toString().replaceAll("-", "");
            zsl.setZid(uuid2);


            //???????????????session

            zsl.setZstudentID(zstudent.getZid());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            zsl.setZrecongnizetime(timestamp);

            //?????????????????????????????????????????????
            zsl.setZtype("??????");

            InetAddress addr = InetAddress.getLocalHost();

            String ip4 = Iputil.getClientIpAddress(request);


            //?????????????????????
            Zstudent_login zstudent_login2=new Zstudent_login();
            zstudent_login2.setZnowtaskname("");
            zstudent_login2.setZtesttime(0);
            zstudent_login2.setZrecognizeIP(ip4);



            //?????????????????????
            Zstudent_event zstudent_event=new Zstudent_event();
            zstudent_event.setZstatus("??????");
            zstudent_event.setZrecognizeIP(ip4);



            zsl.setZcheck("??????");
            System.out.println("ip4" + ip4);

            //System.out.println("ip2"+ip2);
            /*zsl.setZcheck("??????");*/
            zsl.setZrecognizeIP(ip4);

            Ztraining_facility ztrfac = ztrinfser.findbyip(ip4);
            if (ztrfac == null) return Results.newFailedResult(ErrorCodeEnum.NO_FACILITY_STUDENTPCIP);

            //???????????????????????????
            ztrinfser.updateoneportbyip(ip4, 1);
            ztrinfser.updattwoportbyip(ip4,0);


            if (ztrfac.getZtrainingroomID().isEmpty())
                return Results.newFailedResult(ErrorCodeEnum.NO_FACILITY_TRAINROOMID);
            //?????????
            ztraining_room ztr = ztraining_roomService.findbyip(ztrfac.getZtrainingroomID());


            //System.out.println(ztr);

            //?????????????????????????????????
            List<Zstudent_cookie> zsclist = zstudent_cooikeService.findscookiemes(ztr.getZid(), timestamp, zstudent.getZid());
            if(zsclist.size()==0 ){
                return Results.newFailedResult(ErrorCodeEnum.NOT_SCHEDULE);
            }
            if(zsclist.size()>1){
                //???????????????????????????
                List<String> findallzschedulelist = zscheuleService.findallzschedule("????????????");
                for(int n =0;n<zsclist.size();n++){//??????????????????
                    for(int i=0;i<findallzschedulelist.size();i++){
                    if(zsclist.get(n).getZscheduleID().equals(findallzschedulelist.get(i))){
                        zsclist.remove(zsclist.get(n));
                    }
                    }
                }

            }

            zsl.setZstatus("??????");


            zsl.setZscheduleID(zsclist.get(0).getZscheduleID());
            //?????????????????????????????????????????????????????????????????????????????????????????????

            if (zstudent.getZidentity().contains("L")) {//??????????????????
                //?????????????????????????????????
                List<Zstudent_journal> zstudent_journals = zstudentJournalService.findstudentjournaltime(zstudent.getZid());
                if (zstudent_journals.size() > 0) {
                    //?????????????????????face_feature???null
                    int m = userFaceInfoService.updatefacefeature(zstudent.getZidentity());
                    return Results.newFailedResult(ErrorCodeEnum.TEMPUSER_NOT_LOGIN_TWO);
                }

            }
            //???????????????????????????
            Zstudent_login zstudent_login3=zstudent_loginService.findloginbyss(zstudent.getZid(),zsl.getZscheduleID());
            if(zstudent_login3!=null)return Results.newFailedResult(ErrorCodeEnum.Double_Login);

            //??????????????????
            Zstudent_journal zstudentJournal = new Zstudent_journal();
            zstudentJournal.setZstudentID(zstudent.getZid());
            zstudentJournal.setZtype(ztype);
            zstudentJournal.setZoperatedate(timestamp);
            String uuid3 = UUID.randomUUID().toString().replaceAll("-", "");
            zstudentJournal.setZid(uuid3);


            //??????????????????session
            session.setAttribute("zstudent_login",zsl);
            session.setAttribute("zstudent_cookie", zsclist.get(0));
            session.setAttribute("ztraining_room", ztr);
            session.setAttribute("ztraining_facility", ztrfac);
            session.setAttribute("zprogress","??????");
            session.setAttribute("zstudent", zstudent);
            session.setAttribute("faceUserInfo", faceUserInfo);

            //??????????????????????????????
            Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            if (ztrfac.getZpowerIP() != null)
                                if (Powerutil.pingIp(ztrfac.getZpowerIP()))
                                    Powerutil.powercontroller(ztrfac.getZpowerIP(), "11");
                            Powerutil.powercontroller(ztrfac.getZpowerIP(), "22");
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }
                    }
                });

            t.start();

            int j = zstudentJournalService.insertstujournal(zstudentJournal);
            int b = ztrinfser.updatezprogressbyip(ip4, "??????");
            int i = zstudent_loginService.insertnowmessage(zsl);
            int d=zstudent_eventService.updatebeforebyip(zstudent_event);
            int c=zstudent_loginService.updatetatbyip2(zstudent_login2);

            if (zstudent != null && i != 0) {
                //????????????????????????Cookie
                Cookie name = new Cookie("name", faceSearchResDto.getName());
                Cookie faceId = new Cookie("faceId", faceSearchResDto.getFaceId());
                //???????????????????????????
                Cookie inspect = new Cookie("Inspect","1111");
                //?????????\?????????/??????????????????Cookie???
                String path1 = fpath.replace("\\", "/");
                String path = "";
                if(faceUserInfo.getPath().contains("ztempuser")){//???????????????????????????????????????
                    path = path1.substring(46);
                }else {//???????????????????????????
                    path = path1.substring(36);
                }

                //????????????????????????
                Cookie aimPath1 = new Cookie("path", path);//???????????????cookie?????????
                name.setMaxAge(86400);
                faceId.setMaxAge(86400);
                aimPath1.setMaxAge(86400);
                inspect.setMaxAge(86400);
                response.addCookie(inspect);
                response.addCookie(name);
                response.addCookie(faceId);
                response.addCookie(aimPath1);//???????????????cookie???
                return Results.newSuccessResult(faceSearchResDto);
            }

            }
        }


        //???????????????????????????????????????????????????
        String path = "D:\\SchoolTrainFiles\\FacePic\\ztempuser\\student\\" + System.currentTimeMillis() + ".jpg";
        GenerateImage(file, path);
        Timestamp  logintime = ztempuserService.findmaxtime("??????");
        Long  strdate = handleDate(logintime.getTime());
        System.err.println("strdate : "+strdate);
        if(strdate>=1){//?????????????????????????????????
            deletenottodaypicture("D:\\SchoolTrainFiles\\FacePic\\ztempuser\\student\\");
        }
        Ztempuser ztempuser = new Ztempuser();
        String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
        ztempuser.setZid(uuid1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ztempuser.setZlogintime(timestamp);
        ztempuser.setZtype("??????");
        ztempuser.setOriginalPictureUrl(path);
        String ip4 = Iputil.getClientIpAddress(request);
        ztempuser.setZrecognizeIP(ip4);
        ztempuser.setZstatus("?????????");
        int a = ztempuserService.insertoneztempuser(ztempuser);


        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }

    /**
     * ????????????????????????
     * ????????? 2020-11-23
     *
     * @param file
     * @param groupId
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/faceFind", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceFind(String file, Integer groupId, HttpSession session, HttpServletRequest request) throws Exception {
        //???????????????????????????????????????????????????
        Zstudent_cookie  zstucookie = (Zstudent_cookie) session.getAttribute("zstudent_cookie");
        if(null == zstucookie){
            return Results.newFailedResult(ErrorCodeEnum.SESSION_NOT_ACTIVE);//????????????
        }
        String  ztrainingroomID = zstucookie.getZtrainingroomID();
        List<Zteacher_command> zteacherCommandList =  zteacher_commandService.findinspect(ztrainingroomID);
        if(CollectionUtil.isEmpty(zteacherCommandList)){
            return Results.newFailedResult(ErrorCodeEnum.INSPECT_END);//????????????
        }
        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //??????????????????
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//????????????????????????
        }

        //?????????????????????????????????
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            //????????????????????????????????????????????????
            String fpath = userFaceInfoList.get(0).getPath();
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //????????????
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//??????
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "???" : "???");

            }
            //student???????????????
            Zstudent zstudent = new Zstudent();
            int faceid = faceengine.selectidbyname(faceUserInfo.getPath());
            zstudent = zstuservice.findadoptstudent(faceid);
            if(null != zstudent){//?????????????????????????????????
                Zstudent presentZstudent = (Zstudent) session.getAttribute("zstudent");
                if (presentZstudent.getZidentity().equals(zstudent.getZidentity())) {
                    Zstudent_login zsl = new Zstudent_login();
                    String uuid2 = UUID.randomUUID().toString().replaceAll("-", "");
                    zsl.setZid(uuid2);
                    zsl.setZstudentID(presentZstudent.getZid());
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    zsl.setZrecongnizetime(timestamp);
                    String ip4 = Iputil.getClientIpAddress(request);
                    zsl.setZtype("??????");
                    List<String> list = zstudentLoginService.findScheduleBytimeandzstudentID(presentZstudent.getZid(), timestamp);
                    System.out.println("list.size"+list.size());
                    if (!list.isEmpty()) {
                        zsl.setZscheduleID(list.get(0));
                    }
                    //System.out.println(ip4);
                    //System.out.println(ip2);
                    zsl.setZcheck("??????");
                    zsl.setZrecognizeIP(ip4);
                    String studentfpath = faceEngineService.findfopathByfaceid(zstudent.getZfaceinfoID());
                    System.out.println("studentfpath :"+studentfpath);
                    zsl.setOriginalPictureUrl(studentfpath);
                    //????????????????????????
                    int i = zstudent_loginService.updateloginmessage(zsl);

                    return Results.newSuccessResult(faceSearchResDto);
                }
                return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
            }

            return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);

        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }


    @RequestMapping(value = "/loadtrainroom")
    @ResponseBody
    public List<Zteacher_cookie> loadtrainroom(HttpSession session,String id) {
            int faceid=userFaceInfoService.findidbyfaceid(id);
            List<Zteacher_cookie> teachercookies=zteacher_cookieSerice.findbyfaceid(faceid);

            System.out.println(teachercookies);
            session.setAttribute("teachercookies",teachercookies);

            return teachercookies;
    }

    @RequestMapping(value = "/loadtrainroom2")
    @ResponseBody
    public List<Zteacher_cookie> loadtrainroom2(HttpSession session) {
        List<ztraining_room> data= ztraining_roomService.findallztrainroom();

        List<Zteacher_cookie> data2=new ArrayList<>(data.size());
        for (int i=0;i<data.size();i++){
            Zteacher_cookie zteacher_cookie=new Zteacher_cookie();
            zteacher_cookie.setZroomname(data.get(i).getZname());
            zteacher_cookie.setZtrainingroomid(data.get(i).getZid());

            data2.add(zteacher_cookie);
        }

        session.setAttribute("teachercookies",data2);

        return data2;
    }



    @RequestMapping(value = "/choseasavetcookie")
    @ResponseBody
    public int choseasavetcookie(HttpSession session,String id) {
        //int faceid2=userFaceInfoService.findidbyfaceid(id);

        Zteacher_cookie zteacher_cookie=new Zteacher_cookie();

        List<Zteacher_cookie> data=(List<Zteacher_cookie>) session.getAttribute("teachercookies");

        for (int i=0;i<data.size();i++)
            if(data.get(i).getZtrainingroomid().equals(id))
                zteacher_cookie=data.get(i);

        System.out.println(zteacher_cookie);
        // ?????????
        session.setAttribute("zteacher_cookie",zteacher_cookie);

        //???????????????
        Zteacher_journal zteacher_journal=new Zteacher_journal();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        zteacher_journal.setZid(uuid);
        zteacher_journal.setZtype("????????????");
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        zteacher_journal.setZoperatedate(timestamp);

        int i=0;
        i= zteacher_journalService.inserteacherjournal(zteacher_journal);

        return i;

    }




   //????????????
    @RequestMapping(value = "/faceTeacherSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceTeacherSearch(HttpServletRequest request, String ip, String file, Integer groupId, HttpServletResponse response, HttpSession session) throws Exception {
        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));

        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);
        //??????????????????
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);//????????????????????????
        }
        //?????????????????????????????????
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            for(int i =1;i<userFaceInfoList.size();i++){
                String updatfaceid = userFaceInfoList.get(i).getFaceId();
                String deloriginalPictureUrl = userFaceInfoList.get(i).getPath();
                if(updatfaceid.contains("L")){
                    //???????????????????????????
                    int m = userFaceInfoService.updatefacefeature(updatfaceid);
                    int f = ztempuserService.upzstatusbyoriginalPictureUrl(deloriginalPictureUrl);
                }

            }
            //????????????????????????????????????????????????
            String fpath = userFaceInfoList.get(0).getPath();
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //????????????
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//??????
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "???" : "???");

            }

               //  session.setMaxInactiveInterval(60*60*24*30);
                //?????????id
                int faceid = faceengine.selectidbyname(faceUserInfo.getPath());
                //?????????????????????????????????????????????
                List<Zteacher_cookie> data=zteacher_cookieSerice.findbyfaceid(faceid);
                System.out.println(data);
                Zteacher_cookie zteacher_cookie=new Zteacher_cookie();

                //System.out.println(data.size());

                //????????????????????????Cookie
                Cookie name = new Cookie("name", faceSearchResDto.getName());
                Cookie faceId = new Cookie("faceId", faceSearchResDto.getFaceId());
                //?????????\?????????/??????????????????Cookie???
                String path1 = fpath.replace("\\", "/");
                String path = "";
                if(faceUserInfo.getPath().contains("ztempuser")){//???????????????????????????????????????
                    path = path1.substring(46);
                }else {//???????????????????????????
                    path = path1.substring(36);
                }
                //????????????????????????
                Cookie aimPath1 = new Cookie("path", path);//???????????????cookie?????????
                name.setMaxAge(86400);
                faceId.setMaxAge(86400);
                aimPath1.setMaxAge(86400);
                response.addCookie(name);
                response.addCookie(faceId);
                response.addCookie(aimPath1);//???????????????cookie???

            String face_id = userFaceInfoService.selectfaceidbyfpath(faceUserInfo.getPath());

            Zteacher teacher = zteacherService.findteacherByzidentity(face_id);
            System.out.println(teacher);
            //?????????????????????????????????
            if (null == teacher) {
                return Results.newFailedResult(ErrorCodeEnum.NOT_IS_TEACHER);
            }

               if (data.size()==1){
                   zteacher_cookie=data.get(0);
               }
               if (data.size()>1){
                 //   System.out.println("??????");
                    return Results.newResult(faceSearchResDto,"1",false,0);

                }


            //??????????????????????????????????????????????????????????????????????????????????????????
            if (face_id.contains("L")) {
                //??????face_id???????????????????????????????????????????????????????????????????????????????????????
                Timestamp createtimestamp = userFaceInfoService.findcreatimebyfaceid(face_id);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
                String str = sdf.format(date);
                Date date2 = sdf.parse(str);
                Timestamp timestamp2222 = new Timestamp(date2.getTime());
                int compare = createtimestamp.compareTo(timestamp2222);
                System.err.println(face_id);
                if (compare < 0) {
                    //?????????????????????face_feature???null
                    int a = userFaceInfoService.updatefacefeature(face_id);
                    return Results.newFailedResult(ErrorCodeEnum.TEMPUSER_NOT_LOGIN_TWO);
                }
            }


            //????????????????????????????????????????????????????????????????????????????????????
            if (face_id.contains("L")) {
                List<Zteacher_journal> list = zteacher_journalService.findteacherlogin(teacher.getZid());
                if (list.size() > 0) {
                    //?????????????????????face_feature???null
                    int a = userFaceInfoService.updatefacefeature(face_id);
                    return Results.newFailedResult(ErrorCodeEnum.TEMPUSER_NOT_LOGIN_TWO);
                }

            }

            if(data.size()==0 && face_id.contains("L")){
                return Results.newResult(faceSearchResDto,"2",false,0);
            }
            //????????????????????????????????????????????????????????????
            if(data.size()==0 ){
                return Results.newResult(faceSearchResDto,"2",false,0);
            }

            String zgradid=zstuservice.findzgardeidbyscheduid(zteacher_cookie.getZscheduleID());
            zteacher_cookie.setZgradeid(zgradid);

            session.setAttribute("zteacher_cookie", zteacher_cookie);

            //??????????????????
            Zteacher_journal zteacher_journal = new Zteacher_journal();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            zteacher_journal.setZid(uuid);
            zteacher_journal.setZtype("????????????");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            zteacher_journal.setZoperatedate(timestamp);
            zteacher_journal.setZteacherID(teacher.getZid());
            int i = zteacher_journalService.inserteacherjournal(zteacher_journal);


            return Results.newSuccessResult(faceSearchResDto);

        }
        //?????????????????????????????????
        String path = "D:\\SchoolTrainFiles\\FacePic\\ztempuser\\teacher\\" + System.currentTimeMillis() + ".jpg";
        GenerateImage(file, path);
        //???????????????????????????????????????????????????????????????
        Timestamp  logintime = ztempuserService.findmaxtime("??????");
        Long  day = handleDate(logintime.getTime());
        if(day>=1){
            deletenottodaypicture("D:\\SchoolTrainFiles\\FacePic\\ztempuser\\teacher\\");
        }
        Ztempuser ztempuser = new Ztempuser();
        String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
        ztempuser.setZid(uuid1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ztempuser.setZlogintime(timestamp);
        ztempuser.setZtype("??????");
        ztempuser.setOriginalPictureUrl(path);
        String ip4 = Iputil.getClientIpAddress(request);
        ztempuser.setZrecognizeIP(ip4);
        ztempuser.setZstatus("?????????");
        int a = ztempuserService.insertoneztempuser(ztempuser);

        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }


    @RequestMapping(value = "/teacherlogin", method = RequestMethod.GET)
    public String teacherlogin(@CookieValue("name") String name, @CookieValue("faceId") String faceId, @CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId", faceId);
        model.addAttribute("name", name);
        String indPath = path.replace("F:/recognitionFace/src/main/resources/static/", "");
        model.addAttribute("path", indPath);
        System.out.println("inPath" + indPath);
        return "teacherEnter";
    }


    /**
     * ??????????????????????????????????????????
     * ?????????  2020-10-23
     *
     * @param name
     * @param faceId
     * @param model
     * @param path   ?????????????????????
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String register(@CookieValue("name") String name, @CookieValue("faceId") String faceId, @CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId", faceId);
        model.addAttribute("name", name);
        String indPath = path.replace("F:/recognitionFace/src/main/resources/static/", "");
        model.addAttribute("path", indPath);

        return "studentEnter";
    }

    /**
     * ????????????????????????????????????????????????????????????
     * ????????? 2020-10-31
     *
     * @param name
     * @param faceId
     * @param path
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/teachRegister", method = RequestMethod.GET)
    public String teachRegister(@CookieValue("name") String name, @CookieValue("faceId") String faceId, @CookieValue("path") String path, Model model) throws Exception {
        model.addAttribute("faceId", faceId);
        model.addAttribute("name", name);
        String indPath = path.replace("F:/recognitionFace/src/main/resources/static/", "");
        model.addAttribute("path", indPath);
        return "fieldManagement";
    }

    @RequestMapping(value = "/detectFaces", method = RequestMethod.POST)
    @ResponseBody
    public List<FaceInfo> detectFaces(String image) throws IOException {
        byte[] decode = Base64.decode(image);
        InputStream inputStream = new ByteArrayInputStream(decode);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);

        return faceInfoList;
    }


    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }

            return base64Str;
        } else {
            return "";
        }
    }


    /**
     * ??????????????????????????????
     * @param imgFile
     * @return
     */
    public static String GetImageStr(String imgFile)
    {//???????????????????????????????????????????????????????????????Base64????????????
        InputStream in = null;
        byte[] data = null;
        //????????????????????????
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            System.out.println("????????????1 ???"+Arrays.toString(data));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       return Base64.encode(data);
    }

    //???????????????????????????????????????
    private static Long  handleDate(long time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        Date old = sdf.parse(sdf.format(date));
        Date now = sdf.parse(sdf.format(new Date()));
        long oldTime = old.getTime();
        long nowTime = now.getTime();

        long day = (nowTime - oldTime) / (24 * 60 * 60 * 1000);

        /*if (day < 1) {  //??????
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return format.format(date);
        } else if (day == 1) {     //??????
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            return "?????? " + format.format(date);
        } else {    //???????????????
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(date);
        }*/
        return day;
    }
    //?????????????????????????????????
    public static void deletenottodaypicture(String url) {
        File file = new File(url);
        if (file.isDirectory()) {
            {//??????file????????????????????? ????????????TRUE
                String name[] = file.list();//name??????file????????????????????????
                for (int i = 0; i < name.length; i++) {
                    File f=new File(url, name[i]);//???????????????????????????????????????
                    f.delete();//????????????
                }
            }
        }
    }
}
