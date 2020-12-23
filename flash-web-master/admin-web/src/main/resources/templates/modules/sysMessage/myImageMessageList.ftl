<#include "/include/taglib.ftl" >
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript">
	    function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	    
	    function delMyMsg(id){
	    	 top.jBox.confirm('确认要删除该消息吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在删除，请稍等...');
	                  window.location.href="${ctx}/sys/message/delMyMsg?mark=3&id="+id;
	            }
	            return true;
 			 });
	    }
	   
	   function showDetail(orderCode) {
	   		window.location.href="${ctx}/sys/work/detail?orderCode="+orderCode;
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
				    <span>Message Center<i class="fa fa-angle-right"></i></span>
				</li>
            </ul>
        </div>
</div>

	<div class="portlet light portlet-fit portlet-datatable">
	
    	 <ul class="nav nav-tabs mb-25">
	            <li class="active">
	               <a data-toggle="tab" href="javascript:void(0);"><@spring.message code="sys.messageList"></@spring.message></a>
	            </li>
	        </ul>
	            
    	 <@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/sysMessage/list?logIdIsNull=false" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<input id="btnSubmit" class="btn btn-primary" style="display:none" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(0, 20);"/>
		</@form.form>
		<#if (page.content?size > 0)>
        <div class="portlet-body">
             <div id="sample_4_wrapper" class="dataTables_wrapper">
             <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
             <tr>
             			<th width="20%">Message type</th>
						<th width="20%">Message content</th>
						<th width="20%">send the object</th>
						<th width="20%">Receiver</th>
			            <th width="20%"><@spring.message code="marketing.sendTime"></@spring.message></th>
			</tr>
			<#list page.content as info>
	             <tr>	
			           <tr>
							
							<td>
				            	<#if info.isImageText ?? && info.isImageText>
				            		<@spring.message code="sys.graphicMessage"></@spring.message>
				            	<#else>
				            		Text message
				            	</#if>
				            </td>
				            
				            <td>
				            	<#if info.isImageText?? && info.isImageText>
				            		<img src="${info.cover!}_100x100">
				            	<#else>
				            		${info.content!}
				            	</#if>
				            </td>
				            <td>
				           		user
				           </td>
				           <td>
				           		<#if info.toUserType ?? && info.toUserType == '3'>
									${info.repairerName!}/${info.repairerMoile!}
								<#elseif info.toUserType ?? && info.toUserType == '2'>
									${info.nickname!}/${info.userMobile!}
								<#else>
									${info.sendType!}
								</#if>
				           </td>
				           
				            <td>${(info.sendTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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