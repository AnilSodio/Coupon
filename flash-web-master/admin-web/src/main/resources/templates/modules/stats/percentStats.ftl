<#include "/include/taglib.ftl" >
<html>
<head>
<#include "/include/chart.ftl" >
    <style>
        .pie_no_line {
            height: 300px;
            width: 50%;
            padding-bottom: 40px;
            margin-top: 10px;
        }

        .pie {
            height: 300px;
            width: 50%;
            border-top: 1px solid #eee;
            padding-top: 40px;
            padding-bottom: 40px;
        }
    </style>
</head>
<body>

<div class="page-container-custom">
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span><@spring.message code="stats.analysis"></@spring.message></span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span><@spring.message code="stats.dataStatistics"></@spring.message></span>
            </li>
        </ul>
    </div>

    <div class="portlet light ">
        <div class="portlet-title">
            <div class="caption">
                <span class="caption-subject font-green bold"><@spring.message code="stats.dataStatistics"></@spring.message></span>
            </div>
        </div>

        <div style="display: flex; flex-direction: row; flex-wrap: wrap; padding: 20px;background: white">
            <div id="pie_0" class="pie_no_line" ></div>
            <div id="pie_1" class="pie_no_line" ></div>
            <div id="pie_2" class="pie"></div>
            <div id="pie_3" class="pie"></div>
        </div>

    </div>

    <script type="text/javascript">
        require(
                [
                    'echarts',
                    'echarts/theme/macarons',
                    'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                    'echarts/chart/pie',
                    'echarts/chart/bar'
                ],
                function (ec, theme) {

                    <#list stats as stat>
                        var pie#{stat_index} = ec.init(document.getElementById('pie_#{stat_index}'), theme);
                        var pieData#{stat_index} = {
                            title: {
                                text: '${stat.title}',
                                x: 'center'
                            },
                            tooltip : {
                                trigger: 'item',
                                formatter: "{a}<br/>{b}: {c} ({d}%)"
                            },
                            series: [
                                {
                                    name: '${stat.subTitle}',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        <#list stat.list as item>
                                           <#if item_index != 0>,</#if> {value: ${item.value}, name: "${item.label}"}
                                        </#list>
                                    ],
                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    }
                                }
                            ]
                        };

                        pie#{stat_index}.setOption(pieData#{stat_index});

                    </#list>
                }
        );
    </script>
</div>

</body>
</html>