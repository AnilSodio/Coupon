<#include "/include/taglib.ftl" >
<html>
<head>
	<title>会员卡管理</title>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					cardName:{remote: "${ctx}/sys/card/checkCardName?id="+$('#id').val()},
					cardEffectiveTime:{
						required:true,
						digits:true,
						min:1
					}
				},
				messages: {
					cardName: {remote: "The name already exists"},
					cardEffectiveTime:{
						required:"please enter"
					}
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
 		
 		
 		function showPrompt(currency) {
 			<#list currencies as currency>
			if("${currency}" ==  currency.value){
				alert("${currency} currency must be greater than 1 to pay the rate");
			}
			</#list>	
 		}
 		
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
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:card:moidfy">
                    	${(info.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 				<a   href="${ctx}/sys/card/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab"">
					<#if info.id??>
						<@spring.message code="form.edit"></@spring.message>
					<#else>
						<@spring.message code="form.add"></@spring.message>
					</#if>
					</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/card/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.vip.cardName"/>:</label>
						<div class="col-md-4">
							<input type="text" name="cardName" value="${info.cardName!}" htmlEscape=false  maxlength="50" class="form-control required"/>
						</div>
					</div>
					
					<#--
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.currency"/>:</label>
						<div class="col-md-4">
							<select id="currency" name="currency" class="select2 form-control input-small required" onchange="showPrompt(this)">
								<option value="">please choose</option>
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
					 -->
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.vip.amount"/>:</label>
						<div class="col-md-4">
							<input type="text" name="cardAmount" value="${info.cardAmount!}" htmlEscape=false  maxlength="50" class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.vip.effective"/>:</label>
						<div class="col-md-4">
							<input type="text" name="cardEffectiveTime" value="${info.cardEffectiveTime!}" htmlEscape=false  
							maxlength="50" class="form-control required"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="nomo.unit"/>:</label>
						<div class="col-md-4">
							<select id="cardUnit" name="cardUnit" class="select2 form-control required  input-small">
									<option value="">please choose</option>
									<option value="day" <#if info.cardUnit?? && info.cardUnit =='day'>selected='selected'</#if>><@spring.message code="marketing.day"></@spring.message></option>
									<option value="hour" <#if info.cardUnit?? && info.cardUnit =='hour'>selected='selected'</#if>>hour</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.sort"/>:</label>
						<div class="col-md-4">
							<input type="text" name="sort" value="${info.sort!}" htmlEscape=false  maxlength="3"  class="form-control sort"/>
						</div>
					</div>
					
					<#-- 
						<div class="form-group">
							<label class="col-md-3 control-label">折扣:</label>
							<div class="col-md-4">
								<input type="text" name="discount" value="${info.discount!}" htmlEscape=false  maxlength="50" placeholder="购买折扣"  class="form-control required"/>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-md-3 control-label">排序:</label>
							<div class="col-md-4">
								<input type="text" name="sort" value="${info.sort!}" htmlEscape=false  maxlength="50" class="form-control required number"/>
							</div>
						</div>
					-->
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.description"/>:</label>
						<div class="col-md-4">
							<@form.textarea path="cardDesc" htmlEscape=false rows="3" maxlength="1000" class="form-control input-xlarge"/>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:card:list">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"/>"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"/>" onclick="history.go(-1)"/>
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