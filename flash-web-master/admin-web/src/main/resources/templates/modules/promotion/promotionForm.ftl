<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
	
		$(document).ready(function() {
		
			 jQuery.validator.methods.beginTime = function(value, element, param) {
			 
			    var endDate = jQuery(param).val();
			    var date1=new Date(Date.parse(endDate));
			    var date2=new Date(Date.parse(value));
			    
			    if(jQuery.browser.msie==true){
			      date1 = new Date(Date.parse(endDate.replace("-", "/")));
			      date2 = new Date(Date.parse(value.replace("-", "/")));
			    }
			    return date1 >= date2;
			  };
			  
			jQuery.validator.methods.endTime = function(value, element, param) {
			   var startDate = jQuery(param).val();
			   var date1=new Date(Date.parse(startDate));
			   var date2=new Date(Date.parse(value));
			   
			   if(jQuery.browser.msie==true){
			      date1 = new Date(Date.parse(startDate.replace("-", "/")));
			      date2 = new Date(Date.parse(value.replace("-", "/")));
			   }
			   return date1 <= date2;
			 };
			 
			$("#inputForm").validate({
				rules: {
					proName: {required:true},
					// beginDate: {beginTime:"#endDate"},
					endDate: {endTime:"#beginDate"},
					proTypeId: {required:true},
					couponId: {required:true}
				},
				messages: {
					proName: {required:"please enter "},
					// beginDate: {beginTime: "开始时间不能大于结束时间"},
					endDate: {endTime: "结束时间不能小于开始时间"},
					proTypeId: {required:"please choose one"},
					couponId: {required:"please choose one "}
				},
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="marketing.activityManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <@shiro.hasPermission name="sys:promotion:list">
	                <li>
	                    <span>
	                    	<#if obj.id??>
								<@spring.message code="form.edit"></@spring.message>
							<#else>
								<@spring.message code="form.add"></@spring.message>
							</#if>
	                    </span>
	                </li>
                </@shiro.hasPermission>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 					<a href="${ctx}/sys/pro/listPro"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">${((obj.id)??)?string('Edit','New')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="obj" action="${ctx}/sys/pro/savePromotion" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.activityName"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="proName" value="${obj.proName!''}" maxlength="25" class="form-control required"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.activityBeginTime"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" id="beginDate" readonly="readonly" name="beginDate" value="${(obj.beginDate?string("yyyy-MM-dd"))!}" class="form-control required input-small input-inline" onclick="WdatePicker()"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.activityEndTime"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" id="endDate" readonly="readonly" name="endDate" value="${(obj.endDate?string("yyyy-MM-dd"))!}" class="form-control required input-small input-inline" onclick="WdatePicker()"/>
						</div>
					</div>
					<#-- 
						<div class="form-group">
							<label class="col-md-3 control-label">活动地区:</label>
							<div class="col-md-4">	
								<@tags.selectList id="areaId" name="areaId" value="${obj.areaId!}" labelName="areaIdLabel" labelValue="${obj.areaLabel!}"  title='please choose城市' url="/sys/pro/selectCityData" cssClass="form-control" cssStyle="width: 400px; height: 100px;" row="5" col="220"/>
							</div>
						</div>
					-->
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.activityType"></@spring.message>:</label>
						<div class="col-md-8">
							<select id="proTypeId" name="proTypeId" class="input-small form-control">
								<option value=''><@spring.message code="marketing.pleaseChoose"></@spring.message></option>
								<#foreach type in listType>
									<option value='${type.id!}' ${(obj.proTypeId?? && obj.proTypeId==type.id)?string("selected='selected'", "")}>${type.typeName!}</option>
								</#foreach>
							<select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.coupon"></@spring.message>:</label>
						<div class="col-md-8">
							<select id="couponId" name="couponId" class="input-small form-control">
								<option value=''><@spring.message code="marketing.pleaseChoose.coupon"></@spring.message></option>
								<#foreach coupon in listCoupon>
									<option value='${coupon.id!}' ${(obj.couponId?? && obj.couponId==coupon.id)?string("selected='selected'", "")}>${coupon.name!}</option>
								</#foreach>
							<select>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:promotion:modify">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"></@spring.message>" onclick="history.go(-1)"/>
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