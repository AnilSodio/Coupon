<#include "/include/taglib.ftl" >
<html>
<head>
	<title>意见反馈列表管理</title>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    function find(){
	    	var str=$("#feedType").val();
	    	window.location.href="${ctx}/sys/feddback/list?feedType="+str;
	    }
	    function findTypeName(){
	    	var feedType=$("#feedType").val();
	    	var typeName=$("#typeName").val();
	    	window.location.href="${ctx}/sys/feddback/list?feedType="+feedType+"&typeName="+typeName;
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
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/feddback/list" method="post" class="breadcrumb form-search">
					
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					
					<div>
						<label><@spring.message code="marketing.userS.nickname"></@spring.message>：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;<label><@spring.message code="order.mobile"></@spring.message>：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;<label><@spring.message code="nomo.nomoBoxId"></@spring.message>：</label><@form.input path="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;
						<label><@spring.message code="sys.typesOf"></@spring.message>：
							<select id="feedType" name="feedType" class="select2 form-control input-small" onchange="find()">
								<option value=''  <#if vo.feedType ?? && vo.feedType == '' >selected='selected'</#if>><@spring.message code="nomo.pleaseChoose"></@spring.message></opetion>
								<option value='1' <#if vo.feedType ?? && vo.feedType == '1'>selected='selected'</#if>><@spring.message code="feedback.home"></@spring.message></option>
								<option value='2' <#if vo.feedType ?? && vo.feedType == '2'>selected='selected'</#if>><@spring.message code="feedback.using"></@spring.message></option>
								<option value='3' <#if vo.feedType ?? && vo.feedType == '3'>selected='selected'</#if>><@spring.message code="stats.completed"></@spring.message></option>
							<select>
						</label>
						<br/><br/>
						<label><@spring.message code="marketing.typeName"></@spring.message>:
							<select id="typeName" name="typeName" class="select2 form-control input-small" onchange="findTypeName()">
								<option value=""><@spring.message code="nomo.pleaseChoose"></@spring.message></option>
				    		<#list t_list as g>
		               			<option value="${g.typeName!}" <#if vo.typeName ?? && g.typeName == vo.typeName>selected='selected'</#if>>
		               			${g.typeName}
		               			</option>
		            		</#list>
	            			 </select>
						</label>
						&nbsp;&nbsp;
						<label><@spring.message code="form.status"></@spring.message>:
							<select id="status" name="status" class="select2 form-control input-small">
								<option value="" ><@spring.message code="stats.total"></@spring.message></opetion>
								<option value="0" <#if vo.status ?? && vo.status == 0>selected='selected'</#if>>Processed</option>
								<option value="1" <#if vo.status ?? && vo.status == 1>selected='selected'</#if>>Not processed</option>
							<select>
						</label>
						&nbsp;&nbsp;
						<@spring.message code="feedback.time"></@spring.message>：<input type="text" readonly="readonly" name="beginDate" value="${(vo.beginDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/> -
    						<input type="text" readonly="readonly" name="endDate" value="${(vo.endDate)!}" class="form-control input-small input-inline" onclick="WdatePicker()"/>			
						&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
		 <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="4%"><@spring.message code="nomo.nomoBoxId"></@spring.message></th>
						<th width="6%"><@spring.message code="feedback.type"></@spring.message></th>
			            <th width="18%"><@spring.message code="feedback.desc"></@spring.message></th>
						<th width="9%"><@spring.message code="order.userInfo"></@spring.message></th>
			            <th width="4%"><@spring.message code="form.status"></@spring.message></th>
			            <th width="12%"><@spring.message code="feedback.feedTime"></@spring.message></th>
			            <th width="6%"><@spring.message code="feedback.client"></@spring.message></th>
			            <th width="6%"><@spring.message code="form.operation"></@spring.message></th>
					</tr>
					<#list page.content as info>
						<tr>
							
				            <td>${info.sysCode!''}</td>
				            <td>
				            	<#if info.feedType ?? && info.feedType == "1">
				            		<@spring.message code="feedback.home"></@spring.message>
				            	<#elseif info.feedType ?? && info.feedType == "2">
				            		<@spring.message code="feedback.using"></@spring.message>
				            	<#elseif info.feedType ?? && info.feedType == "3">
				            		<@spring.message code="stats.completed"></@spring.message>
				            	<#else>
				            	
				            	</#if>
				            	
				            	${info.typeName!''}
				            </td> 
				            
				            <td>${info.feedDesc!''}</td>
				            
				            <td>
				            	<#if info.nickname??>${info.nickname !''}<br/></#if>
				            	${info.mobile !''}</td>
				            <td>
				            	<#if info.status ?? && info.status == 0>
				            		<@spring.message code="feedback.processed"></@spring.message>
				            	<#elseif info.status ?? && info.status == 1>
				            		<@spring.message code="feedback.noProcessed"></@spring.message>
				            	<#elseif info.status ?? && info.status == 2>
				            		<@spring.message code="feedback.noNeedProcess"></@spring.message>
				            	<#else>
				            	
				            	</#if>
				            </td> 
				            
							<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							<td>${info.clientSource!''}</td>
							<td>
								<@shiro.hasPermission name="sys:feed:modify">
								 	<#if info.status == 1>
								 		<a  href="${ctx}/sys/feddback/modify?id=${info.id!}"><@spring.message code="feedback.deal"></@spring.message></a>&nbsp;
								 	</#if> 
			            		</@shiro.hasPermission>	
			            		
								<@shiro.hasPermission name="sys:feed:detail">
									<#if info.status != 1>
								 		<a  href="${ctx}/sys/feddback/queryDetail?id=${info.id!}"><@spring.message code="form.detail"></@spring.message></a>&nbsp;
								 	</#if> 
			            		</@shiro.hasPermission>	
					        </td>
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