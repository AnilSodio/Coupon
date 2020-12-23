'<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
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
				    <span>系统消息管理<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
<@spring.message code="form.list"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
<@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:sysMessage:modify">
	            <li>
					<a href="${ctx}/sys/sysMessage/modify">添加</a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/sysMessage/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<#--
						<div>
							<label>姓名：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
							&nbsp;<label>手机号：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
							
							<label>状态：
								<select id="status" name="status" class="select2 form-control input-small">
									<option value=''>All</opetion>
									<option value='1'  <#if vo.status ?? && vo.status == '1' >selected='selected'</#if>>正常</opetion>
									<option value='2' <#if vo.status ?? && vo.status == '2'>selected='selected'</#if>>禁用</option>
								<select>
							</label>
							&nbsp;&nbsp;
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
						</div>
					-->
				</@form.form>
		 <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="20%">消息内容</th>
						<th width="6%">接受人员</th>
						<th width="6%">人员类型</th>
						<th width="8%">消息类型</th>
			            <th width="12%">发送时间</th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.content !''}</td>
				            <td>
				            	<#if info.repairerName ??>
				            		${info.repairerName!}
				            	<#else>
				            		平台
				            	</#if>
				            </td>
				            <td>
				            	<#if info.toUserType?? && info.toUserType == "1">
				            		平台
				            	<#elseif info.toUserType ?? && info.toUserType == "2">
				            		业主
				            	<#elseif info.toUserType ?? && info.toUserType == "3">
				            		维保
				            	<#elseif info.toUserType ?? && info.toUserType == "4">
				            		运营
				            	</#if>
				            </td>
				            <td>
				            	<#if info.bizType?? && info.bizType == 11>
				            		报警提醒
				            	<#elseif info.bizType ?? && info.bizType == 12>
				            		保养提醒
				            	<#elseif info.bizType ?? && info.bizType == 13>
				            		合同消息
				            	<#elseif info.bizType ?? && info.bizType == 14>
				            		工单状态消息
				            	<#elseif info.bizType ?? && info.bizType == 15>
				            		其他
				            	<#elseif info.bizType ?? && info.bizType == 21>
				            		停车消息
				            	<#elseif info.bizType ?? && info.bizType == 22>
				            		取车消息
				            	<#elseif info.bizType ?? && info.bizType == 23>
				            		系统消息
				            	<#elseif info.bizType ?? && info.bizType == 31>
				            		工单消息
				            	<#elseif info.bizType ?? && info.bizType == 32>
				            		协助消息
				            	<#elseif info.bizType ?? && info.bizType == 33>
				            		系统消息
				            	<#elseif info.bizType ?? && info.bizType == 41>
				            		车位任务消息
				            	<#elseif info.bizType ?? && info.bizType == 42>
				            		维保管理消息
				            	<#elseif info.bizType ?? && info.bizType == 43>
				            		报警提醒消息
				            	<#elseif info.bizType ?? && info.bizType == 44>
				            		合同消息
				            	</#if>
				            </td>
				            <td>${(info.sendTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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