<#include "/include/taglib.ftl" >
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
	    	 top.jBox.confirm('Are you sure you want to disable this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Disabling, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, disable the success");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('Do you want to enable this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Now working, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, enable success");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('Are you sure you want to delete this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		window.location.href="${ctx}/sys/cabinet/list";
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="role.liveData"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.liveNomoBox"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<form id="showForm"  method="post" class="breadcrumb form-search">
					<div>
						<label><@spring.message code="nomo.office"></@spring.message>:</label><label id="officeName">${officeName!}</label>&nbsp;&nbsp;
						&nbsp;&nbsp;<label><@spring.message code="nomo.disable"></@spring.message>： </label> <label >${abnormal!}</label>&nbsp;&nbsp;
						&nbsp;&nbsp;<label><@spring.message code="nomo.enable"></@spring.message>： </label> <label >${normal!}</label>&nbsp;&nbsp;
						&nbsp;&nbsp;<label><@spring.message code="nomo.total"></@spring.message>： </label> <label >${normal + abnormal}</label>&nbsp;&nbsp;
					</div> 
			</form>
			
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/googleMap/cabinetOnline" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="nomo.nomoBoxId"></@spring.message>：</label>
					<@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label><@spring.message code="nomo.merchant"></@spring.message>：</label>
					<@form.input path="sellerName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<label><@spring.message code="nomo.address"></@spring.message>：</label>
					<@form.input path="locationAddress" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<#-- 
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:</label>
						<select id="status" name="status" class="select2 form-control input-small">
								<option value='' >All</opetion>
	            	 			<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>Enabled</option>
	            	 			<option value="2" <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="marketing.disabled"></@spring.message></option>
	            	 	</select>
            	 	-->
					 &nbsp;&nbsp;
					<@spring.message code="nomo.onOffLine"></@spring.message>
					 <select id="online" name="online" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="1" <#if vo.online ?? && vo.online>selected='selected'</#if>><@spring.message code="nomo.online"></@spring.message></option>
            	 			<option value="0" <#if vo.online ?? && !vo.online>selected='selected'</#if>><@spring.message code="nomo.offline"></@spring.message></option>
            	 	</select>
            	 	&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="3%"><@spring.message code="nomo.nomoBoxId"></@spring.message></th>
						<th width="4%"><@spring.message code="nomo.usableNum"></@spring.message></th>
						<th width="4%"><@spring.message code="nomo.disableNum"></@spring.message></th>
			            <th width="4%"><@spring.message code="nomo.emptyNum"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.sellerName"></@spring.message></th>
			            <th width="2%"><@spring.message code="nomo.onOffLine"></@spring.message></th>
			            <th width="12%"><@spring.message code="nomo.lastUpDate"></@spring.message></th>
			            <th width="3%"><@spring.message code="form.status"></@spring.message></th>
			            <th width="8%"><@spring.message code="nomo.address"></@spring.message></th>
			            <th width="3%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td> ${info.sysCode!''}</br>${info.cabinetCode!''} </td>
				            <td> ${info.existPositionNum!0} </td>
				            <td> ${info.disablePositionNum!0} </td>
				            <td> ${info.idlePositionNum!0}</td>
				            <td> ${info.sellerName!''}</td>
				            <td>
				            	<#if info.online?? && info.online>
				            		<@spring.message code="nomo.online"></@spring.message>
				            	<#else>
				            		<@spring.message code="nomo.offline"></@spring.message>
				            	</#if>
				            </td>
				            <td>
				            	<@spring.message code="nomo.heartbeat"></@spring.message>:${(info.lastHeartbeat?string('yyyy-MM-dd HH:mm:ss'))!''}
				            </td>
				            <td>
				            	<#if info.status ?? && info.status ==1 >
				            		<@spring.message code="status.enable"></@spring.message>
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="status.disable"></@spring.message>
				            	<#else>
				            		${info.status!}
				            	</#if>
				             </td>
				             <td> ${info.locationAddress!''} ${info.locationDesc!}</td>
				           <td>
			           		 <@shiro.hasPermission name="sys:cabinet:detail">
	            				<a  href="${ctx}/sys/cabinet/detail?id=${info.id!}&type=cabientOnline"><@spring.message code="form.detail"></@spring.message></a><br/>
			           		 </@shiro.hasPermission>
			           		 <@shiro.hasPermission name="sys:liveData:PowerbankOnline">
	            				<a  href="${ctx}/sys/googleMap/pbOnline?cabinetId=${info.id!}"><@spring.message code="nomo.pb"></@spring.message></a> 
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
					<@spring.message code="form.noData"></@spring.message>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	