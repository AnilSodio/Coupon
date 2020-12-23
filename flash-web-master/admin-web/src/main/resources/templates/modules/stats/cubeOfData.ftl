<#include "/include/taglib.ftl" >
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
                <span><@spring.message code="stats.realTimeData"></@spring.message></span>
            </li>
        </ul>
    </div>

<div class="portlet light ">

	
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-green bold"><@spring.message code="stats.total"></@spring.message></span>
        </div>
    </div>

    <div class="portlet-body form">
        <!-- BEGIN FORM-->

        <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat blue-madison" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        <#if (total.tripTimes)??&&(total.tripTimes >= 1000)>
                        ${(total.tripTimes/1000)?string('#.#')}k
                        <#else>
                        ${total.tripTimes!0}
                        </#if>
                         <span style="font-size:16px"><@spring.message code="stats.times"></@spring.message></span>
                        </div>
                        <div class="desc">
                        	<@spring.message code="stats.usageCount"></@spring.message>
                        </div>
                    </div>
                    <#--
                     <a href="${ctx}/sys/rlStats/group?type=tripNum&day=total&groupType=client_type" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                     -->
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat yellow" style="height:124px;">
            	
                    <div class="visual">
                        <i class="fa fa-child "></i>
                    </div>
                    <div class="details">
                        <div class="number">
                         	<#if (total.registNums)??&&(total.registNums >= 1000)>
	                        ${(total.registNums/1000)?string('#.#')}k
	                        <#else>
	                        ${total.registNums!0}
	                        </#if>
                         	<span style="font-size:16px"><@spring.message code="stats.people"></@spring.message></span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.registeredNumber"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/userStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10" >
                <div class="dashboard-stat green-haze" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-jpy"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                         	<#if (total.deposit)??&&(total.deposit >= 1000)>
	                        ${(total.deposit/1000)?string('#.#')}k
	                        <#else>
	                        ${total.deposit!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.deposit"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/depositStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>

            <#if setting.isOpenMemberCard>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10" >
                <div class="dashboard-stat grey-cascade" style="height:124px;">
                
                    <div class="visual">
                        <i class="fa fa-jpy"></i>
                    </div>
                    <div class="details">
                    	<div class="number" style=" line-height:1.3; padding-top: 15px;">
                    		<#if total.countryVIPPayMap??>
							    <#list total.countryVIPPayMap?keys as key>
						        	<div style="font-size:20px;"><span style="font-size:12px">
	                          	 	${key!}&nbsp;
	                          	 	</span> ${total.countryVIPPayMap[key]!}</div>
							    </#list>
							</#if>

                        </div>
                        
                        <div class="desc">
                           	 <@spring.message code="stats.Vip.payAmount"></@spring.message>
                        </div>
                    </div>
                    <#--
                    <a  href="${ctx}/sys/rlStats/group?type=vipPay&day=total&groupType=client_type" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>
            </#if>
            
            <#if setting.isOpenBalance ?? && setting.isOpenBalance>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10" >
                <div class="dashboard-stat red-intense" style="height:124px;">
                
                    <div class="visual">
                        <i class="fa fa-mail-forward"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                         	<#if (total.balance)??&&(total.balance >= 1000)>
	                        ${(total.balance/1000)?string('#.#')}k
	                        <#else>
	                        ${total.balance!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.balanceAmount"></@spring.message>
                        </div>
                    </div>
                    <#-- 
                    <a href="javascript:void(0);" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>
            </#if>
            
            
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat purple" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        <#if (total.platform_income)??&&(total.platform_income >= 1000)>
                        ${(total.platform_income/1000)?string('#.#')}k
                        <#else>
                        ${total.platform_income!0}
                        </#if>
                         <span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                        	<@spring.message code="stats.platformIncome"></@spring.message>
                        </div>
                    </div>
                     <a href="${ctx}/sys/stats/platformIncomeStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>
            
        </div>

    </div>
</div>


<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-green bold"><@spring.message code="stats.today"></@spring.message></span>
        </div>
    </div>

    <div class="portlet-body form">
        <!-- BEGIN FORM-->

        <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat blue-madison" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        	<#if (today.tripTimes)??&&(today.tripTimes >= 1000)>
	                        ${(today.tripTimes/1000)?string('#.#')}k
	                        <#else>
	                        ${today.tripTimes!0}
	                        </#if>
                         	  <span style="font-size:16px"><@spring.message code="stats.times"></@spring.message></span>
                        </div>
                        <div class="desc">
                        	<@spring.message code="stats.usageCount"></@spring.message>
                        </div>
                    </div>
                    <#-- 
                    <a href="${ctx}/sys/rlStats/group?type=tripNum&day=today&groupType=client_type" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>

            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat yellow" style="height:124px;">
                    <div class="visual">
                        <i class="fa fa-child "></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        	<#if (today.registNums)??&&(today.registNums >= 1000)>
	                        ${(today.registNums/1000)?string('#.#')}k
	                        <#else>
	                        ${today.registNums!0}
	                        </#if>
                         	<span style="font-size:16px"><@spring.message code="stats.people"></@spring.message></span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.registeredNumber"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/userStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat green-haze" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-jpy"></i>
                    </div>
                    
                    <div class="details">
                        <div class="number">
                         	<#if (today.deposit)??&&(today.deposit >= 1000)>
	                        ${(today.deposit/1000)?string('#.#')}k
	                        <#else>
	                        ${today.deposit!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.deposit"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/depositStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>

            <#if setting.isOpenMemberCard>
             <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat grey-cascade" style="height:124px;">
                
                    <div class="visual">
                        <i class="fa fa-jpy"></i>
                    </div>
                    <div class="details">
                    	<div class="number" style=" line-height:1.3; padding-top: 15px;">
                    		<#if today.countryVIPPayMap??>
							    <#list today.countryVIPPayMap?keys as key>
						        	<div style="font-size:20px;"><span style="font-size:12px">
	                          	 	${key!}&nbsp;
	                          	 	</span> ${today.countryVIPPayMap[key]!}</div>
							    </#list>
							</#if>
                        </div>
                        
                        <div class="desc">
                           	 <@spring.message code="stats.Vip.payAmount"></@spring.message>
                        </div>
                    </div>

                </div>
            </div>
            </#if>
            
            <#if setting.isOpenBalance ?? && setting.isOpenBalance>
            
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat red-intense" style="height:124px;">
                
                    <div class="visual">
                        <i class="fa fa-mail-forward"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                         	<#if (today.balance)??&&(today.balance >= 1000)>
	                        ${(today.balance/1000)?string('#.#')}k
	                        <#else>
	                        ${today.balance!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.balanceAmount"></@spring.message>
                        </div>
                    </div>
                    <#--
                    <a href="javascript:void(0);" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>
            </#if>
            
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat purple" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        <#if (today.platform_income)??&&(today.platform_income >= 1000)>
                        ${(today.platform_income/1000)?string('#.#')}k
                        <#else>
                        ${today.platform_income!0}
                        </#if>
                         <span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                        	<@spring.message code="stats.platformIncome"></@spring.message>
                        </div>
                    </div>
                     
                     <a href="${ctx}/sys/stats/platformIncomeStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                     </a>
                     
                </div>
            </div>
            
        </div>

    </div>
</div>
<div class="portlet light ">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject font-green bold"><@spring.message code="stats.yesterday"></@spring.message></span>
        </div>
    </div>

    <div class="portlet-body form">
        <!-- BEGIN FORM-->

        <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat blue-madison" style="height:124px;">
                    <div class="visual">
                        <i class="fa fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        	<#if (yes.tripTimes)??&&(yes.tripTimes >= 1000)>
	                        ${(yes.tripTimes/1000)?string('#.#')}k
	                        <#else>
	                        ${yes.tripTimes!0}
	                        </#if>
                         	 <span style="font-size:16px"><@spring.message code="stats.times"></@spring.message></span>
                        </div>
                        <div class="desc">
                         	<@spring.message code="stats.usageCount"></@spring.message>
                        </div>
                    </div>
                    <#--
                    <a href="${ctx}/sys/rlStats/group?type=tripNum&day=yes&groupType=client_type" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>

			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat yellow" style="height:124px;">
                    <div class="visual">
                        <i class="fa fa-child "></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        	<#if (yes.registNums)??&&(yes.registNums >= 1000)>
	                        ${(yes.registNums/1000)?string('#.#')}k
	                        <#else>
	                        ${yes.registNums!0}
	                        </#if>
                         	<span style="font-size:16px"><@spring.message code="stats.people"></@spring.message></span>
                        </div>
                        <div class="desc">
                            	<@spring.message code="stats.registeredNumber"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/userStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat green-haze" style="height:124px;">
                    <div class="visual">
                        <i class="fa fa-jpy"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                         	<#if (yes.deposit)??&&(yes.deposit >= 1000)>
	                        ${(yes.deposit/1000)?string('#.#')}k
	                        <#else>
	                        ${yes.deposit!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.deposit"></@spring.message>
                        </div>
                    </div>
                    <a href="${ctx}/sys/stats/depositStats" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                </div>
            </div>

             <#if setting.isOpenMemberCard>
             <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat grey-cascade" style="height:124px;">
                
                    <div class="visual">
                        <i class="fa fa-jpy"></i>
                    </div>
                    <div class="details">
                    	<div class="number" style=" line-height:1.3; padding-top: 15px;">
                    		<#if yes.countryVIPPayMap??>
							    <#list yes.countryVIPPayMap?keys as key>
						        	<div style="font-size:20px;"><span style="font-size:12px">
	                          	 	${key!}&nbsp;
	                          	 	</span> ${yes.countryVIPPayMap[key]!}</div>
							    </#list>
							</#if>
                        </div>
                        
                        <div class="desc">
                           	<@spring.message code="stats.Vip.payAmount"></@spring.message>
                        </div>
                    </div>

                </div>
            </div>
             </#if>
             
             <#if setting.isOpenBalance ?? && setting.isOpenBalance>
             <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
                <div class="dashboard-stat red-intense" style="height:124px;">
                    <div class="visual">
                        <i class="fa fa-mail-forward"></i>
                    </div>
                    <div class="details">	
                        <div class="number">
                         	<#if (yes.balance)??&&(yes.balance >= 1000)>
	                        ${(yes.balance/1000)?string('#.#')}k
	                        <#else>
	                        ${yes.balance!0}
	                        </#if>
                         	<span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                             <@spring.message code="stats.balanceAmount"></@spring.message>
                        </div>
                    </div>
                    
                    <#-- 
                    <a href="javascript:void(0);" class="more">
                       	 <@spring.message code="stats.details"></@spring.message> <i class="m-icon-swapright m-icon-white"></i>
                    </a>
                    -->
                </div>
            </div>
            </#if>
            
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 margin-bottom-10">
            	<div class="dashboard-stat purple" style="height:124px;">
                    <div class="visual">
                        <i class="fa  fa-location-arrow"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                        <#if (yes.platform_income)??&&(yes.platform_income >= 1000)>
                        ${(yes.platform_income/1000)?string('#.#')}k
                        <#else>
                        ${yes.platform_income!0}
                        </#if>
                         <span style="font-size:16px">${defaultCurrency!}</span>
                        </div>
                        <div class="desc">
                        	<@spring.message code="stats.platformIncome"></@spring.message>
                        </div>
                    </div>
                     <a href="${ctx}/sys/stats/platformIncomeStats" class="more">
                       	 details <i class="m-icon-swapright m-icon-white"></i>
                     </a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

