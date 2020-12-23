'<#include "/include/taglib.ftl" >
<html>
<head>
	<title>意见反馈信息管理</title>
	<script type="text/javascript">
		function delMyMsg(id){
	    	 top.jBox.confirm('Are you sure you want to delete this message?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v == true) {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/feedbackMessage/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, disable the success");
			                 		window.location.href="${ctx}/sys/feedbackMessage/list";
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
				    <span><@spring.message code="sys.feedbackMessage"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/feedbackMessage/list" method="post" class="breadcrumb form-search">
					
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					 
					<input id="btnSubmit" style="display:none" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="5%">User's info</th>
			            <th width="10%"><@spring.message code="sys.content"></@spring.message></th>
			            <th width="10%"><@spring.message code="nomo.createTime"></@spring.message></th>
			            <th width="6%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <td> ${info.nickname!''} ${info.mobile!} </td>
				            <td> ${info.content!}</td>
				           	<td> ${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            <td>
				            	<@shiro.hasPermission name="sys:feedback:delete">
			            			 <a class="glyphicon glyphicon-remove" href="javascript:delMyMsg(${info.id!});"></a>&nbsp;
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