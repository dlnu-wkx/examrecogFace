<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录管理</title>
    <link href="./layui/css/login_management.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_service.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.8/css/select2.min.css"/>

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.8/js/select2.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<style>
    html,body{
        height: 100%;
        margin: 0;
        padding: 0;}
    #side_nav ul{display:none}
    table, td, th
    {
        border:1px solid green;
    }
    th
    {
        background-color:green;
        color:white;
    }
</style>
<body style="text-align: center;margin: 0 auto">
<div class="logintop">
    <div class="leftfont">登录管理</div>
    <div class="rightfont">安浩智能学习工厂</div>
</div>
<div style="width: 100%;height: 90%;background-color: #CDCDCD">

    <div class="rightlist">
        <!--右侧显示-->
        <div id="centermenu" class="rightfirstson">

                <div id="showtableid" class="rightfirstson_second_second">
                    <table  class="rightfirstson_second_second_table">
                        <tr style="height: 20%;">
                            <th>序号</th>
                            <th>照片</th>
                            <th>设备名称</th>
                            <th>申请时间</th>
                            <th>权限</th>
                            <th>姓名</th>
                            <th>操作</th>
                        </tr>
                    </table>
                </div>

        </div>
    </div>
    <#--右侧按钮-->
    <div class="i_right" align="center">
        <button onclick="fieldManagement()" id="managementid" class="f_field_management">现场管理</button>
        <button  class="f_field_service" onclick="informationService()">信息查询</button>
        <button onclick="timeStatus()" class="f_field_status">实时状态</button>
        <button onclick="informationDelivery()"class="f_field_delivery">信息发布</button>
        <button class="f_field_exit" id="exit" onclick="outpower()">退出系统</button>
    </div>


</div>

<div hidden class="popup" id="popup" align="center">
    <br><br>
    <button class="p_button2" onclick="lockscreen()">锁屏</button><br>

    <button class="p_button2" onclick="overclass()">下课</button>
</div>

</body>
<script>


    //页面加载完成后当前页面的按钮显示橘色
    function aaa(){
        var servicebutton = document.getElementById("managementid");
        servicebutton.style.backgroundColor="#ED7D31"
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
    //信息查询
    function informationService() {
        location.href="/information_service";
    }
    //退出
    function powerController() {
        location.href="/power_controller";
    }

    var layer;
    $(function () {
        layui.use("layer",function () {
            layer =layui.layer;
        });
    })



    function addcourse(){
        var name =document.getElementById("coursename").value;
        var type =document.getElementById("coursetype").value;
        //插入数据库
        $.ajax({
            type:"post",
            url:"/addcourse",
            data:{"name":name,"type":type},
            success:function (data) {
                if(data==1){
                    layer.msg("新增课程成功", { icon: 1, offset: "auto", time:1000 });
                    document.getElementById("masking").style.display="none"
                    document.getElementById("course-pop-up").style.display="none"
                    findcourse();
                }
            }
        })
        //把课程信息显示到页面中
    }
    function showtimeandsite(){
        document.getElementById("showtableid").style.display="none"
        document.getElementById("righttable").style.display="block"
    }

    /*页面加载时自动把专业和班级加载完成*/
    window.onload =function () {
        aaa()
        showtempuser();
    }

    /*根据班级查找所有的学生并且显示在table中*/
    function showtempuser() {
        var str = "";
        $.ajax({
            type:"post",
            url:"/findAllztempuser",
            success:function (data) {
                if(""!=data){
                    $("#showtableid").empty();
                    str+="<table  class='rightfirstson_second_second_table'>";
                    str+="<tr style='height: 20%;'>";
                    str+="<th>序号</th>";
                    str+="<th>照片</th>";
                    str+="<th>设备名称</th>";
                    str+="<th>申请时间</th>";
                    str+="<th>权限</th>";
                    str+="<th>姓名</th>";
                    str+="<th>操作</th>";
                    str+="</tr>";
                    for(var i =0;i<data.length;i++){
                        var date = formatterDate(data[i].zlogintime);
                        str+="<tr>";
                        str+="<td>"+(i+1)+"</td>";
                        if(data[i].originalPictureUrl.match("ztempuser")){
                            str+="<td><img  src='"+data[i].originalPictureUrl.substring(46)+"'style='width: 4rem;height: 4rem;'></td>";
                        }else {
                            str+="<td><img  src='"+data[i].originalPictureUrl.substring(36)+"'style='width: 4rem;height: 4rem;'></td>";
                        }

                        str+="<td id='td"+data[i].zid+"'>设备名称</td>";
                        str+="<td>"+date+"</td>";
                        if("教师"==data[i].ztype){
                            str+="<td >";
                            str+="请选择权限<select class='rightfirstson_second_first_select' name='selectValue'  id='"+data[i].zid+"'>";
                            str+="</td>";
                            findallauthority(data[i].zid);
                        }else{
                            str+="<td></td>";
                        }
                        str+="<td><input id='input"+data[i].zid+"' type='text'  name='name'value='"+data[i].zname+"'/></td>";
                        str+="<td><button onclick='tempuseragree(\""+data[i].zid+"\")'>同意</button><button onclick='cancle(\""+data[i].zid+"\")'>删除</button></td>";
                        str+="</tr>";
                    }
                    str+="</table>";
                    //加载所有的select选项

                    $("#showtableid").append(str);
                }

            }
        })
    }

    //对TimeStamp时间格式进行处理
    function formatterDate(date){
        //把Timestamp转换成data
        var date1 = new Date(date);
        var date2 =date1.Format("yyyy-MM-dd hh:mm:ss")
        return date2;
    }
    //把Timestamp格式化成为想要的形式
    Date.prototype.Format = function (fmt) { //
        var o = {
            "M+": this.getMonth() + 1, //Month
            "d+": this.getDate(), //Day
            "h+": this.getHours(), //Hour
            "m+": this.getMinutes(), //Minute
            "s+": this.getSeconds(), //Second
            "q+": Math.floor((this.getMonth() + 3) / 3), //Season
            "S": this.getMilliseconds() //millesecond
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() +

            "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1,

                (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };




//加载权限
function findallauthority(zid) {
    var str="";
    $.ajax({
        type:"post",
        url:"/findallauthority",
        success:function (data) {
            if(""!=data){
                $("#"+zid).empty();
                for(var i =0;i<data.length;i++){
                    str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>";
                }
                $("#"+zid).append(str)

            }

        }
    })

}
    /*$(document).ready(function(){
            $("button").click(function (){
                alert(11111)
                console.log(111111)
                 var name = $(this).parent().prev().children().val();
                console.log(name)
                });

    });*/

    function cancle(id) {
        var td =document.getElementById("td"+id).parentNode;
        td.style.display = "none";
    }

//同意
    function tempuseragree(id) {
        //获取选择器
        var selectValue = document.getElementById(id);
        var authorityID = "";
        if(null!= selectValue){
            var index = selectValue.selectedIndex; //获取选项中的索引，selectIndex表示的是当前所选中的index
            authorityID  = selectValue.options[index].value;//获取选项中options的value值
        }

        var name = document.getElementById("input"+id).value;

        if(""==name){
            layer.msg("请输入人员的姓名", { icon: 2, offset: "auto", time:1000 });
            return false;
        }
        $.ajax({
            type:"post",
            url:"/addtempuser",
            data:{"zid":id,"authorityID":authorityID,"zname":name},
            success:function (data) {
            if("1"==data){
                layer.msg("添加成功", { icon: 1, offset: "auto", time:1000 });
            }
            }
        })


    }
</script>
</html>