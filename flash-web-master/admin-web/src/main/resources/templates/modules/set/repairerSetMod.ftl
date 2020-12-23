<#include "/include/taglib.ftl" >
<html>
<head>
	<title>维护员配置管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					
					resTimeout:{
						required:true
					},
					dealTimeout:{
						required:true
					},
					appReportTime:{
						required:true
					}
					
				},
				messages: {
					
					resTimeout:{
						required:"必填信息"
					},
					dealTimeout:{
						required:"必填信息"
					},
					appReportTime:{
						required:"必填信息"
					}
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
                    <span>维保参数管理<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:warning:modify">
                    	${(info.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
					<a data-toggle="tab" href="${ctx}/sys/share/modify">${(info.id??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/repairSet/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 		<div class="form-group">
							<label class="col-md-3 control-label">是否允许巡检员开锁：</label>
							<div class="col-md-4">
								 <p class="form-control-static">
									 <label ><input type="radio" name="isUnlock" value="1" <#if info.isUnlock ?? && info.isUnlock> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
									 <label ><input type="radio" name="isUnlock" value="0" <#if info.isUnlock ?? && !info.isUnlock> checked=checked </#if>/> 否</label>
								 </p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">工单响应超时时间设置:</label>
							<div class="col-md-4">
								<input type="text" name="resTimeout" value="${info.resTimeout !}"  maxlength="3" class="form-control number required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">工单处理超时时间设置:</label>
							<div class="col-md-4">
								<input type="text" name="dealTimeout" value="${info.dealTimeout !}"  maxlength="3" class="form-control number required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">App上报位置时间设置:</label>
							<div class="col-md-4">
								<input type="text" name="appReportTime" value="${info.appReportTime !}"  maxlength="3" class="form-control number required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">分配工单发短信设置：</label>
							<div class="col-md-4">
								 <p class="form-control-static">
									 <label ><input type="radio" name="isSendmsg" value="1" <#if info.isSendmsg ?? && info.isSendmsg> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
									 <label ><input type="radio" name="isSendmsg" value="0" <#if info.isSendmsg ?? && !info.isSendmsg> checked=checked </#if>/> 否</label>
								 </p>
							</div>
						</div>
						
					
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