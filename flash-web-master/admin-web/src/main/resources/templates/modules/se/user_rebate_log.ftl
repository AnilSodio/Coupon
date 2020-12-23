<#include "/include/taglib.ftl" >
<html>
<head>
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"/></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.manage"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.rebate.record"/></span>
                </li>
            </ul>
        </div>

        <div class="portlet light portlet-fit portlet-datatable ">
        
        	<ul class="nav nav-tabs mb-25">
	            <li class="active">
					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
	     
	     <@form.form id="showForm" action="" method="post" class="breadcrumb form-search">
		   		<#if sum_list?? && sum_list?size gt 0>
		   			<#list sum_list as s>
		   				${s.currency!} ${s.rebateAmount!}&nbsp;&nbsp;
		   			</#list>
		   		</#if>
		</@form.form>
		
        <@form.form id="searchForm" action="${ctx}/sys/userRebateLog/list" method="post" class="breadcrumb form-search">
        	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
			
			<@spring.message code="nomo.merchant"/>:<input type="text" name="sellerName" value="${vo.sellerName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<@spring.message code="nomo.agent"/>:<input type="text" name="agentName" value="${vo.agentName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<@spring.message code="nomo.introduct"/>:<input type="text" name="refereeName" value="${vo.refereeName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<@spring.message code="stats.time"/>：
			<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    <input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
		   	&nbsp;&nbsp;
		   	<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
		</@form.form>
		
        
        <#if message??><@tags.message content=message /></#if>
          <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
            <#if page.list?size &gt; 0 >
                <div class="table-scrollable">
			        <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%"><@spring.message code="userInfo.member.rebate.source"/></th>
			            <th width="5%"><@spring.message code="userInfo.member.rebate.user"/></th>
			            <th width="5%"<@spring.message code="userInfo.member.vip.amount"/></th>
			            <th width="6%"><@spring.message code="sys.exchangeRate"></@spring.message></th>
			            <th width="10%"><@spring.message code="stats.time"/></th>
					</tr>
					<#list page.content as info>
						<tr>
					<td>
						${info.nickname!''} 
						<#if info.rebateType?? && info.rebateType == 'basic_vip'>
							Membership card
						<#elseif info.rebateType?? && info.rebateType == 'vip'>
							VIp passes
						<#elseif info.rebateType?? && info.rebateType == 'recharge'>
							Top up
						<#else>
							${info.rebateType!}
						</#if>
					</td>
					
		            <td>
		            	<#if info.userType ?? && info.userType == "seller">
		            		Merchant:${info.sellerName}
		            	<#elseif info.userType ?? && info.userType == "referee">
		            		Introduct:${info.refereeName!}
		            	<#elseif info.userType ?? && info.userType == "agent">
		            		Agent:${info.agentName!}
		            	<#else>
		            		${info.userType!}
		            	</#if>
		            </td>
		            
		            <td>${info.currency!''} ${info.rebateAmount!''}</td>
		            <td>${info.rebateRate!''}</td>
		            
		            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
		             
					</#list>
				</table>
			    	</div>
	                <div class="text-center"><div class="pagination">${page}</div><div>
	               <#else>
						<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
					</#if>
	            </div>
	        </div>
	    </div>
</body>
</html>	