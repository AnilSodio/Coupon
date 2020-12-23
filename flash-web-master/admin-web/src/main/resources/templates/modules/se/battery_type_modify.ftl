<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电宝类型信息管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					isHasLine: {required:true},
					lineType: {required:true},
					typeName: {remote: "${ctx}/sys/battery/checkTypeName?id="+$('#id').val()}
				},
				messages: {
					isHasLine: {required:"请输入"},
					lineType: {required:"please choose"},
					typeName: {remote:"已经存在"}
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
			
			$('input:radio[name="isHasLine"]').change(function(){
			
				var value = $('input:radio[name="isHasLine"]:checked').val();
				
				if(1 == value){
					$("#lineTypeId").attr("style","display:block");
				}
				if(0 == value){
					$("#lineTypeId").attr("style","display:none");
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>充电柜类型管理<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	${((info.id)??)?string('修改','添加')}
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">${((info.id)??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/battery/typeSave" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label">型号名称:</label>
						<div class="col-md-4">
							<input type="text" name="typeName" value="${info.typeName!}" maxlength="50"  class="form-control required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">最大电量:（毫安）</label>
						<div class="col-md-4">
							<input type="text" name="maxElectricity" value="${info.maxElectricity!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">最大电压:（伏特）</label>
						<div class="col-md-4">
							<input type="text" name="maxVoltage" value="${info.maxVoltage!}" maxlength="5"  class="form-control number required"/>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">是否有数据线:</label>
						<div class="col-md-4">
							 <p class="form-control-static">
								 <label ><input type="radio" name="isHasLine" value="1" <#if info.isHasLine ?? && info.isHasLine> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isHasLine" value="0" <#if info.isHasLine ?? && !info.isHasLine> checked=checked </#if>/> 否</label>
							 </p>
						</div>
					</div>
					
				 	<div class="form-group" id="lineTypeId" <#if info.isHasLine?? && info.isHasLine><#else>style="display:none"</#if> >
						<label class="col-md-3 control-label">数据线类型:</label>
						<div class="col-md-4">
							<select id="lineType" name="lineType" class="select2 form-control input-small required">
			           			<option value="">please choose</option>
			           			<option value="nomral" <#if info.lineType ?? && info.lineType == 'nomral'>selected='selected'</#if>> 安卓和苹果通用</option>
			           			<option value="Typc-C" <#if info.lineType ?? && info.lineType == 'Typc-C'>selected='selected'</#if>> 安卓 Typc-C </option>
			           		</select>
						</div>
					</div>

					<div class="form-group">
						
						<label class="col-md-3 control-label">描述:</label>
						<div class="col-md-4">
							<@form.textarea path="typeDesc" htmlEscape=false rows="5"  maxlength="1025" class="form-control input-xlarge"/>
						</div>
						
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:inteRule:list">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="返回" onclick="history.go(-1)"/>
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