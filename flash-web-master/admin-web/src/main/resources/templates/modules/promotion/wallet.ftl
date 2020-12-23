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
	                <a href="${ctx}/sys/pro/addPromotion">添加 </a>
	            </li>
	            </@shiro.hasPermission>
	        </ul>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="15%">活动名称</th>
						<th width="10%">活动类型</th>
			            <th width="20%">活动时间</th>
			            <th width="5%">是否全国</th>
			            <th>活动地区</th>
			            <th width="15%">活动优惠券</th>
			            <th width="10%">操作</th>
					</tr>
			</div>
        </div>
    </div>
</body>
</html>