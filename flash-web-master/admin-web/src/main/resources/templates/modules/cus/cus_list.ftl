<#include "/include/taglib.ftl" >
<html>
<head>
	<title>会员列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center;
	  vertical-align: middle !important;
	}
	</style>
	<script type="text/javascript">
	    function disable(uid){
	    	 top.jBox.confirm('<@spring.message code="userInfo.member.disable"/>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v ==true) {
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
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function enable(uid){
	    	 top.jBox.confirm('<@spring.message code="userInfo.member.restore"/>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
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
				    <span><@spring.message code="userInfo.member.manage"/></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <#if sysSet.isUserRegister ?? && !sysSet.isUserRegister>
            	<li>
	                <a href="${ctx}/sys/customer/Edit"><@spring.message code="form.add"></@spring.message></a>
	            </li>
	            </#if>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/customer/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="userInfo.member.nickname"/>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>

						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.phone"/>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>：
							<select id="status" name="status" class="select2 form-control input-small">
								<option value='' ><@spring.message code="all"/></opetion>
								<option value='1' <#if vo.status ?? && vo.status == 1>selected='selected'</#if>><@spring.message code="normal"/></option>
								<option value='3' <#if vo.status ?? && vo.status == 3>selected='selected'</#if>><@spring.message code="disabled"/></option>
							<select>
						</label>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.country"/>：
							<select id="country" name="country" class="select2 form-control input-small">
								<option value='' ><@spring.message code="all"/></opetion>
								<#list countries as country>
									<#if vo.country ?? && vo.country == country.nationCode>
										<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
									<#else>
										<option value="${country.nationCode}">${country.englishName!}</option>
									</#if>
								</#list>
							<select>
						</label>
						<br/><br/>
						<#if sysSet.isUserRegister?? && sysSet.isUserRegister>
							<label><@spring.message code="userInfo.member.vstep"/>：</label>
							<select id="authStep" name="authStep" class="select2 form-control input-small">
								<option value='' ><@spring.message code="all"/></opetion>
								<option value='1' <#if vo.authStep ?? && vo.authStep = 1>selected='selected'</#if>><@spring.message code="userInfo.member.vstep.phone"/></option>
								<option value='2' <#if vo.authStep ?? && vo.authStep = 2>selected='selected'</#if>><@spring.message code="userInfo.member.vstep.deposit"/></option>
								<#-- 
								<option value='3' <#if vo.authStep ?? && vo.authStep = 3>selected='selected'</#if>>Verified</option>
								-->
								<option value='4' <#if vo.authStep ?? && vo.authStep = 4>selected='selected'</#if>><@spring.message code="userInfo.member.vstep.user"/></option>
							<select>
						</#if>
						&nbsp;&nbsp;
						<label><@spring.message code="userInfo.member.nomoBoxId"/>：</label><@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<#if sysSet.isOpenMemberCard?? && sysSet.isOpenMemberCard>
							&nbsp;&nbsp;
							<label>Membership card：
								<select id="isPayBasicCost" name="isPayBasicCost" class="select2 form-control input-small">
									<option value='' ><@spring.message code="all"/></opetion>
									<option value='1' <#if vo.isPayBasicCost ?? && vo.isPayBasicCost>selected='selected'</#if>><@spring.message code="yes"/></option>
									<option value='0' <#if vo.isPayBasicCost ?? && !vo.isPayBasicCost>selected='selected'</#if>><@spring.message code="no"/></option>
								<select>
							</label>
							&nbsp;&nbsp;
							<label>VIP Passes：
								<select id="isVIP" name="isVIP" class="select2 form-control input-small">
									<option value='' ><@spring.message code="all"/></opetion>
									<option value='1' <#if vo.isVIP ?? && vo.isVIP>selected='selected'</#if>><@spring.message code="yes"/></option>
									<option value='0' <#if vo.isVIP ?? && !vo.isVIP>selected='selected'</#if>><@spring.message code="no"/></option>
								<select>
							</label>
						</#if>
						<br/></br/>
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<#-- 
						<th width="5%">Profile Picture</th>-->
			            <th width="4%"><@spring.message code="userInfo.member.userInfo"/></th>
			            <th width="4%"><@spring.message code="userInfo.member.country"/></th>
			            <#if sysSet.isUserRegister ?? && !sysSet.isUserRegister>
			            	<th width="5%">工号</th>
			            </#if>
			            
			            <#if sysSet.isOpenMemberCard?? && sysSet.isOpenMemberCard>
				            <th width="10%"><@spring.message code="userInfo.member.vip.membership"/></th>
			            	<th width="10%"><@spring.message code="userInfo.member.vip.pass"/></th>
			            </#if>
			            
			            <#if sysSet.isOpenBalance?? && sysSet.isOpenBalance>
			            	<th width="5%"><@spring.message code="userInfo.member.balance"/></th>
			            </#if>
			            
			            <#-- 
			           	 <th width="9%">注册时间</th>
			            -->
			            <#if sysSet.isUserRegister?? && sysSet.isUserRegister>
				            <th width="9%"><@spring.message code="userInfo.member.authStatus"/></th>
			            </#if>
			            
			            <#if sysSet.isNeedAuthenCheck ?? && sysSet.isNeedAuthenCheck  && sysSet.isNeedAuthen?? &&  sysSet.isNeedAuthen>
			            	<th width="5%">审核状态</th>
			            </#if>
			            
			            <th width="6%"><@spring.message code="userInfo.member.nomoBoxId"/></th>
			            <th width="6%"><@spring.message code="userInfo.member.client"/></th>
			            <th width="8%"><@spring.message code="operation"/></th>
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
				            
			            	<#if sysSet.isUserRegister ?? && !sysSet.isUserRegister>
			            	<td> ${info.jobNumber!''}</td>
				            </#if>
				            
				            <#if sysSet.isOpenMemberCard ?? && sysSet.isOpenMemberCard>
				            	<td>
				            		<br/>
				            		<#if info.isPayBasicCost?? && info.isPayBasicCost>
					            		<@spring.message code="yes"/>
					            	<#else>
					            		<@spring.message code="no"/>
					            	</#if>
				            	</td>
					            <td>
					            	<br/>
					            	<#if info.isVIP ?? && info.isVIP>
					            		<@spring.message code="yes"/>,<@spring.message code="userInfo.member.validTill"/>：
					            		<#if info.vipExpiresIn??>
					            			<br/> ${(info.vipExpiresIn?string('yyyy-MM-dd HH:mm:ss'))!''}
					            		</#if>
					            	<#else>
					            		<@spring.message code="no"/>
					            	</#if>
					            </td>
					        </#if>
				            
					        <#if sysSet.isOpenBalance?? && sysSet.isOpenBalance>
					            <td>
					            	<br/>
						            ${info.currency!} ${info.availableBalance!0}
				            	</td>
					        </#if>
					        
			            	<#-- 
				           	 <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            -->
				            
				            <#if sysSet.isUserRegister?? && sysSet.isUserRegister>
					            <td>
					            	<#if info.authStatus ?? && info.authStatus == "0">
					            		<@spring.message code="userInfo.member.auth.not"/>
					            	<#elseif info.authStatus ?? && info.authStatus == "1">
					            		<@spring.message code="userInfo.member.auth.ing"/>
					            	<#elseif info.authStatus ?? && info.authStatus == "2">
					            		<@spring.message code="userInfo.member.auth.success"/> 
					            	<#elseif info.authStatus ?? && info.authStatus == "3">
					            		<@spring.message code="userInfo.member.auth.failed"/>
					            	</#if>
					            	<br/>
					            	<#if info.authStep ?? && info.authStep == 1>
					            		<@spring.message code="userInfo.member.vstep.phone"/>
					            	<#elseif info.authStep ?? && info.authStep == 2>
					            		<@spring.message code="userInfo.member.vstep.deposit"/>
					            	<#-- 
					            	<#elseif info.authStep ?? && info.authStep == 3>
					            		Verified
					            	-->
					            	<#elseif info.authStep ?? && info.authStep == 4>
					            		<@spring.message code="userInfo.member.vstep.user"/>
					            	</#if>
								</td>
							</#if>
							
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
				            <td>${info.clientSource!}</td>
							 <td>
							 	<br/>
								<@shiro.hasPermission name="sys:customer:modify">
									 	<#if info.status ?? && info.status == "1">
									 		<a  href="javascript:disable(${info.uid!});"><@spring.message code="disabled"/></a>
									 	<#elseif info.status ?? && info.status == "3">
									 		<a  href="javascript:enable(${info.uid!});"><@spring.message code="enabled"/></a>
						            	<#else>
						            	</#if>
			            		</@shiro.hasPermission>	
			            		
			            		<@shiro.hasPermission name="sys:customer:detail">
						            	&nbsp; 
						            	<a  href="${ctx}/sys/customer/detail?uid=${info.uid!}"><@spring.message code="form.detail"></@spring.message></a>
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