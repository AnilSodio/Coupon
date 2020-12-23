<#include "/include/taglib.ftl" >
<html>
<head>
	<title>维保人员信息管理</title>
	<#include "/include/treeview.ftl" >
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				ignore: "",
				rules: {
					mobile: {remote: "${ctx}/sys/repairer/checkMobile?uid="+$('#uid').val()},
					name: {remote: "${ctx}/sys/repairer/checkName?uid="+$('#uid').val()}
				},
				messages: {
					mobile: {remote: "Phone number already exists"},
					name:{remote: "Name already exists"},
					confirmNewPassword: {equalTo: "The passwords entered twice are not the same"
				}
				},
				submitHandler: function(form){
					loading('<@spring.message code="form.submitInfo"></@spring.message>');
					form.submit();
				}
			});
			
			$("input[name='areaId']").areaSelect({
    			dateUrl: "${request.contextPath}/dic/getAreaJsPath"
    		});
			
			var setting = {
				check:{enable:true,nocheckInherit:true},
				view:{selectedMulti:false},
				data:{
					simpleData:{enable:true}
				},
				callback:{
					beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}
				}
			};
			
			// 用户-机构
			var zNodes2=[
				<#list officeList as office >
					{id:${office.id!}, pId:${office.parentId!0}, name:'${office.name!}'},
            	</#list>];
			// 初始化树结构
			var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
			// 不选择父节点
			tree2.setting.check.chkboxType = { "Y" : "s", "N" : "s" };
			// 默认选择节点
			var ids2 = "${user.officeIds}".split(",");
			for(var i=0; i<ids2.length; i++) {
				var node = tree2.getNodeByParam("id", ids2[i]);
				try{tree2.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开All节点
			tree2.expandAll(true);
			
			// 刷新（显示/隐藏）机构
			refreshOfficeTree();
			$("#dataScope").change(function(){
				refreshOfficeTree();
			});
			
		});
		
		function refreshOfficeTree(){
			// 按明细设置
			if($("#dataScope").val()==9){ 
				$("#officeTreeDiv").show();
			}else{
				$("#officeTreeDiv").hide();
			}
		}
	</script>
	<style type="text/css">
		#roleIdList span, .roles span{
			width:200px;
			float:left;
		}
	</style>
	
	<link href="${pubStatic}/wgt/area/selectAddress.css" rel="stylesheet" type="text/css" />
	<script src="${pubStatic}/wgt/area/selectAddress.js"></script>
	
