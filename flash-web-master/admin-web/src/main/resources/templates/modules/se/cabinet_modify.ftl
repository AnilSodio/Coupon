<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜信息修改</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					sysCode: {required:true},
					typeId: {required:true},
					cabinetAlias: {required:true},
					cabinetCode: {
						required:true,
						remote: "${ctx}/sys/cabinet/checkCabinetCode?id="+$('#id').val()
					},
					cabinetAlias: {remote: "${ctx}/sys/cabinet/checkCabinetAlias?id="+$('#id').val()}
				},
				messages: {
					sysCode: {required:"<@spring.message code='nomo.pleaseChoose'></@spring.message>"},
					typeId: {required:"<@spring.message code='nomo.pleaseChoose'></@spring.message>"},
					cabinetAlias: {required:"required"},
					cabinetCode: {
						required:"required",
						remote:"<@spring.message code='nomo.alrightExit'></@spring.message>"
					},
					cabinetAlias: {
						remote:"<@spring.message code='nomo.alrightExit'></@spring.message>"
					}
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
 		
 		
 		function addLatAndLon() {
			top.jBox("iframe:${ctx}/sys/seller/selectLonAndLat",{ 
                title: "<@spring.message code='nomo.selectLat'></@spring.message>",
                width: 900,
                height: 500,
                submit: function() {
	                var obj = $(parent.document.body).find('#jbox-iframe').contents().find('#google_location input');
	              	console.log(obj.val());
	              	var result = obj.val();
	              	
					$("#location_lat").val(result.split(":")[0]);
					$("#location_lon").val(result.split(":")[1]);
					$("#locationAddress").val(result.split(":")[2]);
					$("#lonAndLat").html(result.split(":")[2]);
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                
                <li>
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.nomoBox"></@spring.message></span>
				</li>
				
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 					<a  href="${ctx}/sys/cabinet/list"><@spring.message code="form.list"></@spring.message></a>
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
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/cabinet/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
			 	<div class="form-group">
					<label class="col-md-3 control-label"><@spring.message code="nomo.nomoBoxId"></@spring.message>:</label>
					<div class="col-md-4">
						<#if info.id??>
							<p class="form-control-static">${info.sysCode !}</p>
							<input type="hidden" name="sysCode" value="${info.sysCode!}"/>
						<#else>
							<select id="sysCode" name="sysCode" class="select2 form-control input-small">
								<#if (code_list?size > 0)>
				           			<option value="">please choose</option>
				           			<#list code_list as g>
		                	 			<option value="${g.sysCode!}" <#if info.sysCode ?? && info.sysCode == g.sysCode>selected='selected'</#if>>${g.sysCode!}</option>
		                	 		</#list>
		                	 	<#else>
		                	 		<option value="">No number information</option>
		                	 	</#if>
	            	 		</select>
						</#if>
					</div>
				</div>
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.code"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="cabinetCode" value="${info.cabinetCode!}" maxlength="128"  class="form-control required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.promoCode"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="cabinetAlias" value="${info.cabinetAlias!}" maxlength="32"  class="form-control required"/>
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.name"></@spring.message>:</label>
						<div class="col-md-4">
							<select id="typeId" name="typeId" class="select2 form-control input-small">
								<#if (typeList?size > 0)>
				           			<option value="">please  choose</option>
				           			<#list typeList as g>
		                	 			<option value="${g.id!}" <#if info.typeId ?? && info.typeId == g.id>selected='selected'</#if>>${g.typeName!}</option>
		                	 		</#list>
		                	 	<#else>
		                	 		<option value=""><@spring.message code="nomo.typeNo"></@spring.message></option>
		                	 	</#if>
                	 		</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.chargeRule"></@spring.message>:</label>
						<div class="col-md-4">
                	 		<select id="ruleId" name="ruleId" class="select2 form-control input-small">
								<#if (ruleList?size > 0)>
				           			<option value="">please  choose</option>
				           			<#list ruleList as rule>
		                	 			<option value="${rule.id!}" <#if info.ruleId ?? && info.ruleId == rule.id>selected='selected'</#if>>${rule.ruleDesc!}</option>
		                	 		</#list>
		                	 	<#else>
		                	 		<option value=""><@spring.message code="nomo.type.chargeRule"></@spring.message></option>
		                	 	</#if>
                	 		</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.agent"></@spring.message>:</label>
						<div class="col-md-4">
							<#if info.id??>
								<#if info.agentName??>
									<input type="hidden" name="agentId" value="${info.agentId!}"/>
									<p class="form-control-static">${info.agentName !}</p>
								<#else>
									<select id="agentId" name="agentId" class="select2 form-control input-small">
									<#if (agent_list?size > 0)>
					           			<option value="">please choose</option>
					           			<#list agent_list as g>
			                	 			<option value="${g.uid!}" <#if info.agentId ?? && info.agentId == g.uid>selected='selected'</#if>>${g.agentName!}</option>
			                	 		</#list>
			                	 	<#else>
			                	 		<option value="">No agent</option>
			                	 	</#if>
		            	 			</select>
								</#if>
							<#else>
								<select id="agentId" name="agentId" class="select2 form-control input-small">
								<#if (agent_list?size > 0)>
				           			<option value="">please choose</option>
				           			<#list agent_list as g>
		                	 			<option value="${g.uid!}" <#if info.agentId ?? && info.agentId == g.uid>selected='selected'</#if>>${g.agentName!}</option>
		                	 		</#list>
		                	 	<#else>
		                	 		<option value="">No agent</option>
		                	 	</#if>
		        	 			</select>
							</#if>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.address"></@spring.message>:</label>
						<div class="col-md-4">
							
							<p id="lonAndLat" class="form-control-static">
								<#if info.locationAddress ??>
									${info.locationAddress!}
								</#if>
							</p>
							
							<a class="form-control-static" href="javascript:addLatAndLon();">
								<#if info.uid ??>
									<@spring.message code="nomo.clienEdit"></@spring.message> 
								<#else>
									<@spring.message code="nomo.clienEdit"></@spring.message>
								</#if>
							</a>
							<input type="hidden" id ="locationAddress" name="locationAddress" value="${info.locationAddress!}" class="form-control required"/>
							<input type="hidden" id="location_lon" name="locationLon" value="${info.locationLon!}"/>
							<input type="hidden" id="location_lat"  name="locationLat" value="${info.locationLat!}" />
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.addResc"></@spring.message> :</label>
						<div class="col-md-4">
							<input type="text" id ="locationDesc" name="locationDesc" value="${info.locationDesc!}" class="form-control"/>
						</div>
					</div>
					
					<#-- 
				 	<div class="form-group">
						<label class="col-md-3 control-label">容量:</label>
						<div class="col-md-4">
							<input type="text" name="capacity" value="${info.capacity!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					-->
					
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:inteRule:list">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message> "/>&nbsp;
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