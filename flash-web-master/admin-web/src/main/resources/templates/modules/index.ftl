<#include "/include/taglib.ftl" >
<!DOCTYPE html>
<head>
<title><@spring.message code="sys.product.name"></@spring.message></title>
<#include "/include/dialog.ftl" >
</head>
<body>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo" style="padding-left:10; background:rgb(${sysSettingBO.logoRgb!'0,0,0'});">
            <a href="${ctx}">
                <img src="${sysSettingBO.logoImg!}" alt="logo" class="logo-default"/>
            </a>
            <div class="menu-toggler sidebar-toggler">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
           data-target=".navbar-collapse">
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN PAGE TOP -->
        <div class="page-top">

            <div class="nav-collapse">
                <ul class="nav navbar-nav" id="menu">
                
                 <@shiro.hasAnyPermissions name="sys:liveData:initMap,sys:liveData:cabinetOnline,sys:liveData:PowerbankOnline">
                 <li class="menu active"><a id="group_a1" href="javascript:;" class="menu"><@spring.message code="online.liveData"></@spring.message></a></li>
                 </@shiro.hasAnyPermissions>
                  
				 <@shiro.hasAnyPermissions name="sys:cabinetType:list,sys:cabinet:list,sys:sysCode:list,sys:order:list,sys:pbBuy:list,sys:feed:list">
                    <li class="menu"><a id="group_a2" href="javascript:;" class="menu"><@spring.message code="nomo.device"></@spring.message></a></li>
                </@shiro.hasAnyPermissions>
                
				 <@shiro.hasAnyPermissions name="sys:customer:view,sys:inteRule:list,sys:customer:rewares,sys:card:list,sys:vipPay:list,sys:payAmount:list,sys:recharge:list,sys:userRebate:list,sys:repairer:view,sys:seller:list,sys:referee:list,sys:agent:list">
                    <li class="menu"><a id="group_a9" href="javascript:;" class="menu"><@spring.message code="userInfo"/></a></li>
                </@shiro.hasAnyPermissions>

                <#-- 
                <@shiro.hasAnyPermissions name="sys:work:view,sys:work:timeOut,sys:faultType:view">
                    <li class="menu"><a id="group_a3" href="javascript:;" class="menu">运维管理</a></li>
                </@shiro.hasAnyPermissions>
                -->

                <@shiro.hasAnyPermissions name="sys:money:list,sys:trip:view,sys:basicCost:list,sys:userMoneyCash:list">
                    <li class="menu"><a id="group_a5" href="javascript:;" class="menu"><@spring.message code="financial" /></a></li>
                </@shiro.hasAnyPermissions>

                <@shiro.hasAnyPermissions name="sys:stats:Statistics,sys:stats:realTime">
                    <li class="menu"><a id="group_a6" href="javascript:;" class="menu"><@spring.message code="stats.analysis"></@spring.message></a></li>
                </@shiro.hasAnyPermissions>

                <@shiro.hasAnyPermissions name="sys:coupon:list,sys:coupon:sendlist,sys:promotion:list,sys:initImg:list,sys:imageText:view">
                    <li class="menu"><a id="group_a7" href="javascript:;" class="menu"><@spring.message code="marketing.marketingManagement"></@spring.message></a></li>
                </@shiro.hasAnyPermissions>
                
                <@shiro.hasAnyPermissions name="sys:user:view,sys:office:view,sys:role:view,sys:exRate:list,sys:warning:modify,sys:share:modify,sys:guide:view,sys:sysMessage:view,sys:feedback:list,sys:imageText:view">
                    <li class="menu"><a id="group_a4" href="javascript:;" class="menu"><@spring.message code="sys.settings"></@spring.message></a></li>
                </@shiro.hasAnyPermissions>

                </ul>
            </div>

            <!-- BEGIN TOP NAVIGATION MENU -->
            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">

                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                    <!-- 未读消息-->
                    <li id="unReadMessage">
                        <a style="padding: 24px 20px"  href="${ctx}/sys/feedbackMessage/list" target="mainFrame">
                            <span class="glyphicon glyphicon-envelope" style="font-size: 20px"></span>
                            <div id="isHasNewMessage" style="display:none">
	                            <span  style="position: absolute; height: 8px;top: 24px;right: 16px; background-color: rgb(255, 0, 0); border-radius: 50% !important; width: 8px;"></span>
                            </div>
                        </a>
                    </li>
                    <li class="dropdown dropdown-user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <span class="username username-hide-on-mobile"> <@shiro.principal property="userName"/> </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li>
                                <a target="mainFrame" href="${ctx}/sys/user/info">
                                    <i class="icon-user"></i>&nbsp;<@spring.message code="admin.platUser.persionInfo"/></a>
                            </li>
                            <li>
                                <a target="mainFrame" href="${ctx}/sys/user/modifyPwd">
                                    <i class="icon-lock"></i>&nbsp;<@spring.message code="admin.platUser.changePws"/></a>
                            </li>
                            <li>
                                <a target="mainFrame" href="${ctx}/logout">
                                    <i class="icon-key"></i><@spring.message code="admin.platUser.signOut"/></a>
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
            </div>
            <!-- END TOP NAVIGATION MENU -->
        </div>
        <!-- END PAGE TOP -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->

