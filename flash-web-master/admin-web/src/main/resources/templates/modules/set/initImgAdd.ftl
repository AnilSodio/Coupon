<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				ignore: [],
				rules: {
					
					initImg:{ required:true }
					
				},
				messages: {
					
					initImg:{ required:"please enter" }
					
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
                    <span><@spring.message code="marketing.adManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                 <li>
                    <span><@spring.message code="marketing.startPageAdManagement"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
					<a  href="${ctx}/sys/initImg/list?imgType=1"><@spring.message code="marketing.adList"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">
					<#if info.id??>
						<@spring.message code="form.edit"></@spring.message>
					<#else>
						<@spring.message code="form.add"></@spring.message>
					</#if>
					</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/initImg/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<input type="hidden" name="imgType" value="1"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.name"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="name" value="${info.name !}" maxlength="128" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.linked"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="linkUrl" value="${info.linkUrl !}" maxlength="128"  class="form-control required"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.image"></@spring.message>:</label>
						<div class="col-md-4">
							  <div class="upload-pic-box">
		                        <input type="hidden" name="initImg" id="initImg" autocomplete="off" maxCount="1"  value="${info.initImg!}" class="form-control required"/>
		                        <input type="file" class="hidden" id="initImgFile" value="" name="imgFile" />
		                        <button type="button" id="initImgButton" class="btn btn-mini btn-default"><@spring.message code="sys.uploadImage"></@spring.message></button>
		                        <div class="form-tips"><@spring.message code="marketing.recommendedSize"></@spring.message>: 720 * 1280 pixels</div>
		                        <div id="initImgBox" class="view-pic-list">
		                        	<#if info.initImg ?? && info.initImg?length gt 0>
		                        		<span class="item"><img width="150px" height="150px" src="${info.initImg!}"><i class="remove js-remove-upload">×</i></span>
		                        	</#if>
		                        </div>
		                    </div>
						</div>
		              
					</div>
					
					
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
			    	
			    	var fileType = $file.val().split(".")[1];
			    	
			    	if(fileType != "jpg" &&  fileType != "png" ){
			    		top.$.jBox.tip("图片仅支持 jpg格式 和 png格式！");
			            return false;
			    	}
			    	
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
		            		"width":720,
			            	"height":1280,
			            	"size":300
			            },
			            success: function (data, status)  //服务器成功响应处理函数
			            {
			            	console.log(data);
			            	
			            	if(data.error != 0){
			            		top.$.jBox.tip(data.error);
			            		return false;
			            	}
			            	
			            	$view.append('<span class="item"><img src="'+ data.url +'" width="150px" height="150px" /><i class="remove js-remove-upload">×</i></span>');
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
		
		initUpload("initImg","initImgFile","initImgButton","initImgBox");
	</script>
</body>
</html>