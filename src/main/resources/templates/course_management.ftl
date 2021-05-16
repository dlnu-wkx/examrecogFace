<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>课程管理</title>
    <link href="./layui/css/course_management.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.8/css/select2.min.css"/>
    <link href="./layui/css/right_public_bar.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="./bootstrap-3.3.7-dist/css/bootstrap-select.min.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/top_bar.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="./layui/js/common.js "></script>
    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="/bootstrap-3.3.7-dist/js/bootstrap-select.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
    <script src="My97DatePicker/WdatePicker.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.8/js/select2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="./layui/js/exit.js "></script>
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
<div class="top1">
    <div  class="left-bar"><span id="trainroomname"></span>/课程管理</div>
    <div class="mid-bar">安浩智能学习工厂</div>
    <div class="right-bar" id="m_rightfont"></div>
</div>
<div style="width: 100%;height: 90%;background-color: #CDCDCD">
    <div  class="lefelist">
        <div id="menu" >
    <#--左侧班级显示栏-->
            <ul id="side_nav">
            </ul>
            <script type="text/javascript">

        var layer;
        $(function () {
            layui.use("layer",function () {
                layer =layui.layer;
            });
        })

        function showMenuandtable(){
            /* 显示左侧菜单 */
            var menu =document.getElementById("menu")
            menu.style.display="block"
            /* 隐藏实训室等菜单 */
            var righttable =document.getElementById("righttable")
            righttable.style.display="none"
            /* 展示table菜单 */
            var table =document.getElementById("showtableid")
            table.style.display="block"
        }

        /*多选*/
        function allchose(){
            if($("input[name='checkall']").is(':checked')){
                $("[type='checkbox']:checkbox").prop("checked", true);
            }
            else{
                $("[type='checkbox']:checkbox").prop('checked', false);
            }
        }
        //显示增加课程的界面
        function newaddcourse(){
            document.getElementById("masking").style.display="block"
            document.getElementById("course-pop-up").style.display="block"

        }
        function findcourse() {
            var str = "";
            $.ajax({
                type:"post",
                url:"/findAllcourse",
                success:function (data) {
                    if(""!=data){
                        $("#selectcourse").empty();
                        str+="<div>添加课程</div>";
                        str+="<select class='rightfirstson_second_first_select' name='selectValue' onchange='selectStrcourse(this.value)' id='selectName'>";
                        str+="<option>请选择</option>";
                        for(var i=0;i<data.length;i++){
                            str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>";
                        }
                        str+=" </select>";
                        $("#selectcourse").append(str);
                    }
                }
            })
        }
        function addcourse(){
            var name =document.getElementById("coursename").value;
            var type =document.getElementById("coursetype").value;
            //插入进数据库
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
            showmajor();
            findcourse();
            showtrainingroom();
            showAllteacher();
        }
        function showmajor() {
            var str="";
            $.ajax({
                type:"post",
                url:"/findallmajor",
                success:function (data) {
                    if(data!=""){
                    $("#side_nav").empty();
                        for(var i=0;i<data.length;i++){
                            str+="<li id='li'><span id='"+data[i].zid+"' onclick='findALLgrade(this)'>"+data[i].zname+"</span>";
                        }
                        $("#side_nav").append(str)
                    }

                }
            })
        }

        function findALLgrade(e) {
            var str="";
            var zid =e.id;
            $.ajax({
                type:"post",
                url:"/findAllgradebyzmajorid",
                data:  {"zid": zid} ,
                success:function (data) {
                if(""!=data){
                    /*删除li 中 ul*/
                    $("li ul").remove();
                    for(var i=0;i<data.length;i++){
                        str+="<ul id='ul'>";
                        str+="<li><a  onclick='showstudent(\""+data[i].zid+"\")'>"+data[i].zname+"</a></li>";
                        str+="</ul>";
                    }
                    /*追加在span的后面*/
                    $("#"+zid).after($(str));
                    var navWrap=document.getElementById("side_nav");
                    var nav2s=navWrap.getElementsByTagName("ul");
                    for(var j=0;j<nav2s.length;j++){
                        nav2s[j].style.display="block";
                    }
                }
                }
            })
        }
        /*根据班级查找所有的学生并且显示在table中*/
        function showstudent(zid) {
            /* 展示table菜单 */
            var table =document.getElementById("showtableid")
            table.style.display="block"
            var str = "";
            $.ajax({
                type:"post",
                url:"/findAllstudentbygradeid",
                data:{"zid": zid},
                success:function (data) {
                    if(""!=data){
                        $("#showtableid").empty();
                        str+="<table  class='rightfirstson_second_second_table'>";
                        str+="<tr style='height: 20%;' align='center'>";
                        str+="<th>多选<input  type='checkbox'  name='checkall' onclick='allchose()'/></th>";
                        str+="<th>序号</th>";
                        str+="<th>学号</th>";
                        str+="<th>姓名</th>";
                        str+="<th>班级</th>";
                        str+="</tr>";
                        for(var i =0;i<data.length;i++){
                            str+="<tr>";
                            str+="<td><input type='checkbox' value='"+data[i].zid+"' name='check'/></td>";
                            str+="<td>"+(i+1)+"</td>";
                            str+="<td>"+data[i].zidentity+"</td>";
                            str+="<td>"+data[i].zname+"</td>";
                            str+="<td>"+data[i].zgradeID+"</td>";
                            str+="</tr>";
                        }
                        str+="</table>";
                        $("#showtableid").append(str);
                    }

                }
            })
        }
        //显示所有的实训室
        function showtrainingroom() {
            var str = "";
            $.ajax({
                type:"post",
                url:"/findalltrainroom",
                success:function (data) {
                    if(""!=data){
                        $("#trainingroom").empty();
                        str+="<div>实训室</div>";
                        str+="<select class='rightfirstson_second_first_select' name='selectValue' onchange='selectStr()' id='trainingroomselect'>";
                        str+="<option>请选择</option>";
                        for(var i =0;i<data.length;i++){
                            str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>";
                        }
                        str+="</select>";
                        $("#trainingroom").append(str)
                    }
                }
            })
        }
        //显示所有的实训任务
        function showtrainingtask(id) {
            var str = "";
            $.ajax({
                type:"post",
                url:"/findalltraingtaskbyzcourseID",
                data:{"zid":id},
                success:function (data) {
                    if(""!=data){
                        $("#trainingtaskselect").empty();
                        for(var i =0;i<data.length;i++){
                            str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>";
                        }
                        $("#trainingtaskselect").append(str)
                        $("#trainingtaskselect").selectpicker('refresh');
                        $("#trainingtaskselect").selectpicker('render');
                    }
                }
            })
        }
        function showAllteacher() {

            var str = "";
            $.ajax({
                type:"post",
                url:"/findallteacher",
                success:function (data) {
                    if(""!=data){
                        $("#scheduleteacherselect").empty();
                        for(var i =0;i<data.length;i++){
                            str+="<option value='"+data[i].zid+"'>"+data[i].zname+"</option>";
                        }
                        $("#scheduleteacherselect").append(str)
                        $("#scheduleteacherselect").selectpicker('refresh');
                        $("#scheduleteacherselect").selectpicker('render');
                    }
                }
            })

        }
