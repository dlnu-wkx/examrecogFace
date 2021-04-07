<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 信息查询（智能搜索） </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
           <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <script src="https://cdn.bootcss.com/html2canvas/0.5.0-beta4/html2canvas.js"></script>
    <script src="https://cdn.bootcss.com/jspdf/1.3.4/jspdf.debug.js"></script>



    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <style>
        html,body{
            height: 97%;
        }

    </style>
</head>
<body  class="body" >
<!--头部导航条-->
<div class="top">
    <div class="leftfont">信息查询（智能搜索）</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>


<div style="width: 100%;height: 90%">
<!--中间信息查询-->
   <div class="i_center" >
      <div class="i_service">
        <font size="5">人员：</font> &emsp;&emsp;&emsp;&emsp;&emsp; <input type="text"   id="i_name" class="i_text"><br><br><br><br>
        <font size="5">事件：</font> &emsp;&emsp;&emsp;&emsp;&emsp;
        <select class="i_text3" id="selecttype">
            <option value="人脸识别">人脸识别</option>
            <option value ="查岗">查岗</option>
            <option value ="登陆">登陆</option>
            <option value="举手">举手</option>
            <option value="请假/销假">请假/销假</option>
            <option value="测试成绩">测试成绩</option>
            <option value="实训测量值">实训测量值</option>
            <option value="实训时间">实训时间</option>
        </select><br><br><br><br>
      </div>
      <div class="i_timechose">
       <font size="5" class="i_font">时间：</font> <input class="i_text4" id="startday" type="date" /><input class="i_text5" id="starttime" type="time" />
       <input class="i_text6" id="endday" type="date" /><input class="i_text7" id="endtime" type="time" />
       <br><br><br><br>
       <button class="i_button" onclick="selectmes()">查询</button>
      </div>
    </div>
    <!--右侧按键-->
    <div class="i_right" align="center">
        <button onclick="fieldManagement()" class="f_field_management">现场管理</button>
        <button id="serviceid" class="f_field_service">信息查询</button>
        <button onclick="timeStatus()" class="f_field_status">实时状态</button>
        <button onclick="informationDelivery()"class="f_field_delivery">信息发布</button>
        <button class="f_field_exit" id="exit" onclick="outpower()">退出系统</button>

    </div>
</div>

<!--模糊查询框-->
<div id="i_namelike" class="i_namelike" hidden>

</div>

