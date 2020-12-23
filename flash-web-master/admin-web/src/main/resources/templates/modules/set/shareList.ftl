<#include "/include/taglib.ftl" >
<html>
<head>
	<title>分享设置管理</title>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					isAllowShare:{
						required:true
					},
					isAllowInvite:{
						required:true
					},
					shareWay:{
						required:true
					},
					inviteWay:{
						required:true
					}
					
				},
				messages: {
					isAllowShare:{
						required:"please enter"
					},
					isAllowInvite:{
						required:"please enter"
					},
					shareWay:{
						required:"please enter"
					},
					inviteWay:{
						required:"please enter"
					}
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
		
		
		function queryIsShare(share) {
		
			var share = share.value;
			
			if("" == share){
				$("#isAllowShareId").attr("style","display:none");
			}
			
			if("1" == share){
				$("#isAllowShareId").attr("style","display:block");
			}
			
			if("0" == share){
				$("#isAllowShareId").attr("style","display:none");
			}
		}
		
		function queryIsInvite(invite) {
			var invite = invite.value;
			
			if("" == invite){
				$("#isAllowInviteId").attr("style","display:none");
			}
			
			if("1" == invite){
				$("#isAllowInviteId").attr("style","display:block");
			}
			
			if("0" == invite){
				$("#isAllowInviteId").attr("style","display:none");
			}
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
                    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="sys.sharingMessagess"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a  href="javascript:void(0);"><@spring.message code="sys.sharingMessagess"></@spring.message></a>
	            </li>
	            <#if info?? && info.id ??>
	            <#else>
		            <li>
						<a  href="${ctx}/sys/share/modify">${(info.id??)?string('Edit','Added')}</a>
		            </li>
	            </#if>
	        </ul>
            <div class="portlet-body form">
            <#if info?? && info.id ??>
				<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/share/save" method="post" class="form-horizontal">
					<@form.hidden path="id"/>
					 <div class="form-body">
					 
					 	
					 	<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.whetherToAllowSharing"></@spring.message>:</label>
							<div class="col-md-4">
								<select id="isAllowShare" name="isAllowShare" class="select2 form-control input-small" onchange="queryIsShare(this)">
									<option value="">please choose</option>
									<option value="1" <#if info.isAllowShare?? && info.isAllowShare>selected='selected'</#if>>yes</option>
									<option value="0" <#if info.isAllowShare?? && !info.isAllowShare>selected='selected'</#if>>no</option>
								</select>
							</div>
						</div>
					
						<div class="form-group" id="isAllowShareId" <#if info.isAllowShare?? && !info.isAllowShare>style="display:none"</#if>>
							<label class="col-md-3 control-label"><@spring.message code="sys.shareWay"></@spring.message>:</label>
							<div class="col-md-4">
								 <p class="form-control-static">
									<label> <input type="checkbox" name="shareWay"  value="1" <#if info.shareWay ?? &&(info.shareWay?index_of('1') != -1)> checked=checked</#if>/> WeChat</label> &nbsp;&nbsp;&nbsp;&nbsp;
	                        		<label> <input type="checkbox" name="shareWay"  value="2" <#if info.shareWay ?? &&(info.shareWay?index_of('2') != -1)> checked=checked</#if>/> WeChat Circle of Friends</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="shareWay"  value="3" <#if info.shareWay ?? &&(info.shareWay?index_of('3') != -1)> checked=checked</#if>/> QQ</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="shareWay"  value="4" <#if info.shareWay ?? &&(info.shareWay?index_of('4') != -1)> checked=checked</#if>/> QQ Space</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 </p>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.shareTitle"></@spring.message>:</label>
							<div class="col-md-4">
								<input type="text" name="shareTitle" value="${info.shareTitle !}"  maxlength="128" class="form-control  required"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.shareContent"></@spring.message>:</label>
							<div class="col-md-4">
								<@form.textarea path="shareContent" htmlEscape=false rows="3" maxlength="200" class="form-control input-xlarge"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.whetherToInviteFriends"></@spring.message>:</label>
							<div class="col-md-4">
								<select id="isAllowInvite" name="isAllowInvite" class="select2 form-control input-small" onchange="queryIsInvite(this)">
									<option value="">please choose</option>
									<option value="1" <#if info.isAllowInvite?? && info.isAllowInvite>selected='selected'</#if>>Yes</option>
									<option value="0" <#if info.isAllowInvite?? && !info.isAllowInvite>selected='selected'</#if>>No</option>
								</select>
							</div>
						</div>
						
						<div class="form-group" id="isAllowInviteId" <#if info.isAllowInvite?? && !info.isAllowInvite>style="display:none"</#if>>
							<label class="col-md-3 control-label"><@spring.message code="sys.inviteWay"></@spring.message>:</label>
							<div class="col-md-4">
								 <p class="form-control-static">
									<label> <input type="checkbox" name="inviteWay"  value="1" <#if info.inviteWay ?? &&(info.inviteWay?index_of('1') != -1)> checked=checked</#if>/> WeChat</label> &nbsp;&nbsp;&nbsp;&nbsp;
	                        		<label> <input type="checkbox" name="inviteWay"  value="2" <#if info.inviteWay ?? &&(info.inviteWay?index_of('2') != -1)> checked=checked</#if>/> WeChat Circle of Friends</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="inviteWay"  value="3" <#if info.inviteWay ?? &&(info.inviteWay?index_of('3') != -1)> checked=checked</#if>/> QQ</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 	<label> <input type="checkbox" name="inviteWay"  value="4" <#if info.inviteWay ?? &&(info.inviteWay?index_of('4') != -1)> checked=checked</#if>/> QQspace</label>&nbsp;&nbsp;&nbsp;&nbsp;
								 </p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.invitationTitle"></@spring.message>:</label>
							<div class="col-md-4">
								<input type="text" name="inviteTitle" value="${info.inviteTitle !}"  maxlength="128" class="form-control  required"/>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="sys.invitationContent"></@spring.message>:</label>
							<div class="col-md-4">
								<@form.textarea path="inviteContent" htmlEscape=false rows="3" maxlength="200" class="form-control input-xlarge"/>
							</div>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
							<@shiro.hasPermission name="sys:share:list">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.edit"></@spring.message>"/>&nbsp;
							</@shiro.hasPermission>
							</div>
						</div>
					</div>
					
				</@form.form>
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords"></@spring.message>
				</div>
			</#if>
            </div>
        </div>
	</div>
</body>
</html>