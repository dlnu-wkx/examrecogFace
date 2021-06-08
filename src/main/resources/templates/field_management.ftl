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
    <script src="./layui/js/exit.js "></script>
    <script src="./layui/js/common.js "></script>

</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-image:url(/images1/background.png);background-size: 100%;height: 100%">
<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 0px solid #c2c2c2;background-color: #114376">
        <div id ="cameranamepoint" style="display: none"></div>
        <div class="left-bar" ><span id="trainroomname"></span>/现场管理</div>

        <div class="mid-bar">安浩智能学习工厂</div>
        <div class="right-bar" id="m_rightfont"></div>

    </div>
    <div class="layui-row ">
        <div id='welcomeField' class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
        </div>
        <div id='teach' class="layui-col-xs9" align="center" style="width: 74%">
            <div style="margin: 0,auto;margin-top:40px;height:80px;text-align:center;line-height:40px;font-size: 40px;color: #e51c23">
            </div>

        </div>
        <#--二级菜单-->
        <div id='twoMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;width: 90%">
            <div>
                <ul >
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="signIn()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">点名签到</button></li>
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="faceShow()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 12%;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">自主签到</button></li>
                    <li style="margin: 0 auto;margin-left: 10% "><button id="checkPointColor" onclick="checkPoint()"  style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">查岗</button></li>
                    <#--<li style=" margin: 0 auto;margin-right: 10%"><button onclick="studentStatus()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">测试管理</button></li>-->
                </ul>
            </div>
            <div style="margin-top: 10%">
                <ul >
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="groupManagement()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">群组管理</button></li>
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="courseManagement()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 12%;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">计划任务</button></li>
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="informationDelivery()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">现场指派</button></li>
                </ul>
            </div>
            <div style="margin-top: 20%">
                <ul >
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="measure()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">测量</button></li>
                    <li style="margin-left: 10%"><button  onclick="loginManagement()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 12%;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">登录管理</button></li>
                    <li style="margin-left: 10%"><button  onclick="showinformation()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">信息展板</button></li>

            </div>
            <div style="margin-top: 30%">
                <ul>
                    <li style="margin-left: 10%"><button  id="courseRecordColor" onclick="courseRecord()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">课堂记录</button></li>
                </ul>
                </ul>
                </ul>
            </div>
        </div>
        <div id="courseRecordContent"style="display: none;width: 50%;height: 300px;position: fixed;background-color: #CDCDCD;border: 1px solid;top:50%;left: 7%;z-index: 12">
            <div style="margin: 5px auto;width: 29%">
                <div style="float: left">课堂记录</div>
                <select id="valueSelect" style="float: right">
                    <option value="正常">正常</option>
                    <option value="事故">事故</option>
                    <option value="冲突">冲突</option>
                    <option value="其他">其他</option>
                </select>
            </div>
            <div style="margin: 29px auto;background-color: #ffffff;width: 90%;height: 200px"><textarea id="textContent" style="width: 100%;height: 100%"></textarea></div>
            <div style="position: absolute;left: 15%"><button onclick="subContent()" style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">确定</button></div>
            <div style="position: absolute;left: 60%"><button onclick="cancel()" style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">取消</button></div>
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
                <img id="img" style="width: 400px;height: 300px;" src="">

            </div>
            <#--这个地方到时候要循环遍历出来拼接字符串-->
            <div id="identifyAreas"style="width: 80%;height:289px;background-color: #ffff;border: 1px solid red;margin-top: 5%;">

            </div>
        </div>
        <div id='fourMenu1' class="layui-col-xs8" align="center" style="display:none;width: 64%">
            <div style="height: 500px;text-align:center;line-height:40px;font-size: 40px;background-color: #ffff;border: 1px solid red">
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
            <#--人脸识别时显示的开始和结束按钮-->
            <div id="openAndstart"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 50%">
                    <button id="startID" onclick="OpenOTimer(this.value)" value="1" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:right;width: 50%">
                    <button id="endID"onclick="CloseTimer(this.value)" value="1" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">结束</button>
                </div>
            </div>
            <#--查岗时候显示的开始和结束按钮-->
            <div id="openAndstart2"style="display: none">
                <div style="float:left;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:left;width: 50%">
                    <button id="startID2" onclick="OpenOTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px">开始</button>
                </div>
                <div style="text-align:center;line-height:80px;font-size:34px;color:#0C0C0C;float:right;width: 50%">
                    <button id="endID2"onclick="CloseTimer(this.value)" value="2" style="background-color: blue;color: #ffff;border-radius:32px;width: 150px;z-index: 12;position: absolute;top:88%">结束</button>
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

                <button  class="f_field_delivery" >
                    信息发布
                </button>

                <button id="exit" onclick="outpower()" class="f_field_exit">
                    退出系统
                </button>

        </div>
    </div>
