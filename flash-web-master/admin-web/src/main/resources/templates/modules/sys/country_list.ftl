<#include "/include/taglib.ftl" >
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function auditDialogBox(url) {
            $.jBox("get:" + url, {
                title: '<@spring.message code="sys.msg.updateLanguage"></@spring.message>',
                width: 500,
                height: 330,
                buttons: {}
            });
        }
        function setDeault(id) {
            top.jBox.confirm('<@spring.message code="sys.doYouWantToSetUpTheCountryForThis"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
                if (v === 'ok') {
                    $.get("${ctx}/sys/country/setDefault?id="+id,function(){
                        top.location.reload();
                    });
                }
                return true;
            });
        }

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

    </script>
</head>
<body>
	 <#include "/include/dialog.ftl" >
<div class="page-container-custom">
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span><@spring.message code="sys.settings"></@spring.message><i class="fa fa-angle-right"></i></span>
            </li>
            <li>
                <span><@spring.message code="sys.controlManage"></@spring.message></span>
            </li>
        </ul>
    </div>

    <div class="portlet light portlet-fit portlet-datatable ">
        <ul class="nav nav-tabs mb-25">
            <li class="active">
                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
            </li>
            <li>
                <a href="${ctx}/sys/country/edit"><@spring.message code="sys.newCountries"></@spring.message></a>
            </li>
        </ul>

        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/country/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>

					<label><@spring.message code="sys.chineseName"></@spring.message>:</label>
                <@form.input path="chineseName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.englishName"></@spring.message>:</label>
                <@form.input path="englishName" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.defaultLanguage"></@spring.message>:</label>
                <@form.input path="defaultLanguage" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit"
                           value="<@spring.message code='form.query'></@spring.message>" onclick="return page(1, 20)"/>
            </@form.form>

         <#if (page.content?size > 0)>
	        <div class="portlet-body">
	            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<table id="contentTable"
                       class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
                    <tr>
                        <th width="4%"><@spring.message code="sys.chineseName"></@spring.message></th>
                        <th width="4%"><@spring.message code="sys.englishName"></@spring.message></th>
                        <th width="3%"><@spring.message code="sys.defaultLanguage"></@spring.message></th>
                        <th width="6%"><@spring.message code="sys.languageDescription"></@spring.message></th>
                        <th width="3%"><@spring.message code="sys.deposit"></@spring.message></th>
                        <th width="3%"><@spring.message code="sys.callCenter"></@spring.message></th>
                        <th width="3%"><@spring.message code="sys.isItDefault"></@spring.message></th>
                        <th width="3%"><@spring.message code="form.operation"></@spring.message></th>
                    </tr>
					<#list page.content as info>
						<tr>
                            <td> ${info.chineseName!''} </td>
                            <td> ${info.englishName!''} </td>
                            <td> ${info.defaultLanguage!''} </td>
                            <td> ${info.languageDesc!''} </td>
                            <td> ${info.currency!} ${info.deposit!''} </td>
                            <td> ${info.callCenter!''} </td>
                            <td align="center">
                                <#if info.isDefault?? && info.isDefault>
                                        	✔
                                </#if>
                            </td>
                            <td>
                            <@shiro.hasPermission name="sys:country:edit">
                                <#if info.isDefault?? && !info.isDefault>
                                        <a href="javascript:setDeault(${info.id});"><@spring.message code="sys.setDefault"></@spring.message></a>
                                </#if>
                                        <a href="${ctx}/sys/country/edit?id=${info.id}"><@spring.message code="form.edit"></@spring.message></a>
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
</body>
</html>