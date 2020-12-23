<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		function addLatAndLon() {
			top.jBox("iframe:${ctx}/selectLonAndLat",{
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
                    <a target="_parent" href="${ctx}"><@spring.message code="merchant.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="merchant.index.info"></@spring.message></span>
                </li>
            </ul>
        </div>
        <#if message??><@tags.message content=message /></#if>
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <span class="caption-subject font-green bold"><@spring.message code="merchant.index.info"></@spring.message></span>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <@form.form id="inputForm" modelAttribute="seller" action="${ctx}/saveInfo" method="post" class="form-horizontal">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="merchant.info.email"></@spring.message>: </label>
                            <div class="col-md-4">
                                <p class="form-control-static"> ${(seller.email)!}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="merchant.info.contact"></@spring.message>: </label>
                            <div class="col-md-4">
                                <@form.input path="contactUserName" htmlEscape=false maxlength="50" class="form-control required"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><@spring.message code="merchant.info.mobile"></@spring.message>: </label>
                            <div class="col-md-4">
                                <@form.input path="contactMobile" htmlEscape=false maxlength="50" class="form-control required"/>
                            </div>
                        </div>
                        <div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.info.address"></@spring.message>:</label>
						<div class="col-md-4">
							<p id="lonAndLat" class="form-control-static">
								<#if seller.locationAddress ??>
									${seller.locationAddress!}
								</#if>
							</p>
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="merchant.info.addDesc"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" id ="locationDesc" name="locationDesc" value="${seller.locationDesc!}" class="form-control required"/>
						</div>
					</div>
                       
                    </div>

                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input id="btnSubmit" class="btn btn-primary" type="submit" value="save"/>
                            </div>
                        </div>
                    </div>
                    </@form.form>
            </div>
        </div>
	</div>
</body>
</html>