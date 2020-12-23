<#include "/include/taglib.ftl" >
<html>
<head>
<#include "/include/chart.ftl" >
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
                <span>数据魔方</span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>消费统计</span>
            </li>
        </ul>
    </div>

    <div class="portlet light ">
        <div class="portlet-title">
            <div class="caption">
                <span class="caption-subject font-green bold">消费统计</span>
            </div>
        </div>

    <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/stats/consumeStats" method="post" class="breadcrumb form-search">
        <div>
            &nbsp;&nbsp;&nbsp;
            <@form.select id="statsType" path="statsType" class="select2 form-control input-small">
                <@form.option value="1" ><@spring.message code="stats.yearlyStatistics"></@spring.message></@form.option>
                <@form.option value="2"><@spring.message code="stats.monthlyStatistics"></@spring.message></@form.option>
            </@form.select>
            &nbsp;&nbsp;&nbsp;
            <span id="yearSpan"  <#if vo.statsType=='2'>style="display:none;"</#if>>
            	 <input name="year" type="text" readonly="readonly" maxlength="20"
                        class="form-control input-small input-inline"
                        value="${vo.year!}" onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
            	 </span>
            <span id="monthSpan" <#if vo.statsType=='1'>style="display:none;"</#if>>
            	 <input name="month" type="text" readonly="readonly" maxlength="20"
                        class="form-control input-small input-inline"
                        value="${vo.month!}" onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
            	 </span>
            
            &nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>"/>
        </div>
    </@form.form>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <div id="depositLine" style="height:300px; "></div>
        </div>
        <input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
    </div>
</div>
	
	<script type="text/javascript">
		$("#statsType").change(function(){
			if($("#statsType").val()=='1'){
				$("#yearSpan").show();
				$("#monthSpan").hide();
			}else{
				$("#yearSpan").hide();
				$("#monthSpan").show();
			}
		});
        require(
            [
                'echarts',
                'echarts/theme/macarons',
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                'echarts/chart/pie', 
                'echarts/chart/bar'
            ],
            function (ec, theme) {
            	//=====start=====
                var depositLine = ec.init(document.getElementById('depositLine'), theme);
                depositLine.setOption(
                	{
                		title : {
					        text: '${vo.time!}<#if vo.statsType=='1'>年</#if><#if vo.statsType=='2'>月</#if>消费增长趋势图',
					        x:'left'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					     legend: {
					        data:['消费']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					        	dataView : {show: true, readOnly: false},
            					restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false,
					            data : ${timeStr!}
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					            axisLabel : {
					                formatter: '{value}'
					            }
					        }
					    ],
					    series : [
							${growseries!'{name:"",type:"line", data:[]}'}
					    ]
					}
                );
                
            }
        );
    </script>
</body>
</html>