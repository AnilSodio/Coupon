<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    
	    function showCheckDialog(id)
	    {
	    	// 正常打开
			top.$.jBox.open("iframe:${ctx}/sys/userMoney/checkForm?id="+id, "Merchant Withdrawal", 500, 250, {
				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
						if (v=="ok"){
							var cashAdminInfo = h.find("iframe").contents().find("#checkDesc").val();
							var cashStatus = h.find("iframe").contents().find("#result").val();
							if(cashStatus == "")
							{
								top.$.jBox.tip("Please select the audit result");
								return false;
							}
							if(cashAdminInfo == "")
							{
								top.$.jBox.tip("Please enter the note information");
								return false;
							}
							
							if(cashAdminInfo != "" && cashAdminInfo.length < 200)
							{
								top.jBox.confirm('Are you sure you want to review the record?？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
					            if (v === 'ok') {
					                 $.ajax({
					                 	url:"${ctx}/sys/userMoney/cashFormSave?id="+id+"&cashAdminInfo="+cashAdminInfo+"&cashStatus="+cashStatus,
					                 	type:"POST",
					                 	success:function(data){
					                 		if(data == "0000")
					                 		{
					                 			top.$.jBox.close();
					                 			top.$.jBox.tip("恭喜，操作成功");
					                 			window.location.reload();
					                 		}else{
					                 			top.$.jBox.tip(data);
					                 		}
					                 	}
					                 });
					            }
				 			 });
			            	return false;
						}
					}
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
	    }
	    
	    
	      function transfer(id){
	    	 top.jBox.confirm('确认要转账吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在转账，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/userMoney/transfer?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		if("0000"== data){
				                 		top.$.jBox.tip("恭喜，交易成功");
			                 		}else {
			                 			top.$.jBox.tip(data);
			                 		}
			                 		window.location.href="${ctx}/userMoney/list";
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
                    <span>User info<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Rebate’s Record</span>
                </li>
            </ul>
        </div>

        <div class="portlet light portlet-fit portlet-datatable ">
        
        	<ul class="nav nav-tabs mb-25">
	            <li class="active">
					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
	     
	     <@form.form id="showForm" action="" method="post" class="breadcrumb form-search">
		   		Total Rebate: 
		   		<#if rebateMap?exists>
                <#list rebateMap?keys as key> 
                   ${key}&nbsp;&nbsp; ${rebateMap.get(key)}&nbsp;&nbsp; 
                </#list>
            	</#if>
		</@form.form>
		
        <@form.form id="searchForm" action="${ctx}/sys/rebateMoney/list" method="post" class="breadcrumb form-search">
        	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
			
			nickname：<input type="text" name="nickname" value="${vo.nickname!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<@spring.message code="sys.mobile"></@spring.message>：<input type="text" name="mobile" value="${vo.mobile!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			Merchant:<input type="text" name="sellerName" value="${vo.sellerName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			Agent:<input type="text" name="agentName" value="${vo.agentName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<br/><br/>	
			Introduct:<input type="text" name="refereeName" value="${vo.refereeName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			Time：
			<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    <input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
		   	&nbsp;&nbsp;
		   	<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
		   	
		</@form.form>
		
        
        <#if message??><@tags.message content=message /></#if>
          <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
            <#if page.list?size &gt; 0 >
                <div class="table-scrollable">
			        <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%">User Info</th>
			            <th width="5%">Rebate Type</th>
			            <th width="5%">Original Amount</th>
			            <th width="10%">Actual Amount</th>
			            <th width="6%"><@spring.message code="sys.exchangeRate"></@spring.message></th>
			            <th width="10%"><@spring.message code="sys.remarks"></@spring.message></th>
			            <th width="10%"><@spring.message code="stats.time"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
					<td>${info.nickname!''} <br/> ${info.mobile!''}</td>
		            <td>
		            	<#if info.userType ?? && info.userType == 1>
		            		Merchant:${info.sellerName} Rebate
		            	<#elseif info.userType ?? && info.userType == 2>
		            		Introduct:${info.refereeName!} Rebate
		            	<#elseif info.userType ?? && info.userType == 3>
		            		Agent:${info.agentName!} Rebate
		            	<#else>
		            		User Top up
		            	</#if>
		            </td>
		            <td>${info.oldLogCurrency!''} ${info.oldLogAmount!}</td>
		            <td>${info.logCurrency!''} ${info.logAmount!''}</td>
		            <td>${info.exchangeRate!''}</td>
		            <td>${info.logInfo!''}</td>
		            
		             <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
		             
					</#list>
				</table>
			    	</div>
	                <div class="text-center"><div class="pagination">${page}</div><div>
	               <#else>
						<div class="note note-warning alert">No data！</div>
					</#if>
	            </div>
	        </div>
	    </div>
</body>
</html>	