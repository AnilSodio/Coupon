<#include "/include/taglib.ftl" >
<html>
<head>
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
                    <span>User Info management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Member management</span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
	             <li >
	                <a  href="${ctx}/sys/seller/list"><@spring.message code="form.list"></@spring.message> </a>
	            </li>
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">detail</a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/seller/save" method="post" class="form-horizontal">
				<@form.hidden path="uid"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 	
				 	
				 	<div class="form-group">
						<label class="col-md-3 control-label">Profile Picture:</label>
						<p class="form-control-static">
							<span class="item"><img src="${info.sellerLogo!}" width="120px" height="120px"></span>
						</p>
					</div>
					
		
				 	<div class="form-group">
						<label class="col-md-3 control-label">Business Name:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.sellerName !}</p>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">Country:</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
							</p>
						</div>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">Contact name:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.contactUserName !}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Contact phone number:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.contactMobile !}</p>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.mailbox"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.email !}</p>
						</div>
					</div>
					
					 
					<div class="form-group">
						<label class="col-md-3 control-label">Commission:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.rebateRate !}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.address"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.locationAddress !} ${info.locationDesc!} </p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">service hours:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.serviceTime !} </p>
						</div>
					</div>
					
					<#if referee ?? && referee.refereeName??>
						<div class="form-group">
							<label class="col-md-3 control-label">Introducer:</label>
							<div class="col-md-4">
								<p class="form-control-static">${referee.refereeName !} </p>
							</div>
						</div>
					</#if>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Business cover</label>
						
						<#if info.sellerCover1 ?? && info.sellerCover1?length gt 0>
							<img src="${info.sellerCover1!}" width="80" height="80"> &nbsp;&nbsp;
						</#if>
						
						<#if info.sellerCover2 ??  && info.sellerCover2?length gt 0>
							<img src="${info.sellerCover2!}" width="80" height="80">&nbsp;&nbsp;
						</#if>
						
						<#if info.sellerCover3 ??  && info.sellerCover3?length gt 0>
							<img src="${info.sellerCover3!}" width="80" height="80">&nbsp;&nbsp;
						</#if>
						
						<#if info.sellerCover4 ?? && info.sellerCover4?length gt 0>
							<img src="${info.sellerCover4!}" width="80" height="80"> &nbsp;&nbsp;
						</#if>
						
						<#if info.sellerCover5 ??  && info.sellerCover5?length gt 0>
							<img src="${info.sellerCover5!}" width="80" height="80">&nbsp;&nbsp;
						</#if>
						
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">inviteCode:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.inviteCode !} </p>
						</div>
					</div>
					
					<div  class="form-group">
						<label class="col-md-3 control-label">QR code:</label>
						<div class="col-md-4">
							<img src="data:image/jpg;base64,${info.twoCode!}">
						</div>
					</div>
					
				</div>
			</@form.form>
            </div>
        </div>
	</div>
	
</body>
</html>