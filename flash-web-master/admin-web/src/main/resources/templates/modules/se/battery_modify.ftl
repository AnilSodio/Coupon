<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电宝信息管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					typeId: {required:true},
					portableBatteryCode: {remote: "${ctx}/sys/battery/checkPortableBatteryCode?id="+$('#id').val()}
				},
				messages: {
					typeId: {required:"please choose"},
					portableBatteryCode: {remote:"已经存在"}
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
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>Box Management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	${((info.id)??)?string('修改','添加')}
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">${((info.id)??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/battery/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label">资产编号:</label>
						<div class="col-md-4">
							<input type="text" name="portableBatteryCode" value="${info.portableBatteryCode!}" maxlength="50"  class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">型号名称:</label>
						<div class="col-md-4">
							<select id="typeId" name="typeId" class="select2 form-control input-small" onchange="queryType(this)">
								<#if (typeList?size > 0)>
				           			<option value="">please choose</option>
				           			<#list typeList as g>
		                	 			<option value="${g.id!}" <#if info.typeId ?? && info.typeId == g.id>selected='selected'</#if>>${g.typeName!}</option>
		                	 		</#list>
		                	 	<#else>
		                	 		<option value="">暂无型号信息</option>
		                	 	</#if>
                	 		</select>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:battery:list">
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