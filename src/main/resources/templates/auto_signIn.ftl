<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="./jquery/video-js.min.css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/top_bar.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
    <script src="./layui/js/field_management.js "></script>

    <script src="./layui/js/common.js "></script>

</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-image:url(/images1/background.png);background-size: 100%;height: 100%">
<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 0px solid #c2c2c2;background-color: #114376">
        <div id="cameranamepoint" class="left-bar">

        </div>
        <div class="mid-bar" style="margin-left: 15%">安浩智能学习工厂</div>
        <div class="right-bar" id="m_rightfont" style="margin-left: 20%">浙江工业职业技术学院</div>

    </div>
    <div class="layui-row ">

        <#--三级菜单-->
        <div id='threeMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.4%">

        </div>
        <#--查岗的下级功能表-->
        <div id='checkPointMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.3%">

        </div>
        <#--四级菜单-->
        <div id='fourMenu' class="layui-col-xs1" align="center" style="display:none;width: 26%;font-size: 70px">
            <div id="mainDiv">
                <img id="img" style="width: 400px;height: 300px;" src="intelligence.jpg">

            </div>
            <#--这个地方到时候要循环遍历出来拼接字符串-->
            <div id="identifyAreas"style="width: 80%;height:289px;background-color: #ffff;border: 1px solid red;margin-top: 5%;">

            </div>
        </div>
        <div id='fourMenu1' class="layui-col-xs8" align="center" style="display:none;width: 72%">
            <div style="height: 609px;text-align:center;line-height:40px;font-size: 40px;background-color: #ffff;border: 1px solid red">
                <#--这个也要用遍历写出来的，显示最先出现的三个人-->
                <div style="width: 100%;margin-top: 2px;height: 9%;">
                    <div id="left" style="width: 33%;position: absolute;left:1%;background-color: #BDD7EE;color: red;border: 1px solid;font-size:30px"></div>
                    <div id="middle" style="width: 27%;position: absolute;left:36%;background-color: #BDD7EE;color: red;border: 1px solid;;font-size:30px"></div>
                    <div id="right" style="width: 33%;position: absolute;left:65%;background-color: #BDD7EE;color: red;border: 1px solid;;font-size:30px"></div>
                </div>
                <#--按照顺序排出识别的人脸，顺序是最早的在最下最右边-->
                <div id="mainBody"style="width: 100%;height: 90%;overflow: auto">
                </div>
            </div>
        </div>
    </div>
</div>

<#--隐藏摄像头的ID-->
<div style="display: none" id="cameraID"></div>

<script>

    var layer;
    $(function () {
        layui.use("layer",function () {
            layer =layui.layer;
        });
    })

    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    window.onload=show();
    function show() {
        showAllCamera();
        studentShow();
        OpenOTimer(1)

    }

    //数控车讨论区显示每台机的人脸识别情况
    function studentShow(e){

        document.getElementById("threeMenu").style.display="none";
        //摄像头下面的显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始和结束按钮
        document.getElementById("fourMenu1").style.display="block";
        //显示摄像头的信息
        $("#left").hide();
        $("#middle").hide();
        $("#right").hide();
    }

    //数控铣讨论区显示每台机的人脸识别情况
    function studentShow1(e){
        document.getElementById("checkPointMenu").style.display="none";
        //摄像头下面显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始结束按钮
        document.getElementById("fourMenu1").style.display="block";
        document.getElementById("openAndstart2").style.display="block";
        //显示摄像头的信息
        //showCamera(e);
        /* document.getElementById("mainDiv").style.display="block"*/
        // getMedia();
        $("#left").hide();
        $("#middle").hide();
        $("#right").hide();
    }


    //加载所有的摄像头
    function showAllCamera(){
        $.ajax({
            type:"post",
            url:"/autoFindAllCameras",
            data:{"type":"入口"},
            success:function (data) {
                if(""!=data){
                    console.log(data);
                    document.getElementById("cameranamepoint").innerHTML=data.zcameraName;

                }
            }
        })
    }

    //获取所选摄像头的名称

   /* function selectStr() {
        //摄像头的名字
        var optionvalue = $("#trainingroomselect_auto option:selected").val();


    }*/

</script>
</body>
</html>
