'<#include "/include/taglib.ftl" >
<html>
<head>
	<title>充电宝类型管理</title>
	<script type="text/javascript">
	    function disable(id){
	    	 top.jBox.confirm('确认要禁用该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在禁用，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/battery/typeDisAndEnable?status=2&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，禁用成功");
			                 		window.location.href="${ctx}/sys/battery/typeList";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function enable(id){
	    	 top.jBox.confirm('确认要启用该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在启用，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/battery/typeDisAndEnable?status=1&id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，启用成功");
			                 		window.location.href="${ctx}/sys/battery/typeList";
			                 	}
			          	 });
	            }
	            return true;
 			 });
	    }
	    
	    function typeDel(id){
	    	 top.jBox.confirm('确认要删除该记录吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	            	  loading('正在删除，请稍等...');
	            	  $.ajax({
			                 	url:"${ctx}/sys/battery/typeDelete?id="+id,
			                 	type:"POST",
			                 	success:function(data){
			                 		top.$.jBox.tip("恭喜，删除成功");
			                 		window.location.href="${ctx}/sys/battery/typeList";
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
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span>充电宝类型</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
            	<li>
	                <a href="${ctx}/sys/battery/typeModify">添加</a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/battery/typeList" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<input id="btnSubmit" style="display:none" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="6%">型号名称</th>
			            <th width="5%">最大电量</th>
			            <th width="5%">最大电压</th>
			            <th width="4%">是否有线</th>
			            <th width="6%">充电线类型</th>
			            <th width="6%">状态</th>
			            <th width="10%">创建信息</th>
			            <th width="6%">操作</th>
					</tr>
					<#list page.content as info>
						<tr>
							 
				            <td> ${info.typeName!''} </td>
				            <td> ${info.maxElectricity!0} 毫安</td>
				            <td> ${info.maxVoltage!0} 伏特</td>
				            <td>
				            	<#if info.isHasLine?? && info.isHasLine>
				            		是
				            	<#elseif info.isHasLine ?? && !info.isHasLine>
				            		否
				            	</#if>
				            </td>
				            <td>
				            	<#if info.lineType ?? && info.lineType == 'normal'>
				            		IOS/Android通用
				            	<#elseif info.lineType ?? &&  info.lineType == 'Typc-C'>
				            		安卓 Typc-C
				            	</#if>
				            </td>
				            <td>
				            	<#if info.status ?? && info.status ==1 >
				            		正常
				            	<#elseif info.status ?? && info.status == 2>
				            		禁用
				            	<#else>
				            		${info.status!}
				            	</#if>
				             </td>
				            
				           	 <td>${info.createName!}/${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				           
				           <td>
		            			<@shiro.hasPermission name="sys:battery:list">
			            			<#if info.status?? && info.status == 1>
										<a  href="javascript:disable(${info.id!});">禁用</a>
			            			</#if>
			            			<#if info.status?? && info.status == 2>
			            				<a  href="javascript:enable(${info.id!});">启用</a>
			            			</#if>
			            			<a  href="${ctx}/sys/battery/typeModify?id=${info.id!}">编辑</a>
			            			<a  href="javascript:typeDel(${info.id!});">删除</a>
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