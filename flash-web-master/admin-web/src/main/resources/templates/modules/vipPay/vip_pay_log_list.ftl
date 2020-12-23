<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
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
                    <span><@spring.message code="financial.vip.passRecord" /></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/vipPay/log_list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="role.order" /> ：</label><@form.input path="payOrderCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="financial.deposit.business.order" /> ：</label><@form.input path="outOrderId" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.phone" />：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<br/>
						<br/>
						<label><@spring.message code="sys.paymentTypes"></@spring.message>:
							<select id="paymentMark" name="paymentMark" class="select2 form-control input-small">
								<option value=''  >All</opetion>
								<option value='alipay_app'  <#if vo.paymentMark ?? && vo.paymentMark == 'alipay_app' >selected='selected'</#if>>Alipay</opetion>
								<option value='wx_app' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_app'>selected='selected'</#if>>WeChat</option>
								<option value='wx_js' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_js'>selected='selected'</#if>>WeChat applet</option>
								<option value='stripe' <#if vo.paymentMark ?? && vo.paymentMark == 'stripe'>selected='selected'</#if>>stripe</option>
							<select>
						</label>
					 	&nbsp;&nbsp;
						<@spring.message code="userInfo.member.vip.payTime" />：<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    						<input type="text" readonly="readonly" name="endTime" value="${(vo.endTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
						
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
			            <th width="5%"><@spring.message code="role.order" /></th>
			            <th width="8%"><@spring.message code="financial.deposit.business.order" /></th>
			            <th width="4%"><@spring.message code="userInfo.member.vip.amount" /></th>
			            <th width="4%"><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            <th width="10%"><@spring.message code="userInfo.member.userInfo" /></th>
			            <th width="10%"><@spring.message code="userInfo.member.vip.payTime" /></th>
			            
					</tr>
					<#list page.content as info>
						<tr>
				            <td>${info.payOrderCode!''}</td>
				            <td>${info.outOrderId!''}</td>
				            
				            <td> ${info.currency!} ${info.amount!0} </td>
				            
				            <td>
				            	<#if info.paymentMark?? && info.paymentMark == 'wx_js'>
									WeChat applet
								<#elseif info.paymentMark ?? && info.paymentMark == 'wx_app'>
									WeChat
								<#elseif info.paymentMark ?? && info.paymentMark == 'alipay_app'>
									Alipay
								<#else>
									${info.paymentMark!}
								</#if>
				            </td>
				            
				             <td>${info.nickname!} ${info.mobile!}</td>
				            
				            <td>${(info.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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