<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	<script type="text/javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/sys/pro/listPro").submit();
    	return false;
	}
	
	function del(id){
    	 top.jBox.confirm('<@spring.message code="sys.AreYouSureYouWantToDeleteTheRecord"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
            if (v === 'ok') {
            	  loading('Removing, please wait ...');
            	  $.ajax({
		                 	url: "${ctx}/sys/pro/delPromotion?pid="+id,
		                 	type:"POST",
		                 	success:function(data){
		                 		top.$.jBox.tip("Congratulations, delete successfully");
		                 		window.location.href="${ctx}/sys/pro/listPro";
		                 	}
		          	 });
            }
            return true;
		 });
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
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="marketing.activityManagement"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:promotion:list">
	            <li >
	                <a href="${ctx}/sys/pro/addPromotion"><@spring.message code="form.add"></@spring.message> </a>
	            </li>
	            </@shiro.hasPermission>
	        </ul>
			<form id="searchForm"  action="${ctx}/sys/pro/listPromotion" method="post" >
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			</form>
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="15%"><@spring.message code="marketing.activityName"></@spring.message></th>
						<th width="10%"><@spring.message code="marketing.activityType"></@spring.message></th>
			            <th width="16%"><@spring.message code="stats.time"></@spring.message></th>
			            <th width="15%"><@spring.message code="marketing.coupon"></@spring.message></th>
			            <th width="10%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as obj>
						<tr>
							<td>${obj.proName!''}</td>
							<td>${obj.proTypeName!''}</td>
							<td>${obj.beginDate?date} 至 ${obj.endDate?date}</td>
							<#-- 
							<td>
								<#if obj.allCountry ?? && obj.allCountry>
									是
								<#else>
									否
								</#if>
							</td>
							<td>
								<#if obj.allCountry ?? && obj.allCountry>
									全国
								<#else>
									${obj.areaLabel!''}
								</#if>
								
							</td>
							-->
							<td> ${obj.currency!} ${obj.amount!''} ${obj.PercentageOfDiscount!''}元（${obj.dayLimit!''}天有效）</td>
							<td>
						 		<@shiro.hasPermission name="sys:promotion:modify">
						 		<a  href="${ctx}/sys/pro/addPromotion?pid=${obj.id!}"><@spring.message code="form.edit"></@spring.message></a>
						 		</@shiro.hasPermission>
						 		<@shiro.hasPermission name = "sys:promotion:delete">
						 			<a href="javascript:del(${obj.id!});"><@spring.message code="form.delete"></@spring.message></a>
						 		</@shiro.hasPermission>
				            </td>
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