<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				ignore: [],
				rules: {
					sellerName: {remote: "${ctx}/seller/checkSellerName"},
					contactMobile: {remote: "${ctx}/seller/checkContactMobile"},
					email: {remote: "${ctx}/seller/checkEmail"},
					country: {required:true}
				},
				messages: {
					sellerName:{ remote:"The name already exists"},
					contactMobile:{ remote:"The phone already exists"},
					email:{ remote:"The email already exists"},
					country:{ required:"please enter"} 
				},    
				submitHandler: function(form){
					loading('Submitting, please wait...');
					form.submit();
				}
			});
		
		});
		
		
		function addLatAndLon() {
			top.jBox("iframe:${ctx}/seller/selectLonAndLat",{
                title: "please choose address",
                width: 900,
                height: 500,
                submit: function() {
	                var obj = $(parent.document.body).find('#jbox-iframe').contents().find('#google_location input');
	              	console.log(obj.val());
	              	var result = obj.val();
	              	
					$("#location_lat").val(result.split(":")[0]);
					$("#location_lon").val(result.split(":")[1]);
					$("#locationAddress").val(result.split(":")[2]);
					$("#lonAndLat").html(result.split(":")[2]);
                }
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
                    <span><@spring.message code="agent.merchant.add"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	             <li>
 				 	<a href="${ctx}/cabinet/list"><@spring.message code="agent.index.cabinetList"></@spring.message></a>
	            </li>
	            <li>
					<a  href="${ctx}/cabinet/cabinetDelivery?id=${cid!}"><@spring.message code="agent.merchant.putin"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="agent.merchant.add"></@spring.message></a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/seller/save" method="post" class="form-horizontal">
				<input type="hidden" name="cid" value="${cid!}"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.picture"></@spring.message>:</label>
						<div class="col-md-4">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerLogo" id="sellerLogo" autocomplete="off" maxCount="1"  value="${info.sellerLogo!}"/>
			                    <input type="file" class="hidden" id="sellerLogoFile" value="" name="imgFile" />
			                    <button type="button" id="sellerLogoButton" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                   	<div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 300 * 300 pixels</div>
			                    <div id="sellerLogoBox" class="view-pic-list">
			                    	<#if info.sellerLogo ?? && info.sellerLogo?length gt 0>
			                    		<span class="item"><img src="${info.sellerLogo!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="headImg" class="error"></label>
						</div>
					</div>
		
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.name"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="sellerName" value="${info.sellerName !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.country"></@spring.message>:</label>
						<div class="col-md-4">
							<select id="country" name="country" class="select2 form-control input-small">
		           				<option value=""><@spring.message code="agent.form.all"></@spring.message></option>
                	 			<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
									<#else>
										<option value="${country.nationCode}">${country.englishName!}</option>
									</#if>
								</#list>
                	 		</select>
						</div>
					</div>
				 	
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.contact"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="contactUserName" value="${info.contactUserName !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.mobile"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="contactMobile" value="${info.contactMobile !}"  placeholder="86-13520894287" maxlength="32" class="form-control required"/>
						</div>
					</div>
					
				
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.email"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="email" value="${info.email !}"  maxlength="32" class="form-control required email"/>
						</div>
					</div>
					
					<#-- 
					<div class="form-group">
						<label class="col-md-3 control-label">Commission:</label>
						<div class="col-md-4">
							<input type="text" name="rebateRate" value="${info.rebateRate !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					 -->
					 
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.address"></@spring.message>:</label>
						<div class="col-md-4">
							
							<p id="lonAndLat" class="form-control-static">
								<#if info.locationAddress ??>
									${info.locationAddress!}
								</#if>
							</p>
							
							<a class="form-control-static" href="javascript:addLatAndLon();">
								<@spring.message code="agent.form.addLatLon"></@spring.message>
							</a>
							<input type="hidden" id ="locationAddress" name="locationAddress" value="${info.locationAddress!}" class="form-control required"/>
							<input type="hidden" id="location_lon" name="locationLon" value="${info.locationLon!}"/>
							<input type="hidden" id="location_lat"  name="locationLat" value="${info.locationLat!}" />
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.address.desc"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" id ="locationDesc" name="locationDesc" value="${info.locationDesc!}" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.openTime"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="serviceTime" value="${info.serviceTime !}"  maxlength="32" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.merchant.business"></@spring.message>Business:</label>
						
						<div class="col-md-2">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerCover1" id="sellerCover1" autocomplete="off" maxCount="1"  value="${info.sellerCover1!}"/>
			                    <input type="file" class="hidden" id="sellerCover1File" value="" name="imgFile" />
			                    <button type="button" id="sellerCover1Button" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                    <div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 720 * 405 pixels</div>
			                    <div id="sellerCover1Box" class="view-pic-list">
			                    	<#if info.sellerCover1 ?? && info.sellerCover1?length gt 0>
			                    		<span class="item"><img src="${info.sellerCover1!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="sellerCover1" class="error"></label>
						</div>
						<div class="col-md-2">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerCover2" id="sellerCover2" autocomplete="off" maxCount="1"  value="${info.sellerCover2!}"/>
			                    <input type="file" class="hidden" id="sellerCover2File" value="" name="imgFile" />
			                    <button type="button" id="sellerCover2Button" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                    <div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 720 * 405 pixels</div>
			                    <div id="sellerCover2Box" class="view-pic-list">
			                    	<#if info.sellerCover2 ?? && info.sellerCover2?length gt 0>
			                    		<span class="item"><img src="${info.sellerCover2!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="sellerCover2" class="error"></label>
						</div>
						<div class="col-md-2">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerCover3" id="sellerCover3" autocomplete="off" maxCount="1"  value="${info.sellerCover3!}"/>
			                    <input type="file" class="hidden" id="sellerCover3File" value="" name="imgFile" />
			                    <button type="button" id="sellerCover3Button" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                    <div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 720 * 405 pixels</div>
			                    <div id="sellerCover3Box" class="view-pic-list">
			                    	<#if info.sellerCover3 ?? && info.sellerCover3?length gt 0>
			                    		<span class="item"><img src="${info.sellerCover3!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="sellerCover3" class="error"></label>
						</div>
						
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-2">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerCover4" id="sellerCover4" autocomplete="off" maxCount="1"  value="${info.sellerCover4!}"/>
			                    <input type="file" class="hidden" id="sellerCover4File" value="" name="imgFile" />
			                    <button type="button" id="sellerCover4Button" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                    <div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 720 * 405 pixels</div>
			                    <div id="sellerCover4Box" class="view-pic-list">
			                    	<#if info.sellerCover4 ?? && info.sellerCover4?length gt 0>
			                    		<span class="item"><img src="${info.sellerCover4!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="sellerCover4" class="error"></label>
						</div>
						<div class="col-md-2">
							  <div class="upload-pic-box">
			                    <input type="hidden" name="sellerCover5" id="sellerCover5" autocomplete="off" maxCount="1"  value="${info.sellerCover5!}"/>
			                    <input type="file" class="hidden" id="sellerCover5File" value="" name="imgFile" />
			                    <button type="button" id="sellerCover5Button" class="btn btn-mini btn-default"><@spring.message code="agent.form.upload"></@spring.message></button>
			                    <div class="form-tips"><@spring.message code="agent.form.upload.imageSize"></@spring.message>: 720 * 405 pixels</div>
			                    <div id="sellerCover5Box" class="view-pic-list">
			                    	<#if info.sellerCover5 ?? && info.sellerCover5?length gt 0>
			                    		<span class="item"><img src="${info.sellerCover5!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
			                    	</#if>
			                    </div>
			                </div>
			                <label for="sellerCover5" class="error"></label>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="save"/>&nbsp;
								<input id="btnCancel" class="btn red" type="button" value="go back" onclick="history.go(-1)"/>
							</div>
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
			            top.$.jBox.tip("Please delete first, and then re-upload！");
			            return false;
			        }
				    	
			        $.ajaxFileUpload({
			        
			            url:'${ctx}/upload', //用于文件上传的服务器端请求地址
			            secureuri: false, //是否需要安全协议，一般设置为false
			            fileElementId: fileId, //文件上传域的ID
			            dataType: 'json', //返回值类型 一般设置为json
			            data:{
			            	"size":200
			            },
			            
			            success: function (data, status)  //服务器成功响应处理函数
			            {
			            	console.log(data);
			            	
			            	if(data.error != 0){
			            		top.$.jBox.tip(data.error);
			            		return false;
			            	}
			            	
			            	$view.append('<span class="item"><img src="'+ data.url +'" width="120px" height="120px"/><i class="remove js-remove-upload">×</i></span>');
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
			        $(this).parent().remove();
			        $input.val(valArr.join("*"));
			  });
			}
		
		initUpload("sellerLogo","sellerLogoFile","sellerLogoButton","sellerLogoBox");
		initUpload("sellerCover1","sellerCover1File","sellerCover1Button","sellerCover1Box");
		initUpload("sellerCover2","sellerCover2File","sellerCover2Button","sellerCover2Box");
		initUpload("sellerCover3","sellerCover3File","sellerCover3Button","sellerCover3Box");
		initUpload("sellerCover4","sellerCover4File","sellerCover4Button","sellerCover4Box");
		initUpload("sellerCover5","sellerCover5File","sellerCover5Button","sellerCover5Box");
	</script>
	
</body>
</html>