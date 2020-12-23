<#include "/include/taglib.ftl" >
<html>
<head>
	<title>维保人员信息管理</title>
	<script type="text/javascript">
	
		function disable(uid){
	    	 top.jBox.confirm('Are you sure you want to disable this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Disabling, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/repairer/startOrStop?status=2&uid="+uid,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, the operation is successful");
			                 		window.location.href="${ctx}/sys/repairer/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function enable(uid){
	    	 top.jBox.confirm('Do you want to enable this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Now working, please wait...');
	            	  $.ajax({
		                 	url:"${ctx}/sys/repairer/startOrStop?status=1&uid="+uid,
		                 	type:"POST",
		                 	success:function(data){
		                 		top.$.jBox.tip("Congratulations, the operation is successful");
		                 		window.location.href="${ctx}/sys/repairer/list";
		                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
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
                    <span><@spring.message code="userInfo.maintenance"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"/> </a>
	            </li>
	            <@shiro.hasPermission name="sys:repairer:modify">
	            <li>
					<a href="${ctx}/sys/repairer/modify"><@spring.message code="form.add"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/repairer/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<div>
						<label><@spring.message code="userInfo.maintenance.username"/>：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="marketing.userS.phoneNumber"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:
							<select id="status" name="status" class="select2 form-control input-small">
								<option value=''>All</opetion>
								<option value='1'  <#if vo.status ?? && vo.status == '1' >selected='selected'</#if>>normal</opetion>
								<option value='2' <#if vo.status ?? && vo.status == '2'>selected='selected'</#if>><@spring.message code="marketing.disabled"></@spring.message></option>
							<select>
						</label>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.country"/>：
							<select id="country" name="country" class="select2 form-control input-small">
								<option value=''><@spring.message code="all"/></opetion>
								<#list countries as country>
									<#if vo.country ?? && vo.country == country.nationCode>
										<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
									<#else>
										<option value="${country.nationCode}">${country.englishName!}</option>
									</#if>
								</#list>
							<select>
						</label>
						&nbsp;&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
		<@tags.message content=message! />
		 <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="5%"><@spring.message code="userInfo.maintenance.username"/></th>
						<th width="6%"><@spring.message code="marketing.userS.phoneNumber"></@spring.message></th>
			            <th width="5%"><@spring.message code="userInfo.member.country"/></th>
			            <th width="8%"><@spring.message code="userInfo.maintenance.company"/></th>
			            <th width="8%"><@spring.message code="userInfo.maintenance.office"/></th>
			            <th width="5%"><@spring.message code="form.status"/></th>
			            <th width="10%"><@spring.message code="nomo.createTime"></@spring.message></th>
			            <th width="12%"><@spring.message code="operation"/></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.name !''}</td>
				            <td>${info.mobile !''}</td>
				            <td>
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>				           	  	
				            </td>
				            <td>${info.companyName !''}</td>
				            <td>${info.officeName !''}</td>
				            <td>
				            	<#if info.status ?? && info.status == 1>
				            		normal
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="marketing.disabled"></@spring.message>
				            	<#else>
				            	
				            	</#if>
				            </td> 
				            
							<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							
							<td>
								<@shiro.hasPermission name="sys:repairer:view">
									 	<#if info.status == 1>
									 		<a  href="javascript:disable(${info.uid!});"><@spring.message code="marketing.disabled"></@spring.message></a>
									 	<#else>
									 		<a  href="javascript:enable(${info.uid!});">Enabled</a>
									 	</#if> 
			            		</@shiro.hasPermission>	
			            		
			            		<@shiro.hasPermission name="sys:repairer:modify">
									 <a  href="${ctx}/sys/repairer/modify?uid=${info.uid!}"><@spring.message code="form.edit"></@spring.message></a>&nbsp;
			            		</@shiro.hasPermission>
			            		
			            		<@shiro.hasPermission name="sys:repairer:detail">
									 <a  href="${ctx}/sys/repairer/detail?uid=${info.uid!}"><@spring.message code="form.detail"></@spring.message></a>&nbsp;
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
					<@spring.message code="noRecords"/>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>