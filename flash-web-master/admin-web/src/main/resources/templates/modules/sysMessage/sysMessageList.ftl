<#include "/include/taglib.ftl" >
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
	    
	
		function showPeople(id) {
			top.jBox("iframe:${ctx}/sys/sysMessage/showPeople?id="+id,{
                title: "Sending staff",
                width: 650,
                height: 340,
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
				    <span><@spring.message code="sys.settings"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="sys.message"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message> </a>
	            </li>
	            <@shiro.hasPermission name="sys:sysMessage:send">
	            <li>
					<a href="${ctx}/sys/sysMessage/addSysMessage"><@spring.message code="sys.send"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
					   
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/sysMessage/slist" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
				</@form.form>
		 <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="10%">Message type</th>
						<th width="10%">Message content</th>
						<th width="10%">send the object</th>
			            <th width="10%">Send range</th>
			            <th width="10%"><@spring.message code="marketing.sendTime"></@spring.message></th>
			            
					</tr>
					<#list page.content as info>
						<tr>
							<td>
								<#if info.isImageText ?? && info.isImageText>
									<@spring.message code="sys.graphicMessage"></@spring.message>
								<#else>
									Text message
								</#if>
							</td>
							<td>
					            <#if info.cover??>
					            	<img src="${info.cover!}_100x100">
					            <#else>
					            	${info.content!}
					            </#if>
							</td>
							<td>
								<#if info.sendType ?? && info.sendType == 3>
									repairer
								<#elseif info.sendType ?? && info.sendType == 2>
									member
								<#else>
									${info.sendType!}
								</#if>
							</td>
							<td>
								<#if info.isSendAll>
				            		all
				            	<#else>
				            		<a href="javascript:void(0);" onclick="showPeople('${info.id!}')">Specify to send</a>
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