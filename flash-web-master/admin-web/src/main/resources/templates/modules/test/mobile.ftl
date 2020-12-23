<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	
<link href="http://static.thinker.vc/static/wgt/jquery-jbox/2.3/Skins/Bootstrap/jbox.css" rel="stylesheet" />
<script src="http://static.thinker.vc/static/wgt/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="http://static.thinker.vc/static/wgt/jquery-jbox/2.3/i18n/jquery.jBox-zh-CN.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					mobile: {required:true},
				},
				messages: {
					mobile: {required:"必输字段"},
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		});
 		
 		
 		function pbBuyStatusRet(){
 			
 			var mobile = $("#mobile").val();
 			
 			if("" == mobile){
 				loading('手机号不能为空');
 				return false;
 			}
 			
 			$.ajax({
	    		url: "${ctx}/sys/pbreturn/returnStats",  
	    		data:{
	    			"mobile":mobile
	    		},
	    		success:function(data) {
	    			if("0000" != data){
	    				loading(data);
	    			}else {
	    				loading("恭喜，操作成功！");
	    			}
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
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	             
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">状态还原</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/pbreturn/returnStats" method="post" class="form-horizontal">
				
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label">用户手机号:</label>
						<div class="col-md-4">
							<input type="text" id="mobile" name="mobile" value="${info.mobile!}" maxlength="20" placeholder="86-13520894287"  class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<input id="btnSubmit" class="btn btn-primary" type="button" onclick="pbBuyStatusRet();" value="保 存"/>&nbsp;
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