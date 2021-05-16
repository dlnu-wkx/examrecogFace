<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <title>现场管理</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="./jquery/video-js.min.css">
    <link href="./layui/css/time_status.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/exam_field_management.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/group_management.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/top_bar.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./layui/js/field_management.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
    <script src="./layui/js/exam_field_management.js "></script>
    <script src="./layui/js/exit.js "></script>

</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #114376">
        <div  class="left-bar"><span id="trainroomname"></span>/群组管理</div>
        <div class="mid-bar">安浩智能学习工厂</div>
        <div class="right-bar" id="m_rightfont"></div>
    </div>
    <div class="layui-row ">
        <div id='welcomeField' class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
        </div>
        <div id='teach' class="layui-col-xs9" align="center" style="width: 74%">
            <div class="teach-son">
            </div>
        </div>

        <div id="hiddenArea" class="hiddenArea">
        </div>
        <#--三级菜单-->
        <div id='threeMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.4%">

        </div>
        <#--查岗的下级功能表-->
        <div id='checkPointMenu' class="layui-col-xs10" style="display: none; margin-top: 40px;margin: 10px 20px;width: 87.3%">

        </div>
        <#--四级菜单-->
        <div id='fourMenu' class="layui-col-xs1" align="center" style="display:none;width: 15%;font-size: 40px">
            <#--<div id="mainDiv">
                <img id="img" style="width: 400px;height: 300px;" src="trophy.jpg">
            </div>-->
            <div id="grades" class="grades">

            </div>
            <div class="grades-foot">
                <button onclick="addgroup()" class="add">添加组</button>
                <button onclick="delegroup()" class="undo">删除组</button>
            </div>
        </div>
        <div id='fourMenu1' class="layui-col-xs8" align="center" style="display:none;width: 75%;">
            <div  align="center" class="middle">
                <div class="gradename">群组名称：<input id="gradename" value=""/><span id="gradeID" style="display: none"></span></div>
                <div class="center-right">
                    <button onclick="deleteStudent(this)" class="center-right-undo">删除名单</button>
                    <button onclick="showNameList(this)" class="center-right-add">添加名单</button>
                </div>
            </div>
            <div class="mainBody-parent">
                <#--按照顺序排出识别的人脸，顺序是最早的在最下最右边-->
                <div id="mainBody" class="mainBody">
                </div>
            </div>
            <div>
                <button onclick="commitAndAddStudent()" class="determine">确定</button>
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

<!--弹框-->
<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>
    <button class="p_button2" onclick="overclass()">下课</button>
</div>

<!--蒙版-->
<div id="parent" class="facebox_exit" hidden></div>
<div id="showVdieo" style="position: absolute;z-index:10;top: 24%;left: 41%"></div>
<!-- 增加选择班级的蒙版 -->
<div id="masking" class="masking"></div>
<!-- 增加课程的弹框信息 -->
<div id="grade-pop-up" class="pop-up">
</div>
</body>
<script>
    window.onload=show1();
    /*window.onLoad=showCamera();*/
    //展示现场管理的二级菜单
    function show1() {
        document.getElementById("colorType").style.backgroundColor="#ED7D31";
        document.getElementById("welcomeField").style.display="none";
        document.getElementById("teach").style.display="none";
        //document.getElementById("threeMenu").style.display="block";
        document.getElementById("threeMenu").style.display="none";
        //摄像头下面的显示的五个人
        document.getElementById("fourMenu").style.display="block";
        //显示开始和结束按钮
        document.getElementById("fourMenu1").style.display="block";
        showgrades();
        loadteachername();
        gettrainroom();
    }
    function showgrades() {
        var str = "";
        $.ajax({
            type:"post",
            url:"/findAllgrade",
            data:{} ,
            success:function (data) {
                if(""!=data){
                    $("#grades").empty();
                    for(var i=0;i<data.length;i++){
                        str+=" <button id = '"+data[i].zid+"' onclick='showstudentbygradeid(\""+data[i].zid+"\",\""+data[i].zname+"\",this)'  style='width: 100%;font-size:20px;background-color: #4472c4;color:#FFFFFF; border-radius:8px'>"+data[i].zname+"</button>";
                    }
                    $("#grades").append(str);
                }
            }
        })
    }


    //showstudentbygradeid展示改班级所有的人员
    function showstudentbygradeid(zid,zname,event){
        //在中心体上面显示班级
        $("#gradename").val(zname);
        document.getElementById("gradeID").innerHTML=zid;
        var buttons = document.getElementsByTagName("button");
        //变色
        //var buttons = document.getElementById("grades").getElementsByTagName("button")
        for(var a = 0;a<buttons.length;a++){
            buttons[a].style.backgroundColor = "#4472c4"
        }
        event.style.backgroundColor = "yellow";
        showStudent(zid);
    }
    <!---->
    //点击整个div变色
    function changecolor(event) {
        //名字的背景色为白色，改变背景色的同时改变复选框的值
        if($(event).css("background-color") == "rgba(0, 0, 0, 0)"){
            event.style.backgroundColor = "#4472c4";
            event.getElementsByTagName("input").check.checked = true
        }else{
            event.style.backgroundColor = "rgba(0, 0, 0, 0)";
            event.getElementsByTagName("input").check.checked = false
        }
    }

    //添加组
