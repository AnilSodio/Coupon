<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('确认要禁用该记录吗？', '提示', function (v, h, f) {
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
	    	 top.jBox.confirm('确认要启用该记录吗？', '提示', function (v, h, f) {
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
	    	 top.jBox.confirm('确认要删除该记录吗？', '提示', function (v, h, f) {
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
	    
	    function showEndDialog(id) {
	    	
	    	top.$.jBox.open("iframe:${ctx}/sys/order/endForm?id="+id, "End use", 500, 250, {
	    	
	    		buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	    		
						if (v=="ok"){
						
							var checkDesc = h.find("iframe").contents().find("#checkDesc").val();
							
							if(checkDesc == ""){
								top.$.jBox.tip("Please enter the note information");
								return false;
							}
							
							if(checkDesc != "" && checkDesc.length < 200)
							{
								top.jBox.confirm('Are you sure you want to end this itinerary?', '提示', function (v, h, f) {
					            if (v === 'ok') {
					                 $.ajax({
					                 	url:"${ctx}/sys/order/endTrip?id="+id+"&checkDesc="+checkDesc,
					                 	type:"POST",
					                 	success:function(data){
					                 		if(data == "0000")
					                 		{
					                 			top.$.jBox.close();
					                 			top.$.jBox.tip("Congratulations, the operation is successful。");
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
				    <span>Order records</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">List</a>
	            </li>
	            
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/order/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label>powerbank ID：</label>
					<@form.input path="pbCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label>
					<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label>User's mobile phone：</label>
					<@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<br/></br/>
					&nbsp;&nbsp;
					<label>status：</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value='' >All</opetion>
            	 			<option value="30" <#if vo.status ?? && vo.status == 30>selected='selected'</#if>><@spring.message code="sys.using"></@spring.message></option>
            	 			<option value="40" <#if vo.status ?? && vo.status == 40>selected='selected'</#if>>unpaid</option>
            	 			<option value="50" <#if vo.status ?? && vo.status == 50>selected='selected'</#if>>Paid</option>
            	 	</select>
            	 	&nbsp;&nbsp;
					Payment time：<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    						    <input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					
					 &nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="Search" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<#-- 
						<th width="3%">订单号</th>
						<th width="5%">服务商</th>
						-->
			            <th width="4%"><@spring.message code="sys.powerBank"></@spring.message></th>
			            <th width="6%">User's information</th>
			            <th width="4%">Borrow information</th>
			            <th width="4%">Return information</th>
			            <th width="10%">usage time</th>
			            <th width="4%">Use time</th>
			            <th width="6%">cost</th>
			            <th width="7%"><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            <th width="4%">Status</th>
			            <th width="8%">operation</th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <#-- 
				            <td> ${info.orderCode!''} </td>
				            <td> ${info.sellerName!''} </td>
				            -->
				             <td> ${info.pbCode!''}</td>
				            <td> 
				            	<#if info.nickname??>${info.nickname!''}<br/></#if>
				            	${info.mobile!}
				            </td>
				           
				            <td> 
				            	${info.borrowSysCode!''}<br/>
				            	Card slot：${info.borrowChannel!}
				            </td>
				            <td> 
				            	${info.returnSysCode!''}<br/>
				            	Card slot：${info.returnChannel!}
				            </td>
				           	<td>
				           		${(info.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}<br/>
				           		${(info.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}
				           	</td>
				           	<td> ${info.rideTime!''} min</td>
				           	<td> ${info.currency!} ${info.price!''} </td>
				           	<td>
				           		<#if info.payType?? && info.payType == 'pb_buy'>
									Deposit Deducted
								<#elseif info.payType ?? && info.payType == 'free'>
									free
								<#elseif info.payType ?? && info.payType == 'vip'>
									vip
								<#elseif info.payType ?? && info.payType == 'balance'>
									 balance ${info.payPrice!}
										<#if info.ticketAmount??>
										<br/>
										<@spring.message code="marketing.coupon"></@spring.message>:${info.ticketAmount!}
										</#if>
								<#elseif info.payType ?? && info.payType == 'cash'>
									<#if info.paymentMark ??>
										<#if info.ticketAmount ?? && info.price ??>
											<#if info.price lte info.ticketAmount>
												 ${info.ticketName! ''} pay ${info.currenct!}  ${info.ticketAmount! 0} </br>
											<#else>
												<#if info.paymentMark != "alipay_app">
													WeChat Pay ${info.currenct!} ${info.price - info.ticketAmount} ${info.currency!}
												<#else>
													Alipay Pay ${info.currenct!} ${info.price - info.ticketAmount} 
												</#if>
												
												${info.ticketName! ''}Pay  ${info.currenct!} ${info.ticketAmount! 0}</br>
											</#if>
										<#else>
											<#if info.paymentMark != "alipay_app">
												WeChat Pay ${info.currenct!} ${info.price!} 
											<#else>
												Alipay Pay ${info.currenct!} ${info.price!} 
											</#if>
										</#if>
									<#else>
										${info.paymentMark!}
									</#if>
								<#else>
									${info.payType!}
								</#if>
				           	</td>
				           	
				           	<td>
				           		<#if info.status ?? && info.status == 10>
				            		Unlocking
				            	<#elseif info.status ?? &&  info.status == 20>
				            		Unlock failed
				            	<#elseif info.status ?? &&  info.status == 30>
				            		<@spring.message code="sys.using"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 40>
				            		unpaid
				            	<#elseif info.status ?? &&  info.status == 50>
				            		Paid
				            	<#else>
				            		
				            	</#if>
				           	</td>
				           <td>
			            		<a href="${ctx}/order/detail?id=${info.id!}"><@spring.message code="stats.details"></@spring.message></a>
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					No records!！
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	