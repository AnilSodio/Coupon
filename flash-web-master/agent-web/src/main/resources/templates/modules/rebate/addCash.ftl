<#include "/include/taglib.ftl" >
<html>
<head>
<title></title>
<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				ignore: "",
				rules: {
				},
				messages: {
				},
				submitHandler: function(form){
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
                    <a target="_parent" href="${ctx}"><@spring.message code="agent.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                 <li>
                 <span>cash manage</span>
                 <i class="fa fa-angle-right"></i>
                </li>
                <li>
                      apply	cash 
                </li>
            </ul>
        </div>
		<#if message??><@tags.message content=message /></#if>
		<div class="portlet light ">
				<ul class="nav nav-tabs mb-25">
		            <li >
		                <a  href="${ctx}/rebate/cashLog"><@spring.message code="agent.cash.withdrawal.records"></@spring.message></a>
		            </li>
		            <li class="active">
		                <a  href="javascript:;">
		                	Cash-out Request
		            	 </a>
		            </li>
	        	</ul>
	            <div class="portlet-body form">
	                <!-- BEGIN FORM-->
					<@form.form id="inputForm" modelAttribute="obj" action="${ctx}/rebate/saveCash" method="post" class="form-horizontal" >
						<div class="form-body">
							<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message code="agent.cash.available.amount"></@spring.message>:</label>
									<div class="col-md-4">
										<p class="form-control-static">${money.availableBalance!}(${money.currency!})</p>
									</div>
							</div>
							<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message code="agent.cash.withdrawal.amount"></@spring.message>:</label>
									<div class="col-md-4">
										<input name="cashAmount" id="cashAmount" type="text" value=""  maxlength="11" class="form-control required digits" />
									</div>
							</div>
							<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message code="agent.cash.withdrawal.bankUserName"></@spring.message>:</label>
									<div class="col-md-4">
										<input name="cashUserName" id="cashUserName" type="text" value=""  maxlength="11" class="form-control required" />
									</div>
							</div>
							<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message code="agent.cash.withdrawal.bankName"></@spring.message>:</label>
									<div class="col-md-4">
										<input name="cashBank" id="cashBank" type="text" value=""  maxlength="11" class="form-control required" />
									</div>
							</div>
							<div class="form-group">
									<label class="col-md-3 control-label"><@spring.message code="agent.cash.withdrawal.bankNumber"></@spring.message>:</label>
									<div class="col-md-4">
										<input name="cashAccount" id="cashAccount" type="text" value=""  maxlength="11" class="form-control required" />
									</div>
							</div>
							<div class="form-actions">
								<div class="row">
					                <div class="col-md-offset-3 col-md-4">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="save"/>&nbsp;
									<input id="btnCancel" class="btn red" type="button" value="return" onclick="history.go(-1)"/>
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