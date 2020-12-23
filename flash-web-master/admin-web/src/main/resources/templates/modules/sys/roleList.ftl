<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	    function deleteThis(uuid){
	    	 top.jBox.confirm('Are you sure you want to delete this character?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/role/delete?id="+uuid;
	            }
	            return true;
 			 });
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
				    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="sys.roleManagement"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message> </a>
	            </li>
                <@shiro.hasPermission name="sys:role:modify">
	            <li>
					<a href="${ctx}/sys/role/form"><@spring.message code="form.add"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
            
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
                <div class="table-scrollable">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="sys.roleName"></@spring.message></th>
						<th><@spring.message code="sys.roleIdentification"></@spring.message></th>
						<th><@spring.message code="sys.attributionAgencies"></@spring.message></th>
<!-- 						<th>数据范围</th>
						<th>角色类型</th>
						<th>是否系统数据</th> -->
						<th><@spring.message code="sys.itSUsableOrNot"></@spring.message></th>
						<th><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list list as role>
						<tr>
							<td>
								<a href="form?id=${role.id}">${role.name!}</a>
							</td>
							<td>${role.code!}</td>
							<td>${role.officeName!}</td>
							<!-- <td>
								${fns.getDictLabel(role.dataScope, 'sys_data_scope', '无')}
							</td>
							<td>
								<#if role.roleType??>
									<#if role.roleType==1>
									普通角色
									<#elseif role.roleType==2>
									管理角色
									<#elseif role.roleType==3>
									任务分配
									</#if>
								</#if>
							</td>
							<td>
								<#if role.isSys??>
									<#if role.isSys==1>
									是
									<#elseif role.isSys==0>
									否
									</#if>
								</#if>
							</td> -->
							<td>
								<#if role.useable??>
									<#if role.useable==1>
									是
									<#elseif role.useable==0>
									否
									</#if>
								</#if>
							</td>
							<td>
							<@shiro.hasPermission name="sys:role:modify">
								<a href="${ctx}/sys/role/form?id=${role.id}"><@spring.message code="form.edit"></@spring.message></a>
							</@shiro.hasPermission>	
							<@shiro.hasPermission name="sys:role:delete">
								<a onclick="deleteThis(${role.id!})"><@spring.message code="form.delete"></@spring.message></a>
							</@shiro.hasPermission>	
							</td>
						</tr>
					</#list>
				</table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>