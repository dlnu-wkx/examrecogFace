
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 测试管理 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/common.js"></script>
    <script src="./layui/layui.js"></script>

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
    <div class="leftfont"><font size="5" >测试管理</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>


<!--中部电源显示-->
<div class="i_center" id="p_center">

</div>


<!--左侧按键-->
<div class="p_left" align="center" id="p_left">

</div>
<!--右侧按键-->
<div class="i_right" align="center">
    <button onclick="fieldManagement()" class="p_field_management">现场管理</button>
    <button onclick="informationService()" class="p_information_service">信息查询</button>
    <button onclick="timeStatus()" class="p_time_status">实时状态</button>
    <button onclick="informationDelivery()" class="p_information_delivery">信息发布</button>
    <button class="p_exit" id="p_button4" onclick="outpower()">退出</button>
</div>
<#--<div class="outdiv">
   </div>-->


<!--下方按键及内容-->
<div class="p_text" align="center">
    <br><br>
    <button class="p_button5" onclick="startchose()">开启</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="startallfacti()">全部开启</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="closechose()">关闭</button>
    &emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="p_button5" onclick="endallfacti()">全部关闭</button>
</div>

<#--<!--关闭时间控制&ndash;&gt;
<div class="closetime" align="center">
    <font size="3">关闭电源时作用：</font><br><br>
    <input type="checkbox" name="closetime" id="closetime" class="p_chose1">&emsp;&emsp;&emsp;
    <font size="3">关闭时延时</font><input type="number" class="p_input" id="timenumber"><font size="3">分钟</font>

</div>-->


<!--测试题控制-->
<div class="p_testchose" >
    &emsp;&emsp;<input type="checkbox" name="istest" id="istest" hidden class="p_chose1">&emsp;&emsp;&emsp;
    <div class="p_chose2">
        <font>测试合格分数</font>&emsp;&emsp;

        <#--<input type="number" class="p_input" id="testcode">-->
        <select class="p_input" id="p_passcode">
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
            <option value="40">40</option>
            <option value="50">50</option>
            <option value="60" selected="selected">60</option>
            <option value="70">70</option>
            <option value="80">80</option>
            <option value="90">90</option>
            <option value="100">100</option>
        </select>

        &emsp;分
   </div>


    <div class="p_chose3">
        <font>题目数量</font>&emsp;&emsp;

        <#--<input type="number" class="p_input" id="testcode">-->
        <select class="p_input" id="p_testnum">
            <option value="5">5</option>
            <option value="10" selected="selected">10</option>
            <option value="20">20</option>
            <option value="50">50</option>
            <option value="100">100</option>
        </select>

        &emsp;个
    </div>


    <div class="p_chose4">
        <font>题库选择</font>&emsp;&emsp;

        <#--<input type="number" class="p_input" id="testcode">-->
        <select class="p_input" id="p_testtype">
            <option value="机床安全操作">机床安全操作</option>
            <option value="铣床安全操作">铣床安全操作</option>
            <option value="车间注意事项">车间注意事项</option>
        </select>

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


<!--在线-->
<div class="s_online">
    <font size="5">在线：</font><input type="checkbox" class="delivery_quanxuan2" id="s_onlinebox" onclick="getisonline2()">
</div>



</body>



