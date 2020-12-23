<#include "/include/taglib.ftl" >
<html>
<head>
	<title>系统参数管理</title>
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
				    <span>参数管理<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>系统参数</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <@shiro.hasPermission name="sys:warning:save">
	            <li class="">
	            	<#if (page.content?size > 0)>
	            		
					<#else>
						<a href="${ctx}/sys/warning/modify">添加</a>
					</#if>
	            </li>
                </@shiro.hasPermission>
                
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/warning/list" method="post" class="breadcrumb form-search">
					
			</@form.form>
				
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th>公司名称</th>
						<th>应用名称</th>
						<th>押金</th>
			            <th>电量预警值</th>
			            <th>电量停用值</th>
			            <th>流量预警值</th>
			            <th>跨城预警值</th>
			            <th>预约限制次数</th>
			            <th>预约限制时间</th>
			            <th>行程/心跳/位置预警时间</th>
			            <th>操作</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>${info.corporateName!''} </td>
							<td>${info.appName!''} </td>
							<td>${info.deposit!0} 元 </td>
				            <td>${info.electricityAlarm!''} %</td>
				            <td>${info.electricityClose!''} %</td>
				            <td>${info.dataAlarm!''}%</td>
				            <td>${info.crossCityAlarm!''} 米</td>
							<td>${info.reserveNum!''} 次</td>
							<td>${info.reserveTime!''} min</td>
							<td>${info.abnormalTime!''} min /${info.heartbeatTime!''}min /${info.addressTime!''}min</td>
							<#--
								<td>${info.heartbeatTime!''}min</td>
								<td>${info.addressTime!''}min</td>
					            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							-->
							<@shiro.hasPermission name="sys:warning:modify">
								 <td>
								 	<a  href="${ctx}/sys/warning/modify?id=${info.id!}">编辑</a>&nbsp; 
					            </td>
		            		</@shiro.hasPermission>	
						</tr>
					</#list>
				</table>
				
				<div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>