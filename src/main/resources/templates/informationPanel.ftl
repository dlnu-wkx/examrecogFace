<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>信息展示面板</title>
    <link rel="stylesheet" href="./InformationPanel/css/index.css" />
</head>

<body>
<!-- 头部的盒子 -->
<header>
    <h1>实训车间信息展示面板</h1>
    <div class="showTime"></div>
</header>
<!-- 页面主体部分 -->
<section class="mainbox">
    <div class="cloumn">
        <div class="panel year">
            <h2>各个工种本年度实训人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel month">
            <h2>各个工种本月实训人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel week">
            <h2>各个工种今天实训人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
    </div>
    <div class="cloumn">
        <!-- no模块制作 -->
        <div class="no">
            <div class="no-hd ">
                <ul>
                    <li id="yeardata">12356</li>
                    <li id="monthdata">2133</li>
                </ul>
            </div>
            <div class="no-bd no-bd1">
                <ul>
                    <li>本年度实训人数</li>
                    <li>本月实训人数</li>
                </ul>
            </div>
            <div class="no-hd no-hd1">
                <ul>
                    <li id="weekdata">124</li>
                    <li id="todaydata">213</li>
                </ul>
            </div>
            <div class="no-bd">
                <ul>
                    <li>本周实训人数</li>
                    <li>今日实训人数</li>
                </ul>
            </div>
        </div>
        <!-- 地图模块 -->
        <div class="map">

            <div class="map1"></div>
            <div class="map2"></div>
            <div class="map3"></div>
        </div>
    </div>
    <div class="cloumn">
        <div class="panel use-rate">
            <h2>车间车床使用比率
            </h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel test-rate">
            <h2>安全测试通过率</h2>
            <div class="chart">
            </div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel pie">
            <h2>全部机床的累计使用时间</h2>
            <div class="chart">
            </div>
            <div class="panel-footer"></div>
        </div>
    </div>

</section>
<script src="jquery/jquery-3.3.1.min.js"></script>
<script src="InformationPanel/js/flexible.js"></script>
<script src="InformationPanel/js/echarts.min.js"></script>
<script src="InformationPanel/js/index.js"></script>
<script src="InformationPanel/js/showTime.js "></script>
<script type="text/javascript">
    window.onload =function () {


        $.ajax({
            type: "post",
            url: "/yeardata",
            success: function (data) {
                document.getElementById("yeardata").innerHTML = data

            }
        });
        $.ajax({
            type: "post",
            url: "/monthdata",
            success: function (data) {
                document.getElementById("monthdata").innerHTML = data
            }
        });

        $.ajax({
            type: "post",
            url: "/weekdata",
            success: function (data) {
                document.getElementById("weekdata").innerHTML = data
            }
        });
        $.ajax({
            type: "post",
            url: "/todaydata",
            success: function (data) {
                document.getElementById("todaydata").innerHTML = data
            }
        });

        $.ajax({
            type: "post",
            url: "/useRate",
            success: function (data) {
                console.log(data);
            }
        });

        $.ajax({
            type: "post",
            url: "/passRate",
            success: function (data) {
                console.log(data);
            }
        });


    }

</script>
</body>



</html>