<script>
    var static_isonline=0;

    function getisonline2(){
        if(static_isonline==0)
            static_isonline=1;
        else
            static_isonline=0;

        findfacbyrid(ztrainroomid);
    }


    //全部开启
    function startallfacti() {
       //alert(1)

        //更改实训室中每一个设备对应的测试值
            var p_passcode=$("#p_passcode").val()
            var p_testnum=$("#p_testnum").val()
            var p_testtype=$("#p_testtype").val()

            $.ajax({
                type: "post",
                url: "/updatefatestbyroomid",
                data:{"ztrainingroomID":ztrainroomid,"zpassingscore":p_passcode,"zsafetestingNum":p_testnum,"zsafetestingType":p_testtype},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已全部开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });

        /*else{
            $.ajax({
                type: "post",
                url: "/updatenotestbyscheduleid",
                async: false,
                success: function (data) {
                    //alert(data)
                    if(data>0){
                        layer.msg("不开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });
        }*/

       $.ajax({
            type: "post",
            url: "/updateallfacility",
            data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"已开机"},
            async: false,
            success: function (data) {
                if(data>0){
                    layer.msg("已全部开启，等待电源开启", { icon: 1, offset: "auto", time:1000 });
                }else{
                    alert("出错")
                }
            }
        });
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }

    //全部关闭
    function endallfacti() {
       // alert(2)
        //延时按键有被选中，加载延时关闭选项
       /* if($('#closetime').prop('checked')){

            var timenumber=$("#timenumber").val();
            setTimeout(function (){

                $.ajax({
                    type: "post",
                    url: "/updateallfacility",
                    data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"未开机"},
                    async: false,
                    success: function (data) {
                        if(data>0){
                            layer.msg("已接受关机命令，请等待"+timenumber+"分钟后关机", { icon: 1, offset: "auto", time:1000 });
                        }else{
                            alert("出错")
                        }
                    }
                });

            }, 60000*timenumber);
        }//延时按键没有被选中，加载直接关闭方法
        else{*/
            $.ajax({
                type: "post",
                url: "/updateallfacility",
                data:{"ztrainroomid":ztrainroomid,"zpowerstatus":"未开机"},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已全部关闭，等待设备关闭", { icon: 1, offset: "auto", time:1000 });
                    }else{
                        alert("出错")
                    }
                }
            });
      //  }
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);

    }

    //开启选中的
    function startchose() {
        var j=0;
        var startchose =[];
        var p_passcode=$("#p_passcode").val()
        var p_testnum=$("#p_testnum").val()
        var p_testtype=$("#p_testtype").val()
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            startchose[i] =$(this).val();
        });

        //电源管理
          $.ajax({
                type: "post",
                url: "/updateallfacilitybyzid",
                data:{"zid":startchose,"zpowerstatus":"已开机","kind":"开启"},
                async: false,
                success: function (data) {
                   if(data>0){
                        layer.msg("已开启选中，等待电源开启", { icon: 1, offset: "auto", time:1000 });
                    }else{
                        alert("出错")
                    }
                }
            });


        //更改电源被选中设备的电源管理
          /*  $.ajax({
                type: "post",
                url: "/updateftestbych",
                data:{"zid":startchose,"zpassingscore":p_passcode,"zsafetestingNum":p_testnum,"zsafetestingType":p_testtype},
                async: false,
                success: function (data) {
                    if(data>0){
                        layer.msg("已开启安全测试", { icon: 1, offset: "auto", time:1000 });
                    }
                }
            });*/

        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }


    //关闭选中
    function closechose() {

        var j=0;
        var closechose =[];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            closechose[i] =$(this).val();
        });
              // alert(closechose);
                $.ajax({
                    type: "post",
                    url: "/updateallfacilitybyzid",
                    data: {"zid": closechose, "zpowerstatus": "未开机","kind":"关闭"},
                    async: false,
                    success: function (data) {
                        if(data>0){
                            layer.msg("已关闭选中，等待电源关闭", { icon: 1, offset: "auto", time:1000 });
                        }else{
                            alert("出错")
                        }
                    }
                });
        setTimeout(function (){ findfacbyrid(ztrainroomid)},100);
    }




   /* //退出
    function outpower(){
        $("#popup").show()
    }



    function lockscreen() {
        //蒙版出现
        $("#parent").show();
        //弹框消失
        $("#popup").hide();
        //按键文字改变，颜色改变，方法改变
        $("#p_button4").css('background-color','#FFC000');
        $("#p_button4").text('解锁');
        $("#p_button4").attr("onclick","removescreer()");

    }

    function removescreer() {
       /!* $("#parent").hide();
        $("#p_button4").text('退出系统');
        $("#p_button4").css('background-color','#4472c4');
        $("#p_button4").attr("onclick","outpower();");*!/
       getMedia1()
    }*/

    function removemes() {
        $("#inputmes").val("");
    }

    var ztrainroomid="";

    var static_teststate=new Array();

    //查到被点击实训室的所有设备
    function findfacbyrid(id) {
        ztrainroomid =id;

        $("#p_left button").css("background-color","#70AD47");

        var str="";
        var p_center=$("#p_center");
        p_center.empty();
        $.ajax({
            type: "post",
            url: "/findfacilitybyrid",
            data:{"id":id,"isonline":static_isonline},
            async: false,
            success: function (data) {
                //alert(data.length)

                $("#"+id+"").css("background-color","#FFC000")

                if(data.length <7){
                    str+="<table class='p_bbbox' id='p_bbox'>"
                    str+=" <tr>";
                    for(var i=0; i<data.length;i++){
                        //str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_unpowerbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox' class='p_check'/></div></th>";
                        static_teststate[i]=data[i].zid

                        $.ajax({
                            type: "post",
                            url: "/findstunamebyfacid",
                            data:{"zid":data[i].zid},
                            async: false,
                            success: function (data2) {
                                if (data2){
                                    str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_sbox'>"+data2+"<input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                }else{
                                    str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[i].zidentity+"</font><div id='div"+data[i].zid+"' class='delivery_sbox'><input name='check' id='"+data[i].zid+"' value='"+data[i].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                }
                            }
                        })
                        static_teststate[i]=data[i].zid
                    }
                    str+="</tr>";
                    str+="</table>";
                    str+="<div class='d_button1'><font size='5'>全选：</font><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"

                }else {
                    var j=0;
                    str+="<table class='p_bbbox' id='p_bbox'>"
                    for (var i=0;i<(data.length/6+1);i++){

                        str+=" <tr>";
                        for(;j<6*(i+1);j++){
                            if(j==data.length){break;}
                            else {
                                $.ajax({
                                    type: "post",
                                    url: "/findstunamebyfacid",
                                    data:{"zid":data[j].zid},
                                    async: false,
                                    success: function (data2) {
                                        if (data2){
                                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'>"+data2+"<input name='check' id='"+data[j].zid+"' value='"+data[j].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                        }else{
                                            str+="<th><div class='power_bbox'  align='center'> <font size='3'>"+data[j].zidentity+"</font><div id='div"+data[j].zid+"' class='delivery_sbox'><input name='check' id='"+data[j].zid+"' value='"+data[j].zid+"' type='checkbox'onclick='addchoice(this)' class='p_check'/></div></th>";
                                        }
                                    }
                                })
                                static_teststate[j]=data[j].zid
                            }
                        }
                        str+="</tr>";
                        //  j+=6;
                    }

                    str+="</table>";
                    //str+="<button class='d_button1' onclick='allchose()'>全选</button>"
                    str+="<div class='d_button1' ><font size='5'>全选：</font><input class='delivery_quanxuan' type='checkbox' name='checkall' onclick='allchose()'/> </div>"
                }

                p_center.html(str)
               //电源管理
             for (var i=0;i<static_teststate.length;i++){
                    $.ajax({
                        type: "post",
                        url: "/findteststatebyfid",
                        data:{"id":static_teststate[i]},
                        async: false,
                        success: function (data) {
                           // alert(data)
                            if(data==0){
                                $("#div"+static_teststate[i]+"").css('background-color','rgba(112,167,71)');
                            }
                        }
                    });
                }
            }
        });
    }

    window.onload =function () {
        onloadallroom();
    }

    function onloadallroom(){
        var p_left=$("#p_left");
        var str=""

        $.ajax({
            type: "post",
            url: "/findalltrainroom",
            success: function (data) {

                for(var i =0; i<data.length;i++){
                    str+=" <br><br><button onclick='findfacbyrid(\""+data[i].zid+"\")' class='p_button2'id='"+data[i].zid+"'>"+data[i].zname+"</button>"
                }
                p_left.html(str)

            }
        });

    }

    function  findHaveStudent(zid){
        $.ajax({
            type: 'post',
            url: '/findStudentName',
            data:  {"zid": zid} ,
            success: function (name){
                if(name!=""){
                    $("#div"+zid).css('background-color','rgba(112,167,71)');
                }else{
                    $("#div"+zid).css('background-color','rgba(128,128,128)');
                }

            }
        });
    }

    function allchose() {
        /*$("#p_center  input[type='checkbox']").attr("checked","true");*/

        if($("input[name='checkall']").is(':checked')){
            /*$("[name='checkall']:checkbox").attr('checked', true);*/
            $("[type='checkbox']:checkbox").prop("checked", true);
            //$("#p_center  input[type='checkbox']").attr("checked","")
        }
        else{
            /*$("[name='checkall']:checkbox").attr('checked', false);*/
            $("[type='checkbox']:checkbox").prop('checked', false);
            /*$("#p_center  input[type='checkbox']").attr("checked",false)*/
        }
    }


    /**
     * 下课
     */
   /* function overclass() {
        $.ajax({
            type: "post",
            url: "/overclass",
            data:{"ztrainroomid":ztrainroomid},
            success: function (data) {
                if(data!=0){
                    layer.msg("已下课，等待电源关闭", { icon: 1, offset: "auto", time:2000 });
                }else{
                    alert("出错")
                }
            }
        });
    }*/

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
        location.href="/time_status";
    }
    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }

</script>

</html>
