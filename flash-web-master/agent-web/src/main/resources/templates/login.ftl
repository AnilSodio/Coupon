<#include "/include/taglib.ftl" >
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><@spring.message code="agent.productName"></@spring.message></title>
    <link type="text/css" rel="stylesheet" href="/resources/css/login.css">
    
    <script>
    	if (parent.frames.length > 0) {
    		parent.location.href = window.location.href;
    	}
    </script>

    <script src="/resources/js/jquery-1.8.2.js"></script>
    <script src="/resources/js/jquery.validate.min.js"></script>
    
</head>
<body>
<#if message?? >
<div class="ui-warn">
	${message}
</div>
</#if>
<div class="login-wrap">
    <div class="text-center pb-20" style="margin-top:80px;"><span style="font-size:30px;color:white;"><@spring.message code="agent.productName"></@spring.message></span></div>
    <div class="login-panel" >
        <div class="login-box">
            <div class="title"><@spring.message code="agent.login"></@spring.message></div>
            <form class="login-form" id="loginForm" action="/login" method="post">
                <div class="item">
                    <span><@spring.message code="agent.account"></@spring.message></span>
                    <input type="text" class="txt" id="username" name="username" />
                </div>
                <div class="item">
                    <span><@spring.message code="agent.password"></@spring.message></span>
                    <input type="password" class="txt" name="password" />
                </div>
                <#if isValidateCodeLogin?? && isValidateCodeLogin >
                <div class="item">
                    <span><@spring.message code="agent.verify"></@spring.message></span>
                    <@tags.validateCode name="validateCode" inputCssStyle="margin-bottom:0;" />
                </div>
				</#if>
                <div class="item btns" >
                    <input type="submit" class="btn btn-blue btn-block btn-large" value="login" style="cursor:pointer"/>
                </div>
            </form>
        </div>
    </div>
    <div class="login-copyright"><@spring.message code="agent.copyright"></@spring.message></div>
</div>
<script>

    jQuery("#loginForm").validate({
        rules:{
        	username:{required :true},
            password:{required :true},
            validateCode: {required:true,remote: "${request.contextPath}/servlet/validateCodeServlet"}
        },
        messages:{
        	username:{required:"account can not be null"},
            password:{required:"password can not be null"},
            validateCode: {remote: "Incorrect verification code.", required: "Please fill in the verification code."}
         },
         errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
    });
</script>
</body>
</html>