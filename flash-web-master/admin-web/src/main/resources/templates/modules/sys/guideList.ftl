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
	    
	     function ajax_update(id,  obj) {
			var sort = $(obj).val();
			$.ajax({
				type : 'POST',
				url : '${ctx}/sys/guide/ajax',
				data : {
					"id" : id,
					"sort" : sort
				},
				beforeSend : function () {},
				success : function (data) {
				
				}
			});
		}
		 
	    function deleteGuide(id) {
	    	top.jBox.confirm('<@spring.message code="sys.AreYouSureYouWantToDeleteTheRecord"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('Removing, please wait...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/guide/delete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("Congratulations, delete successfully");
			                 		window.location.href="${ctx}/sys/guide/list";
			                 	}
			          	 });
	            }
	            return true;
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="sys.guidancemanagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span><@spring.message code="sys.articleList"></@spring.message></span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
	                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <@shiro.hasPermission name="sys:guide:modify">
	            <li class="">
					<a href="${ctx}/sys/guide/modify"><@spring.message code="form.add"></@spring.message></a>
	            </li>
                </@shiro.hasPermission>
                
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/guide/list" method="post" >
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<label><@spring.message code="sys.language"/>：</label>
					<select id="lockTypeId" name="language" class="select2 form-control input-small">
	           			<#list lan_list as g>
            	 			<option value="${g.defaultLanguage!}" <#if vo.language ?? && vo.language == g.defaultLanguage>selected='selected'</#if>>${g.languageDesc!}</option>
            	 		</#list>
            	 	</select>
	                	 	
					<input id="btnSubmit" class="btn btn-primary"  type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(0, 20);"/>
			</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="8%" ><@spring.message code="sys.typesOf"></@spring.message></th>
						<th width="20%" ><@spring.message code="sys.title"></@spring.message></th>
			            <th width="6%" ><@spring.message code="sys.sort"></@spring.message></th>
			            <th width="12%"><@spring.message code="nomo.createTime"></@spring.message></th>
			            <th width="8%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
				            <td>
				            <#if info.type==1>
				            	<@spring.message code="sys.userGuide"></@spring.message>
				            </#if>
				            <#if info.type==2>
				            	Set
				            </#if>
				            <#if info.type==3>
				            	Warranty end settings
				            </#if>
				            <#if info.type == 4>
				            	Service regulations
				            </#if>
				            <#if info.type == 5>
				            	Points rules
				            </#if>
				            <#if info.type == 10>
				            	Recharge agreement
				            </#if>
				            <#if info.type == 11>
				            	Purchase agreement
				            </#if>
				            </td>
				            <td>${info.title!''}</td>
				            <td><input type="text"  style="width:80px;" name="${info.sort!}" id="${info.sort!}"  value="${info.sort!}"  onblur="ajax_update('${info.id!}',this)" title="可编辑"/></td>
				            <td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							<td>
							<@shiro.hasPermission name="sys:guide:modify">
					            	&nbsp; <a  href="${ctx}/sys/guide/modify?id=${info.id!}"><@spring.message code="form.edit"></@spring.message></a>
		            		</@shiro.hasPermission>	
							<@shiro.hasPermission name="sys:guide:delete">
					            	&nbsp; <a  href="javascript:deleteGuide(${info.id!})"><@spring.message code="form.delete"></@spring.message></a>
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