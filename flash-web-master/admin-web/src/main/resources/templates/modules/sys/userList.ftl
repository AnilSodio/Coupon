<#include "/include/taglib.ftl" >
<#include "/include/dialog.ftl" >
<html>
<head>
	<title>用户管理</title>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 表格排序
			//tableSort({callBack : page});
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/user/").submit();
	    	return false;
	    }
	    function deleteUser(uid){
	    
	    	 top.jBox.confirm('<@spring.message code="form.deleteInfo"></@spring.message>?', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	            if (v === 'ok') {
	                  window.location.href="${ctx}/sys/user/delete?id="+uid;
	            }
	            return true;
 			 });
	    }
	</script>
</head>
<body>

        <div class=" ">

        <ul class="nav nav-tabs mb-25">
            <li class="active">
              <a data-toggle="tab" href="javascript:;">
         	       <@spring.message code="sys.platformUsers"></@spring.message>
              </a>
            </li>
            <@shiro.hasPermission name="sys:user:modify"> 
	            <li class="">
	              <a  href="${ctx}/sys/user/form">
	                                         <@spring.message code='form.add'></@spring.message>
				  </a>
	            </li>
           	</@shiro.hasPermission>
                                
        </ul>

        <@form.form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/" method="post" class="form-search">
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!}"/>
            <input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy!}"/>
            <div>


                <label class="ml-15"><@spring.message code="sys.aAttributionCompany"></@spring.message>：</label>
                 <div style="display: inline-block; vertical-align: bottom;max-width: 28%;">
                <@tags.treeselect id="company" name="companyId" value=user.companyId! labelName="companyName" labelValue=user.companyName!
                    title='归属公司' url="/sys/office/treeData?type=1" cssClass="form-control" allowClear=true/>
				 </div>
				 
                <label class="ml-15"><@spring.message code="sys.ownershipDepartment"></@spring.message>：</label>
                <div style="display: inline-block; vertical-align: bottom;max-width: 28%;">
                <@tags.treeselect id="office" name="officeId" value=user.officeId! labelName="officeName" labelValue=user.officeName!
                    title='归属部门' url="/sys/office/treeData?type=2" cssClass="form-control" allowClear=true/>
				 </div>
				<br/><br/>
                <label class="ml-15"><@spring.message code="sys.LogInName"></@spring.message>：</label><@form.input path="loginName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
                <label class="ml-15"><@spring.message code="marketing.name"></@spring.message>：</label><@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
                &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1);"/>
            </div>
        </@form.form>
        <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
                <div class="table-scrollable">

                    <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable" role="grid">
                        <thead><tr><th><@spring.message code="sys.aAttributionCompany"></@spring.message></th><th><@spring.message code="sys.ownershipDepartment"></@spring.message></th><th class="sort loginName"><@spring.message code="sys.LogInName"></@spring.message></th><th class="sort name"><@spring.message code="marketing.name"></@spring.message></th><th><@spring.message code="sys.phone"></@spring.message></th><th><@spring.message code="sys.mobile"></@spring.message></th><th><@spring.message code="sys.userRole"></@spring.message></th><@shiro.hasPermission name="sys:user:view"><th><@spring.message code="form.operation"></@spring.message></th></@shiro.hasPermission></tr></thead>
                        <tbody>
                        <#list page.list as user >
                            <tr>
                                <td>${(user.company.name)!}</td>
                                <td>${(user.office.name)!}</td>
                                <td><a href="${ctx}/sys/user/form?id=${user.uid!}">${user.loginName!}</a></td>
                                <td>${user.name!}</td>
                                <td>${user.phone!}</td>
                                <td>${user.mobile!}</td>
                                <td>${user.roleNames!}</td>
                                <@shiro.hasPermission name="sys:user:modify">
                                <td>
                                    <a href="${ctx}/sys/user/form?id=${user.uid!}"><@spring.message code="form.edit"></@spring.message></a>
                                </@shiro.hasPermission>
                                <@shiro.hasPermission name="sys:user:delete">
                                    <a   onclick="deleteUser(${user.uid!})"><@spring.message code="form.delete"></@spring.message></a>
                                </@shiro.hasPermission>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="text-center"><div class="pagination">${page}</div><div>
            </div>
        </div>
    </div>
</body>
</html>