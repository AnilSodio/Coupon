<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="marketing.activityManagement"></@spring.message></title>
	
	<script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		 
    	function abnormalExport() {
    	
			var startTimeString = $("#startTimeString").val();
			var endTimeString = $("#endTimeString").val();
			
			if(startTimeString == "" && endTimeString == ""){
				 top.$.jBox.tip("发放时间不可为空，please choose！");
				 return;
			}
			
			document.getElementById("searchForm").action="${ctx}/sys/pro/findCouponToExport";
    		$("#searchForm").submit();
    		document.getElementById("searchForm").action="${ctx}/sys/pro/findCouponList";
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
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
				 <li>
				    <span><@spring.message code="marketing.couponSendList"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            </li>
	        </ul>
        	
        	
			<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/pro/findCouponList" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
				<label>
					&nbsp;&nbsp;<@spring.message code="marketing.userS.nickname"></@spring.message>:
				</label>
				<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
				<label>
					&nbsp;&nbsp;<@spring.message code="marketing.userS.phoneNumber"></@spring.message>:
				</label>
				<@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label>
					&nbsp;&nbsp;<@spring.message code="marketing.statusOfUse"></@spring.message>:
				</label>
				<select id="status" name="status" class="select2 form-control input-small">
					<option value="">all</option>
					<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>><@spring.message code="marketing.unused"></@spring.message></option>
					<option value="2" <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="marketing.alreadyUsed"></@spring.message></option>
	             </select>
	             <br/></br>
	             &nbsp;&nbsp;
							<@spring.message code="marketing.sendTime"></@spring.message>：<input type="text" readonly="readonly" id="startTimeString" name="startTimeString" value="${(vo.startTimeString)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    						    <input type="text" readonly="readonly" id="endTimeString" name="endTimeString" value="${(vo.endTimeString)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
						&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnSubmit" class="btn btn-primary " type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				
				<#-- 
					<#if (page.content?size > 0)>
						 <@shiro.hasPermission name="sys:coupon:list">
							<a class="btn btn-primary" href="javascript:abnormalExport();">导出</a>&nbsp;
		                </@shiro.hasPermission>
					</#if>
				-->
						
			</@form.form>
         	<#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="12%"><@spring.message code="marketing.typeOfActivity"></@spring.message></th>
			            <th width="12%"><@spring.message code="marketing.userS.nickname"></@spring.message></th>
			            <th width="10%"><@spring.message code="marketing.userS.phoneNumber"></@spring.message></th>
			            <th width="6%">Amount</th>
			            <th width="6%">Status</th>
			            <th width="14%"><@spring.message code="marketing.expiration"></@spring.message></th>
			            <th width="14%"><@spring.message code="marketing.sendTime"></@spring.message></th>
					</tr>
					<#list page.content as info>
					<tr>
						<td>${info.source !''}</td>
						<td>${info.nickname !''}</td>
						<td>${info.mobile !''}</td>
						<td>${info.currency!} ${info.amount !''}</td>
						<td>${info.percentageOfDiscount !''}</td>
						<td>
						<#if info.status ?? && info.status ==1>
							<@spring.message code="marketing.unused"></@spring.message>
						<#elseif info.status ?? && info.status==2>
							<@spring.message code="marketing.alreadyUsed"></@spring.message>
						<#else>
						</#if>
						</td>
						<td>${(info.expireDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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