<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 同屏测试 </title>

    <link href="https://vjs.zencdn.net/7.10.2/video-js.css" rel="stylesheet" />
    <script src="https://vjs.zencdn.net/7.10.2/video.min.js"></script>
    <link href="./layui/css/demo.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/power_controller.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/information_delivery.css" rel="stylesheet" type="text/css">
    <link href="./layui/css/screen_delivery.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="./jquery/jquery.cookie.js"></script>
    <script src="./layui/layui.js"></script>
    <script src="./layui/js/common.js"></script>

</head>
<body>

    <div class="testimage" id="testimage">
        <img src="" class="allimage" id="allimgage">
    </div>

</body>



<script>
    window.onload =function () {
        window.setInterval(function () {//每隔几秒查询对比一次结果，循环对比
            load()
        }, 1000);

    }

    function load() {
        $.ajax({
            type: "get",
            url: "/testscreen",
            async: false,
            success: function (data) {
                $("#allimgage").attr('src',data);
            }
        })
    }

</script>

</html>