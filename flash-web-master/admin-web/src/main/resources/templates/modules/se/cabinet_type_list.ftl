<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜类型管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>	
	<script type="text/javascript">
	    function disable(id){
	    	top.jBox.confirm('<@spring.message code="form.disableInfo"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinetType/disAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/cabinetType/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('<@spring.message code="form.enableInfo"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	   loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinetType/disAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/cabinetType/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('<@spring.message code="form.deleteSure"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="form.deleteInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinetType/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code="form.deleteSuccess"></@spring.message>");
			                 		window.location.href="${ctx}/sys/cabinetType/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
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
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.nomoBoxType"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="5%"><@spring.message code="nomo.type.name"></@spring.message></th>
			            <th width="4%"><@spring.message code="nomo.type.capacity"></@spring.message></th>
			            <th width="4%"><@spring.message code="form.status"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td> ${info.typeName!''} </td>
				            <td> ${info.capacity!0} <@spring.message code="nomo.pcs"></@spring.message></td>
				            <td>
				            	<#if info.status ?? && info.status ==1 >
				            		<@spring.message code="status.enable"></@spring.message>
				            	<#elseif info.status ?? && info.status == 2>
				            	<@spring.message code="status.disable"></@spring.message>
				            	<#else>
				            		${info.status!}
				            	</#if>
				             </td>
						</tr>
					</#list>
				</table>
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="form.noData"></@spring.message>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	