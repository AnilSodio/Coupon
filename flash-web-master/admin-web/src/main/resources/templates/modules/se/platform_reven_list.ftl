<#include "/include/taglib.ftl" >
<html>
<head>
	<title>平台用户管理</title>
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('确认要禁用该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在禁用，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，禁用成功");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('确认要启用该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在启用，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，启用成功");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('确认要删除该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在删除，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，删除成功");
			                 		window.location.href="${ctx}/sys/cabinet/list";
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
				    <span><@spring.message code="financial.manage" /><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="stats.platformRevenue"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/playReven/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<label><@spring.message code="form.status"></@spring.message>:</label>
					<select id="logType" name="logType" class="select2 form-control input-small">
							<option value='' >All</opetion>
            	 			<option value="rebate" <#if vo.logType ?? && vo.logType == "rebate">selected='selected'</#if>>Expenditure</option>
            	 			<#if sysSet.isOpenMemberCard?? && sysSet.isOpenMemberCard>
	            	 			<option value="vip" <#if vo.logType ?? && vo.logType == "vip">selected='selected'</#if>>VIP Approved Setting to buy</option>
							</#if>
							<#if sysSet.isOpenBalance?? && sysSet.isOpenBalance>
	            	 			<option value="recharge" <#if vo.logType ?? && vo.logType == "recharge">selected='selected'</#if>>User Top up </option>
							</#if>
            	 	</select>
            	 	&nbsp;&nbsp;
            	 	<label><@spring.message code="stats.time" />：</label>
            	 	<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    				<input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					
					&nbsp;&nbsp;	
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%"><@spring.message code="financial.income.type" /></th>
						<th width="10%"><@spring.message code="nomo.type.desc" /></th>
			            <th width="4%"><@spring.message code="financial.income.originalamount" /></th>
			            <th width="5%"><@spring.message code="sys.exchangeRate"></@spring.message></th>
			            <th width="4%"><@spring.message code="financial.income.convertedamount" /></th>
			            <th width="12%"><@spring.message code="nomo.createTime"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <td> 
				            	<#if info.logType?? && info.logType == "recharge">
				            		User Top up 
				            	<#elseif info.logType??&& info.logType =="rebate">
				            		Expenditure
				            	<#elseif info.logType??&& info.logType =="order">
				            		Pay the order
				            	<#elseif info.logType??&& info.logType =="vip">
				            		VIp passes
				            	<#elseif info.logType??&& info.logType =="basic_vip">
				            		Membership card
				            	<#else>
				            		${info.logType!}
				            	</#if>
				            </td>
				            <td> ${info.logInfo!} </td>
				            <td> ${info.oldLogCurrency!} ${info.oldLogAmount!}</td>
				            <td> ${info.exchangeRate!}</td>
				            <td> ${info.logCurrency!} ${info.logAmount!}</td>
				            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords" />
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	