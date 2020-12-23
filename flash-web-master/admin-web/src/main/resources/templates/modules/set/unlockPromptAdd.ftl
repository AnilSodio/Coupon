<#include "/include/taglib.ftl" >
<html>
<head>
	<title>设置提示语</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					
					title:{ required:true },
					content:{ required:true }
					
				},
				messages: {
					
					title:{ required:"请输入标题"},
					content:{ required:"请输入内容"}
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
                    <span>参数管理<i class="fa fa-angle-right"></i></span>
                </li>
                 <li>
                    <span>开锁提示语<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:unlockPrompt:modify">
                    	${(info.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
					<@spring.message code="form.list"></@spring.message></a>
	            </li>
	             <li class="active">
					<a data-toggle="tab" href="${ctx}/sys/unlockPrompt/modify">${(info.id??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/unlockPrompt/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label">标题:</label>
						<div class="col-md-4">
							<#if info?? && info.id??>
								<p class="form-control-static">${info.title! ''} </p>
							<#else>
								<input type="text" name="title" value="${info.title !}" maxlength="128"  class="form-control required"/>
							</#if>
							
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">内容:</label>
						<div class="col-md-4">
							<input type="text" name="content" value="${info.content !}" maxlength="128" class="form-control required"/>
						</div>
					</div>
					
					<#if info.id??>
						<div class="form-group">
							<label class="col-md-3 control-label">是否开启:</label>
							<div class="col-md-4">
								<select  name="isDelete" class="select2 form-control required">
										<option value="0" <#if info.isDelete ?? && !info.isDelete>selected='selected'</#if>>开启</option>
										<option value="1" <#if info.isDelete ?? && info.isDelete>selected='selected'</#if>>关闭</option>
		                	 	</select>
							</div>
						</div>
					</#if>
							
					
					<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-4">
						<@shiro.hasPermission name="sys:warning:modify">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
						</@shiro.hasPermission>
						<input id="btnCancel" class="btn red" type="button" value="返回" onclick="history.go(-1)"/>
						</div>
					</div>
				
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>