<div class="clearfix">
</div>


<!-- BEGIN CONTAINER -->
<div class="">
    <div class="page-container">
        <!-- BEGIN SIDEBAR -->
        <div class="page-sidebar-wrapper">
            <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
            <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
            <div class="page-sidebar navbar-collapse collapse" id="sideMenu">
                <!-- BEGIN SIDEBAR MENU -->
                <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->

				<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 322px;">
					<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" data-height="322" style="overflow: hidden; width: auto; height: 322px;" data-initialized="1">

	                    <@shiro.hasAnyPermissions name="sys:liveData:initMap,sys:liveData:cabinetOnline,sys:liveData:PowerbankOnline">
	                   	<li pid="group_a1" class="open">
	                        <a href="javascript:;">
		                        <i class="fa fa-map-marker"></i>
		                        <span class="title">&nbsp;<@spring.message code="online.liveData"></@spring.message></span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu" style="display: block;">
		                        <@shiro.hasPermission name="sys:liveData:initMap">
		                            <li class="active">
		                                <a target="mainFrame" href="${ctx}/sys/googleMap/initMap">
		                                &nbsp;<@spring.message code="online.onMap"></@spring.message>
		                                </a>
		                            </li>
		                        </@shiro.hasPermission>
		                        <@shiro.hasPermission name="sys:liveData:cabinetOnline">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/googleMap/cabinetOnline">
	                                &nbsp;<@spring.message code="online.liveNomoBox"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:liveData:PowerbankOnline">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/googleMap/pbOnline">
	                                &nbsp;<@spring.message code="online.livePb"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
		                </@shiro.hasAnyPermissions>


		                <@shiro.hasAnyPermissions name="sys:cabinetType:list,sys:cabinet:list,sys:sysCode:list">
		                
	                    <li pid="group_a2" style="display: none;">
	                        <a href="javascript:;">
		                        <i class="fa fa-flash"></i>
		                        <span class="title">&nbsp;<@spring.message code="nomo.boxes"></@spring.message></span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu" style="display: block;">
	                        	<@shiro.hasPermission name="sys:cabinetType:list">
	                            <li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/cabinetType/list">
	                                &nbsp;<@spring.message code="role.nomoBoxType"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        	<@shiro.hasPermission name="sys:rules:chargeRules">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/rules/ruleList">
	                                &nbsp;<@spring.message code="nomo.type.chargeRule"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>	                            
	                            
	                        	<@shiro.hasPermission name="sys:cabinet:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/cabinet/list">
	                                &nbsp;<@spring.message code="role.nomoBox"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        	<@shiro.hasPermission name="sys:sysCode:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/sysCode/list">
	                                &nbsp;<@spring.message code="role.qrCode"></@spring.message>
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/sysCode/list?isPrint=true">
	                                &nbsp;<@spring.message code="role.history.qrCode"></@spring.message>
	                                </a>
	                            </li>

	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <@shiro.hasAnyPermissions name="sys:order:list,sys:pbBuy:list">
	                    <li pid="group_a2" style="display: none;">
	                        <a href="javascript:;">
		                        <i class="fa fa-plane"></i>
		                        <span class="title">&nbsp;<@spring.message code="nomo.order"></@spring.message></span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                        	<@shiro.hasPermission name="sys:order:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/order/list">
	                                &nbsp;<@spring.message code="role.order"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        	<@shiro.hasPermission name="sys:pbBuy:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/pbBuy/list">
	                                &nbsp;<@spring.message code="role.pbBuy"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <@shiro.hasPermission name="sys:feed:list">
	                    <li pid="group_a2" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-envelope-o"></i>
	                        <span class="title">&nbsp;<@spring.message code="nomo.feedback"></@spring.message></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/feddback/list">
										&nbsp;<@spring.message code="role.feedback"></@spring.message>
	                                </a>
	                            </li>

	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/feddback/typeList">
										&nbsp;<@spring.message code="role.feedbackType"></@spring.message>
	                                </a>
	                            </li>
	                        </ul>
                    	</li>
	                    </@shiro.hasPermission>


	                    <@shiro.hasAnyPermissions name="sys:customer:view,sys:inteRule:list,sys:customer:rewares,sys:card:list,sys:vipPay:list,sys:payAmount:list,sys:recharge:list,sys:userRebate:list">
	                    <li pid="group_a9" style="display: none;">
	                        <a href="javascript:;">
		                        <i class="fa fa-user"></i>
		                        <span class="title">&nbsp;<@spring.message code="userInfo.member"/></span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu" style="display: block;">
	                        	<@shiro.hasPermission name="sys:customer:view">
	                            <li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/customer/list">
	                                &nbsp;<@spring.message code="userInfo.member.manage"/>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <#-- 
	                            <@shiro.hasPermission name="sys:inteRule:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/inteRule/list">
	                                &nbsp;<@spring.message code="userInfo.member.rewards.setting"/>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            -->
	                            
	                            <@shiro.hasPermission name="sys:customer:rewares">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/customer/integral_log">
	                                &nbsp;<@spring.message code="userInfo.member.rewards.redemption"/>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <#if isOpenMemberCard?? && isOpenMemberCard>
		                            <@shiro.hasPermission name="sys:card:list">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/card/list">
		                                &nbsp;<@spring.message code="userInfo.member.vip.set"/>
		                                </a>
		                            </li>
		                            </@shiro.hasPermission>

		                            <@shiro.hasPermission name="sys:vipPay:list">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/vipPay/list">
		                                &nbsp;<@spring.message code="userInfo.member.vip.cardRecord"/>
		                                </a>
		                            </li>
		                            </@shiro.hasPermission>
	                            </#if>
	                            
	                            <#if isOpenBalance?? && isOpenBalance>
		                            <@shiro.hasPermission name="sys:payAmount:list">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/payAmount/list">
		                                &nbsp;<@spring.message code="userInfo.member.top.set"/>
		                                </a>
		                            </li>
		                            </@shiro.hasPermission>
		                            <@shiro.hasPermission name="sys:recharge:list">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/recharge/list">
		                                &nbsp;<@spring.message code="userInfo.member.top.record"/>
		                                </a>
		                            </li>
		                            </@shiro.hasPermission>


		                            <@shiro.hasPermission name="sys:userRebate:list">
		                             <li>
		                                <a target="mainFrame" href="${ctx}/sys/userRebateLog/list">
		                                &nbsp;<@spring.message code="userInfo.member.rebate.record"/>
		                                </a>
		                            </li>
		                            </@shiro.hasPermission>
	                            </#if>
	                        </ul>
	                    </li>

	                    </@shiro.hasAnyPermissions>

	                    <@shiro.hasPermission name="sys:repairer:view">
	                    <li pid="group_a9" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-user"></i>
	                        <span class="title">&nbsp;<@spring.message code="role.maintenance"/></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/repairer/list">
	                                &nbsp;<@spring.message code="userInfo.maintenance"/>
	                                </a>
	                            </li>
	                        </ul>
                    	</li>
	                    </@shiro.hasPermission>

	                    <@shiro.hasPermission name="sys:seller:list">
	                    <li pid="group_a9" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-user"></i>
	                        <span class="title">&nbsp;<@spring.message code="nomo.merchant"/></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/seller/list">
	                                &nbsp;<@spring.message code="userInfo.merchant.manage"/>
	                                </a>
	                            </li>
	                        </ul>
                    	</li>
	                    </@shiro.hasPermission>

	                    <@shiro.hasPermission name="sys:referee:list">
	                    <li pid="group_a9" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-user"></i>
	                        <span class="title">&nbsp;<@spring.message code="nomo.introducer"/></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/referee/list">
	                                &nbsp;<@spring.message code="role.introducer"/>
	                                </a>
	                            </li>
	                        </ul>
                    	</li>
	                    </@shiro.hasPermission>

	                    <@shiro.hasPermission name="sys:agent:list">
	                    <li pid="group_a9" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-user"></i>
	                        <span class="title">&nbsp;<@spring.message code="nomo.agent"/></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/agent/list">
	                                &nbsp;<@spring.message code="role.agent"/>
	                                </a>
	                            </li>
	                        </ul>
                    	</li>
	                    </@shiro.hasPermission>



	                    <#-- Home -->
	                    <@shiro.hasAnyPermissions name="sys:user:view,sys:office:view,sys:role:view">
	                    <li pid="group_a4" class="open" style="display: none;">
	                        <a href="javascript:;">
		                        <i class="fa-check-square-o"></i>
		                        <span class="title">&nbsp;<@spring.message code="sys.authorization"></@spring.message></span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu" style="display: block;">
	                            <@shiro.hasPermission name="sys:user:view">
	                            <li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/user/">
	                                &nbsp;<@spring.message code="sys.platformUsers"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:office:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/office/">
	                                    &nbsp;<@spring.message code="sys.organizational"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:role:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/role/">
	                                &nbsp;<@spring.message code="sys.roleManagement"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <#--
	                            <@shiro.hasPermission name="sys:dict:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/dict/">
	                                    &nbsp;字典管理
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:template:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/template/">
List
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            -->
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <@shiro.hasAnyPermissions name="sys:exRate:list,sys:warning:modify,sys:share:modify,sys:resource:list">
	                    <li pid="group_a4" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-ge "></i>
	                        <span class="title">&nbsp;<@spring.message code="sys.basic.settings"></@spring.message></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                        	<@shiro.hasPermission name="sys:warning:modify">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/warning/modify">
		                                &nbsp;<@spring.message code="sys.basic.settings"></@spring.message>
		                                </a>
		                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <#-- 
	                            <@shiro.hasPermission name="sys:share:modify">
		                            <li>
		                                <a target="mainFrame" href="${ctx}/sys/share/modify">
		                                &nbsp;<@spring.message code="sys.sharingMessagess"></@spring.message>
		                                </a>
		                            </li>
	                            </@shiro.hasPermission>
                                <@shiro.hasPermission name="sys:resource:list">
                                    <li>
                                        <a target="mainFrame" href="${ctx}/sys/resource/list">
                                            &nbsp;<@spring.message code="sys.multilingualSetting"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>
								-->
                                <@shiro.hasPermission name="sys:country:list">
                                    <li>
                                        <a target="mainFrame" href="${ctx}/sys/country/list">
                                            &nbsp;<@spring.message code="sys.controlManage"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>
								<@shiro.hasPermission name="sys:exRate:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/exRate/list">
	                                &nbsp;<@spring.message code="sys.currencyExchangeRate"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <#--
	                    <@shiro.hasAnyPermissions name="sys:log:view,sys:log:loginlist">
	                    <li pid="group_a4" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa  fa-desktop"></i>
	                        <span class="title">&nbsp;日志Search </span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                        	<@shiro.hasPermission name="sys:log:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/log/loginList">
	                                &nbsp;登录日志
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:log:loginlist">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/log">
	                                    &nbsp;日志Search
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>
	                    -->
	                    <@shiro.hasPermission name="sys:guide:view">
	                    <li pid="group_a4" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-check-square-o "></i>
	                        <span class="title">&nbsp;<@spring.message code="sys.guidance"></@spring.message></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/guide/list"><@spring.message code="sys.articleList"></@spring.message>
	                                </a>
	                            </li>
	                        </ul>
	                    </li>
	                    </@shiro.hasPermission>

	                    <@shiro.hasAnyPermissions name="sys:sysMessage:view,sys:feedback:list,sys:imageText:view">
	                    <li pid="group_a4" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa  fa-desktop"></i>
	                        <span class="title">&nbsp;<@spring.message code="sys.message"/></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                        
	                        	<@shiro.hasPermission name="sys:sysMessage:view">
	                        	<li>
	                                <a target="mainFrame" href="${ctx}/sys/sysMessage/slist">
	                                &nbsp;<@spring.message code="sys.systemNotification"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:imageText:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/imageText/list?imageType=1">
	                                &nbsp;<@spring.message code="sys.materialManagement"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <#-- Financial Management -->
	                    <@shiro.hasAnyPermissions name="sys:money:list,sys:trip:view,sys:playReven:list,sys:userMoneyCash:list,sys:userRebate:list,sys:basicCost:list,sys:vipPay:list">
	                    <li pid="group_a5" class="open" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-money"></i>
	                        <span class="title">&nbsp;<@spring.message code="financial.manage" /></span>
	                        <span class="arrow "></span>
	                        </a>
	                       <ul class="sub-menu" style="display: block;">
	                       		<@shiro.hasPermission name="sys:money:list">
	                            <li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/money/list">
	                                &nbsp;<@spring.message code="financial.deposit.manage" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                       		
	                            <@shiro.hasPermission name="sys:money:check">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/money/check">
	                                &nbsp;<@spring.message code="financial.deposit.refund" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:money:list">
	                            <li >
	                                <a target="mainFrame" href="${ctx}/sys/money/log_list">
	                                &nbsp;<@spring.message code="financial.deposit.record" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            <@shiro.hasPermission name="sys:basicCost:list">
	                            <li >
	                                <a target="mainFrame" href="${ctx}/sys/basicCost/list">
	                                &nbsp;<@spring.message code="financial.membership.cardRecord" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <#if isOpenBalance?? && isOpenBalance>
	                            <@shiro.hasPermission name="sys:payAmount:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/recharge/log_list">
	                                &nbsp;<@spring.message code="userInfo.member.top.record" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            </#if>
	                            <#if isOpenMemberCard?? && isOpenMemberCard>
	                            <@shiro.hasPermission name="sys:vipPay:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/vipPay/log_list">
	                                &nbsp;<@spring.message code="financial.vip.passRecord" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            </#if>
	                            
	                            <@shiro.hasPermission name="sys:playReven:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/playReven/list">
	                                &nbsp;<@spring.message code="stats.platformRevenue"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:userMoneyCash:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userMoneyCash/agentCash">
	                                &nbsp;<@spring.message code="financial.withdrawal.agent" />
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userMoneyCash/refereeCash">
	                                &nbsp;<@spring.message code="financial.withdrawal.introducer" />
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userMoneyCash/sellerCash">
	                                &nbsp;<@spring.message code="financial.withdrawal.merchant" /> 
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:userRebate:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userRebateLog/agentList">
	                                &nbsp;<@spring.message code="stats.agentIncome"></@spring.message>
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userRebateLog/refereeList">
	                                &nbsp;<@spring.message code="stats.introducerIncome"></@spring.message>
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/userRebateLog/sellerList">
	                                &nbsp;<@spring.message code="financial.income.withdrawal" />
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>


	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <#-- Analysis  -->
	                    <@shiro.hasAnyPermissions name="sys:stats:Statistics,sys:stats:realTime">
	                    <li pid="group_a6" class="open" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-database"></i>
	                        <span class="title">&nbsp;<@spring.message code="stats.analysis"></@spring.message></span>
	                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu" style="display: block;">

								<@shiro.hasPermission name="sys:stats:realTime">
                                    <li class="active">
                                        <a target="mainFrame" href="${ctx}/sys/rlStats/realTime">
                                            &nbsp;<@spring.message code="stats.realTimeData"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>

	                           <#--<@shiro.hasPermission name="sys:stats:datastats">-->
	                           <#--<li>-->
	                                <#--<a target="mainFrame" href="${ctx}/sys/stats/dataStats">-->
	                                <#--&nbsp;数据统计-->
	                                <#--</a>-->
	                            <#--</li>-->
	                            <#--</@shiro.hasPermission>-->



								<@shiro.hasPermission name="sys:stats:Statistics">
                                    <li>
                                        <a target="mainFrame" href="${ctx}/sys/stats/percentStats">
                                            &nbsp;<@spring.message code="stats.dataStatistics"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>

	                            <@shiro.hasPermission name="sys:stats:Statistics">
	                             <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/userStats">
	                                &nbsp;<@spring.message code="stats.userStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:stats:Statistics">
	                             <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/depositStats">
	                                &nbsp;<@spring.message code="stats.depositStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/platformIncomeStats">
	                                &nbsp;<@spring.message code="stats.platformRevenue"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:stats:Statistics">
	                             <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/cabinetStats">
	                                &nbsp;<@spring.message code="stats.NOMOBoxStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:stats:Statistics">
	                             <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/pbStats">
	                                &nbsp;<@spring.message code="stats.powebankStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>

	                            <#--
	                            <@shiro.hasPermission name="sys:stats:consumestats">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/consumeStats">
	                                &nbsp;行程消费统计
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                             -->
	                            
	                            
	                            <@shiro.hasPermission name="sys:stats:Statistics">
	                           <#-- 
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/memberConsumeStats">
	                                &nbsp;<@spring.message code="stats.VIP.approvedSettingStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            -->
	                            
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/balanceConsumeStats">
	                                &nbsp;<@spring.message code="stats.balanceStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/feedbackStats">
	                                &nbsp;<@spring.message code="stats.feedbackStatistics"></@spring.message>
	                                </a>
	                            </li>
	                            
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/sellerIncomeStats">
	                                &nbsp;<@spring.message code="stats.merchantsIncome"></@spring.message>
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/refereeIncomeStats">
	                                &nbsp;<@spring.message code="stats.introducerIncome"></@spring.message>
	                                </a>
	                            </li>
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/stats/agentIncomeStats">
	                                &nbsp;<@spring.message code="stats.agentIncome"></@spring.message>
	                                </a>
	                            </li>


	                            </@shiro.hasPermission>
	                            
	                            <#-- 
	                            <@shiro.hasPermission name="sys:stats:realTime">
                                    <li>
                                        <a target="mainFrame" href="${ctx}/sys/reportStats/reportForm">
                                            &nbsp;<@spring.message code="stats.dataReport"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>
								<@shiro.hasPermission name="sys:stats:Statistics">
                                    <li>
                                        <a target="mainFrame" href="${ctx}/sys/stats/reset">
                                            &nbsp;<@spring.message code="stats.dataProofing"></@spring.message>
                                        </a>
                                    </li>
								</@shiro.hasPermission>
								-->
                       		 </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <#-- Marketing Management-->
	                    <@shiro.hasAnyPermissions name="sys:coupon:list,sys:coupon:sendlist,sys:promotion:list">
	                    <li pid="group_a7" class="open" style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-gift  "></i>
	                        <span class="title">&nbsp;<@spring.message code="marketing.couponManagement"></@spring.message></span>
	                        <span class="arrow "></span>
	                        </a>
	                       <ul class="sub-menu" style="display: block;">
	                       		<@shiro.hasPermission name="sys:coupon:list">
	                            <li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/pro/listCoupon">
	                                &nbsp;<@spring.message code="marketing.couponType"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                            <@shiro.hasPermission name="sys:coupon:sendlist">
	                        	<li>
	                                <a target="mainFrame" href="${ctx}/sys/pro/findCouponList">
	                                &nbsp;<@spring.message code="marketing.couponSendList"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>

	                            <#--
	                            <@shiro.hasPermission name="sys:proType:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/proType/list">
	                                &nbsp;<@spring.message code="marketing.activityType"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            -->
	                            
	                        	<@shiro.hasPermission name="sys:promotion:list">
	                        	<li class="active">
	                                <a target="mainFrame" href="${ctx}/sys/pro/listPro">
	                                &nbsp;<@spring.message code="marketing.activityManagement"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>


	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>

	                    <@shiro.hasAnyPermissions name="sys:initImg:list,sys:imageText:view">
	                    <li pid="group_a7"  style="display: none;">
	                        <a href="javascript:;">
	                        <i class="fa fa-heart"></i>
	                        <span class="title">&nbsp;<@spring.message code="marketing.adManagement"></@spring.message> </span>
	                        <span class="arrow "></span>
	                        </a>
	                       <ul class="sub-menu">
	                       		<@shiro.hasPermission name="sys:initImg:list">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/initImg/list?imgType=1">
	                                &nbsp;<@spring.message code="marketing.startPageAds"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                            
	                       		<@shiro.hasPermission name="sys:imageText:view">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/sys/imageText/list?imageType=2">
	                                &nbsp;<@spring.message code="marketing.homeAds"></@spring.message>
	                                </a>
	                            </li>
	                            </@shiro.hasPermission>
	                        </ul>
	                    </li>
	                    </@shiro.hasAnyPermissions>
					</ul>
				</div>
				
            </div>
        </div>
        <!-- END SIDEBAR -->
        <!-- BEGIN CONTENT -->
        <div class="page-content-wrapper">
            <div class="page-content">
            	<#--

                <iframe id="mainFrame" name="mainFrame" src="${ctx}/sys/data/heatMapQuery" scrolling="auto"
                        frameborder="no" width="100%" onLoad="iFrameHeight(this)"></iframe>
            	-->
                <iframe id="mainFrame" name="mainFrame" src="${ctx}/sys/googleMap/initMap" scrolling="auto"
                        frameborder="no" width="100%" onLoad="iFrameHeight(this)"></iframe>
            </div>
        </div>
        <!-- END CONTENT -->
        <!-- BEGIN QUICK SIDEBAR -->
        <!--Cooming Soon...-->
        <!-- END QUICK SIDEBAR -->
    </div>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
    <div class="page-footer center">
        <div class="page-footer-inner">
		    Copyright &copy; <@spring.message code="sys.product.copyright"></@spring.message> <@spring.message code="sys.product.name"></@spring.message>「<@spring.message code="sys.product.version"></@spring.message>」 - <@spring.message code="sys.product.website"> </@spring.message> 
        </div>
        <div class="scroll-to-top">
            <i class="icon-arrow-up"></i>
        </div>
    </div>
    <!-- END FOOTER -->
