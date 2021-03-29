    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统222222</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin"  style="background-color: #CDCDCD">
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #CDCDCD">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs10" align="center" style="left: 150px">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                欢迎使用安浩智学习工厂
            </div>
            <#--//摄像头的位置-->
            <div id="regcoDiv"></div>

            <#--<div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='images/shibie.jpg'style='width: 15rem;height: 16rem;'></div>-->
            <#--<div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:20px;color:#0C0C0C"> 进入安浩智能学习工厂</div>-->
            <#--<div>
                <button style="color:#FFFFFF;height: 75px;display:block;margin:0 auto;margin-top:0px;width:211px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 32px">
                    开始测试
                </button>
            </div>-->
        </div>
        <div class="layui-col-xs1" align="right" style="height:700px;left: 124px;border-left: 1px solid #c2c2c2;">
            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    现场管理
                </button>
            </div>
            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息查询
                </button>
            </div>
            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    实时状态
                </button>
            </div>
            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                    信息发布
                </button>
            </div>
            <div>
                <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:70px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 27px">
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
        let vedioComp = "<video id='video2' width='600px' height='400px' autoplay='autoplay' style='margin-top: 20px'></video><canvas id='canvas2' width='500px' height='500px' style='display: none'></canvas>";
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

    //去后台查询人脸数据
    function chooseFileChangeComp() {
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
                        var name = text.data.name;
                        $("#nameDiv").html("姓名：" + name);
                        var similar = text.data.similarValue;
                        $("#similarDiv").html("相似度：" + similar + "%");
                        var age = text.data.age;
                        $("#ageDiv").html("年龄：" + age);
                        var gender = text.data.gender;
                        $("#genderDiv").html("性别：" + gender);
                        // img.css("background-image", 'url(' + text.data.image + ')');
                        //alert("姓名：" + name +"\n相似度：" + similar + "%" + "\n年龄：" + age +"\n性别：" + gender);
                        // layer.msg("姓名：" + name +"\n相似度：" + similar + "%" + "\n年龄：" + age +"\n性别：" + gender);
                        //自定义提示框
                        location.href = "/teachRegister";
                        //showTips( "姓名：" + name +"\n相似度：" + similar + "%" + "\n年龄：" + age +"\n性别：" + gender);
                    } else {
                        $("#nameDiv").html("");
                        $("#similarDiv").html("");
                        $("#ageDiv").html("");
                        $("#genderDiv").html("");
                        //alert("人脸不匹配");
                        //自定义提示框
                        showTips("人脸不匹配");
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
                            var name = text.data.name;
                            // $("#nameDiv").html("姓名：" + name);
                            var similar = text.data.similarValue;
                            // $("#similarDiv").html("相似度：" + similar + "%");
                            var age = text.data.age;
                            // $("#ageDiv").html("年龄：" + age);
                            var gender = text.data.gender;
                            // $("#genderDiv").html("性别：" + gender);
                            // img.css("background-image", 'url(' + text.data.image + ')');
                            //alert("姓名：" + name +"\n相似度：" + similar + "%" + "\n年龄：" + age +"\n性别：" + gender);
                            //自定义提示框
                            showTips("姓名：" + name + "\n相似度：" + similar + "%" + "\n年龄：" + age + "\n性别：" + gender);
                        } else {
                            $("#nameDiv").html("");
                            $("#similarDiv").html("");
                            $("#ageDiv").html("");
                            $("#genderDiv").html("");
                            alert("人脸不匹配")
                            showTips("人脸不匹配");
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
</script>

</body>
</html>