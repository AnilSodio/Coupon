<#include "/include/taglib.ftl" >
<!DOCTYPE html>
<head>
	<title>${config.productName}</title>
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
		    <!-- 
		    <div class="nav-collapse">
               <ul class="nav navbar-nav" id="menu">
                    <li class="menu active"><a href="javascript:;" class="menu">系统设置</a></li>
               </ul>
            </div>
            -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
				<#--
					<li class="dropdown dropdown-user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<span class="username username-hide-on-mobile">${(lang.getLocale()?? && lang.getLocale() == 'en')?string('<img src="/static/assets/global/img/flags/gb.png" class="flag"> English', '<img src="/static/assets/global/img/flags/cn.png" class="flag"> 中文')}</span>
						<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a onclick="changeLanguage('cn')" href="javascript:void(0);">
									<img src="/static/assets/global/img/flags/cn.png" class="flag"> 中文
								</a>
							</li>
							<li>
								<a onclick="changeLanguage('en')" href="javascript:void(0);">
									<img src="/static/assets/global/img/flags/gb.png" class="flag">  English
								</a>
							</li>
						</ul>
					</li>
				-->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<span class="username username-hide-on-mobile"> <@shiro.principal property="userName"/> </span>
						<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a target="mainFrame" href="${ctx}/userInfo">
								<i class="icon-user"></i>&nbsp; User information</a>
							</li>
							<li>
								<a target="mainFrame" href="${ctx}/modifyPwd">
								<i class="icon-lock"></i>&nbsp; Change password</a>
							</li>
							<li>
								<a target="mainFrame"  href="${ctx}/logout">
								<i class="icon-key"></i>&nbsp; Logout</a>
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
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
				<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
				<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
				<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				
					<#-- 个人信息   -->
					<li>
                        <a href="javascript:;">
                        <i class="icon-user"></i>
                        <span class="title">&nbsp;User information</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a target="mainFrame" href="${ctx}/userInfo">
                                &nbsp;User information
                                </a>
                            </li>
                            <li>
                                <a target="mainFrame" href="${ctx}/modifyPwd">
                                &nbsp;Change password
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a target="mainFrame" href="${ctx}/seller/list" >
                        <i class="icon-credit-card"></i>
                        <span class="title">&nbsp;merchant list</span>
                        </a>
                    </li>
                    
                    
                    <!--
                    <li>
                        <a target="mainFrame" href="${ctx}/order/list" >
                        <i class="icon-film"></i>
                        <span class="title">&nbsp;order manage</span>
                        </a>
                    </li>-->
                    <#--会员 -->
	                    <li>
	                        <a href="javascript:;">
		                        <i class="fa fa-user"></i>
		                        <span class="title">&nbsp;Member management</span>
		                        <span class="arrow "></span>
	                        </a>
	                        <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/customer/list">
	                                &nbsp;Member management
	                                </a>
	                            </li>
	                        </ul>
	                    </li>
                    <#-- 订单   -->
                    <li>
	                        <a href="javascript:;">
		                        <i class="fa fa-plane"></i>
		                        <span class="title">&nbsp;Order Management</span>
		                        <span class="arrow "></span>
	                        </a>
	                       <ul class="sub-menu">
	                            <li>
	                                <a target="mainFrame" href="${ctx}/order/list">
	                              	 &nbsp;Order records
	                                </a>
	                            </li>
	                        </ul>
	                    </li>
                    <#-- 收益   -->
					<li>
                        <a href="javascript:;">
                        <i class="icon-note"></i>
                        <span class="title">&nbsp;Profit Sharing</span>
                        <span class="arrow "></span>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a target="mainFrame" href="${ctx}/rebate/list">
                                &nbsp;Profit Sharing Records
                                </a>
                            </li>
                            <li>
                                <a target="mainFrame" href="${ctx}/rebate/cashLog">
                                &nbsp;Cash Withdrawal Record
                                </a>
                            </li>
                            <li>
                                <a target="mainFrame" href="${ctx}/rebate/moneyLog">
                                &nbsp;Balance Record
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!--
					<li class="start active ">
						<a href="index.html">
						<i class="icon-home"></i>
						<span class="title">Dashboard</span>
						<span class="selected"></span>
						</a>
					</li>
					-->
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
            <div class="page-content"><iframe id="mainFrame" name="mainFrame" src="${ctx}/index" scrolling="auto" frameborder="no" width="100%" onLoad="iFrameHeight(this)"></iframe></div>
		</div>
		<!-- END CONTENT -->
		<!-- BEGIN QUICK SIDEBAR -->
		<!--Cooming Soon...-->
		<!-- END QUICK SIDEBAR -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="page-footer">
		<div class="page-footer-inner">
		    Copyright © 2018 SUPERBIG PTE LTD All Rights Reserved
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
                resizeTimer = setTimeout(function(){
                    iFrameHeight(document.getElementById("mainFrame"));
                }, 400);
            }
        );
        
        function changeLanguage(val){
        	window.location.href="/setLocale/"+val+"?url="+encodeURI(window.location.href);
        };
	</script>
</body>
</html>