<head>
    <meta charset="UTF-8">
    <title> 测试管理 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script type="text/javascript" src="./layui/js/jQuery_timers.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/js/common.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/exit.js "></script>
</head>
<style>
    html,body{
        height: 100%;
        margin: 0;
        padding: 0;}
    #side_nav ul{display:none}
</style>
<body  class="body">
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

<!--在线-->
<div class="s_online">
    <font size="5">在线：</font><input type="checkbox" class="delivery_quanxuan2" id="s_onlinebox" onclick="getisonline()">
</div>


<!--头部导航条-->
<div class="top">
    <div class="leftfont">实 时 状 态 显 示</div>

    <div class="topcenter">
        安 浩 智 能 学 习 工 厂
    </div>

    <div class="rightfont" id="m_rightfont"></div>
</div>


<!--中部电源显示-->
<div class="p_center" id="p_center">

</div>



<!--右侧按键-->
<div class="p_right" align="center">
    <button onclick="fieldManagement()" class="p_field_management">现场管理</button>
<br><br>
    <button onclick="informationService()" class="p_information_service">信息查询</button>
<br><br>
    <button onclick="timeStatus()" id="statusid" class="p_time_status">实时状态</button>
<br><br>
    <button  class="p_information_delivery">信息发布</button>
    <br><br>
    <button class="p_exit" id="p_button4" onclick="outpower()">退出系统</button>
</div>
<#--<div class="outdiv">
   </div>-->


<!--下方按键及内容-->
<div class="p_text" align="center" onclick="removebig()">

    <font class="P_fachoose" size="5">机床选择:</font>
        <button class="singlechoose" onclick="openchose()" id="openchose">点选:</button>
        <button class="allchoose" onclick="allchose()" id="allchose">全选:</button>
    <font class="p_testopen" size="5">测试开关:</font>
    <input type="checkbox" id="p_testisopen" class="p_testisopen" name="p_isconnectopen" onchange="istestchange()">

    <font class="p_isconnect" size="5">测试与电源联动:</font>
    <input type="checkbox" id="p_isconnectopen" class="p_isconnectopen" hidden>

    <div class="p_trainroomchose" id="p_trainroomchose">

    </div>

    <font class="p_testbankchoose" size="5">测试题库:</font>

    <div class="p_stestchose" id="p_stestchose" onchange="getalltestnumber()">

    </div>


    <font class="p_tetestnum" size="5">题目数量:</font>

    <select class="p_stestnmchose" id="p_testnum">
        <option value="5">5</option>
        <option value="10" selected="selected">10</option>
        <option value="20">20</option>
        <option value="50">50</option>
        <option value="100">100</option>
        <option value="9999">全部</option>
    </select>

    <font class="p_tefpass" size="5">合格分数:</font>

    <input type="tel" id="p_passcode" class="p_tepasschose" value="0">

    <button id="p_chbuttonpa" class="p_chbuttonpa" onclick="chosetestnumber()">选择</button>


    <div class="p_tepasschose2" id="p_passcode2" hidden>
        <font size="3" onclick="chosefixednumber(10)">10</font><br>
        <font size="3" onclick="chosefixednumber(20)">20</font><br>
        <font size="3" onclick="chosefixednumber(30)">30</font><br>
        <font size="3" onclick="chosefixednumber(40)">40</font><br>
        <font size="3" onclick="chosefixednumber(50)">50</font><br>
        <font size="3" onclick="chosefixednumber(60)">60</font><br>
        <font size="3" onclick="chosefixednumber(70)">70</font><br>
        <font size="3" onclick="chosefixednumber(80)">80</font><br>
        <font size="3" onclick="chosefixednumber(90)">90</font><br>
        <font size="3" onclick="chosefixednumber(100)">100</font><br>
    </div>

    <#--<select class="p_tepasschose2" id="p_passcode2">
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
    </select>-->

    <button class="p_clcik" onclick="controllerthing()">确认</button>

</div>

