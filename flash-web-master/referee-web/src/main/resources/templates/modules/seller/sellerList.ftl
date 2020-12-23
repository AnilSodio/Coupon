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
				    <span>merchant list</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">merchant list </a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/seller/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label>merchant name：</label>
					<@form.input path="sellerName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label>email：</label>
					<@form.input path="email" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label><@spring.message code="sys.mobile"></@spring.message>：</label>
					<@form.input path="contactMobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="query" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
			            <th>name</th>
			            <th>email</th>
			            <th>country</th>
			            <th>contact</th>
			            <th><@spring.message code="sys.mobile"></@spring.message></th>
			            <th><@spring.message code="marketing.address"></@spring.message></th>
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
					no data！
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	