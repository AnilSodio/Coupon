<html>
<head lang="en">
    <meta charset="UTF-8">
    <#include "/include/taglib.ftl" >
</head>
<body>

<div class="page-container-custom">
    <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    	<a target="_parent" href="/admin"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                    	<a target="_parent" href="/admin"><@spring.message code="sys.settings"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                    <a target="_parent" href="/admin"><@spring.message code="sys.management"></@spring.message></a>
                </li>
                <li>
				    <span><i class="fa fa-angle-right"></i></span>
				</li>
				<li><@spring.message code="sys.systemNotification"></@spring.message></li>
            </ul>
        </div>

<div class="portlet light portlet-fit portlet-datatable">

    <form action="${ctx}/sys/sysMessage/sendSysMessage" method="post" id="sendForm" onkeydown="if(event.keyCode==13)return false;">
    	<input type="hidden" name="imageTextId" id="imageTextId" value=""/>
    	<input type="hidden" name="isImageText" id="isImageText" value="true"/>
    	<input type="hidden" name="toUserIds" id="toUserIds" value=""/>
    	<input type="hidden" name="sendType" id="sendType" value="3"/>
    	
	<div class="row">
		<div class="col-md-8">
			<ul class="nav nav-tabs tabs-line-black">
			  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab" onclick="choseImage()"><@spring.message code="sys.graphicMessage"></@spring.message></a></li>
			  <li role="presentation"><a href="#profile" role="tab" data-toggle="tab" onclick="choseText()">Text</a></li>
			</ul>
			<!-- Tab panes -->
			  <div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">

				    <div class="add-msg-box" id="contentAll">
				    	<#if imageText.id??>
				    		<div class="add-msg-action">
					            <a href="javascript:;" id="selectTemplate" onclick="selectTemplate()" >
	                                  	<div class="cover">
                    					 <#if imageText.cover??>
								            <img id="img_img" src="${imageText.cover!}_250x139">
								            <#else>
								            <img src="/images/defuatcover.png" width="250" height="139">
								            </#if>
              							  </div>
						                <p id="img_title" class="title"></p>
						                <i class="ico-sel-arrow"></i>
					            </a>
					            <a href="javascript:;" id="delselected"  onclick="delselected()" class="remove" ><@spring.message code="form.delete"></@spring.message></a>
					        </div>

				    		<#else>
					    	<div class="add-msg-action">
					            <a href="javascript:;" id="selectTemplate" onclick="selectTemplate()" >
	                                <i class="ico-msg-plus"></i>
	                                <p>Choose from the images available</p>
					            </a>
					            <a href="javascript:;" id="delselected" style="display:none" onclick="delselected()" class="remove" >删除</a>
					        </div>
					         
					        <div class="add-msg-action" id="newTemplate">
					            <a href="${ctx}/sys/imageText/modify?imageType=1" >
	                                <i class="ico-msg-plus"></i>
	                                <p>New graphic</p>
	                            </a>
					        </div>
				    	</#if>
				        
				    </div>

				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
				    <div class="add-msg-box">
                        <div class="add-msg-textarea">
                            <textarea placeholder="Please enter the posting content" maxlength="300" name="content" id="content"></textarea>
                        </div>
                    </div>
				</div>

				<div class="add-msg-foot">
                    <span class="selected-all"><@spring.message code="sys.visibleRange"></@spring.message>: <@spring.message code="sys.visibleToAll"></@spring.message></span>
                    <div class="release-msg">
                    	<#-- 
	                       <span class="set-time-send">
	                           <label><input type="checkbox" id="setTimeSend" autocomplete="off" /> 定时发布 </label>
	                           <input style="width:140px;" type="text" name="sendTimeStr" id="setTimeInput" class="txt min-txt" placeholder="选择发布时间" readonly onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
	                       </span>
                       -->
                       
                       <input type="button" onclick="saveForm()" class="btn btn-blue btn-medium" value="<@spring.message code="sys.send"></@spring.message>" />
                    
                    </div>
                    <div id="receiverList" class="selected-receiver"></div>
                </div>

			  </div>
		</div>
		<div class="col-md-4">
		    <ul id="userSelectTabs" class="nav nav-tabs tabs-line-black">
                <#if isOpVip ?? && isOpVip>
               	  <li wid="J_vip"><a href="javascript:;">VIP user</a></li>
               	  <li wid="J_no_vip"><a href="javascript:;">Non-VIP users</a></li>
                </#if>
                <li wid="J_all" class="active"><a href="javascript:;">all</a></li>  
            </ul>
			<div class="select-user-box">
				<div class="static-search-box">
					<i class="ico-search"></i>
					<input type="text" id="filterUser" class="inp-text" placeholder="please enter mobile" />
				</div>
                <ul id="userList" class="select-user-list">
					<#--
                 	<#list repa_list as obj>
                    <li title="${obj.name!}" id="${obj.uid!}" class="J_repa">
                   		 <#if obj.headImgPath?? && obj.headImgPath?length gt 0>
                   		 	<img src="${obj.headImgPath!}_60x60">
                   		 <#else>
                   			 <img src="/images/defuatcover.png" width="60" height="60">
                   		 </#if>
                        <div class="cont">
                            <div class="name">${obj.name!}</div>
                            <div class="tel">${obj.mobile!}</div>
                        </div>
                    </li>
                    </#list>
                     -->
                    <#if (vip_list?? && vip_list?size > 0)>
	                    <#list vip_list as obj>
		                <li title="${obj.mobile!}" id="${obj.uid!}" class="J_vip" style="display:none">
		                    <div>
		                    	<#if obj.headImgPath??>
	                   		 		<img src="${obj.headImgPath!}_60x60">
	                   			 <#else>
	                   				 <img src="/images/defuatcover.png" width="60" height="60">
	                   		 	</#if>
	                   		 	<div class="cont">
			                        <div class="name">${obj.nickname!}</div>
			                        <div class="tel">${obj.mobile!}</div>
		                        </div>
		                    </div>
		                </li>
		                </#list>
	                </#if>
	                
                    <#if (noVip_list?? && noVip_list?size > 0)>
	                    <#list noVip_list as obj>
		                <li title="${obj.mobile!}" id="${obj.uid!}" class="J_no_vip" style="display:none">
		                    <div>
		                    	<#if obj.headImgPath??>
	                   		 		<img src="${obj.headImgPath!}_60x60">
	                   			 <#else>
	                   				 <img src="/images/defuatcover.png" width="60" height="60">
	                   		 	</#if>
	                   		 	<div class="cont">
			                        <div class="name">${obj.nickname!}</div>
			                        <div class="tel">${obj.mobile!}</div>
		                        </div>
		                    </div>
		                </li>
		                </#list>
	                </#if>
	                
	                <#-- 
	                <#if (all_list?size > 0)>
	                    <#list all_list as obj>
		                <li title="${obj.mobile!}" id="${obj.uid!}" class="J_all" style="display:none">
		                    <div>
		                    	<#if obj.headImgPath??>
	                   		 		<img src="${obj.headImgPath!}_60x60">
	                   			 <#else>
	                   				 <img src="/images/defuatcover.png" width="60" height="60">
	                   		 	</#if>
		                        <div class="name">${obj.nickname!}</div>
		                        <div class="tel">${obj.mobile!}</div>
		                    </div>
		                </li>
		                </#list>
	                </#if>
	                -->
	                <li  title="Everyone can see" class="text-center J_all">Everyone can see</li>
                </ul>
			</div>
		</div>
	</div>
	</form>
	</div>
