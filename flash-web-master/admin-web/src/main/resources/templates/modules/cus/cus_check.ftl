<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
			
			$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					authApplyRemark: {required:true},
					authApplyStatus: {required:true}
				},
				messages: {
					authApplyStatus: {remote: "please choose"},
					authApplyRemark: {remote: "请输入"}
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
                    <span>共享单车<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Member management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
List
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
<@spring.message code="form.list"></@spring.message></a></li>
	             
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">审核</a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/customer/applyCheckSave" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	<div class="form-group">
						<label class="col-md-3 control-label">用户头像:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<#if info.headImgPath??>
									<img src="${info.headImgPath!}" width="50px" height="50px">
								<#else>
									<img src="/images/defuatcover.png" width="50px" height="50px">
								</#if>
							</p>
						</div>
					</div>
					
					<#if setting.isNeedAuthen ?? && setting.isNeedAuthen>
						<div class="form-group">
							<label class="col-md-3 control-label">用户姓名:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.name!}</p>
							</div>
						</div>
					</#if>
					
					<div class="form-group">
						<label class="col-md-3 control-label">用户昵称和手机:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.nickname!}/${info.mobile!} </p>
						</div>
					</div>
					
					<#if setting.isNeedAuthen?? && setting.isNeedAuthen>
						<div class="form-group">
							<label class="col-md-3 control-label">身份证号:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.idCard!}</p>
							</div>
						</div>
					</#if>
						
					<#if setting.isNeedAuthen?? && setting.isNeedAuthen && setting.authenType?? && setting.authenType == 'student_card'>
						
						<div class="form-group">
							<label class="col-md-3 control-label">学校名称:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.schoolName!} </p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">学号:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.studentId!} </p>
							</div>
						</div>
					
					</#if>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">证件图片:</label>
						
						<#if img_list ?? && img_list?size gt 0>
					
							<#list img_list as img>
							  		
							  		<div class="col-md-2">
										  <div class="upload-pic-box" id="imgUrl1Container">
						                        <div id="imgUrl1Box" class="view-pic-list">
						                        	<#if img ?? && img ?length gt 0>
						                        		<a target="_blank" href="${img!}">
						                        			<span class="item"><img src="${img!}" width="120px" height="120px"></span>
						                        		</a>
						                        	</#if>
						                        </div>
						                    </div>
						                    <label for="imgUrl1" class="error"></label>
									</div>
							</#list>
							
						</#if>	
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">please choose审核结果:</label>
						<div class="col-md-4">
							<select id="authApplyStatus" name="authApplyStatus" class="select2 form-control input-small">
			    	 			<option value="">please choose</option>
			    	 			<option value="2">审核通过</option>
			    	 			<option value="3" >审核不通过</option>
		    	 			</select>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">请输入审核备注:</label>
						<div class="col-md-4">
							<textarea maxlength="150" cols="60" rows="4" name="authApplyRemark" class="form-control input-xlarge"/></textarea>
						</div>
					</div>
			
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:customer:view">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="返回" onclick="history.go(-1)"/>
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