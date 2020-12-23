<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="marketing.activityTypeManagement"></@spring.message></title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					typeCode: {required:true},
					typeName: {required:true}
				},
				messages: {
					typeCode:{ required:"please enter"},
					typeName: {required:"please enter"}
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="marketing.activityTypeManagement"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 						<a href="${ctx}/sys/proType/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="${ctx}/sys/proType/modify">
					<#if info.id??>
							<@spring.message code="form.edit"></@spring.message>
						<#else>
							<@spring.message code="form.add"></@spring.message>
						</#if>
					</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/proType/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.typeName"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="typeName" value="${info.typeName !}" maxlength="128"  class="form-control required"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">type code:</label>
						<div class="col-md-4">
							<input type="text" name="typeCode" value="${info.typeCode !}" maxlength="128" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:proType:list">
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