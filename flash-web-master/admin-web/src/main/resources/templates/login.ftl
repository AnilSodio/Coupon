<#include "/include/taglib.ftl" >
<!DOCTYPE html>
<html>
<head>
    <title><@spring.message code="sys.product.name"></@spring.message></title>
    <#include "/include/head.ftl" >
    <link rel="stylesheet" href="${ctxStatic}/common/typica-login.css">
	<link rel="stylesheet" href="${pubStatic}/bootstrap/v3/bootstrap.min.css">
    <link rel="stylesheet" href="${pubStatic}/bootstrap/fonts/font-awesome-4/css/font-awesome.min.css">
    <!-- Custom styles for this template -->
    <link href="${ctxStatic}/common/style.css" rel="stylesheet" />
    <style type="text/css">
        .container { position: absolute; width: 100%; padding: 0 120px;}
        .mid { padding-left: 10px; padding-top: 6px;}
        .control-group {
            border-bottom: 0px;
        }
        .login-ie-warn .number { float: left;}
        .login-ie-warn .details { margin-left: 140px;}
        .login-ie-warn { width: 500px; margin: 100px auto 0; background-color: #fff; padding: 20px; filter:alpha(opacity=80); opacity: 0.8;}
    </style>
    <script src="${pubStatic}/common/backstretch.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.backstretch([
                "${ctxStatic}/images/bg1.jpg",
                "${ctxStatic}/images/bg2.jpg",
                "${ctxStatic}/images/bg3.jpg"
            ], {duration: 10000, fade: 2000});

            $("#loginForm").validate({
                rules: {
                    validateCode: {remote: "${request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "Please enter your username."}, password: {required: "Please fill in the password."},
                    validateCode: {remote: "Incorrect verification code.", required: "Please fill in the verification code."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架中，则跳转刷新上级页面
        if (self.frameElement && self.frameElement.tagName == "IFRAME") {
            parent.location.reload();
        }
    </script>
</head>
<body>

<div class="container">
    <!--[if lte IE 8]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
    <div id="messageBox" class="alert alert-error <#if !message?? >hide</#if>">
    <button data-dismiss="alert" class="close" style="right: 0;">×</button>
    <label id="loginError" class="error">
    	<#if message?? >  
    		<#if message == '用户名或者密码错误'>
    			User name or password error
    		<#else>
    			${message}
    		</#if>
    	</#if>
    </label>
</div>

</div>
<div id="cl-wrapper" class="login-container">

    <div class="middle-login">
        <div class="block-flat">
            <div class="header">
                <h3 class="text-center"><@spring.message code="sys.product.name"></@spring.message></h3>
            </div>
            <div>
                <form id="loginForm" style="margin-bottom: 0px !important;" class="form-horizontal" action="/login" method="post">
                    <div class="content">
                        <h4 class="title">User login</h4>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="icon-user"></i></span>
                                    <input type="text" placeholder="log in account" id="username" name="username" class="form-control required" value="${username!}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="icon-lock"></i></span>
                                    <input type="password" id="password" class="form-control required" name="password" placeholder="Password">
                                </div>
                            </div>
                        </div>
						<#if isValidateCodeLogin?? && isValidateCodeLogin >
							<div class="form-group">
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-question"></i></span>
                                        <@tags.validateCode name="validateCode" inputCssStyle="margin-bottom:0;" />
                                    </div>
                                </div>
                            </div>
						</#if>
                    </div>
                    <div class="foot">
                        <input class="btn btn-primary" type="submit" value="Login"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="text-center out-links"><a href="#">&copy; <@spring.message code="sys.product.copyright"></@spring.message> <@spring.message code="sys.product.name"></@spring.message></a></div>
    </div>

</div>
</body>
</html>