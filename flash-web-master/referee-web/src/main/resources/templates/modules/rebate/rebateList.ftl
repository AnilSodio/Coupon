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
				    <span>Profit Sharing</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">Profit Sharing Records </a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/rebate/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<label>user nickname：</label>
					<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="query" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th>user nickname</th>
						<th>revenue type</th>
			            <th>revenue time</th>
			            <th>revenue amount</th>
					</tr>
					<#list page.content as obj>
						<tr>
				            <td> ${obj.nickname!''} </td>
				            <td>
				            <#if obj.rebateType?? && obj.rebateType == 'basic_vip'>
				            		Basic membership fee
				            	<#elseif obj.rebateType ?? && obj.rebateType == 'vip'>
				            		Member recharge
				            	<#elseif obj.rebateType ?? && obj.rebateType == 'recharge'>
				            		Balance recharge
				            	<#else>
				            		${obj.rebateType!}
				            	</#if>
				            </td>
				           	 <td>${(obj.createTime?datetime)!} </td>
				            <td>${obj.currency!}${(obj.rebateAmount)!} </td>
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