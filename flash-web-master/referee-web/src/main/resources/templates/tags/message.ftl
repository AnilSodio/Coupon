<#macro message content type="success">
<script type="text/javascript">top.$.jBox.closeTip();</script>
	<#if content != "">
	<div id="messageBox" class="alert alert-${type}"><button data-dismiss="alert" class="close">×</button>${content}</div> 
	<#--<script type="text/javascript">if(!top.$.jBox.tip.mess){top.$.jBox.tip.mess=1;top.$.jBox.tip("${content}","${type}",{persistent:true,opacity:0});$("#messageBox").show();}</script>-->
	</#if>
</#macro>