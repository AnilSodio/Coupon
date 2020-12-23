<#include "/include/taglib.ftl" >
<html>
<head>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					corporateName:{
						required:true
					},
					appName:{
						required:true
					},
					
					mapSearchScope:{
						required:true,
						number:true,
						digits:true,
						min:1,
						max:200000
					},
					
					isOnlineTime:{
						required:true,
						digits:true,
						min:1
					}
				},
				messages: {
					corporateName:{
						required:"required"
					},
					appName:{
						required:"required"
					},
					 
					mapSearchScope:{
						required:"required",
						number:"please enter number",
						digits: "Please enter only digits.",
						max: $.validator.format( "Please enter a value less than or equal to {0}." ),
   						min: $.validator.format( "Please enter a value greater than or equal to {0}." )
					},
					isOnlineTime:{
						required:"required",
						digits: "Please enter only digits.",
						min: $.validator.format( "Please enter a value greater than or equal to {0}." )
					}
					
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
			
		});
 		
 		<#-- 
 			$('input:radio[name="isAutomaticRefund"]').change(function(){
			
				var value = $('input:radio[name="isAutomaticRefund"]:checked').val();
				if(1 == value){
					$("#refundAccountId").attr("style","display:none");
				}
				if(0 == value){
					$("#refundAccountId").attr("style","display:block");
				}
			});
 		-->
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
                    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="sys.settings"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/warning/save" method="post" class="form-horizontal">
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="sys.PLATFORMRELATED"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.platformName"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="corporateName" value="${info.corporateName !}"  maxlength="128" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.platformLOGO"></@spring.message>:</label>
						<div class="col-md-6">
							  <div class="upload-pic-box">
		                        <input type="hidden" name="logoImg" id="logoImg" autocomplete="off" maxCount="1"  value="${info.logoImg!}"/>
		                        <input type="file" class="hidden" id="logoImgFile" value="" name="imgFile" />
		                        <button type="button" id="logoImgButton" class="btn btn-mini btn-default"><@spring.message code="sys.uploadImage"></@spring.message></button>
		                        <div id="logoImgBox" class="view-pic-list" style="height: 46px;">
	                        		<span class="item">
	                        			<#if info.logoImg??>
	                        			<img src="${info.logoImg!''}" style="width: 220px; height: 44px;">
	                        			</#if>
	                        			<i class="remove js-remove-upload">×</i></span>
		                        </div>
		                    </div>
		                    <label for="logoImage" class="error"></label>
						</div>
						
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">Logo RGB:</label>
						<div class="col-md-3">
							<input type="text" name="logoRgb" value="${info.logoRgb!}"  maxlength="12" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.defaultLanguage"></@spring.message>:</label>
						<div class="col-md-3">
							 <p class="form-control-static">
								 <label ><input type="radio" name="defaultLanguage" value="1" <#if info.defaultLanguage ?? && info.defaultLanguage == '1'> checked=checked </#if>/> Chinese Simplified</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="defaultLanguage" value="0" <#if info.defaultLanguage ?? && info.defaultLanguage == '0'> checked=checked </#if>/> English</label>
							 </p>
						</div>
					</div>
					
					
					<div class="form-group">
					
						<label class="col-md-2 control-label"><@spring.message code="sys.applicationName"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="appName" value="${info.appName !}"  maxlength="128" class="form-control required"/>
						</div>
						<label class="col-md-2 control-label">Application Name:</label>
						<div class="col-md-3">
							<input type="text" name="appNameEnglish" value="${info.appNameEnglish !}"  maxlength="128" class="form-control"/>
						</div>
						
					</div>
					<div class="form-group">
					
						<label class="col-md-2 control-label"><@spring.message code="sys.paymentTypes"></@spring.message>:</label>
						<div class="col-md-3">
							 <p class="form-control-static">
								<label> <input type="checkbox" name="payWay"  value="1" <#if info.payWay ?? &&(info.payWay?index_of('1') != -1)> checked=checked</#if>/> WeChat</label> &nbsp;&nbsp;
                        		<label> <input type="checkbox" name="payWay"  value="2" <#if info.payWay ?? &&(info.payWay?index_of('2') != -1)> checked=checked</#if>/> Alipay</label>
							 </p>
						</div>
						
					</div>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold"><@spring.message code="sys.PARAMETERRELATED"></@spring.message></span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label"><@spring.message code="sys.isOpenAd"></@spring.message>:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isOpenAd" value="1" <#if info.isOpenAd ?? && info.isOpenAd> checked=checked </#if>/> yes</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isOpenAd" value="0" <#if info.isOpenAd ?? && !info.isOpenAd> checked=checked </#if>/> no</label>
							 </p>
						</div>
						
						<label class="col-md-2 control-label"><@spring.message code="sys.adDuration.second"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="imgTime" value="${info.imgTime !}"  maxlength="2" class="form-control required number"/>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label"> <@spring.message code="sys.isRefundAutomatically"></@spring.message>:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isAutomaticRefund" value="1" <#if info.isAutomaticRefund ?? && info.isAutomaticRefund> checked=checked </#if>/> yes</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isAutomaticRefund" value="0" <#if info.isAutomaticRefund ?? && !info.isAutomaticRefund> checked=checked </#if>/> no</label>
							 </p>
						</div>
					</div>
					
					
					<div class="form-group">
						
						<label class="col-md-2 control-label"><@spring.message code="sys.appDownloadAddress"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="appDownloadUrl" value="${info.appDownloadUrl !}" placeholder="app的下载地址"  class="form-control"/>
						</div>	
						
					</div>
					
					
					<div class="form-group">
						
						<label class="col-md-2 control-label"><@spring.message code="sys.androidDownloadAddress"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="androidDownloadUrl" value="${info.androidDownloadUrl !}" placeholder="app的下载地址"  class="form-control"/>
						</div>	
						
						<label class="col-md-2 control-label"><@spring.message code="sys.IOS.DownloadAddress"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="iosDownloadUrl" value="${info.iosDownloadUrl !}" placeholder="app的下载地址"  class="form-control"/>
						</div>	
						
					</div>

					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.isOnlineTime.min"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="isOnlineTime" value="${info.isOnlineTime !}"  maxlength="10" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group" style="display:none">
						<label class="col-md-2 control-label">Introductor rebate rate:</label>
						<div class="col-md-3">
							<input type="text" name="introductroRebateRate" value="${info.introductroRebateRate!}"  maxlength="6" class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.mapSearchScope.km"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="mapSearchScope" value="${info.mapSearchScope!}"  maxlength="4" class="form-control number required"/>
						</div>
					</div>
					
					<#-- 
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.deposit.deduction"></@spring.message>:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isOpenDepositDeduction" value="1" <#if info.isOpenDepositDeduction ?? && info.isOpenDepositDeduction> checked=checked </#if>/> yes</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isOpenDepositDeduction" value="0" <#if info.isOpenDepositDeduction ?? && !info.isOpenDepositDeduction> checked=checked </#if>/> no</label>
							 </p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.deduction.days"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="deductionDays" value="${info.deductionDays!}"  maxlength="2" class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.hours.day"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="hourOfDay" value="${info.hourOfDay!}"  maxlength="2" class="form-control number required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.send.sms"></@spring.message>:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isOpenSendSms" value="1" <#if info.isOpenSendSms ?? && info.isOpenSendSms> checked=checked </#if>/> yes</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isOpenSendSms" value="0" <#if info.isOpenSendSms ?? && !info.isOpenSendSms> checked=checked </#if>/> no</label>
							 </p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label"><@spring.message code="sys.sendSms.hour"></@spring.message>:</label>
						<div class="col-md-3">
							<input type="text" name="sendSmsHour" value="${info.sendSmsHour!}"  maxlength="2" class="form-control number required"/>
						</div>
					</div>
					-->
					
					<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-4">
						<@shiro.hasPermission name="sys:warning:modify">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
						</@shiro.hasPermission>
						<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"></@spring.message>" onclick="history.go(-1)"/>
						</div>
					</div>
				
				</div>
			</@form.form>
            </div>
        </div>
	</div>
	<script type="text/javascript" src="/ajaxfileupload.js"></script>
	<script>
		function initUpload(inputId, fileId, buttonId, viewId) {
			    var $input = $("#" + inputId), $file = $("#" + fileId), $button = $("#" + buttonId), $view = $("#" + viewId), uploadFun;
			    $button.click(function() {
			    	$("#" + fileId).click();
			    });
			    uploadFun = function() {
			    	var $file = $(this);
			        if (!$.trim($file.val())) return false;
			    	if ($view.find(".item").length == $input.attr("maxCount")) {
			            alert("Please delete first, and then re-upload！");
			            return false;
			        }
				    	
			        $.ajaxFileUpload({
			        
			            url:'${ctx}/upload', //用于文件上传的服务器端请求地址
			            secureuri: false, //是否需要安全协议，一般设置为false
			            fileElementId: fileId, //文件上传域的ID
			            dataType: 'json', //返回值类型 一般设置为json
			            success: function (data, status)  //服务器成功响应处理函数
			            {
			            	console.log(data);
			            	
			            	if(data.error != 0){
			            		top.$.jBox.tip(data.error);
			            		return false;
			            	}
			            	
			            	$view.append('<span class="item"><img src="'+ data.url +'" style="width: 110px; height: 100px;" /><i class="remove js-remove-upload">×</i></span>');
			                if ($.trim($input.val())) {
			                    $input.val($input.val() + "*" + data.url);
			                } else {
			                    $input.val(data.url);
			                }
			            	
			                if (typeof (data.error) != 'undefined') {
			                    if (data.error == 1) {
			                        alert(data.message);
			                    } 
			                }
			                $file.val("").empty();
			            },
			            error: function (data, status, e)//服务器响应失败处理函数
			            {
			                alert(e);
			            }
			        });
			    };
				    
			    $("body").on("change", "#" + fileId, function() {
			    	uploadFun.call(this);
			    });
			    $view.on("click",".js-remove-upload",function(){
			        var valArr = $input.val().split("*");
			        valArr.splice($(this).parent().index(),1);
			        $("#" + fileId).val("");
			        $(this).parent().remove();
			        $input.val(valArr.join("*"));
			  });
			}
		
		initUpload("logoImg","logoImgFile","logoImgButton","logoImgBox");
	</script>
</body>
</html> 