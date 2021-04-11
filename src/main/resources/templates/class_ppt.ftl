<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 实训任务 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/class_ppt.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/fixed_task.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>

</head>
<body class="body">
<!--警示消息-->
<div>

    <script>
        var layer;
        $(function () {
            layui.use("layer", function () {
                layer = layui.layer;
            });
        })
    </script>

</div>


<!--分屏-->
<div class="com_screen" id="com_screen" hidden>
    <div class="com_screen21" id="com_screen21"></div>
    <div class="com_screen22" id="com_screen22"></div>
</div>



<!--头部导航条-->
<div class="top">
    <div class="leftfont"><font size="5" >实训任务</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>
<#--隐藏区域是为了解决摄像头显示其他不能点击的问题-->
<div class = "mid" style="background-image: url(/images1/background.png); background-size:100%; height: 100%">
    <div id="hiddenArea"
         style="position: absolute;height: 200%;width: 200%;filter: alpha(opacity=60);opacity: 0.6;display: none;z-index: 2"></div>
    <!--左侧灰色按键-->
    <div class="left" id="leftbutton">

    </div>


    <div id="regcoDiv"
         style="position: absolute;width: 69%;height: 380px;margin-top: 2%;margin-left: 15%;display: none"></div>


    <div id="chagangID" style="display: none"></div>
    <div id="gundongID" style="display: none"></div>

    <!--请假弹框-->
    <div class="co_leavemes" hidden id="co_leavemes" align="center">
        <font size="5">请假原因</font>
        <#--<textarea type="text" class="co_mes" id="co_mes"></textarea>-->
        <select id="co_mes" class="co_mes">
            <option value="事假">事假</option>
            <option value="病假">病假</option>
            <option value="卫生间">卫生间</option>
            <option value="其它">其它</option>
        </select>
        <button class="co_button" onclick="common_leave()">确认</button>
    </div>


    <div class="right">
        <!--右侧按键-->
        <div class="right1">
            <button class="button5" onclick="upheads()" id="upheads">举手</button>
        </div>

        <div class="right2">
            <button class="button5" onclick="showleave()" id="leave">请假</button>
        </div>

        <div class="right3">
            <button class="button5" onclick="leaveclass()">退出系统</button>
        </div>
    </div>


    <!--中间题目主体部分-->
    <div class="center" id="cp_content" style="background-color: white;z-index:-1;">

    </div>


    <!--翻页-->
    <div class="pages" hidden id="pages">
        <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
        <div class="cp_number" id="cp_number"></div>
        <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
    </div>


    <!--滚动弹幕-->
    <div class="rolling_barrage" id="rolling_barrage" hidden>


    </div>

</div>
<br><br>
</body>

