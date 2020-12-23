<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="form.detail"></@spring.message></title>

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
				    <span>feedback<i class="fa fa-angle-right"></i></span>
				</li>
				

				
                <li>
                    <span><@spring.message code="form.detail"></@spring.message></span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	       		 
	        	<li>
	                <a  href="${ctx}/sys/feddback/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	           
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0)"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/bicycle/save" method="post" class="form-horizontal">
				 <div class="form-body">
				 
				 	
					<div class="form-group">
						<label class="col-md-3 control-label">Box ID:</label>
						<p class="form-control-static">${info.sysCode !}</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="marketing.userS.nickname"></@spring.message>:</label>
						<p class="form-control-static">${info.nickname !}</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">User phone number:</label>
						<p class="form-control-static">${info.mobile !}</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Feedback time:</label>
						<p class="form-control-static">${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">description:</label>
						<p class="form-control-static">${info.feedDesc !}</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">photo</label>
						
						<#if info.imgUrl1 ?? && info.imgUrl1?length gt 0>
									<img src="${info.imgUrl1!}" width="80" height="80"> &nbsp;&nbsp;
						<#else>
						</#if>
						
						<#if info.imgUrl2 ??  && info.imgUrl2?length gt 0>
							<img src="${info.imgUrl2!}" width="80" height="80">&nbsp;&nbsp;
						<#else>
						</#if>
						
						<#if info.imgUrl3 ??  && info.imgUrl3?length gt 0>
							<img src="${info.imgUrl3!}" width="80" height="80">&nbsp;&nbsp;
						<#else>
						</#if>
						
						<#if info.imgUrl4 ??  && info.imgUrl4?length gt 0>
							<img src="${info.imgUrl4!}" width="80" height="80">&nbsp;&nbsp;
						<#else>
							
						</#if>
					</div>
					
					<div class="form-group">
					
						<label class="col-md-3 control-label">status:</label>
						
						<p class="form-control-static">
							<#if info.status?? && info.status == 0>
								Processed
							<#elseif info.status?? && info.status == 1>
								Not processed
							<#elseif info.status?? && info.status == 2>
								No need to deal with
							<#else>
							</#if>
						</p>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">Processing method:</label>
						
						<p class="form-control-static">
							<#if info.dealType ?? &&  info.dealType?index_of('1') != -1 >
								<#if coupon ?? && coupon.name ??>
									Tickets issued, the name is：${coupon.name!''}
								<#else>
								</#if>
							</#if>
							
							<#if info.dealType ?? &&  info.dealType?index_of('2') != -1 >
								End of the trip
							</#if>
							
							<#if info.dealType ?? &&  info.dealType?index_of('3') != -1 >
								Mark fault
							</#if>
						</p>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.remarks"></@spring.message>:</label>
						<p class="form-control-static">${info.remark !}</p>
						
					</div>
					
					
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>