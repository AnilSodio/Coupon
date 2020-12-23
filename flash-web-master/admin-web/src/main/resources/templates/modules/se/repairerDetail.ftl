<#include "/include/taglib.ftl" >
<html>
<head>
</head>
<body>

	<div>
       

	<#if message??><@tags.message content=message /></#if>
	<div class="portlet light ">
			<ul class="nav nav-tabs mb-25">
	            <li >
	                <a  href="${ctx}/sys/repairer/list"><@spring.message code="form.list"></@spring.message>
	                </a>
	            </li>
	            <li class="active">
	                <a  href="javascript:;">
                                                     <@spring.message code="form.detail"></@spring.message>
	            	 </a>
	            </li>
        	</ul>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
	<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/repairer/save" method="post" class="form-horizontal">
		<@form.hidden path="uid"/>
		<div class="form-body">
		  
		<div class="form-group">
			<label class="col-xs-3 control-label">Profile Picture:</label>
			<div class="col-xs-4">
				<p class="form-control-static">
					<#if info.headImgPath ?? && info.headImgPath ? length gt 0 >
	            		<span class="item"><img src="${(info.headImgPath)!}" width="120px" height="120px"></span>
	            	</#if>
            	</p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">User's mobile:</label>
			<div class="col-xs-4">
				<p class="form-control-static"> ${info.mobile!} </p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">User's name:</label>
			<div class="col-xs-4">
				<p class="form-control-static"> ${info.name!} </p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">company name:</label>
			<div class="col-xs-4">
				<p class="form-control-static"> ${info.companyName!} </p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">institution name:</label>
			<div class="col-xs-4"> 
				<p class="form-control-static"> ${info.officeName!} </p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">contry:</label>
			<div class="col-xs-4"> 
				<p class="form-control-static"> 
						<#list countries as country>
							<#if info.country ?? && info.country == country.nationCode>
								${country.englishName!}
							</#if>
						</#list>		
				</p>
			</div>
		</div>
		
		<#--
		<div class="form-group">
			<label class="col-xs-3 control-label">所属区域:</label>
			<div class="col-xs-4">
			<p class="form-control-static"> 
				${fns.getDictFullLabel(info.areaId,'vc.thinker.sys.bo.DicAreaBO',' ')}
			</p>
			</div>
		</div>
		 -->
	</@form.form>
	</div>
   </div>
</body>
</html>