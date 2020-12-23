<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电柜列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center;
	  vertical-align: middle !important;
	}
	</style>
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('<@spring.message code="form.disableInfo"></@spring.message>?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('<@spring.message code="form.disableInfo"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/disAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('Are you sure you want to delete this record?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/cabinet/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		window.location.href="${ctx}/sys/cabinet/list";
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
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
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
            	<li>
	                <a href="${ctx}/sys/cabinet/modify"><@spring.message code="form.add"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/cabinet/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="nomo.nomoBoxId"></@spring.message>：</label>
					<@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="nomo.code"></@spring.message>：</label>
					<@form.input path="cabinetCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="nomo.type.name"></@spring.message>:</label>
					<select id="typeId" name="typeId" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
		           			<#list typeList as g>
                	 			<option value="${g.id!}" <#if vo.typeId ?? && vo.typeId == g.id>selected='selected'</#if>>${g.typeName!}</option>
                	 		</#list>
            	 	</select>
					&nbsp;&nbsp;
					<label><@spring.message code="form.status"></@spring.message>:</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>><@spring.message code="status.enable"></@spring.message></option>
            	 			<option value="2" <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="status.disable"></@spring.message></option>
            	 	</select>
            	 	<br/></br/>
            	 	<label><@spring.message code="nomo.merchant"></@spring.message>：</label>
					<@form.input path="sellerName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="nomo.deployed"></@spring.message>：</label>
					<select id="isDelivery" name="isDelivery" class="select2 form-control input-small">
							<option value='' ><@spring.message code="status.all"></@spring.message></opetion>
            	 			<option value="1" <#if vo.isDelivery ?? && vo.isDelivery>selected='selected'</#if>><@spring.message code="nomo.deployed.yes"></@spring.message></option>
            	 			<option value="0" <#if vo.isDelivery ?? && !vo.isDelivery>selected='selected'</#if>><@spring.message code="nomo.deployed.no"></@spring.message></option>
            	 	</select>
            	 	&nbsp;&nbsp;
            	 	<label><@spring.message code="nomo.createTime"></@spring.message>：</label>
            	 	<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    				<input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					&nbsp;&nbsp;	
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="3%"><@spring.message code="nomo.nomoBoxId"></@spring.message></br><@spring.message code="nomo.code"></@spring.message></th>
						<th width="4%"><@spring.message code="nomo.promoCode"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.type.name"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.type.chargeRule"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.merchant"></@spring.message></th>
			            <th width="6%"><@spring.message code="nomo.agent"></@spring.message></th>
			            <th width="4%"><@spring.message code="form.status"></@spring.message></th>
			            <th width="5%"><@spring.message code="nomo.deployed"></@spring.message></th>
			            <th width="12%"><@spring.message code="nomo.address"></@spring.message></th>
			            <th width="6%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td> ${info.sysCode!''}</br>${info.cabinetCode!''} </td>
				            <td> ${info.cabinetAlias!''} </td>
				            <td> ${info.typeName!''}</td>
				            <td>
				            <#if info.ruleId?? && info.ruleId gt 0>
				            <a href="${ctx}/sys/rules/ruleModify?id=${info.ruleId!}">${info.ruleDesc!''}</a>
				            <#else>
				            <a href="${ctx}/sys/rules/ruleList?cabinetId=${info.id!}">选择费率+</a>
				            </#if>
				            </td>
				            <td> ${info.sellerName!''}</td>
				            <td> ${info.agentName!''}</td>
				            <td>
				            	<#if info.status ?? && info.status ==1 >
				            		<@spring.message code="status.enable"></@spring.message>
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="status.disable"></@spring.message>
				            	<#else>
				            		${info.status!}
				            	</#if>
				             </td>
				            <td>
				            	<#if info.isDelivery ?? && info.isDelivery>
				            		<@spring.message code="nomo.deployed.yes"></@spring.message>
				            	<#elseif info.isDelivery ?? && !info.isDelivery>
				            		<@spring.message code="nomo.deployed.no"></@spring.message>
				            	</#if>
				            </td>
				            <td> ${info.locationAddress!} ${info.locationDesc!}</td>
				            <td>
		            			<@shiro.hasPermission name="sys:cabinet:modify">
		            				<a  href="${ctx}/sys/cabinet/modify?id=${info.id!}"><@spring.message code="form.edit"></@spring.message></a>
			            			</br>
			            			<#if info.status?? && info.status == 1>
										<a  href="javascript:disable(${info.id!});"><@spring.message code="status.disable"></@spring.message></a>
			            				</br>
			            			</#if>
			            			<#if info.status?? && info.status == 2>
			            				<a  href="javascript:enable(${info.id!});"><@spring.message code="status.enable"></@spring.message></a>
			            				</br>
			            			</#if>
			            			<#if info.isDelivery ?? && !info.isDelivery>
				            			<a  href="${ctx}/sys/cabinet/cabinetDelivery?id=${info.id!}"><@spring.message code="nomo.putIn"></@spring.message></a>
				            			</br>
				            		<#else>
				            			<a  href="${ctx}/sys/cabinet/cabinetDelivery?id=${info.id!}"><@spring.message code="nomo.rlaunch"></@spring.message></a>
			            				</br>
			            			</#if>
			            		</@shiro.hasPermission>	
		            			<@shiro.hasPermission name="sys:cabinet:print">
			            			<a  href="${ctx}/sys/cabinet/cabinetPrint?id=${info.id!}"><@spring.message code="form.print"></@spring.message></a>
			            			</br>
			            		</@shiro.hasPermission>	
			            		
		            			<@shiro.hasPermission name="sys:cabinet:detail">
			            			<a  href="${ctx}/sys/cabinet/detail?id=${info.id!}&type=cabinet"><@spring.message code="form.detail"></@spring.message></a>
			            			</br>
			            		</@shiro.hasPermission>	
			            		
			            		<#-- 
			            			<@shiro.hasPermission name="sys:cabinet:detail">	
			            				<a  href="javascript:typeDel(${info.id!});">删除</a>
				            		</@shiro.hasPermission>
				            		</br>	
				            		
				            		<@shiro.hasPermission name="sys:customer:view">	
				            		<a  href="${ctx}/sys/customer/list?sysCode=${info.sysCode!}"><@spring.message code="nomo.bingdingUse"></@spring.message></a>
			            		</@shiro.hasPermission>	
			            		
			            		-->
		            			
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="form.noData"></@spring.message>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	