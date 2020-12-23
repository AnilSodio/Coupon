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
	        
	        <#if (img_list?size > 0)>
		        <div class="select-cover-box">
		            <div id="sample_4_wrapper" class="dataTables_wrapper">
					<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
						<tr>
							<th width="30%">image</th>
							<th width="20%"><@spring.message code="sys.title"></@spring.message></th>
							<th width="5%">choose</th>
						</tr>
						
					<#list img_list as info>
						<tr id="${info.id}">
				            <td><img width="80px" height="80px" src="${info.cover!''}"/></td> 
				            <td>${info.title!}</td> 
				            <td>
				            	<input class="radio" type="radio" id="repselect${info.id!}"  name="repselect" value="${info.id!}&${info.cover!}&${info.title!}"/>
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