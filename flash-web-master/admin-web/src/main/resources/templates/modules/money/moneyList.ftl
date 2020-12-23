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
	    
	    function abnormalExport() {
    	
			document.getElementById("searchForm").action="${ctx}/sys/money/export";
    		$("#searchForm").submit();
    		document.getElementById("searchForm").action="${ctx}/sys/money/list";
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
				    <span><@spring.message code="financial.manage" /><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="financial.deposit.manage" /></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/money/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp; &nbsp;
						<label><@spring.message code="marketing.userS.phoneNumber"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp; &nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="marketing.userS.nickname"></@spring.message></th>
			            <th><@spring.message code="marketing.userS.phoneNumber"></@spring.message></th>
			            <th><@spring.message code="stats.deposit"></@spring.message></th>
			            <!--
			            	<th>时间</th>
			            -->
			            <th><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.nickname!''}</td>
				            <td>${info.mobile!''}</td>
				            <td>${info.currency!} ${info.deposit!''}</td>
				            
				            <!-- 
				            	<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							-->
							<@shiro.hasPermission name="sys:money:list">
								 <td>
					            	 <a  href="${ctx}/sys/money/details?uid=${info.uid!}"><@spring.message code="form.detail"></@spring.message></a>&nbsp;
					            </td>
		            		</@shiro.hasPermission>	
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords" />
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>