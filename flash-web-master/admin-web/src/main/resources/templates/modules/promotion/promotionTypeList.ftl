<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	<script type="text/javascript">
		
	     function deleteType2(id){
	    	 top.jBox.confirm('<@spring.message code="sys.AreYouSureYouWantToDeleteTheRecord"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Removing, please wait...');
	                  window.location.href="${ctx}/sys/proType/delete?id="+id;
	            }
	            return true;
 			 });
	    }
	    
	    function deleteType(id){
	    	 top.jBox.confirm('Are you sure you want to delete the record', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/proType/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		window.location.href="${ctx}/sys/proType/list";
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
                    <span><@spring.message code="marketing.activityTypeManagement"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:proType:list">
	            <li >
	                <a href="${ctx}/sys/proType/modify"><@spring.message code="form.add"></@spring.message> </a>
	            </li>
	            </@shiro.hasPermission>
	        </ul>
			<form id="searchForm"  action="${ctx}/sys/proType/list" method="post" >
			</form>
		
        <#if (type_list?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="marketing.typeName"></@spring.message></th>
						<th>type code</th>
						<th><@spring.message code="nomo.createTime"></@spring.message></th>
			            <th><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list type_list as obj>
						<tr>
							<td>${obj.typeName!''}</td>
							<td>${obj.typeCode!''} </td>
							<td>${(obj.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							 <td>
						 		<@shiro.hasPermission name = "sys:proType:edit">
						 			<a  href="${ctx}/sys/proType/modify?id=${obj.id!}"><@spring.message code="form.edit"></@spring.message></a>
						 		</@shiro.hasPermission>
						 		<@shiro.hasPermission name = "sys:proType:delete">
						 			<a  href="javascript:deleteType(${obj.id!});"><@spring.message code="form.delete"></@spring.message></a>
						 		</@shiro.hasPermission>
				            </td>
						</tr>
					</#list>
				</table>
			    <#else>
					<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
				</#if>
			</div>
        </div>
    </div>
</body>
</html>