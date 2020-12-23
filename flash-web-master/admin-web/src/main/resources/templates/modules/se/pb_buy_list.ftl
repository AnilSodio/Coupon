<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电宝购买记录管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center;
	  vertical-align: middle !important;
	}
	</style>	
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
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="role.order"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/pbBuy/list" method="post" class="breadcrumb form-search">
					
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label>
					<@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<@spring.message code="order.mobile"></@spring.message>
					</label>
					<@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					 
            	 	&nbsp;&nbsp;
            	 	<@spring.message code="order.buyTime"></@spring.message>
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
			            <th width="4%"><@spring.message code="nomo.pbId"></@spring.message></th>
			            <th width="6%"><@spring.message code="order.userInfo"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.orderId"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.orderAmount"></@spring.message></th>
			            <th width="4%"><@spring.message code="order.pbAmount"></@spring.message></th>
			            <th width="8%"><@spring.message code="order.payTime"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td> ${info.pbCode!''}</td>
				            <td> 
				            	${info.nickname!''}
				            	${info.mobile!}
				            </td>
				            <td>  ${info.orderCode!''} </td>
				            <td>  ${info.currency!} ${info.orderAmount!''} </td>
				            <td>  ${info.currency!} ${info.depositAmount!''} </td>
				            
				           	<td>
				           		${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}<br/>
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