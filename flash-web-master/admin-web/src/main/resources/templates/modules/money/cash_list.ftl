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
			top.$.jBox.open("iframe:${ctx}/sys/userMoney/checkForm?id="+id, "提现审核", 500, 250, {
				buttons:{"Yes":"ok", "Close":true}, submit:function(v, h, f){
						if (v=="ok"){
							var cashAdminInfo = h.find("iframe").contents().find("#checkDesc").val();
							var cashStatus = h.find("iframe").contents().find("#result").val();
							if(cashStatus == "")
							{
								top.$.jBox.tip("Please choose the result");
								return false;
							}
							if(cashAdminInfo == "")
							{
								top.$.jBox.tip("Please enter the remark");
								return false;
							}
							
							if(cashAdminInfo != "" && cashAdminInfo.length < 200)
							{
								top.jBox.confirm('Confirm that you want to review the record？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
						            if (v === 'ok') {
						                 $.ajax({
						                 	url:"${ctx}/sys/userMoney/cashFormSave?id="+id+"&cashAdminInfo="+cashAdminInfo+"&cashStatus="+cashStatus,
						                 	type:"POST",
						                 	success:function(data){
						                 		if(data == "0000") {
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
                    <span>Financial Management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Merchant Withdrawal</span>
                </li>
            </ul>
        </div>

        <div class="portlet light portlet-fit portlet-datatable ">
        
        	<ul class="nav nav-tabs mb-25">
	            <li class="active">
				<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
	        
        <@form.form id="searchForm" action="${ctx}/sys/userMoney/list" method="post" class="breadcrumb form-search">
        	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
			
			Business Name：<input type="text" name="sellerName" value="${vo.sellerName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			Business phone number：<input type="text" name="contactMobile" value="${vo.contactMobile!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<@spring.message code="sys.LogInName"></@spring.message>：<input type="text" name="loginName" value="${vo.loginName!}" maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<label><@spring.message code="form.status"></@spring.message>:</label>
			<select id="cashStatus" name="cashStatus" class="select2 form-control input-small">
				<option value='' >All</opetion>
    	 		<option value="-1" <#if vo.cashStatus ?? && vo.cashStatus == "-1">selected='selected'</#if>>Awaiting Approval</option>
    	 		<option value="0" <#if vo.cashStatus ?? && vo.cashStatus == "0">selected='selected'</#if>>Pass</option>
    	 		<option value="2" <#if vo.cashStatus ?? && vo.cashStatus == "2">selected='selected'</#if>>Refuse</option>
    	 	</select>
    	 	<#-- 
			&nbsp;&nbsp;
			<label>提现方式：</label>
			<select id="cashPaymentType" name="cashPaymentType" class="select2 form-control input-small">
				<option value="">All</option>
    	 		<option value="alipay" <#if vo.cashPaymentType ?? && vo.cashPaymentType == "alipay">selected='selected'</#if>>Alipay</option>
    	 		<option value="chinabank" <#if vo.cashPaymentType ?? && vo.cashPaymentType == "chinabank">selected='selected'</#if>>网银在线</option>
    	 	</select>
    	 	-->
    	 	&nbsp;&nbsp;
			application time：
			<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    <br/></br/>
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
						<th width="8%">Business information</th>
			            <th width="5%"><@spring.message code="sys.LogInName"></@spring.message></th>
			            <th width="5%"><@spring.message code="marketing.amount"></@spring.message></th>
			            <#-- 
			            <th width="5%">Reflect the way</th>
			            -->
			            <th width="5%">Payee Name</th>
			            <th width="10%">Billing message</th>
			            <th width="5%">Status</th>
			            <th width="12%">application time</th>
			            <#-- 
			            <th width="10%"><@spring.message code="sys.remarks"></@spring.message></th>
			            -->
			            <th width="6%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
					<td>${info.sellerName!''} <br/> ${info.contactMobile!''}</td>
		            <td>${info.loginName!''}</td>
		            <td>
	            		<#list countries as country>
							<#if info.country ?? && info.country == country.nationCode>
								${country.englishName!}
							</#if>
						</#list>
		            </td>
		            <#-- 
		             <td><#if info.cashPaymentType ?? && info.cashPaymentType == "alipay">
		            		Alipay
		            	<#elseif info.cashPaymentType ?? && info.cashPaymentType == "chinabank">
		            		Internet Banking Online
		            	<#else>
		            		${info.cashPaymentType!}
		            	</#if>
		            	</td>
		            	-->
		            <td>${info.cashUserName!''}</td>
		            <td>${info.cashBank!''} ${info.cashAccount!''}</td>
		            <td>
		            	<#if info.cashStatus == "-1">
		            		Awaiting Approval
		            	<#elseif info.cashStatus == "0">
		            		Pass
		            	<#elseif info.cashStatus == "1">
		            		Transferred
	            		<#elseif info.cashStatus == "2">
		            		Refuse
		            	</#if>
		            </td>
		             <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
		             <#--
		            <td>${info.cashInfo!''}</td>
		             -->
		            <td>
		            	<#if info.cashStatus == "-1">
		            		<a  href="javascript:showCheckDialog(${info.id!});">Audit</a>
		            	<#elseif info.cashStatus == "0">
		            		<#-- 
		            		<a  href="javascript:transfer(${info.id!});">转账</a>
		            		-->
		            	<#elseif info.cashStatus == "1">
		            		<a  href="${ctx}/userMoney/detail?id=${info.id!}"><@spring.message code="form.detail"></@spring.message></a>
		            	</#if>
		            </td>
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