<!--中间表格-->
<div class="i_excel" id="i_excel" hidden>

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
    //页面加载完成后当前页面的按钮显示橘色
    window.onLoad=aaa();
        function aaa(){
        var servicebutton = document.getElementById("serviceid");
        servicebutton.style.backgroundColor="#ED7D31"
    }
    function selectmes(){
        //alert(0)
        var i_name=$("#i_name").val()
        var selecttype=$("#selecttype").val()
        var startday=$("#startday").val()
        var endday=$("#endday").val()
        var starttime=$("#starttime").val()
        var endtime=$("#endtime").val()
        var i_excel=$("#i_excel")
        var str=""

        starttime=startday+" "+starttime+":00";
        endtime=endday+" "+endtime+":59";

        //初始值学生数为0
        var studntcount=0;

        var static_str="";
        $.ajax({
            type: "post",
            url: "/findcountbystudentname",
            data:{"zname":i_name},
            async: false,
            success: function (data){
                studntcount=data
            }
        });

        if(!i_name){
            alert(1)

        }

      else if (selecttype=="人脸识别"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"人脸识别查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='4'><font size='5'>"+i_name+"人脸识别信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth3'>识别时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectattandancemes2",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"zcheck":"人脸识别"},
                async: false,
                success: function (data){
                   // alert(createTime(data[0].mestime))
                    for (var i=0;i<data.length;i++){

                         //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="查岗"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"查岗查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='4'><font size='5'>"+i_name+"查岗信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth3'>查到时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectattandancemes",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"zcheck":"查岗"},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="登陆"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"登陆查询.xlsx\")'>下载</button>&emsp; <button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='6'><font size='5'>"+i_name+"登陆信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth2'>登陆/退出</th><th class='i_tableth3'>登陆/退出时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectinandout",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth2'>"+data[i].isintype+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else if (selecttype=="举手"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"举手查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='6'><font size='5'>"+i_name+"举手信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth3'>举手时间</th><th class='i_tableth3'>取消时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectheadsup",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime,"ztype":"举手"},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th><th class='i_tableth3'>"+createTime(data[i].deltetime)+"</th></tr>"
                    }
                }
            });
        }else if(selecttype=="请假/销假"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"请假（销假）查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='5'>"+i_name+"请假/销假信息查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>请假原因</th><th class='i_tableth3'>请假时间</th><th class='i_tableth3'>销假时间</th><th class='i_tableth2'>审批人</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectleave",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].leavereason+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th><th class='i_tableth3'>"+createTime(data[i].deltetime)+"</th>"

                        $.ajax({
                            type: "post",
                            url: "/selectteachernamebyid",
                            data:{"zid":data[i].approver},
                            async: false,
                            success: function (data2){
                                if (data2)
                                    str+="<th class='i_tableth1'>"+data2+"</th>"
                                else
                                    str+="<th class='i_tableth1'></th>"
                            }
                        })

                        str+="</tr>"
                    }
                }
            });
        }else  if(selecttype=="测试成绩"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"测试成绩查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='5'>"+i_name+"测试成绩查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>教室</th><th class='i_tableth2'>实训设备</th><th class='i_tableth2'>测试类型</th><th class='i_tableth2'>成绩</th><th class='i_tableth3'>测试时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/selectscore",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].facilityname+"</th><th class='i_tableth3'>"+data[i].testtype+"</th><th class='i_tableth2'>"+data[i].score+"</th><th class='i_tableth2'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else  if(selecttype=="实训测量值"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"实训测量值查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='6'>"+i_name+"实训测量值查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>任务名</th><th class='i_tableth2'>序号</th><th class='i_tableth2'>自检值</th><th class='i_tableth2'>师检值</th><th class='i_tableth3'>输入时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/findztaskinput",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth2'>"+data[i].score+"</th><th class='i_tableth2'>"+data[i].leavetype+"</th><th class='i_tableth2'>"+data[i].approver+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th></tr>"
                    }
                }
            });
        }else  if(selecttype=="实训时间"){
            str+="<div class='i_tbutton'><button class='i_tbutton1' onclick='loadexcel(\""+i_name+"实训时间查询.xlsx\")'>下载</button>&emsp;<button class='i_tbutton1' onclick='hideexcel()'>取消</button></div>"
            str+="<table class='i_table' id='i_table'><tr><th class='i_tableth1' colspan='7'><font size='5'>"+i_name+"实训时间查询</font></th></tr>"
            str+="<tr><th class='i_tableth1'>序号</th><th class='i_tableth1'>姓名</th><th class='i_tableth2'>任务名</th><th class='i_tableth2'>页码</th><th class='i_tableth3'>开始时间</th><th class='i_tableth3'>结束时间</th></tr>"
            $.ajax({
                type: "post",
                url: "/findztasktime",
                data:{"zname":i_name,"starttime":starttime,"endtime":endtime},
                async: false,
                success: function (data){
                    for (var i=0;i<data.length;i++){
                        //data[i].mestime=data[i].mestime..slice(0,9)+"  "+data[i].mestime..slice(10,18)
                        str+="<tr><th class='i_tableth1'>"+(i+1)+"</th><th class='i_tableth1'>"+data[i].studentname+"</th><th class='i_tableth2'>"+data[i].trainingroomname+"</th><th class='i_tableth3'>"+data[i].score+"</th><th class='i_tableth3'>"+createTime(data[i].mestime)+"</th><th class='i_tableth2'>"+createTime(data[i].deltetime)+"</th></tr>"
                    }
                }
            });
        }
        str+="</table>"
        i_excel.html(str)

        i_excel.show()
    }


    var URLToPDF = "";
    var theFileName = URLToPDF.substring(URLToPDF.lastIndexOf('/') + 1, URLToPDF.length);
    var URL = window.URL || window.webkitURL;
    function saveAs(blob, filename) {
        var type = blob.type;
        var force_saveable_type = 'application/octet-stream';
        if (type && type != force_saveable_type) {
            var slice = blob.slice || blob.webkitSlice || blob.mozSlice;
            blob = slice.call(blob, 0, blob.size, force_saveable_type);
        }
        var url = URL.createObjectURL(blob);
        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
        save_link.href = url;
        save_link.download = filename;

        var event = new MouseEvent("click", {
            bubbles: true,
            cancelable: true,
            view: window
        });
        save_link.dispatchEvent(event);
        URL.revokeObjectURL(url);
    }
    $('#downloads').on('click', function (){
        var oReq = new XMLHttpRequest();

        oReq.open("GET", URLToPDF, true);

        oReq.responseType = "blob";
        oReq.onload = function() {
            var file = new Blob([oReq.response], {
                type: 'application/pdf'
            });
            saveAs(file, theFileName);
        };
        oReq.send();
    })


    var last_counts = 20;

    function loadpdf(){
        html2canvas(document.getElementById("i_table").outerHTML, {
        onrendered: function(canvas) {
            // 页面生成的canvas高
            var contentHeight = canvas.height;

            //未生成pdf的html页面高度
            var allHeight = contentHeight;

            //pdf页面头偏移距离(偏移量)
            var position = 60;

            //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
            var imgWidth = 595;

            var imgData = canvas.toDataURL('image/jpeg');

　　　　　　/*　var img1_base = '~';
　　　　　　　var img2_base = '~';
　　　　　　　var img3_base = '~';
　　　　　　　var img4_base = '~';
　　　　　　　var img5_base = '~';*/

            //初始化pdf，设置相应格式
            var doc = new jsPDF("p", "pt", "a4");
            var p = 1,
                i = 1.5,
                z = Math.ceil(last_counts/26);
　　　　　　　
　　　　　　　// 如果高度小于一页就
            if (allHeight < 1100) {
                // 页面表单
                doc.addImage(imgData, 0, position, imgWidth, contentHeight/i);
                // 一张空白图片（将超过的覆盖）
               /* doc.addImage(img4_base, 0, 0, 600, 110);
                // 公司logo
                doc.addImage(img1_base, 10, 5, 90, 50);
                doc.addImage(img2_base, 450, 5, 130, 50);
                // 空白图片（将不需要的覆盖）
                doc.addImage(img4_base, 0, 760, 600, 200);
                doc.addImage(img3_base, 450, 780, 120, 40);

                // 订单总计图片
                doc.addImage(img5_base, 0, 68, 600, 25);*/
　　　　　　　　　// 文字（因为jspdf不支持中文，所以先将要用的中文制作成图片使用）
                doc.setFontSize(10);
                doc.text(8, 85, start_time);
                doc.text(75, 85, end_time);
                doc.text(160, 85, String(last_counts));
                doc.text(268, 85, String(total_price));
                doc.text(508, 85, String(lists[p]));

                doc.text(290, 820, String(p));
            } else {
                // 如果大于一页
                if (z > 0) {
                    // 页面表单
                    doc.addImage(imgData, 0, position, imgWidth, contentHeight/i);
                    // 空白图片
                  /*  doc.addImage(img4_base, 0, 0, 600, 110);
                    // 公司logo
                    doc.addImage(img1_base, 10, 5, 90, 50);
                    doc.addImage(img2_base, 450, 5, 130, 50);
                    // 空白图片
                    doc.addImage(img4_base, 0, 760, 600, 200);
                    doc.addImage(img3_base, 450, 780, 120, 40);

                    // 订单总计图片
                    doc.addImage(img5_base, 0, 68, 600, 25);*/
                    doc.setFontSize(10);
                   /* doc.text(8, 85, start_time);
                    doc.text(75, 85, end_time);
                    doc.text(160, 85, String(last_counts));
                    doc.text(268, 85, String(total_price));
                    doc.text(508, 85, String(lists[p]));

                    doc.text(290, 820, String(p));*/
　　　　　　　　　　　　// 每导出一页就进行相应的偏移
                    position -= 624;
                    z -= 1;
                    while (z > 0) {
                        doc.addPage('a4', 'pt');
                        // 页面表单
                        doc.addImage(imgData, 0, position, imgWidth, contentHeight/i);
                        // 空白图片
                       /* doc.addImage(img4_base, 0, 0, 600, 130);
                        // 表头
                        doc.addImage(img6_base, 5, 113, 586, 24);
                        // 公司logo
                        doc.addImage(img1_base, 10, 5, 90, 50);
                        doc.addImage(img2_base, 450, 5, 130, 50);
                        doc.addImage(img4_base, 0, 760, 600, 200);
                        doc.addImage(img3_base, 450, 780, 120, 40);
*/
                        // 订单总计图片
                        doc.addImage(img5_base, 0, 68, 600, 25);
                        doc.setFontSize(10);
                      /*  doc.text(8, 85, start_time);
                        doc.text(75, 85, end_time);
                        doc.text(160, 85, String(last_counts));
                        doc.text(268, 85, String(total_price));*/
                        p += 1;
                       /* doc.text(508, 85, String(lists[p]));

                        doc.text(300, 820, String(p));*/
                        // allHeight -= 1000;
                        position -= 624;
                        z -= 1;
                    }
                }
            }
            doc.save('bill.pdf');
        },
        background: '#FFF'
    })
    }



    function loadexcel(name) {

            //alert(name)
            var exportFileContent = document.getElementById("i_table").outerHTML;

            //使用Blob
            var blob = new Blob([exportFileContent], {type: "text/plain;charset=utf-8"});         //解决中文乱码问题
            blob =  new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
            //设置链接
            var link = window.URL.createObjectURL(blob);
            var a = document.createElement("a");    //创建a标签
            //a.download = "信息查询表格.xls";
             a.download = name;
            // 设置被下载的超链接目标（文件名）
            a.href = link;                            //设置a标签的链接
            document.body.appendChild(a);            //a标签添加到页面
            a.click();                                //设置a标签触发单击事件
            document.body.removeChild(a);            //移除a标签
    }




    function hideexcel(){
        $("#i_excel").hide()
    }

