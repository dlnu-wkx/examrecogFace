<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>现场管理</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="./jquery/video-js.min.css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/exam_field_management.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./layui/js/field_management.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
    <script src="./layui/js/field_management.js "></script>
    <script src="./layui/js/exam_field_management.js "></script>

    <script src="./layui/js/common.js "></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">签到</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div id='welcomeField' class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
        </div>
        <div id='teach' class="layui-col-xs9" align="center" style="width: 74%">
            <div style="margin: 0,auto;margin-top:40px;height:80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
            </div>
             </div>
        <#--二级菜单-->
        <#--<div id='twoMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;width: 90%">
            <div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="faceShow()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">签到</button></li>
                    &lt;#&ndash;<li style=" margin: 0 auto;margin-right: 10%"><button onclick="studentStatus()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">测试管理</button></li>&ndash;&gt;
                </ul>
            </div>
            <div>
                <ul style="margin: 16% auto;width: 80%">
                   &lt;#&ndash; <li style="margin: 0 auto;margin-left: 10% "><button onclick="loginManagement()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">登录管理</button></li>
                    <li style="margin: 0 auto;margin-right: 10% "><button onclick="courseManagement()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">任务管理</button></li>&ndash;&gt;
                </ul>
            </div>
            <div>
                <ul style="margin: 30% auto;width: 80% ">
                    &lt;#&ndash;<li style="margin-left: 10%"><button id="checkPointColor" onclick="checkPoint()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">查岗</button></li>
                    <li style="margin-right: 10%"><button  id="courseRecordColor" onclick="courseRecord()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">课堂记录</button></li>&ndash;&gt;
                </ul>
            </div>
        </div>-->
        <div id="courseRecordContent"style="display: none;width: 50%;height: 300px;position: fixed;background-color: #CDCDCD;border: 1px solid;top:50%;left: 7%;z-index: 12">
        </div>
        <div id="hiddenArea"style="position: absolute;height: 100%;width: 100%;filter: alpha(opacity=60);opacity: 0.6;display: none;z-index: 11">
        </div>
        <#--三级菜单-->
        <div id='threeMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.4%">

        </div>
        <#--查岗的下级功能表-->
        <div id='checkPointMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.3%">

        </div>
        <#--四级菜单-->
        <div id='fourMenu' class="layui-col-xs1" align="center" style="display:none;width: 26%;font-size: 70px">
            <div id="mainDiv">
                <img id="img" style="width: 400px;height: 300px;" src="trophy.jpg">
            </div>
            <#--这个地方到时候要循环遍历出来拼接字符串-->
            <div id="identifyAreas"style="width: 80%;height:289px;background-color: #ffff;border: 1px solid red;margin-top: 5%;">
            </div>
        </div>
        <div id='fourMenu1' class="layui-col-xs8" align="center" style="display:none;width: 64%;">
            <div  align="center" style="width: 100%;">
                <div style="float:left;margin-left: 2%;font-size: 20px">签到班级：<span id="gradename"></span><span id="gradeID" style="display: none"></span></div>
                <div style="float: right;margin-right: 2%;font-size: 20px">签到人数：<span id="currentnumber">0</span>/<span id="totalpeople">0</span></div>
            </div>
            <div style="height: 500px;text-align:center;line-height:40px;font-size: 40px;background-color: #ffff;border: 1px solid red;margin-top: 3%">
                <#--按照顺序排出识别的人脸，顺序是最早的在最下最右边-->
                <div id="mainBody"style="width: 100%;height: 100%;overflow: auto">

                </div>
            </div>
            <#--人脸识别时显示的开始和结束按钮-->
            <div id="openAndstart"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 30%">
                    <button id="startID" onclick="startcollect()" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 30%">
                    <button id="endID"onclick="endcollect()" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">结束</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 30%">
                    <button id="endID"onclick="choosegrads()" value="1" style="background-color: blue;color: #ffff;border-radius:32px;width: 200px">选择班级</button>
                </div>
            </div>
            <#--查岗时候显示的开始和结束按钮-->
            <div id="openAndstart2"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 50%">
                    <button id="startID2" onclick="OpenOTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:right;width: 50%">
                    <button id="endID2"onclick="CloseTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px;position: fixed;z-index: 12;">结束</button>
                </div>
            </div>

        </div>
        <#--右边功能按钮-->
        <div class="right_parent" align="center" >

            <button id='colorType'  onclick="fieldManagement()"  class="f_field_management" >
                现场管理
            </button>


            <button onclick="informationService()" class="f_field_service"  >
                信息查询
            </button>


            <button onclick="timeStatus()" class="f_field_status" >
                实时状态
            </button>

            <button onclick="informationDelivery()" class="f_field_delivery" >
                信息发布
            </button>

            <button id="exit" onclick="outpower()" class="f_field_exit">
                退出系统
            </button>

        </div>
    </div>
