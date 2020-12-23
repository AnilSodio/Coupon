<#include "/include/taglib.ftl" >
<html>
<head>
	<title>收费规则列表管理</title>
	<style>
	.dataTable td,.dataTable th{
	  text-align:center
	}
	</style>
	<script type="text/javascript">
	    function typeDel(id){
	    	 top.jBox.confirm('<@spring.message code="form.disableInfo"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('<@spring.message code="form.opInfo"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/rules/deleteRule?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='form.opSuccess'></@spring.message>");
			                 		window.location.href="${ctx}/sys/rules/ruleList";
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
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
              <li>
				    <span><@spring.message code="role.nomoBox"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="nomo.type.chargeRule"></@spring.message></span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	        <ul class="nav nav-tabs mb-25">
	             <li class="active">
 					<a data-toggle="tab" href="javascript:;"><@spring.message code="nomo.rule_list"></@spring.message></a>
	             </li>
	             <#if !vo.cabinetId??>
	             <li>
 					<a href="${ctx}/sys/rules/ruleAdd""><@spring.message code="form.add"></@spring.message></a>
	             </li>
	             </#if>
	        </ul>
         	<#if (ruleList?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<#if vo.cabinetId??>
						<th width="3%" >选择</th>
						</#if>
			            <th width="5%"><@spring.message code="nomo.type.chargeRule"></@spring.message></th>
			            <th width="4%"><@spring.message code="nomo.type.costcap"></@spring.message></th>
			            <th width="4%"><@spring.message code="nomo.type.freeTime"></@spring.message></th>
			            <th width="10%"><@spring.message code="nomo.type.desc"></@spring.message></th>
			            <#if !vo.cabinetId??>
			            <th width="4%"><@spring.message code="form.operation"></@spring.message></th>
			            </#if>
					</tr>
					<#list ruleList as info>
						<tr>
							<#if vo.cabinetId??>
							<td width="5%"><input type="radio" name="id" id="id"></td>
							</#if>
				            <td> ${info.unitMinute!} min / ${info.currency!} ${info.unitPrice!} </td>
				            <td> ${info.currency!} ${info.costTop!}</td>
				            <td> ${info.freeUseTime!0}min</td>
				            <td> ${info.ruleDesc!}</td>
				            <#if !vo.cabinetId??>
				            <td>
		            			<@shiro.hasPermission name="sys:rules:ruleModify">
			            			<a  href="${ctx}/sys/rules/ruleModify?id=${info.id!}"><@spring.message code="form.edit"></@spring.message></a>
		            			</@shiro.hasPermission>	
		            			<@shiro.hasPermission name="sys:rules:ruleDelete">
			            			<a  href="javascript:typeDel(${info.id!});"><@spring.message code="form.delete"></@spring.message></a>
		            			</@shiro.hasPermission>	
				            </td>
				            </#if>
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