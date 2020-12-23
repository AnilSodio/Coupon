<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="agent.member.info"></@spring.message></title>
	<script type="text/javascript">
	</script>
</head>
<body>
    <div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="agent.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="agent.member.info"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
						<@spring.message code="agent.form.detail"></@spring.message>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li >
					<a  href="${ctx}/customer/list">List</a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="agent.form.detail"></@spring.message></a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/customer/save" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.member.headImgPath"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<#if info.headImgPath??>
									<img src="${info.headImgPath!}" width="50px" height="50px">
								<#else>
									<img src="/images/defuatcover.png" width="50px" height="50px">
								</#if>
							</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.member.nickname"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.nickname!}/${info.mobile!} </p>
							<p class="form-control-static">
								<#if isBingdingWx?? && isBingdingWx>
									Bindings of WeChat account
								</#if>
								<#if isBingdingFB?? && isBingdingFB>
									Bindings of Facebook account
								</#if>
							</p>
							
						</div>
					</div>
					
					
					<#if is_open_balance?? && is_open_balance>
					
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.balance"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
									${info.currency!} ${info.balance!0}
								</p>
							</div>
						</div>
					</#if>
					
					<#if is_open_vip?? && is_open_vip>
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.vip"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
									<#if info.isVIP?? && info.isVIP>
										yes
									<#else>
										no
									</#if>
								</p>
							</div>
						</div>
						
						<#if info.isVIP?? && info.isVIP>
							<div class="form-group">
								<label class="col-md-3 control-label">yes,valid till:</label>
								<div class="col-md-4">
									<p class="form-control-static">
										${(info.vipExpiresIn?string('yyyy-MM-dd HH:mm:ss'))!''}
									</p>
								</div>
							</div>
						</#if>
					</#if>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="agent.member.register"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}
							</p>
						</div>
					</div>
					
					<#if u_regist?? && u_regist >
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.check"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
					            	
					            	<#if info.authStatus ?? && info.authStatus == "0">
					            		not certified
					            	<#elseif info.authStatus ?? && info.authStatus == "1">
					            		Authenticating
					            	<#elseif info.authStatus ?? && info.authStatus == "2">
					            		Authentication successful
					            	<#elseif info.authStatus ?? && info.authStatus == "3">
					            		Authentication failed
					            	</#if>
								</p>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.check.step"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
					            	
					            	<#if info.authStep ?? && info.authStep == 1>
					            		Phone Number Verified
					            	<#elseif info.authStep ?? && info.authStep == 2>
					            		No Deposit
					            	<#elseif info.authStep ?? && info.authStep == 3>
					            		Verified
					            	<#elseif info.authStep ?? && info.authStep == 4>
					            		user verified
					            	</#if>
								</p>
							</div>
						</div>
					</#if>
					
					<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.cabinet.id"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
									 ${info.sysCode!}
								</p>
							</div>
						</div>
					
					<#if u_regist?? && !u_regist>
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.number"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">
									${info.idCard!}
								</p>
							</div>
						</div>
					</#if>
					
					<#if setting.isNeedAuthen?? && setting.isNeedAuthen>
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.idcard"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.idCard!}</p>
							</div>
						</div>
					</#if>
					
					<#if setting.isNeedAuthen?? && setting.isNeedAuthen && setting.authenType?? && setting.authenType == 'student_card'>
						
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.school"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.schoolName!} </p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"><@spring.message code="agent.member.studentId"></@spring.message>:</label>
							<div class="col-md-4">
								<p class="form-control-static">${info.studentId!} </p>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-xs-3 control-label"><@spring.message code="agent.member.studentCard"></@spring.message>:</label>
							
							<#if img_list ?? && img_list?size gt 0>
						
								<#list img_list as img>
								  		
								  		<div class="col-md-2">
											  <div class="upload-pic-box" id="imgUrl1Container">
							                        <div id="imgUrl1Box" class="view-pic-list">
							                        	<#if img ?? && img ?length gt 0>
							                        		<a target="_blank" href="${img!}">
							                        			<span class="item"><img src="${img!}" width="120px" height="120px"></span>
							                        		</a>
							                        	</#if>
							                        </div>
							                    </div>
							                    <label for="imgUrl1" class="error"></label>
										</div>
								</#list>
								
							</#if>	
						</div>
					</#if>
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>