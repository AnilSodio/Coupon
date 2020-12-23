<#include "/include/taglib.ftl" >
<#include "/include/dialog.ftl" >
<html>
<head>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${jsKey!}"></script>
	<style>
			.BMap_cpyCtrl, .anchorBL{display:none;}
	</style>
	<link href="/common/template.css"  rel="stylesheet" type="text/css"/>
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
                <#if trip.tripType ?? && trip.tripType == 'bicycle'>
                 <li>
				    <span>共享单车<i class="fa fa-angle-right"></i></span>
				</li>
                <#elseif trip.tripType ?? && trip.tripType == 'battery'>
                 <li>
				    <span>共享电池<i class="fa fa-angle-right"></i></span>
				</li>
                </#if>
					
                <#if mark ?? && mark == '1'>
					
				<#elseif mark ?? && mark == '2'>
					<li>
					    <span>行程管理<i class="fa fa-angle-right"></i></span>
					</li>
				<#elseif mark ?? && mark == 'car'>
					<li>
					    <span>车辆管理<i class="fa fa-angle-right"></i></span>
					</li>
                </#if>
               	<li>
				    <span>行程详情</span>
				</li>
            </ul>
        </div>
	        
      	<div class="portlet light portlet-fit portlet-datatable ">
	       <ul class="nav nav-tabs mb-25">
	       		<#if mark ?? && mark == "car">
	       			<li>
<@spring.message code="form.list"></@spring.message></a>
		            </li>
		            <li>
	                	<a  href="${ctx}/sys/customer/trips?mark=car&bicycleId=${trip.bicycleId!}">车辆行程</a>&nbsp; 
		            </li>
		        <#elseif mark ?? && mark == "1">
		        	<li>
 <a data-toggle="tab" href="javascript:;"><@spring.message code="form.list"></@spring.message></a>
		            </li>
		        	<li>
	                	<a  href="${ctx}/sys/customer/trips?mark=1&uid=${user.uid!}">用户行程</a>
		            </li>
		        <#elseif mark ?? && mark == "2">
		        	<li>
