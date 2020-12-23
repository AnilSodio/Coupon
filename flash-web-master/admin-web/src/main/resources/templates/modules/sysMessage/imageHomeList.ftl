'<#include "/include/taglib.ftl" >
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
	    
	    
	    function delImageText(id){
	
	    	top.jBox.confirm('<@spring.message code="sys.AreYouSureYouWantToDeleteTheRecord"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
			    if (v ==true) {
					 $.ajax({
	                 	url:"${ctx}/sys/imageText/delete?id="+id,
	                 	type:"POST",
	                 	success:function(data){
	                 		if(data == "1"){
	                 			top.$.jBox.tip("Congratulations, delete successfully");
	                 			window.location.reload();
	                 		}else{
	                 			top.$.jBox.tip(data);
	                 			window.location.reload();
	                 		}
	                 	}
	                 });    	
			    }
	    	}, { buttons: { 'Yes': true, 'No': false} });
	    }
    
	    
	    
	    function disable(id){
	    	 top.jBox.confirm('<@spring.message code="marketing.areYouSureYouWantToDisableThisAd"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	              if (v ==true) {
	            	  loading('Is expired, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/imageText/stopOrStart?status=3&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		if(data == "1"){
				                 		top.$.jBox.tip("Congratulations, the failure of success");
				                 		window.location.href="${ctx}/sys/imageText/list?imageType=2";
			                 		}else {
			                 			top.$.jBox.tip(data);
			                 		}
			                 	}
			          	 });
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('Do you want to validate the record?？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	             if (v ==true) {
	            	  loading('Being effective, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/imageText/stopOrStart?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		if(data == "1"){
				                 		top.$.jBox.tip("Congratulations, effective");
				                 		window.location.href="${ctx}/sys/imageText/list?imageType=2";
			                 		}else {
			                 			top.$.jBox.tip(data);
			                 		}
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="marketing.marketingManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="marketing.adManagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="marketing.homeAd.management"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
						<a data-toggle="tab" href="javascript:;"><@spring.message code="marketing.adList"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:imageText:modify">
	            <li>
					<a href="${ctx}/sys/imageText/modify?imageType=2"><@spring.message code="form.add"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/imageText/list?" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<input id="imageType" name="imageType" type="hidden" value="2"/>
			</@form.form>
			
			<@tags.message content=message! />
			 <#if (page.content?size > 0)>
			 
		        <div class="portlet-body">
		            <div id="sample_4_wrapper" class="dataTables_wrapper">
					<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
						<tr>
				           	<th width="5%"><@spring.message code="sys.cover"></@spring.message></th>
				           	<th width="8%"><@spring.message code="sys.title"></@spring.message></th>
				           	<th width="13%"><@spring.message code="sys.summary"></@spring.message></th>
				           	<th width="5%"><@spring.message code="marketing.image"></@spring.message></th>
				           	<th width="5%">Status</th>
				            <th width="10%"><@spring.message code="nomo.createTime"></@spring.message></th>
				            <th width="10%"><@spring.message code="marketing.expirationTime"></@spring.message></th>
				            <th width="9%"><@spring.message code="form.operation"></@spring.message></th>
				        </tr>
				        
						<#list page.content as info>
							<tr>
								
					            <td>
					            	<#if info.cover ??>
					            		<img src="${info.cover!}_100x100">
					            	<#else>
					            		默认图片
					            	</#if>
					            </td>
					            <td>${info.title!}</td>
					            <td>${info.remark!}</td>
					            
					            <td>
					            	<#if info.cover ??>
					            		<img src="${info.adCover!}_100x100">
					            	<#else>
					            		默认图片
					            	</#if>
					            </td>
					            
					            <td>
					            	<#if info.status ?? && info.status == 1>
					            		<@spring.message code="sys.notActive"></@spring.message>
					            	<#elseif info.status ?? && info.status == 2>
					            		<@spring.message code="sys.effective"></@spring.message>
					            	<#elseif info.status ?? && info.status == 3>
					            		<@spring.message code="sys.expired"></@spring.message>
					            	<#else>
					            		${inof.status!}
					            	</#if>
					            </td>
					            
					            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					            <td>${(info.invalidDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					            <td>
					            	<@shiro.hasPermission name="sys:imageText:modify">
										<a  href="${ctx}/sys/imageText/modify?id=${info.id!}&imageType=2"><@spring.message code="form.edit"></@spring.message></a> &nbsp;
										<#if info.status ?? && info.status != 2>
											<a href="javascript:enable(${info.id!});"><@spring.message code="status.enable"/></a>
										<#elseif info.status ?? && info.status == 2>
											<a href="javascript:disable(${info.id!});"><@spring.message code="status.disable"></@spring.message></a>
										</#if>
									</@shiro.hasPermission>
									
					            	<@shiro.hasPermission name="sys:imageText:delete">
										<a href="javascript:delImageText(${info.id!});"><@spring.message code="form.delete"/></a>&nbsp;
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
</body>

</html>