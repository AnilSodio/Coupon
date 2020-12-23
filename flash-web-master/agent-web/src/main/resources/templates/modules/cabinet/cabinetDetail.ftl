<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
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
                    <span>NOMO Box Manage<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	<@spring.message code="stats.details"></@spring.message>
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/cabinet/list">NOMOBOX list</a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="stats.details"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<div class="form-horizontal">
			 	<div class="form-group">
					<label class="col-md-3 control-label">NOMObox ID:</label>
					<div class="col-md-4">
						<p class="form-control-static">${info.sysCode!}</p>
					</div>
				</div>
				 <div class="form-body">
				 	<div class="form-group">
						<label class="col-md-3 control-label">box IMEI ID:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.cabinetCode!}</p>
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-3 control-label">model type:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.typeName!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">status:</label>
						<div class="col-md-4">
							<p class="form-control-static">
							<#if info.isDelivery ?? && info.isDelivery>
				            		Delivered
				            	<#elseif info.isDelivery ?? && !info.isDelivery>
									Not running
				            	</#if>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.address"></@spring.message>:</label>
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