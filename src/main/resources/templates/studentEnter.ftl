<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">


    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
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



<!--请假弹框-->
<div class="co_leavemes" hidden id="co_leavemes" align="center">
    <font size="5">请假原因</font>
    <#--<textarea type="text" class="co_mes" id="co_mes"></textarea>-->
    <select id="co_mes" class="co_mes">
        <option value ="事假">事假</option>
        <option value ="病假">病假</option>
        <option value="卫生间">卫生间</option>
        <option value="其它">其它</option>
    </select>
    <button class="co_button" onclick="common_leave()">确认</button>
</div>



<div class="layui-layout layui-layout-admin" >
    <!--头部导航条-->
    <div class="top">
        <div class="leftfont"><font size="5" ></font></div>
        <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
            欢迎
        </div>
        <div class="layui-col-xs9" align="center" style="width: 69%">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
                ${name}
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='${path}'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C"> 进入安浩智能学习工厂</div>
            <div>

                    <button style="color:#FFFFFF;height: 75px;display:block;margin:0 auto;margin-top:0px;width:211px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 32px" onclick="test()" id="e_test">
                        开始测试    
                    </button>
            </div>
        </div>


        <div class="right">
            <!--右侧按键-->
            <div class="right1">
                <button class="button5" onclick="upheads()" id="upheads">举手</button>
            </div>

            <div class="right2">
                <button class="button5" onclick="showleave()"  id="leave">请假</button>
            </div>

            <div class="right3">
                <button class="button5" onclick="leaveclass()" id="outsystem">退出系统</button>
            </div>
        </div>


    </div>
</div>
<script>

    //window
    window.onbeforeunload=function(e){
        //if(event.clientX>document.body.clientWidth && event.clientY < 0 || event.altKey)
            a()
    }

    function a(){


        $.ajax({
            type: "post",
            url: "/deletemes",
            data:{},
            //    async: false,
            success: function (data){

            }
        });

        //将继电器6号端口断开
        $.ajax({
            type: "post",
            url: "/usixout",
            data:{},
            //    async: false,
            success: function (data){

            }
        });

        //设备状态的更改
        $.ajax({
            type: "post",
            url: "/updateprogress",
            data:{},
            //    async: false,
            success: function (data){

            }
        });

        //学生退出时改变实训设备的zprogress
        $.ajax({
            type:"post",
            url:"/exitsystem",
            data:{},
            //    async: false,
            success:function(data){

            }
        })

        //清除当堂课请假与举手状态
        $.ajax({
            type:"post",
            url:"/updatealleventbystu",
            data:{},
            //    async: false,
            success:function(data){

            }
        })

        //更改学生登陆的状态
        $.ajax({
            type:"post",
            url:"/updatestatusbout",
            data:{},
            //   async: false,
            success:function(data){

            }
        })
    }


    window.onload = function(){
        loadisevent()

        $.ajax({
            type: "post",
            url: "/findteststatebyIP",
            data:{},
            async: false,
            success: function (data) {
                //alert(data)
                if(data!="是"){
                    $("#e_test").attr('onclick', 'simulationtasks()')
                    $("#e_test").text("实训任务");
                }
            }
        });

        window.setInterval(function () {
            findisleave()
        }, 3000);
    }


    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    
    //跳安全测试的方法
    function test() {
        /*ajax不能实现只发送不要数据所以不能用ajax*/
       // location.href = "/student_test";
     //跳转开始测试的controller
        $.ajax({
            type: "post",
            url: "/updatetesttime",
            async: false,
            data:{},
            success: function (data){

            }
        });


      $.ajax({
            type: "post",
            url: "/starttest",
            contentType: false,
            processData: false,
            async: false,
            success: function (){
                location.href = "/student_test";
            }
        });

        }
    //跳实训任务的方法  
    function simulationtasks() {
        location.href = "/class_ppt";
    }    
    
        
    function show() {
        alert("进入到show中")
    }
</script>
</body>
</html>