//各个工种本年度实训人数
(function () {
    //实例化对象
    var myChart = echarts.init(document.querySelector(".year .chart"));
    //指定配置项和数据
    var option = {
        tooltip: {
            color: ["blue"],
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'line' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            //修改图表的大小
            left: '0%',
            right: '10px',
            bottom: '4%',
            top: '10px',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ["计划人数","实际完成人数"],
            axisTick: {
                alignWithLabel: true
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
            },
            axisLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            // 设置y轴的刻度最大为数据中最大值的1.13倍
            max: function (value) {
                return parseInt(value.max * 1.13);
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12",
                // formatter: '{value}人'
            },
            //y轴的线条的样式
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 2
                }
            },
            //更改y轴分割线
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1
                }
            }
        }],
        series: [{
            name: '实训人数',
            type: 'bar',
            barWidth: '18%',
            data: [
                {
                    value: 2312,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#D9FFFF'},
                                {offset: 0.5, color: '#00FFFF'},
                                {offset: 1, color: '#007979'}
                            ]
                        )
                    }
                },
                {
                    value: 2214,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#D9FFFF'},
                                {offset: 0.5, color: '#00FFFF'},
                                {offset: 1, color: '#007979'}
                            ]
                        )
                    }
                },
                // {
                //     value: 3901,
                //     itemStyle: {
                //         color: new echarts.graphic.LinearGradient(
                //             0, 0, 0, 1,
                //             [
                //                 {offset: 0, color: '#D9FFFF'},
                //                 {offset: 0.5, color: '#00FFFF'},
                //                 {offset: 1, color: '#007979'}
                //             ]
                //         )
                //     }
                // }
                ],
            itemStyle: {
                normal: {
                    barBorderRadius: 3
                }
            },
            label: {
                show: true,
                position: "top",
                formatter:'{c}人',
                textStyle: {
                    color: "rgba(255,255,255,.7)"
                }
            }
        }]
    };
    //把配置项给实例对象
    myChart.setOption(option);

    //让图表跟随屏幕尺寸自适应
    window.addEventListener("resize", function () {
        myChart.resize();
    })
})();

//各个工种本月实训人数
(function () {
    //实例化对象
    var myChart = echarts.init(document.querySelector(".month .chart", 'dark'));
    //指定配置项和数据
    var option = {
        color: ["#00FFFF"],
        tooltip: {
            // trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'cross' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            //修改图表的大小
            left: '0%',
            right: '10px',
            bottom: '4%',
            top: '10px',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ["计划完成人数","实际完成人数"],
            axisTick: {
                alignWithLabel: true
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
            },
            axisLine: {
                show: false
            }
        }],
        yAxis: [{
            type: 'value',
            max: function (value) {
                return parseInt(value.max * 1.13);
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12",
                // formatter:'{value}人'
            },
            //y轴的线条的样式
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 2
                }
            },
            //更改y轴分割线
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1
                }
            }
        }],
        series: [{
            name: '实训人数',
            type: 'line',
            data: [158,146],
            label: {
                show: true,
                position: "top",
                formatter: '{c}人',
                textStyle: {
                    color: "rgba(255,255,255,.7)"
                }
            }
        }]
    };
    //把配置项给实例对象
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    })
})();

