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
                    <span>Financial Management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Merchant Withdrawal</span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	           
	            <li >
<@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
            	<li class="active">
					<a data-toggle="tab" href="javascript:void(0)"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	            
	            
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="" method="post" class="form-horizontal">
				 <div class="form-body">
				 	
				 	<div class="form-group">
						<label class="col-xs-3 control-label">服务商:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.sellerName !}</p>
						</div>
					</div>
			
					<div class="form-group">
						<label class="col-xs-3 control-label">手机号:</label>
						<div class="col-xs-4">
						<p class="form-control-static">${info.loginName !}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">审核人:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.checkName!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">审核时间:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${(info.checkTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">审核备注:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.cashAdminInfo!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">转账操作人:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.transName!}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label">转账时间:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${(info.transTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">转账费用:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.cmmsAmt/100}元</p>
						</div>
					</div>
					
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>