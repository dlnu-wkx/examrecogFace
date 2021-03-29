


<head>
    <meta charset="UTF-8">
    <#--给https添加可以访问的组件-->
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

   <#-- <script type="text/javascript" src="http://ip.chinaz.com/getip.aspx"></script>-->
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
</head>
<div class="layui-row">
    <div class="layui-col-xs7 layui-col-md-offset3" align="center">
        <div style="margin: 0,auto; width: 800px;height: 80px;background-color: #383939">
            <div style="margin: 0,auto;height: 80px;text-align:center;line-height:80px;font-size: 40px;color: #E51C23">
                人脸识别系统
            </div>
        </div>

        <div id="regcoDiv">

        </div>

        <div>
            <img id="imageDivComp"/>
        </div>

    </div>

</div>
<div class="facetest_btn">

    <button style="color:#FFFFFF;height: 30px;display:block;margin:0 auto;margin-top:10px;width:120px;background-color: #3F51B5;border-radius:5px;text-align: center;line-height: 30px;font-size: 20px"
            onclick="imageTo()">照片识别
    </button>
    <br><br>
    <button id="snap"
            style="color:#FFFFFF;height: 30px;display:block;margin:0 auto;margin-top:10px;width:100px;background-color: #3F51B5;border-radius:5px;text-align: center;line-height: 30px;font-size: 20px"
            onclick="chooseFileChangeComp()">提交
    </button>
</div>

<script>
    /* window.onload=function () {//页面打开直接加载这个方法
         getMedia2();
     }*/

    function chooseFile() {
        $("#file1").trigger('click');
    }

    function imageTo() {
        $("#regcoDiv").empty();
        let imageInput = "<h2>点击图片区域上传文件</h2><input style='display: none' type='file' name='file1' id='file1' multiple='multiple' /><br><img src='images/shibie.jpg' onclick='chooseFile()' id='img0' style='width: 30rem;height: 25rem;'>";
        $("#regcoDiv").append(imageInput);

    }

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
        let vedioComp = "<video id='video2' width='500px' height='500px' autoplay='autoplay' style='margin-top: 20px'></video><canvas id='canvas2' width='500px' height='500px' style='display: none'></canvas>";
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
        //关闭
        //document.getElementById("recognitionFaceId").style.display = "none";
        window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
            chooseFileChangeComp()
        }, 5000);
        /*var t1 = window.setTimeout(function() {
            chooseFileChangeComp()
        },2000);*/
    }

    //去后台查询人脸数据
    function chooseFileChangeComp() {
        //alert("进入")
        var ip=returnCitySN["cip"];

        var file = $("#file1")[0].files[0];
        if (file == undefined) {
            alert("请选择有人脸的图片进行识别");
            return;
        }

        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            //alert("load图片")
            var base64 = reader.result;
            //alert(base64);
            var formData = new FormData();
            formData.append("file", base64);
            formData.append("groupId", "101");
            //操作系统
            formData.append("ztype", "windows");
            //ip地址
            formData.append("ip", ip);

            //alert(formData);
            $.ajax({
                type: "post",
                url: "/faceSearch",
                data: formData,
                contentType: false,
                processData: false,
                async: false,
                success: function (text) {
                   // alert(1)
                    var res = JSON.stringify(text)

                    if (text.code == 0) {
                        location.href = "/login";
                    } else {
                        if (text.code == 16) {
                            alert("人脸信息表（user_face_info表）里没有地址信息");
                        } else if (text.code == 17) {
                            alert("学生表（zstudent表）里没有人脸id(faceid),请添加人脸信息");
                        } else if (text.code == 18) {
                            alert("设备表（ztraining_facility）中没有这台设备的Ip(zsutdentPCIP)");
                        } else if (text.code == 19) {
                            alert("设备表（ztraining_facility）中没有这台设备的所处的实训室(ztrainingroomID)");
                        } else if (text.code == 23) {
                            alert("学生上课表(zstudent_schedule)没有该名学生的信息");
                        } else {
                            alert(text.code)
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
