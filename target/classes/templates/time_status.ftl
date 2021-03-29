<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title> 实时状态 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="/layui/layui.js"></script>
    <script type="text/javascript" src="jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/field_management.js "></script>

    <style>
        html,body{
            height: 97%;
        }

    </style>
</head>
<body  class="body" >
<div>
    <script>
        var layer;
        $(function () {
            layui.use("layer",function () {
                layer =layui.layer;
            });
        })
    </script>

</div>
<!--头部导航条-->
<div class="top">
    <div class="leftfont">实时状态</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>

<div style="width: 100%;height: 100%">
<!--机床信息示意-->
    <div id="center" class="i_center" >
    </div>
<!--右侧按键-->
    <div class="i_right" align="center">
        <button  onclick="fieldManagement()"class="f_field_management">现场管理</button>
        <button onclick="informationService()" class="f_field_service">信息查询</button>
        <button id="statusid" class="f_field_status">实时状态</button>
        <button onclick="informationDelivery()" class="f_field_delivery">信息发布</button>
        <button class="f_field_exit" id="exit" onclick="outpower()">退出系统</button>
    </div>
</div>

<div class ="t_hiddenArea" id="hiddenArea">
</div>


<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="parent" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>

</body>

<script>

    //页面加载完成后当前页面的按钮显示橘色
    window.onLoad=aaa();
    function aaa(){
        var servicebutton = document.getElementById("statusid");
        servicebutton.style.backgroundColor="#ED7D31"
    }

    //弹框
    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
        $("#popup").hide();
        $("#exit").css('background-color','#FFC000');
        $("#exit").text('解锁');
        $("#exit").attr("onclick","removescreer();");
    }
    function removescreer(){
       /* $("#parent").hide();
        $("#exit").text('退出系统');
        $("#exit").css('background-color','#4472c4');
        $("#exit").attr("onclick","outpower();");*/
        getMedia1()
    }


    window.onload=showStudentStatus();
       var times=setInterval(function(){
        showStudentStatus()
    }, 5000)





    //学生实时视频
    function diagram(id) {

        var str =$("#status"+id).val();
        var raisehand = new RegExp("举手");
        var leave = new RegExp("请假")
        if(raisehand.test(str)){
            //处理学生的举手请求
            cancelRaisehand(id);
        }
        if(leave.test(str)){
            document.getElementById(id).style.display="block";
        }
    //取消举手
    function cancelRaisehand(id) {
        $.ajax({
            type: 'post',
            url: '/cancelRaisehand',
            data:{"id":id},
            success:function (data) {
                if(data == 1){
                    layer.msg("已处理", { icon: 1, offset: "auto", time:1000 });
                }
            }
        })
        }


    }
    //关闭
    function closemessage(id) {
        $("#t_message"+id).hide()
    }
    //现场管理
    function fieldManagement() {
        location.href="/field_management";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }


    var mediaStreamTrack;
    var time=null;
    function getMedia1() {
        $("#showVdieo").empty();
        let videoComp = "<video id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
        $("#showVdieo").append(videoComp);

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
        promise.then(function (stream) {
            mediaStreamTrack = typeof stream.stop === 'function' ? stream : stream.getTracks()[1];
            video.srcObject = stream;
            video.play();
        });

        // var t1 = window.setTimeout(function() {
        //     takePhoto();
        // },2000)
        time= window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
            chooseFileChangeCompT_S()
        }, 5000);

    }

    function chooseFileChangeCompT_S() {

        /* var ip=returnCitySN["cip"];*/


        let showVdieo = $("#showVdieo");
        if (showVdieo.has('video').length) {
            let video = document.getElementById("video");
            let canvas = document.getElementById("canvas");
            let ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, 500, 500);
            var base64File = canvas.toDataURL();
            var formData = new FormData();
            formData.append("groupId", "101")
            formData.append("file", base64File);

            //var data=getOsInfo();

            //操作系统

            //ip地址
            formData.append("ip",1);

            $.ajax({
                type: "post",
                url: "/faceTeacherSearch",
                data: formData,
                contentType: false,
                processData: false,
                async: false,
                success: function (text) {
                    var res = JSON.stringify(text)
                    if (text.code == 0) {
                        var name = text.data.name;
                        $("#nameDiv").html("姓名：" + name);
                        var similar = text.data.similarValue;
                        $("#similarDiv").html("相似度：" + similar + "%");
                        var age = text.data.age;
                        $("#ageDiv").html("年龄：" + age);
                        var gender = text.data.gender;
                        $("#genderDiv").html("性别：" + gender);
                        mediaStreamTrack.stop();
                        $("#showVdieo").hide();
                        $("#parent").hide();
                        $("#exit").text('退出系统');
                        $("#exit").css('background-color','#4472c4');
                        $("#exit").attr("onclick","outpower();");
                        clearInterval(time);
                    } else {
                        $("#nameDiv").html("");
                        $("#similarDiv").html("");
                        $("#ageDiv").html("");
                        $("#genderDiv").html("");

                        showTips(text.message);
                    }

                },
                error: function (error) {
                    $("#nameDiv").html("");
                    $("#similarDiv").html("");
                    $("#ageDiv").html("");
                    $("#genderDiv").html("");
                    alert(JSON.stringify(error))
                }
            });
        }
    }



</script>

</html>