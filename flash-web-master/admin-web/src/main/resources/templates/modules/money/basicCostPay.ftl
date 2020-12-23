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
				    <span>Financial Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>VIp passes record</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">VIp passes record</a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/basicCost/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
					
						<label>Order code：</label><@form.input path="payOrderCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label>Business order：</label><@form.input path="outOrderId" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<br/><br/>
						<label>User's phone：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<label><@spring.message code="sys.paymentTypes"></@spring.message>：
							<select id="paymentMark" name="paymentMark" class="select2 form-control input-small">
								<option value=''  ><@spring.message code="stats.total"></@spring.message></opetion>
								<option value='wx_js' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_js'>selected='selected'</#if>>WeChat applet</option>
								<option value='stripe' <#if vo.paymentMark ?? && vo.paymentMark == 'stripe'>selected='selected'</#if>>stripe</option>
							<select>
						</label>
						&nbsp;&nbsp;
						Payment time：<input type="text" readonly="readonly" id="startTime" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
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
						
						<th width="12%">User's infomation</th>
						
						<th width="8%">Order code</th>
			            <th width="5%">Business order</th>
			            <th width="6%"><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            
			            <th width="6%">Amount</th>
			            <th width="12%">Pay time</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>
				            	${info.nickname!} ${info.mobile!}
				            </td>
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
				            
				            <td> ${info.currency!} ${info.amount!}</td>
				            
				            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
				            
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