<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>数控车实训信息展板</title>
    <link rel="stylesheet" href="./InformationPanel/css/index.css"/>
</head>

<body>
<!-- 头部的盒子 -->
<header>
    <ul>
        <li>
            <a href="/field_management">Exit</a>
        </li>
    </ul>
    <h1>数控车实训信息展板</h1>
    <div class="showTime"></div>
</header>
<!-- 页面主体部分 -->
<section class="mainbox">
    <div class="cloumn">
        <div class="panel year">
            <h2>今年计划/实际完成人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel month">
            <h2>本月计划/实际完成人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
        <div class="panel week">
            <h2>今日计划/实际完成人数</h2>
            <div class="chart"></div>
            <div class="panel-footer"></div>
        </div>
    </div>
    <div class="cloumn">
        <!-- no模块制作 -->
        <div class="no">
            <div class="no-hd ">
                <ul>
                    <li id="yeardata">--</li>
                    <li id="monthdata">--</li>
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
                    <li id="weekdata">--</li>
                    <li id="todaydata">--</li>
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
            <div class="map_panel">
                <div class="info">
                    <ul>
                        <li id="className">当前课程：xxxx班</li>
                        <li id="totalNumber">应到人数：xx</li>
                        <li id="arrivedNumber">实到人数：xx</li>
                    </ul>
                </div>
                <div class="chart">
                </div>
                <div class="title">
                    学生签到率
                </div>
                <div class="not_sign">
                    <h4>未签到学生:</h4>
                    <div class="not_sign_name">
                        <div class="text_box">
                            <p id="notArrivedStudents"> </p>
                        </div>
                    </div>
                </div>
            </div>
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
        <div class="panel use-time">
            <h2>机床的平均使用时间</h2>
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
</body>
</html>
