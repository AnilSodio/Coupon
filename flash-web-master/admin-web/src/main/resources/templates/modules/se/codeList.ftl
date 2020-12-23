<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
	    
	     function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	    
	   // function printme(){
	   //	window.print();
	   // }
	    
	    function printSysCode(isPrint){
	    
	    	console.log(isPrint);
	    	
	    	// var belongTypeId = $("#belongTypeId").val();
	    	
	    	 top.jBox.confirm('<@spring.message code="nomo.print.tishi"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	    	 
	              if (v ==true) {
	            	  
	            	  $.ajax({
			                 	url:"${ctx}/sys/sysCode/print",
			                 	type:"POST",
			                 	data:{
					            	"pageNo":${page.pageNo!'0'},
					            	"isPrint":isPrint
					            },
			                 	success:function(data){
			                 		window.print();
			                 	}
			          	 });
			          	 
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
	    }
	    
	</script>
	
	<script type="text/javascript">
	
		function addCode() {
		
	    	top.jBox.confirm('<@spring.message code="nomo.sure.create"></@spring.message>', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	    	
	    		var belongTypeId = $("#belongTypeId").val();
	    		
	             if (v ==true) {
	            	  loading('<@spring.message code="nomo.createIng"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/sysCode/save",
			                 	type:"POST",
			                 	data:{
			                 		"belongType":belongTypeId
			                 	},
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='nomo.createSu'></@spring.message>");
			                 		
			                 		window.location.href="${ctx}/sys/sysCode/list";
			                 	}
			          	 });
			          	 
			          	
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
		 }
		 
	function addSingleCode() {
		
	    	top.jBox.confirm('<p>Please enter a Qr Code</p><input id="singleQRId" name="singleCode" type="text" value="" maxlength="7" />', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
	    	
	    		var belongTypeId = f.singleCode;
	    	
	             if (v ==true) {
	            	  loading('<@spring.message code="nomo.createIng"></@spring.message>');
	            	  $.ajax({
			                 	url:"${ctx}/sys/sysCode/saveSingle",
			                 	type:"POST",
			                 	data:{
			                 		"belongType":belongTypeId
			                 	},
			                 	success:function(data){
			                 		top.$.jBox.tip("<@spring.message code='nomo.createSu'></@spring.message>");
			                 		
			                 		window.location.href="${ctx}/sys/sysCode/list";
			                 	}
			          	 });
			          	 
			          	
	            }
	            return true;
 			 }, { buttons: { 'Yes': true, 'No': false} });
		 }
		 
		 
		 
	</script>
	
	<style type="text/css" media="print">
		    @page {
		    	padding: 0;
		        margin:0;
		        page-break-after: always;
		        background-color:#f00;
		    	color: #f00;
		    	size: A4;
		    }
		    html, body { margin: 0; padding: 0;}
		     
		.page-container-custom { display: none;}
		.print-div { margin:4mm 0 0 3.6mm; display: inline-block !important;text-align: center;}
	</style>
	
	
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
                    <span>
                    	<#if vo.isPrint?? && vo.isPrint>
	                    	<@spring.message code="role.history.qrCode"></@spring.message>
                    	<#else>
                    		<@spring.message code="role.qrCode"></@spring.message>
                    	</#if>
                    </span>
                </li>
            </ul>
        </div>
        
        <div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	       		
	            
	            <li class="active">
	            	<a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            
	            <#if vo.isPrint?? && vo.isPrint>
	            <#else>
	            <@shiro.hasPermission name="sys:sysCode:create">
		            <li class="">
						<a href="javascript:addCode();"><@spring.message code="nomo.create"></@spring.message></a>
		            </li>
		            <li class="">
						<a href="javascript:addSingleCode();"><@spring.message code="nomo.create"></@spring.message></a>
		            </li>
		           </@shiro.hasPermission>
		           </#if>
                
	        </ul>
        	
        	<@form.form id="searchForm" modelAttribute="vo" action="${ctx}/sys/sysCode/list" method="post" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo!'0'}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize!'100'}"/>
					<input id="belongTypeId" name="belongType" type="hidden" value="${vo.belongType!}"/>
					
					<input id="btnSubmit" class="btn btn-primary" style="display:none" type="submit" value="<@spring.message code='form.query'></@spring.message>" onclick="return page(0, 100);"/>
					<input id="isPrint" name="isPrint" type="hidden" value="${vo.isPrint?string('true','false')}"/>
					
					 <@shiro.hasPermission name="sys:sysCode:create">
						<#if (page.content?size > 0)>
							<a class="btn btn-primary"  href="javascript:printSysCode('${vo.isPrint?string('true','false')}');"><@spring.message code='nomo.oneclickprint'></@spring.message></a>
						</#if>
					 </@shiro.hasPermission>
					
			</@form.form>
			
        	<#if (page.content?size > 0)>
		        <div class="portlet-body">
		            <div id="sample_4_wrapper" class="rint-div">
						<div>
						<#list page.content as info>
							<div style="display: inline-block; border:1px #000 solid; width: 30mm;">
								<div style=" padding: 0; line-height:0.8; text-align: center; font-size: 16pt; margin: 0 auto;">&nbsp;</div>
								<img style="width:26mm; display: block; margin: 0 auto;" src="data:image/jpg;base64,${info.twoCode!}" class="p-code">
								<div style=" padding-top:0.2mm; text-align: center; font-size: 12pt; margin: 0 auto;">${info.sysCode!}</div>
							</div>
						</#list>
						</div>
						<br/><br/>
						<div class="pagination">
					        ${page}
					    </div>
				  </div>
				</div>
				</#if>
        	</div>
    	</div>
    </div>
    
		<#if (page.content?size > 0)>
				<#list page.content as info>
					<div class="print-div" style="display:none">
						<div style="display: inline-block; border:1px #000 solid; width: 30mm;">
							<div style=" padding: 0; line-height:0.8; text-align: center; font-size: 16pt; margin: 0 auto;">&nbsp;</div>
							<img style="width:26mm; display: block; margin: 0 auto;" src="data:image/jpg;base64,${info.twoCode!}" class="p-code">
							<div style=" padding-top:0.2mm; text-align: center; font-size: 12pt; margin: 0 auto;">${info.sysCode!}</div>
						</div>
						<#-- 
							<div style="display: inline-block; border:1px #000 solid;  line-height:147px; font-size:120px;vertical-align: top;">
								<div style="padding-top:0.2mm; text-align: center; margin: 0 auto;">${info.sysCode!}</div>
							</div>
						-->
					</div>
				</#list>
				
		<#else>
			<div class="note note-warning alert">
				<@spring.message code="form.noData"></@spring.message>
			</div>
		</#if>
    
</body>
</html>