<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 同屏测试 </title>

    <link href="https://vjs.zencdn.net/7.10.2/video-js.css" rel="stylesheet" />
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/screen_delivery.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/common.js"></script>


    <style type="text/css">

        /*标题样式*/
        p{
            text-align: center;
            font-size: 25px;
            color: cadetblue;
            font-family: fantasy;

        }


        .t_leftmes img{
            width: 100%;
            height: 100%;
            margin: 0 auto;
            padding-top: 30px;

        }

        .img1{
            display: block;
        }

        .img2{
            display: none;
        }

        .img3{
            display: none;
        }
    </style>


</head>
<body>

    <div class="testimage" id="testimage">
        <div class="t_leftmes" id="t_leftmes">
          <#-- <img class="img-slide img1" src="1609748715669.png" alt="1">
            <img class="img-slide img2" src="1609911956032.png" alt="2">
            <img class="img-slide img3" src="1609911958030.png" alt="3">-->
        </div><div class="t_rightmes"></div>
    </div>

</body>



<script>

    var index=0;
    //改变图片
    function ChangeImg() {
        index++;
        var a=document.getElementsByClassName("img-slide");
        if(index>=a.length) index=0;
        for(var i=0;i<a.length;i++){
            a[i].style.display='none';
        }
        a[index].style.display='block';
    }


  window.onload =function () {
      load()
       window.setInterval(function () {
            loadnum()
        }, 3000);

    }



    var static_num=0;
    var static_time=0;

    function loadnum(){
        $.ajax({
            type: "get",
            url: "/findlefcount",
            async: false,
            success: function (data) {
               // alert(data)
              //  alert(static_num)
                if(data != static_num){
                    load()
                }
            }
        })
    }



    function load(){

        var t_leftmes=$("#t_leftmes")
        var str=""
        $.ajax({
            type: "get",
            url: "/findleftimg",
            async: false,
            success: function (data) {
                    static_num=data.length;
                    for (var i = 0; i < data.length; i++) {
                        str += " <img  class='img-slide img"+(i + 1)+"' src='" + data[i].filename + "' alt='" + (i + 1) + "'>"
                        static_time = data[i].timein
                    }
            }

        });
        t_leftmes.html(str)
        setInterval(ChangeImg,static_time*1000)
    }



</script>

</html>