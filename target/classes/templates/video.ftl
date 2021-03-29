<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Video.js | HTML5 Video Player</title>
    <link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/5.20.1/videojs-ie8.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery-3.3.1.min.js "></script>
    <script src="http://vjs.zencdn.net/5.20.1/video.js"></script>
</head>
<body width="640px" height="360px">

<div id="id" style="margin-top: 100px; margin-left: 70px">
    <h1>测试的摄像头</h1>

    <#--<video id="video" class="video-js vjs-default-skin" controls preload="auto" muted width="352px" height="198px"  data-setup="{}" style="float: left">
        <source src="" type="rtmp/flv">
        <p class="vjs-no-js">To view this video please enable JavaScript,
            and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
        </p>
    </video>-->
</div>
<!-- <video id="example_video_2" class="video-js vjs-default-skin" controls
        preload="auto" width="352px" height="198px" data-setup="{}"
        style="float: left">
     <source src="rtmp://127.0.0.1:1935/live/stream34" type="rtmp/flv">
     <p class="vjs-no-js">
         To view this video please enable JavaScript, and consider upgrading
         to a web browser that <a
             href="http://videojs.com/html5-video-support/" target="_blank">supports
         HTML5 video</a>
     </p>
 </video>

 <video id="example_video_3" class="video-js vjs-default-skin" controls
        preload="auto" width="352px" height="198px" data-setup="{}"
        style="float: left">
     <source src="rtmp://127.0.0.1:1935/live/stream35" type="rtmp/flv">
     <p class="vjs-no-js">
         To view this video please enable JavaScript, and consider upgrading
         to a web browser that <a
             href="http://videojs.com/html5-video-support/" target="_blank">supports
         HTML5 video</a>
     </p>
 </video>

 <video id="example_video_4" class="video-js vjs-default-skin" controls
        preload="auto" width="352px" height="198px" data-setup="{}"
        style="float: left">
     <source src="rtmp://127.0.0.1:1935/live/stream36" type="rtmp/flv">
     <p class="vjs-no-js">
         To view this video please enable JavaScript, and consider upgrading
         to a web browser that <a
             href="http://videojs.com/html5-video-support/" target="_blank">supports
         HTML5 video</a>
     </p>
 </video>

 <video id="example_video_5" class="video-js vjs-default-skin" controls
        preload="auto" width="352px" height="198px" data-setup="{}"
        style="float: left">
     <source src="rtmp://127.0.0.1:1935/live/stream37" type="rtmp/flv">
     <p class="vjs-no-js">
         To view this video please enable JavaScript, and consider upgrading
         to a web browser that <a
             href="http://videojs.com/html5-video-support/" target="_blank">supports
         HTML5 video</a>
     </p>
 </video>-->


<!--<div style="margin-left: 70px">
    <h1 style="clear: left; padding-top: 100px">ÀúÊ·»Ø·Å</h1>
    <video id="example_video_6" class="video-js vjs-default-skin" controls
           preload="auto" width="352px" height="198px" data-setup="{}"
           style="float: left">
        <source src="rtmp://127.0.0.1:1935/history/stream33" type="rtmp/flv">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading
            to a web browser that <a
                href="http://videojs.com/html5-video-support/" target="_blank">supports
            HTML5 video</a>
        </p>
    </video>

    <video id="example_video_7" class="video-js vjs-default-skin" controls
           preload="auto" width="352px" height="198px" data-setup="{}"
           style="float: left">
        <source src="rtmp://127.0.0.1:1935/history/stream34" type="rtmp/flv">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading
            to a web browser that <a
                href="http://videojs.com/html5-video-support/" target="_blank">supports
            HTML5 video</a>
        </p>
    </video>

    <video id="example_video_8" class="video-js vjs-default-skin" controls
           preload="auto" width="352px" height="198px" data-setup="{}"
           style="float: left">
        <source src="rtmp://127.0.0.1:1935/history/stream35" type="rtmp/flv">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading
            to a web browser that <a
                href="http://videojs.com/html5-video-support/" target="_blank">supports
            HTML5 video</a>
        </p>
    </video>

    <video id="example_video_9" class="video-js vjs-default-skin"
           controls="false" preload="auto" width="352px" height="198px"
           data-setup="{}" style="float: left">
        <source src="rtmp://127.0.0.1:1935/history/stream36" type="rtmp/flv">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading
            to a web browser that <a
                href="http://videojs.com/html5-video-support/" target="_blank">supports
            HTML5 video</a>
        </p>
    </video>

    <video id="example_video10" class="video-js vjs-default-skin" controls
           preload="auto" width="352px" height="198px" data-setup="{}"
           style="float: left">
        <source src="rtmp://127.0.0.1:1935/history/stream36" type="rtmp/flv">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading
            to a web browser that
            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
        </p>
    </video>
</div>-->

<script >

       window.onload=function (ev) {
           var str = "";
           var info ={username:"admin",password:"djtu13840903462",channel:"1",ip:"192.168.1.64",stream:"main"}
           $.ajax({
               type: 'post',
               url: '/cameras',
               contentType:'application/json',
               data:  JSON.stringify(info) ,
               dataType:'json',
               async: false,
               success:function (data) {
                   if(data.code==0){
                       var src=data.url;
                       var sr ="rtmp://58.200.131.2:1935/livetv/hunantv"
                       str+= "<video id='video' class='video-js vjs-default-skin' controls='controls' preload='auto'  width='352px' height='198px'  data-setup='{}' style='float: left'>";
                       str+="<source src='"+src+"' type='rtmp/flv'>";
                       str+="<p class='vjs-no-js'>To view this video please enable JavaScript,";
                       str+="and consider upgrading to a web browser that <a href='http://videojs.com/html5-video-support/' target='_blank'>supports HTML5 video</a>";
                       str+="</p></video>";
                       $("#id").append(str);

                      /* let video = document.getElementById("video");
                      video.play();*/
                       // document.getElementById("example_video_1").play();
                       //$("#video").get(0).play()
                   }

               }
           })

       }


</script>



</body>
</html>