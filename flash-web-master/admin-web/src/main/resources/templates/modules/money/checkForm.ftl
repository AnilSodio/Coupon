<#include "/include/taglib.ftl" >
<html>
<head>
	<title>餐厅管理</title>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$("#btnSubmit").click(function(){
			
				var refundAccount = $("#refundAccount").val();
				
				if("" == refundAccount){
					top.$.jBox.tip("please choose退款资金来源");
					return false;
				}
				
				var remark = $("#remark").val();
				if("" == remark){
					top.$.jBox.tip("请输入备注信息");
					return false;
				}
				
				top.jBox.confirm('确认要审核吗？', '<@spring.message code="sys.prompt"></@spring.message>', function (v, h, f) {
    				if (v === 'ok') {
    					loading('正在审核，请稍等...');
         				$.ajax({
             				url:"${ctx}/sys/money/refund?id="+${info.id!}+"&refundAccount="+refundAccount+"&refundRemark="+remark,
             				type:"POST",
         					success:function(data){
             					if(1 == data) {
             						top.$.jBox.tip("恭喜，审核成功");
             						window.location.href="${ctx}/sys/money/check";
             					}else{
             						top.$.jBox.tip(data);
             					}
         					}
             			});
    				}
    				return true;
 				});
			})
		
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
                    <span>Financial Management<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>Refund deposit review<i class="fa fa-angle-right"></i></span>
                </li>
                <li>
                    <span>
                    	审核
                    </span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
	       <ul class="nav nav-tabs mb-25">
	           	<li><a href="${ctx}/sys/money/check"><@spring.message code="form.list"></@spring.message></a></li>
	             
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0);">审核</a>
	            </li>
	            	
	        </ul>
            <div class="portlet-body form">
			<@form.form id="inputForm" modelAttribute="info" action="" method="post" class="form-horizontal">
				<@form.hidden path="id"/>
				 <div class="form-body">
					<div class="form-group">
						<label class="col-md-3 control-label">退款资金来源:</label>
						<div class="col-md-4">
							<select id="refundAccount" name="refundAccount" class="select2 form-control input-small" onchange="queryContent(this)">
			    	 			<option value="REFUND_SOURCE_UNSETTLED_FUNDS">未结算资金</option>
			    	 			<option value="REFUND_SOURCE_RECHARGE_FUNDS" >可用余额</option>
		    	 			</select>
						</div>
					</div>
					
					
					<div class="form-group">
						<label class="col-md-3 control-label">备注:</label>
						<div class="col-md-4">
							<textarea maxlength="100" cols="80" rows="6" id="remark" name="remark" class="form-control input-xlarge"/></textarea>
						</div>
					</div>
					
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-4">
								<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>&nbsp;
							<input id="btnCancel" class="btn red" type="button" value="返回" onclick="history.go(-1)"/>
							</div>
						</div>
					</div>
				</div>
			</@form.form>
            </div>
        </div>
	</div>
</body>
</html>