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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"/></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.manage"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.top.record"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/recharge/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.phone"/>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:
							<select id="status" name="status" class="select2 form-control input-small">
								<option value='' ><@spring.message code="all"/></opetion>
								<option value='1' <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>Purchased</option>
								<option value='2' <#if vo.status ?? && vo.status == 2>selected='selected'</#if>>Purchased Successfully</option>
							<select>
						</label>
						&nbsp;&nbsp;
						<br/><br/>
						<@spring.message code="userInfo.member.vip.payTime"/>：<input type="text" readonly="readonly" name="startTime" value="${(vo.startTime)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
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
			            <th width="6%"><@spring.message code="marketing.userS.nickname"></@spring.message></th>
			            <th width="4%"><@spring.message code="userInfo.member.phone"/></th>
			            <th width="4%"><@spring.message code="userInfo.member.top.serial"/></th>
			            <th width="4%"><@spring.message code="userInfo.member.vip.amount"/></th>
			            <th width="4%"><@spring.message code="sys.paymentTypes"/></th>
			            <th width="4%"><@spring.message code="form.status"/></th>
			            <th width="10%"><@spring.message code="userInfo.member.vip.payTime"/></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td>${info.nickname!''}</td>
				            <td>${info.mobile!''}</td>
				            <td>${info.sn!''}</td>
				            <td>${info.currency!} ${info.amount!0}</td>
				            <td>${info.paymentType!}</td>
				            <td>
				            	<#if info.status ?? && info.status == 1>
				            		Purchased
				            	<#elseif info.status?? && info.status == 2>
				            		Purchased Successfully
				            	<#else>
				            		${info.status!}
				            	</#if>
				            </td>
				            
				            <td>${(info.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords"/>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>