<script>

    var indexleft = 1;
    var indexright = 1;

    //改变图片
    function ChangeImgright() {

        indexright++;
        var a = document.getElementsByClassName("img-slideright");

        if (indexright >= a.length) indexright = 0;

        for (var i = 0; i < a.length; i++) {

            a[i].style.display = 'none';
        }
        a[indexright].style.display = 'block';
    }

    function ChangeImgleft() {

        indexleft++;
        var a = document.getElementsByClassName("img-slideleft");

        if (indexleft >= a.length) indexleft = 0;

        for (var i = 0; i < a.length; i++) {

            a[i].style.display = 'none';
        }
        a[indexleft].style.display = 'block';
    }

    //左右时间
    var fonttotime = {left: 10, right: 10};


    function getscreencontent(commandlocation, screenid, order) {

        $.ajax({
            type: "post",
            url: "/findscreencontenbyid",
            data: {"id": screenid},
            async: false,
            success: function (data2) {
                //alert(data2.zcontent)
                var ztype = data2.ztype;
                var str1 = ""
                var com_screen21 = $("#com_screen21")
                if (ztype == "图片") {
                    // alert(1)
                    var time2 = data2.zcontent.split(";");
                    //alert(time2)
                    var time = time2[0];
                    var screenimage = time2[1];
                    //alert(screenimage)

                    var screenimaget = new Array();
                    screenimaget = screenimage.split(",")

                    var index2 = new Array();
                    var screenimagec = new Array();
                    for (var i = 0; i < screenimaget.length; i++) {
                        index2[i] = screenimaget[i].lastIndexOf("\/");
                        screenimagec[i] = screenimaget[i].substring(index2[i] + 1, screenimaget[i].length);
                        str1 += "<img class='img-slide" + order + " img" + (i + 1) + "' src='" + screenimagec[i] + "'>"
                    }
                    // alert(screenimagec)
                    commandlocation.html(str1)
                    if (order == "left") {
                        setInterval(ChangeImgleft, time * 1000)
                    } else if (order == "right") {
                        setInterval(ChangeImgright, time * 1000)
                    }
                }
            }
        })
    }

    var static_time = ""

    function loadscreentime() {
        $.ajax({
            type: "post",
            url: "/findclosescreen",
            async: false,
            success: function (data) {

                if (data != static_time) {
                    $("#com_screen").hide()
                    $("#com_screen21").empty()
                    $("#com_screen22").empty()
                    //  getcommand2()
                }
            }
        })
    }

    /* function getcommand2() {
         $.ajax({
             type: "post",
             url: "/findscreencommand",
             data: {},
             async: false,
             success: function (data){
                 static_time=data.zpublishtime
                 var comscreen= $("#com_screen")
                 var screentype=data.zcontent.split(";");
                 //alert(screentype[0])
                 if (screentype[0]=='2'){

                     var commandscreenid1=screentype[1].split(",")[0];
                     var commandscreenid2=screentype[1].split(",")[1];

                     var com_screen21=$("#com_screen21")
                     var com_screen22=$("#com_screen22")
                     //将左右两个放入其中
                     getscreencontent(com_screen21,commandscreenid1,"left")
                     getscreencontent(com_screen22,commandscreenid2,"right")

                     comscreen.show()

                 }
             }
         })

     }
 */


    //备用参数，方便处理逻辑
    var j = 0;

    //默认页面数
    var pages = 1;
    //training_taskid
    var static_taskid = "";

    //共有几页，最后一页
    var last_page = 0;
    //training_task_contentid
    var task_contentid = 0;
    //zassign_scheduleid
    var zassign_scheduleid = 0;

    //任务类型：1为固定任务，2为临时任务
    var static_kindid = 0;

    //当前任务内容的类型（文字，图片，测量数据，视频）
    var static_ztype = "";

    //测量数据类任务的参数
    //学生的自检值，填入文本类的值
    var zselfcheck = new Array();
    //实训任务评分表id，每条测量数据都有一个id
    var ztrainingtaskassessID = new Array();
    //需要测量的总数据条数
    var static_assessnum = 0;


    function sendclassmessage(zname, taskid, kindid, assid, pages) {
        //alert(taskid)
        //保存已做题目信息
        $.ajax({
            type: "post",
            url: "/sendclassmessage",
            async: false,
            data: {"zname": zname, "taskid": taskid, "kindid": kindid, "assid": assid, "pages": pages},
            success: function (data) {

            }
        });

    }


    //加载页面前页码及按键逻辑处理（名称,任务表id,任务类型，固定任务Id）
    function loadcontentbypages2(zname, taskid, kindid, assid) {
        //alert(taskid)
        //sendclassmessage(zname,taskid,kindid,assid)
        static_zname = zname;
        $(".cp_button2").css("background-color", "#0d2f52");
        $(".cp_button1").css("background-color", "rgb(255, 192, 0)");


        $("#" + taskid + "").css("background-color", "#d2744b");

        //  alert(zname)
        $.ajax({
            type: "post",
            url: "/updatetaskname",
            async: false,
            data: {"zname": zname},
            success: function (data) {

            }
        });
        //每次点击一个新任务都将交卷更改为下一页并变更方法
        $("#nextpage").attr("onclick", "nextpage()");
        $("#nextpage").text('下一页');

        //页码代码
        $("#pages").show()
        //当前页为1
        if (static_ismes != 1) {
            pages = 1;
        }

        loadcontentbypages(taskid, kindid, assid);

        //上一页按键变灰
        $("#lastpage").css("background-color", "#a5a5a5");
        //移除上一页方法
        $("#lastpage").removeAttr("onclick");

        //如果这个任务只有一页
        if (last_page == 1) {
            //下一面变灰，并移除下一页方法
            $("#nextpage").text("结束任务")
            $("#nextpage").attr("onclick", "sumbmitpages()");
            /* $("#nextpage").css("background-color","#A5A5A5");
             $("#nextpage").removeAttr("onclick");*/

        } else {
            //除了上述特殊情况外，正常加载下一页方法及颜色
            $("#nextpage").css("background-color", "#4472C4");
            $("#nextpage").attr("onclick", "nextpage()");
        }

    }

    var static_ztitle = ""

    //根据页面与任务id加载任务主体内容
    //任务表id,任务类型（1为固定任务,2为临时任务），固定任务id
    function loadcontentbypages(taskid, kindid, assid) {
        sendclassmessage(static_zname, taskid, kindid, assid, pages);

        //将传进来的三个值附值给全局变量
        static_kindid = kindid;
        static_taskid = taskid;
        zassign_scheduleid = assid;

        //返回值data类，确定有多个变量用
        var formData = new FormData();
        formData.append("page", pages);
        formData.append("taskid", taskid);

        //任务主体及其拼接类
        var cp_content = $("#cp_content");
        var str = "";
        //页码部分及其拼接类
        var cp_number = $("#cp_number");
        var str2 = "";

        static_ztitle = "";
        //主体任务方法
        $.ajax({
            type: "post",
            url: "/selectcontentbypage",
            contentType: false,
            processData: false,
            data: formData,
            async: false,
            success: function (data) {
                //将取出来的值附值给全局变量
                task_contentid = data.zid;
                static_ztype = data.ztype;

                static_ztitle = data.ztitle;
                //判断主体内容，图片，视频，文字，数据测量各不相同
                //任务有title的情况

                if (data.ztype == "图片") {
                    var zcontent = data.zcontent;
                    var content = zcontent.substr(43);

                    str += "<img src='" + content + "'  alt='测试用' class='cp_message' />";
                } else if (data.ztype == "视频") {
                    var zcontent = data.zcontent;
                    var content = zcontent.substr(43);
                    str += "<video src='" + content + "' controls='controls' class='cp_message'>您的浏览器不支持 video 标签。</video>"
                } else if (data.ztype == "文字") {
                    str += "<font class='cp_message' size='3'>" + data.zcontent + "</font>"
                } else if (data.ztype == "评分表") {
                    var zcontent = data.zcontent;
                    var content = zcontent.split(";");
                    content[0] = content[0].substr(43);
                    var arr = content[1].split(",");
                    // alert(content[0]);


                    str += " <div class='left_table2'><img src='" + content[0] + "'  alt='测试用' class='right_message' /></div>"
                    str += " <div class='reight_mes2'>"
                    str += " <table class='r_table2'>";
                    str += "<tr><th class='r_tableth1'>序号</th><th class='r_tableth2'>尺寸值</th><th class='r_tableth3'>输入尺寸</th></tr>"

                    //加载测量值的个数及id
                    $.ajax({
                        type: "post",
                        url: "/findtaskassessbytrainingid",
                        async: false,
                        data: {"ztraining_taskID": static_taskid},
                        success: function (data) {
                            static_assessnum = data.length;
                            for (var i = 0; i < data.length; i++) {
                                ztrainingtaskassessID[i] = data[i].zid;
                                j = i + 1;
                                str += " <tr><th class='r_tableth1'>" + data[i].zorder + "</th><th class='r_tableth3'><input class='rmes_input' type='tel'  id='" + data[i].zid + "'></th></tr>"
                            }
                        }
                    });

                    str += "</table>";
                    str += "</div>"
                }
            }

        });

        cp_content.html(str);
        //加载任务的页码
        $.ajax({
            type: "post",
            url: "/findendpages",
            contentType: false,
            processData: false,
            data: formData,
            async: false,
            success: function (data2) {
                //将其赋值给全局变量
                last_page = data2;
            }
        });
        //页码前端加载
        if (static_ztitle) {
            str2 = "<font size='5'>" + pages + "页/共" + last_page + "页  (" + static_ztitle + ")</font>"
        } else {
            str2 = "<font size='5'>" + pages + "页/共" + last_page + "页</font>"
        }
        cp_number.html(str2)

        //如果是固定任务就更新任务的日志表
        if (static_kindid == 1) {
            //更新固定任务的各种时间及状态
            $.ajax({
                type: "post",
                url: "/updatetasktime",
                data: {"taskid": taskid, "zassign_scheduleid": zassign_scheduleid, "task_contentid": task_contentid},
                success: function (data) {
                    // alert(data)
                }
            });
        }
    }

    function getfixtask() {
        $.ajax({
            type: "post",
            url: "/findallfixedtasks",
            contentType: false,
            processData: false,
            async: false,
            success: function (data){

                if (data.length != static_fixleng) {
                    static_fixleng = data.length;
                    if (static_ismes == 1)
                        loadcontentbypages2(static_zname, static_ztaskid, static_kindid, static_assid)
                    location.reload();
                }

            }

        });
    }


    function gettemporary() {

        $.ajax({
            type: "post",
            url: "/findalltemporarytask",
            contentType: false,
            processData: false,
            async: false,
            success: function (data) {

                if (data.length != static_temleng) {
                    static_temleng = data.length;
                    if (static_ismes == 1) {

                        loadcontentbypages2(static_zname, static_ztaskid, static_kindid, static_assid)
                        location.reload();
                    }
                }
            }
        });
    }


    //页面加载前方法
    window.onload = function () {


        //getcommand2();
        getclassmesssion();

        loadisevent();

        if (static_ismes != 1)
            welcome();

        $("#lastpage").css("background-color", "#A5A5A5");

        findalltask();
        getcommand();
        //循环查找老师的命令
        window.setInterval(function () {
            findisleave()
            getcommand();
            gettemporary();
            getfixtask();
            loadscreentime();
        }, 3000);
    }

    var static_ismes;
    var static_zname;
    var static_ztaskid;
    var static_kindid;
    var static_assid;

    //获取任务页面的session
    function getclassmesssion() {
        $.ajax({
            type: "post",
            url: "/getclassmesssion",
            data: {},
            async: false,
            success: function (data) {
                if (data) {
                    //alert(11)
                    static_ismes = 1;
                    static_zname = data.zname;
                    static_ztaskid = data.taskid;
                    static_kindid = data.kindid;
                    static_assid = data.assid;
                    pages = data.pages;

                }
            }
        })
    }


    function welcome() {
        var cp_content = $("#cp_content")
        var str = "";
        $.ajax({
            type: "post",
            url: "/getnameandpath",
            data: {},
            success: function (data) {
                // alert(data)
                var content = data.path.substr(35);

                str += " <div><img src='" + content + "' class='c_centerimg'>"
                str += "<font class='c_centerfont' size='5'>欢迎" + data.name + "同学进入实训,请点击左侧实训任务,开始实训。</font></div>"
                cp_content.html(str);
            }
        });
    }

    var static_temleng = 0;
    var static_fixleng = 0;

    //所有任务
    function findalltask() {
        var leftbutton = $("#leftbutton");
        var str = "";

        //固定任务按键
        $.ajax({
            type: "post",
            url: "/findallfixedtasks",
            contentType: false,
            processData: false,
            async: false,
            success: function (data) {
                static_fixleng = data.length;
                str += "<br><br><br>"
                for (var i = 0; i < data.length; i++) {
                    str += "<button onclick='loadcontentbypages2(\"" + data[i].zname + "\",\"" + data[i].zstudent_scheduleid + "\",1,\"" + data[i].zassign_scheduleid + "\")' class='cp_button2' id='" + data[i].zstudent_scheduleid + "'>" + data[i].zname + "</button> <br><br>"
                }
            }
        });
        //临时任务按键
        /*$.ajax({
            type: "post",
            url: "/findalltemporarytask",
            contentType: false,
            processData: false,
            async: false,
            success: function (data){
                static_temleng=data.length
                for(var i=0;i<data.length;i++){
                    str+="<button class='cp_button1' onclick='loadcontentbypages2(\""+data[i].ztitle+"\",\""+data[i].zcontentID+"\",2,\"\")' id='"+data[i].zcontentID+"'>"+data[i].ztitle+"</button> <br><br>"
                }
            }
        });*/
        leftbutton.html(str)

        if (static_ismes == 1) {

            loadcontentbypages2(static_zname, static_ztaskid, static_kindid, static_assid)
        }

    }


    //上一页方法
    function lastpage() {
        //如果是数据测量的表，将插入学生输入的测量数据
        if (static_ztype == "评分表") {

            for (var i = 0; i < static_assessnum; i++) {
                //对每个传过来的字符串去空格
                var removenull = Trim($("#" + ztrainingtaskassessID[i] + "").val());
                //空字符串与空格判断
                /* if(!removenull){
                     alert("你还有未输入的测量数据！")
                     return;
                 }*/
                //长度长于数据库字体长度，直接返回
                if (titleLength(removenull) > 20) {
                    layer.msg("你输入的长度过长！", {icon: 1, offset: "auto", time: 2000});
                    return;
                }
                //长度与空格检查都通过，将值附给自检值
                else {
                    zselfcheck[i] = removenull
                }
            }
            $.ajax({
                type: "post",
                url: "/inserttaskinput",
                async: false,
                data: {
                    "zassign_scheduleid": zassign_scheduleid,
                    "zselfcheck": zselfcheck,
                    "ztrainingtaskassessID": ztrainingtaskassessID
                },
                success: function (data) {
                    if (data > 0)
                        layer.msg("成功提交测量数据", {icon: 1, offset: "auto", time: 1000});
                }
            });
        }

        //固定任务更新content_log时间
        if (static_kindid == 1) {
            $.ajax({
                type: "post",
                url: "/updatetaskendtime",
                data: {"zassign_scheduleid": zassign_scheduleid, "task_contentid": task_contentid},
                success: function (data) {
                    // alert(data)
                }
            });
        }

        //如果此时页面值为2
        if (pages == 2) {
            //上一页按键变灰
            $("#lastpage").css("background-color", "#A5A5A5");
            //移除上一页方法
            $("#lastpage").removeAttr("onclick");

        }
        pages--;
        //上一页后变成最后一页的前一页，按键变蓝，并加载下一页方法
        if (pages == last_page - 1) {
            $("#nextpage").attr("onclick", "nextpage()");
            $("#nextpage").text('下一页');
        }

        //加载上一页
        loadcontentbypages(static_taskid, static_kindid, zassign_scheduleid);
        if (static_ztype == "评分表" && zselfcheck) {
            //alert(1)
            for (var i = 0; i < zselfcheck.length; i++) {
                $("#" + ztrainingtaskassessID[i] + "").val(zselfcheck[i]);
            }

        }
    }


    //下一页方法
    function nextpage() {
        if (static_ztype == "评分表") {
            for (var i = 0; i < static_assessnum; i++) {
                var removenull = Trim($("#" + ztrainingtaskassessID[i] + "").val())
                /* if(!removenull){
                     alert("你还有未输入的测量数据！")
                     return;
                 }else */
                if (titleLength(removenull) > 20) {
                    layer.msg("你输入的长度过长", {icon: 1, offset: "auto", time: 1000});
                    return;
                } else {
                    zselfcheck[i] = removenull
                }
            }

            $.ajax({
                type: "post",
                url: "/inserttaskinput",
                async: false,
                data: {
                    "zassign_scheduleid": zassign_scheduleid,
                    "zselfcheck": zselfcheck,
                    "ztrainingtaskassessID": ztrainingtaskassessID
                },
                success: function (data) {
                    if (data > 0)
                        layer.msg("成功提交测量数据", {icon: 1, offset: "auto", time: 1000});
                }
            });
        }

        //固定任务更新content_log时间
        if (static_kindid == 1) {
            $.ajax({
                type: "post",
                url: "/updatetaskendtime",
                data: {"zassign_scheduleid": zassign_scheduleid, "task_contentid": task_contentid},
                success: function (data) {
                    // alert(data)
                }
            });
        }

        if (pages == last_page - 1) {
            $("#nextpage").text("结束任务")
            $("#nextpage").attr("onclick", "sumbmitpages()");
        }
        pages++;
        //下一页到第二页，上一页按键变蓝，并加载上一页方法
        if (pages == 2) {
            $("#lastpage").css("background-color", "#4472C4");
            $("#lastpage").attr("onclick", "lastpage()");
        }


        loadcontentbypages(static_taskid, static_kindid, zassign_scheduleid);
        if (static_ztype == "评分表" && zselfcheck) {
            //alert(1)
            for (var i = 0; i < zselfcheck.length; i++) {
                $("#" + ztrainingtaskassessID[i] + "").val(zselfcheck[i]);
            }
        }
    }


    function sumbmitpages() {
        //alert("进入")

        if (static_kindid == 1) {
            $.ajax({
                type: "post",
                url: "/updatealltaskend",
                data: {
                    "zassign_scheduleid": zassign_scheduleid,
                    "ztrainingtaskID": static_taskid,
                    "task_contentid": task_contentid
                },
                success: function (data) {
                    if (data > 0) {
                        layer.msg("已提交成功", {icon: 1, offset: "auto", time: 1000});
                        last_page = 0;

                    } else {
                        //alert("出错，请吉时联系老师")
                        layer.msg("出错，请吉时联系老师", {icon: 1, offset: "auto", time: 2000});
                    }
                }
            });
        } else {
            layer.msg("已提交成功", {icon: 1, offset: "auto", time: 1000});
        }
        var cp_content = $("#cp_content")
        var str = "";
        $.ajax({
            type: "post",
            url: "/getnameandpath",
            data: {},
            success: function (data) {
                // alert(data)
                var content = data.path.substr(35);

                str += " <div><img src='" + content + "' class='c_centerimg'>"
                str += "<font class='c_centerfont2' size='5'>" + data.name + "同学,请点击左侧实训任务,开始实训。</font></div>"
                cp_content.html(str);
            }
        });
        $("#nextpage").css("background-color", "#A5A5A5");
    }



</script>

</html>