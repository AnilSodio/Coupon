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
                <li>
				    <span>用户管理<i class="fa fa-angle-right"></i></span>
				</li>
                <li>
                    <span>日程详情</span>
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
			         <li><span class="ordertitle">车辆编号：</span><span>${trip.sysCode!}</span></li>
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
			        <li><span class="ordertitle">行程开始位置：</span><span>${trip.beginArea!}</span></li>
			        <li><span class="ordertitle">行程结束位置：</span><span>${trip.endArea!}</span></li>
			        <li><span class="ordertitle">行程距离：</span><span>${trip.distance !} m</span></li>
			      </ul>
			    </div>
			    
			    <div class="orderhh">支付相关</div>
			    <div class="orderul">
			      <ul>
			        <li><span class="ordertitle">行程费用：</span><span>${trip.price!}元</span></li>
			        <li><span class="ordertitle">支付时间：</span><span>${(trip.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</span></li>
			         <li><span class="ordertitle">支付方式：</span>
			         	<span>
			         		<#if trip.paymentMark ??>
			         			<#if coupon??>
			         				<#if trip.price lt coupon.amount>
			         					使用 ${coupon.name! ''}支付 ${coupon.amount!} 元
			         				<#else>
			         					使用 ${coupon.name! ''}支付 ${coupon.amount!}元,
			         					<#if trip.paymentMark != "alipay_app">
											WeChat支付${trip.price - coupon.amount} 元
										<#else>
											Alipay支付${trip.price - coupon.amount} 元
										</#if>
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
			         	</span>
			         </li>
			      </ul>
			    </div>
			    
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
			    	<div class="orderhh">行程轨迹</div>
			    	<div class="orderul">
				    	<ul>
				    		<li><span class="ordertitle">轨迹</span>
				    			<span>
						    		<div id="allmap" style="height:400px;width:800px;"></div>
						    	</span>
				    		</li>
				    	</u>
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
  
    var list = ${p_list!};
    var listLast = list.length-1;  
    // 百度地图API功能  
    var map = new BMap.Map("allmap");    // 创建Map实例  
    
    var maxl = list[0].lon,minl=list[0].lon,maxb=list[0].lat,minb=list[0].lat;  
	$.each(list,  
	    function(i, res) {  
	    	console.log(res);
	        if(res.lon > maxl) {maxl =res.lon};  
	        if(res.lon < minl) {minl =res.lon};  
	        if(res.lat > maxb) {maxb =res.lat};  
	        if(res.lat < minb) {minb =res.lat};  
	}); 
	    
	var cenL = (parseFloat(maxl)+parseFloat(minl))/2;  
	var cenB = (parseFloat(maxb)+parseFloat(minb))/2;
    
    var zoom = getZoom_2(maxl, minl, maxb, minb);
    
     if (zoom == 1) {   
        cenB = 0;  
        cenL = 0;  
     }
    
    console.log(zoom);
    
    map.centerAndZoom(new BMap.Point(cenL,cenB), zoom+1);  // 初始化地图,设置中心点坐标和地图级别  
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件  
  
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放  
  
    setTimeout(drawIcon,500);  
    var carMk;  
    var myBeginIcon = new BMap.Icon("/images/starting_point.svg", new BMap.Size(30,30), {imageOffset: new BMap.Size(0, 0)});//人  
    var myEndIcon = new BMap.Icon("/images/end_point.svg", new BMap.Size(30,30), {imageOffset: new BMap.Size(0, 0)});//人  
  
    function drawGreenLine(i){  
        var polyline = new BMap.Polyline([  
            new BMap.Point(list[i].lon,list[i].lat),//起始点的经纬度  
            new BMap.Point(list[i+1].lon,list[i+1].lat)//终点的经纬度  
        ], {strokeColor:"green",//设置颜色  
            strokeWeight:4, //宽度  
            strokeOpacity:1});//透明度  
        map.addOverlay(polyline);  
    }  
  
    function drawIcon(){  
        if(carMk){  
            map.removeOverlay(carMk);  
        }  
        carMk2 = new BMap.Marker(  
                new BMap.Point(list[0].lon,list[0].lat),//起始点的经纬度  
                {icon:myBeginIcon});  
        map.addOverlay(carMk2);  
  
        carMk = new BMap.Marker(  
                new BMap.Point(list[listLast].lon,list[listLast].lat),//终点的经纬度  
                {icon:myEndIcon});  
        map.addOverlay(carMk);  
  
        for(var i=0;i<list.length-1;i++){  
            drawGreenLine(i);  
        }  
    } 
    
    function getZoom(maxJ, minJ, maxW, minW) {  
	    if (maxJ == minJ && maxW == minW) return 13;  
	    var diff = maxJ - minJ;  
	    if (diff < (maxW - minW) * 2.1) diff = (maxW - minW) * 2.1;  
	    diff = parseInt(10000 * diff) / 10000;  
	  
	    var zoomArr = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);  
	    var diffArr = new Array(180, 90, 45, 22, 11, 5.5, 2.75, 1.37, 0.68, 0.34, 0.17, 0.08, 0.04);  
	  
	    for (var i = 0; i < diffArr.length; i++) {  
	        if ((diff - diffArr[i]) >= 0) {  
	            return zoomArr[i];  
	        }  
	    }  
	    return 14;  
	} 
	
	function getZoom_2 (maxLng, minLng, maxLat, minLat) {  
	    var zoom = ["50","100","200","500","1000","2000","5000","10000","20000","25000","50000","100000","200000","500000","1000000","2000000"]//级别18到3。  
	    var pointA = new BMap.Point(maxLng,maxLat);  // 创建点坐标A  
	    var pointB = new BMap.Point(minLng,minLat);  // 创建点坐标B  
	    var distance = map.getDistance(pointA,pointB).toFixed(1);  //获取两点距离,保留小数点后两位  
	    for (var i = 0,zoomLen = zoom.length; i < zoomLen; i++) {  
	        if(zoom[i] - distance > 0){  
	            return 18-i+2;//之所以会多3，是因为地图范围常常是比例尺距离的10倍以上。所以级别会添加3。  
	        }  
	    };  
	}
</script>  
</body>
</html>