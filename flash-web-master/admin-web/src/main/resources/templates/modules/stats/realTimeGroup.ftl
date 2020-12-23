<#include "/include/taglib.ftl" >
<#include "/include/chart.ftl" >
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#groupType").val("${groupType!}").select2();
		
		});
 		
	</script>
<div class="page-container-custom">
    <div class="page-bar">
         <ul class="page-breadcrumb">
	        <li>
	            <i class="icon-home"></i>
	            <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
	            <i class="fa fa-angle-right"></i>
	        </li>
	          <li>
	                <span><@spring.message code="stats.realTimeData"></@spring.message></span>
	            </li>
	    </ul>
    </div>
    <div class="portlet light ">
        <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/rlStats"><@spring.message code="stats.realTimeData"></@spring.message> </a>
	            </li>
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="stats.realTimeData"></@spring.message> </a>
	            </li>
	     </ul>
    <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/rlStats/group" method="post" class="breadcrumb form-search">
        <input type="hidden" name="type" value="${type!}"/>
         <input type="hidden" name="day" value="${day!}"/>
        <div>
            <select id="groupType" name="groupType" class="select2 form-control input-small">
            	<#--
	            	<#if type!="registNum">
	                <option value="area">区域</option>
	                </#if>
                 -->
                <option value="client_type"><@spring.message code="stats.client"></@spring.message> </option>
                <#-- 
                <option value="sex">性别</option>
                <#if type!="registNum">
                <option value="time">时间段</option>
                </#if>
                <option value="age">年龄段</option>
                -->
                <#if type=="vipPay">
                	<option value="vip_type">member card</option>
                </#if>
            </select>
            &nbsp;&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>"/>
        </div>
    </@form.form>
        <div class="portlet-body form">
            <!-- BEGIN FORM-->
            <div id="tripNum" style="height:400px; "></div>
        </div>
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
                //=====start=====
                var tripNum = ec.init(document.getElementById('tripNum'), theme);
                tripNum.setOption(
                        {
						   title : {
						        text: '${dayStr!}${typeStr!}',
						        x:'center'
						    },
						    tooltip : {
						        trigger: 'item',
						        formatter: "{a} <br/>{b} : {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						       	x : 'left',
						        data: ${groupName!}
						    },
						    series : [
						        {
						            name: '${typeStr!}',
						            type: 'pie',
						            radius : '55%',
						            center: ['50%', '60%'],
						            data:${value!},
						            itemStyle: {
						            	normal: {
						            	label:{  
						                	show:true,  
						                	formatter:'{b} : {c} ({d}%)'  
							            	},  
							            	labelLine:{show:true}
							            }, 
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
                        }
                );

              
            }
    );
</script>
