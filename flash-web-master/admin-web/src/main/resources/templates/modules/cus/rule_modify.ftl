<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					integralNum: {digits:true}
				},
				messages: {
					integralNum: {required:"please enter"}
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
                    <span><@spring.message code="userInfo.member.manage"/><i class="fa fa-angle-right"></i></span>
                </li>
                <@shiro.hasPermission name="sys:inteRule:list">
	                <li>
	                    <span>
	                    	<@spring.message code="edit"/>
	                    </span>
	                </li>
                </@shiro.hasPermission>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 					<a  href="${ctx}/sys/inteRule/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="form.edit"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/inteRule/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.description"/>:</label>
						<div class="col-md-4">
							<input type="text" name="ruleDescribe" value="${info.ruleDescribe!}" maxlength="50"  class="form-control required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.points"/>:</label>
						<div class="col-md-4">
							<input type="text" name="integralNum" value="${info.integralNum!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					<#-- 
						<div class="form-group">
							<label class="col-md-3 control-label">编码:</label>
							<div class="col-md-4">
								<input type="text" name="ruleCode" value="${info.ruleCode!}" maxlength="25" class="form-control required"/>
							</div>
						</div>
					-->
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:inteRule:list">
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