</div>

<script>

	$("#userSelectTabs li").click(function() {
		$("#userList li").removeClass("selected").hide().filter("."+$(this).attr("wid")).show();
		$(this).addClass("active").siblings().removeClass("active");
		$("#receiverList").empty();
	});

	<#-- 
	    $("#setTimeSend").change(function() {
	        if ($(this).is(":checked")) {
	        	$("#status").val("1");
	            $("#setTimeInput").show();
	        } else {
	        	$("#status").val("2");
	            $("#setTimeInput").hide();
	        }
	    });
    -->

    $("#userList li").click(function() {
        var $selected, $list = $("#receiverList"), rec = "";
        $(this).toggleClass("selected");
        $selected = $(this).parent().find(".selected");
        if ($selected.length > 0) {
            $selected.each(function() {
                rec += ' <span sid="' + $(this)[0].id + '">'+ $(this)[0].title +'</span>';
            });
            rec += ' <a href="javascript:;" class="remove">remove all</a>';
            $list.html(rec);
        } else {
            $list.empty();
        }
    });

    $("#receiverList").click(function(e) {
        var $this = $(e.target), $box = $(this), tag = $this[0].tagName.toLowerCase();
        if (tag == "a") {
            $box.empty();
            $("#userList").find(".selected").removeClass("selected");
        } else if (tag == "span") {
            $("#" + $this.attr("sid")).removeClass("selected");
            $this.remove();
        }
        if ($box.find("span").length == 0) {
            $box.empty();
        }
    });

    $("#filterUser").keyup(function() {
        var $this = $(this), val = $.trim($this.val()), $li = $("#userList").find("li:visible");
        $li.show();
        if (val) {
            $li.not("[title*=" + val + "]").hide();
        }else {
        	//请输入你的代码
        	$("#userList").find("li").show();
        }
    });
    function delselected(){
    	$("#contentAll").html('<div class="add-msg-action">'
				        +'<a href="javascript:;" id="selectTemplate" onclick="selectTemplate()">'
                         +' <i class="ico-msg-plus"></i>'
                         +  ' <p>从已有的图文中选择</p>'
				        +    '</a>'
				        + '<a href="javascript:;" id="delselected" style="display:none"  onclick="delselected()" class="remove">删除</a>'
				        +'</div>'
				        +'<div class="add-msg-action" id="newTemplate">'
				        +    '<a href="${ctx}/sys/imageText/modify">'
                        +       ' <i class="ico-msg-plus"></i>'
                        +        '<p>新建图文</p>'
                        +    '</a>'
				       + '</div>');
		$("#imageTextId").val("");
    }
	 	
	 
	function selectTemplate(){
		 top.jBox("iframe:${ctx}/sys/sysMessage/selectImageText",{
                title: "<@spring.message code="sys.chooseFromTheExistingDrawings"></@spring.message>",
                width: 865,
                height: 440,
                submit: function() {
                
	                var obj = $(parent.document.body).find('#jbox-iframe').contents().find('#contentTable input:checked[name="repselect"]');
	             
	              	var result = obj.val();
	              	
	              	var html = '<div class="cover"><img src='+ result.split("&")[1] +'_250x139></div>\
			                <p class="title">'+ result.split("&")[2] +'</p>\
			                <i class="ico-sel-arrow"></i>';

	              	$("#imageTextId").val(result.split("&")[0]);
	              	$("#selectTemplate").html(html);
                  	$("#newTemplate").remove();
                  	$("#delselected").show();
                }
            });
	}
	function choseImage(){
		$("#isImageText").val(true);
	}
	function choseText(){
		$("#isImageText").val(false);
	}
    function saveForm(){
    
    	var toUserIds = "";
    	var arr = $("#userList").find(".selected");
    	if(arr!=null&&arr.length>0){
    		for(var i=0;i<arr.length;i++){
	    	  	if(i!=0){
	    	  		toUserIds+=","+arr[i].id;
	    	  	}else{
	    	  		toUserIds+=arr[i].id;
	    	  	}
    		}
    	}
    	$("#toUserIds").val(toUserIds);
    	if($("#isImageText").val()=='true'){
    		if($("#imageTextId").val()==''){
    			top.$.jBox.error('please choose图文', '<@spring.message code="sys.prompt"></@spring.message>');
    			return;
    		}
    	}else{
    		if($("#content").val()==''){
    			top.$.jBox.error('please enter content', '<@spring.message code="sys.prompt"></@spring.message>');
    			return;
    		}
    	}
    	
    	var userType = $("#userSelectTabs li.active").attr("wid");
    	//维保
    	if(userType == 'J_vip'){
    		$("#sendType").val(1);
    	}
    	if(userType == 'J_no_vip'){
    		$("#sendType").val(2);
    	}
    	//c端用户
    	if(userType == 'J_all'){
    		$("#sendType").val(3);
    	}
    	
    	jQuery("#sendForm").submit();
    }
</script>

</body>
</html>