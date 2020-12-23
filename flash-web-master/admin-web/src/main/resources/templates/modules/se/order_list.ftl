<#include "/include/taglib.ftl" >
<html>
<head>
	<title>订单列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center;
	  vertical-align: middle !important;
	}
	</style>	
	<script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function showEndDialog(id) {
	    	
	    	top.$.jBox.open("iframe:${ctx}/sys/order/endForm?id="+id, "<@spring.message code='order.endUse'></@spring.message>", 500, 250, {
	    	
	    		buttons:{"<@spring.message code='form.okay'></@spring.message>":"ok", "<@spring.message code='form.close'></@spring.message>":true}, submit:function(v, h, f){
	    		
						if (v=="ok"){
						
							var checkDesc = h.find("iframe").contents().find("#checkDesc").val();
							
							if(checkDesc == ""){
								top.$.jBox.tip("<@spring.message code='form.textareaPlaceHolder'></@spring.message>");
								return false;
							}
							
							if(checkDesc != "" && checkDesc.length < 200)
							{
								top.jBox.confirm('<@spring.message code="order.endUse.tips"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
					            if (v === 'ok') {
					                 $.ajax({
					                 	url:"${ctx}/sys/order/endTrip?id="+id+"&checkDesc="+checkDesc,
					                 	type:"POST",
					                 	success:function(data){
					                 		if(data == "0000")
					                 		{
					                 			top.$.jBox.close();
					                 			top.$.jBox.tip('<@spring.message code="order.endUse.success.tips"></@spring.message>');
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
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.order"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/order/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="nomo.nomoBoxId"></@spring.message>：</label>
					<@form.input path="pbCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label>
					<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<@spring.message code="order.mobile"></@spring.message>：
					</label>
					<@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="form.status"></@spring.message>:</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="30" <#if vo.status ?? && vo.status == 30>selected='selected'</#if>><@spring.message code="order.status30"></@spring.message></option>
            	 			<option value="40" <#if vo.status ?? && vo.status == 40>selected='selected'</#if>><@spring.message code="order.status40"></@spring.message></option>
            	 			<option value="50" <#if vo.status ?? && vo.status == 50>selected='selected'</#if>><@spring.message code="order.status50"></@spring.message></option>
            	 	</select>
            	 	&nbsp;&nbsp;
            	 	<br/></br/>
					<@spring.message code="order.payTime"></@spring.message>：
						<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
			    		<input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					<label><@spring.message code="nomo.borrowNomoBoxId"></@spring.message>：</label>
					<@form.input path="borrowSysCode" htmlEscape=false maxlength="10" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="nomo.returnNomoBoxId"></@spring.message>：</label>
					<@form.input path="returnSysCode" htmlEscape=false maxlength="10" class="form-control input-small input-inline"/>
					 &nbsp;&nbsp;
					 <br/></br/>
					 <label>Merchant:</label>
					<select name="borrowSellerId" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
		           			<#list sellers as g>
                	 			<option value="${g.uid!}" <#if vo.borrowSellerId ?? && vo.borrowSellerId == g.uid>selected='selected'</#if>>${g.sellerName!}</option>
                	 		</#list>
            	 	</select>
            	 	&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
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
			            <th width="4%"><@spring.message code="nomo.pb"></@spring.message></th>
			            <th width="6%"><@spring.message code="order.userInfo"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.borrowInfo"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.returnInfo"></@spring.message></th>
			            <th width="10%"><@spring.message code="order.beginAndEndTime"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.useTime"></@spring.message></th>
			            <th width="6%"><@spring.message code="order.orderAmount"></@spring.message></th>
			            <th width="7%"><@spring.message code="order.payType"></@spring.message></th>
			            <th width="4%"><@spring.message code="form.status"></@spring.message></th>
			            <th width="4%">Merchant</th>
			            <th width="8%"><@spring.message code="form.operation"></@spring.message></th>
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
				            	<@spring.message code="order.slot"></@spring.message>：${info.borrowChannel!}
				            </td>
				            <td> 
				            	${info.returnSysCode!''}<br/>
				            	<@spring.message code="order.slot"></@spring.message>：${info.returnChannel!}
				            </td>
				           	<td>
				           		${(info.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}<br/>
				           		${(info.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}
				           	</td>
				           	<td> ${info.rideTime!''} min</td>
				           	<td> ${info.currency!} ${info.price!''} </td>
				           	<td>
				           		<#if info.payType?? && info.payType == 'pb_buy'>
									<@spring.message code="order.pbBuy"></@spring.message>
								<#elseif info.payType ?? && info.payType == 'free'>
									<@spring.message code="order.free"></@spring.message>
								<#elseif info.payType ?? && info.payType == 'vip'>
									<@spring.message code="order.vip"></@spring.message>
								<#elseif info.payType ?? && info.payType == 'balance'>
									 <@spring.message code="order.balance"></@spring.message>:${info.payPrice!}
										<#if info.ticketAmount??>
										<br/>
										<@spring.message code="order.coupon"></@spring.message>:${info.ticketAmount!}
										</#if>
								<#elseif info.payType ?? && info.payType == 'cash'>
									<#if info.paymentMark ??>
										<#if info.ticketAmount ?? && info.price ??>
											<#if info.price lte info.ticketAmount>
												 ${info.ticketName! ''} <@spring.message code="order.pay"></@spring.message> ${info.currenct!}  ${info.ticketAmount! 0} </br>
											<#else>
												<#if info.paymentMark != "alipay_app">
													<@spring.message code="order.weChat"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${info.currenct!} ${info.price - info.ticketAmount} ${info.currency!}
												<#else>
													<@spring.message code="order.alipay"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${info.currenct!} ${info.price - info.ticketAmount} 
												</#if>
												
												${info.ticketName! ''} <@spring.message code="order.pay"></@spring.message>  ${info.currenct!} ${info.ticketAmount! 0}</br>
											</#if>
										<#else>
											<#if info.paymentMark != "alipay_app">
												<@spring.message code="order.weChat"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${info.currenct!} ${info.price!} 
											<#else>
												<@spring.message code="order.alipay"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${info.currenct!} ${info.price!} 
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
				            		<@spring.message code="order.status10"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 20>
				            		<@spring.message code="order.status20"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 30>
				            		<@spring.message code="order.status30"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 40>
				            		<@spring.message code="order.status40"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 50>
				            		<@spring.message code="order.status50"></@spring.message>
				            	<#else>
				            		
				            	</#if>
				           	</td>
				           	<td>${info.borrowSellerName!}</td>
				           <td>
		            			<@shiro.hasPermission name="sys:order:detail">
			            			<a href="${ctx}/sys/order/detail?id=${info.id!}"><@spring.message code="form.detail"></@spring.message></a></br>
		            			</@shiro.hasPermission>	
		            			
				            	<@shiro.hasPermission name="sys:order:endTrip">
			            			<#if info.status?? && info.status == 30>
										 <a  href="javascript:showEndDialog(${info.id!});"><@spring.message code="order.endUse"></@spring.message></a>
				            		</#if>
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