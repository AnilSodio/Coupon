\<#include "/include/taglib.ftl" >
<html>
<head>
	<title>积分记录管理</title>
	<script type="text/javascript">
	    function delete(id){
	    	 top.jBox.confirm('确认要删除该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在删除，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/inteRule/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("删除成功");
			                 		window.location.href="${ctx}/sys/inteRule/list";
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
                    <span><@spring.message code="userInfo.member.manage"/></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
       <ul class="nav nav-tabs mb-25">
            <li class="active">
				<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
            </li>
        </ul>
        <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/inteRule/list"  hidden="ture" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
			<input id="btnSubmit" class="btn btn-primary" style="display:none" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
		</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="6%"><@spring.message code="userInfo.member.description"/></th>
			            <th width="4%"><@spring.message code="userInfo.member.points"/></th>
			            <th width="12%"><@spring.message code="userInfo.member.createdBy"/></th>
			            <th width="12%"><@spring.message code="userInfo.member.modifiedBy"/></th>
			            <th width="3%"><@spring.message code="operation"/></th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <td> ${info.ruleDescribe!''}  </td>
				            <td> ${info.integralNum!''}  </td>
				           	<td> ${info.createName!''}  ${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				           	<td>${info.updateName!''}  ${(info.updateTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
							 <td>
			            		<@shiro.hasPermission name="sys:inteRule:modify">
						            	<a  href="${ctx}/sys/inteRule/modify?id=${info.id!}"><@spring.message code="edit"/></a>
			            		</@shiro.hasPermission>
				            </td>
						</tr>
					</#list>
				</table>
				
				 <div class="pagination">
			        ${page}
			    </div>
			    
			<#else>
				<div class="note note-warning alert">
					<@spring.message code="noRecords"/>
				</div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	