//Timestamp转字符串
    function createTime(v){
        var now = new Date(v);
        var yy = now.getFullYear();      //年
        var mm = now.getMonth() + 1;     //月
        var dd = now.getDate();          //日
        var hh = now.getHours();         //时
        var ii = now.getMinutes();       //分
        var ss = now.getSeconds();       //秒
        var clock = yy + "-";
        if(mm < 10) clock += "0";
        clock += mm + "-";
        if(dd < 10) clock += "0";
        clock += dd + " ";
        if(hh < 10) clock += "0";
        clock += hh + ":";
        if (ii < 10) clock += '0';
        clock += ii + ":";
        if (ss < 10) clock += '0';
        clock += ss;
        return clock;
    }



    //输入姓名发生改变
    $('#i_name').bind('input propertychange', function () {
        var i_namelike=$("#i_namelike")
        var str="";
        var i_name=$("#i_name").val();
        str+="<ul align='center'>"
        $.ajax({
            type: "post",
            url: "/findgradeandnamelike",
            data:{"zname":i_name},
            async: false,
            success: function (data){
                for (var i=0;i<data.length;i++){
                    str+="<li onclick='i_inputname(\""+data[i]+"\")'>"+data[i]+"</li>"
                }
            }
        });
        str+="</ul>"
        i_namelike.html(str)
        i_namelike.show();
    });
    //点击补全姓名
    function i_inputname(name) {
        $("#i_name").val(name);
        $("#i_namelike").hide();
    }


    function outpower(){
        $("#popup").show()
    }

    function lockscreen() {
        $("#parent").show()
        $("#popup").hide();
        $("#exit").css('background-color','#FFC000');
        $("#exit").text('解锁');
        $("#exit").attr("onclick","removescreer();");

    }
    function removescreer(){
        /*$("#parent").hide();
        $("#exit").text('退出系统');
        $("#exit").css('background-color','#4472c4');
        $("#exit").attr("onclick","outpower();");*/
        getMedia1();
    }

    //信息发布
    function informationDelivery() {
        location.href="/information_delivery";
    }
    //实时状态
    function timeStatus() {
        location.href="/student_status";
    }
    //现场管理
    function fieldManagement() {
        location.href="/field_management";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }


    var mediaStreamTrack;
    var time=null;
    function getMedia1() {
        $("#showVdieo").empty();
        let videoComp = "<video  muted id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
        $("#showVdieo").append(videoComp);

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
        promise.then(function (stream) {
            mediaStreamTrack = typeof stream.stop === 'function' ? stream : stream.getTracks()[1];
            video.srcObject = stream;
            video.play();
        });

        // var t1 = window.setTimeout(function() {
        //     takePhoto();
        // },2000)
        time= window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
            chooseFileChangeCompI_S()
        }, 5000);

    }

    function chooseFileChangeCompI_S() {

        /* var ip=returnCitySN["cip"];*/


        let showVdieo = $("#showVdieo");
        if (showVdieo.has('video').length) {
            let video = document.getElementById("video");
            let canvas = document.getElementById("canvas");
            let ctx = canvas.getContext('2d');
            ctx.drawImage(video, 0, 0, 500, 500);
            var base64File = canvas.toDataURL();
            var formData = new FormData();
            formData.append("groupId", "101")
            formData.append("file", base64File);

            //var data=getOsInfo();

            //操作系统

            //ip地址
            formData.append("ip",1);

            $.ajax({
                type: "post",
                url: "/faceTeacherSearch",
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
                        mediaStreamTrack.stop();
                        $("#showVdieo").hide();
                        $("#parent").hide();
                        $("#exit").text('退出系统');
                        $("#exit").css('background-color','#4472c4');
                        $("#exit").attr("onclick","outpower();");
                        clearInterval(time);
                    } else {
                        $("#nameDiv").html("");
                        $("#similarDiv").html("");
                        $("#ageDiv").html("");
                        $("#genderDiv").html("");

                        showTips(text.message);
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



</script>

</html>