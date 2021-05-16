
//退出
function outpower(){
    $("#popup").show()
}

function removescreer(){
    //解锁时进行人脸识别
    getMedia1();

}


var mediaStreamTrack;
var time=null;
function getMedia1() {
    $("#showVdieo").empty();
    let videoComp = "<video muted id='video' width='400px' height='400px' autoplay='autoplay'></video><canvas id='canvas' width='400px' height='400px' style='display: none'></canvas>";
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
        chooseFileChangeCompF_M()
    }, 5000);

}

function chooseFileChangeCompF_M() {

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
//锁屏
function lockscreen() {
    $("#parent").show()
    $("#popup").hide();
    $("#exit").css('background-color','#FFC000');
    $("#exit").text('解锁');
    $("#exit").attr("onclick","removescreer();");
}
//下课
function overclass(){
    $.ajax({
        type: "post",
        url: "/overclass2",
        success: function (data){
            layer.msg("已下课，等待学生端电源关闭", { icon: 1, offset: "auto", time:1000 });
        }
    });

}
