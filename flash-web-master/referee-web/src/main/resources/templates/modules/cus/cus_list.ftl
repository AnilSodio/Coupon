<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
	    function disable(uid){
	    	 top.jBox.confirm('Are you sure you want to disable this user？', '提示', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Disabling, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/customer/modify?mark=1&uid="+uid,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, the operation is successful");
			                 		window.location.href="${ctx}/sys/customer/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(uid){
	    	 top.jBox.confirm('Are you sure you want to restore this user?', '提示', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Recovering, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/customer/modify?mark=3&uid="+uid,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, the operation is successful");
			                 		window.location.href="${ctx}/sys/customer/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
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
						
						top.jBox.confirm('确认要审核吗？', '提示', function (v, h, f) {
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span>Member management</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 <a data-toggle="tab" href="javascript:;">List</a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/customer/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label>User's phone：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label>status：
							<select id="status" name="status" class="select2 form-control input-small">
								<option value='' >All</opetion>
								<option value='1' <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>normal</option>
								<option value='3' <#if vo.status ?? && vo.status == 3>selected='selected'</#if>><@spring.message code="marketing.disabled"></@spring.message></option>
							<select>
						</label>
						&nbsp;&nbsp;
						<label>country：
							<select id="country" name="country" class="select2 form-control input-small">
								<option value='' >All</opetion>
								<#list countries as country>
									<#if vo.country ?? && vo.country == country.nationCode>
										<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
									<#else>
										<option value="${country.nationCode}">${country.englishName!}</option>
									</#if>
								</#list>
							<select>
						</label>
						<label>NOMO Box ID：</label><@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<#if openCard?? && openCard>
							&nbsp;&nbsp;
							<label>Membership card：
								<select id="isPayBasicCost" name="isPayBasicCost" class="select2 form-control input-small">
									<option value='' >All</opetion>
									<option value='1' <#if vo.isPayBasicCost ?? && vo.isPayBasicCost>selected='selected'</#if>>yes</option>
									<option value='0' <#if vo.isPayBasicCost ?? && !vo.isPayBasicCost>selected='selected'</#if>>no</option>
								<select>
							</label>
							&nbsp;&nbsp;
							<label>VIP Membership：
								<select id="isVIP" name="isVIP" class="select2 form-control input-small">
									<option value='' >All</opetion>
									<option value='1' <#if vo.isVIP ?? && vo.isVIP>selected='selected'</#if>>yes</option>
									<option value='0' <#if vo.isVIP ?? && !vo.isVIP>selected='selected'</#if>>no</option>
								<select>
							</label>
						</#if>
						&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="Search" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<#-- 
						<th width="5%">Profile Picture</th>-->
			            <th width="4%">User's information</th>
			            <th width="4%">country</th>
			            <#if adminRegist ?? && adminRegist>
			            	<th width="5%">工号</th>
			            </#if>
			            
			            <#if openCard?? && openCard>
				            <th width="10%">VIP Membership</th>
			            </#if>
			            
			            <#if isOpenBalance?? && isOpenBalance>
			            	<th width="5%">Balance</th>
			            </#if>
			            
			            <#-- 
			           	 <th width="9%">注册时间</th>
			            -->
			            
			            <#if sysSet.isNeedAuthenCheck ?? && sysSet.isNeedAuthenCheck  && sysSet.isNeedAuthen?? &&  sysSet.isNeedAuthen>
			            	<th width="5%">审核状态</th>
			            </#if>
			            
			            <th width="6%">NOMO Box ID</th>
			            
			            <th width="8%">operation</th>
					</tr>
					<#list page.content as info>
						<tr>
							<#-- 
							<td>
								<#if info.headImgPath ??>
									<img src="${info.headImgPath!}" width="50px" height="50px">
								<#else>
									<img src="/images/defuatcover.png" width="50px" height="50px">
								</#if>
							</td>
							-->
				            <td>
				            	${info.nickname!''}<br/>
				            	${info.mobile!''}
				           	</td>
				           	<td>
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
				           	</td>
				            
			            	<#if adminRegist ?? && adminRegist>
			            	<td> ${info.jobNumber!''}</td>
				            </#if>
				            
				            <#if openCard ?? && openCard>
					            <td>
					            	<br/>
					            	<#if info.isVIP ?? && info.isVIP>
					            		yes,valid till：
					            		<#if info.vipExpiresIn??>
					            			<br/> ${(info.vipExpiresIn?string('yyyy-MM-dd HH:mm:ss'))!''}
					            		</#if>
					            	<#else>
					            		no
					            	</#if>
					            </td>
					        </#if>
				            
					        <#if isOpenBalance?? && isOpenBalance>
					            <td>
					            	<br/>
						            ${info.currency!} ${info.availableBalance!0}
				            	</td>
					        </#if>
					        
			            	<#-- 
				           	 <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            -->
				            
							
							 <#if sysSet.isNeedAuthenCheck ?? && sysSet.isNeedAuthenCheck  && sysSet.isNeedAuthen?? &&  sysSet.isNeedAuthen>
				            	<td> 
				            		<#if info.authApplyStatus?? && info.authApplyStatus == 1>
				            			待审核
				            		<#elseif info.authApplyStatus ?? && info.authApplyStatus == 2>
				            			审核通过
				            		<#elseif info.authApplyStatus ?? && info.authApplyStatus == 3>
				            			审核失败
				            		<#else>
				            			${info.authApplyStatus!}
				            		</#if>
				            	</td>
				            </#if>
				            <td>${info.sysCode!}</td>
							 <td>
						         <a href="${ctx}/customer/detail?uid=${info.uid!}"><@spring.message code="stats.details"></@spring.message></a>
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					No records!！
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	