<!--放大设备信息-->
<div id="trainfactity" class="bigfactity" hidden>


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


<div id="headeventdiv" class="p_headeventdiv" hidden>

</div>


</body>



<script>
    var stataic_isonline=0;

    function getisonline(){
        if(stataic_isonline==0){

            stataic_isonline=1;
            loadfaclity(static_trainroomid)
        }else if(stataic_isonline==1){
            stataic_isonline=0;
            loadfaclity(static_trainroomid)
        }

    }




    function chosetestnumber(){
        $("#p_passcode2").show()
    }

    function chosefixednumber(number){
        //alert(number)
        $("#p_passcode").val(number)
        $("#p_passcode2").hide()
    }

    function fieldManagement(){
        location.href="/field_management";
    }

    function timeStatus() {
        location.href="/student_status";
    }

    /*//退出
    function outpower(){
        $("#popup").show()
    }*/

    window.onLoad=aaa();
    function aaa(){
        var servicebutton = document.getElementById("statusid");
        servicebutton.style.backgroundColor="#ED7D31"
    }

    var istestchange=0;

    $("#p_testisopen").change(function() {
        if(istestchange==0){
            $("#p_isconnectopen").show()
            istestchange=1;
        }else {
            $(".p_isconnectopen").prop("checked", false);
            $("#p_isconnectopen").hide()
            istestchange=0;
        }
    });

   /* function istestchange(){
        if(istestchange==0){
            $("#p_isconnectopen").show()
            istestchange=1;
        }else {
            $("#p_isconnectopen").hide()
            istestchange=0;
        }
    }*/

    function controllerthing(){
        //合格分数
        var p_passcode=$("#p_passcode").val()
        //题目数量
        var p_testnum=$("#p_testnum").val()
        //题库类型
        var p_testtype=$("#p_testtype").val()

        var istest=0
        var isconnected=0

        if($('#p_testisopen').is(':checked')){
            istest=1;
        }

        if($('#p_isconnectopen').is(':checked')){
            isconnected=1;
        }
        //所有选中的id
        var startchose =[];

        $("input[name='topchose']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            startchose[i] =$(this).val();
        });

       // alert(istest)
        $.ajax({
            type: "post",
            url: "/updatefaclitybychose2",
            data:{"zid":startchose,"p_passcode":p_passcode,"p_testnum":p_testnum,"p_testtype":p_testtype,"istest":istest,"isconnected":isconnected},
            async: false,
            success: function (data) {
                if(data>0){
                    layer.msg("已修改成功", { icon: 1, offset: "auto", time:1000 });

                    $("#openchose").css("background","#4472C4");
                    static_chose=0;
                    $("#allchose").css("background","#4472C4");
                    static_choseval=0;

                    setTimeout(function (){ loadfaclity(static_trainroomid)},100);

                }else{
                    alert("出错")
                }

            }
        })
    }

    var static_chose=0

    //全选
    function allchose() {
        //alert(2)
        openchose()
        if(static_chose==0){
            $("input[name='topchose']:checkbox").prop("checked", true);
            $("#allchose").css("background","#F2A774");
            static_chose = 1;
        }
        else{
            $("input[name='topchose']:checkbox").prop('checked', false);
            $("#allchose").css("background","#4472C4");
            static_chose=0;
        }
    }

    function removebig(){
        $("#trainfactity").hide()
    }

    //放大th
    function loadth(zid){

        //alert(zid);
        var trainfactity=$("#trainfactity")
        var str=""

        $.ajax({
            type: "post",
            url: "/findfaclitybyzid",
            data:{"zid":zid},
            async: false,
            success: function (data) {
                str+="<div class='power_bbox4'  align='center'> "

                str+="<div class='p_bigclose' onclick='removebig()'> <img src='/images/close.jpg'  class='p_close'/></div>"

                str+="<div  class='delivery_unpowerbox2' align='center'>"
                str+="<div class='p_islogin'>"

                if(data.zpowerStatus5==2){
                    str += "<button class='p_islobutton3'></button></div>"
                }else if (data.zpowerStatus6==0) {
                    str += "<button class='p_islobutton'></button></div>"
                }else if(data.zpowerStatus6==1){
                    str += "<button class='p_islobutton2'></button></div>"
                }


                str+="<font class='p_faclinum' size='5' >"+data.zidentity+"</font>"


                $.ajax({
                    type: "post",
                    url: "/findstunamebyfacid",
                    data:{"zid":data.zid},
                    async: false,
                    success: function (data2) {
                        str+="<font class='p_facliname' size='5'>"+data2+"</font>"
                    }
                })


                str+="<div class='p_ispower'>"

                if (data.zpowerStatus1==1 && data.zpowerStatus2==0){
                    str+="<button class='p_ispobutton2'></button> </div>"
                }else if(data.zpowerStatus1==0 && data.zpowerStatus2==1){
                    str+="<button class='p_ispobutton3'></button> </div>"
                }else{
                    str+="<button class='p_ispobutton1'></button> </div>"
                }


                str+=" <font class='p_fistest' size='5'>是否测试</font>"

                if (data.zselecttest=="是"){
                    str+="<font class='p_istest2' size='5'>开</font>"
                }else{
                    str+="<font class='p_istest1' size='5'>关</font>"
                }

                str+="<font class='p_fispower' size='5'>电源联动</font>"

                if (data.zpowerStatus8==0){
                    str+=" <font class='p_ispower2' size='5'>关</font>"
                } else{
                    str+=" <font class='p_ispower3' size='5'>开</font>"
                }

                str+="<font class='p_ftestnum' size='5'>题目数量</font>"

                if(data.zsafetestingNum==9999){

                    $.ajax({
                        type: "post",
                        url: "/findtnumberbyfaid",
                        data:{"zid":data.zid},
                        async: false,
                        success: function (data6) {
                            str+=" <font class='p_testnum' size='5'>"+data6+"</font>"
                        }
                    })

                }else {
                    str+=" <font class='p_testnum' size='5'>"+data.zsafetestingNum+"</font>"
                }


                str+=" <font class='p_fpassnum' size='5'>合格分数</font>"
                str+="<font class='p_passnum' size='5'>"+data.zpassingscore+"</font>"

                $.ajax({
                    type: "post",
                    url: "/findtatbyip",
                    data:{"zid":data.zid},
                    async: false,
                    success: function (data5) {
                        if (data5){
                            if (data5.ztesttime==0){
                                str+=" <font class='p_testtime1' size='5'>"+data5.ztesttime+"</font>"
                                str+="<font class='p_testname1' size='5'>"+data.zsafetestingType+"</font>"
                            }else if((data5.ztesttime>=1 && data5.ztesttime<=4 && data.zpowerStatus7==0)||(data5.ztesttime>=2 && data5.ztesttime<=4 && data.zpowerStatus7==1)){
                                str+=" <font class='p_testtime2' size='5'>"+data5.ztesttime+"</font>"
                                str+="<font class='p_testname2' size='5'>"+data.zsafetestingType+"</font>"
                            }else if(data5.ztesttime==1 && data.zpowerStatus7==1){
                                str+=" <font class='p_testtime3' size='5'>"+data5.ztesttime+"</font>"
                                str+="<font class='p_testname3' size='5'>"+data.zsafetestingType+"</font>"
                            }else {
                                str+=" <font class='p_testtime4' size='5'>"+data5.ztesttime+"</font>"
                                str+="<font class='p_testname4' size='5'>"+data.zsafetestingType+"</font>"
                            }
                            str+="<font class='p_nowtask' size='5'>"+data5.znowtaskname+"</font>"
                        }
                        else {
                            str+=" <font class='p_testtime1' size='5'>0</font>"
                            str+="<font class='p_testname1' size='5' >"+data.zsafetestingType+"</font>"

                            str+="<font class='p_nowtask'></font>"
                        }

                    }
                })


                //onclick='handevent(1,\""+data3.zid+"\")'

                $.ajax({
                    type: "post",
                    url: "/getishandsup",
                    data:{"zid":data.zid,"ztype":"举手"},
                    async: false,
                    success: function (data3) {
                        if (data3)
                            str+="<font class='p_upheads1' size='5' id='"+data3.zid+"' >举手</font>";
                        else
                            str+="<font class='p_upheads2' size='5'></font>";
                    }
                })

                $.ajax({
                    type: "post",
                    url: "/getishandsup",
                    data:{"zid":data.zid,"ztype":"请假"},
                    async: false,
                    success: function (data4) {
                        if (data4)
                            str+="<font class='p_leavereson1' size='5'>请假:"+data4.zcontent+"</font>";
                        else
                            str+="<font class='p_leavereson2' size='5'></font>";
                    }
                })

                str+="</div> </div>";
                trainfactity.html(str);
                trainfactity.show();
            }
        })


    }

    var static_choseval=0;

    //点选方法
    function openchose(){

        if (static_choseval==0){
            $("#openchose").css("background","#F2A774");
            static_choseval=1;
            $("input[name='topchose']").show()
            clearInterval(interval);
        }else{
            $("#openchose").css("background","#4472C4");
            static_choseval=0;
            $("input[name='topchose']").hide()
            interval= window.setInterval(function () {
                loadfaclity(static_trainroomid);
            }, 10000);
        }

    }


    var static_trainroomid=""

    var interval;
    window.onload =function () {
        loadteachername()
        onloadallroom();
        loadalltesttype();
        getteacherroom();
        loadfaclity(static_trainroomid);

      /*interval= window.setInterval(function () {
            loadfaclity(static_trainroomid);
        }, 20000);*/
    }


    var static_allnumber=0;

    function getalltestnumber(){
        //数量
        var p_testnum=$("#p_testnum")
        //题库
        var p_testtype=$("#p_testtype")

        $.ajax({
            type: "post",
            url: "/findtenumbytype",
            data:{"type":p_testtype},
            async: false,
            success: function (data) {
                static_allnumber=data;
            }
        })

    }

   /* var static_eventid=""

    function  handevent(kind,id){
        if (kind==1){

            $.ajax({
                type: "post",
                url: "/findfacilitybyrid",
                data:{"id":id},
                async: false,
                success: function (data) {

                }
            })

        }else{
            static_eventid=id;
            $("#headeventdiv").show()
        }

    }*/


    function loadfaclity(trainroomid) {

        //alert(trainroomid)
        var p_center=$("#p_center")
        var p_center_top=$("#p_center_top")
        var str=""
        p_center.empty();

        $.ajax({
            type: "post",
            url: "/findfacilitybyrid",
            data:{"id":trainroomid,"isonline":stataic_isonline},
            async: false,
            success: function (data) {
                //alert(data.length)

                //alert(data)
                if(data.length <7){
                    str+="<table class='p_bbbox_1' id='p_bbox'>"
                    str+="<tr  align='center'>";
                    str+="<th></th>";
                    str+="<th></th>";
                    str+="<th></th>";
                    str+="<th></th>";
                    str+="<th></th>";
                    str+="</tr>";
                    str+=" <tr>";

                    for(var i=0; i<data.length;i++){

                        str+="<th ><div class='power_bbox2_1'  align='center'> "
                        str+="<input type='checkbox' id='"+data[i].zid+"' value='"+data[i].zid+"' class='chosetop' hidden name='topchose'>"
                        str+="<div  class='delivery_unpowerbox2' align='center' onclick='loadth(\""+data[i].zid+"\")'>"


                        str+="<div class='p_islogin'>"
                        if(data[i].zpowerStatus5==2){
                            str += "<button class='p_islobutton3'></button></div>"
                        } else if (data[i].zpowerStatus6==0) {
                            str += "<button class='p_islobutton'></button></div>"
                        }else if(data[i].zpowerStatus6==1){
                            str += "<button class='p_islobutton2'></button></div>"
                        }
                        str+="<font class='p_faclinum' >"+data[i].zidentity+"</font>"

                        $.ajax({
                            type: "post",
                            url: "/findstunamebyfacid",
                            data:{"zid":data[i].zid},
                            async: false,
                            success: function (data2) {
                                str+="<font class='p_facliname'>"+data2+"</font>"
                            }
                        })


                        str+="<div class='p_ispower'>"

                            if (data[i].zpowerStatus1==1 && data[i].zpowerStatus2==0){
                                str+="<button class='p_ispobutton2'></button> </div>"
                            }else if(data[i].zpowerStatus1==0 && data[i].zpowerStatus2==1){
                                str+="<button class='p_ispobutton3'></button> </div>"
                            }else{
                                str+="<button class='p_ispobutton1'></button> </div>"
                            }


                        str+=" <font class='p_fistest' size='2'>是否测试</font>"

                        if (data[i].zselecttest=="是"){
                            str+="<font class='p_istest2' size='2'>开</font>"
                        }else{
                            str+="<font class='p_istest1' size='2'>关</font>"
                        }

                        str+="<font class='p_fispower' size='2'>电源联动</font>"

                        if (data[i].zpowerStatus8==0){
                            str+=" <font class='p_ispower2' size='2'>关</font>"
                        } else{
                            str+=" <font class='p_ispower3' size='2'>开</font>"
                        }

                        str+="<font class='p_ftestnum' size='2'>题目数量</font>"

                        if(data[i].zsafetestingNum==9999){

                            $.ajax({
                                type: "post",
                                url: "/findtnumberbyfaid",
                                data:{"zid":data[i].zid},
                                async: false,
                                success: function (data6) {
                                    str+=" <font class='p_testnum' size='2'>"+data6+"</font>"
                                }
                            })

                        }else {
                            str+=" <font class='p_testnum' size='2'>"+data[i].zsafetestingNum+"</font>"
                        }



                        str+=" <font class='p_fpassnum' size='2'>合格分数</font>"
                        str+="<font class='p_passnum' size='1'>"+data[i].zpassingscore+"</font>"

                        $.ajax({
                            type: "post",
                            url: "/findtatbyip",
                            data:{"zid":data[i].zid},
                            async: false,
                            success: function (data5) {
                                if (data5){
                                    if (data5.ztesttime==0){
                                        str+=" <font class='p_testtime1'>"+data5.ztesttime+"</font>"
                                        str+="<font class='p_testname1' size='1'>"+data[i].zsafetestingType+"</font>"
                                    }else if(data5.ztesttime>=2 && data5.ztesttime<=4 ){
                                        str+=" <font class='p_testtime2'>"+data5.ztesttime+"</font>"
                                        str+="<font class='p_testname2' size='1'>"+data[i].zsafetestingType+"</font>"
                                    }else if(data5.ztesttime==1 ){
                                        str+=" <font class='p_testtime3'>"+data5.ztesttime+"</font>"
                                        str+="<font class='p_testname3' size='1'>"+data[i].zsafetestingType+"</font>"
                                    }else {
                                        str+=" <font class='p_testtime4'>"+data5.ztesttime+"</font>"
                                        str+="<font class='p_testname4' size='1'>"+data[i].zsafetestingType+"</font>"
                                    }
                                    str+="<font class='p_nowtask'>"+data5.znowtaskname+"</font>"
                                }
                                else {
                                    str+=" <font class='p_testtime1'>0</font>"
                                    str+="<font class='p_testname1' size='1' >"+data[i].zsafetestingType+"</font>"

                                    str+="<font class='p_nowtask'></font>"
                                }

                            }
                        })




                        $.ajax({
                            type: "post",
                            url: "/getishandsup",
                            data:{"zid":data[i].zid,"ztype":"举手"},
                            async: false,
                            success: function (data3) {
                                if (data3)
                                    str+="<font class='p_upheads1'>举手</font>";
                                else
                                    str+="<font class='p_upheads2'></font>";
                            }
                        })

                        $.ajax({
                            type: "post",
                            url: "/getishandsup",
                            data:{"zid":data[i].zid,"ztype":"请假"},
                            async: false,
                            success: function (data4) {
                                if (data4)
                                    str+="<font class='p_leavereson1'>请假:"+data4.zcontent+"</font>";
                                else
                                    str+="<font class='p_leavereson2'></font>";
                            }
                        })


                        str+="</div> </div></th>";

                    }
                    str+="</tr>";

                    for(var i=0;i<4;i++){
                        str+="<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"
                    }
                    str+="</table>";



                }else {
                    var j=0;
                    str+="<table class='p_bbbox' id='p_bbox'>"

                    for (var i=0;i<(data.length/6+1);i++){

                        str+=" <tr>";

                        for(;j<6*(i+1);j++){
                            if(j==data.length){break;}
                            else {
                                    str+="<th ><div class='power_bbox2'  align='center'> "
                                    str+="<input type='checkbox' id='"+data[j].zid+"' value='"+data[j].zid+"' class='chosetop' hidden name='topchose'>"
                                    str+="<div  class='delivery_unpowerbox2' align='center' onclick='loadth(\""+data[j].zid+"\")'>"


                                    str+="<div class='p_islogin'>"
                                if(data[j].zpowerStatus5==2){
                                    str += "<button class='p_islobutton3'></button></div>"
                                }else if (data[j].zpowerStatus6==0) {
                                    str += "<button class='p_islobutton'></button></div>"
                                }else if(data[j].zpowerStatus6==1){
                                    str += "<button class='p_islobutton2'></button></div>"
                                }
                                    str+="<font class='p_faclinum' >"+data[j].zidentity+"</font>"

                                $.ajax({
                                    type: "post",
                                    url: "/findstunamebyfacid",
                                    data:{"zid":data[j].zid},
                                    async: false,
                                    success: function (data2) {
                                        str+="<font class='p_facliname'>"+data2+"</font>"
                                    }
                                })


                                    str+="<div class='p_ispower'>"

                                    if (data[j].zpowerStatus1==1 && data[j].zpowerStatus2==0){
                                        str+="<button class='p_ispobutton2'></button> </div>"
                                    }else if(data[j].zpowerStatus1==0 && data[j].zpowerStatus2==1){
                                        str+="<button class='p_ispobutton3'></button> </div>"
                                    }else{
                                        str+="<button class='p_ispobutton1'></button> </div>"
                                    }


                                    str+=" <font class='p_fistest' size='2'>是否测试</font>"

                                    if (data[j].zselecttest=="是"){
                                        str+="<font class='p_istest2' size='2'>开</font>"
                                    }else{
                                        str+="<font class='p_istest1' size='2'>关</font>"
                                    }

                                    str+="<font class='p_fispower' size='2'>电源联动</font>"

                                    if (data[j].zpowerStatus8==0){
                                        str+=" <font class='p_ispower2' size='2'>关</font>"
                                    } else{
                                        str+=" <font class='p_ispower3' size='2'>开</font>"
                                    }

                                    str+="<font class='p_ftestnum' size='2'>题目数量</font>"

                                if(data[j].zsafetestingNum==9999){

                                    $.ajax({
                                        type: "post",
                                        url: "/findtnumberbyfaid",
                                        data:{"zid":data[j].zid},
                                        async: false,
                                        success: function (data6) {
                                            str+=" <font class='p_testnum' size='2'>"+data6+"</font>"
                                        }
                                    })

                                }else {
                                    str+=" <font class='p_testnum' size='2'>"+data[j].zsafetestingNum+"</font>"
                                }

                                    str+=" <font class='p_fpassnum' size='2'>合格分数</font>"
                                    str+="<font class='p_passnum' size='1'>"+data[j].zpassingscore+"</font>"

                                $.ajax({
                                    type: "post",
                                    url: "/findtatbyip",
                                    data:{"zid":data[j].zid},
                                    async: false,
                                    success: function (data5) {
                                        if (data5){
                                            if (data5.ztesttime==0){
                                                str+=" <font class='p_testtime1'>"+data5.ztesttime+"</font>"
                                                str+="<font class='p_testname1' size='1'>"+data[j].zsafetestingType+"</font>"
                                            }else if(data5.ztesttime>=2 && data5.ztesttime<=4 ){
                                                str+=" <font class='p_testtime2'>"+data5.ztesttime+"</font>"
                                                str+="<font class='p_testname2' size='1'>"+data[j].zsafetestingType+"</font>"
                                            }else if(data5.ztesttime==1){
                                                str+=" <font class='p_testtime3'>"+data5.ztesttime+"</font>"
                                                str+="<font class='p_testname3' size='1'>"+data[j].zsafetestingType+"</font>"
                                            }else {
                                               /* alert(data5.ztesttime)
                                                alert(data[j].zpowerStatus7)*/
                                                str+=" <font class='p_testtime4'>"+data5.ztesttime+"</font>"
                                                str+="<font class='p_testname4' size='1'>"+data[j].zsafetestingType+"</font>"
                                            }
                                            str+="<font class='p_nowtask'>"+data5.znowtaskname+"</font>"
                                        }
                                        else {
                                            str+=" <font class='p_testtime1'>0</font>"
                                            str+="<font class='p_testname1' size='1' >"+data[j].zsafetestingType+"</font>"

                                            str+="<font class='p_nowtask'></font>"
                                        }

                                    }
                                })





                                    $.ajax({
                                        type: "post",
                                        url: "/getishandsup",
                                        data:{"zid":data[j].zid,"ztype":"举手"},
                                        async: false,
                                        success: function (data3) {

                                            if (data3)
                                                str+="<font class='p_upheads1'>举手</font>";
                                            else
                                                str+="<font class='p_upheads2'></font>";
                                        }
                                     })

                                $.ajax({
                                    type: "post",
                                    url: "/getishandsup",
                                    data:{"zid":data[j].zid,"ztype":"请假"},
                                    async: false,
                                    success: function (data4) {
                                        if (data4)
                                            str+="<font class='p_leavereson1'>请假:"+data4.zcontent+"</font>";
                                        else
                                            str+="<font class='p_leavereson2'></font>";
                                    }
                                })
                                    str+="</div> </div></th>";

                            }
                        }
                        str+="</tr>";

                    }
                    str+="</table>";

                }
                p_center.html(str)

            }
    });
    }



    function getteacherroom() {
        $.ajax({
            type: "post",
            url: "/getteacherroom",
            data:{},
            async: false,
            success: function (data) {
                static_trainroomid=data;
            }
        });
    }

    function onloadallroom() {
        var str=""
        var p_trainroomchose=$("#p_trainroomchose")

        $.ajax({
            type: "post",
            url: "/findalltrainroom",
            success: function (data) {
                str+="<select class='isin' id='testchange' onchange='trainroomchange()'>"
                for(var i =0; i<data.length;i++){
                    str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>"
                }
                str+="</select>"
                p_trainroomchose.html(str)

            }
        });
    }


    function loadalltesttype(){
        var str="";
        var p_stestchose=$("#p_stestchose")
        $.ajax({
            type: "post",
            url: "/findallsafetype",
            async: false,
            success: function (data) {
                str+="<select class='isin' id='p_testtype' >"
                for(var i =0; i<data.length;i++){
                    str+="<option  value='"+data[i]+"'>"+data[i]+"</option>"
                }
                str+="</select>"
                p_stestchose.html(str)

            }
        });
    }

    function trainroomchange(){
        //alert(1)
        static_trainroomid=$("#testchange").val()
        //alert(static_trainroomid)
        loadfaclity(static_trainroomid)
    }

</script>

</html>