<@spring.message code="form.list"></@spring.message></a>
		            </li>
	       		</#if>
	           
	            
	            <li class="active">
					<a data-toggle="tab" href="javascript:void(0)">行程详情</a>
	            </li>
	        </ul>
        
			<#if trip??>
			 <div class="orderdetail">
			    <div class="orderul">
			      <ul>
			        <li><span class="ordertitle">行程编号：</span><span>${trip.id!}</span></li>
			         <li><span class="ordertitle">用户昵称：</span><span>${user.nickname !}</span></li>
			        <li><span class="ordertitle">用户手机号：</span><span>${user.mobile !}</span></li>
			         <li><span class="ordertitle">系统编号：</span><span>${trip.sysCode!}</span></li>
			      </ul>
			    </div>
			   <div class="orderhh">时间相关</div>
			    <div class="orderul">
			      <ul>                        
			        <li><span class="ordertitle">行程开始时间：</span><span>${(trip.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}</span></li>
			        <li><span class="ordertitle">行程结束时间：</span><span>${(trip.finishTime?string('yyyy-MM-dd HH:mm:ss'))!''}</span></li>
			        <li><span class="ordertitle">
						        				<#if exception ?? && exception == '1'>
													<font color="red">(时长异常)</font> 行程时长：
												<#elseif exception ?? && exception == '2'>
													<font color="red">(时长异常)</font> 行程时长：
												<#else>
													行程时长：
												</#if>
												<span>${trip.rideTime!} min</span></li>
			      </ul>
			    </div>
			     <div class="orderhh">位置相关</div>
			    <div class="orderul">
			      <ul>
			        <li><span class="ordertitle">行程开始位置：</span><span>${trip.beginLocationDetails!}</span></li>
			        <li><span class="ordertitle">行程结束位置：</span><span>${trip.endLocationDetails!}</span></li>
			        <li><span class="ordertitle">行程距离：</span><span>${trip.distance !} m</span></li>
			      </ul>
			    </div>
			    
			    <#if isPayTrip?? && isPayTrip>
			    <div class="orderhh">支付相关</div>
			    <div class="orderul">
			      <ul>
			        <li><span class="ordertitle">行程费用：</span><span>${trip.price!}元</span></li>
			        <li><span class="ordertitle">支付时间：</span><span>${(trip.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</span></li>
			         <li><span class="ordertitle">支付方式：</span>
			         	<span>
							
								<#if trip.payType?? && trip.payType == 'free'>
									免费
								<#elseif trip.payType ?? && trip.payType == 'vip'>
									会员卡
								<#elseif trip.payType ?? && trip.payType == 'balance'>
									余额
								<#elseif trip.payType ?? && trip.payType == 'cash'>
									<#if trip.paymentMark ??>
										<#if trip.ticketAmount ?? && trip.price ??>
											<#if trip.price lte trip.ticketAmount>
												 ${trip.ticketName! ''}支付 ${trip.ticketAmount! 0} 元</br>
											<#else>
												<#if info.paymentMark != "alipay_app">
													WeChat支付${trip.price - trip.ticketAmount} 元
												<#else>
													Alipay支付${trip.price - trip.ticketAmount} 元
												</#if>
												
												${trip.ticketName! ''}支付 ${trip.ticketAmount! 0}元</br>
											</#if>
										<#else>
											<#if trip.paymentMark != "alipay_app">
												WeChat支付${trip.price!} 元
											<#else>
												Alipay支付${trip.price!} 元
											</#if>
										</#if>
									<#else>
										${trip.paymentMark!}
									</#if>
								<#else>
									${trip.payType!}
								</#if>
							
			         	</span>
			         </li>
			      </ul>
			    </div>
			    </#if>
			    <#if trip.tripRemark ??>
			    	<div class="orderhh">审核信息</div>
				    <div class="orderul">
				      <ul>
				        <li><span class="ordertitle">审核人：</span><span>${trip.checkName!}</span></li>
			        	<li><span class="ordertitle">审核时间：</span><span>${(trip.checkTime?string('yyyy-MM-dd HH:mm:ss'))!''}</span></li>
				        <li><span class="ordertitle">备注：</span><span>${trip.tripRemark!}</span></li>
				      </ul>
				    </div>
			    </#if>
			    
			    
			    <#if p_list??>
			    	<div class="orderhh">
			    		行程轨迹 
			    		  
			    	</div>
			    	
			    	<div class="orderul">
			    		<div style="padding: 20px; clear: both;">
				    		<div id="allmap" style="height:400px;width:800px;">
				    			<input id="follow" type="checkbox"><span style="font-size:12px;">画面跟随</span></input>  
				    		</div>
				    	</div>
				    	<div style="padding-left: 20px;">
				    		<input id="play"  class="btn btn-primary" type="button" value="播放" onclick="play();" disabled />  
				    		<input id="pause" class="btn btn-primary" type="button" value="暂停" onclick="pause();" disabled />  
	           				<input id="reset" class="btn btn-primary" type="button" value="重置" onclick="reset()" disabled />
				    	</div>
				    	
			    	</div>
			    </#if>
			    
			    <#if feed_list??>
			    
			    	<#if (feed_list?size > 0)>
				    	 
			    	 	<div class="orderhh">投诉情况</div>
				        <div class="portlet-body">
				            <div id="sample_4_wrapper" class="dataTables_wrapper">
							<table id="contentTable" class="table table-striped table-bordered table-hover table-checkable order-column dataTable">
							            <th>问题类型</th>
							            <th>问题名称</th>
							            <th>问题描述</th>
							            <th>状态</th>
							            <th>反馈时间</th>
							            <th>备注</th>
							            <th>操作</th>
									</tr>
									<#list feed_list as info>
										<tr>
								            <td>
								            	<#if info.feedType ?? && info.feedType == "1">
								            		Home
								            	<#elseif info.feedType ?? && info.feedType == "2">
								            		行程中
								            	<#elseif info.feedType ?? && info.feedType == "3">
								            		已完成
								            	<#else>
								            	
								            	</#if>
								            </td> 
								            <td>${info.typeName!''}</td>
								            <td>${info.feedDesc!''}</td>
								            
								            <td>
								            	<#if info.status ?? && info.status == 0>
								            		已处理
								            	<#elseif info.status ?? && info.status == 1>
								            		未处理
								            	<#elseif info.status ?? && info.status == 2>
								            		无需处理
								            	<#else>
								            	
								            	</#if>
								            </td> 
								            
											<td>${(info.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
											
											 <td>${info.remark !''}</td>
											 
											<@shiro.hasPermission name="sys:feed:modify">
												 <td>
												 	<#if info.status == 1>
												 		<a  href="${ctx}/sys/feed/modify?id=${info.id!}">处理</a>&nbsp;
												 	<#else>
												 		<a  href="${ctx}/sys/feed/queryDetail?mark=${mark!}&id=${info.id!}">查看详情</a>&nbsp;
												 	</#if> 
									            </td>
						            		</@shiro.hasPermission>	
						            		
										</tr>
									</#list>
								</table>
							</div>
						</div>
				    <#else>
						<div class="note note-warning alert"><@spring.message code="noRecords"/></div>
					</#if>
			    <#else>
			    </#if>
			<#else>
		    	对不起，行程不存在！
		    </#if>
	 </div>
</div>
	 
<script type="text/javascript">  
  
  
  	$(document).ready(function() {
		init();
	});
		
    var points = [];  
    var aa_list = ${p_list!};
  
	for(var i=0;i<aa_list.length;i++){
		points[i] = new BMap.Point(aa_list[i].lon,aa_list[i].lat);
	}
   
    var myEndIcon = new BMap.Icon("/images/bike_blue.png", new BMap.Size(20,24));
    var startImg = new BMap.Icon("/images/starting_point.svg", new BMap.Size(30,30), {imageOffset: new BMap.Size(0, 0)});//人  
    var endImg = new BMap.Icon("/images/end_point.svg", new BMap.Size(30,30), {imageOffset: new BMap.Size(0, 0)});//人  
   
    var map;   //百度地图对象  
    var car;   //汽车图标  
    var label; //信息标签  
    var centerPoint;  
      
    var timer;     //定时器  
    var index = 0; //记录播放到第几个point  
      
    var followChk, playBtn, pauseBtn, resetBtn; //几个控制按钮
    
    var maxl = aa_list[0].lon,minl=aa_list[0].lon,maxb=aa_list[0].lat,minb=aa_list[0].lat;  
	$.each(aa_list,  
	    function(i, res) {  
	        if(res.lon > maxl) {maxl =res.lon};  
	        if(res.lon < minl) {minl =res.lon};  
	        if(res.lat > maxb) {maxb =res.lat};  
	        if(res.lat < minb) {minb =res.lat};  
	}); 
	    
	var cenL = (parseFloat(maxl)+parseFloat(minl))/2;  
	var cenB = (parseFloat(maxb)+parseFloat(minb))/2;
	
      
    function init() {  
    	
   		followChk = document.getElementById("follow");  
        playBtn = document.getElementById("play");  
        pauseBtn = document.getElementById("pause");  
        resetBtn = document.getElementById("reset");  
      
        //初始化地图,选取第一个点为起始点  
  		map = new BMap.Map("allmap");    // 创建Map实例  
        map.centerAndZoom(points[0], 15);  
        console.log("======="+points[0]);
        map.enableScrollWheelZoom();  
        map.addControl(new BMap.NavigationControl());  
        map.addControl(new BMap.ScaleControl());  
        map.addControl(new BMap.OverviewMapControl({isOpen: true}));  
          
        //通过DrivingRoute获取一条路线的point  
        var driving = new BMap.DrivingRoute(map);  
        driving.search(new BMap.Point(aa_list[0].lon, aa_list[0].lat), 
		new BMap.Point(aa_list[aa_list.length-1].lon, aa_list[aa_list.length-1].lat));  
        driving.setSearchCompleteCallback(function() {  
            //得到路线上的所有point  
           // points = driving.getResults().getPlan(0).getRoute(0).getPath();  
            //画面移动到起点和终点的中间  
            centerPoint = new BMap.Point((points[0].lng + points[points.length - 1].lng) / 2, (points[0].lat + points[points.length - 1].lat) / 2);  
            map.panTo(centerPoint);  
            //连接所有点  
            map.addOverlay(new BMap.Polyline(points, {strokeColor: "green", strokeWeight: 3, strokeOpacity: 1}));  
              
            //显示小车子  
            // label = new BMap.Label("", {offset: new BMap.Size(-20, -20)});  
            car = new BMap.Marker(points[0],{icon:myEndIcon});  
            //car.setLabel(label);  
            map.addOverlay(car);  
              
            //点亮操作按钮  
            playBtn.disabled = false;  
            resetBtn.disabled = false;  
        });  
    }  
      
    function play() {  
    
        playBtn.disabled = true;  
        pauseBtn.disabled = false;  
          
        var point = points[index];  
        if(index > 0) {  
            map.addOverlay(new BMap.Polyline([points[index - 1], point], {strokeColor: "green", strokeWeight: 3, strokeOpacity: 1}));  
        }  
       // label.setContent(${trip.sysCode!});  
        car.setPosition(point);  
        index++;  
        if(followChk.checked) {  
            map.panTo(point);  
        }  
        if(index < points.length) {
        	if(index == 1) {
        		var startMark = new BMap.Marker(  
                new BMap.Point(aa_list[0].lon, aa_list[0].lat),//起始点的经纬度  
                {icon:startImg});  
       			map.addOverlay(startMark); 
        	}
            timer = window.setTimeout("play(" + index + ")", 50);  
        } else {  
            playBtn.disabled = true;  
            pauseBtn.disabled = true;  
            map.panTo(point);  
            
            var endMark = new BMap.Marker(  
                new BMap.Point(aa_list[aa_list.length-1].lon, aa_list[aa_list.length-1].lat),//起始点的经纬度  
                {icon:endImg});  
       		map.addOverlay(endMark); 
        
        }  
    }  
      
    function pause() {  
        playBtn.disabled = false;  
        pauseBtn.disabled = true;  
          
        if(timer) {  
            window.clearTimeout(timer);  
        }  
    }  
      
    function reset() {  
    
        followChk.checked = false;  
        playBtn.disabled = false;  
        pauseBtn.disabled = true;  
        
        map.clearOverlays();
        
         car = new BMap.Marker(points[0],{icon:myEndIcon});  
         map.addOverlay(car);  
        
        if(timer) {  
            window.clearTimeout(timer);  
        }  
        index = 0;  
        car.setPosition(points[0]);  
        map.panTo(centerPoint);  
    }  
</script>  
</body>
</html>