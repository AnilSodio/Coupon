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
	    
	    function showCheckDialog(id)
	    {
	    	// 正常打开	
			top.$.jBox.open("iframe:${ctx}/sys/money/refundForm?id="+id, "Refund audit", 500, 250, {
				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
						if (v=="ok"){
							var url = "${ctx}/sys/money/refund?id=${id!}";
							var checkDesc = h.find("iframe").contents().find("#checkDesc").val();
							var result = h.find("iframe").contents().find("#result").val();
							if(result == "")
							{
								top.$.jBox.tip("Please select the audit result");
								return false;
							}
							if(checkDesc == "")
							{
								top.$.jBox.tip("Please enter the note information");
								return false;
							}
							
							if(checkDesc != "" && checkDesc.length < 200)
							{
								top.jBox.confirm('Confirm review of the record？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
					            if (v === 'ok') {
					                 $.ajax({
					                 	url:"${ctx}/sys/money/refund?id="+id+"&refundRemark="+checkDesc+"&result="+result,
					                 	type:"POST",
					                 	success:function(data){
					                 		if(data == "1")
					                 		{
					                 			top.$.jBox.close();
					                 			top.$.jBox.tip("Congratulations, the refund operation was successful");
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
				    <span><@spring.message code="financial.manage" /><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="financial.deposit.refund" /></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/money/check" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="userInfo.member.nickname" />：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<label><@spring.message code="userInfo.member.phone" />：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:
							<select id="status" name="status" class="select2 form-control input-small">
								<option value='' ><@spring.message code="all" /></opetion>
								<option value='3' <#if vo.status ?? && vo.status == 3>selected='selected'</#if>>Pending review</option>
								<option value='4' <#if vo.status ?? && vo.status == 4>selected='selected'</#if>>refunded</option>
							<select>
						</label>
						<br/></br/>
						<label><@spring.message code="sys.paymentTypes"></@spring.message>：
							<select id="paymentMark" name="paymentMark" class="select2 form-control input-small">
								<option value=''  ><@spring.message code="stats.total"></@spring.message></opetion>
								<option value='alipay_app'  <#if vo.paymentMark ?? && vo.paymentMark == 'alipay_app' >selected='selected'</#if>>Alipay</opetion>
								<option value='wx_app' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_app'>selected='selected'</#if>>WeChat</option>
								<option value='wx_js' <#if vo.paymentMark ?? && vo.paymentMark == 'wx_js'>selected='selected'</#if>>WeChat applet</option>
								<option value='stripe' <#if vo.paymentMark ?? && vo.paymentMark == 'stripe'>selected='selected'</#if>>stripe</option>
							<select>
						</label>
						
						&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%"><@spring.message code="userInfo.member.information" /></th>
			            <th width="5%"><@spring.message code="marketing.amount"></@spring.message></th>
			            <th width="6%"><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            <th width="12%"><@spring.message code="financial.deposit.applyTime"/></th>
			            <th width="6%"><@spring.message code="form.status"/></th>
			            <th width="8%"><@spring.message code="financial.deposit.refund.account" /></th>
			            <th width="10%"><@spring.message code="financial.deposit.refund.number" /></th>
			            <th width="4%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>
					            ${info.nickname!''} <br/> 
					            ${info.mobile!''}
				            </td>
				            <td> ${info.currency!} ${info.amount!0}</td>
				            <td>
				            	<#if info.paymentMark ??>
				            		<#if info.paymentMark == "alipay_app">
				            			Alipay
				            		<#elseif info.paymentMark == "wx_js">
				            			WeChat applet
				            		<#elseif info.paymentMark == "wx_app">
				            			WeChat
				            		<#else>
				            			${info.paymentMark!}
				            		</#if>
				            	<#else>
				            	</#if>
				            </td>
				            <td>${(info.refundApplyTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
				            <td>
				            	<#if info.status ?? && info.status==3 >
				            		Pending review
				            	<#elseif info.status?? && info.status == 4>
				            		refunded
				            	<#else>
				            		${info.status!}
				            	</#if>
				            </td>
				             <td>
				             	<#if info.refundAccount?? && info.refundAccount == "REFUND_SOURCE_UNSETTLED_FUNDS">
				             		Unsettled funds
				             	<#elseif info.refundAccount?? && info.refundAccount == "REFUND_SOURCE_RECHARGE_FUNDS">
				             		Available Balance
				             	<#else>
				             		${info.refundAccount!}
				             	</#if>
				             </td>
				            <td>${info.refundId!}</td>
				            
							<td>
							<@shiro.hasPermission name="sys:money:refund">
								 	 
								 	 <#if info.status ?? && info.status == 3>
								 	 	<#if info.paymentMark ?? && info.paymentMark == "alipay_app">
								 	 		<a  href="javascript:showCheckDialog(${info.id!});">Audit</a>
								 	 	<#elseif info.paymentMark ?? && info.paymentMark == "stripe">
								 	 		<a  href="javascript:showCheckDialog(${info.id!});">Audit</a>
								 	 	<#else>
								 	 		<a  href="${ctx}/sys/money/checkRedirect?id=${info.id!}">Audit</a>
								 	 	</#if>
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
					<@spring.message code="noRecords" />
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>