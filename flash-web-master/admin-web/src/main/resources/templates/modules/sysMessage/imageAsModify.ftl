<#include "/include/taglib.ftl" >
<html>
<head>
	<meta charset="UTF-8">
	<title>新建图文</title>
	<script charset="utf-8" src="/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/editor/lang/zh_CN.js"></script>
	
	<script type="text/javascript">
	
		
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
                    <span><@spring.message code="marketing.homeAd.management"></@spring.message></span>
                </li>
            </ul>
        </div>

		<#if message??><@tags.message content=message /></#if>
		<div class="portlet light ">
				<ul class="nav nav-tabs mb-25">
		            <li >
						<a  href="${ctx}/sys/imageText/list?imageType=2">  <@spring.message code="marketing.adList"></@spring.message></a>
		            </li>
		            <li class="active">
		                <a  href="javascript:;">
	                                                     <@spring.message code="form.add"></@spring.message>
		            	 </a>
		            </li>
	        	</ul>
	            <div class="portlet-body form">
				<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/imageText/save" method="post" class="form-horizontal">
					<@form.hidden path="id"/>
					<input type="hidden" name="imageType" value="2"/>
					<input type="hidden" id="status" name="status" value="${info.status!}"/>
					
					<div class="form-body">
						
						<div class="form-group">
							<label class="col-xs-3 control-label"><@spring.message code="sys.title"></@spring.message>:</label>
							<div class="col-xs-4">
								<input type="text" name="title" value="${info.title!}" htmlEscape=false  maxlength="11" class="form-control required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.content"></@spring.message>:</label>
							<div class="col-md-4">
								<textarea id="content"  name="content" >${info.content!}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.cover"></@spring.message>:</label>
							<div class="col-md-4">
								  <div class="upload-pic-box">
				                    <input type="hidden" name="cover" id="cover" autocomplete="off" maxCount="1"  value="${info.cover!}" class="form-control required"/>
				                    <input type="file" class="hidden" id="coverFile" value="" name="imgFile" />
				                    <button type="button" id="coverButton" class="btn btn-mini btn-default"><@spring.message code="sys.uploadImage"></@spring.message></button>
				                    <div class="form-tips"><@spring.message code="marketing.recommendedSize"></@spring.message>: 600 * 700 pixels</div>
				                    <div id="coverBox" class="view-pic-list">
				                    	<#if info.cover ?? && info.cover?length gt 0>
				                    		<span class="item"><img src="${info.cover!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
				                    	</#if>
				                    </div>
				                </div>
				                <label for="cover" class="error"></label>
							</div>
						</div>
		
						<div class="form-group" id="adCoverId">
							<label class="col-md-3 control-label"><@spring.message code="marketing.image"></@spring.message>:</label>
							<div class="col-md-4">
								  <div class="upload-pic-box">
				                    <input type="hidden" name="adCover" id="adCover" autocomplete="off" maxCount="1"  value="${info.adCover!}" class="form-control required"/>
				                    <input type="file" class="hidden" id="adCoverFile" value="" name="imgFile" />
				                    <button type="button" id="adCoverButton" class="btn btn-mini btn-default"><@spring.message code="sys.uploadImage"></@spring.message></button>
				                    <div class="form-tips"><@spring.message code="marketing.recommendedSize"></@spring.message>: 600 * 700 pixels</div>
				                    <div id="adCoverBox" class="view-pic-list">
				                    	<#if info.adCover ?? && info.adCover?length gt 0>
				                    		<span class="item"><img src="${info.adCover!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
				                    	</#if>
				                    </div>
				                </div>
				                <label for="adCover" class="error"></label>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.summary"></@spring.message>:</label>
							<div class="col-sm-5">
			                	  <textarea name="remark" id="remark" rows="10" cols="140" class="form-control required">${info.remark!''}</textarea>
			                </div>
						</div>
						
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="marketing.effectiveTime"></@spring.message>:</label>
							<div class="col-md-4">
								<input type="text" id="startDateString" readonly="readonly" name="startDateString" value="${(info.startDate?string("yyyy-MM-dd"))!}" class="form-control required input-small input-inline" onclick="WdatePicker()"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="marketing.expirationTime"></@spring.message>:</label>
							<div class="col-md-4">
								<input type="text" id="invalidDateString" readonly="readonly" name="invalidDateString" value="${(info.invalidDate?string("yyyy-MM-dd"))!}" class="form-control required input-small input-inline" onclick="WdatePicker()"/>
							</div>
						</div>
						
						<div class="form-actions">
							<div class="row">
				                <div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:imageText:view">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
								<input id="saveImageText" class="btn btn-primary" type="button" value="<@spring.message code="marketing.saveAndEffective"></@spring.message>" onclick="saveImage()"/>&nbsp;
								</@shiro.hasPermission>
								</div>
				            </div>
						</div>
		
				</@form.form>
				</div>
	  	 </div>
	</div>
<script type="text/javascript" src="/ajaxfileupload.js"></script>
<script>
		$(document).ready(function() {
				
				$("#inputForm").validate({
					ignore: "",
					rules: {
						content: {required:true}
					},
					messages: {
						content:{ required:"必填信息"}
					},
					submitHandler: function(form){
						loading('<@spring.message code="form.submitInfo"></@spring.message>');
						form.submit();
					}
				});
			
				var optionsApp = {
		        filterMode : true,
				uploadJson:'/upload.htm',
				width : '600px',
				height:'300px',
				minWidth:'400px',
				allowImageUpload : true,
				syncType:"form",
				afterCreate : function() {
									var self = this;
									self.sync();
								},
				afterChange : function() {
									var self = this;
									self.sync();
								},
				afterBlur : function() {
									var self = this;
									self.sync();
								},
				items:['source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter',  '|',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage', '|', 'about']
				
			}
			Editor = KindEditor.create('#content', optionsApp);
			
			});
			
		
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
			            data:{
		            		"width":600,
			            	"height":700,
			            	"size":300,
			            	"isEqualComparison":true
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
			        $("#" + fileId).val("");
			        $(this).parent().remove();
			        $input.val(valArr.join("*"));
			  });
			}
		
		initUpload("cover","coverFile","coverButton","coverBox");
		initUpload("adCover","adCoverFile","adCoverButton","adCoverBox");
		
		
		function saveImage() {
			$("#status").val(2);
			$("#inputForm").submit();
		}
	</script>
	
</body>
</html>