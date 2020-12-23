<#include "/include/taglib.ftl" >
<html>
<head>
	<title>推荐人列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>	
	<script type="text/javascript">
	    function disable(uid){
	    	 top.jBox.confirm('Are you sure you want to disable this introducer?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Disabling, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/referee/disAndEnable?status=2&uid="+uid,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, disable the success");
			                 		window.location.href="${ctx}/sys/referee/list";
			                 	}
			          	 });
	            }
	            return true;
 			  }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function enable(uid){
	    	 top.jBox.confirm('Are you sure you want to restore this introducer?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Recovering, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/referee/disAndEnable?status=1&uid="+uid,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, enable success");
			                 		window.location.href="${ctx}/sys/referee/list";
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
	    
	    
		function authCheck(uid){
		
			top.$.jBox.open("iframe:${ctx}/sys/customer/memberCheckForm", "认证审核", 600, 400, {
	    	
	    		buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
                	
                	if (v=="ok"){
                	
		                var authApplyStatus = h.find("iframe").contents().find("#authApplyStatus").val();
		                var authApplyRemark = h.find("iframe").contents().find("#authApplyRemark").val();
		                
		                if(authApplyStatus == ""){
							top.$.jBox.tip("please choose结果");
							return false;
						}
						
						if(authApplyRemark== ""){
							top.$.jBox.tip("请输入备注");
							return false;
						}
						
						top.jBox.confirm('确认要审核吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	        				if (v === 'ok') {
	        					loading('正在审核，请稍等...');
	             				$.ajax({
	             				url:"${ctx}/sys/customer/memberCheck",
	             				data:{
	             					"uid":uid,
	             					"authApplyStatus":authApplyStatus,
	             					"authApplyRemark":authApplyRemark
	             				},
	             				type:"POST",
	             					success:function(data){
	                 					if(1 == data) {
	                 						top.$.jBox.tip("恭喜，操作成功");
	                 						window.location.href="${ctx}/sys/customer/list";
	                 					}else{
	                 						top.$.jBox.tip("很抱歉，操作失误");
	                 					}
	             					}
	             				});
	        				}
	        				return true;
		 				});
		 			 }		
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"/></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
				    <span><@spring.message code="role.introducer"/></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active"><a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:referee:modify">
            	<li>
	                <a href="${ctx}/sys/referee/modify"><@spring.message code="form.add"></@spring.message></a>
	            </li>
	            </@shiro.hasPermission>	
	        </ul>
        	
    		<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/referee/list" method="post" class="breadcrumb form-search">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
				<div>
					<label><@spring.message code="userInfo.introducer.loginAccount"/>：</label><@form.input path="email" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="userInfo.introducer.name"/>：</label><@form.input path="refereeName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.mobile"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					
					<label><@spring.message code="form.status"></@spring.message>：
						<select id="status" name="status" class="select2 form-control input-small">
							<option value='' >All</opetion>
							<option value='1' <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>normal</option>
							<option value='2' <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="marketing.disabled"></@spring.message></option>
						<select>
					</label>
					&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</div>
			</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="5%"><@spring.message code="userInfo.introducer.loginAccount"/></th>
			            <th width="10%"><@spring.message code="userInfo.introducer.name"/></th>
			            <th width="8%"><@spring.message code="sys.mobile"></@spring.message></th>
			            <th width="4%"><@spring.message code="userInfo.merchant.rebaterate"/></th>
			            <th width="6%"><@spring.message code="userInfo.member.country"/></th>
			            <th width="14%"><@spring.message code="marketing.address"></@spring.message></th>
			            <th width="6%"><@spring.message code="form.status"/></th>
			            <th width="12%"><@spring.message code="nomo.createTime"></@spring.message></th>
			            <th width="8%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.email!''}</td>
				            <td>${info.refereeName!''}</td>
				            <td>${info.mobile!''}</td>
				            <td>${info.rebateRate!''}</td>
				            
				            <td>
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
				            </td>
				            
				            <td>${info.address!''}</td>
				            
				            <td>
				            	<#if info.status?? && info.status == 1>
				            		normal
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="marketing.disabled"></@spring.message>
				            	</#if>
				            </td>
				           	<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
							 <td>
								<@shiro.hasPermission name="sys:referee:modify">
									 	<#if info.status ?? && info.status == 1>
									 		<a  href="javascript:disable(${info.uid!});"><@spring.message code="marketing.disabled"></@spring.message></a>&nbsp;
									 	<#elseif info.status ?? && info.status == 2>
									 		<a  href="javascript:enable(${info.uid!});">Enabled</a>&nbsp;
						            	<#else>
						            	</#if>
						            	<a  href="${ctx}/sys/referee/modify?uid=${info.uid!}"><@spring.message code="form.edit"></@spring.message></a>
			            		</@shiro.hasPermission>	
			            		
								<@shiro.hasPermission name="sys:userRebate:list">
						            	<a  href="${ctx}/sys/userRebateLog/refereeList?uid=${info.uid!}">Income</a>
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