<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜投放管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					sellerId: {required:true},
				},
				messages: {
					sellerId: {required:"必选项"},
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
		
		function queryType(uid) {
			var sellerId = uid.value;
			if("" == sellerId){
				$("#google_location").attr("style","display:none");
	    		$("#location_details").attr("style","display:none");
			}
			 $.ajax({
	    		url: "${ctx}/sys/seller/findOne?sellerId="+sellerId,  
	    		success:function(data)
	    		{
	    			 $("#lonAndLat").html(data.locationAddress);
	    			 $("#locationAddress").val(data.locationAddress);
	    			 $("#location_lon").val(data.locationLon);
	    			 $("#location_lat").val(data.locationLat);
	    			 $("#locationDesc").val(data.locationDesc);
	    			 
	    			 $("#google_location").attr("style","display:block");
	    			 $("#location_details").attr("style","display:block");
	    		}
			}); 
		}
 		
 		function addLatAndLon() {
			top.jBox("iframe:${ctx}/sys/seller/selectLonAndLat",{
                title: "please choose address",
                width: 900,
                height: 500,
                submit: function() {
	                var obj = $(parent.document.body).find('#jbox-iframe').contents().find('#google_location input');
	              	console.log(obj.val());
	              	var result = obj.val();
	              	
					$("#location_lat").val(result.split(":")[0]);
					$("#location_lon").val(result.split(":")[1]);
					$("#locationAddress").val(result.split(":")[2]);
					$("#lonAndLat").html(result.split(":")[2]);
                }
       		});
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
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="role.nomoBox"></@spring.message></span>
                </li>
            
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 						<a data-toggle="tab" href="${ctx}/sys/cabinet/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="nomo.putIn"/></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/cabinet/cabinetDeliverySave" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
			 	
			 	<div class="form-group">
					<label class="col-md-3 control-label"><@spring.message code="nomo.nomoBoxId"/></label>
					<div class="col-md-4">
						<p class="form-control-static">${info.sysCode !}</p>
					</div>
				</div>
				
			 	<div class="form-group">
					<label class="col-md-3 control-label"><@spring.message code="nomo.code"/>:</label>
					<div class="col-md-4">
						<p class="form-control-static">${info.cabinetCode !}</p>
					</div>
				</div>
					
			 	<div class="form-group">
					<label class="col-md-3 control-label"><@spring.message code="nomo.merchant"/>:</label>
					<div class="col-md-4">
						<select id="uid" name="uid" class="select2 form-control input-small" onchange="queryType(this)">
							<#if (seller_list?size > 0)>
			           			<option value=""><@spring.message code="nomo.pleaseChoose"/></option>
			           			<#list seller_list as g>
	                	 			<option value="${g.uid!}" <#if info.sellerId?? && info.sellerId == g.uid >selected='selected'</#if> >${g.sellerName!}</option>
	                	 		</#list>
	                	 	<#else>
	                	 		<option value=""><@spring.message code="form.noData"/></option>
	                	 	</#if>
            	 		</select>
					</div>
				</div>
				 
				<div class="form-group" id="google_location" style="display:none">
						<label class="col-md-3 control-label"><@spring.message code="nomo.address"/>:</label>
						<div class="col-md-4">
							
							<p id="lonAndLat" class="form-control-static">
								
							</p>
							
							<a class="form-control-static" href="javascript:addLatAndLon();">
								Click Edit
							</a>
							
							<input type="hidden" id ="locationAddress" name="locationAddress" value="" class="form-control required"/>
							<input type="hidden" id="location_lon" name="locationLon" value=""/>
							<input type="hidden" id="location_lat"  name="locationLat" value="" />
						</div>
					</div>
					
					<div class="form-group" id="location_details" style="display:none">
						<label class="col-md-3 control-label">Address Description:</label>
						<div class="col-md-4">
							<input type="text" id ="locationDesc" name="locationDesc" value="" class="form-control required"/>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:cabinet:list">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message> "/>&nbsp;
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