//各个工种今天实训人数
(function () {
    var myChart = echarts.init(document.querySelector(".week .chart"));

    var option = {
        tooltip: {
            color: ["blue"],
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            //修改图表的大小
            left: '0%',
            right: '10px',
            bottom: '4%',
            top: '10px',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ["计划人数","实际完成人数"],
            axisTick: {
                alignWithLabel: true
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
            },
            axisLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            max: function (value) {
                return parseInt(value.max * 1.13);
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12",
                // formatter: '{value}人'
            },
            //y轴的线条的样式
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 2
                }
            },
            //更改y轴分割线
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1
                }
            }
        }],
        series: [{
            name: '实训人数',
            type: 'bar',
            barWidth: '18%',
            data: [
                {
                    value: 98,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#C4E1E1'},
                                {offset: 0.5, color: '#6FB7B7'},
                                {offset: 1, color: '#336666'}
                            ]
                        )
                    }
                },
                {
                    value: 80,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#C4E1E1'},
                                {offset: 0.5, color: '#6FB7B7'},
                                {offset: 1, color: '#336666'}
                            ]
                        )
                    }
                },
                // {
                //     value: 50,
                //     itemStyle: {
                //         color: new echarts.graphic.LinearGradient(
                //             0, 0, 0, 1,
                //             [
                //                 {offset: 0, color: '#C4E1E1'},
                //                 {offset: 0.5, color: '#6FB7B7'},
                //                 {offset: 1, color: '#336666'}
                //             ]
                //         )
                //     }
                // }
                ],
            itemStyle: {
                normal: {
                    barBorderRadius: 3,
                }
            },
            label: {
                show: true,
                position: "top",
                formatter: '{c}人',
                textStyle: {
                    color: "rgba(255,255,255,.7)"
                }
            }
        }]
    };

    myChart.setOption(option);

    window.addEventListener("resize", function () {
        myChart.resize();
    })
})();


//中间学生签到率仪表图
(function () {
    $.ajax({
        type:"post",
        url:"/arrivedRate",
        success: function(data){
            var myChart = echarts.init(document.querySelector(".map .map_panel .chart"));
            // console.log(data);
            option = {
                animation: false,
                series: [{
                    type: 'gauge',
                    radius: "85%",
                    axisLine: {
                        lineStyle: {
                            width: 20,
                            color: [
                                [0.3, '#fc4e55'],
                                [0.7, '#329cd5'],
                                [1, '#4cdebc']
                            ]
                        }
                    },
                    pointer: {
                        itemStyle: {
                            color: 'auto'
                        }
                    },
                    axisTick: {
                        distance: -30,
                        length: 5,
                        lineStyle: {
                            color: '#fff',
                            width: 2
                        }
                    },
                    splitLine: {
                        distance: -30,
                        length: 20,
                        lineStyle: {
                            color: '#fff',
                            width: 3
                        }
                    },
                    axisLabel: {
                        color: 'auto',
                        distance: 10,
                        fontSize: 10
                    },
                    detail: {
                        valueAnimation: true,
                        fontSize: 25,
                        formatter: '{value}%',
                        color: '#fff'
                    },
                    data: [data]
                }]
            }
            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            })
        }
    })

})();


//车间车床使用比例
(function () {
    var myChart = echarts.init(document.querySelector(".use-rate .chart"));

    $.ajax({
        url: "/useNumber",
        type: 'post',
        success: function (data) {
            option = {
                color: ["#FF60AF", "#00FFFF", "#FF5151"],
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'horizontal',
                    left: 'left',
                    // right: 'right',
                    textStyle: {
                        color: "rgba(255,255,255,.7)",
                        fontSize: 10
                    }
                },
                series: [{
                    name: '使用比率',
                    type: 'pie',
                    radius: '50%',
                    data: [
                        {value: data[0], name: '登录'},
                        {value: data[1], name: '实训中'},
                        {value: data[2], name: '空闲'}
                    ],
                    label: {
                        show: true,
                        normal: {
                            show: true,
                            formatter: '{b}:{c}\n({d}%)'
                        }
                    },
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 20,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.8)'
                        }
                    }
                }]
            };
            myChart.setOption(option);
            window.addEventListener("resize", function () {
                myChart.resize();
            })
        }
    });
})();


