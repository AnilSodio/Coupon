<#include "/include/taglib.ftl" >
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>${config.productName} - login</title>
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
    <div class="text-center pb-20" style="margin-top:80px;"><span style="font-size:50px;color:white;">NOMO</span></div>
    <div class="login-panel" >
        <div class="login-box">
            <div class="title">introducer login</div>
            <form class="login-form" id="loginForm" action="/login" method="post">
                <div class="item">
                    <span>account</span>
                    <input type="text" class="txt" id="username" name="username" />
                </div>
                <div class="item">
                    <span><@spring.message code="sys.password"></@spring.message></span>
                    <input type="password" class="txt" name="password" />
                </div>
                <#if isValidateCodeLogin?? && isValidateCodeLogin >
                <div class="item">
                    <span>verify</span>
                    <@tags.validateCode name="validateCode" inputCssStyle="margin-bottom:0;" />
                </div>
				</#if>
                <div class="item btns" >
                    <input type="submit" class="btn btn-blue btn-block btn-large" value="login" style="cursor:pointer"/>
                </div>
            </form>
        </div>
    </div>
    <div class="login-copyright">Copyright © 2018 nomo</div>
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