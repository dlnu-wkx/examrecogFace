<head>

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link rel="stylesheet" href="./layui/css/register.css">
    <link href="./layui/css/fixed_task.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <script src="./layui/layui.js"></script>

</head>

<body class="body">

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
<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >用户注册</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧摄像头-->
<div id="mainDiv" class="mainDiv">

</div>

<!--右侧注册信息部分-->
<div class="re_rightmes" align="center">
 <table class="r_table1" id="r_table1">
     <tr id="userkind">
         <th class="r_title1">用户类型：</th>
         <th>
             <select class="t_select" id="r_userkind">
                 <option value ="学生">学生</option>
                 <option value ="教师">教师</option>
                 <option value="管理员">管理员</option>
             </select>
         </th>
     </tr>
     <tr id="idcardtr">
         <th id="idcardtitle">
             学号:
         </th>
         <th>
             <input type="text" class="t_select" id="zidentity">
         </th>
     </tr>
     <tr id="nametr">
         <th>
             姓名:
         <th>
             <input type="text" class="t_select" id="userName">
         </th>
     </tr>
     <tr id="sextr">
         <th>
             性别:
         </th>
         <th>
             <label><input type="checkbox" id="sex" class="sex1" value="男">男</label>
             &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
             <label><input type="checkbox" id="sex" class="sex2" value="女">女</label>
         </th>
     </tr>
    <tr id="passtr">
         <th>
             密码:
         </th>
         <th>
             <input type="text" class="t_select" id="password">
         </th>
     </tr>
    <tr id="phonetr">
         <th>
             电话:
         </th>
         <th>
             <input type="number" class="t_select" id="zphone">
         </th>
     </tr>


 </table>

</div>

<button class="r_button" id="r_button" onclick="takePhoto()">注册</button>


<div class="reg_namelike" id="reg_namelike" hidden>


</div>

