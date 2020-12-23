<#include "/include/taglib.ftl" >
<html>
<head>
	<title>收费规则修改页面</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					unitMinute: {required:true},
					unitPrice: {required:true},
					costTop: {required:true},
					freeUseTime: {required:true},
					currency: {required:true}
				},
				messages: {
					unitMinute: {required:"required"},
					unitPrice: {required:"required"},
					costTop: {required:"required"},
					freeUseTime: {required:"required"},
					currency: {required:"<@spring.message code='nomo.pleaseChoose'></@spring.message>"}
					
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
				   	<span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.nomoBoxType"></@spring.message></span>
				</li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	       	    <li >
 					<a  href="${ctx}/sys/rules/ruleList"><@spring.message code="nomo.rule_list"></@spring.message></a>
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
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/rules/ruleSave" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@form.hidden path="cabinetTypeId"/>
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.cost"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="unitMinute" value="${info.unitMinute!}" maxlength="50"  class="form-control required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.costP"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="unitPrice" value="${info.unitPrice!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.currency"></@spring.message>:</label>
						<div class="col-md-4">
							<select id="currency" name="currency" class="select2 form-control input-small required">
							    <#list currencies as currency>
                            	<#if info.currency?? && info.currency == currency>
								<option selected="selected" value="${currency}">${currency}</option>
								<#else>
								<option value="${currency}">${currency}</option>								
								</#if>
								</#list>
							</select>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.costcap"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="costTop" value="${info.costTop!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.freeTime"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="freeUseTime" value="${info.freeUseTime!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.type.desc"></@spring.message>:</label>
						<div class="col-md-4">
							<@form.textarea path="ruleDesc" htmlEscape=false rows="5"  maxlength="1025" class="form-control input-xlarge"/>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:rules:ruleModify">
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