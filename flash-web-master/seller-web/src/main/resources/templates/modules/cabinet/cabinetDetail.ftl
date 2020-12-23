<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="merchant.cabinet.info"></@spring.message></title>
	<script type="text/javascript">
	</script>
</head>
<body>
    <div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="merchant.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="merchant.cabinet.info"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	<@spring.message code="merchant.form.detail"></@spring.message>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/cabinet/list"><@spring.message code="merchant.info.canbinetList"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="merchant.form.detail"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<div class="form-horizontal">
			 	<div class="form-group">
					<label class="col-md-3 control-label"><@spring.message code="merchant.cabinet.id"></@spring.message>:</label>
					<div class="col-md-4">
						<p class="form-control-static">${info.sysCode!}</p>
					</div>
				</div>
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.cabinet.code"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.cabinetCode!}</p>
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.cabinet.type"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.typeName!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.cabinet.delivery"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">
							<#if info.isDelivery ?? && info.isDelivery>
				            		<@spring.message code="merchant.cabinet.delivery.usable"></@spring.message>
				            	<#elseif info.isDelivery ?? && !info.isDelivery>
									<@spring.message code="merchant.cabinet.delivery.disable"></@spring.message>
				            	</#if>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.cabinet.address"></@spring.message>:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.locationAddress!}</p>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<input id="btnCancel" class="btn red" type="button" value="return" onclick="history.go(-1)"/>
							</div>
						</div>
					</div>
				</div>
			</div>
            </div>
        </div>
	</div>
</body>
</html>