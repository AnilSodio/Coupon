<#include "/include/taglib.ftl" >
<html>
<head>
	<title>余额充值记录管理</title>
	<script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    
	    function delAmount(id){
	    	 top.jBox.confirm('Are you sure you want to delete this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/payAmount/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		if(data=="true"){
				                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		}else {
				                 		top.$.jBox.tip("Unfortunately，"+data);
			                 		}
			                 		window.location.href="${ctx}/sys/payAmount/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
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
                    <span><@spring.message code="userInfo.member.top.set"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 				<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <li>
	            	<@shiro.hasPermission name="sys:payAmount:modify">
	                	<a href="${ctx}/sys/payAmount/modify"><@spring.message code="form.add"></@spring.message></a>
		            </@shiro.hasPermission>
	            </li>
	        </ul>
        	
        	   <@form.form id="searchForm" modelAttribute="vo" hidden="true" action="${ctx}/sys/payAmount/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<input id="btnSubmit" class="btn btn-primary" style="display:none" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
			            <th width="9%"><@spring.message code="userInfo.member.vip.payAmount"/></th>
			            <th width="6%"><@spring.message code="userInfo.member.vip.giftAmount"/></th>
			            <th width="12%"><@spring.message code="userInfo.member.description"/></th>
			            <th width="4%"><@spring.message code="sys.sort"/></th>
			            <th width="10%"><@spring.message code="userInfo.member.createdBy"/></th>
			            <th width="10%"><@spring.message code="nomo.createTime"/></th>
			            <th width="8%"><@spring.message code="form.operation"/></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td>${info.currency!} ${info.payAmount!0}</td>
				            <td>${info.currency!} ${info.sendAmount!0}</td>
				            <td>${info.remark!''}</td>
				            <td>${info.sort!''}</td>
				            <td>${info.adminName!}/${info.adminMobile!}</td>
				            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            <td>
				            	<@shiro.hasPermission name="sys:payAmount:modify">
								 	<a  href="${ctx}/sys/payAmount/modify?id=${info.id!}"><@spring.message code="form.edit"/></a> &nbsp;&nbsp;
		            			</@shiro.hasPermission>
		            			
		            			<@shiro.hasPermission name="sys:payAmount:delete">
								 	<a  href="javascript:delAmount(${info.id!});"><@spring.message code="form.delete"/></a> &nbsp;&nbsp;
		            			</@shiro.hasPermission>	
		            			
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords"/>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>