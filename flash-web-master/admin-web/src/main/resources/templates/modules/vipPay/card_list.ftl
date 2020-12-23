<#include "/include/taglib.ftl" >
<html>
<head>
	<title>会员卡列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>	
	<script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function delCard(id) {
	    
	    	 top.jBox.confirm('<@spring.message code="form.deleteSure"/>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/card/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code="form.deleteSuccess"/>");
			                 		window.location.href="${ctx}/sys/card/list";
			                 	}
			          	 });
	            }
	            return true;
 			  }, { buttons: { 'Yes': true, 'No': false} });
	    }
	</script>
</head>
<body>

	<div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                 <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="home"/></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.manage"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member.vip.set"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <@shiro.hasPermission name="sys:card:modify">
	            <li>
					<a href="${ctx}/sys/card/modify"><@spring.message code="form.add"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
                
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/card/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label><@spring.message code="userInfo.member.vip.cardName"/>：</label><@form.input path="cardName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
	                	 	
						&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="12%"><@spring.message code="userInfo.member.vip.cardName"/></th>
			            <th width="6%"><@spring.message code="userInfo.member.vip.amount"/></th>
			            <th width="6%"><@spring.message code="userInfo.member.vip.effective"/></th>
			            <#-- 
			            <th width="4%">折扣</th>
			            -->
			            <th width="16%"><@spring.message code="userInfo.member.description"/></th>
			            <#-- 
			            <th width="10%">Created By</th>
			            <th width="10%"><@spring.message code="nomo.createTime"></@spring.message></th>
			            -->
			            <th width="6%"><@spring.message code="sys.sort"/></th>
			            <th width="6%"><@spring.message code="form.operation"/></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.cardName!''}</td>
				            <td>${info.currency!} ${info.cardAmount!''}</td>
				            <td>
				            	<#if info.cardUnit?? && info.cardUnit == 'day'>
				            		${info.cardEffectiveTime!''} <@spring.message code="marketing.day"></@spring.message>
				            	<#elseif info.cardUnit?? && info.cardUnit == 'hour'>
				            		${info.cardEffectiveTime!''} hour
				            	<#else>
				            		${info.cardUnit!}
				            	</#if>
				            </td>
				            <#--
				            <td>${info.discount!''}</td>
				            -->
				            <td>${info.cardDesc!''}</td>
				             <td>${info.sort!}</td>
				            <#--
				            <td>${info.adminName!}/${info.adminPhone!}</td>
				            
				            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							 -->
							 <td>
								<@shiro.hasPermission name="sys:card:modify">
									 	<a  href="${ctx}/sys/card/modify?id=${info.id!}"><@spring.message code="form.edit"></@spring.message></a>&nbsp;
			            		</@shiro.hasPermission>
			            		<@shiro.hasPermission name="sys:card:delete">
									 	<a  href="javascript:delCard(${info.id!});"><@spring.message code="form.delete"></@spring.message></a>&nbsp;
			            		</@shiro.hasPermission>		
				            </td>
						</tr>
					</#list>
				</table>
				<div class="pagination">
			        ${page}
			    </div>
			<#else>
				<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
			</#if>
			</div>
        </div>
    </div>
</body>
</html>