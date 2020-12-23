'<#include "/include/taglib.ftl" >
<html>
<head>
	<title></title>
	
	<script charset="utf-8" src="/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/editor/lang/zh_CN.js"></script>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#inputForm").validate({
				rules: {
					 
				},
				messages: {
					 
				},    
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
		
		});
 		
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
				    <span><@spring.message code="sys.guidancemanagement"></@spring.message><i class="fa fa-angle-right"></i></span>
				</li>
                <li>
				    <span><@spring.message code="sys.articleList"></@spring.message></span>
				</li>
               
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/guide/list"><@spring.message code="form.list"></@spring.message> </a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);"><@spring.message code="form.edit"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/guide/save" method="post" class="form-horizontal">
				 <div class="form-body">
					
					<input type="hidden" name="id" value="${info.id!}"/>
					<#--  
					<input type="hidden" name="language" value="${info.language!}"/>
					-->
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.language"></@spring.message>:</label>
						<div class="col-md-4">
							<select id="language" name="language" class="select2 form-control input-small">
								<#list lan_list as g>
		            	 			<option value="${g.defaultLanguage!}" <#if info.language ?? && info.language == g.defaultLanguage>selected='selected'</#if>>${g.languageDesc!}</option>
		            	 		</#list>
							<select>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.typesOf"></@spring.message>:</label>
						<div class="col-md-4">
							<select id="type" name="type" class="select2 form-control input-small">
								<option value='1' <#if info.type ?? && info.type == 1>selected='selected'</#if>><@spring.message code="sys.userGuide"/></option>
								<option value='2' <#if info.type ?? && info.type == 2>selected='selected'</#if>><@spring.message code="sys.guide.type_2"/></option>
								<option value='3' <#if info.type ?? && info.type == 3>selected='selected'</#if>><@spring.message code="sys.guide.type_3"/></option>
								<option value='4' <#if info.type ?? && info.type == 4>selected='selected'</#if>><@spring.message code="sys.guide.type_4"/></option>
								<option value='5' <#if info.type ?? && info.type == 5>selected='selected'</#if>><@spring.message code="sys.guide.type_5"/></option>
								<option value='10' <#if info.type ?? && info.type == 10>selected='selected'</#if>><@spring.message code="sys.guide.type_10"/></option>
								<option value='11' <#if info.type ?? && info.type == 11>selected='selected'</#if>><@spring.message code="sys.guide.type_11"/></option>
							<select>
						</div>
					</div>
					
					
				 	<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.title"></@spring.message>:</label>
						<div class="col-md-4">
							<input type="text" name="title" value="${info.title !}" maxlength="128"  class="form-control required"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><@spring.message code="sys.content"></@spring.message>:</label>
						<div class="col-md-4">
							<textarea id="content"  name="content">${info.content!}</textarea>
						</div>
					</div>
					
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<@shiro.hasPermission name="sys:guide:view">
									<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
								</@shiro.hasPermission>
								<input id="btnCancel" class="btn red" type="button" value="<@spring.message code="form.goback"></@spring.message>" onclick="history.go(-1)"/>
							</div>
						</div>
					</div>
				</div>
			</@form.form>
            </div>
        </div>
	</div>
	<script>
	$(document).ready(function() {
			var optionsApp = {
	        filterMode : true,
			uploadJson:'${ctx}/upload', 
			width : '650px',
			height:'500px',
			minWidth:'400px',
			allowImageUpload : true,
			syncType:"form",
			afterCreate : function() {
								var self = this;
								self.sync();
							},
			afterChange : function() {
								var self = this;
								self.sync();
							},
			afterBlur : function() {
								var self = this;
								self.sync();
							},
			items:['source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter',  '|',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage', '|', 'about']
		};
		editor = KindEditor.create('#content', optionsApp);
	
	});
		
	</script>
</body>
</html>