<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
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
                    <span><@spring.message code="agent.index.info"></@spring.message></span>
                </li>
            </ul>
        </div>
        <#if message??><@tags.message content=message /></#if>
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <span class="caption-subject font-green bold"><@spring.message code="agent.index.info"></@spring.message></span>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <@form.form id="inputForm" modelAttribute="user" action="${ctx}/saveInfo" method="post" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="agent.info.email"></@spring.message>: </label>
                            <div class="col-md-4">
                                <p class="form-control-static"> ${(user.email)!}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="agent.info.contact"></@spring.message>: </label>
                            <div class="col-md-4">
                            	<@form.input path="agentName" htmlEscape=false maxlength="50" class="form-control required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="agent.info.mobile"></@spring.message>: </label>
                            <div class="col-md-4">
                                <@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control required"/>
                            </div>
                        </div>
                       
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input id="btnSubmit" class="btn btn-primary" type="submit" value="save"/>
                            </div>
                        </div>
                    </div>
                    </@form.form>
            </div>
        </div>
	</div>
</body>
</html>