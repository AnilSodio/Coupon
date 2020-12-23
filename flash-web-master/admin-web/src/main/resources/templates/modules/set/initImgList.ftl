<#include "/include/taglib.ftl" >
<html>
<head>
	<title>广告列表管理</title>
	
	<script type="text/javascript">
		 function delImg(id){
		    	 top.jBox.confirm('<@spring.message code="sys.AreYouSureYouWantToDeleteTheRecord"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
		              if (v ==true) {
		            	  loading('Removing, please wait...');
		                  window.location.href="${ctx}/sys/initImg/delete?id="+id;
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message></span>
				</li>
                <li>
				    <span><@spring.message code="marketing.adManagement"></@spring.message></span>
				</li>
              
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <#if is_open_add?? && is_open_add>
		            <@shiro.hasPermission name="sys:initImg:modify">
		            <li>
		            	<a href="${ctx}/sys/initImg/modify?imgType=${vo.imgType!}"><@spring.message code="form.add"></@spring.message></a>
		            </li>
	                </@shiro.hasPermission>
	            </#if>
                
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/initImg/list" method="post" class="breadcrumb form-search">
					
			</@form.form>
				
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="marketing.name"></@spring.message></th>
			            <th><@spring.message code="marketing.image"></@spring.message></th>
			            <th><@spring.message code="marketing.linked"></@spring.message></th>
			            <th><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>${info.name!''} </td>
				            
				            <td>
				            	<img src="${info.initImg!}" width="50" height="50">
				            </td>
				            <td>${info.linkUrl!''}</td>
							
							 <td>
							 
							 	<@shiro.hasPermission name="sys:initImg:modify">
									<a  href="${ctx}/sys/initImg/modify?id=${info.id!}&imgType=${info.imgType!}"><@spring.message code="form.edit"></@spring.message></a>&nbsp;
		            			</@shiro.hasPermission>	
		            		
							 	<@shiro.hasPermission name="sys:initImg:delete">
							 		<a  href="javascript:delImg(${info.id!});"/><@spring.message code="form.delete"></@spring.message></a>
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