<#include "/include/taglib.ftl" >
<html>
<head>
	<title>管理</title>
	<script type="text/javascript">
	    
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    $(function() {
	    	$("#contentTable").click(function(e) {
	        var $target = $(e.target);
	    	if(!$target.hasClass("radio")) {
	    		var $radio = $(e.target).parents("tr").find(".radio");
	    		console.log($radio.prop('checked', !$radio.prop('checked')));
	    	}
	    });
	    })
	    
	</script>
	
</head>
<body>

	<div class="page-container-custom">
        
        <div class="portlet light portlet-fit portlet-datatable ">
        	
        	<ul class="nav nav-tabs mb-25">
	            <li class="active">
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
	        
	        <#if (info_list?size > 0)>
		        <div class="select-cover-box">
		            <div id="sample_4_wrapper" class="dataTables_wrapper">
					<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
						<tr>
							<#if isHaveImg ?? && isHaveImg>
								<th width="30%"><@spring.message code="marketing.image"></@spring.message></th>
							</#if>
							<th width="20%">User's name</th>
							<th width="5%">User's phone </th>
						</tr>
						
					<#list info_list as info>
						<tr>
							<#if isHaveImg ?? && isHaveImg>
								<td>
									<#if info.headImgPath ??>
							            <img width="50px" height="50px" src="${info.headImgPath!''}"/>
									<#else>
							        	<img src="/images/defuatcover.png" width="50px" height="50px">
									</#if>
								</td> 
							 <#else>
							</#if>
				            <td>
				            	${info.name!}
				            </td> 
				            <td>
				            	${info.mobile!}
				            </td>
						</tr>
					</#list>
						
					</table>
				<#else>
					<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
				</#if>
			</div>
        </div>
    </div>
</body>
</html>