//安全测试通过率的echarts图
(function () {
        var myChart = echarts.init(document.querySelector(".test-rate .chart"))

        $.ajax({
            type: "post",
            url: "/passRate",
            success: function (data) {
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: { // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        top: "5%",
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        data: ["年通过率", "月通过率", "周通过率"],
                        axisTick: {
                            alignWithLabel: true
                        },
                        axisLabel: {
                            color: "rgba(255,255,255,.7)",
                            fontSize: 12
                        }
                    }],
                    yAxis: [{
                        type: 'value',
                        max: 120,
                        axisLine: {
                            lineStyle: {
                                color: "rgba(255,255,255,.1)"
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                width: 1,
                                color: "rgba(255,255,255,.1)"
                            }
                        },
                        axisLabel: {
                            fontSize: 12,
                            color: "rgba(255,255,255,.7)",
                            formatter:'{value}%'
                        }
                    }],
                    series: [{
                        type: 'bar',
                        barWidth: '20%',
                        data: [{
                            value: data[0],
                            itemStyle: {
                                color: "#e01f54"
                            }
                        },
                            {
                                value: data[1],
                                itemStyle: {
                                    color: "#d3758f"
                                }
                            }, {
                                value: data[2],
                                itemStyle: {
                                    color: "#f5e8c8"
                                }
                            }
                        ],
                        itemStyle: {
                            normal: {
                                barBorderRadius: 4,
                                formatter: function (params) {
                                    if (params.value) {
                                        return params.value + '%'
                                    } else {
                                        return '';
                                    }
                                }
                            }
                        },
                        label: {
                            show: true,
                            position: "top",
                            formatter:'{c}%',
                            textStyle: {
                                color: "rgba(255,255,255,.7)"
                            }
                        },
                    }]
                };
                myChart.setOption(option);
                window.addEventListener("resize", function () {
                    myChart.resize();
                })
            }
        });
    }
)();


//全部机床的累计使用时间
(function () {
    var myChart = echarts.init(document.querySelector(".use-time .chart"));

    var option = {
        tooltip: {
            color: ["blue"],
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            //修改图表的大小
            left: '0%',
            right: '10px',
            bottom: '4%',
            top: '10px',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ["年使用时间", "月使用时间", "周使用时间"],
            axisTick: {
                alignWithLabel: true
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
            },
            axisLine: {
                show: false
            }

        }],
        yAxis: [{
            type: 'value',
            max: function (value) {
                return parseInt(value.max * 1.1);
            },
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12",
                formatter:'{value} (h)'
            },

            //y轴的线条的样式
            axisLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 2
                }
            },
            //更改y轴分割线
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1
                }
            }
        },],
        series: [{
            // name: '使用时间',
            type: 'bar',
            barWidth: '30%',
            data: [
                {
                    value: 1021,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#a8ccf8'},
                                {offset: 0.5, color: '#2894FF'},
                                {offset: 1, color: '#004B97'}
                            ]
                        )
                    }
                },
                {
                    value: 114,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#a8ccf8'},
                                {offset: 0.5, color: '#2894FF'},
                                {offset: 1, color: '#004B97'}
                            ]
                        )
                    }
                },
                {
                    value: 23,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#a8ccf8'},
                                {offset: 0.5, color: '#2894FF'},
                                {offset: 1, color: '#004B97'}
                            ]
                        )
                    }
                }
            ],
            itemStyle: {
                normal: {
                    barBorderRadius: 3,
                }
            },
            label: {
                show: true,
                position: "top",
                formatter:'{c} h',
                textStyle: {
                    color: "rgba(255,255,255,.7)"
                }
            }
        }]
    };
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    })
})();

//设置滚动条
(function () {
    var timer = setTimeout(this.marquee, 1000);
}());

function marquee() {
    var scrollWidth = $('.not_sign_name').width();
    var textWidth = $('.not_sign_name .text_box').width();
    var i = scrollWidth;
    setInterval(function () {
        i--;
        if (i < -textWidth) {
            i = scrollWidth;
        }
        $('.text_box').animate({
            'left': i + 'px'
        }, 10);
    }, 10);
}

//向信息展板界面中的中间部分添加各个部分实训人数的数据
{
    window.onload = function () {
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
            url: "/className",
            success: function (data) {
                document.getElementById("className").innerHTML = "当前实训班级：" + data
            }
        })

        $.ajax({
            type: "post",
            url: "/classTotalNumber",
            success: function (data) {
                document.getElementById("totalNumber").innerHTML = "应到人数：" + data
            }
        })

        $.ajax({
            type: "post",
            url: "/arrivedNumber",
            success: function (data) {
                document.getElementById("arrivedNumber").innerHTML = "实到人数：" + data
            }
        })

        $.ajax({
            type: "post",
            url:"/notArrivedStudents",
            success: function(data) {
                document.getElementById("notArrivedStudents").innerHTML = data
            }
        })

    }
}
