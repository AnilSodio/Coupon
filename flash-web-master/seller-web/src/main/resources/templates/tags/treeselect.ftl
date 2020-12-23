<#include "/include/taglib.ftl" >
<#macro treeselect id name value labelName labelValue title url extId="" cssClass="" cssStyle="" checked=false notAllowSelectRoot=false notAllowSelectParent=false selectScopeModule=false allowClear=false disabled=false nodesLevel=1 nameLevel=1>

<div class="input-group">
    <input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}" ${disabled?string("disabled='disabled'","")}/>
    <input id="${id}Name" name="${labelName}" readonly="readonly" type="text" value="${labelValue}" maxlength="50" ${disabled?string("disabled='disabled'","")}"
	class="${cssClass}" style="${cssStyle}"/><a id="${id}Button" href="javascript:" class="input-group-addon ${disabled?string("disabled='disabled'","")}"><i class="fa fa-search"></i></a>
</div>

<script type="text/javascript">
	$("#${id}Button").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Id").attr("disabled")){
			return true;
		}
        var nameLevel = ${nameLevel};
        
		// 正常打开	
		top.$.jBox.open("iframe:${ctx}/tag/treeselect?url=" + encodeURIComponent("${url}") + "&checked=${checked?c}&extId=${extId}&nodesLevel=${nodesLevel}&selectIds=" + $("#${id}Id").val(), "选择${title}", 300, 420, {
			buttons:{"确定":"ok", ${(allowClear)?string("'清除':'clear',","")}"关闭":true}, submit:function(v, h, f){
				if (v=="ok"){
					var ids = [], 
						  names = [], 
						  nodes = [],
						  tree = h.find("iframe")[0].contentWindow.tree; //h.find("iframe").contents();
						  
					if (${checked?c}){
						nodes = tree.getCheckedNodes(); //省略checked参数，等同于 true
					}else{
						nodes = tree.getSelectedNodes();
					}
					
					for(var i=0; i<nodes.length; i++) {//<#if checked>
						if (nodes[i].isParent){
							continue; // 如果为复选框选择，则过滤掉父节点
						}//</#if><#if notAllowSelectRoot>
						if (nodes[i].level == 0){
							top.$.jBox.tip("不能选择根节点（"+nodes[i].name+"）请重新选择。");
							return false;
						}//</#if><#if notAllowSelectParent>
						if (nodes[i].isParent){
							top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
							return false;
						}//</#if><#if selectScopeModule>
						if (nodes[i].module == ""){
							top.$.jBox.tip("不能选择公共模型（"+nodes[i].name+"）请重新选择。");
							return false;
						}else if (nodes[i].module != "${module}"){
							top.$.jBox.tip("不能选择当前栏目以外的栏目模型，请重新选择。");
							return false;
						}//</#if>
						
						ids.push(nodes[i].id);
						
                        var t_name = "",
                        	  t_node = nodes[i],
                        	  name_l = 0;
                        
                        do{
                            name_l++;
                            t_name = t_node.name + " " + t_name;
                            t_node = t_node.getParentNode();
                        }while(name_l < nameLevel);
                        
						names.push(t_name);//<#if !checked>
						break; // 如果为非复选框选择，则返回第一个选择  </#if>
					}
					
					$("#${id}Id").val(ids);
					$("#${id}Name").val(names);
				}//<#if allowClear>
				else if (v == "clear"){
					$("#${id}Id").val("");
					$("#${id}Name").val("");
                }//</#if>
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	});
</script>
</#macro>
