<#include "/include/taglib.ftl" >
<html>
<head>
	<title>代理商列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>	
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
				    <span>Financial Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="stats.agentIncome"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/userRebateLog/agentList" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label>Agent Name：</label><@form.input path="agentName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
            	 	&nbsp;&nbsp;
					<label><@spring.message code="sys.mobile"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
            	 	&nbsp;&nbsp;
            	 	<label>Type：</label>
					<select id="authStep" name="authStep" class="select2 form-control input-small">
						<option value='' >All</opetion>
						<option value='basic_vip' <#if vo.rebateType ?? && vo.rebateType = 'basic_vip'>selected='selected'</#if>>Basic membership fee</option>
						<option value='vip' <#if vo.rebateType ?? && vo.rebateType = 'vip'>selected='selected'</#if>>Member recharge</option>
						<option value='recharge' <#if vo.rebateType ?? && vo.rebateType = 'recharge'>selected='selected'</#if>>Balance recharge</option>
					<select>
					<br/><br/>
            	 	<label><@spring.message code="marketing.sendTime"></@spring.message>：</label>
            	 	<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    				<input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					
					&nbsp;&nbsp;	
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%">Agent Name</th>
						<th width="8%">Agent Mobile</th>
						<th width="8%">Type</th>
			            <th width="6%"><@spring.message code="marketing.amount"></@spring.message></th>
			            <th width="10%"><@spring.message code="marketing.sendTime"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            </td>
				            <td> ${info.agentName!} </td>
				            <td> ${info.mobile!} </td>
				            <td>
				            	<#if info.rebateType?? && info.rebateType == 'basic_vip'>
				            		Basic membership fee
				            	<#elseif info.rebateType ?? && info.rebateType == 'vip'>
				            		Member recharge
				            	<#elseif info.rebateType ?? && info.rebateType == 'recharge'>
				            		Balance recharge
				            	<#else>
				            		${info.rebateType!}
				            	</#if>
				            </td>
				            <td> 
				            	${info.currency!}&nbsp;${info.rebateAmount!} 
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