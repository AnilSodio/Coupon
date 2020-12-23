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
				    <span>Balance Record</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">Balance Record </a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/rebate/moneyLog" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					 time：<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    				<input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					&nbsp;&nbsp;
					<label>type：</label>
					<select id="logType" name="logType" class="select2 form-control input-small">
							<option value="">all</option>
            	 			<option value="rebate" <#if vo.logType ?? && vo.logType == 'rebate'>selected='selected'</#if>>Revenue</option>
            	 			<option value="cash" <#if vo.cashStatus ?? && vo.cashStatus == 'cash'>selected='selected'</#if>>Cash-Out</option>
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
						<th>Payment Details</th>
						<th>Original amount</th>
			            <th><@spring.message code="sys.exchangeRate"></@spring.message></th>
			            <th>Converted Amount</th>
			            <th>time</th>
					</tr>
					<#list page.content as obj>
						<tr>
				            <td> ${obj.logType!''} </td>
				            <td> ${obj.oldLogCurrency!''}${obj.oldLogAmount!''} </td>
				            <td> ${obj.exchangeRate!''}</td>
				             <td> ${obj.logCurrency!''} ${obj.logAmount!''}</td>
				           	 <td>${(obj.createTime?datetime)!} </td>
						</tr>
					</#list>
				</table>
				 <div class="pagination">
			        ${page}
			    </div>
			<#else>
				<div class="note note-warning alert">no data</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	