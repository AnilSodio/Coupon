<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	<#include "/include/treetable.ftl" >
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    function deleteOrg(uid){
	    	 top.jBox.confirm('<@spring.message code="sys.confirmThatYouWantToDeleteThisAndAllSubAgencies"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/office/delete?id="+uid;
	            }
	            return true;
 			 });
	    }
	</script>
</head>
<body>
	<div class="page-container-custom">
	<#-- 
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
				    <span><@spring.message code="home"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>机构管理</span>
                </li>
            </ul>
        </div>
	 -->
         <div class="portlet light portlet-fit portlet-datatable ">
         	 <ul class="nav nav-tabs mb-25">
            <li class="active">
                <a data-toggle="tab" href="javascript:;">
             <@spring.message code="sys.org.management"></@spring.message></a>
            </li>
            <@shiro.hasPermission name="sys:office:modify">
            <li class="">
                <a  href="${ctx}/sys/office/form">
             <@spring.message code="form.add"></@spring.message> </a>
            </li>
            </@shiro.hasPermission>
        	</ul>
			<div class="portlet-body">
		            <div id="sample_4_wrapper" class="dataTables_wrapper">
		            	<@tags.message content=message! />
		                <div class="table-scrollable">
							<table id="treeTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
								<tr>
								<th><@spring.message code="sys.org.name"></@spring.message></th>
								<#-- 
								<th>归属区域</th>
								-->
								<th><@spring.message code="sys.org.code"></@spring.message></th>
								<th><@spring.message code="sys.org.type"></@spring.message></th>
								<th><@spring.message code="sys.remarks"></@spring.message></th>
								<@shiro.hasPermission name="sys:office:Edit">
								<th><@spring.message code="form.operation"></@spring.message></th></@shiro.hasPermission>
								</tr>
								<#list list as item>
									<tr id="${item.id}" pId="${(item.parentId != office.id)?string(item.parentId,'0')}">
										<td>
											<a href="${ctx}/sys/office/form?id=${item.id}">${item.name}</a>
										</td>
										<#-- 
										<td>
											${fns.getDictFullLabel(item.areaId,'vc.thinker.sys.bo.DicAreaBO',' ')}
										</td>-->
										<td>${item.code}</td>
										<td>${fns.getDictLabel(item.type, 'sys_office_type', '无')}</td>
										<td>${item.remarks}</td>
										<td>
										<@shiro.hasPermission name="sys:office:modify">
											<a href="${ctx}/sys/office/form?id=${item.id}"><@spring.message code="form.edit"></@spring.message></a>
											<a href="${ctx}/sys/office/form?parentId=${item.id}"><@spring.message code="sys.addASubordinateBody"></@spring.message></a>
										</@shiro.hasPermission>
										<@shiro.hasPermission name="sys:office:delete">
											<a  onclick="deleteOrg(${item.id})"><@spring.message code="form.delete"></@spring.message></a>
										</@shiro.hasPermission>
										</td>
									</tr>
								</#list>
							</table>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>