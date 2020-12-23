<#include "/include/taglib.ftl" >
<html>
<head>
    <title></title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                rules: {
                    capacity: {digits: true},
                    typeName: {
                        required: true,
                        remote: "${ctx}/sys/cabinetType/checkTypeName?id=" + $('#id').val()
                    }
                },
                messages: {
                    capacity: {required: "required"},
                    typeName: {
                        required: "required",
                        remote: "<@spring.message code='nomo.alrightExit'></@spring.message>"
                    }
                },
                submitHandler: function (form) {
                    loading('<@spring.message code="form.submitInfo"></@spring.message>');
                    form.submit();
                }
            });
            // ready for nation select
            <#if info.id??>
　　　　　　	var vCode=$('#supportNations').find("option:selected").attr("vCode"); 
　　　　　　	var vzhName=$('#supportNations').find("option:selected").attr("vzhName"); 
			var venName=$('#supportNations').find("option:selected").attr("venName");
			var vLanguage=$('#supportNations').find("option:selected").attr("vLanguage"); 
			var vCurrency=$('#supportNations').find("option:selected").attr("vCurrency"); 
			$("input[name='chineseName']").val(vzhName);
			$("input[name='englishName']").val(venName);
			$("input[name='defaultLanguage']").val(vLanguage);
			$("input[name='currency']").val(vCurrency);
			$("input[name='nationCode']").val(vCode);
            <#else>
           	var vCode=$('#supportNations option:first').attr("vCode"); 
　　　　　　	var vzhName=$('#supportNations option:first').attr("vzhName"); 
			var venName=$('#supportNations option:first').attr("venName");
			var vLanguage=$('#supportNations option:first').attr("vLanguage"); 
			var vCurrency=$('#supportNations option:first').attr("vCurrency"); 
			$("input[name='chineseName']").val(vzhName);
			$("input[name='englishName']").val(venName);
			$("input[name='defaultLanguage']").val(vLanguage);
			$("input[name='currency']").val(vCurrency);
			$("input[name='nationCode']").val(vCode);
            </#if>
            // change for nation select
            $('#supportNations').change(function(){  
	　　　　　　	var vCode=$(this).children('option:selected').attr("vCode"); 
	　　　　　　	var vzhName=$(this).children('option:selected').attr("vzhName"); 
				var venName=$(this).children('option:selected').attr("venName");
				var vLanguage=$(this).children('option:selected').attr("vLanguage"); 
				var vCurrency=$(this).children('option:selected').attr("vCurrency"); 
				$("input[name='chineseName']").val(vzhName);
				$("input[name='englishName']").val(venName);
				$("input[name='defaultLanguage']").val(vLanguage);
				$("input[name='currency']").val(vCurrency);
				$("input[name='nationCode']").val(vCode);
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
                <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span><@spring.message code="sys.settings"></@spring.message><i class="fa fa-angle-right"></i></span>
            </li>
            <li>
                <span><@spring.message code="sys.controlManage"></@spring.message></span>
            </li>
        </ul>
    </div>
    <div class="portlet light ">
        <ul class="nav nav-tabs mb-25">
            <li>
                <a href="${ctx}/sys/country/list"><@spring.message code="form.list"></@spring.message></a>
            </li>
            <li class="active">
                <a data-toggle="tab"
                   href="javascript:void(0);"><#if info.id??> <@spring.message code="form.edit"></@spring.message><#else ><@spring.message code="sys.newCountries"></@spring.message></#if></a>
            </li>
        </ul>
        <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/country/save" method="post" class="form-horizontal">
                <input type="hidden" name="id" value="${info.id!''}"/>
                <div class="form-body">
                	<div class="form-group">
                        <label class="col-md-3 control-label">Select Nation:</label>
                        <div class="col-md-4">
                            <select id="supportNations" name="supportNations" class="select2 form-control input-small">
                            	<#list supportNations as nation>
                            	<#if info.nationCode?? && info.nationCode == nation.code>
								<option selected="selected" vCode="${nation.code}" vzhName="${nation.zhName}" venName="${nation.enName}" vLanguage="${nation.language!''}" vCurrency="${nation.currency.currencyCode}">${nation.enName}</option>
								<#else>
								<option vCode="${nation.code}" vzhName="${nation.zhName}" venName="${nation.enName}" vLanguage="${nation.language!''}" vCurrency="${nation.currency.currencyCode}">${nation.enName}</option>								
								</#if>
								</#list>
							<select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.nationCode"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" readonly name="nationCode" value="${info.nationCode!''}" maxlength="50" class="form-control required"/>
                        </div>
                    </div>
                
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.chineseName"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" readonly name="chineseName" value="${info.chineseName!''}" maxlength="50" class="form-control required"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.englishName"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" readonly name="englishName" value="${(info.englishName)!''}" maxlength="50" class="form-control required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.defaultLanguage"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" readonly name="defaultLanguage" value="${(info.defaultLanguage)!''}"  maxlength="10" class="form-control required"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.languageDescription"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" name="languageDesc" value="${(info.languageDesc)!''}" maxlength="100" class="form-control required"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.currency"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" readonly name="currency" value="${(info.currency)!''}" maxlength="10"  class="form-control required"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.deposit"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" name="deposit" value="${(info.deposit)!''}" maxlength="5"
                                   class="form-control required number"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-md-3 control-label"><@spring.message code="sys.callCenter"></@spring.message>
                            :</label>
                        <div class="col-md-4">
                            <input type="text" name="callCenter" value="${(info.callCenter)!''}" maxlength="13"
                                   class="form-control required"/>
                        </div>
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:cabinetType:modify">
									<input id="btnSubmit" class="btn btn-primary" type="submit"
                                           value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
                                </@shiro.hasPermission>
                                <input id="btnCancel" class="btn red" type="button"
                                       value="<@spring.message code="form.goback"></@spring.message>"
                                       onclick="history.go(-1)"/>
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