</head>
<body>

       <div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="home"/></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="userInfo.member"/><i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span><@spring.message code="userInfo.maintenance"/></span>
                </li>
            </ul>
        </div>

	<#if message??><@tags.message content=message /></#if>
	<div class="portlet light ">
			<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/repairer/list"><@spring.message code="form.list"></@spring.message>
	                </a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
                            <#if info.uid??>
								<@spring.message code="form.edit"></@spring.message>
							<#else>
								<@spring.message code="form.add"></@spring.message>
							</#if>
	            	 </a>
	            </li>
        	</ul>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
	<@form.form id="inputForm" modelAttribute="user" action="${ctx}/sys/repairer/save" method="post" class="form-horizontal">
		<@form.hidden path="uid"/>
		<div class="form-body">
		  
		<div class="form-group">
			<label class="col-xs-3 control-label">company name:</label>
			<div class="col-xs-4">
                <@tags.treeselect id="company" name="companyId" value=(user.company.id)! labelName="companyName" labelValue=(user.company.name)!
					title='归属公司' url="/sys/office/treeData?type=1" cssClass="form-control required" allowClear=false />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label"><@spring.message code="userInfo.maintenance.office"/>:</label>
			<div class="col-xs-4">
                <@tags.treeselect id="office" name="officeId" value=(user.office.id)! labelName="officeName" labelValue=(user.office.name)!
					title='归属部门' url="/sys/office/treeData?type=2" cssClass="form-control required" allowClear=false />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label"><@spring.message code="userInfo.maintenance.username"/>:</label>
			<div class="col-xs-4">
				<#if info.uid??>
					<input type="hidden" name="name" value="${info.name !}"/>
					<p class="form-control-static">${info.name !}</p>
				<#else>
					<input type="text" name="name" value="${info.name!}" htmlEscape=false  maxlength="50" class="form-control required"/>
				</#if>
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-xs-3 control-label"><@spring.message code="sys.password"/> :</label>
			<div class="col-xs-4">
				<input type="password" id="password"  name="password" value=""  htmlEscape=false  maxlength="20" class="form-control ${user.uid!'required'}"/>
				<#if user.uid??><span class="help-inline">If you do not change your password, leave it blank。</span></#if>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label"><@spring.message code="sys.confirmPassword"></@spring.message> :</label>
			<div class="col-xs-4">
				<input type="password" id="confirmNewPassword"  name="confirmNewPassword" value=""  htmlEscape=false  maxlength="20" class="form-control" equalTo="#password"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label"><@spring.message code="userInfo.member.country"/>:</label>
			<div class="col-md-4">
				<#if info.uid ??>
					<input type="hidden" name="country" value="${info.country !}"/>
					<p  class="form-control-static">
								<#list countries as country>
									<#if info.country ?? && info.country == country.nationCode>
										${country.englishName!}
									</#if>
								</#list>
					</p>
				<#else>
					<select id="country" name="country" class="select2 form-control input-small required">
						<#list countries as country>
							<#if info.country ?? && info.country == country.nationCode>
								<option value="${country.nationCode}" selected='selected'>${country.englishName!}</option>
							<#else>
								<option value="${country.nationCode}">${country.englishName!}</option>
							</#if>
						</#list>
					</select>
				</#if>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label"><@spring.message code="userInfo.member.phone"/> :</label>
			<div class="col-xs-4">
				<input type="text" name="mobile" value="${info.mobile!}" placeholder="86-13520894287"  htmlEscape=false  maxlength="14" class="form-control required"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-3 control-label"><@spring.message code="userInfo.member.picture"/>:</label>
			<div class="col-md-4">
				  <div class="upload-pic-box">
                    <input type="hidden" name="headImgPath" id="headImgPath" autocomplete="off" maxCount="1"  value="${info.headImgPath!}"/>
                    <input type="file" class="hidden" id="headImgFile" value="" name="imgFile" />
                    <button type="button" id="headImgButton" class="btn btn-mini btn-default"><@spring.message code="sys.uploadImage"/></button>
                    <div id="headImgBox" class="view-pic-list">
                    	<#if info.headImgPath ?? && info.headImgPath?length gt 0>
                    		<span class="item"><img src="${info.headImgPath!}" width="120px" height="120px"><i class="remove js-remove-upload">×</i></span>
                    	</#if>
                    </div>
                </div>
                <label for="headImg" class="error"></label>
			</div>
		</div>
		
		<div class="form-actions">
			<div class="row">
                <div class="col-md-offset-3 col-md-4">
				<@shiro.hasPermission name="sys:repairer:view">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"/>"/>&nbsp;
				</@shiro.hasPermission>
				</div>
            </div>
		</div>
	</@form.form>
	</div>
   </div>

<script type="text/javascript" src="/ajaxfileupload.js"></script>
<script>
		function initUpload(inputId, fileId, buttonId, viewId) {
			    var $input = $("#" + inputId), $file = $("#" + fileId), $button = $("#" + buttonId), $view = $("#" + viewId), uploadFun;
			    $button.click(function() {
			    	$("#" + fileId).click();
			    });
			    uploadFun = function() {
			    	var $file = $(this);
			        if (!$.trim($file.val())) return false;
			    	if ($view.find(".item").length == $input.attr("maxCount")) {
			            alert("Please delete first, and then re-upload！");
			            return false;
			        }
				    	
			        $.ajaxFileUpload({
			        
			            url:'${ctx}/upload', //用于文件上传的服务器端请求地址
			            secureuri: false, //是否需要安全协议，一般设置为false
			            fileElementId: fileId, //文件上传域的ID
			            dataType: 'json', //返回值类型 一般设置为json
			            success: function (data, status)  //服务器成功响应处理函数
			            {
			            	console.log(data);
			            	
			            	if(data.error != 0){
			            		top.$.jBox.tip(data.error);
			            		return false;
			            	}
			            	
			            	$view.append('<span class="item"><img src="'+ data.url +'" width="120px" height="120px"/><i class="remove js-remove-upload">×</i></span>');
			                if ($.trim($input.val())) {
			                    $input.val($input.val() + "*" + data.url);
			                } else {
			                    $input.val(data.url);
			                }
			            	
			                if (typeof (data.error) != 'undefined') {
			                    if (data.error == 1) {
			                        alert(data.message);
			                    } 
			                }
			                $file.val("").empty();
			            },
			            error: function (data, status, e)//服务器响应失败处理函数
			            {
			                alert(e);
			            }
			        });
			    };
				    
			    $("body").on("change", "#" + fileId, function() {
			    	uploadFun.call(this);
			    });
			    $view.on("click",".js-remove-upload",function(){
			        var valArr = $input.val().split("*");
			        valArr.splice($(this).parent().index(),1);
			        $("#" + fileId).val("");
			        $(this).parent().remove();
			        $input.val(valArr.join("*"));
			  });
			}
		
		initUpload("headImgPath","headImgFile","headImgButton","headImgBox");
	</script>
	
</body>
</html>