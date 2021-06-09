<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 安全测试题 </title>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>
</head>
<body  class="body" onunload="goodbye()" >

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
    <div class="leftfont"><font size="5" >安全测试题</font></div>
    <div class="rightfont"><font size="5" >安浩智能学习工厂</font></div>
</div>

<!--左侧灰色按键-->
<div class="left">

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

<div class="right">
<!--右侧按键-->
    <div class="right1">
        <button class="button5" onclick="upheads()" id="upheads">举手</button>
    </div>

    <div class="right2">    
        <button class="button5" onclick="showleave()"  id="leave">请假</button>
    </div>

    <div class="right3">
        <button class="button5" onclick="leaveclass()" >退出系统</button>
    </div>
</div>

<div class="t_centerindex" hidden id="t_centerindex">
    <font class="t_centerfont" size="5">你还有未做完的题，请确认是否提交？</font>
    <button onclick="clicksubmit1()" class="t_click1">确认</button>
    <button onclick="removesubmit1()" class="t_remove1">取消</button>
</div>



<!--中间题目主体部分-->
<div class="center" id="qbank" style="background-color: white">


</div>


<br><br>


<!--翻页-->
<div class="pages">
    <button class="button3" id="lastpage" onclick="lastpage()">上一页</button>
    <button class="sbutton" id="submit" onclick="submit()">提交</button>
    <button class="button4" id="nextpage" onclick="nextpage()">下一页</button>
</div>


<div class="pagesnumber" id="pagesnumber">

</div>


</body>

