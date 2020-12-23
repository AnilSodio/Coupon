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
                    <a target="_parent" href="${ctx}"><@spring.message code="merchant.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="merchant.cash.withdrawal.records"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="merchant.cash.withdrawal.records"></@spring.message></a>
	            </li>
	            <li >
	                <a href="${ctx}/rebate/addCash"><@spring.message code="merchant.cash.withdrawal.apply"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/rebate/cashLog" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<@spring.message code="merchant.cash.withdrawal.requestTime"></@spring.message>：<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    				<input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					&nbsp;&nbsp;
					<label><@spring.message code="merchant.cash.withdrawal.status"></@spring.message>：</label>
					<select id="status" name="cashStatus" class="select2 form-control input-small">
							<option value=""><@spring.message code="merchant.form.all"></@spring.message></option>
            	 			<option value="-1" <#if vo.cashStatus ?? && vo.cashStatus == '-1'>selected='selected'</#if>>Awaiting Approval</option>
            	 			<option value="0" <#if vo.cashStatus ?? && vo.cashStatus == '0'>selected='selected'</#if>>Pass</option>
            	 			<option value="2" <#if vo.cashStatus ?? && vo.cashStatus == '2'>selected='selected'</#if>>Refuse</option>
            	 	</select>
            	 	&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="query" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
	            <@tags.message content=message! />
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="merchant.cash.withdrawal.amount"></@spring.message></th>
						<th><@spring.message code="merchant.cash.withdrawal.bankUserName"></@spring.message></th>
			            <th><@spring.message code="merchant.cash.withdrawal.bankName"></@spring.message></th>
			            <th><@spring.message code="merchant.cash.withdrawal.bankNumber"></@spring.message></th>
			            <th><@spring.message code="merchant.cash.withdrawal.status"></@spring.message></th>
			            <th><@spring.message code="merchant.cash.withdrawal.requestTime"></@spring.message></th>
					</tr>
					<#list page.content as obj>
						<tr>
				            <td> ${obj.cashAmount!''} </td>
				            <td> ${obj.cashUserName!''} </td>
				            <td> ${obj.cashBank!''}</td>
				             <td> ${obj.cashAccount!''}</td>
				            <td>
				            	<#if obj.cashStatus=="-1">
				            	<@spring.message code="merchant.cash.withdrawal.status.waiting"></@spring.message>
				            	<#elseif obj.cashStatus=="0">
				            	<@spring.message code="merchant.cash.withdrawal.status.pass"></@spring.message>
				            	<#elseif obj.cashStatus=="2">
				            	<@spring.message code="merchant.cash.withdrawal.status.refuse"></@spring.message>
				            	</#if>
				            </td>
				           	 <td>${(obj.createTime?datetime)!} </td>
						</tr>
					</#list>
				</table>
				 <div class="pagination">
			        ${page}
			    </div>
			<#else>
				<div class="note note-warning alert"><@spring.message code="merchant.form.noData"></@spring.message></div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	