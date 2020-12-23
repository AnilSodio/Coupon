<#include "/include/taglib.ftl" >
<html>
<head>
	<title>订单信息管理</title>
	
	<script type="text/javascript">
		
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
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	             
	            <li >
	                <a  href="${ctx}/sys/order/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/order/save" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.info"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.orderId"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.orderCode!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.userInfo"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.nickname!}/${info.mobile!} </p>
						</div>
					</div>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.time"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.borrowTime"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${(trip.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.returnTime"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${(trip.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.time"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${trip.rideTime!0} min
							</p>
						</div>
					</div>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.borrowInfo"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.busiName"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static"><#if borrowSeller??>${borrowSeller.sellerName!}</#if></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.borrowNomoBoxId"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.borrowSysCode!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.slot"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.borrowChannel!}</p>
						</div>
					</div>
					
					<#if trip.payType?? && trip.payType != 'pb_buy'>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.returnInfo"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.sellerName"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static"><#if returnSeller??>${returnSeller.sellerName!}</#if></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.returnNomoBoxId"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.returnSysCode!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.slot"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.returnChannel!}</p>
						</div>
					</div>
					
					</#if>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.payment"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.orderAmount"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								 ${trip.currency!} ${trip.price!}
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.payTime"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								 ${(trip.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.paymentMethod"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<#if trip.payType?? && trip.payType == 'pb_buy'>
									<@spring.message code="order.pbBuy"></@spring.message>
								<#elseif trip.payType ?? && trip.payType == 'vip'>
									<@spring.message code="order.free"></@spring.message>
								<#elseif trip.payType ?? && trip.payType == 'vip'>
									<@spring.message code="order.vip"></@spring.message>
								<#elseif trip.payType ?? && trip.payType == 'balance'>
									 <@spring.message code="order.balance"></@spring.message>
								<#elseif trip.payType ?? && trip.payType == 'stripe'>
									stripe
								<#elseif trip.payType ?? && trip.payType == 'cash'>
									<#if trip.paymentMark ??>
										<#if trip.ticketAmount ?? && trip.price ??>
											<#if trip.price lte trip.ticketAmount>
												 ${trip.ticketName! ''} Pay ${trip.currency!} ${trip.ticketAmount! 0} </br>
											<#else>
												<#if info.paymentMark != "alipay_app">
													<@spring.message code="order.weChat"></@spring.message> <@spring.message code="order.pay"></@spring.message>  ${trip.currency!} ${trip.price - trip.ticketAmount}
												<#else>
													<@spring.message code="order.alipay"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${trip.currency!} ${trip.price - trip.ticketAmount}
												</#if>
												
												${trip.ticketName! ''} <@spring.message code="order.pay"></@spring.message> ${trip.currency!} ${trip.ticketAmount! 0} </br>
											</#if>
										<#else>
											<#if trip.paymentMark != "alipay_app">
												<@spring.message code="order.weChat"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${trip.currency!} ${trip.price!}
											<#else>
												<@spring.message code="order.alipay"></@spring.message> <@spring.message code="order.pay"></@spring.message> ${trip.currency!} ${trip.price!}
											</#if>
										</#if>
									<#else>
										${trip.paymentMark!}
									</#if>
								<#else>
									${trip.payType!}
								</#if>
							</p>
						</div>
					</div>
					
					
					<#if trip.checkName ??>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="order.review"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="order.review"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<@spring.message code="order.reviewer"></@spring.message>:${trip.checkName!}
								<@spring.message code="order.reviewTime"></@spring.message>:${(trip.checkTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					</#if>
					
			    	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="stats.feedback"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					<#if feed_list??>
			    	
			    		<#if (feed_list?size > 0)>
				    	 
			    	 	<div class="orderhh"><@spring.message code="role.feedback"></@spring.message></div>
				        <div class="portlet-body">
				            <div id="sample_4_wrapper" class="dataTables_wrapper">
							<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
							            <th><@spring.message code="feedback.type"></@spring.message></th>
							            <th><@spring.message code="feedback.type.name"></@spring.message></th>
							            <th><@spring.message code="feedback.desc"></@spring.message></th>
							            <th><@spring.message code="form.status"></@spring.message></th>
							            <th><@spring.message code="feedback.feedTime"></@spring.message></th>
							            <th><@spring.message code="sys.remarks"></@spring.message></th>
							            <th><@spring.message code="form.operation"></@spring.message></th>
									</tr>
									<#list feed_list as info>
										<tr>
								            <td>
								            	<#if info.feedType ?? && info.feedType == "1">
								            		<@spring.message code="feedback.home"></@spring.message>
								            	<#elseif info.feedType ?? && info.feedType == "2">
								            		<@spring.message code="sys.using"></@spring.message>
								            	<#elseif info.feedType ?? && info.feedType == "3">
								            		<@spring.message code="stats.completed"></@spring.message>
								            	<#else>
								            	
								            	</#if>
								            </td> 
								            <td>${info.typeName!''}</td>
								            <td>${info.feedDesc!''}</td>
								            
								            <td>
								            	
								            	<#if info.status ?? && info.status == 0>
								            		<@spring.message code="feedback.processed"></@spring.message>
								            	<#elseif info.status ?? && info.status == 1>
								            		<@spring.message code="feedback.noProcessed"></@spring.message>
								            	<#elseif info.status ?? && info.status == 2>
								            		<@spring.message code="feedback.noNeedProcess"></@spring.message>
								            	<#else>
								            	
								            	</#if>
								            </td> 
								            
											<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
											
											 <td>${info.remark !''}</td>
											 
											<@shiro.hasPermission name="sys:feed:modify">
												 <td>
												 	<#if info.status == 1>
												 		<a  href="${ctx}/sys/feed/modify?id=${info.id!}"><@spring.message code="feedback.deal"></@spring.message></a>&nbsp;
												 	<#else>
												 		<a  href="${ctx}/sys/feed/queryDetail?mark=${mark!}&id=${info.id!}"><@spring.message code="form.detail"></@spring.message></a>&nbsp;
												 	</#if> 
									            </td>
						            		</@shiro.hasPermission>	
						            		
										</tr>
									</#list>
								</table>
							</div>
						</div>
				    <#else>
						<div class="note note-warning alert">
							<@spring.message code="form.noData"></@spring.message>
						</div>
					</#if>
			    <#else>
			    </#if>
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>