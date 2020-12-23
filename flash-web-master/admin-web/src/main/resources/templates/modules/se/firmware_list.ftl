<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜固定管理</title>
	<script type="text/javascript">
	
	    function delFirmware(id){
	    
	    	 top.jBox.confirm('Are you sure you want to delete this record?', '提示', function (v, h, f) {
	            if (v == true) {
	            	   loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/firmware/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		window.location.href="${ctx}/sys/firmware/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
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
                    <a target="_parent" href="${ctx}">Home</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
				<li>
				    <span>Cabinet firmware<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>list</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">list</a>
	            </li>
	            <@shiro.hasPermission name="sys:firmware:modify">
	            <li class="">
					<a href="${ctx}/sys/firmware/modify">New</a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
        	
			<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/firmware/list" method="post" class="breadcrumb form-search">
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
						<th width="6%">Module name</th>
						<th width="6%">Firmware name</th>
						<th width="8%">Firmware path</th>
			            <th width="5%">Operation</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>${info.moubleName!''}</td>
							<td>${info.firmwareName!''}</td>
							<td>${info.firmwareUrl!''}</td>
				            
							<td>
							<@shiro.hasPermission name="sys:firmware:modify">
								 		<a  href="${ctx}/sys/firmware/modify?id=${info.id!}">Edit</a> &nbsp;&nbsp;
		            		</@shiro.hasPermission>	
							<@shiro.hasPermission name="sys:firmware:delete">
								 		<a  href="javascript:delFirmware(${info.id!});">Delete</a> &nbsp;&nbsp;
		            		</@shiro.hasPermission>	
					        </td>
						</tr>
					</#list>
				</table>
			    <#else>
				<div class="note note-warning alert">
					暂无记录！
				</div>
			</#if>
			</div>
        </div>
    </div>
</body>
</html>