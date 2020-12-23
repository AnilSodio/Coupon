\<#include "/include/taglib.ftl" >
<html>
<head>
	<title>用户反馈管理</title>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
		            remark:{required:true}
				},
				messages: {
					remark:{ required:"Please Enter Remarks"}
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
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span>feedback<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>deal with</span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            
            	<li >
						<a href="${ctx}/sys/feddback/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
             
            	<li class="active">
					<a data-toggle="tab" href="javascript:void(0)">deal with</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/feddback/save" method="post" class="form-horizontal">
				 <div class="form-body">
				 	<input type="hidden" name="id" value="${info.id!}"/>
				 	<input type="hidden" name="uid" value="${info.uid!}"/>
				 	<input type="hidden" name="orderId" value="${info.orderId!}"/>
				 	<input type="hidden" name="pbId" value="${info.pbId!}"/>
				 	
				 	<#if order??>
				 	<div class="form-group">
						<label class="col-xs-3 control-label">Box ID:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.sysCode !}</p>
						</div>
					</div>
					</#if>
					
					<#if order??>
						<div class="form-group">
							<label class="col-xs-3 control-label">Live Powerbank code:</label>
							<div class="col-xs-4">
								<p class="form-control-static">${order.pbCode !}</p>
							</div>
						</div>
					</#if>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">User's infomation:</label>
						<div class="col-xs-4">
						<p class="form-control-static">${info.nickname !}/${info.mobile !}</p>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">User's country:</label>
						<div class="col-xs-4">
							<p class="form-control-static">
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
							</p>
						</div>
					</div>
					
					<#if seller??>
					
						<div class="form-group">
							<label class="col-xs-3 control-label">Business Name:</label>
							<div class="col-xs-4">
								<p class="form-control-static">${seller.sellerName !}</p>
							</div>
						</div>
						
						<div class="form-group">
						<label class="col-xs-3 control-label">Business country:</label>
						<div class="col-xs-4">
							<p class="form-control-static">
								<#list countries as country>
									<#if seller.country ?? && seller.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
							</p>
						</div>
					</div>
					
					</#if>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">Feedback time:</label>
						<div class="col-xs-4">
							<p class="form-control-static">${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-xs-3 control-label">question type:</label>
						<div class="col-xs-4">
							<p class="form-control-static">
								<#if info.feedType?? && info.feedType == "1">
									Home
								<#elseif info.feedType ?? && info.feedType == "2">
									Using
								<#elseif info.feedType ?? && info.feedType == "3">
									<@spring.message code="stats.completed"></@spring.message>
								<#else>
								</#if>
							</p>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-xs-3 control-label">Description</label>
						<div class="col-xs-4">
							<p class="form-control-static">${info.feedDesc !}</p>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-xs-3 control-label">photo:</label>
						<div class="col-xs-4">
							<label class="lbl">
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
							</label>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class=" col-xs-3 control-label">Processing method:</label>
						<div class="col-md-4 col-xs-6">
							<p class="form-control-static user-role-list">
								  <input id="" type="checkbox" name="dealType" value="1" /> <@spring.message code="marketing.issuingCoupons"></@spring.message></label>
			                        <#if order?? >
			                        	 <#if order.status ?? && order.status == 30>
				                        	 <input type="checkbox" name="dealType" value="2" /> End of the trip</label>
				                        </#if>
			                        </#if>
		                        <#-- 
		                         <input type="checkbox" name="dealType" value="3" /> 标记故障车</label>
								-->
							</p>
						</div>
					</div>
		
					
					<div class="form-group">
						<label class="col-xs-3 control-label">
							<#if info.status == 1>
								Choose a coupon:
						 	<#else>
						 		<@spring.message code="marketing.coupon"></@spring.message>
						 	</#if> 
						</label>
						<div class="col-xs-4">
							<label class="lbl">
								<#if tickets ??>
		                      		<select name="ticketId" id="ticketId" class="select2 form-control input-small">
			                      		<option value="">--please choose--</option>
			                      		<#list tickets as t>
			                      			<option value="${t.id!}">${t.name!}, ${t.currency!} ${t.amount!}</option>
			                      		</#list>
		                      		</select>
		                      	<#else>
		                      		Sorry, no tickets yet!
		                      	</#if>
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label"><@spring.message code="sys.remarks"></@spring.message>:</label>
						<div class="col-xs-4">
							<@form.textarea path="remark" htmlEscape=false rows="3" maxlength="200" class="form-control input-xlarge"/>
						</div>
					</div>
					
					<#if channelStatusList??>
						<div class="form-group">
						<label class="col-md-3 control-label">Card slot information:</label>
						<div class="col-md-8">
							<p class="form-control-static">
									<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
						        <tr>
						            <th>Is it locked?</th>
						            <th>Left sensor at the origin</th>
						            <th>Right sensor at the origin</th>
						            <th>Channel button is pressed</th>
						            <th>Is it read id?</th>
						           
								</tr>
								<#list channelStatusList as channel>
									<tr>
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
					
					</#if>

					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:feed:list">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"></@spring.message>" onclick="history.go(-1)"/>
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