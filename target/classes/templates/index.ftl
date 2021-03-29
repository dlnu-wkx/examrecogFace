<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人脸识别系统</title>
    <link rel="stylesheet" href="layui/css/layui.css">

    <script src="jquery/jquery-3.3.1.min.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="jquery/jquery.cookie.js"></script>
</head>
<body class="layui-layout-body" style="width: 100%;height: 100%;background-color: #CDCDCD">
<div class="layui-layout layui-layout-admin" >
    <div class="layui-header" style="border-bottom: 1px solid #c2c2c2;background-color: #C6C6C6">
        <div class="layui-logo" style="osition: absolute;left: 0;top: 0;width: 200px;height: 100%;line-height: 60px;text-align: center;font-size: 16px;left:14px;letter-spacing:4px;color: #0C0C0C">登录界面</div>
        <ul class="layui-nav layui-layout-right" style="right: 35px">
            <li class="layui-nav-item" style="letter-spacing:4px;left:30px;color: #0C0C0C">安浩智能学习工厂</li>
        </ul>
    </div>
    <div class="layui-row ">
        <div class="layui-col-xs1" align="center" style="width: 17%;font-size: 70px;margin-top: 40px">
            欢迎
        </div>
        <div class="layui-col-xs9" align="center" style="width: 69%">
            <div style="margin: 0,auto;margin-top:40px;height: 80px;text-align:center;line-height:40px;font-size: 40px;color: #E51C23">
            </div>
            <div style=";margin:0 auto;margin-top:0px;height: 100px"><img src='<#--${path}-->'style='width: 15rem;height: 16rem;'></div>
            <div style="margin: 0,auto;margin-top:220px;height: 80px;text-align:center;line-height:80px;font-size:34px;color:#0C0C0C"> 进入安浩智能学习工厂</div>
            <div style="position: absolute">
               <iframe src="http://127.0.0.1/stream" width="450" height="400">
                    <a href="included.html">你的浏览器不支持iframe页面嵌套，请点击这里访问页面内容。</a>
                </iframe>
                <#--<object type="application/x-vlc-plugin" pluginspage="http://www.videolan.org/" id="vlc" events="false" width="95%" height="85%">
                    <param name="mrl" value="rtsp://admin:djtu13840903462@192.168.1.64:554/h264/ch1/main/av_stream" />
                    <param name="controls" value="false" />
                    <param name="fullscreen" value="false" />
                    <param name="loop" value="false" />
                    <param name="autoplay" value="true" />

                </object>-->
            </div>
            <div>
               <button style="color:#FFFFFF;height: 75px;display:block;margin:0 auto;margin-top:0px;width:211px;background-color:#71B863;border-radius:32px;text-align: center;line-height: 50px;font-size: 32px" onclick="test()">
                开始测试
               </button>
            </div>
       </div>
       <div class="layui-col-xs1" align="right" style="right:-3%;left:80px;height:100%;width: 10%;border-left: 1px solid #c2c2c2;">
           <div>
               <button onclick="show()" style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:0px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                   举手
               </button>
           </div>
           <div>
               <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                   请假
               </button>
           </div>
          <#-- <div>
               <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                   实时状态
               </button>
           </div>
           <div>
               <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:40px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 30px;font-size: 27px">
                   信息发布
               </button>
           </div>-->
          <div>
            <button style="color:#FFFFFF;height: 80px;display:block;margin:0 auto;margin-top:300px;width:80px;background-color: #4472c4;border-radius:14px;text-align: center;line-height: 27px;font-size: 27px">
                退出系统
            </button>
          </div>
       </div>
  </div>
</div>
<script>

   /* let vlc = document.getElementById('vlc')
    let ar = '14:9'
    vlc.video.aspectRatio = ar
*/
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    function test() {
        /*ajax不能实现只发送不要数据所以不能用ajax*/
        location.href = "/test";
    }
    function show() {
        alert("进入到show中")
    }
</script>
</body>
</html>