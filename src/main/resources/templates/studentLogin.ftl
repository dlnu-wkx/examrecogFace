<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <#--给https添加可以访问的组件-->
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <#--<script type="text/javascript" src="http://ip.chinaz.com/getip.aspx"></script>-->
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>

</head>
<body class="layui-layout-body">

<!--警示消息-->
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


<div class="layui-layout layui-layout-admin"  style="background-image:url(/images1/background.png); background-size: 100%">
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 23px;left:14px;letter-spacing:4px;color: #ffffff">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;font-size: 23px; color: #ffffff">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs10" align="center" style="left: 150px">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #ffffff">
                欢迎使用安浩智能学习工厂
            </div>
            <!--摄像头的位置-->
          <div id="regcoDiv"></div>

        </div>
        <div class="layui-col-xs1" align="right" style="height:700px;left: 75px;border-left: 0px solid #c2c2c2;">
            <div>
        <button  style="pointer-events:none;color:#FFFFFF;height: 73px;display:block;margin:0 auto;margin-top:35px;width:102px;background-image: url(/images1/raise_hands.png); background-size: 100%; border-radius:14px;text-align: center;line-height: 30px;font-size: 20px">
            举手
        </button>
    </div>
    <div>
        <button  style="pointer-events:none;color:#FFFFFF;height: 73px;display:block;margin:0 auto;margin-top:70px;width:102px;border-width: 0px;background-image: url(/images1/raise_hands.png); background-size: 100%; border-radius:14px;text-align: center;line-height: 30px;font-size: 20px">
            请假
        </button>
    </div>
    <div>
        <button style="color:#FFFFFF;height: 100px;display:block;margin:0 auto;margin-top:235px;width:100px; border-radius: 50%;background-color: #123360;border-width:0px;text-align: center;line-height: 27px;font-size: 20px">
            退出系统
        </button>
    </div>
</div>
    </div>
</div>


