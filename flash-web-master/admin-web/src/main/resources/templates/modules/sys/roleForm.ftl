<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
		$(document).ready(function(){
				
			<#if role?? && role.permissionIds?? && role.permissionIds!="">
			var permIds = "${role.permissionIds!}".split(",");
			for(var i=0; i<permIds.length; i++) {
				if(permIds[i] != ""){
					$("#permName_"+permIds[i]).prop("checked",true);
				}
			}
			<#list permissionGroupNameList as g>
				parentCheck(${g_index});
			</#list>
			</#if>
			
			$("input[id^='permName_']").change(function(){
				var pid = $(this).attr("pid");
				var pidNum = pid.split("_")[1];
				if(pidNum!="")
					parentCheck(pidNum);
			});
			
			$("input[id^='groupName_']").change(function(){
	 			var id = $(this).attr("id");
	 			var idNum = id.split("_")[1];
	 			if($(this).prop("checked")){
	 				$("#groupDiv_"+idNum).find("input[id^='permName_']").each(function(){
	 					$(this).prop("checked",true);
	 				});
	 			}else{
	 				$("#groupDiv_"+idNum).find("input[id^='permName_']").each(function(){
	 					$(this).prop("checked",false);
	 				});
	 			}
	 		});
		
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {
						required:true ,
						remote: "${ctx}/sys/role/checkName?id=${role.id!}"
					},
					officeId:{
						required:true 
					}
				},
				messages: {
					name: {
						required:"required",
						remote: "Already exist"
					},
					officeId:{	
						required:"required"
					}
				},
				submitHandler: function(form){
				 	var ids = "";
		 			$("input[id^='permName_']").each(function(){
		 				if($(this).prop("checked")){
		 					ids += $(this).val();
		 					ids += ",";
		 				}
		 			});
					$("#permissionIds").val(ids);
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		});
 		
 		function parentCheck(id){
  			if(id!="" || !id){
	 			var count = $("#permDiv_"+id).find("input:checkbox").length;
				var checkFlag = 0;
				$("#permDiv_"+id).find("input:checkbox").each(function(){
					if($(this).prop('checked')){
						checkFlag += 1;
					}
				});
				if(checkFlag==count){
					$("#groupName_"+id).prop("checked",true);
				}else{
					$("#groupName_"+id).prop("checked",false);
				}
 			}
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
				    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="sys.roleManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:role:Edit">
	                    <#if role.id??>
							<@spring.message code="form.edit"></@spring.message>
						<#else>
							<@spring.message code="form.add"></@spring.message>
						</#if>
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/role/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="${ctx}/sys/role/form">
					<#if role.id??>
						<@spring.message code="form.edit"></@spring.message>
					<#else>
						<@spring.message code="form.add"></@spring.message>
					</#if>
					</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="role" action="${ctx}/sys/role/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.attributionAgencies"></@spring.message>:</label>
						<div class="col-md-4">
			                <@tags.treeselect id="office" name="officeId" value=(role.office.id)! labelName="officeName" labelValue=(role.office.name)! 
								title='归属部门' url="/sys/office/treeData?type=2" cssClass="form-control" allowClear=false />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.roleName"></@spring.message>:</label>
						<div class="col-md-4">
							<input id="oldName" name="oldName" type="hidden" value="${role.name!}">
							<@form.input path="name" htmlEscape=false maxlength="50" class="form-control required"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.roleIdentification"></@spring.message>:</label>
						<div class="col-md-4">
							<input id="oldName" name="oldName" type="hidden" value="${role.name!}">
							<@form.input path="code" htmlEscape=false maxlength="50" class="form-control" />
						</div>
					</div>
					
<!-- 					<div class="form-group">
						<label class="col-md-3 control-label">数据范围:</label>
						<div class="col-md-4">
							<@form.select path="dataScope"  cssClass="form-control">
								<@form.options items=fns.getDictList('sys_data_scope') itemLabel="label" itemValue="value" htmlEscape=false/>
							</@form.select>
						</div>
					</div> -->
					
<!-- 					<div class="form-group">
						<label class="col-md-3 control-label">角色类型:</label>
						<div class="col-md-4">
							<@form.select path="roleType"  cssClass="form-control">
								<@form.option value="">--please choose--</@form.option>
								<@form.option value="3">任务分配</@form.option>
								<@form.option value="2">管理角色</@form.option>
								<@form.option value="1">普通角色</@form.option>
							</@form.select>
						</div>
					</div> -->
					
<!-- 					<div class="form-group">
						<label class="col-md-3 control-label">是否系统数据:</label>
						<div class="col-md-4">
							<@form.select path="isSys"  cssClass="form-control">
								<@form.option value="">--please choose--</@form.option>
								<@form.option value="1">是</@form.option>
								<@form.option value="0">否</@form.option>
							</@form.select>
						</div>
					</div> -->
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.itSUsableOrNot"></@spring.message>:</label>
						<div class="col-md-4">
							<@form.select path="useable"  cssClass="form-control">
								<@form.option value="">--please choose--</@form.option>
								<@form.option value="1">yes</@form.option>
								<@form.option value="0">no</@form.option>
							</@form.select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Role <@spring.message code="sys.authorization"></@spring.message>:</label>
						<div class="col-md-8">
							<#list permissionGroupNameList as groupName>
							<div id="groupDiv_${groupName_index}" style="padding-bottom: 10px;">
						 		<div style="padding-top: 8px;"><label><input id="groupName_${groupName_index}" type="checkbox" value="" /><b> ${groupName!}</b></label></div>
								<div id="permDiv_${groupName_index}">
							 	<#list permissionList as perm>
							 		<#if perm.groupName==groupName>
							 			<label style="display: inline-block; min-width: 110px; padding-right: 10px; white-space: nowrap;"><input id="permName_${perm.id!}" type="checkbox" value="${perm.id!}" pid="groupName_${groupName_index}" /> ${perm.name!}</label>
							 		</#if>
							 	</#list>
						 		</div>
						 	</div>
						 	</#list>
							<@form.hidden path="permissionIds"/>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:role:modify">
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