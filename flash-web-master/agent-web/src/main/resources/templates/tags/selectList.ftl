<#include "/include/taglib.ftl" >
<#macro selectList id name value labelName labelValue title url row col cssClass="" cssStyle="">

<div class="input-append">
    <div class="input-group input-small">
	    <input id="${id}Id" name="${name}" type="hidden" value="${value}"/>
	    <textarea id="${id}Name" name="${labelName}" readonly="readonly" type="text" row=${row} col="${col}" class="${cssClass}" style="${cssStyle}">${labelValue}</textarea>
		<a id="${id}Button" href="javascript:void(0);" onclick="1" class="input-group-addon"><i class="fa fa-search"></i></a>
    </div>
</div>

<script type="text/javascript">
	$("#${id}Button").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Id").attr("disabled")){
			return true;
		}
		// 正常打开	
		top.$.jBox.open("iframe:${ctx}/tag/selectList?url=" + encodeURIComponent("${url}") + "&ids=" + $("#${id}Id").val(), "${title}", 600, 475, {
			buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
				if (v=="ok"){
					var ids = "";
					var names = "";
					h.find("iframe").contents().find("#selectRight").find("option").each(function(index, item){
						ids += $(item).attr("id")+";";
						names += $(item).attr("title")+"; ";
					});
					$("#${id}Id").val(ids);
					$("#${id}Name").text(names);
				}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	});
</script>
</#macro>