<script>

    $(document).on("change", "#file1", function () {
        var objUrl = getObjectURL(this.files[0]);//获取文件信息
        console.log("objUrl = " + objUrl);
        if (objUrl) {
            $("#img0").attr("src", objUrl);
        }
    });



    function chooseFile() {
        $("#file1").trigger('click');
    }


    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    $(function () {
        getMedia2();
    })
    //从fac_search.ftl拿出来的代码
    function chooseFile() {
        $("#file1").trigger('click');
    }
    //相片对比功能
    /*function imageTo() {
        $("#regcoDiv").empty();
        let imageInput = "<h2>点击图片区域上传文件</h2><input style='display: none' type='file' name='file1' id='file1' multiple='multiple' /><br><img src='images/shibie.jpg' onclick='chooseFile()' id='img0' style='width: 30rem;height: 25rem;'>";
        $("#regcoDiv").append(imageInput);

    }*/

    $(document).on("change", "#file1", function () {
        var objUrl = getObjectURL(this.files[0]);//获取文件信息
        console.log("objUrl = " + objUrl);
        if (objUrl) {
            $("#img0").attr("src", objUrl);
        }
    });

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    $("#imageDivComp").click(function () {
        $("#chooseFileComp").click();

    });

    function getMedia2() {
        $("#regcoDiv").empty();
        let vedioComp = "<video muted id='video2' width='600px' height='400px' autoplay='autoplay' style='margin-top: 20px'></video><canvas id='canvas2' width='500px' height='500px' style='display: none'></canvas>";
        $("#regcoDiv").append(vedioComp);
        let constraints = {
            video: {width: 500, height: 500},
            audio: true
        };
        //获得video摄像头区域
        let video = document.getElementById("video2");
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
        /*
        * 这个一定要注释掉不然不会跳转页面
        * weikaixuan 2020-10-23
        * */
        /*document.getElementById("recognitionFaceId").style.display = "none";*/
        window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比

            chooseFileChangeComp()
        }, 5000);
        /*var t1 = window.setTimeout(function() {
            chooseFileChangeComp()
        },2000);*/
    }


    //获取内网ip



    //去后台查询人脸数据
    function chooseFileChangeComp() {

        var ip=returnCitySN["cip"];

        //alert(ip);
        //alert(getUserIP(ip));

        //document.write(returnCitySN["cip"]+','+returnCitySN["cname"] + "真实IP地址")

        let regcoDivComp = $("#regcoDiv");
        if (regcoDivComp.has('video').length) {
            let video = document.getElementById("video2");
            let canvas = document.getElementById("canvas2");
            let ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, 500, 500);
            var base64File = canvas.toDataURL();
            var formData = new FormData();
            formData.append("groupId", "101")
            formData.append("file", base64File);

            var data=getOsInfo();

            //操作系统
            formData.append("ztype",data.version);
            //ip地址
            formData.append("ip",ip);

            $.ajax({
                type: "post",
                url: "/faceSearch",
                data: formData,
                contentType: false,
                processData: false,
                async: false,
                success: function (text) {
                    var res = JSON.stringify(text)

                    if (text.code == 0) {
                        location.href = "/login";
                    } else {
                        if(text.code==16){
                           // alert("人脸信息表（user_face_info表）里没有地址信息");
                            layer.msg("人脸信息表（user_face_info表）里没有地址信息", { icon: 1, offset: "auto", time:2000 });
                        }
                        else if(text.code==17){
                            layer.msg("学生表（zstudent表）里没有人脸id(faceid)或者没有审核通过,请添加人脸信息", { icon: 1, offset: "auto", time:2000 });
                            //alert("学生表（zstudent表）里没有人脸id(faceid)或者没有审核通过,请添加人脸信息");
                        }
                        else if (text.code==18){
                            //alert("设备表（ztraining_facility）中没有这台设备的Ip(zsutdentPCIP)");
                            layer.msg("设备表（ztraining_facility）中没有这台设备的Ip(zsutdentPCIP)", { icon: 1, offset: "auto", time:2000 });
                        }
                        else if (text.code==19){
                            //alert("设备表（ztraining_facility）中没有这台设备的所处的实训室(ztrainingroomID)");
                            layer.msg("设备表（ztraining_facility）中没有这台设备的所处的实训室(ztrainingroomID)", { icon: 1, offset: "auto", time:2000 });
                        }else if (text.code==23){
                            layer.msg("学生上课表(zstudent_schedule)没有该名学生的信息", { icon: 1, offset: "auto", time:2000 });
                            //alert("学生上课表(zstudent_schedule)没有该名学生的信息");
                        } else if (text.code==30){
                            layer.msg("不可以使用图片登录", { icon: 1, offset: "auto", time:2000 });
                            //alert("学生上课表(zstudent_schedule)没有该名学生的信息");
                        }
                        else{
                            //alert("摄像头未开或其它错误")
                            layer.msg("摄像头未开或人脸识别失败", { icon: 1, offset: "auto", time:2000 });
                        }

                    }

                },
                error: function (error) {

                    alert(JSON.stringify(error))
                }
            });
        } else {//这个对比根据图片进行查找的
          var file = $("#file1")[0].files[0];
            if (file == undefined) {
                alert("请选择有人脸的图片进行识别");
                return;
            }
            var formData = new FormData();
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                var base64 = reader.result;
                formData.append("file", base64);
                formData.append("groupId", 101);
                var data=getOsInfo();

                //操作系统
                formData.append("ztype",data.version);
                //ip地址
                formData.append("ip",ip);
                alert(formData)
                $.ajax({
                    type: "post",
                    url: "/faceSearch",
                    data: formData,
                    contentType: false,
                    processData: false,
                    async: false,
                    success: function (text) {
                        alert(1)
                        var res = JSON.stringify(text)

                        if (text.code == 0) {
                            location.href = "/login";
                        } else {
                            if(text.code==16){
                                alert("人脸信息表（user_face_info表）里没有地址信息");
                            }
                            else if(text.code==17){
                                alert("学生表（zstudent表）里没有人脸id(faceid),请添加人脸信息");
                            }
                            else if (text.code==18){
                                alert("设备表（ztraining_facility）中没有这台设备的Ip(zsutdentPCIP)");
                            }
                            else if (text.code==19){
                                alert("设备表（ztraining_facility）中没有这台设备的所处的实训室(ztrainingroomID)");
                            }else if (text.code==23){
                                alert("学生上课表(zstudent_schedule)没有该名学生的信息");
                            }else if (text.code==31){
                                alert("不能重复登陆,请退出当前登陆的机器");
                            }
                            else{
                                alert("摄像头未开或其它错误")
                            }

                        }

                    },
                    error: function (error) {
                        //alert(0)
                        alert(JSON.stringify(error))
                    }
                });
            }
        }
    }

    function home() {
        location.href = "http://localhost:8080/demo"
    }

    function showTips(content) {
        //窗口的宽度
        height = 200;
        var windowWidth = $(window).width();
        var tipsDiv = '<div class="tipsClass">' + content + '</div>';
        $('body').append(tipsDiv);
        $('div.tipsClass').css({
            'top': height + 'px',
            'left': (windowWidth / 2) - 350 / 2 + 'px',
            'position': 'absolute',
            'padding': '3px 5px',
            'background': '#fff',
            'font-size': 20 + 'px',
            'margin': '0 auto',
            'text-align': 'center',
            'width': '350px',
            'height': 'auto',
            'color': '#fc2b2e',
            'opacity': '1.0'
        }).show();
        setTimeout(function () {
            $('div.tipsClass').fadeOut();
        }, 2000);
    }

    //获取操作系统
    function getOsInfo() {
        var userAgent = navigator.userAgent.toLowerCase();
        var name = 'Unknown';
        var version = 'Unknown';
        if (userAgent.indexOf('win') > -1) {
            name = 'Windows';
            if (userAgent.indexOf('windows nt 5.0') > -1) {
                version = 'Windows 2000';
            } else if (userAgent.indexOf('windows nt 5.1') > -1 || userAgent.indexOf('windows nt 5.2') > -1) {
                version = 'Windows XP';
            } else if (userAgent.indexOf('windows nt 6.0') > -1) {
                version = 'Windows Vista';
            } else if (userAgent.indexOf('windows nt 6.1') > -1 || userAgent.indexOf('windows 7') > -1) {
                version = 'Windows 7';
            } else if (userAgent.indexOf('windows nt 6.2') > -1 || userAgent.indexOf('windows 8') > -1) {
                version = 'Windows 8';
            } else if (userAgent.indexOf('windows nt 6.3') > -1) {
                version = 'Windows 8.1';
            } else if (userAgent.indexOf('windows nt 6.2') > -1 || userAgent.indexOf('windows nt 10.0') > -1) {
                version = 'Windows 10';
            } else {
                version = 'Unknown';
            }
        } else if (userAgent.indexOf('iphone') > -1) {
            name = 'Iphone';
        } else if (userAgent.indexOf('mac') > -1) {
            name = 'Mac';
        } else if (userAgent.indexOf('x11') > -1 || userAgent.indexOf('unix') > -1 || userAgent.indexOf('sunname') > -1 || userAgent.indexOf('bsd') > -1) {
            name = 'Unix';
        } else if (userAgent.indexOf('linux') > -1) {
            if (userAgent.indexOf('android') > -1) {
                name = 'Android';
            } else {
                name = 'Linux';
            }
        } else {
            name = 'Unknown';
        }
        return { name, version };
    }



</script>

</body>
</html>
