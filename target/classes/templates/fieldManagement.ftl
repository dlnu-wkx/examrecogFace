<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div id='welcomeField' class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
            欢迎
        </div>
        <div id='teach' class="layui-col-xs9" align="center" style="width: 74%">
            <div style="margin: 0,auto;margin-top:40px;height:80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                ${name}老师
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='${path}'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C"> 进入安浩智能学习工厂</div>
        </div>
         <#--二级菜单-->
       <#-- <div id='twoMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="faceShow()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">人脸识别</button></li>
                    <li style=" margin: 0 auto;margin-right: 10%"><button onclick="powerController()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">设备电源</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 30% auto;width: 80% ">
                    <li style="margin-left: 10%"><button id="checkPointColor" onclick="checkPoint()" style="float:left;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">查岗</button></li>
                    <li style="margin-right: 10%"><button  id="courseRecordColor" onclick="courseRecord()" style="float:right;color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">课堂记录</button></li>
                </ul>
            </div>
        </div>
        <div id="courseRecordContent"style="display: none;width: 50%;height: 300px;position: fixed;background-color: #CDCDCD;border: 1px solid;top:50%;left: 7%;z-index: 3">
              <div style="margin: 10px auto;width: 29%">课堂记录</div>
              <div style="margin: 10px auto;background-color: #ffffff;width: 90%;height: 200px"><textarea style="width: 100%;height: 100%"></textarea></div>
            <div style="position: absolute;left: 15%"><button style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">确定</button></div>
            <div style="position: absolute;left: 60%"><button onclick="cancel()" style="background-color: #0000FF;height: 30px;width: 81px;border-radius: 10px;color: #ffffff">取消</button></div>
        </div>
        <div id="hiddenArea"style="position: absolute;height: 100%;width: 100%;filter: alpha(opacity=60);opacity: 0.6;display: none;z-index: 2">
        </div>
        &lt;#&ndash;三级菜单&ndash;&gt;
        <div id='threeMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 10% "><button onclick="studentShow(this.value)" value="1" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控车讨论区</button></li>
                    <li style=" margin: 0 auto;margin-right: 10%"><button onclick="studentShow1(this.value)" value="1" style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控铣讨论区</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 15% auto;width: 80% ">
                    <li style="margin-left: 10%"><button  style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">东大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">东大门出口</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 25% auto;width: 80% ">
                    <li style="margin-left: 10%"><button style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">西大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">西大门出口</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 35% auto;width: 80% ">
                    <li style="margin-left: 10%"><button style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">北大门入口</button></li>
                    <li style="margin-right: 10%"><button style="float:right;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">北大门出口</button></li>
                </ul>
            </div>
        </div>
        &lt;#&ndash;查岗的下级功能表&ndash;&gt;
        <div id='checkPointMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 88%">
            <div>
                <ul style="margin-top: 10%;width: 80%;left: 40%;margin: 37px auto">
                    <li style="margin: 0 auto;margin-left: 46% "><button onclick="studentShow(this.value)" value="2" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控区实训区</button></li>
                </ul>
            </div>
            <div>
                <ul style="margin: 30% auto;width: 80% ">
                    <li style="margin-left: 46%"><button  onclick="studentShow1(this.value)" value="2" style="float:left;color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:250px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 35px">数控铣实训区</button></li>
                </ul>
            </div>
        </div>-->
        <#--四级菜单-->


        <#--右边功能按钮-->
        <div class="layui-col-xs1" align="right" style="height:100%;width: 9%;border-left: 1px solid #c2c2c2;">
            <div>
                <button id='colorType' onclick="fieldManagement()"  style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    现场管理
                </button>
            </div>
            <div>
                <button onclick="informationService()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息查询
                </button>
            </div>
            <div>
                <button onclick="timeStatus()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    实时状态
                </button>
            </div>
            <div>
                <button onclick="informationDelivery()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息发布
                </button>
            </div>
            <div>
                <button onclick="powerController()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:70px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 27px">
                    退出系统
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    //做出一个判断如果现场管理一直是橘黄色则无法点击
    function fieldManagement() {
        location.href="/field_management"
        //location.href="/teachRegister"
    }
    //展示现场管理的二级菜单
   /* function show() {
        document.getElementById("colorType").style.backgroundColor="#ED7D31";
        document.getElementById("welcomeField").style.display="none";
        document.getElementById("teach").style.display="none";
        document.getElementById("twoMenu").style.display="block";
    }
    //展示
    function faceShow() {
        document.getElementById("twoMenu").style.display="none";
        document.getElementById("threeMenu").style.display="block";
    }
    //数控车讨论区显示每台机的人脸识别情况
    function studentShow(e){
        var a =e;
        if(a==2){
            document.getElementById("checkPointMenu").style.display="none";
        }
        if(a==1){
            document.getElementById("threeMenu").style.display="none";
        }

        document.getElementById("fourMenu").style.display="block";
        document.getElementById("fourMenu1").style.display="block";
        getMedia();
    }
    //数控铣讨论区显示每台机的人脸识别情况
    function studentShow1(e){
        var b =e;
        if(b==2){
            document.getElementById("checkPointMenu").style.display="none";
        }
        if(b==1){
            document.getElementById("threeMenu").style.display="none";
        }

        document.getElementById("fourMenu").style.display="block";
        document.getElementById("fourMenu1").style.display="block";
        getMedia();
    }*/
    //获取摄像头
    function getMedia() {
        $("#mainDiv").empty();
        let videoComp = " <video muted id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
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
   /*function checkPoint() {
       document.getElementById("checkPointColor").style.backgroundColor="#ED7D31";
       document.getElementById("twoMenu").style.display="none";
       document.getElementById("checkPointMenu").style.display="block";
   }
   //从数据库中显示已经检测到的人脸信息
    function showRecognitionFace() {
        alert("目前还没有检测到人脸");
    }*/
    //课堂记录
    /*function courseRecord() {
        document.getElementById("courseRecordColor").style.backgroundColor="#ED7D31";
        document.getElementById("hiddenArea").style.display="block";
        document.getElementById("courseRecordContent").style.display="block";
    }
    //课程记录中的取消按钮
    function cancel() {
        document.getElementById("hiddenArea").style.display="none";
        document.getElementById("courseRecordContent").style.display="none";
        document.getElementById("courseRecordColor").style.backgroundColor="#71B863";
    }*/
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
        location.href="/time_status";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }

</script>
</body>
</html>