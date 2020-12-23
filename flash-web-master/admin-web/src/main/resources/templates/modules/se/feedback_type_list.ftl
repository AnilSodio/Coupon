<#include "/include/taglib.ftl" >
<html>
<head>
	<title>用户反馈类型管理</title>
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
				    <span><@spring.message code="role.feedback"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 						<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
	        
	        <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/feddback/typeList" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<input id="belongType" name="belongType" type="hidden" value="${vo.belongType!}"/>
					
					<div>
						<label><@spring.message code="marketing.typeName"></@spring.message>：</label><@form.input path="typeName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<@spring.message code="feedback.type"></@spring.message>
							<select id="type" name="type" class="select2 form-control input-small">
								<option value=''  <#if vo.type ?? && vo.type == '' >selected='selected'</#if>><@spring.message code="stats.total"></@spring.message></opetion>
								<option value='1' <#if vo.type ?? && vo.type == '1'>selected='selected'</#if>>Home</option>
								<option value='2' <#if vo.type ?? && vo.type == '2'>selected='selected'</#if>><@spring.message code="sys.using"></@spring.message></option>
								<option value='3' <#if vo.type ?? && vo.type == '3'>selected='selected'</#if>><@spring.message code="stats.completed"></@spring.message></option>
							<select>
						</label>
						&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
        <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th><@spring.message code="marketing.typeName"></@spring.message></th>
						<th><@spring.message code="feedback.type.name"></@spring.message></th>
						<th><@spring.message code="feedback.type.desc"></@spring.message></th>
			            <th><@spring.message code="feedback.type.feedback"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.typeName!''}</td> 
				            
				            <td>
				            	<#if info.type ?? && info.type == "1">
				            		<@spring.message code="feedback.home"></@spring.message>
				            	<#elseif info.type ?? && info.type == "2">
				            		<@spring.message code="feedback.using"></@spring.message>
				            	<#elseif info.type ?? && info.type == "3">
				            		<@spring.message code="stats.completed"></@spring.message>
				            	<#else>
				            	
				            	</#if>
				            </td> 
				            
				            <td>${info.typeDesc!''}</td>
				            
							<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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