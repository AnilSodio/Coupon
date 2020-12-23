<#import "/tags/tags.ftl" as tags >
<#import "/include/spring.ftl" as spring >
 
<#assign shiro=JspTaglibs["http://shiro.apache.org/tagss"] />
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<#assign fns=JspTaglibs["http://java.sun.com/jsp/jstl/functionss"] />
 
<#assign ctx="${request.contextPath}${config.adminPath}" /> 
<#assign ctxStatic="${request.contextPath}" />
<#assign pubStatic="${config.staticPuthPath}" />
<#assign ctxAssets="${request.contextPath}/assets" />
<#assign lang=springMacroRequestContext />