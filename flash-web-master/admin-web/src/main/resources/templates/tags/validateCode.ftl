<#macro validateCode name inputCssStyle>
<input type="text" id="${name}" name="${name}" maxlength="5" class="form-control required" style="font-weight:bold;width:145px;${inputCssStyle}" placeholder="验证码" />
<img src="${request.contextPath}/servlet/validateCodeServlet" onclick="$('.${name}Refresh').click();" class="mid ${name}"/>
<a href="javascript:" onclick="$('.${name}').attr('src','${request.contextPath}/servlet/validateCodeServlet?'+new Date().getTime());" class="mid ${name}Refresh">看不清</a>
</#macro>