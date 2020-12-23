<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜信息查询</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>	
	<script type="text/javascript">
	</script>
</head>
<body>
    <div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-<@spring.message code="home"></@spring.message>"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="form.detail"></@spring.message></span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <#if type?? && type == "cabientOnline">
	            <li><a href="${ctx}/sys/googleMap/cabinetOnline"><@spring.message code="online.liveData"></@spring.message></a></li>
	            <#elseif type ?? && type = "">
	            <li> <a href="${ctx}/sys/cabinet/list"><@spring.message code="role.nomoBox"></@spring.message></a></li>
	            <#else>
	            <li> <a href="${ctx}/sys/googleMap/initMap"><@spring.message code="online.onMap"></@spring.message></a></li>
	            </#if>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/order/save" method="post" class="form-horizontal">
				 <div class="form-body">
				 	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Basic Information</span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Box ID:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.sysCode!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Box Code:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.cabinetCode!}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Box Type:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.typeName!}</p>
						</div>
					</div>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Merchant Information</span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Merchant Name:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.sellerName!} </p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Business Add:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.locationAddress!}<br/>${info.locationDesc!}</p>
						</div>
					</div>
					
					<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Agent Information</span>
					    </div>
					  </div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">Agent Name:</label>
						<div class="col-md-4">
							<p class="form-control-static">${info.agentName!} </p>
						</div>
					</div>
					
			    	<div class="portlet mt-20 ">
					  <div class="portlet-title">
					    <div class="caption">
					      <span class="caption-subject font-green bold">Box Slots</span>
					    </div>
					  </div>
					</div>
					
		    		<#if channelList ?? &&  (channelList?size > 0)>
		    		
		    		<div class="form-group">
						<div class="col-md-8">
							<p class="form-control-static">
								<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
						        <tr>
						            <th>slot ID</th>
						            <th>locked?</th>
						            <th>left sensor is ok?</th>
						            <th>right sensor is ok?</th>
						            <th>Channel button is pressed</th>
						            <th>Whether to read id</th>
								</tr>
								<#list channelList as channel>
									<tr>
							            <td>
							            	${channel.pbCode!}
							            </td>
							            <td>
							            	<#if channel.lock?? && channel.lock>
							            		yes
							            	<#elseif channel.lock?? && !channel.lock>
							            		no
							            	</#if>
							            </td>
							            <td>
							            	<#if channel.leftOrigin?? && channel.leftOrigin>
							            		yes
							            	<#elseif channel.leftOrigin?? && !channel.leftOrigin>
							            		no
							            	<#else>
							            	</#if>
							            </td>
							            <td>
							            	<#if channel.rightOrigin?? && channel.rightOrigin>
							            		yes
							            	<#elseif channel.rightOrigin?? && !channel.rightOrigin>
							            		no
							            	<#else>
							            	</#if>
							            </td>
							            <td>
							            	<#if channel.isButton ?? && channel.isButton>
							            		yes
							            	<#elseif channel.isButton ?? && !channel.isButton>
							            		no
							            	<#else>
							            	</#if>
							            </td>
							            <td>
							            	<#if channel.isReadId?? && channel.isReadId>
							            		yes
							            	<#elseif channel.isReadId?? && !channel.isReadId>
							            		no
							            	<#else>
							            	</#if>
							            </td>
							            
									</tr>
								</#list>
							</table>
							
							</p>
						</div>
					</div>
					<#else>
						<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
					</#if>
					
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>