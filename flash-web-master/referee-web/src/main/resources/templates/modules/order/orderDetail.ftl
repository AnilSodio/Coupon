<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
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
                    <span>Use management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="stats.details"></@spring.message></span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	             
	            <li >
	                <a  href="${ctx}/order/list">List</a>
	            </li>
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="stats.details"></@spring.message></a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/order/save" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">order information</span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Order Number:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.orderCode!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">User nickname and phone:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.nickname!}/${info.mobile!} </p>
						</div>
					</div>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Time related</span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Borrow time:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${(trip.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">return time:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${(trip.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Order duration:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${trip.rideTime!0} min
							</p>
						</div>
					</div>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Borrow the information</span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Business Name:</label>
						<div class="col-md-4">
							<p class="form-control-static"><#if borrowSeller??>${borrowSeller.sellerName!}</#if></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">NOMO box code:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.borrowSysCode!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Card slot number:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.borrowChannel!}</p>
						</div>
					</div>
					
					<#if trip.payType?? && trip.payType != 'pb_buy'>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Return information</span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Business Name:</label>
						<div class="col-md-4">
							<p class="form-control-static"><#if returnSeller??>${returnSeller.sellerName!}</#if></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">NOMO box code:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.returnSysCode!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Card slot number:</label>
						<div class="col-md-4">
							<p class="form-control-static">${trip.returnChannel!}</p>
						</div>
					</div>
					
					</#if>
					
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Payment related</span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Order fee:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								 ${trip.currency!} ${trip.price!}
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Payment time:</label>
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
									押金抵扣   订单金额 ${trip.price!} 支付的押金金额${trip.payPrice!}
								<#elseif trip.payType ?? && trip.payType == 'vip'>
									free
								<#elseif trip.payType ?? && trip.payType == 'vip'>
									vip
								<#elseif trip.payType ?? && trip.payType == 'balance'>
									balance
								<#elseif trip.payType ?? && trip.payType == 'stripe'>
									stripe
								<#elseif trip.payType ?? && trip.payType == 'cash'>
									<#if trip.paymentMark ??>
										<#if trip.ticketAmount ?? && trip.price ??>
											<#if trip.price lte trip.ticketAmount>
												 ${trip.ticketName! ''} Pay ${trip.currency!} ${trip.ticketAmount! 0} </br>
											<#else>
												<#if info.paymentMark != "alipay_app">
													WeChat Pay ${trip.currency!} ${trip.price - trip.ticketAmount}
												<#else>
													Alipay Pay ${trip.currency!} ${trip.price - trip.ticketAmount}
												</#if>
												
												${trip.ticketName! ''} Pay ${trip.currency!} ${trip.ticketAmount! 0} </br>
											</#if>
										<#else>
											<#if trip.paymentMark != "alipay_app">
												WeChat Pay ${trip.currency!} ${trip.price!}
											<#else>
												Alipay Pay ${trip.currency!} ${trip.price!}
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
					      <span class="caption-subject font-green bold">udit information</span>
					    </div>
					  </div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">udit information:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								Auditor:${trip.checkName!}
								Audit time:${(trip.checkTime?string('yyyy-MM-dd HH:mm:ss'))!''}
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
				    	 
			    	 	<div class="orderhh">Complaints</div>
				        <div class="portlet-body">
				            <div id="sample_4_wrapper" class="dataTables_wrapper">
							<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
							            <th>question type</th>
							            <th>Question name</th>
							            <th>Description</th>
							            <th>Status</th>
							            <th>Feedback time</th>
							            <th><@spring.message code="sys.remarks"></@spring.message></th>
							            <th>operation</th>
									</tr>
									<#list feed_list as info>
										<tr>
								            <td>
								            	<#if info.feedType ?? && info.feedType == "1">
								            		<@spring.message code="home"></@spring.message>
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
								            		Processed
								            	<#elseif info.status ?? && info.status == 1>
								            		Not processed
								            	<#elseif info.status ?? && info.status == 2>
								            		No need to deal with
								            	<#else>
								            	
								            	</#if>
								            </td> 
								            
											<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
											
											 <td>${info.remark !''}</td>
											 
											<@shiro.hasPermission name="sys:feed:modify">
												 <td>
												 	<#if info.status == 1>
												 		<a  href="${ctx}/sys/feed/modify?id=${info.id!}">deal with</a>&nbsp;
												 	<#else>
												 		<a  href="${ctx}/sys/feed/queryDetail?mark=${mark!}&id=${info.id!}"><@spring.message code="stats.details"></@spring.message></a>&nbsp;
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
							No records!
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