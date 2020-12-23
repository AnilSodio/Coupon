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
				    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="sys.exchangeRateManagement"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
			<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/lock/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					 <input id="btnSubmit" class="btn btn-primary" style="display:none" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
			</@form.form>
			
		<@tags.message content=message! />
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="10%"><@spring.message code="sys.theOriginalCurrency"></@spring.message></th>
						<th width="10%"><@spring.message code="sys.theTargetCurrency"></@spring.message></th>
						<th width="8%"><@spring.message code="sys.exchangeRate"></@spring.message></th>
			            <th width="5%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>
								${info.currencyFrom!}
							</td>
							<td>
								${info.currencyTo!}
							</td>
				            <td>${info.exchangeRate! 0.00}</td>
				         
							<@shiro.hasPermission name="sys:exRate:modify">
								 <td>
							 		<a  href="${ctx}/sys/exRate/modify?id=${info.id!}"><@spring.message code="form.edit"></@spring.message></a> &nbsp;&nbsp;
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