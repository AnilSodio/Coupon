<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				ignore: [],
				rules: {
				},
				messages: {
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
			
			
			$("#btnSubmit").click(function(){
			
				var msgType = $("#msgType").val();
				
				if("" == msgType){
					top.$.jBox.tip("please choose短信标题");
					return false;
				}
				
				top.jBox.confirm('确认要发送短信吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
    				if (v === 'ok') {
    					loading('正在发送，请稍等...');
         				$.ajax({
             				url:"${ctx}/sys/customer/sendMsg?uid="+${info.uid!}+"&msgType="+msgType,
             				type:"POST",
         					success:function(data){
             					if(1 == data) {
             						top.$.jBox.tip("恭喜，发送成功");
             						window.location.href="${ctx}/sys/customer/list";
             					}else{
             						top.$.jBox.tip("超过日限额");
             					}
         					}
             			});
    				}
    				return true;
 				});
			})
		
		});
		
		
		function queryContent(type) {
		
			var mark = type.value;
			
			if(mark == ""){
				$("#msgContent").attr("style","display:none");
				return false;
			}
			
			$.ajax({
	    		url: "${ctx}/sys/customer/queryContent",  
	    		data: {                    
	                  "mark": mark
			     },
	    		success:function(data)
	    		{
	    			$("#msgContent").attr("style","display:block");
	    			$("#pMsgContent").html(data);
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
                    <span>共享单车<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Member management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	发送短信
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
<@spring.message code="form.list"></@spring.message></a></li>
	             
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">发送短信</a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	<div class="form-group">
						<label class="col-md-3 control-label">用户名称:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.nickname! ''} </p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">用户手机:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.mobile! ''} </p>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">短信标题:</label>
						<div class="col-md-4">
							<select id="msgType" name="msgType" class="select2 form-control input-small" onchange="queryContent(this)">
			    	 			<option value="">please choose</option>
			    	 			<option value="illegal_parking_notice">违规停车通知</option>
			    	 			<option value="un_unlock_notice" >未关锁通知</option>
			    	 			<option value="fault_notice" >单车故障通知</option>
		    	 			</select>
						</div>
					</div>
					
					
					<div class="form-group" id="msgContent" style="display:none">
						<label class="col-md-3 control-label">短信内容:</label>
						<div class="col-md-4">
							<p class="form-control-static" id="pMsgContent"></p>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
							<@shiro.hasPermission name="sys:spot:modify">
								<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
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