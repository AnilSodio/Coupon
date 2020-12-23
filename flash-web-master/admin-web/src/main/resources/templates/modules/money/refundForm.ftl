<#include "/include/taglib.ftl" >
<html>
<head>
</head>
<body>
	 	<div class="row" style="margin-left:20px">
				<input type="hidden" id="result" name="result" value ="1"/>
			<!--
				<label class="col-md-3">审核结果:</label>
				<option value='1' >审核通过</option>
			
				<select id="result" name="result" class="input-small form-control" style="margin-left:20px">
					<option value=''>－please choose审核结果－</option>
					
					<option value='0' >审核不通过</option>
				<select>
			-->
		</div>
		<div class="row" style="margin-left:20px">
			<label class="col-md-3 control-label"><@spring.message code="sys.remarks"></@spring.message>:</label>
			<textarea name="checkDesc"  class="form-control" id="checkDesc" style="width: 400px; margin-left: 20px; height: 70px;"></textarea>
		</div>
</body>
</html>