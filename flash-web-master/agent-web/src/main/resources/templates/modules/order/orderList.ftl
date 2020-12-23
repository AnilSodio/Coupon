<#include "/include/taglib.ftl" >
<html>
<head>
	<title><@spring.message code="agent.index.orderList"></@spring.message></title>
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('<@spring.message code="agent.form.operate.tips"></@spring.message>', '<@spring.message code="agent.form.tips"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="agent.form.waiting"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip('<@spring.message code="agent.form.success"></@spring.message>');
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('<@spring.message code="agent.form.operate.tips"></@spring.message>', '<@spring.message code="agent.form.tips"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="agent.form.waiting"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip('<@spring.message code="agent.form.success"></@spring.message>');
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('<@spring.message code="agent.form.operate.tips"></@spring.message>', '<@spring.message code="agent.form.tips"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="agent.form.waiting"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip('<@spring.message code="agent.form.success"></@spring.message>');
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function showEndDialog(id) {
	    	
	    	top.$.jBox.open("iframe:${ctx}/sys/order/endForm?id="+id, "End use", 500, 250, {
	    	
	    		buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
	    		
						if (v=="ok"){
						
							var checkDesc = h.find("iframe").contents().find("#checkDesc").val();
							
							if(checkDesc == ""){
								top.$.jBox.tip("Please enter the note information");
								return false;
							}
							
							if(checkDesc != "" && checkDesc.length < 200)
							{
								top.jBox.confirm('<@spring.message code="agent.form.operate.tips"></@spring.message>', '<@spring.message code="agent.form.tips"></@spring.message>', function (v, h, f) {
					            if (v === 'ok') {
					                 $.ajax({
					                 	url:"${ctx}/sys/order/endTrip?id="+id+"&checkDesc="+checkDesc,
					                 	type:"POST",
					                 	success:function(data){
					                 		if(data == "0000")
					                 		{
					                 			top.$.jBox.close();
					                 			top.$.jBox.tip('<@spring.message code="agent.form.success"></@spring.message>');
					                 			window.location.reload();
					                 		}else{
					                 			top.$.jBox.tip(data);
					                 		}
					                 	}
					                 });
					            }
				 			 });
			            	return false;
						}
					}
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
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
                    <a target="_parent" href="${ctx}"><@spring.message code="agent.home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="agent.index.orderList"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">List</a>
	            </li>
	            
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/order/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="agent.cabinet.powerbankId"></@spring.message>：</label>
					<@form.input path="pbCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="agent.member.nickname"></@spring.message>：</label>
					<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="agent.member.phone"></@spring.message>：</label>
					<@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					<br/></br/>
					&nbsp;&nbsp;
					<label><@spring.message code="agent.order.status"></@spring.message>：</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value='' ><@spring.message code="agent.form.all"></@spring.message></opetion>
            	 			<option value="30" <#if vo.status ?? && vo.status == 30>selected='selected'</#if>><@spring.message code="sys.using"></@spring.message></option>
            	 			<option value="40" <#if vo.status ?? && vo.status == 40>selected='selected'</#if>>unpaid</option>
            	 			<option value="50" <#if vo.status ?? && vo.status == 50>selected='selected'</#if>>Paid</option>
            	 	</select>
            	 	&nbsp;&nbsp;
					<@spring.message code="agent.order.payment.time"></@spring.message>：<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
		    						    <input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					
					 &nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="Search" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<#-- 
						<th width="3%">订单号</th>
						<th width="5%">服务商</th>
						-->
     					<th width="4%"><@spring.message code="agent.cabinet.powerbankId"></@spring.message></th>
			            <th width="10%"><@spring.message code="agent.member.nickname"></@spring.message></th>
			            <th width="8%"><@spring.message code="agent.order.borrow"></@spring.message></th>
			            <th width="8%"><@spring.message code="agent.order.return"></@spring.message></th>
			            <th width="10%"><@spring.message code="agent.order.usage.time"></@spring.message></th>
			            <th width="4%"><@spring.message code="agent.order.use.time"></@spring.message></th>
			            <th width="6%"><@spring.message code="agent.order.cost"></@spring.message></th>
			            <th width="4%"><@spring.message code="agent.order.status"></@spring.message></th>
			            <th width="8%"><@spring.message code="agent.form.operate"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <#-- 
				            <td> ${info.orderCode!''} </td>
				            <td> ${info.sellerName!''} </td>
				            -->
				             <td> ${info.pbCode!''}</td>
				            <td> 
				            	<#if info.nickname??>${info.nickname!''}<br/></#if>
				            	${info.mobile!}
				            </td>
				           
				            <td> 
				            	${info.borrowSysCode!''}<br/>
				            	Card slot：${info.borrowChannel!}
				            </td>
				            <td> 
				            	${info.returnSysCode!''}<br/>
				            	Card slot：${info.returnChannel!}
				            </td>
				           	<td>
				           		${(info.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}<br/>
				           		${(info.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}
				           	</td>
				           	<td> ${info.rideTime!''} min</td>
				           	<td> ${info.currency!} ${info.price!''} </td>
				           	
				           	<td>
				           		<#if info.status ?? && info.status == 10>
				            		Unlocking
				            	<#elseif info.status ?? &&  info.status == 20>
				            		Unlock failed
				            	<#elseif info.status ?? &&  info.status == 30>
				            		<@spring.message code="sys.using"></@spring.message>
				            	<#elseif info.status ?? &&  info.status == 40>
				            		unpaid
				            	<#elseif info.status ?? &&  info.status == 50>
				            		Paid
				            	<#else>
				            		
				            	</#if>
				           	</td>
				           <td>
			            		<a href="${ctx}/order/detail?id=${info.id!}"><@spring.message code="agent.form.detail"></@spring.message></a>
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="agent.form.noData"></@spring.message>！
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	