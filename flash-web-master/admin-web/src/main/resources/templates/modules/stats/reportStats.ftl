<#include "/include/taglib.ftl" >
<div class="page-container-custom">
	<div class="page-bar">
	    <ul class="page-breadcrumb">
	        <li>
	            <i class="icon-home"></i>
	            <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
	            <i class="fa fa-angle-right"></i>
	        </li>
	          <li>
	                <span><@spring.message code="stats.analysis"></@spring.message></span><i class="fa fa-angle-right"></i>
	            </li>
	          <li>
	                <span><@spring.message code="stats.dataReport"></@spring.message></span>
	            </li>
	    </ul>
	</div>

	<div class="portlet light ">
		<div class="portlet-body form">
			<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/reportStats" method="post" class="breadcrumb form-search">
			<div>
				<label><@spring.message code="stats.year"></@spring.message>：</label><input id="year" name="year" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
					value="${year!}" onclick="WdatePicker({dateFmt:'yyyy'});"/>
				<label><@spring.message code="stats.month"></@spring.message>：</label><input id="month" name="month" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
					value="${month!}" onclick="WdatePicker({dateFmt:'MM'});"/>
				&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" />
				&nbsp;<input id="print" onclick="printme()" class="btn btn-primary" type="button" value="<@spring.message code="stats.export"></@spring.message>" />
			</div>
			</@form.form>
			<table cellpadding="0" cellspacing="0" width="100%" class="table table-striped table-bordered table-hover" >
				<thead>
				        <tr>
				            <th  colspan='3' class="text-center">
				            <#if month??&&month!=''>
				            	<h3>${year!}<@spring.message code="stats.year"></@spring.message> ${month!} Data Report(JSON Formatter)</h3>
				            <#else>
				            	<h3>${year!} Data Report(JSON Formatter)</h3>
				            </#if>
				            </th>
				        </tr>
				</thead>
				<tbody>
				        <tr>
				            <td> 
				            	<pre id="out_json"></pre>
				            </td>
				        </tr>
				</tbody>
				</table>
			</div>
	</div>

</div>
<script type="text/javascript">
   	var result = JSON.stringify(${json}, null, 2);
   	document.getElementById('out_json').innerText= result ;
   	// print
	function printme(){
		window.print();
    }
</script>
<style type="text/css" media="print">
	.breadcrumb{ display: none;}
</style>
