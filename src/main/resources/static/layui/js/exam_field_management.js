let time1;
var layer;
$(function () {
    layui.use("layer",function () {
        layer =layui.layer;
    });
})
function startcollect() {

    //如果没有选择班级给与提示
    if("" == document.getElementById("gradename").innerHTML){
        layer.msg("请选择班级", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
    //每次点击开始的时候数据刷新
    $("#mainBody").empty()
    $("#mainBody").append(backup)
    document.getElementById("currentnumber").innerHTML = 0;
    $("#startID").css('background-color', 'rgba(237,125,49)')
    $("#endID").css('background-color', 'rgba(0,0,255)')
    //打开杰视的远程socket,建立连接websocket的端口
    handlewebsocket("开始");
    var zid = document.getElementById("gradeID").innerText;

    var myDate = new Date();
    var mytime = myDate.getTime();
    time1 = setInterval(function () {
        showRecognitionFaceexam(mytime, "签到", zid)
        findAllLoginpeopleexam(mytime,"人脸识别")
    }, 3000)

}
//连接websocket获取数据
function handlewebsocket(a) {
    var formData = new FormData();
    formData.append("startorend",a);
    $.ajax({
        type:"post",
        url:"/handlewebsocket",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success:function (data) {
        }
    })
}
//从数据库中显示已经检测到的人脸信息
//从数据中找到签到的学生
function showRecognitionFaceexam(mytime,zcheck,zid) {
    var cameraname = document.getElementById("rollcallcamera").innerHTML;
    var formData = new FormData();
    formData.append("zcheck",zcheck);
    formData.append("mytime",mytime );
    formData.append("zid",zid)
    formData.append("cameraname",cameraname)
        $.ajax({
            type:"post",
            url:"/signInInspectSitStudent",
            data:formData,
            contentType: false,
            processData: false,
            async: false,
            success:function (data) {
                console.log(data);
                if(data!=null){
                    if(data.length>0){//不大于三个则显示现有的个数
                        for(var i = 0;i<data.length;i++){
                             if(document.getElementById(""+data[i].zstudentID).innerHTML !=null){
                                document.getElementById(""+data[i].zstudentID).style.border = "1px solid red"
                                document.getElementById(""+data[i].zstudentID).style.color = "red"
                            }
                        }
                        document.getElementById("currentnumber").innerHTML = data.length
                    }
                }else {
                    var center=$("#mainBody");
                    center.empty();
                }
            }
        })

}
//查找所有的人包括学生和教师
function findAllLoginpeopleexam(mytime,zcheck) {
    var cameraname = document.getElementById("rollcallcamera").innerHTML;
    var formData = new FormData();
    formData.append("zcheck",zcheck)
    formData.append("mytime",mytime );
    formData.append("cameraname",cameraname)
    $.ajax({
        type:"post",
        url:"/InspectSitStudentandTeacher",
        data:formData,
        contentType: false,
        processData: false,
        async: false,
        success:function (data) {
            if(""!=data){
                $("#identifyAreas").empty();
                if(zcheck=="人脸识别"){
                    //显示最后一个识别成功的人的图片
                    if(""!=data[0].originalPictureUrl){
                        var str =data[0].originalPictureUrl;
                        var path =str.substring(23);
                        /*var ip =getIPAdress()
                        console.log(ip)*/
                        document.getElementById("img").src = "http://localhost:81"+path;
                        /*document.getElementById("img").src = str;*/
                    }
                }
                if(zcheck=="查岗"){
                    if(""!=data[0].originalPictureUrl){
                        var str =data[0].originalPictureUrl;
                        var path = "";
                        if(str.match("D:")){
                            if(str.match("ztempuser")){
                                path =str.substring(46);
                            }else {
                                path =str.substring(36);
                            }
                        }
                        if(str.match("http:")){
                            path =str.substring(23);
                        }
                        document.getElementById("img").src=""+path;
                    }
                }

                //只显示最后五个数据
                if(data.length>5){
                    for(var i=0;i<5;i++){
                        if(data[i].zgradeName=="陌生人"){
                            content =" <div style='font-size: 30px;width: 80%;margin-top: 15px'>"+data[i].zname+"</div>";
                        }else{
                            content =" <div style='font-size: 30px;width: 80%;margin-top: 15px'>"+data[i].zname+"&emsp;"+data[i].zgradeName+"</div>";
                        }
                        $("#identifyAreas").append(content);
                    }
                }else {
                    for(var i=0;i<data.length;i++){
                        if(data[i].zgradeName=="陌生人"){
                            content =" <div style='font-size: 30px;width: 80%;margin-top: 15px'>"+data[i].zname+"</div>";
                        }else{
                            content =" <div style='font-size: 30px;width: 80%;margin-top: 15px'>"+data[i].zname+"&emsp;"+data[i].zgradeName+"</div>";
                        }
                        $("#identifyAreas").append(content);
                    }
                }

            }else{//如果在识别时间段内一个都没识别成功则为空
                $("#identifyAreas").empty();
            }
        }

    })

}
function endcollect(){
    //如果没有选择班级给与提示
    if("" == document.getElementById("gradename").innerHTML){
        layer.msg("请选择班级", { icon: 1, offset: "auto", time:1000 });
        return false;
    }
        $("#endID").css('background-color','rgba(237,125,49)')
        $("#startID").css('background-color','rgba(0,0,255)')
        //结束连接杰视数据接口
        handlewebsocket("结束")
        window.clearInterval(time1);
}
