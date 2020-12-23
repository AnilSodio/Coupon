<#macro validateCode name inputCssStyle>
<input type="text" id="${name}" name="${name}" maxlength="5" class="txt very-code"  placeholder="verify code" />
<img src="${request.contextPath}/servlet/validateCodeServlet" onclick="$('.${name}Refresh').click();" class="mid ${name}" style="float:right;margin-right:40px;"/>
<a href="javascript:" onclick="$('.${name}').attr('src','${request.contextPath}/servlet/validateCodeServlet?'+new Date().getTime());" class="mid ${name}Refresh"></a>
</#macro>