function savecourse() {
    //获取实训室的ID
    var trainingroom =document.getElementById("trainingroomselect")
    var index = trainingroom.selectedIndex; //获取选项中的索引，selectIndex表示的是当前所选中的index
    var trainingroomID  = trainingroom.options[index].value;//获取选项中options的value值

    if("请选择"==trainingroomID){
        layer.msg("请选择实训室", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //获取开始时间
    var zstartdate = document.getElementById("startDate").value;

    if(""==zstartdate){
        layer.msg("请选择开始时间", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //获取结束时间
    var zenddate = document.getElementById("endDate").value;
    if(""==zenddate){
        layer.msg("请选择结束时间", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //单个值选项的获取实训任务的ID方式
    /*var trainingtask =document.getElementById("trainingtaskselect")
    var index = trainingtask.selectedIndex; //获取选项中的索引，selectIndex表示的是当前所选中的index
    var trainingtaskID  = trainingtask.options[index].value;//获取选项中options的value值*/
    //当是多选时的获取id的方式
    var trainingtaskID = [];
    trainingtaskID = $("#trainingtaskselect").val();

    if(0==trainingtaskID.length){
        layer.msg("请选择学生任务", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //获取课程的id
    var selectName =document.getElementById("selectName");
    var index = selectName.selectedIndex; //获取选项中的索引，selectIndex表示的是当前所选中的index
    var courseID  = selectName.options[index].value;//获取选项中options的value值
    if("请选择"==courseID){
        layer.msg("请选择课程", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    var startchose =[];
    $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
        startchose[i] =$(this).val();
    });
    if(""==startchose){
        layer.msg("请选择学生名单", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //取出多选任务的value值
    var scheduleteacherid = [];
    scheduleteacherid = $("#scheduleteacherselect").val();
    if(0==scheduleteacherid.length){
        layer.msg("请选任课教师", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //保存到zschedule表中并且为每个学生添加学生上课表
    $.ajax({
        type:"post",
        url:"/addzscheduleAndzstudentscheduleAndassignschedule",
        data:{"zid":startchose,"trainingroomID":trainingroomID,"zstartdate":zstartdate,"zenddate":zenddate,"courseID":courseID,"trainingtaskID":trainingtaskID,"scheduleteacherid":scheduleteacherid},
        success:function (data) {
        if(data=="success"){
            layer.msg("新增课程表成功", { icon: 1, offset: "auto", time:1000 });

        }
        if(""==data || "success"!=data){
            layer.msg("上课表已经存在", { icon: 1, offset: "auto", time:1000 });
        }
        }
    })
}

        function selectStrcourse(id){
            showtrainingtask(id)
        }
        function selectStr2(){

        }

        function selectStr1() {
            var color_value = $("#trainingroomselect").val();
            console.log(color_value);
        }
        function selectStr(){

        }
    </script>
        </div>
    </div>
    <div class="rightlist">
    <!--右侧显示-->
        <div class="rightfirstson">
            <#--<div class="rightfirstson_first">
            <!--人员&ndash;&gt;
                <div class="rightfirstson_first_first">
                    <button class="rightfirstson_first_first_button" onclick="showMenuandtable()">人员</button>
                </div>
            </div>-->
         <div id="centermenu" class="rightfirstson_second">

            <div id="showtableid" class="rightfirstson_second_second" align="center">
                <table  class="rightfirstson_second_second_table">
                    <tr style="height: 20%;text-align: center">
                        <th>多选<input  type="checkbox"  name='checkall' onclick='allchose()'/></button></th>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>班级</th>
                    </tr>

                </table>
            </div>
         </div>
         <div id="righttable" class="rightfirstson_second_first">
                <div id="trainingroom">
                    <div>实训室</div>
                    <select class="rightfirstson_second_first_select" name="selectValue" onchange="selectStr1()" id="trainingroomselect">
                        <option>请选择</option>
                        <option value="1">首页</option>
                        <option value="2">其它</option>
                    </select>
                </div>
                <div>开始时间</div>
                <input type="text" class="Wdate form-controls" style="width: 68%;height: 30px"
                       onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})" readonly="readonly" id="startDate">
                <div>结束时间</div><input type="text" class="Wdate form-controls" style="width: 68%;height: 30px"
                                      onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})" readonly="readonly" id="endDate">

                <div id="scheduleteacher">
                    <div>上课教师</div>
                    <select class="selectpicker" name="selectValue" multiple="true" onchange="selectStr2()" id="scheduleteacherselect">
                        <option>请选择</option>
                        <option value="1">首页</option>
                        <option value="2">其它</option>
                    </select>
                </div>
             <div  id="selectcourse">
                 <div>添加课程</div>
                 <select class="rightfirstson_second_first_select" name="selectValue" onchange="selectStr()" id="selectName">
                     <option>请选择</option>
                     <option value="1">首页</option>
                     <option value="2">其它</option>
                 </select>
             </div>
             <div id="trainingtask">
                 <div>实训任务</div>
                 <select class="selectpicker" name="selectValue" multiple="true" onchange="selectStr()" id="trainingtaskselect">
                     <option>请选择</option>
                     <option value="1">首页</option>
                     <option value="2">其它</option>
                 </select>
             </div>
             <br>
             <div >
                 <button class="rightsecondson_first_button1" onclick="newaddcourse()">新建课程</button>
             </div>

          </div>


        </div>
    <!--保存-->
        <div class="rightsecondson">
            <button class="rightfirstson_second_first_button" onclick="savecourse()">保存</button>
        </div>
    </div>
<#--右侧按钮-->
<div class="right_parent1" align="center">
    <button onclick="fieldManagement()" id="managementid" class="f_field_management">现场管理</button>
    <button  class="f_field_service" onclick="informationService()">信息查询</button>
    <button onclick="timeStatus()" class="f_field_status">实时状态</button>
    <button  class="f_field_delivery">信息发布</button>
    <button class="f_field_exit" id="exit" onclick="outpower()">退出系统</button>
</div>


<!-- 增加蒙版 -->
<div id="masking" class="masking"></div>
<!-- 增加课程的弹框信息 -->
<div id="course-pop-up" class="pop-up">
    <div class="pop-up_div">
        <th>课程名字：</th><input id="coursename" class="pop-up_div_input" value="请输入" />
    </div>
    <div class="pop-up_div">
        <th>课程类型：</th><input id="coursetype" class="pop-up_div_input" value="请输入" />
    </div>
    <div class="pop-up_div">
        <button class="pop-up_div_button" onclick="addcourse()">确认</button>
        <button class="pop-up_div_button" onclick="course_cancel()">取消</button>
    </div>
</div>
</div>
</body>
<script>
    //取消蒙版效果
    function course_cancel(){
        document.getElementById("masking").style.display="none";
        document.getElementById("course-pop-up").style.display="none";
    }

    //页面加载完成后当前页面的按钮显示橘色
    window.onLoad=aaa();
    function aaa(){
        var servicebutton = document.getElementById("managementid");
        servicebutton.style.backgroundColor="#ED7D31"
        loadteachername();
        gettrainroom();
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
</script>
</html>