</div>

<#--隐藏摄像头的ID-->
<div style="display: none" id="cameraID"></div>
<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="facebox_exit" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>

<script>

    //教师解锁时的人脸识别






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
   /*window.onLoad=showCamera();*/
    //展示现场管理的二级菜单
    function show() {
        document.getElementById("colorType").style.backgroundColor="#ED7D31";
        document.getElementById("welcomeField").style.display="none";
        document.getElementById("teach").style.display="none";
        document.getElementById("twoMenu").style.display="block";
        loadteachername();
        gettrainroom();
    }
    //展示信息展板
    function showinformation() {
        location.href = "/informationPanel"
    }
    //群组管理
    function groupManagement() {
        location.href="/group_management"
    }
    //点名签到
    function signIn() {
        location.href="/exam_field_management"
    }

    //登录管理
    function loginManagement() {
        location.href="/loginManagement"
    }
    //课程管理
    function courseManagement() {
        location.href="/course"
    }
    //展示
    function faceShow() {
        document.getElementById("twoMenu").style.display="none";
        //遍历所有的实训室的摄像装备

        findAllCameras();


        document.getElementById("threeMenu").style.display="block";
    }
    //数控车讨论区显示每台机的人脸识别情况
    function studentShow(e){
       //$("#cameraID").val("改变成功")
        document.getElementById("cameraID").innerHTML = e
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
    function showCamera(e) {
        var info ={username:"admin",password:"djtu13840903462",channel:"1",ip:"192.168.1.64",stream:"main"}
        $.ajax({
            type: 'post',
            url: '/cameras',
            contentType:'application/json',
            data:  JSON.stringify(info) ,
            dataType:'json',
            success:function (data) {
                if(data.code==0){
                    var src=data.url;
                    console.log(src)
                    document.getElementById("videosrc").src=src;
                    document.getElementById("video").load();
                    var player = videojs('video', {autoplay:true,preload:"auto"}, function onPlayerReady(){ console.log("视频准备好了")})

                }

            }
        })
    }
    //数控铣讨论区显示每台机的人脸识别情况
    function studentShow1(zcameraIP,zcameraName){
        //把摄像头的名字写到相应的位置上
        document.getElementById("cameranamepoint").innerHTML = zcameraName;

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
    //获取摄像头
    /*function getMedia(){

    }*/

   function getMedia() {
        $("#mainDiv").empty();
        let videoComp = " <video id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
        $("#mainDiv").append(videoComp);

        let constraints = {
            video: {width: 500, height: 500},
            audio: true
        };
        //获得video摄像头区域
        let video = document.getElementById("video");
        //这里介绍新的方法，返回一个 Promise对象
        // 这个Promise对象返回成功后的回调函数带一个 MediaStream 对象作为其参数
        // then()是Promise对象里的方法
        // then()方法是异步执行，当then()前的方法执行完后再执行then()内部的程序
        // 避免数据没有获取到
        let promise = navigator.mediaDevices.getUserMedia(constraints);
        promise.then(function (MediaStream) {
            video.srcObject = MediaStream;
            video.play();
        });

        // var t1 = window.setTimeout(function() {
        //     takePhoto();
        // },2000)
    }
    //查岗功能
    function checkPoint() {
        document.getElementById("checkPointColor").style.backgroundColor="#ED7D31";
        document.getElementById("twoMenu").style.display="none";
        //找出所有实训室所有的种类的摄像头
        CheckPointFindAllCameras("查岗");
        document.getElementById("checkPointMenu").style.display="block";

    }

    //课堂记录
    function courseRecord() {
        document.getElementById("courseRecordColor").style.backgroundColor="#ED7D31";
        document.getElementById("hiddenArea").style.display="block";
        document.getElementById("courseRecordContent").style.display="block";


    }
    //课程记录中的取消按钮
    function cancel() {
        document.getElementById("hiddenArea").style.display="none";
        document.getElementById("courseRecordContent").style.display="none";
        document.getElementById("courseRecordColor").style.backgroundColor="#71B863";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //实时状态
    function timeStatus() {
        location.href="/student_status";
    }
    //退出
    function studentStatus() {
        location.href="/student_status";
    }

    function fieldManagement() {
        location.href="/field_management";
    }


    function measure() {
        location.href="/teacher_measure";
    }

    //关闭浏览器时执行的函数
    window.onbeforeunload = onbeforeunload_handler;
    function onbeforeunload_handler(){
        if($("#startID2").css("background-color") == "rgb(237, 125, 49)"){
            $.ajax({
                type: "post",
                url: "/delCheckPoint",
                data:{},
                success: function (data){

                }
            })
        }

    }



</script>
</body>
</html>
