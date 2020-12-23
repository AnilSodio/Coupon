<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#uid").val("${info.sellerId!}").select2();
			$("#inputForm").validate({
				rules: {
					sellerId: {required:true},
				},
				messages: {
					sellerId: {required:"not null"},
				},    
				submitHandler: function(form){
					loading('Submitting, please wait...');
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
	    		url: "${ctx}/seller/findOne?sellerId="+sellerId,  
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
			top.jBox("iframe:${ctx}/seller/selectLonAndLat",{
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
                    <span>NOMO box<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	Put in
                    </span>
                </li>
                
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 				 	<a href="${ctx}/cabinet/list">NOMOBOX list</a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">Put in</a>
	            </li>
	            <li>
 				 	<a href="${ctx}/seller/add?cid=${info.id!}">add merchant</a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/cabinet/cabinetDeliverySave" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
			 	
			 	<div class="form-group">
					<label class="col-md-3 control-label">NOMO Box ID:</label>
					<div class="col-md-4">
						<p class="form-control-static">${info.sysCode !}</p>
					</div>
				</div>
				
			 	<div class="form-group">
					<label class="col-md-3 control-label">Asset Number:</label>
					<div class="col-md-4">
						<p class="form-control-static">${info.cabinetCode !}</p>
					</div>
				</div>
					
			 	<div class="form-group">
					<label class="col-md-3 control-label">business name:</label>
					<div class="col-md-4">
						<select id="uid" name="uid" class="select2 form-control input-small" onchange="queryType(this)">
							<#if (seller_list?size > 0)>
			           			<option value="">please choose</option>
			           			<#list seller_list as g>
	                	 			<option value="${g.uid!}">${g.sellerName!}</option>
	                	 		</#list>
	                	 	<#else>
	                	 		<option value="">No business information</option>
	                	 	</#if>
            	 		</select>
					</div>
				</div>
				 
				<div class="form-group" id="google_location" <#if !info.sellerId??>style="display:none"</#if>>
						<label class="col-md-3 control-label"><@spring.message code="marketing.address"></@spring.message>:</label>
						<div class="col-md-4">
							
							<p id="lonAndLat" class="form-control-static">
								${info.locationAddress!}
							</p>
							
							<a class="form-control-static" href="javascript:addLatAndLon();">
								Click Edit
							</a>
							
							<input type="hidden" id ="locationAddress" name="locationAddress" value="" class="form-control required"/>
							<input type="hidden" id="location_lon" name="locationLon" value="${info.locationLon!}"/>
							<input type="hidden" id="location_lat"  name="locationLat" value="${info.locationLat!}" />
						</div>
					</div>
					
					<div class="form-group" id="location_details"  <#if !info.sellerId??>style="display:none"</#if>>
						<label class="col-md-3 control-label">Address Description:</label>
						<div class="col-md-4">
							<input type="text" id ="locationDesc" name="locationDesc" value="${info.locationDesc}" class="form-control required"/>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="save "/>&nbsp;
								<input id="btnCancel" class="btn red" type="button" value="go back" onclick="history.go(-1)"/>
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