</div>

<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="facebox_exit" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>
<!-- 增加选择班级的蒙版 -->
<div id="masking" class="masking"></div>
<!-- 增加课程的弹框信息 -->
<div id="grade-pop-up" class="pop-up">
</div>
</body>
<script>
    window.onload=show1();
    /*window.onLoad=showCamera();*/
    //展示现场管理的二级菜单
    function show1() {
        document.getElementById("colorType").style.backgroundColor="#ED7D31";
        document.getElementById("welcomeField").style.display="none";
        document.getElementById("teach").style.display="none";
        findAllCameras();
        document.getElementById("threeMenu").style.display="block";
    }
    //数控车讨论区显示每台机的人脸识别情况
    function studentShow(e){

        /* var b =$("#zcameraIP"+e).val();
         alert(b)*/

        document.getElementById("threeMenu").style.display="none";
        //摄像头下面的显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始和结束按钮
        document.getElementById("fourMenu1").style.display="block";
        document.getElementById("openAndstart").style.display="block";
        //显示摄像头的信息
        // showCamera(e)
        /* document.getElementById("mainDiv").style.display="block"*/
        $("#left").hide();
        $("#middle").hide();
        $("#right").hide();
    }
    //显示选择班级的界面
    function choosegrads() {
     document.getElementById("masking").style.display="block"
     document.getElementById("grade-pop-up").style.display="block"
        //查找所有的班级
        var str = "";
        $.ajax({
            type:"post",
            url:"/findallgradebytrainroom",
            success:function (data) {
                if(""!=data){
                    $("#grade-pop-up").empty();
                    for(var i =0;i<data.length;i++){
                        str+=" <div class='pop-up_div' align='center'>"
                        str+="<button style='background-color: blue;color: #ffff;border-radius:32px;width: 250px;height: 55px'onclick='showgradeandpeo(\""+data[i].zid+"\",\""+data[i].zname+"\")'>"+(data[i].zname)+"</button>"
                        str+="</div>"
                    }
                    $("#grade-pop-up").append(str);
                }
            }
        })
    }
    //显示签到的班级
    var backup ="";//这个参数是放置班级数据的初试值
    function showgradeandpeo(id,zname) {
        document.getElementById("currentnumber").innerHTML = 0
        document.getElementById("gradename").innerHTML=zname
        document.getElementById("gradeID").innerHTML=id
        document.getElementById("masking").style.display="none"
        document.getElementById("grade-pop-up").style.display="none"
        //查找相应班级的人数并显示出来
        var str ="";
        var center=$("#mainBody");
        center.empty();
        $.ajax({
            type:"post",
            url:"/findgradestudentnumbbyID",
            data:{"zid":id},
            success:function (data) {
                if(""!=data){
                    document.getElementById("totalpeople").innerHTML = data.length;
                    var j=0;
                    str+="<table class='exam_table1' id='p_bbox'>";
                    for (var i=0;i<(data.length/4+1);i++) {
                        str += " <tr>";
                        for (; j < 4 * (i + 1); j++) {
                            if (j == data.length) {break;}
                            str += "<th><div class='exam_button1' id='"+data[j].zid+"'>" + data[j].zname +"</div></th>";
                        }
                    }
                    str += "</tr>";
                    str+="</table>";
                    center.html(str)
                    backup = str
                }
            }
        })

    }
</script>
</html>
