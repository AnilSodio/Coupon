<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				ignore: [],
				rules: {
					refereeName: {remote: "${ctx}/sys/referee/checkRefereeName?uid="+$('#uid').val()},
					mobile: {remote: "${ctx}/sys/referee/checkMobile?uid="+$('#uid').val()},
					email: {remote: "${ctx}/sys/referee/checkEmail?uid="+$('#uid').val()},
					country: {required:true}
					 
				},
				messages: {
					refereeName:{ remote:"The name already exists"},
					mobile:{ remote:"The mobile already exists"},
					email:{ remote:"The email already exists"},
					country:{ required:"please choose"}
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
		
		
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
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
	             <li >
	                <a  href="${ctx}/sys/referee/list"><@spring.message code="form.list"/> </a>
	            </li>
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">
					<#if info.id??>
						<@spring.message code="form.edit"></@spring.message>
					<#else>
						<@spring.message code="form.add"></@spring.message>
					</#if>
					</a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/referee/save" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				
				 <div class="form-body">
				 	 
		
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.introducer.name"/>:</label>
						<div class="col-md-4">
							<input type="text" name="refereeName" value="${info.refereeName !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.mobile"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="mobile" value="${info.mobile !}"  placeholder="86-13520894287" maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					<#if info.uid ?? >
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.country"/>:</label>
						<div class="col-md-4">
							<input type="hidden" name="country" value="${info.country !}"/>
							
							<p  class="form-control-static">
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
							</p>
							
						</div>
					</div>
					<#else>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.country"/>:</label>
						<div class="col-md-4">
							<select id="country" name="country" class="select2 form-control input-small" onchange="queryType(this)">
			           				<option value="">please choose</option>
	                	 			<#list countries as country>
										<#if info.country ?? && info.country == country.nationCode>
											<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
										<#else>
											<option value="${country.nationCode}">${country.englishName!}</option>
										</#if>
									</#list>
	                	 		</select>
						</div>
					</div>
					</#if>
					
					<#if info.uid??>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.merchant.email"/>:</label>
						<div class="col-md-4">
							<p  class="form-control-static">
								<input type="hidden" name="email" value="${info.email!}"/>
								${info.email!}
							</p>
						</div>
					</div>
					<#else>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.merchant.email"/>:</label>
						<div class="col-md-4">
							<input type="text" name="email" value="${info.email !}"  maxlength="32" class="form-control required email"/>
						</div>
					</div>
					</#if>
					
				
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.address"/>:</label>
						<div class="col-md-4">
							<input type="text" id ="address" name="address" value="${info.address!}" class="form-control required"/>
						</div>
					</div>
					
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
							<@shiro.hasPermission name="sys:referee:list">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
							</@shiro.hasPermission>
							<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"></@spring.message>" onclick="history.go(-1)"/>
							</div>
						</div>
					</div>
				</div>
			</@form.form>
            </div>
        </div>
	</div>
	
	
</body>
</html>