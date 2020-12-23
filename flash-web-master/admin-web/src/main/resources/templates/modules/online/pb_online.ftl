'<#include "/include/taglib.ftl" >
<html>
<head>
	<title>设备实时数据管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center;
	  vertical-align: middle !important;
	}
	</style>	
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('<@spring.message code="form.disableInfo"></@spring.message>?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/googleMap/pbDisAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/googleMap/pbOnline";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('<@spring.message code="form.enableInfo"></@spring.message>?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/googleMap/pbDisAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/googleMap/pbOnline";
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
				    <span><@spring.message code="role.liveData"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.livePb"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/googleMap/pbOnline" method="post" class="breadcrumb form-search">
					
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="nomo.pbId"></@spring.message>：</label>
					<@form.input path="portableBatteryCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="nomo.nomoBoxId"></@spring.message>：</label>
					<@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="form.status"></@spring.message>:</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>><@spring.message code="nomo.enable"></@spring.message></option>
            	 			<option value="2" <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="nomo.disable"></@spring.message></option>
            	 			<option value="3" <#if vo.status ?? && vo.status == 3>selected='selected'</#if>><@spring.message code="nomo.hasBuied"></@spring.message></option>
            	 	</select>
            	 	&nbsp;&nbsp;
					<label><@spring.message code="nomo.location.type"></@spring.message>：</label>
					<select id="locationType" name="locationType" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="1" <#if vo.locationType ?? && vo.locationType ==1>selected='selected'</#if>><@spring.message code="nomo.location.inUse"></@spring.message></option>
            	 			<option value="2" <#if vo.locationType ?? && vo.locationType ==2>selected='selected'</#if>><@spring.message code="nomo.location.niCabinet"></@spring.message></option>
            	 	</select>
            	 	<br/><br/>
            	 	<label><@spring.message code="nomo.lineType"></@spring.message>：</label>
					<select id="cable" name="cable" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="0" <#if vo.cable ?? && vo.cable =="0">selected='selected'</#if>><@spring.message code="nomo.lineType_0"></@spring.message></option>
            	 			<option value="1" <#if vo.cable ?? && vo.cable =="1">selected='selected'</#if>><@spring.message code="nomo.lineType_1"></@spring.message></option>
            	 			<option value="2" <#if vo.cable ?? && vo.cable =="2">selected='selected'</#if>><@spring.message code="nomo.lineType_2"></@spring.message></option>
            	 			<option value="3" <#if vo.cable ?? && vo.cable =="3">selected='selected'</#if>><@spring.message code="nomo.lineType_3"></@spring.message></option>
            	 			<option value="4" <#if vo.cable ?? && vo.cable =="4">selected='selected'</#if>><@spring.message code="nomo.lineType_4"></@spring.message></option>
            	 			<option value="5" <#if vo.cable ?? && vo.cable =="5">selected='selected'</#if>><@spring.message code="nomo.lineType_5"></@spring.message></option>
            	 			<option value="6" <#if vo.cable ?? && vo.cable =="6">selected='selected'</#if>><@spring.message code="nomo.lineType_6"></@spring.message></option>
            	 			<option value="7" <#if vo.cable ?? && vo.cable =="7">selected='selected'</#if>><@spring.message code="nomo.lineType_7"></@spring.message></option>
            	 			<option value="8" <#if vo.cable ?? && vo.cable =="8">selected='selected'</#if>><@spring.message code="nomo.lineType_8"></@spring.message></option>
            	 			<option value="9" <#if vo.cable ?? && vo.cable =="9">selected='selected'</#if>><@spring.message code="nomo.lineType_9"></@spring.message></option>
            	 			<option value="A" <#if vo.cable ?? && vo.cable =="A">selected='selected'</#if>><@spring.message code="nomo.lineType_10"></@spring.message></option>
            	 			<option value="B" <#if vo.cable ?? && vo.cable =="B">selected='selected'</#if>><@spring.message code="nomo.lineType_11"></@spring.message></option>
            	 			<option value="C" <#if vo.cable ?? && vo.cable =="C">selected='selected'</#if>><@spring.message code="nomo.lineType_12"></@spring.message></option>
            	 			<option value="D" <#if vo.cable ?? && vo.cable =="D">selected='selected'</#if>><@spring.message code="nomo.lineType_13"></@spring.message></option>
            	 			<option value="E" <#if vo.cable ?? && vo.cable =="E">selected='selected'</#if>><@spring.message code="nomo.lineType_14"></@spring.message></option>
            	 			<option value="F" <#if vo.cable ?? && vo.cable =="F">selected='selected'</#if>><@spring.message code="nomo.lineType_15"></@spring.message></option>
            	 	</select>
            	 	&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="3%"><@spring.message code="nomo.pbId"></@spring.message></th>
						<th width="12%"><@spring.message code="nomo.lineType"></@spring.message></th>
						<th width="5%"><@spring.message code="form.status"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.location.type"></@spring.message></th>
			            <th width="10%"><@spring.message code="nomo.lastUser"></@spring.message></th>
			            <th width="10%"><@spring.message code="nomo.lastLocationTime"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.lastUsedInNomobox"></@spring.message></th>
			            <th width="4%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td> ${info.portableBatteryCode!''} </td>
				            <td> 
				            	<#if info.battType ?? && info.battType == '1'>
				            		<@spring.message code="nomo.battType_1"></@spring.message>
				            	<#elseif info.battType ?? && info.battType == '2'>
				            		<@spring.message code="nomo.battType_2"></@spring.message>
				            	<#elseif info.battType ?? && info.battType == '3'>
				            		<@spring.message code="nomo.battType_3"></@spring.message>
				            	<#elseif info.battType ?? && info.battType == '4'>
				            		<@spring.message code="nomo.battType_4"></@spring.message>
				            	<#else>
				            	</#if>
				            </td>
				            <td> 
				            	<#if info.status ?? && info.status == 1>
				            		<@spring.message code="nomo.enable"></@spring.message>
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="nomo.disable"></@spring.message>
				            	<#elseif info.status ?? && info.status == 3>
				            		<@spring.message code="nomo.hasBuied"></@spring.message>
				            	<#else>
				            		${info.status!}
				            	</#if>
				             </td>
				            
				            <td> 
				            	<#if info.locationType?? && info.locationType == 1>
				            		<@spring.message code="nomo.location.inUse"></@spring.message>
				            	<#elseif info.locationType ?? && info.locationType == 2>
				            		${info.sysCode!},${info.cabinetChannel!}
				            	<#elseif info.locationType ?? && info.locationType == 3>
									<@spring.message code="nomo.hasBuied"></@spring.message>
				            	<#else>
				            		${info.locationType!}
				            	</#if>
				            </td>
				           
				            <td> ${info.lastUseName!}</br>${info.lastUseMobile!} </td>
				           	<td>${(info.lastLocationTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            <td> ${info.lastUseCabinetCode!''} </td>
				            
				           <td>
				           		<#if info.locationType?? && info.locationType != 3>
			            			<@shiro.hasPermission name="sys:liveData:pbModify">
				            			<#if info.status?? && info.status == 1>
											<a  href="javascript:disable(${info.id!});"><@spring.message code="nomo.disable"></@spring.message></a>
				            			</#if>
				            			<#if info.status?? && info.status == 2>
				            				<a  href="javascript:enable(${info.id!});"><@spring.message code="nomo.enable"></@spring.message></a>
				            			</#if>
			            			</@shiro.hasPermission>	
				           		</#if>
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
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