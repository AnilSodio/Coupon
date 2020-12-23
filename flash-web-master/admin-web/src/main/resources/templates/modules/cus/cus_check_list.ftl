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
				    <span>User Info management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span>Member management</span>
				</li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	            <li class="active">
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/customer/check_list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>
					<div>
						<label>姓名：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<label>昵称：</label><@form.input path="nickname" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						<label>手机号：</label><@form.input path="mobile" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
						&nbsp;
						<label>审核状态：
							<select id="authApplyStatus" name="authApplyStatus" class="select2 form-control input-small">
								<option value='' >All</opetion>
								<option value='1' <#if vo.authApplyStatus ?? && vo.authApplyStatus == 1>selected='selected'</#if>>待审核 </option>
								<option value='2' <#if vo.authApplyStatus ?? && vo.authApplyStatus == 2>selected='selected'</#if>>审核成功</option>
								<option value='3' <#if vo.authApplyStatus ?? && vo.authApplyStatus == 3>selected='selected'</#if>>审核失败</option>
							<select>
						</label>
						
						&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
					</div>
				</@form.form>
				
         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
					<tr>
						<th width="5%">头像</th>
			            <th width="4%">姓名</th>
			            <th width="4%">昵称</th>
			            <th width="4%">手机号</th>
			           	<th width="10%">注册时间</th>
				        <th width="9%">认证状态</th>
			            <th width="5%">审核状态</th>
			            <th width="8%">审核备注</th>
			            <th width="5%">操作</th>
					</tr>
					<#list page.content as info>
						<tr>
							<td>
								<#if info.headImgPath ??>
									<img src="${info.headImgPath!}" width="50px" height="50px">
								<#else>
									<img src="/images/defuatcover.png" width="50px" height="50px">
								</#if>
							</td>
							
				            <td>
				            	${info.name!''}<br/>
				           	</td>
				            <td>
				            	${info.nickname!''}<br/>
				           	</td>
				            <td>
				            	${info.mobile!''}
				           	</td>
				            
				           	<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				            
					            <td>
					            	<#if info.authStatus ?? && info.authStatus == "0">
					            		未认证
					            	<#elseif info.authStatus ?? && info.authStatus == "1">
					            		认证中
					            	<#elseif info.authStatus ?? && info.authStatus == "2">
					            		认证成功
					            	<#elseif info.authStatus ?? && info.authStatus == "3">
					            		认证失败
					            	</#if>
					            	/
					            	<#if info.authStep ?? && info.authStep == 1>
					            		手机认证
					            	<#elseif info.authStep ?? && info.authStep == 2>
					            		押金充值
					            	<#elseif info.authStep ?? && info.authStep == 3>
					            		实名认证
					            	<#elseif info.authStep ?? && info.authStep == 4>
					            		认证完成
					            	</#if>
								</td>
							
				            	<td> 
				            		<#if info.authApplyStatus?? && info.authApplyStatus == 1>
				            			待审核
				            		<#elseif info.authApplyStatus ?? && info.authApplyStatus == 2>
				            			审核通过
				            		<#elseif info.authApplyStatus ?? && info.authApplyStatus == 3>
				            			审核失败
				            		<#else>
				            			${info.authApplyStatus!}
				            		</#if>
				            	</td>
				            	<td>${info.authApplyRemark!}</td>
							 <td>
		            			<@shiro.hasPermission name="sys:customer:view">
		            			<#if info.authApplyStatus?? && info.authApplyStatus == 1>
			            			&nbsp; 
										 <a  href="${ctx}/sys/customer/applyCheck?uid=${info.uid!}">审核</a>
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
				<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
			</#if>
			
			</div>
        </div>
    </div>
</body>
</html>	