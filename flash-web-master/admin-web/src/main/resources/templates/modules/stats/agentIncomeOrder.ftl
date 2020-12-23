<#include "/include/taglib.ftl" >
<html>
<head>
<#include "/include/chart.ftl" >

<script type="text/javascript">
	 
    function quickQuery(queryModel){
    
		$("#queryModelId").val(queryModel);
		$("#searchForm").submit();
    }
    
    
</script>
	
	
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
                    <span><@spring.message code="stats.agentIncome"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <span class="caption-subject font-green bold"><@spring.message code="stats.RANKING"></@spring.message></span>
                </div>
            </div>
            <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/stats/agentIncomeStats" method="post" class="breadcrumb form-search">
            <div>
            	
                <a class="btn btn-primary" href="javascript:quickQuery(1);"><@spring.message code="stats.nearlySevenDays"></@spring.message></a>
                 &nbsp;&nbsp;
                <a class="btn btn-primary" href="javascript:quickQuery(2);"><@spring.message code="stats.nearlyAMonth"></@spring.message></a>
                 &nbsp;&nbsp;
                 <a class="btn btn-primary" href="javascript:quickQuery(3);"><@spring.message code="stats.nearlyThreeMonths"></@spring.message></a>
                 &nbsp;&nbsp;
            	 Time
            	 <input type="text" readonly="readonly" name="startTime" class="form-control input-small input-inline" 
            	 	value="${(vo.startTime)!}" onclick="WdatePicker()"/> - 
            	 <input type="text" readonly="readonly" name="endTime" class="form-control input-small input-inline" 
            	    value="${(vo.endTime)!}"  onclick="WdatePicker()"/>
            	 <input type="hidden" id="queryModelId" name= "queryModel" value="" />
                 <br/><br/>
                 <label><@spring.message code="stats.theNumberOfResults"></@spring.message>：
					<select id="limitNum" name="limitNum" class="select2 form-control input-small">
						<option value='10' <#if vo.limitNum ?? && vo.limitNum == 10>selected='selected'</#if>>10</option>
						<option value='10' <#if vo.limitNum ?? && vo.limitNum == 20>selected='selected'</#if>>20</option>
					<select>
				</label>
				 &nbsp;&nbsp;
                 <input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="stats.inquire"></@spring.message>" />
            </div>
        </@form.form>
            <div class="portlet-body form">
            	<#if stats??>  
				<#list stats?keys as statkey> 
				<div id="firstLine${statkey_index+1}" style="height:300px; "></div>
				</#list>
				</#if>
            </div>
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
            	var nameArr = "";
            	var numArr = "";
            	<#if stats??>  
				<#list stats?keys as statkey> 
            	<#assign statVal=stats[statkey] >  
           		<#if statVal??> 
           		<#list statVal.nameArr as name> 
           			nameArr = nameArr+"'"+${name}+"'"+<#if name_has_next>","</#if>;
           		</#list>  
           		<#list statVal.numArr as num> 
           			numArr = numArr+${num}+<#if num_has_next>","</#if>;
           		</#list> 
           		var lineName = "firstLine"+"${statkey_index+1}";
           		var firstLine = ec.init(document.getElementById(lineName), theme);
                firstLine.setOption(
                	{
                		title : {
					        text: '<#if vo.queryModel?? && vo.queryModel == "1"><@spring.message code="stats.nearlySevenDays"></@spring.message><#elseif vo.queryModel?? && vo.queryModel == "2"><@spring.message code="stats.nearlyAMonth"></@spring.message><#elseif vo.queryModel?? && vo.queryModel == "3"><@spring.message code="stats.nearlyThreeMonths"></@spring.message><#else>${vo.startTime!}</#if> Top ${vo.limitNum!} (${statkey}) ',
					        x:'left'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    
					    toolbox: {
					        show : true,
					        feature : {
					        	mark : {show: true},
					        	dataView : {show: true, readOnly: false},
					        	magicType: {show: true, type: ['line', 'bar']},
					        	restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 0.01]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'category',
					            data: [nameArr]
					        }
					    ],
					    series : [
					        {
					        	name:'${statkey}',
					            type:'bar',
					            data:[numArr]
					        }
					    ]
					}
                );
           		
            	</#if> 
            	</#list>   
				</#if> 
            }
        );
    </script>
</body>
</html>