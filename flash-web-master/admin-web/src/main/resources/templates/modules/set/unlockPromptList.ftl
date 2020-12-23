<#include "/include/taglib.ftl" >
<html>
<head>
	<title>提示语管理</title>
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
				    <span>开锁提示语<i class="fa fa-angle-right"></i></span>
				</li>
                
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/unlockPrompt/list" method="post" class="breadcrumb form-search">
					
			</@form.form>
				
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th>标题</th>
			            <th>内容</th>
			            <th>是否开启</th>
			            <th>操作</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>${info.title!''} </td>
				            <td>${info.content!''}</td>
				            <td>	
				            	<#if info.isDelete ?? && info.isDelete>
				            		关闭
				            	<#else>
				            		开启
				            	</#if>
				            	
				            </td>
							<@shiro.hasPermission name="sys:unlockPrompt:modify">
								 <td>
								 	<a  href="${ctx}/sys/unlockPrompt/modify?id=${info.id!}">编辑</a>&nbsp; 
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