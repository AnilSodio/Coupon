<#include "/include/taglib.ftl" >
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
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
				    <span>NOMO Box Manage<i class="fa fa-angle-right"></i></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;">NOMOBOX list </a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/cabinet/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<label>NOMObox ID：</label>
					<@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label>box IMEI ID：</label>
					<@form.input path="cabinetCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label>model type：</label>
					<select id="typeId" name="typeId" class="select2 form-control input-small">
							<option value="">all</option>
		           			<#list typeList as g>
                	 			<option value="${g.id!}" <#if vo.typeId ?? && vo.typeId == g.id>selected='selected'</#if>>${g.typeName!}</option>
                	 		</#list>
            	 	</select>
					&nbsp;&nbsp;
					<label>status：</label>
					<select id="status" name="status" class="select2 form-control input-small">
							<option value="">all</option>
            	 			<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>normal</option>
            	 			<option value="2" <#if vo.status ?? && vo.status == 2>selected='selected'</#if>><@spring.message code="marketing.disabled"></@spring.message></option>
            	 	</select>
					<br/></br/>
					<label>Delivery status：</label>
					<select id="isDelivery" name="isDelivery" class="select2 form-control input-small">
							<option value="">all</option>
            	 			<option value="1" <#if vo.isDelivery ?? && vo.isDelivery>selected='selected'</#if>>Delivered</option>
            	 			<option value="0" <#if vo.isDelivery ?? && !vo.isDelivery>selected='selected'</#if>>Not running</option>
            	 	</select>
            	 	&nbsp;&nbsp;
            	 	<label><@spring.message code="nomo.createTime"></@spring.message>：</label>
            	 	<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    				<input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
					&nbsp;&nbsp;	
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="query" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="3%">NOMObox ID</th>
						<th width="4%">box IMEI ID</th>
			            <th width="4%">model type</th>
			            <th width="4%">status</th>
			            <th width="5%">Delivery status</th>
			            <th width="12%"><@spring.message code="marketing.address"></@spring.message></th>
			            <th width="12%">created by</th>
			            <th width="7%">operate</th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <td> ${info.sysCode!''} </td>
				            <td> ${info.cabinetCode!''} </td>
				            <td> ${info.typeName!''}</td>
				            <td>
				            	<#if info.status ?? && info.status ==1 >
				            		normal
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="marketing.disable"></@spring.message>
				            		${info.status!}
				            	</#if>
				             </td>
				            <td>
				            	<#if info.isDelivery ?? && info.isDelivery>
				            		Delivered
				            	<#elseif info.isDelivery ?? && !info.isDelivery>
				            		Not running
				            	</#if>
				             </td>
				            
				             <td> ${info.locationAddress!''}</td>
				             <#-- 
				           	 <td>${info.createName!}/${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				           	 -->
				           	 <td>${info.createName!} / ${(info.createTime?string('yyyy-MM-dd'))!''}</td>
				           
				           <td>
		            				<a  href="${ctx}/cabinet/detail?id=${info.id!}">view</a> &nbsp;
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">no data</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	