</div>

<script type="text/javascript">

    function iFrameHeight(frame) {
        /*
            var ifm = frame;
            if (typeof frame == "string") {
                ifm = frame = document.getElementById(frame);
            }
            var subWeb = document.frames ? document.frames[ifm.id].document : ifm.contentDocument;
            if(ifm != null) {
               ifm.height = subWeb.body.scrollHeight;
               ifm.width = $(frame).parent().width();
            }
            */
        var ifm = frame;
        var content = $('.page-content');
        var available_height = Metronic.getViewPort().height - $('.page-footer').outerHeight() - $('.page-header').outerHeight();


        $(frame).height(available_height - 6);

    }

    var resizeTimer = null;
    $(window).on('resize', function () {
                if (resizeTimer) {
                    clearTimeout(resizeTimer)
                }
                resizeTimer = setTimeout(function () {
                    iFrameHeight(document.getElementById("mainFrame"));
                }, 400);
            }
    );

    function changeLanguage(val) {
        window.location.href = "/setLocale/" + val + "?url=" + encodeURI(window.location.href);
    }

    $("#sideMenu").find("a[target]").click(function () {
        $("#sideMenu").find(".active").removeClass("active");
        $(this).parent().addClass("active");
    });

    $("#menu").find("a").click(function () {
        var $a = $(".page-sidebar-menu > li").hide().filter("[pid=" + $(this)[0].id + "]").show().find("a[target]").eq(0),
                $ul = $a.parents("ul").eq(0);
        $("#menu").find(".active").removeClass("active");
        $(this).parent().addClass("active");

        mainFrame.location.href = $a.attr("href");
        $ul.show();
        $ul.parent().addClass("open").siblings().removeClass("open");

    });


    $(document).ready(function() {
		 $.ajax({
    		url: "${ctx}/sys/feedbackMessage/checkIsNewMessage",
    		success:function(data)
    		{
    			 if(data){
    			 	$("#isHasNewMessage").attr("style","display:block");
    			 }else {
    			 	$("#isHasNewMessage").attr("style","display:none");
    			 }
    		}
		});

		$("#unReadMessage").click(function(){
			$("#isHasNewMessage").attr("style","display:none");
		});
	});

</script>
</body>
</html>