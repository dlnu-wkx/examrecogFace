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
