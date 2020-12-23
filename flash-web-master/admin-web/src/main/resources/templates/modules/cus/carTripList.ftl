<#include "/include/taglib.ftl" >
<html>
<head>
	<title>车辆行程管理</title>
	<script type="text/javascript">
	    
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
				    <span>车辆信息管理<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>车辆管理</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li>
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0)">车辆行程</a>
	            </li>
	        </ul>
        	
        	<form id="showForm"  method="post" class="breadcrumb form-search">
					<label>车辆系统编号：</label>${lock.sysCode!''}
			</form>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/customer/trips?mark=car&bicycleId=${bicycleId!}" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					
					<label>用户昵称：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					
					行程开始时间：<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    		<input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
		    						    
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
			</@form.form>
				
          <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th>用户昵称</th>
			            <th>车辆编号</th>
			            <th>开始时间</th>
			            <th>结束时间</th>
			            <th>行程时长</th>
			            <th>行程距离</th>
			            <#if ipPayTrip?? && ipPayTrip>
			            <th>行程费用</th>
			            <th>支付方式</th>
			            </#if>
			            <th>状态</th>
			            <th>操作</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>${info.nickname!''}</td>
							<td>${info.sysCode!''}</td>
							<td>${(info.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							<td>${(info.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							
							<td>${info.rideTime !''} min</td>
							<td>${info.distance!''} 米</td>
							<#if ipPayTrip?? && ipPayTrip>
							
							<td>${info.price!''} 元</td>
							<td>
								<#if info.paymentMark ?? && info.paymentMark == "wx_js">
									<#if info.ticketAmount ??>
										<#if info.price lt info.ticketAmount>
											使用 ${info.ticketName! ''}支付 ${info.ticketAmount! 0} 元</br>
										<#else>
											使用 ${info.ticketName! ''}支付 ${info.ticketAmount! 0}元</br>
											<#if info.paymentMark != "alipay_app">
												WeChat支付${info.price - info.ticketAmount} 元
											<#else>
												Alipay支付${info.price - info.ticketAmount} 元
											</#if>
										</#if>
										
									<#else>
											<#if info.paymentMark != "alipay_app">
												WeChat支付${info.price - info.ticketAmount} 元
											<#else>
												Alipay支付${info.price - info.ticketAmount} 元
											</#if>
									</#if>
								<#else>
									<#if info.payType ?? && info.payType == 'vip'>
										会员卡
									</#if>
								</#if>
								
							</#if>
				            
				            <td>
				            	<#if info.status ?? && info.status == 10>
				            		开锁中
				            	<#elseif info.status ?? &&  info.status == 20>
				            		开锁失败
				            	<#elseif info.status ?? &&  info.status == 30>
				            		行驶中
				            	<#elseif info.status ?? &&  info.status == 40>
				            		未支付
				            	<#elseif info.status ?? &&  info.status == 50>
				            		已支付
				            	<#else>
				            		
				            	</#if>
							</td>
		            		
		            		<@shiro.hasPermission name="sys:bicycle:modify">
								 <td>
								 	<a  href="${ctx}/sys/trip/tripDetails?mark=${mark!}&id=${info.id!}"><@spring.message code="form.detail"></@spring.message></a>
								 	<!--
								 		<a  href="${ctx}/sys/bicycle/modify?id=${info.id!}">结束行程</a>&nbsp; 
								 	-->
					            </td>
		            		</@shiro.hasPermission>	
		            		
						</tr>
					</#list>
				</table>
				 <div class="pagination">
			        ${page}
			    </div>
		    <#else>
				<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
			</#if>
			</div>
        </div>
    </div>
</body>
</html>