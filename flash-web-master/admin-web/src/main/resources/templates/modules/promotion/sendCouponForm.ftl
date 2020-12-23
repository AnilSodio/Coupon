<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function selectAll()
	    {
	    	$("input[name='userId']").each(function (index, item){
	    		if($(item).attr("checked") == "checked")
	    		{
	    			$(item).attr("checked", false);
	    		}
	    		else
	    			$(item).attr("checked", true);
	    	});
	    }
	 
	    $(document).ready(function(){
	    	var userIds = "";
	    	var mobiles="";
		    $("#sendCoupon").click(function(){
		    	$("input[name='userId']").each(function (index, item){
		    		if($(item).attr("checked") == "checked")
		    		{
		    			userIds += $(item).val() +";";
		    		}
		    	});
		    	if(userIds != "")
		    	{
		    		top.$.jBox.open("iframe:${ctx}/sys/pro/sendNum", "coupon number", 700, 400, {
	    				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	    		
							if (v=="ok"){
								var r=/^[0-9]+.?[0-9]*$/;
								var num = h.find("iframe").contents().find("#tripRemark").val();
								if(num == ""){
									top.$.jBox.tip("Please enter the quantity");
									return false;
								}
								if(!r.test(num)){
									top.$.jBox.tip("Please enter the number");
									return false;
								}
								if(num>100 || num <= 0){
									top.$.jBox.tip("Please enter a number less than 100");
									return false;
								}
								
								
								if(num>0){
									top.jBox.confirm('Do you want to send the coupon?？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
			            				if (v === 'ok') {
			            					loading('Being issued, please wait...');
			                 				$.ajax({
			                 				url:"${ctx}/sys/pro/saveSendCoupon?cid=${cid}&userIds="+userIds+"&num="+num,
			                 				type:"POST",
			                 					success:function(data){
				                 					if(1 == data) {
				                 						top.$.jBox.tip("Congratulations, issued success");
				                 						window.location.href="${ctx}/sys/pro/listCoupon";
				                 					}else{
				                 						top.$.jBox.tip("Unfortunately, the Send failed");
				                 					}
			                 					}
			                 				});
			            				}
			            				return true;
		 			 				});
								}
							}
						}
					});
					
		    	}
		    	else
		    	{
		    		top.$.jBox.tip("Choose at least one user");
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
				    <span><@spring.message code="marketing.activityManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="marketing.couponManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="marketing.issuingCoupons"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li>
 					<a  href="${ctx}/sys/pro/listCoupon"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:promotion:list">
	            <li >
	                <a href="${ctx}/sys/pro/addCoupon"><@spring.message code="form.add"></@spring.message> </a>
	            </li>
	            </@shiro.hasPermission>
				<@shiro.hasPermission name="sys:promotion:list">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:void(0);"><@spring.message code="marketing.issue"></@spring.message></a>
	            </li>
	            </@shiro.hasPermission>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/pro/sendCoupon" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'10'}"/>
					<input id="cid" name="cid" type="hidden" value="${cid!}"/>
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<label><@spring.message code="marketing.userS.phoneNumber"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
			</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="20"></th>
			            <th><@spring.message code="marketing.userS.nickname"></@spring.message></th>
			            <th><@spring.message code="marketing.userS.phoneNumber"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>
								<input type="checkbox" name="userId" value="${info.uid!}" />
							</td>
				            <td>${info.nickname!''}(${info.mobile!''})</td>
				            <td>${info.mobile!''}</td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination" style="width:100%">
				 	<div class="col-md-4" style="padding-left:10px;">
				 	<@shiro.hasPermission name="sys:promotion:list">
						<input type="checkbox" onclick="selectAll();" /> <@spring.message code="marketing.selectAll"></@spring.message>／ <@spring.message code="marketing.reElection"></@spring.message> &nbsp;&nbsp;&nbsp;&nbsp;
						<input id="sendCoupon" class="btn btn-primary" type="button" value="<@spring.message code="marketing.issuingCoupons"></@spring.message>"/>&nbsp;
					</@shiro.hasPermission>
					</div>
					<div class="col-md-8">
			        	${page}
					</div>
			    </div>
			<#else>
				<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
			</#if>
			</div>
        </div>
    </div>
</body>
</html>