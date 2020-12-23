<#include "/include/taglib.ftl" >
<html>
<head>
</head>
<body>
		<div class="row" style="margin-left:20px;height:100px;width:150px;">
			<br/><br/>
			<br/>
			<div class="form-body">
			<div class="form-group">
				<label class="col-md-3 control-label">please choose审核结果:</label>
				<div class="col-md-4">
					<select id="authApplyStatus" name="authApplyStatus" class="select2 form-control input-small" onchange="queryContent(this)">
	    	 			<option value="">please choose</option>
	    	 			<option value="2">审核通过</option>
	    	 			<option value="3" >审核不通过</option>
    	 			</select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-3 control-label">请输入审核备注:</label>
				<div class="col-md-4">
					<textarea maxlength="150" cols="80" rows="6" id="authApplyRemark" name="authApplyRemark" class="form-control input-xlarge"/></textarea>
				</div>
			</div>
			
			</div>
			<br/>
		</div>
</body>
</html>