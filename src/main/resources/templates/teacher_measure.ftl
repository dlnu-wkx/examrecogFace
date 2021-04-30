<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 测量 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/teacher_measure.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <script src="https://cdn.bootcss.com/html2canvas/0.5.0-beta4/html2canvas.js"></script>
    <script src="https://cdn.bootcss.com/jspdf/1.3.4/jspdf.debug.js"></script>



    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="./layui/layui.js"></script>
    <style>
        html,body{
            height: 97%;
        }
    </style>
</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont">测量</div>
    <div class="m_centerfont">安浩智能学习工厂</div>
    <div class="rightfont" id="m_rightfont"></div>
</div>

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


<div style="width: 100%;height: 100%">
    <!--中间信息查询-->
    <div class="i_center" >

    </div>

    <!--头部的查询块-->
    <div class="m_titleselect">
        <font class="m_classfont" size="5">班级：</font>
        <select class="m_classselect" id="m_classselect" onchange="getmeasurebygt2()"></select>
        <font class="m_taskfont" size="5">实训任务：</font>
        <select class="m_taskselect" id="m_taskselect"  onchange="getmeasurebygt2()"></select>
        <#--<button class="m_submitbutton">提交</button>-->  <button class="m_savebutton" onclick="saveteachercheck()">保存</button>
    </div>

    <div class="m_tablemes">
        <table class="m_table" id="measure_table">

        </table>
        <table class="m_table2" id="measure_table2">


        </table>
    </div>


    <#--右边功能按钮-->
    <div class="right_parent" align="center" >

        <button id='f_field_management'  onclick="fieldManagement()"  class="f_field_management" >
            现场管理
        </button>


        <button onclick="informationService()" class="f_field_service"  >
            信息查询
        </button>


        <button onclick="timeStatus()" class="f_field_status" >
            实时状态
        </button>

        <button onclick="informationDelivery()" class="f_field_delivery" >
            信息发布
        </button>

        <button id="exit" onclick="outpower()" class="f_field_exit">
            退出系统
        </button>

    </div>
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
    //按enter跳转文本框
    $(function(){
        $('input').bind('keypress',function(event){
            if(event.keyCode == "13")
            {
                if($(this).is($('input').last())){
                    // 回到第一个
                    $('input').get(0).focus();
                }else{
                    $(this).next().focus();
                }
            }
        });
    });



    window.onload =function () {
        $("#f_field_management").css('background-color','#ED7D31');
        loadteachername();
        loadallgrad();
        loadalltask();

        getmeasurebygt2()
    }
    function loadteachername(){
        $.ajax({
            type: "post",
            url: "/getteachername",
            data:{},
            async: false,
            success: function (data){
                var m_rightfont=$("#m_rightfont")
                var str="指导老师："+data+"";
                m_rightfont.html(str);
            }
        });
    }

    function loadalltask(){
        var m_taskselect=$("#m_taskselect")


        $.ajax({
            type: "post",
            url: "/getalltask",
            data:{},
            async: false,
            success: function (data){
                // alert(data.si)
                for(var i=0;i<data.length;i++){
                    var str="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>"
                    m_taskselect.append(str);
                }
            }
        });
    }


    function loadallgrad(){
        var m_classselect=$("#m_classselect")

        $.ajax({
            type: "post",
            url: "/getallgrade",
            data:{},
            async: false,
            success: function (data){
               // alert(data.si)
               for(var i=0;i<data.length;i++){
                   var str="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>"
                   m_classselect.append(str);
               }
            }
        });
    }

    var intervalnum=0;
    function getmeasurebygt2(){

        getmeasurebygt()

        intervalnum=1;
    }
    var max=0;

    function getmeasurebygt(){
        var m_classselect=$("#m_classselect").val();
        var m_taskselect=$("#m_taskselect").val();

        var studentid=new Array();
        //任务的输入框中的最大值

        $.ajax({
            type: "post",
            url: "/findtaskassessbytrainingid",
            data:{"ztraining_taskID":m_taskselect},
            async: false,
            success: function (data){
                if(data.length!=0){
                  //  alert(data[data.length-1].zorder)
                    max=data[data.length-1].zorder
                }
                else{
                    $("#measure_table").html("")
                    $("#measure_table2").html("")
                    return;
                }
            }
        });

        var studentlen=0;
        $.ajax({
            type: "post",
            url: "/findAllstudentbyg",
            data:{"zgradeID":m_classselect},
            async: false,
            success: function (data){

                var measure_table=$("#measure_table")
                var str="";
                str+="<tr><th>序号</th><th>学号</th><th>姓名</th></tr>" ;

                for(var i=0;i<data.length;i++){
                    studentlen++;
                    studentid[i]=data[i].zid;
                    str+="<tr id="+data[i].zid+"><th>"+(i+1)+"</th><th>"+data[i].zidentity+"</th><th>"+data[i].zname+"</th></tr>";
                }
                measure_table.html(str)
            }
        });

        var measure_table2=$("#measure_table2")
        var str2=""
        if(max!=0){
            //alert(max)
            str2+="<tr>"
            for(var i=1;i<=max;i++){
                str2+="<th colspan='2'>"+i+"</th>"
            }
            str2+="</tr>"

            str2+="<tr>"
            for(var i=1;i<=max;i++){
                str2+="<th>自测</th><th>师测</th>"
            }
            str2+="</tr>"

            for(var i=0;i<studentlen;i++){
                $.ajax({
                    type: "post",
                    url: "/getmeasurebygt",
                    data:{"zgradeID":studentid[i],"ztrainingtaskID":m_taskselect},
                    async: false,
                    success: function (data){
                        str2+="<tr>"
                        if(data.length==0){
                            for(var j=0;j<=max-1;j++){
                              str2+="<th class='m_font' value='0'></th><th><input  onkeydown='return changeTab(event,this)'  class='meausre_input'></th>";
                            }
                        }else{
                            for(var j=0;j<=max-1;j++){
                                if(!data[j].zteachercheck){
                                    str2+="<th class='m_font' value='"+data[j].zselfcheck+"'>"+data[j].zselfcheck+"</th><th><input type='tel' id='"+data[j].ztaskinputid+"' onkeydown='return changeTab(event,this)' class='meausre_input' ></th>";
                                }
                                else{
                                    str2+="<th class='m_font' value='"+data[j].zselfcheck+"'>"+data[j].zselfcheck+"</th><th><input type='tel' id='"+data[j].ztaskinputid+"' onkeydown='return changeTab(event,this)' class='meausre_input' value='"+data[j].zteachercheck+"'></th>";
                                }

                            }
                        }

                        str2+="</tr>"
                    }
                });
            }
            measure_table2.html(str2)
        }
        loadcolor();

    }
    function loadcolor(){
        var inputs = jQuery("#measure_table2 :input");
        var fonts=$(".m_font")
        for(var i=0;i<inputs.length;i++){
            var num1=parseFloat(fonts.eq(i).text());
            var num2=parseFloat(inputs.eq(i).val());
            if(num2>(num1+0.01)||num2<(num1-0.01))
                fonts.eq(i).css('background-color','#ec0750');
        }

    }



    function saveteachercheck(){
        var inputs = jQuery("#measure_table2 :input");
        for(var i=0;i<inputs.length;i++){
            var inputid=inputs.eq(i).attr('id');
            var inputvalue=inputs.eq(i).val();
            if(inputvalue){
                //alert(inputvalue)
                $.ajax({
                    type: "post",
                    url: "/updatetcheckbyid",
                    data:{"zid":inputid,"zteachercheck":inputvalue},
                    async: false,
                    success: function (data){

                    }
                })
            }

        }
        layer.msg("保存成功", { icon: 1, offset: "auto", time:1000});

    }


    function changeTab(event,i){
        //alert(i)
        var keyCode = event.keyCode;
        var inputs = jQuery("#measure_table2 :input"); // 获取表单中的所有输入框
        var fonts=$(".m_font")
        var idx = inputs.index(i);

        var num=0.00;
        num=parseFloat(fonts.eq(idx).text());
        var num2=parseFloat(inputs.eq(idx).val());
        //alert(num)
        if(keyCode==13||keyCode==37||keyCode==38||keyCode==39){
            //自检>
           // alert(num+0.01)
            if(num2>(num+0.01)||num2<(num-0.01))
                fonts.eq(idx).css('background-color','#ec0750');
        }

        switch(keyCode){
            case 13:
                inputs[idx + 1].focus(); // 设置焦点
                inputs[idx + 1].select(); // 选中
                break;
            case 37:        //←
                inputs[idx - 1].focus(); // 设置焦点
                inputs[idx - 1].select(); // 选中
                break;
            case 38:        //↑
                inputs[idx - max].focus(); // 设置焦点
                inputs[idx - max].select(); // 选中
                break;
            case 39:        //→
                inputs[idx + 1].focus(); // 设置焦点
                inputs[idx + 1].select(); // 选中
                break;
            case 40:        //↓
                inputs[idx + max].focus(); // 设置焦点
                inputs[idx + max].select(); // 选中
                break;
            default:
                break;
        }
        return true;
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
        location.href="/student_status";
    }
    //退出
    function studentStatus() {
        location.href="/student_status";
    }

    function fieldManagement() {
        location.href="/field_management";
    }


</script>

</html>