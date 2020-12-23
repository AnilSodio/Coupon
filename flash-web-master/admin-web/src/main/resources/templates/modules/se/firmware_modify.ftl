<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜固件信息管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					moduleCode: {required:true},
					firmwareName: {required:true},
					file: {required:true}
				},
				messages: {
					moduleCode:{ required:"required"},
					firmwareName:{ required:"required"},
					file:{ required:"required"}
				},    
				submitHandler: function(form){
					loading('正在提交，请稍等...');
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
                    <a target="_parent" href="${ctx}">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
				<li>
				    <span>Cabinet firmware<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:firmware:modify">
                    	${(info.id??)?string('Edit','New')}
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/firmware/list">list </a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">${(info.id??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/firmware/save" enctype="multipart/form-data" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				 <div class="form-body">
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Module name:</label>
						<div class="col-md-4">
							<select id="moduleCode" name="moduleCode" class="select2 form-control input-small" ">
		           			<option value="">please choose</option>
		           			<#list moudle_list as g>
                	 			<option value="${g.code!}" <#if info.moduleCode ?? && info.moduleCode == g.code>selected='selected'</#if>>${g.name!}</option>
                	 		</#list>
                	 		</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Firmware name:</label>
						<div class="col-md-4">
							<input type="text" name="firmwareName" value="${info.firmwareName !}" maxlength="128"  class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Firmware file:</label>
						<div class="col-md-4">
							<input type="file" name="file" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:firmware:modify">
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