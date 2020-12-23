<#include "/include/taglib.ftl" >
<html>
<head>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					
					cardDesc:{
						required:true
					},
					playformDefaultCurrency:{
						required:true
					},
					businessModel:{
						required:true
					}
					
				},
				messages: {
					
					playformDefaultCurrency:{
						required:"please choose"
					},
					cardDesc:{
						required:"请收入"
					},
					businessModel:{
						required:"please choose商业模式"
					}
					
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		
			$('input:radio[name="isOpenMemberCard"]').change(function(){
			
				var value = $('input:radio[name="isOpenMemberCard"]:checked').val();
				if(1 == value){
					$("#cardDescId").attr("style","display:block");
				}
				if(0 == value){
					$("#cardDescId").attr("style","display:none");
				}
			});
			
			$('input:radio[name="isNeedAuthen"]').change(function(){
			
				var value = $('input:radio[name="isNeedAuthen"]:checked').val();
				
				if(1 == value){
					$("#isNeedUpCertificatesId").attr("style","display:block");
					$("#authenTypeId").attr("style","display:block");
					$("#isNeedAuthenCheckId").attr("style","display:block");
				}
				if(0 == value){
					$("#isNeedUpCertificatesId").attr("style","display:none");
					$("#authenTypeId").attr("style","display:none");
					$("#isNeedAuthenCheckId").attr("style","display:none");
				}
			});
			
		});
		
	    function cipherEncryption(){
 		
 			 top.jBox.confirm('确定要进行密码加密操作吗 ?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('处理中，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/warning/cipherEncryption",
			                 	type:"POST",
			                 	success:function(data){
			                 		loading(data);
			                 	}
			          	 });
	            }
	            return true;
 			 });
 			 
	    }
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
                    <span>参数管理<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>系统参数<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>管理员参数修改</span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">管理员参数修改</a>
	            </li>
	            
	            <li >
					<a  href="javascript:cipherEncryption();">密码加密</a>
	            </li>
	            
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/warning/save_admin" method="post" class="form-horizontal">
				 <div class="form-body">
				 
				 	
				 	<div class="form-group">
						<label class="col-md-2 control-label">通讯地址:</label>
						<div class="col-md-3">
							<input type="text" name="communicationIp" value="${info.communicationIp !}"  maxlength="16" class="form-control required"/>
						</div>
						
						<label class="col-md-2 control-label">通讯端口:</label>
						<div class="col-md-3">
							<input type="text" name="communicationPort" value="${info.communicationPort !}"  maxlength="10" class="form-control required number"/>
						</div>
					</div>
				 	<div class="form-group">
						
						<label class="col-md-2 control-label">通讯域名:</label>
						<div class="col-md-3">
							<input type="text" name="communicationDomainName" value="${info.communicationDomainName !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label">平台默认币种:</label>
						<div class="col-md-3">
							 <p class="form-control-static">
							 	<#list currencies as currency>
                            	<#if info.playformDefaultCurrency?? && info.playformDefaultCurrency == currency>
								<label ><input type="radio" name="playformDefaultCurrency" value="${currency}"  checked=checked />${currency}</label>
								<#else>
								<label ><input type="radio" name="playformDefaultCurrency" value="${currency}" /> ${currency}</label>								
								</#if>
								</#list>
							 </p>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label">是否自主注册:</label>
						<div class="col-md-3">
							 <p class="form-control-static">
								 <label ><input type="radio" name="isUserRegister" value="1" <#if info.isUserRegister ?? && info.isUserRegister> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isUserRegister" value="0" <#if info.isUserRegister ?? && !info.isUserRegister> checked=checked </#if>/> 否</label>
							 </p>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label">是否开启余额支付:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isOpenBalance" value="1" <#if info.isOpenBalance ?? && info.isOpenBalance> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isOpenBalance" value="0" <#if info.isOpenBalance ?? && !info.isOpenBalance> checked=checked </#if>/> 否</label>
							 </p>
						</div>
						
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label">是否开启会员体系:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isOpenMemberCard" value="1" <#if info.isOpenMemberCard ?? && info.isOpenMemberCard> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isOpenMemberCard" value="0" <#if info.isOpenMemberCard ?? && !info.isOpenMemberCard> checked=checked </#if>/> 否</label>
							 </p>
						</div>
						
					</div>
					
					<div class="form-group" id="cardDescId" <#if info.id?? && info.isOpenMemberCard><#else>style="display:none"</#if> >
						
						<label class="col-md-2 control-label">会员卡描述:</label>
						<div class="col-md-3">
							<@form.textarea path="cardDesc" htmlEscape=false rows="5"  maxlength="1025" class="form-control input-xlarge"/>
						</div>
						
					</div>
					
					<div class="form-group">
						
						<label class="col-md-2 control-label">是否需要认证:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isNeedAuthen" value="1" <#if info.isNeedAuthen ?? && info.isNeedAuthen> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isNeedAuthen" value="0" <#if info.isNeedAuthen ?? && !info.isNeedAuthen> checked=checked </#if>/> 否</label>
							 </p>
						</div>
					</div>
					
					<div class="form-group" id="authenTypeId" <#if info.isNeedAuthen?? && info.isNeedAuthen><#else>style="display:none"</#if> >
						
						<label class="col-md-2 control-label">认证类型:</label>
						<div class="col-md-3">
							 <p class="form-control-static">
								<label> <input type="radio" name="authenType"  value="id_card" <#if info.authenType ?? && info.authenType == 'id_card'> checked=checked</#if>/> 身份证认证</label> &nbsp;&nbsp;
                        		<label> <input type="radio" name="authenType"  value="student_card" <#if info.authenType ?? && info.authenType == 'student_card'> checked=checked</#if>/>学生证认证</label>
							 </p>
						</div>
						
					</div>
					
					<div class="form-group" id="isNeedUpCertificatesId" <#if info.isNeedAuthen?? && info.isNeedAuthen><#else>style="display:none"</#if>>
						
						<label class="col-md-2 control-label">是否需要上传证件:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isNeedUpCertificates" value="1" <#if info.isNeedUpCertificates ?? && info.isNeedUpCertificates> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isNeedUpCertificates" value="0" <#if info.isNeedUpCertificates ?? && !info.isNeedUpCertificates> checked=checked </#if>/> 否</label>
							 </p>
						</div>
						
					</div>
					
					
					<div class="form-group" id="isNeedAuthenCheckId" <#if info.isNeedAuthen?? && info.isNeedAuthen><#else>style="display:none"</#if>>
						
						<label class="col-md-2 control-label">认证是否需要审核:</label>
						<div class="col-md-3">
							<p class="form-control-static">
								 <label ><input type="radio" name="isNeedAuthenCheck" value="1" <#if info.isNeedAuthenCheck ?? && info.isNeedAuthenCheck> checked=checked </#if>/> 是</label>&nbsp;&nbsp;
								 <label ><input type="radio" name="isNeedAuthenCheck" value="0" <#if info.isNeedAuthenCheck ?? && !info.isNeedAuthenCheck> checked=checked </#if>/> 否</label>
							 </p>
						</div>
					</div>
					
					<div class="form-group">
							
						<label class="col-md-2 control-label">app下载地址:</label>
						<div class="col-md-3">
							<input type="text" name="appDownloadUrl" value="${info.appDownloadUrl !}" placeholder="app的下载地址"  class="form-control"/>
						</div>	
					</div>
						
					<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-4">
						<@shiro.hasPermission name="sys:warning:modify">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
						</@shiro.hasPermission>
						<input id="btnCancel" class="btn red" type="button" value="返回" onclick="history.go(-1)"/>
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
			            	
			            	$view.append('<span class="item"><img src="'+ data.url +'" /><i class="remove js-remove-upload">×</i></span>');
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
	
<link href="https://static.thinker.vc/static/wgt/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="https://static.thinker.vc/static/wgt/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="https://static.thinker.vc/static/wgt/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
</body>
</html>