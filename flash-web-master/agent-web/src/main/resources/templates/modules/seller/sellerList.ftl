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
                    <a target="_parent" href="${ctx}"><@spring.message code="agent.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="agent.index.merchantList"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="agent.index.merchantList"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/seller/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="agent.merchant.name"></@spring.message>：</label>
					<@form.input path="sellerName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label><@spring.message code="agent.merchant.email"></@spring.message>：</label>
					<@form.input path="email" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label><@spring.message code="agent.merchant.mobile"></@spring.message>：</label>
					<@form.input path="contactMobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="query" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
			            <th><@spring.message code="agent.merchant.name"></@spring.message></th>
			            <th><@spring.message code="agent.merchant.email"></@spring.message></th>
			            <th><@spring.message code="agent.merchant.country"></@spring.message></th>
			            <th><@spring.message code="agent.merchant.contact"></@spring.message></th>
			            <th><@spring.message code="agent.merchant.mobile"></@spring.message></th>
			            <th><@spring.message code="agent.merchant.address"></@spring.message></th>
					</tr>
					<#list page.content as obj>
						<tr>
				             <td> ${obj.sellerName!}</td>
				             <td> ${obj.email!}</td>
				             <td> ${obj.country!}</td>
				             <td> ${obj.contactUserName!}</td>
				             <td> ${obj.contactMobile!}</td>
				             <td> ${obj.locationAddress!}</td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="agent.form.noData"></@spring.message>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	