</body>
<script>



    function chosename(cname){
        //alert(cname)


        $("#reg_namelike").hide()
        $("#zidentity").val(cname)


        //按键修改
        $("#r_button").css('background-color','#FFC000');
        $("#r_button").text('修改信息');

        var t_test=$('#r_userkind').val();
        //alert(t_test);
        if(t_test=="教师"){
            $.ajax({
                type: "post",
                url: "/findallbyteachername",
                async: false,
                data: { "name": cname},
                success: function (data) {
                    if (data.zsex=="男")
                        $(".sex1").prop("checked",true);
                    else
                        $(".sex2").prop("checked",true);
                    $("#userName").val(data.zname)
                    $("#password").val(data.zpass)
                    $("#zphone").val(data.zphone)
                    //专业在此处更新
                }
            });
        }else if(t_test=="学生"){
            $.ajax({
                type: "post",
                url: "/findallbystudentname",
                async: false,
                data: { "name": cname},
                success: function (data) {
                   // alert(data)
                    if (data.zsex=="男")
                        $(".sex1").prop("checked",true);
                    else
                        $(".sex2").prop("checked",true);
                    $("#userName").val(data.zname)
                    $("#password").val(data.zpass)
                    $("#zphone").val(data.zphone)
                    //班级在此处更新
                }
            });
        }else if(t_test=="管理员"){
            $.ajax({
                type: "post",
                url: "/findallbymeangername",
                async: false,
                data: { "name": cname},
                success: function (data) {
                    //$("#sex").val(data.zsex)
                    $("#userName").val(data.zname)
                    $("#password").val(data.zpass)
                    $("#zphone").val(data.zphone)

                }
            });
        }
    }

    /*$('#userName').change(function(){
        $("#reg_namelike").hide()
    })*/

    function hidename(){
        $("#reg_namelike").hide()
    }

    $('#zidentity').bind('input propertychange', function () {
        //alert(1)
        var zidentity=$("#zidentity").val()
        $("#reg_namelike").show()
        var reg_namelike =$("#reg_namelike")
        var str="<table align='center' class='registername_table'>"


        if($("#r_userkind").val()=="学生"){
            $.ajax({
                type: "post",
                url: "/findstudentnamelike",
                async: false,
                data: { "name": zidentity},
                success: function (data) {
                    // alert(data)
                    for (var i=0;i<data.length;i++){
                        str+="<tr>"
                        str+="<th  onclick='chosename(\""+data[i]+"\")' class='registername_list' value ='"+data[i]+"' ><font size='5'>"+data[i]+"</font></th>";
                        str+="</tr>"
                    }

                }
            });str+="<tr><button class='button5' onclick='hidename()'>取消</button></tr>"
            str+="</table>"
        }

        if($("#r_userkind").val()=="教师"){
            $.ajax({
                type: "post",
                url: "/findteachernamelike",
                async: false,
                data: { "name": zidentity},
                success: function (data) {
                    // alert(data)
                    for (var i=0;i<data.length;i++){
                        str+="<tr>"
                        str+="<th  onclick='chosename(\""+data[i]+"\")' class='registername_list' value ='"+data[i]+"' ><font size='5'>"+data[i]+"</font></th>";
                        str+="</tr>"
                    }

                }
            });str+="<tr><button class='button5' onclick='hidename()'>取消</button></tr>"
            str+="</table>"
        }

        if($("#r_userkind").val()=="管理员"){
            $.ajax({
                type: "post",
                url: "/findmanagernamelike",
                async: false,
                data: { "name": zidentity},
                success: function (data) {
                    // alert(data)
                    for (var i=0;i<data.length;i++){
                        str+="<tr>"
                        str+="<th  onclick='chosename(\""+data[i]+"\")' class='registername_list' value ='"+data[i]+"' ><font size='5'>"+data[i]+"</font></th>";
                        str+="</tr>"
                    }

                }
            });
           str+="<tr><button class='button5' onclick='hidename()'>取消</button></tr>"
            str+="</table>"
        }

        reg_namelike.html(str)

    });


    //用户类型联动
    $('#r_userkind').change(function() {
       var t_test=$('#r_userkind').val();
       if(t_test=="教师"){
           $("#sextr").show()
           $("#idcardtitle").html("工号:")
           $("#classestr").hide();
           $("#majortr").show()
       }
        else if(t_test=="学生"){
           $("#sextr").show()
           $("#idcardtitle").html("学号:")
           $("#classestr").show();
           $("#majortr").hide();
       }else if(t_test=="管理员"){
            $("#idcardtitle").html("工号:")
            $("#sextr").hide();
           $("#classestr").hide();
       }
    });
 function loadclassandmajor(){
       // alert(1)
        var r_table1=$("#r_table1");
        var str="<tr id='classestr'><th>班级:</th><th><select class='t_select' id='classes'>";
        $.ajax({
            type: "post",
            url: "/findallgrade",
            async: false,
            success: function (data) {
                    //alert(data)
                for (var i=0;i<data.length;i++){
                    str+="<option value ='"+data[i].zid+"'>"+data[i].zname+"</option>";
                }
            }
        });
            str+="</select></th></tr>";
            r_table1.append(str);
            var str2="<tr id='majortr' hidden><th>专业:</th><th><select class='t_select' id='major'>";
         $.ajax({
             type: "post",
             url: "/findallmajor",
             async: false,
             success: function (data) {
                 //alert(data)
                 for (var i = 0; i < data.length; i++) {
                     str2 += "<option value ='" + data[i].zid + "'>" + data[i].zname + "</option>";
                 }
             }
         });
     str2+="</select></th></tr>";
     r_table1.append(str2);

    }


    window.onload=function () {
        getMedia();
        loadclassandmajor();
    }


    function getMedia() {
       // alert(1)
        $("#mainDiv").empty();
        let videoComp = " <video id='video' muted autoplay='autoplay' class='left_video'></video><canvas id='canvas' width='500px' height='500px' style='display: none'></canvas>";
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

    //拍照事件
    function takePhoto() {
        let mainComp = $("#mainDiv");
        var zidentity=$("#zidentity").val();
        var password=$("#password").val();
        var zphone=$("#zphone").val();
        var sex=$("#sex").val();
        var r_userkind=$("#r_userkind").val();
        var classes=$("#classes").val();
        var major=$("#major").val();

        if (mainComp.has('video').length) {
            let userNameInput = $("#userName").val();
            if (userNameInput == "") {
                alert("姓名不能为空!");
                return false;
            }
            //获得Canvas对象
            let video = document.getElementById("video");
            let canvas = document.getElementById("canvas");
            let ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, 500, 500);
            var formData = new FormData();
            var base64File = canvas.toDataURL();
            var userName = $("#userName").val();
            formData.append("file", base64File);
            formData.append("name", userName);
            formData.append("groupId", "101");
            formData.append("zidentity",zidentity);
            formData.append("password",password);
            formData.append("zphone",zphone);
            formData.append("sex",sex);
            formData.append("userkind",r_userkind);
            formData.append("classes",classes);
            formData.append("major",major);

            $.ajax({
                type: "post",
                url: "/faceAdd",
                data: formData,
                contentType: false,
                processData: false,
                async: false,
                success: function (text) {
                    var res = JSON.stringify(text)
                    if (text.code == 0) {
                        layer.msg("注册成功", { icon: 1, offset: "auto", time:1000 });
                        location.href("/demo");
                    } else {
                        alert(text.message)
                    }
                },
                error: function (error) {
                    alert(JSON.stringify(error))
                }
            });
        } else {
            var formData = new FormData();
            let userName = $("#userName").val();
            formData.append("groupId", "101");
            var file = $("#file0")[0].files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                var base64 = reader.result;
                formData.append("file", base64);
                formData.append("name", userName);
                $.ajax({
                    type: "post",
                    url: "/faceAdd",
                    data: formData,
                    contentType: false,
                    processData: false,
                    async: false,
                    success: function (text) {
                        var res = JSON.stringify(text)
                        if (text.code == 0) {
                            layer.msg("注册成功", { icon: 1, offset: "auto", time:1000 });
                        } else {
                            alert(text.message)
                        }
                    },
                    error: function (error) {
                        alert(JSON.stringify(error))
                    }
                });
                location.reload();
            }
        }

    }

    function imageRecog() {
        let imageInput = " <h2>点击图片区域上传文件</h2><input style='display: none' type='file'  name='file0' id='file0' multiple='multiple' /><br><img src='images/shibie.jpg' id='img0' onclick='toChooseFile()' style='width: 30rem;height: 25rem;'>";
        $("#mainDiv").empty();
        $("#mainDiv").append(imageInput);
    }

    function toChooseFile() {
        $("#file0").trigger('click');
    }

    $(document).on("change", "#file0", function () {
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


</script>
