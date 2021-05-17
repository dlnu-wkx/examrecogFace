//柱状图模块一
(function() {


    //实例化对象
    var myChart = echarts.init(document.querySelector(".year .chart"));
    //指定配置项和数据
    var option = {
        color: ["#9393FF"],
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
            data: ["钳工", "铣工", "车工"],
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
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
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
            barWidth: '30%',
            data: [1312, 4432, 6131],
            itemStyle: {
                normal: {
                    barBorderRadius: 7,
                    label: {
                        show: true,
                        position: "top",
                        textStyle: {
                            color: "rgba(255,255,255,.7)"
                        }
                    }
                }

            },
        }]
    };
    //把配置项给实例对象
    myChart.setOption(option);

    //让图表跟随屏幕尺寸自适应
    window.addEventListener("resize", function() {
        myChart.resize();
    })
})();
//柱状图模块一
(function() {
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
            data: ["钳工", "铣工", "车工"],
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
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
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
            data: [4323, 5434, 3242, 5433, 4423],
            itemStyle: {
                // barBorderRadius: 3,
                normal: {
                    label: {
                        show: true,
                        position: "top",
                        textStyle: {
                            color: "rgba(255,255,255,.7)"
                        }
                    }
                }
            }
        }]
    };
    //把配置项给实例对象
    myChart.setOption(option);

    window.addEventListener("resize", function() {
        myChart.resize();
    })
})();

(function() {
    var myChart = echarts.init(document.querySelector(".week .chart"));

    var option = {
        color: ["#FF77FF"],
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
            data: ["铣工", "钳工", "车工"],
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
            //修改刻度标签相关样式
            axisLabel: {
                color: "rgba(255,255,255,.7)",
                fontSize: "12"
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
            barWidth: '30%',
            data: [2312, 3432, 2131, 5435, 3123],
            itemStyle: {

                normal: {
                    barBorderRadius: 7,
                    label: {
                        show: true,
                        position: "top",
                        textStyle: {
                            color: "rgba(255,255,255,.7)"
                        }
                    }
                }
            }
        }]
    };

    myChart.setOption(option);

    window.addEventListener("resize", function() {
        myChart.resize();
    })
})();
(function() {
    var myChart = echarts.init(document.querySelector(".use-rate .chart"));
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
                { value: 70, name: '闲置中' },
                { value: 40, name: '实训中' },
                { value: 40, name: '退出系统' }
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

    window.addEventListener("resize", function() {
        myChart.resize();
    })
})();

(
    function() {
        var myChart = echarts.init(document.querySelector(".test-rate .chart"))

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
                    color: "rgba(255,255,255,.7)"
                }
            }],
            series: [{
                type: 'bar',
                barWidth: '20%',
                data: [{
                        value: 90,
                        itemStyle: {
                            color: "#e01f54"
                        }
                    },
                    {
                        value: 93,
                        itemStyle: {
                            color: "#d3758f"
                        }
                    }, {
                        value: 97,
                        itemStyle: {
                            color: "#f5e8c8"
                        }
                    }
                ],
                itemStyle: {
                    normal: {
                        barBorderRadius: 4,
                        label: {
                            show: true,
                            position: "top",
                            textStyle: {
                                color: "rgba(255,255,255,.7)"
                            }
                        },
                        formatter: function(params) {
                            if (params.value) {
                                return params.value + '%'
                            } else {
                                return '';
                            }
                        }
                    }
                }
            }]
        };

        myChart.setOption(option);
    }

)();
