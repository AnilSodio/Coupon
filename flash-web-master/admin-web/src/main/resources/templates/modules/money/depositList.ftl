<#include "/include/taglib.ftl" >
<html>
<head>
	<title>押金列表管理</title>
	    
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
                    <span>Deposit management</span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	       		<li>
					 <a  href="${ctx}/sys/money/list"><@spring.message code="form.list"></@spring.message> </a>
	            </li>
		            
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/money/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label>${info.nickname !} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label><@spring.message code="marketing.userS.phoneNumber"></@spring.message>：</label>${info.mobile !} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<#-- 
						<#if (page.content?size > 0)>
							&nbsp;&nbsp;&nbsp;<a class="btn btn-primary" href="${ctx}/sys/money/detailExport?uid=${info.uid!}">导出</a>&nbsp;
						</#if>
						-->
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
			            <th>Deposit type</th>
			            <th><@spring.message code="sys.paymentTypes"></@spring.message></th>
			            <th><@spring.message code="marketing.amount"></@spring.message></th>
			            <th><@spring.message code="stats.times"></@spring.message></th>
					</tr>
					<#list page.content as log>
						<tr>
				            <td>
				            	<#if log.type?? >
				            		<#if log.type =="1">
				            			Pay
				            		<#elseif log.type == "2">
				            			Pending review
				            		<#elseif log.type == "3">
				            			refunded
				            		<#elseif log.type == "4">
				            			扣除
				            		<#else>
				            			${log.type!}
				            		</#if>
				            	</#if>
				            </td>
				            <td>
				            	<#if log.payMark?? && log.payMark == "wx_js">
				            		WeChat Applet
				            	<#elseif log.payMark?? && log.payMark == "wx_app">
				            		WeChat
				            	<#elseif log.payMark?? && log.payMark == "alipay_app">
				            		Alipay
				            	<#elseif log.payMark?? && log.payMark == "stripe">
				            		stripe
				            	<#elseif log.payMark?? && log.payMark == "pb_buy">
				            		订单抵扣
				            	<#else>
				            		${log.payMark!}
				            	</#if>
				            </td>
				           <td> ${info.currency!} ${log.amount!} </td>
				           <td>${(log.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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