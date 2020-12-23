<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				ignore: [],
				rules: {
					 payAmount: {required:true}
				},
				messages: {
					payAmount:{ required:"please enter"}
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
                    <span><@spring.message code="userInfo.member.top.set"/></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
					<a  href="${ctx}/sys/payAmount/list"><@spring.message code="form.list"></@spring.message></a>
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
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/payAmount/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				 <div class="form-body">
				 
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
					 --->
					
					<div class="form-group">
					
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.vip.payAmount"/>:</label>
						<div class="col-md-4">
							<input type="text" name="payAmount" id="payAmount" maxlength="6" value="${info.payAmount!}" class="form-control required number"/>
						</div>
					</div>
					
					
					<div class="form-group">
					
						<label class="col-md-3 control-label"><@spring.message code="userInfo.member.vip.giftAmount"/>:</label>
						<div class="col-md-4">
							<input type="text" name="sendAmount" id="sendAmount" maxlength="6" value="${info.sendAmount!}" class="form-control required number"/>
						</div>
					</div>
					
					<div class="form-group">
					
						<label class="col-md-3 control-label"><@spring.message code="sys.sort"/>:</label>
						<div class="col-md-4">
							<input type="text" name="sort" id="sort" maxlength="6" value="${info.sort!}" class="form-control required number"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.currency"/>Description:</label>
						<div class="col-md-4">
							<@form.textarea path="remark" id="remark" htmlEscape=false maxlength="8" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-4">
						<@shiro.hasPermission name="sys:payAmount:list">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"/>"/>&nbsp;
						</@shiro.hasPermission>
						<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"/>" onclick="history.go(-1)"/>
						</div>
					</div>
				
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>