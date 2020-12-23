<#include "/include/taglib.ftl" >
<html>
<head>
	<title>设置分享参数</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
				},
				messages: {
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
                    <span>参数管理<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>分享设置<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    <@shiro.hasPermission name="sys:warning:modify">
                    	${(info.id??)?string('修改','添加')}
                    </@shiro.hasPermission>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
<@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="${ctx}/sys/share/modify">${(info.id??)?string('修改','添加')}</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/share/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
							<label class="col-md-3 control-label">行程分享途径:</label>
							<div class="col-md-8">
								 <p class="form-control-static">
									<label> <input type="checkbox" name="shareWay"  value="1" <#if info.shareWay ?? &&(info.shareWay?index_of('1') != -1)> checked=checked</#if>/> WeChat</label> &nbsp;&nbsp;&nbsp;&nbsp;
	                        		<label> <input type="checkbox" name="shareWay"  value="2" <#if info.shareWay ?? &&(info.shareWay?index_of('2') != -1)> checked=checked</#if>/> 朋友圈</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="shareWay"  value="3" <#if info.shareWay ?? &&(info.shareWay?index_of('3') != -1)> checked=checked</#if>/> QQ</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="shareWay"  value="4" <#if info.shareWay ?? &&(info.shareWay?index_of('4') != -1)> checked=checked</#if>/> QQ空间</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 </p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">分享标题:</label>
							<div class="col-md-4">
								<input type="text" name="shareTitle" value="${info.shareTitle !}"  maxlength="128" class="form-control required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">分享内容:</label>
							<div class="col-md-4">
								<input type="text" name="shareContent" value="${info.shareContent !}"  maxlength="128" class="form-control required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label">邀请好友途径:</label>
							<div class="col-md-8">
								 <p>
									<label> <input type="checkbox" name="inviteWay"  value="1" <#if info.inviteWay ?? &&(info.inviteWay?index_of('1') != -1)> checked=checked</#if>/> WeChat</label> &nbsp;&nbsp;&nbsp;&nbsp;
	                        		<label> <input type="checkbox" name="inviteWay"  value="2" <#if info.inviteWay ?? &&(info.inviteWay?index_of('2') != -1)> checked=checked</#if>/> 朋友圈</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="inviteWay"  value="3" <#if info.inviteWay ?? &&(info.inviteWay?index_of('3') != -1)> checked=checked</#if>/> QQ</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="inviteWay"  value="4" <#if info.inviteWay ?? &&(info.inviteWay?index_of('4') != -1)> checked=checked</#if>/> QQ空间</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 </p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-md-3 control-label">分享标题:</label>
							<div class="col-md-4">
								<input type="text" name="inviteTitle" value="${info.inviteTitle !}"  maxlength="128" class="form-control required"/>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-md-3 control-label">分享内容:</label>
							<div class="col-md-4">
								<input type="text" name="inviteContent" value="${info.inviteContent !}"  maxlength="128" class="form-control required"/>
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
	
</body>
</html>