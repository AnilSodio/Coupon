<#include "/include/taglib.ftl" >
<html>
<head>
	<title>Deposit management</title>
	    
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
				    <span><@spring.message code="financial.manage" /><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="financial.deposit.record" /></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="financial.deposit.record" /></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/money/log_list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
					
						<label><@spring.message code="userInfo.member.vip.orderCode" />：</label><@form.input path="payOrderCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="financial.deposit.business.order" />：</label><@form.input path="outOrderId" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="financial.deposit.refund.number" />：</label><@form.input path="refundId" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<br/>
						<br/>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.phone" />：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:
							<select id="status" name="status" class="select2 form-control input-small">
								<option value='' >All</opetion>
								<option value='2' <#if vo.status ?? && vo.status == 2>selected='selected'</#if>>payment successful</option>
								<option value='3' <#if vo.status ?? && vo.status == 3>selected='selected'</#if>>Refund review</option>
								<option value='4' <#if vo.status ?? && vo.status == 4>selected='selected'</#if>>refunded</option>
							<select>
						</label>
						
						<br/>
						<br/>
						<label><@spring.message code="sys.paymentTypes"></@spring.message>：
							<select id="paymentMark" name="paymentMark" class="select2 form-control input-small">
								<option value=''  ><@spring.message code="stats.total"></@spring.message></opetion>
								<option value='alipay_app'  <#if vo.paymentMark ?? && vo.paymentMark == 'alipay_app' >selected='selected'</#if>>Alipay</opetion>
								<option value='wx_app' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_app'>selected='selected'</#if>>WeChat</option>
								<option value='wx_js' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_js'>selected='selected'</#if>>WeChat applet</option>
								<option value='stripe' <#if vo.paymentMark ?? && vo.paymentMark == 'stripe'>selected='selected'</#if>>stripe</option>
							<select>
						</label>
						&nbsp;&nbsp;
						<@spring.message code="userInfo.member.vip.payTime" />：<input type="text" readonly="readonly" id="startTime" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    						    <input type="text" readonly="readonly" id="endTime" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
						&nbsp;&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						
						<th width="8%"><@spring.message code="userInfo.member.vip.orderCode" /></th>
			            <th width="5%"><@spring.message code="financial.deposit.business.order" /></th>
			            <th width="6%"><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            <th width="12%"><@spring.message code="userInfo.member.userInfo" /></th>
			            <th width="6%"><@spring.message code="marketing.amount"></@spring.message></th>
			            <th width="12%"><@spring.message code="userInfo.member.vip.payTime" /></th>
			            <th width="6%"><@spring.message code="form.status" /></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>
					          ${info.payOrderCode!}
				            </td>
				            <td>
					          ${info.outOrderId!}
				            </td>
				            <td>
			            		<#if info.paymentMark?? && info.paymentMark == "alipay_app">
			            			Alipay
			            		<#elseif info.paymentMark?? && info.paymentMark == "wx_js">
			            			WeChat applet
			            		<#elseif info.paymentMark?? && info.paymentMark == "wx_app">
			            			WeChat
			            		<#else>
			            			${info.paymentMark!}
			            		</#if>
				            </td>
				            
				            <td>
				            	${info.nickname!} ${info.mobile!}
				            </td>
				            
				            <td> ${info.currency!} ${info.amount!}</td>
				            
				            <td>${(info.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
				            <td>
				            	
				            	<#if info.status ?? && info.status==1 >
				            		Payments
				            	<#elseif info.status?? && info.status == 2>
				            		payment successful
				            	<#elseif info.status?? && info.status == 3>
				            		Refund review
				            	<#elseif info.status?? && info.status == 4>
				            		refunded
				            		${info.refundId!}
				            	<#else>
				            		${info.status!}
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
					<@spring.message code="noRecords" />
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>