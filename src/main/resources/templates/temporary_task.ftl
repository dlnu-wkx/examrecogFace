<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 任务发布 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/fixed_task.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/common.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/exit.js "></script>
</head>
<body  class="body" >

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
    <div class="leftfont">任务发布</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>




<!--右侧按键-->
<div class="p_right" align="center">
    <button onclick="fieldManagement()" class="p_field_management">现场管理</button>
    <br><br>
    <button onclick="informationService()" class="p_information_service">信息查询</button>
    <br><br>
    <button onclick="timeStatus()" id="statusid" class="p_time_status">实时状态</button>
    <br><br>
    <button  class="p_information_delivery" id="deliveryid" onclick="outmessage()">信息发布</button>
    <br><br>
    <button class="p_exit" id="p_button4" onclick="outpower()">退出系统</button>
</div>


<!--左侧人员信息-->
<div class="f_left">
    <font size="3" class="f_font1">设备列表</font>
    <button class="f_button1" onclick="allchose()">全选</button>

    <div class="f_leftmes" id="f_leftmes">

    </div>
</div>

<!--右侧课件列表-->
<div class="f_rmessage">
    <font class="f_font2">课件列表</font>
    <input class="f_input1" type="text" id="f_input1">
    <button class="f_button2" onclick="tasklike()">搜索</button>

   <div class="f_table2mes" id="f_table2mes">

   </div>
</div>


<!--弹框-->
<div hidden class="d_popup" id="de_popup" align="center">
    <br>
    <button class="p_button2" onclick="">固定任务</button><br><br>
    <button class="p_button2" onclick="">临时任务</button><br><br>
    <button class="p_button2" onclick="">信息发送</button>
</div>

<div class="f_bottom">
    <button class="p_button2" onclick="informationDelivery()">信息广播</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2" onclick="fixed_task()">固定任务</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button2" onclick="inserttemptask()">任务发送</button>
</div>

<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>

    <button class="p_button2" onclick="overclass()">下课</button>
</div>
<!--蒙版-->
<div id="parent" class="parent" hidden></div>


</body>

<script>

    window.onLoad=aaa();
    function aaa(){
        var servicebutton = document.getElementById("deliveryid");
        servicebutton.style.backgroundColor="#ED7D31"
    }


    function informationDelivery() {
        location.href="/information_delivery"
    }


   /* function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
    }

    function outmessage() {
        $("#de_popup").show()
    }
*/

    function allchose() {

        //全选
        $("#f_leftmes  input[type='checkbox']").attr("checked","true");
    }


    window.onload =function () {
        load();
    }

    function load() {

        var str=""
        var f_leftmes=$("#f_leftmes")

        var str2=""
        var f_table2mes=$("#f_table2mes")

        $.ajax({
            type: "post",
            url: "/findallnandf",
            success: function (data) {

                str+="  <table class='f_table1' align='center' id='f_table' id='studenttable'>"
                str+="<tr> <th> 当前人员 </th> <th>机床号 </th> <th> 选项 </th> </tr>"
                for (var i=0; i<data.length;i++) {
                    str+=" <tr> <th> "+data[i].zname+" </th> <th>"+data[i].zidentity+" </th> <th> <input name='studentid' id='"+data[i].zid+"' type='checkbox' value='"+data[i].zid+"'> </th> </tr> "
                }
                str+=" </table>"
                f_leftmes.html(str)
            }
        });


        $.ajax({
            type: "post",
            url: "/findalltask",
            success: function (data) {

                str2+=" <table class='f_table2' id='temtasktable'>";
                str2+="<tr><th>课件名称</th><th>选项</th></tr>"
                for (var i=0;i<data.length;i++){
                    str2+=" <tr><th>"+data[i].zname+"</th><th><input name='temtaskid' type='checkbox' id='"+data[i].zid+"' value='"+data[i].zid+"'></th></tr>"
                }
                str2+="</table>";

                f_table2mes.html(str2)
            }
        });

    }


    function tasklike() {
        var str2=""
        var f_table2mes=$("#f_table2mes")
        var zname=$("#f_input1").val()

        $.ajax({
            type: "post",
            url: "/findtasklikename",
            data:{"zname":zname},
            success: function (data) {
                str2+=" <table class='f_table2' id='temtasktable'>";
                str2+="<tr><th>课件名称</th><th>选项</th></tr>"
                for (var i=0;i<data.length;i++){
                    str2+=" <tr><th>"+data[i].zname+"</th><th><input name='temtaskid' type='checkbox' value='"+data[i].zid+"'></th></tr>"
                }
                str2+="</table>";

                f_table2mes.empty();
                f_table2mes.html(str2)
            }
        })
        }

        function inserttemptask() {
        //alert(22)
            var studentid = [];
            var taskid=[];
            var data1=0;
            var data2=0;

            $("input[name='studentid']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                studentid[i] =$(this).val();
            });
            $("input[name='temtaskid']:checked").each(function(i){//把所有被选中的复选框的值存入数组
                taskid[i] =$(this).val();
            });

            //alert(studentid)
            //alert(taskid)
            for(var i=0;i<studentid.length;i++){
                for (var j=0;j<taskid.length;j++){
                    //上课学生任务表是否有该任务
                    $.ajax({
                        type: "post",
                        url: "/findisinassigan",
                        async: false,
                        data:{"studentid":studentid[i],"taskid":taskid[j]},
                        success: function (data) {
                           // alert(data)
                            data1=data;
                        }
                    });
                   // alert(data1)
                    if(data1>0){continue;}

                   //教师临时任务表是否有该学生和任务
                    $.ajax({
                        type: "post",
                        url: "/findisintemp2",
                        data:{"studentid":studentid[i],"taskid":taskid[j]},
                        async: false,
                        success: function (data) {
                           // alert(data)
                            data2 =data;
                        }
                    });
                   // alert(data2)
                    if(data2>0){continue;}



                 $.ajax({
                        type: "post",
                        url: "/inserttemptask",
                        data:{"studentid":studentid[i],"taskid":taskid[j]},
                        async: false,
                        success: function (data) {
                            //alert(data)
                            if(data>0) layer.msg("成功上传任务", { icon: 1, offset: "auto", time:1000 });
                        }
                    });

                }
            }

        }


    //现场管理
    function fieldManagement() {
        location.href="/field_management";
    }
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //实时状态
    function timeStatus() {
        location.href="/student_status";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }

    function temporary_task() {

        location.href = "/temporary_task";
    }

    function fixed_task() {

        location.href = "/fixed_task";
    }


   /* //退出
    function outpower(){
        $("#popup").show()
    }*/






</script>

</html>
