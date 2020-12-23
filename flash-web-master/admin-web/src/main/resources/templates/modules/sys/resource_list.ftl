<#include "/include/taglib.ftl" >
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function auditDialogBox(url){
            $.jBox("get:"+url,{
                title:'<@spring.message code="sys.msg.updateLanguage"></@spring.message>',
                width: 500,
                height: 330,
                buttons:{}
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
                <span><@spring.message code="sys.multilingualSetting"></@spring.message></span>
            </li>
        </ul>
    </div>

    <div class="portlet light portlet-fit portlet-datatable ">
        <ul class="nav nav-tabs mb-25">
            <li class="active">
                <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
            </li>
        </ul>

        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/resource/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'20'}"/>

					<label><@spring.message code="sys.msg.messageKey"></@spring.message>:</label>
                <@form.input path="name" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.msg.text"></@spring.message>:</label>
                <@form.input path="text" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.msg.language"></@spring.message>:</label>
                <select name="language" class="form-control select2 input-small">
                    <option value=""><@spring.message code="marketing.pleaseChoose"></@spring.message></option>
                    <#list countryList as country>
                        <option value="${country.defaultLanguage}" <#if vo.language?? && country.defaultLanguage==vo.language>selected="selected"</#if>>
                            ${country.defaultLanguage}
                        </option>
                    </#list>
                </select>
					&nbsp;&nbsp;
					<label><@spring.message code="sys.msg.functionModule"></@spring.message>:</label>
                <@form.input path="functionModule" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
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
                        <th width="2%"><@spring.message code="sys.msg.messageKey"></@spring.message></th>
                        <th width="3%"><@spring.message code="sys.msg.text"></@spring.message></th>
                        <th width="1%"><@spring.message code="sys.msg.language"></@spring.message></th>
                        <th width="1%"><@spring.message code="sys.msg.functionModule"></@spring.message></th>
                        <th width="1%"><@spring.message code="sys.platformType"></@spring.message></th>
                       	<#--
                        <th width="1%"><@spring.message code="sys.secondMenu"></@spring.message></th>
                       	-->
                        <th width="1%"><@spring.message code="form.operation"></@spring.message></th>
                    </tr>
					<#list page.content as info>
						<tr>
                            <td> ${info.name!''} </td>
                            <td> ${info.text!''} </td>
                            <td> ${info.language!''} </td>
                            <td> ${info.functionModule!''} </td>
                            <td> ${info.platType!''} </td>
                           	<#-- 
                            <td> ${info.secondMenu!'null'} </td>
                            -->
                            <td> <a href="javascript:auditDialogBox('${ctx}/sys/resource/edit?id=${info.id}');"><@spring.message code="form.edit"></@spring.message></a> </td>
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