function addgroup() {
    $(".add").css("background-color","yellow");
    document.getElementById("gradeID").innerHTML = "";
    //把左侧的班级还原为默认色
    var buttons = document.getElementById("grades").getElementsByTagName("button");
    for(var a = 0;a<buttons.length;a++){
        buttons[a].style.backgroundColor = "#4472c4"
    }
        $("#gradename").val("")
        $("#mainBody").empty();
        showgrades();

}
//删除组
function delegroup() {
    var gradeID = document.getElementById("gradeID").innerHTML;
    if(gradeID == ""){
        layer.msg("请选择班级", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    $.ajax({
        type:"post",
        url:"/deletgroup",
        data:{"zid":gradeID},
        success:function (data) {
            if(data==1){
                showgrades();
                $("#gradename").val("")
                $("#mainBody").empty();
            }

        }
    })
}

function commitAndAddStudent() {

    var gradeID =  document.getElementById("gradeID").innerHTML;
    var startchose =[];
    $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
        startchose[i] =$(this).val();
    });
    var zname = $("#gradename").val();

    if(zname == ""){
        layer.msg("请输入群组名称", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    $.ajax({
        type:"post",
        url:"/addGroupAndzid",
        data:{"zname": zname,"zid":startchose,"gradeID":gradeID},
        success:function (data) {
        if(data == 1){
            var str = "";
            $.ajax({
                type:"post",
                url:"/findAllgrade",
                data:{} ,
                success:function (data) {
                    if(""!=data){
                        $("#grades").empty();
                        for(var i=0;i<data.length;i++){
                            str+=" <button id = '"+data[i].zid+"' onclick='showstudentbygradeid(\""+data[i].zid+"\",\""+data[i].zname+"\",this)'  style='width: 100%;font-size:20px;background-color: #4472c4;color:#FFFFFF; border-radius:8px'>"+data[i].zname+"</button>";
                        }
                        $("#grades").append(str);
                        var buttons = document.getElementById("grades").getElementsByTagName("button");
                        for(var a = 0;a<buttons.length;a++){
                            if(buttons[a].innerHTML == zname){
                                buttons[a].style.backgroundColor = "yellow"
                                document.getElementById("gradeID").innerHTML=buttons[a].id;
                                $("#mainBody").empty();
                                showStudent(buttons[a].id)
                            }
                        }
                    }
                }
            })

        }
        }
    })
}

//显示所有的学生
function showNameList(event) {
    $.ajax({
        type:"post",
        url:"/findAllStudents",
        data:{},
        success:function (data) {
            if(""!=data)
            var str="";
            var center=$("#mainBody");
            center.empty();
            var j=0;
            str+="<table class='gm_table1' id='p_bbox'>";
            for (var i=0;i<(data.length/4+1);i++) {
                str += " <tr>";
                for (; j < 4 * (i + 1); j++) {
                    if (j == data.length) {break;}
                    str += "<th><div class='gm_button1' onclick='changecolor(this)' ><input type='checkbox' value='"+data[j].zid+"' name='check'/>" + data[j].zname +"&nbsp;&nbsp;"+ data[j].zgradeID+"</div></th>";
                }
            }
            str += "</tr>";
            str+="</table>";
            center.html(str)
        }

    })
}
//删除学生名单
function deleteStudent(event) {
    var gradeID = document.getElementById("gradeID").innerHTML;
    var studentid =[];
    $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
        studentid[i] =$(this).val();
    });
    console.log(studentid);
    $.ajax({
        type:"post",
        url:"/deleteStudentbyzid",
        data:{"zid":studentid},
        success:function (data) {
            if(data == 1){
                $("#mainBody").empty();
                showStudent(gradeID)
            }
        }
    })
}

function showStudent(zid) {
    $.ajax({
        type:"post",
        url:"/findAllstudentbygradeid",
        data:{"zid": zid},
        success:function (data) {
            if(""!=data)
            var str="";
            var center=$("#mainBody");
            center.empty();
            var j=0;
            str+="<table class='gm_table1' id='p_bbox'>";
            for (var i=0;i<(data.length/4+1);i++) {
                str += " <tr>";
                for (; j < 4 * (i + 1); j++) {
                    if (j == data.length) {break;}
                    str += "<th><div class='gm_button1' onclick='changecolor(this)' ><input type='checkbox' value='"+data[j].zid+"' name='check'/>" + data[j].zname +"&nbsp;&nbsp;"+ data[j].zgradeID+"</div></th>";
                }
            }
            str += "</tr>";
            str+="</table>";
            center.html(str)
        }

    })
}


</script>
</html>