<script>
    //window
    window.onbeforeunload=function(e){
        a();
    }

  //  var static_light=1;
    var static_testtimes2=0;

    function a(){
        static_testtimes2=static_testtimes;

        static_testtimes=0;
        //记录当前状态信息
     //   sendnowstatusmes();

        $.ajax({
            type: "post",
            url: "/findsessionprogress",
            data:{},
          //  async: false,
            success: function (data){
                submit2()
            }
        });

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



   /* window.onunload = function() {
        alert('ddd');
        leaveclass()
    }*/

    function clicksubmit1(){
        $("#t_centerindex").hide()
        submit2()
    }

    function loadpagenumber(i) {
        //先清空里面的
        $("#pagesnumber").empty();
        var str=" <font size='5'>"+i+"/"+static_questionnum+"</font>"
        var pagesnumber=$("#pagesnumber")
        pagesnumber.html(str)
    }


    //分数
    var code=0;

    var static_questionnum=0;
    var static_singlecode=0;
    var static_total=0;

    //学生题解
    var ananswer=new Array();
    //正确题解
    var answer=new Array();

    var answercode=new Array();

    var questionid=new Array()


    var static_passingcode=0;

    //记录页数的i值,页数减1
    var i=0;

    var static_tnumber2=0;

    var static_testtimes=0;

    var shuxingtime=0;



    function gettestmessage(){
        if(static_testtimes2!=0)
            static_testtimes=static_testtimes2;

        static_testtimes++;
       // alert(static_testtimes)
        $.ajax({
            type: "post",
            url: "/updatetesttime",
            async: false,
            data:{"ztesttime":static_testtimes},
            success: function (data){
                //  alert(data)
            }
        });
    }

    function refresh2(){
        $.ajax({
            type:"post",
            url:"/noexitsystem2",
            data:{},
            //    async: false,
            success:function(data){

            }
        })

        //更改学生登陆的状态
        $.ajax({
            type:"post",
            url:"/updatestatusbout2",
            data:{},
            //   async: false,
            success:function(data){

            }
        })

    }



    //页面加载前方法
    window.onload =function () {
        refresh2()

      //  gettestmessage()

        loadisevent()

        //通过的分数
        $.ajax({
            type: "post",
            url: "/findpassingcode",
            async: false,
            success: function (data){
                static_passingcode=data;
                //alert(static_passingcode)
            }
        });


        var pagesnumber=$("#pagesnumber")
        //题目数量
        $.ajax({
            type: "post",
            url: "/findtestnumber",
            async: false,
            success: function (data){
                if(data==9999){
                    static_tnumber2=1
                    $.ajax({
                        type: "post",
                        url: "/findtenumbytype",
                        async: false,
                        success: function (data2){
                            static_questionnum=data2;
                            var str=" <font size='5'>1/"+static_questionnum+"</font>"
                            pagesnumber.html(str)
                            static_singlecode=100/static_questionnum;
                        }
                    })

                }else{
                    static_questionnum=data;
                    var str=" <font size='5'>1/"+static_questionnum+"</font>"
                    pagesnumber.html(str)
                    static_singlecode=100/static_questionnum;
                }
            }
        });


        //总分
        $.ajax({
            type: "post",
            url: "/findtesttotal",
            async: false,
            success: function (data){
                static_total=data;
            }
        });


        load();

        window.setInterval(function () {
            findisleave()
        }, 3000);
    }

    function findisleave(){
        // alert(1)
        $.ajax({
            type: "post",
            url: "/findisleave",
            async: false,
            success: function (data) {
                // alert(data)
                if (data>0) {
                    static_testtimes=0;
                    a()
                    setTimeout(function (){ location.href="/student"},1500);
                }
            }
        })
    }




    var static_testinputnum=new Array();


    /*
       将所有问题都加载，显示一题，隐藏其它
        */
    function load() {
        //上一页按键变灰
        $("#lastpage").css("background-color","#A5A5A5");
        $("#nextpage").show();
        //上一页展现出来


        //下一页恢复原来的样式与方法
        var button4 =  $("#nextpage")
        button4.attr("onclick","nextpage()");
        button4.text('下一页');

        //去除中间显示的样式
        $("#qbank").removeAttr("align");

        var str = "";
        //测试cookie
        //alert($.cookie('name'));


        //连接Servlet
        $.ajax({
            type: "post",
            url: "/findtestbynumber",
            data:{"number":static_questionnum},
            async: false,
            success: function (question) {
                static_testinputnum=question.testinputid;

                //选择题循环取出并加载
               for (var w =0; w<question.cbank.length;w++) {
                    var k=w+1;

                   questionid[w]=question.cbank[w].zid;
                    str+="<div class='qbank"+w+"' id='qbank"+w+"'>";

                    str+="<font size='5' class='title'>安全测试题，"+static_questionnum+"个，共"+static_total+"分，"+static_passingcode+"分及格</font>";
                    str+="<font size='5' class='questiontitle'>选择题("+question.cbank.length+"个)</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.cbank[w].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+w+"'>";
                    str += " <p onclick='checkquestion(\"qbank"+w+"\",\"A"+w+"\")'><input type='checkbox' class='t_choose1' name='message' id='A"+w+"' value='A' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>A.</font><font  size='5' >" + question.cbank[w].zoptionA + "</font></p><br><br><br>";
                    str += " <p onclick='checkquestion(\"qbank"+w+"\",\"B"+w+"\")'><input type='checkbox' class='t_choose2' name='message' id='B"+w+"'    value='B' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>B.</font><font  size='5' >" + question.cbank[w].zoptionB + "</font></p><br><br><br>";
                    str += " <p onclick='checkquestion(\"qbank"+w+"\",\"C"+w+"\")'><input type='checkbox' class='t_choose3' name='message' id='C"+w+"'   value='C'>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>C.</font><font size='5' >" + question.cbank[w].zoptionC + "</font></p><br><br><br>";
                    str += " <p onclick='checkquestion(\"qbank"+w+"\",\"D"+w+"\")'><input type='checkbox' class='t_choose4' name='message' id='D"+w+"'   value='D' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>D.</font><font  size='5' >" + question.cbank[w].zoptionD + "</font></p><br><br><br>";
                    str += "</div>";

                    str+="</div>";
                    str+=""
                }

                //判断题循环取出并加载
                for(var j=0; j<question.jbank.length;j++){
                    var k=j+question.cbank.length+1;
                    var p=j+question.cbank.length;

                    questionid[p]=question.jbank[j].zid;
                    str+="<div class='qbank"+p+"' id='qbank"+p+"'>";

                    str+="<font size='5' class='questiontitle'>判断题("+question.jbank.length+"个)</font>";

                    str += " <div id='question' class='question'><font size='5' >"+k+"." + question.jbank[j].ztitlecontent + "</font></div><br><br><br>";

                    str += " <div class='cbooks' id='cbooks"+p+"'>";
                    str += " <p onclick='checkquestion(\"qbank"+p+"\",\"t"+p+"\")'><input type='checkbox' name='message' id='t"+p+"'  class='t_choose1'  value='对' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>对</font></p><br><br><br>";
                    str += " <p onclick='checkquestion(\"qbank"+p+"\",\"f"+p+"\")'><input type='checkbox' name='message'  id='f"+p+"'  class='t_choose5'  value='错' >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<font size='5'>错</font></p><br><br><br>";

                    str += " </div>";

                    str+="</div>";

                }
                //先清空里面的
                $("#qbank").empty();

                //再将所有html放入
                $("#qbank").html(str);

                for (var c=1;c<question.cbank.length+question.jbank.length;c++) {
                    $("#qbank"+c+"").hide();
                }

                //获取正确答案
                $.ajax({
                    type: "post",
                    url: "/getsafetestanswer",
                    async: false,
                    success: function (data){
                        for (var c=0;c<data.length;c++) {
                            answer[c]=data[c]
                        }
                       // alert(answer)
                    }
                });

                //单选方法加载
                onechose();

            },
            error: function (error) {
               // alert(JSON.stringify(error))
            }
        });
    }

    //点击题目选中选项
   function checkquestion(id2,id){

        //alert(id2)
       //alert($("#"+id+"").val());
       if ($("#"+id+"").prop('checked'))

           $("#"+id+"").prop("checked",false);
       else
           $("#"+id2+" input[type='checkbox']").each(function(){$(this).prop("checked",false);});
           $("#"+id+"").prop("checked",true);
   }



   function submit() {
       //学生题解记录
       ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();
       for (var f=0;f<static_questionnum;f++){
           if (!ananswer[f]){
              // alert(1)
               $("#t_centerindex").show()
               return;
           }
       }

      submit2()
   }

   function removesubmit1(){
       $("#t_centerindex").hide()
   }


    /**
     * 提交方法
     */

    function submit2() {


        /*for (var f=0;f<i+1;f++){
            if (!ananswer[f]){
                alert("你还有未做的题，请仔细检查")
                return ;
            }
        }*/
        //分数信息居中显示
        $("#qbank").attr("align","center");

        $("#lastpage").css("background-color","#A5A5A5");
        $("#nexttpage").css("background-color","#A5A5A5");

        //中间题目获取
        var qbank=$("#qbank");
        var str3="";

        //总分
        if(static_tnumber2==1){
            for (var j=0;j<static_questionnum;j++){

                //正确答案与学生题解对比
                if(ananswer[j]==answer[j]){
                    code+=1;
                    answercode[j]=1;
                }else{
                    answercode[j]=0;
                }
            }
        }else{
            for (var j=0;j<static_questionnum;j++){

                //正确答案与学生题解对比
                if(ananswer[j]==answer[j]){
                    code+=static_singlecode;
                    answercode[j]=static_singlecode;
                }else{
                    answercode[j]=0;
                }
            }
        }

        //隐藏题目，并加载分数信息
        $("#qbank"+i+"").hide();
        str3+="<br><br><br><br><br>"

        if(static_tnumber2==1){

            str3+="<div><font size='3' >安全测试评分</font></div>";
            str3+="<div><font size='3' >题目数量："+static_questionnum+"题</font></div>";
            str3+="<div><font size='3' >每题分数：1分</font></div>";
            str3+="<div><font size='3' >测试总分："+static_questionnum+"分</font></div>";
            str3+="<div><font size='3' >合格分数："+static_passingcode+"分</font></div>";

        }else{

            str3+="<div><font size='3' >安全测试评分</font></div>";
            str3+="<div><font size='3' >题目数量："+static_questionnum+"题</font></div>";
            str3+="<div><font size='3' >每题分数："+static_singlecode+"分</font></div>";
            str3+="<div><font size='3' >测试总分：100分</font></div>";
            str3+="<div><font size='3' >合格分数："+static_passingcode+"分</font></div>";

        }

        str3+="<br><br><br>"

        if(code<static_passingcode){
            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>需要重新答题</font>";
            str3+="<br><br>"
            str3+=" <button class='button7' onclick='reanswer()'>重新答题</button>"
        }else{

            str3+="<font size='3' >您的分数是：</font><font size='7' class='code'>"+code+"分</font> <font size='3'>已经</font><font size='3'color='red'>合格</font>";
            str3+="<br><br>"
            str3+="  <button class='start_button' onclick='begintrain()'>开始实训</button>";

            $.ajax({
                type: "post",
                url: "/updatesixstateaftertest",
                data: {},
                async: false,
                success: function (data) {
                   // alert(data)
                }
            });


        }
        //放入
        qbank.html(str3);
        //提交按键隐藏（防止分数多次叠加）
        $("#nextpage").hide();
        $("#lastpage").hide();
        //安全测试题可以不计分数，而计次数
      $.ajax({
            type: "post",
            url: "/insertscore",
            data: {"zscore":code},
            async: false,
            success: function (data) {
                //alert(data)
            }
        });

     //  更改输入
      $.ajax({
            type: "post",
            url: "/updatetestinput",
            data: {"ananswer":ananswer,"answercode":answercode,"questionid":questionid,"number":static_questionnum,"id":static_testinputnum},
            success: function (data) {

                if(data){
                    layer.msg("提交成功", { icon: 1, offset: "auto", time:2000 });
                }else{
                    //alert("提交出错")
                    layer.msg("提交出错", { icon: 1, offset: "auto", time:2000 });
                }
            }
        });

        code=0;

        for(var c=0;c<static_questionnum;c++)
            ananswer[c]="";

        gettestmessage()

    }

    //用来测试的方法
  /*  function test() {
        alert(1)
    }*/

    //重新答题方法
    function reanswer() {
        //下一页出现
        $("#nextpage").show();
        $("#lastpage").show();

        //颜色改变
        $("#lastpage").css("background-color","#4472C4");
        $("#nextpage").css("background-color","#4472C4");

        //初始化页面值
        i=0;

        //加载题目
        load();

        loadpagenumber(1);

    }

    //下一页
    function nextpage() {
        //alert(i+1)
        //翻页前先存储一下题解
        ananswer[i]=$("#cbooks"+i+"").find(':checkbox:checked').val();


        if(i!=(static_questionnum-1)){

            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i++;
            //加载下一页
            $("#qbank"+i+"").show();
            if(i==1){
                $("#lastpage").css("background-color","#4472C4");
                $("#lastpage").attr("onclick","lastpage()");
            }

            if(i==(static_questionnum-1)){
                var button4 =  $("#nextpage")
                $("#nextpage").css("background-color","#A5A5A5");
                $("#nextpage").removeAttr("onclick");
            }

        }else {
            //alert("已经没有下一页了")
            layer.msg("已经没有下一页了", { icon: 1, offset: "auto", time:2000 });
        }
        //当前页面单选
        onechose();
        loadpagenumber(i+1)
    }

    //上一页
    function lastpage(){
       // alert(i+1)
        //翻页前先存储一下题解
        ananswer[i]= $("#cbooks"+i+"").find(':checkbox:checked').val();
        if(i!=0){
            //清除前一个题
            $("#qbank"+i+"").hide();

            //页面翻页
            i--;

            //加载上一页
            $("#qbank"+i+"").show();
            //当前页面单选
            onechose();
            if(i==0){
                $("#lastpage").css("background-color","#A5A5A5");
                $("#lastpage").removeAttr("onclick");
            }
            if(i==(static_questionnum-2)){
                var button4 =  $("#nextpage")
                button4.attr("onclick","nextpage()");
                button4.css("background-color","#4472C4");
                button4.text('下一页');
            }

        }else{
            //alert("没有上一页了")
            layer.msg("已经没有上一页了", { icon: 1, offset: "auto", time:2000 });
        }
        loadpagenumber(i+1)

    }





    //单选方法
    function onechose(){
        //在当前题解区域查找按键
        $("#cbooks"+i+"").find(":checkbox").each(function(){
            $(this).click(function () {
                if ($(this).is(":checked")) {
                    //将之前确认的选项取消
                    $("#cbooks"+i+"").find(":checkbox").each(function () {
                        $(this).prop("checked", false);
                    });
                    //钩选现在需要确认的选项
                    $(this).prop("checked", true);
                }
            });
        });
    }

function begintrain() {

    location.href = "/class_ppt";
}



</script>

</html>