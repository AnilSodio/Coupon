<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
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
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">list</a>
	            </li>
	        </ul>
         
		<@tags.message content=message! />
        <#if (firmwares?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="6%">Module name</th>
						<th width="6%">Firmware name</th>
						<th width="8%">Firmware path</th>
			            <th width="5%">Operation</th>
					</tr>
					<#list firmwares as info>
						<tr>
							<td>${info.moubleName!''}</td>
							<td>${info.firmwareName!''}</td>
							<td>${info.firmwareUrl!''}</td>
							<td><input type="radio" name="firmwareId" value="${info.id!}" /></td>
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