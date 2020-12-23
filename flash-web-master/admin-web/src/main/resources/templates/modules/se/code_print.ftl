<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		 function printme(){
			window.print();
	    }
	</script>
	<style type="text/css" media="print">
		    @page {
		    	padding: 0;
		        margin:0;
		        page-break-after: always;
		        background-color:#f00;
		    	color: #f00;
		    }
		    html, body { margin: 0; padding: 0;}
		     
		.page-container-custom { display: none;}
		.print-div { display: block !important;text-align: center;
           /* -webkit-transform: rotate(90deg);
            -moz-transform: rotate(90deg);
            -o-transform: rotate(90deg);
            -ms-transform: rotate(90deg);
            transform: rotate(90deg);*/
		}
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
				    <span>Box Management<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>NOMO box<i class="fa fa-angle-right"></i></span>
                </li>
               
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	            <li >
 					<a href="${ctx}/sys/cabinet/list"><@spring.message code="form.list"></@spring.message></a>
	            </li>
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0)"><@spring.message code="form.detail"></@spring.message></a>
	            </li>
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="${ctx}/sys/carinfo/save" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				<@tags.message content=message! />
				 <div class="form-body">
				 
				 	<div class="form-group">
						<label class="col-md-3 control-label">Box ID:</label>
						<p class="form-control-static">${info.sysCode !}</p>
					</div>
					
				 	<div class="form-group">
						<label class="col-md-3 control-label">Model name:</label>
						<p class="form-control-static">${info.typeName !}</p>
					</div>
					
					<div  class="form-group">
						<label class="col-md-3 control-label">QR code:</label>
						<div class="col-md-4">
							<img src="data:image/jpg;base64,${twoCode!}" class="p-code">
							<a  href="javascript:printme();">print</a>
						</div>
					</div>
					
				</div>
			</@form.form>
            </div>
        </div>
	</div>
	<div style="display: none; border:1px #f00 solid; width: 30mm; margin: 0 auto;" class="print-div">
		<div style=" padding: 0; line-height:1.2; text-align: center; font-size: 16pt; margin: 0 auto;">扫码借走</div>
		<img style="width:26mm; display: block; margin: 0 auto;" src="data:image/jpg;base64,${twoCode!}" class="p-code">
		<div style=" padding-top:0.2mm; text-align: center; font-size: 12pt; margin: 0 auto;">${info.sysCode!}</div>
	</div>
</body>
</html>