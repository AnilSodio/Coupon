<#include "/include/taglib.ftl" >
<html>
<head>
<#include "/include/chart.ftl" >
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
                    <span><@spring.message code="stats.analysis"></@spring.message></span>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="stats.dataProofing"></@spring.message></span>
                </li>
            </ul>
        </div>
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <span class="caption-subject font-green bold"><@spring.message code="stats.dataProofreading"></@spring.message></span>
                </div>
            </div>
            <div class="note note-success">
						<p>
						<@spring.message code="stats.chooseTimeToProofread"></@spring.message>
						</p>
					</div>
             <form id="searchForm" action="${ctx}/sys/stats/resetSave" method="post" class="breadcrumb form-search">
            <div>
            	&nbsp;&nbsp;&nbsp;
            	time：
            	 <input  name="beginDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
            	 value="${beginDate!}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>-
            	 <input  name="endDate" type="text" readonly="readonly" maxlength="20" class="form-control input-small input-inline"
            	 value="${endDate!}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                &nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="stats.proofreading"></@spring.message>" />
            </div>
        </form>
        <div class="portlet-body">
            <div id="sample_4_wrapper" class="dataTables_wrapper">
				<@tags.message content=message! />
            </div>
        </div>
        </div>
	</div>
	
	<script type="text/javascript">
		
    </script>
</body>
</html>