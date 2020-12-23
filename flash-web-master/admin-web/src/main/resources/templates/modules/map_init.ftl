<#include "/include/taglib.ftl" >
<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
  	<div class="breadcrumb form-search">
			<label><@spring.message code='nomo.address'></@spring.message>：</label>
			<div style="position: relative; display: inline-block;">
				<input name="lastLocationCity" id="pac-input" class="form-control input-large input-inline" type="text"  placeholder="Enter a location">
				<input type="hidden" id="location_lat" value=""/>
				<input type="hidden" id="location_lng" value=""/>
			</div>
			&nbsp;&nbsp;
			<label><@spring.message code='nomo.nomoBoxId'></@spring.message>：</label><input id="sysCode" name="sysCode" htmlEscape=false maxlength="50" class="form-control input-small input-inline"/>
			&nbsp;&nbsp;
			<a href="javascript:queryBike();" class="btn btn-primary"><@spring.message code='form.query'></@spring.message></a>
	</div>
			
    <div id="map" style="height:600px"></div>
    
    <script>
      var map ;
      
      var markers = [];
      
      function initMap() {
       	  map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 15
        });
        
        
       var infoWindow = new google.maps.InfoWindow({map: map});
        
        if (navigator.geolocation) {
        
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            map.setCenter(pos);
            map.setZoom(15);
            console.log(pos);
            // ==============================开始Search后数据开始=====================================
            
            platQuery(position.coords.latitude,position.coords.longitude,"");
		
            // ==============================开始Search后数据结束=====================================
            
            
            // ============================加载 地址搜索自动填充 开始 ==========================
            var input = (document.getElementById('pac-input'));
            var autocomplete = new google.maps.places.Autocomplete(input);
            autocomplete.bindTo('bounds', map);
            var autoInfoWindow = new google.maps.InfoWindow();
	        var marker = new google.maps.Marker({
	          map: map,
	          anchorPoint: new google.maps.Point(position.coords.latitude, position.coords.longitude)
	        });
	        
		   autocomplete.addListener('place_changed', function() {
	          autoInfoWindow.close();
	          marker.setVisible(false);
	          var place = autocomplete.getPlace();
	          if (!place.geometry) {
	            // User entered the name of a Place that was not suggested and
	            // pressed the Enter key, or the Place Details request failed.
	            window.alert("No details available for input: '" + place.name + "'");
	            return;
	          }

	          // If the place has a geometry, then present it on a map.
	          if (place.geometry.viewport) {
	            map.fitBounds(place.geometry.viewport);
	          } else {
	            map.setCenter(place.geometry.location);
	            map.setZoom(15);  // Why 17? Because it looks good.
	          }
	          marker.setIcon(/** @type {google.maps.Icon} */({
	            url: place.icon,
	            size: new google.maps.Size(71, 71),
	            origin: new google.maps.Point(0, 0),
	            anchor: new google.maps.Point(17, 34),
	            scaledSize: new google.maps.Size(35, 35)
	          }));
          
	          marker.setPosition(place.geometry.location);
	          marker.setVisible(true);

	          var address = '';
	          if (place.address_components) {
	            address = [
	              (place.address_components[0] && place.address_components[0].short_name || ''),
	              (place.address_components[1] && place.address_components[1].short_name || ''),
	              (place.address_components[2] && place.address_components[2].short_name || '')
	            ].join(' ');
	          }
	          
	          $("#location_lat").val(place.geometry.location.lat());
	          $("#location_lng").val(place.geometry.location.lng());
	          
	          console.log("初始位置：" + place.geometry.location.lat());
	          console.log("初始位置：" + place.geometry.location.lng());
	          
	          platQuery(place.geometry.location.lat(), place.geometry.location.lng(),"");
          
        	});

	        // Sets a listener on a radio button to change the filter type on Places
	        // Autocomplete.
	        function setupClickListener(id, types) {
	          var radioButton = document.getElementById(id);
	          radioButton.addEventListener('click', function() {
	            autocomplete.setTypes(types);
	         });
        }
            
       // ============================加载 地址搜索自动填充 开始 ==========================
       
       
       }, function() {
          	console.log("===============error=============");
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
        	console.log("==========Browser doesn't support Geolocation==========");
          	handleLocationError(false, infoWindow, map.getCenter());
        }
      }
      
      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
      }
      
      
      
      function platQuery(lat,lng,sysCode){
      	
      	console.log("query:"+lat);
      	console.log("query:"+lng);
      	
      	top.$.ajax({
      	
				url:"${ctx}/sys/googleMap/mapQuery",
				dataType:"json",
				data:{lng:lng, lat:lat, sysCode:sysCode},
				type:"POST",
				success:function(data){
					
					clearMarker();
					
					if(data.length >= 1){
						
						var center_pos = {
			              lat: data[0].lat,
			              lng: data[0].lng
			            };

            			map.setCenter(center_pos);
					}
				
					var ajaxInfoWindow = new google.maps.InfoWindow;
					
					Array.prototype.forEach.call(data,function(d){
						
						var point = new google.maps.LatLng(
			                  parseFloat(d.lat),
			                  parseFloat(d.lng));

						 var marker = new google.maps.Marker({
				          position: point,
				          icon:'/images/bike_blue.png',
				          map: map
				        });
				        
						marker.addListener('click', function() {
							var content = d.sysCode + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ "<a href='${ctx}/sys/cabinet/detail?id="+d.id+"'><@spring.message code="form.detail"></@spring.message></a>" + "<br/><br/>" + d.address;
			                ajaxInfoWindow.setContent(content);
			                ajaxInfoWindow.open(map, marker);
			              });
			           
			             markers.push(marker);
			         });
				}
			});
      }
      
      function clearMarker(){
      	 console.log("markers的数据大小:" + markers.length);
      	 for (var i = 0; i < markers.length; i++) {
          	markers[i].setMap(null);
         }
      	markers = [];
      		
      }
      
      function queryBike() {
      		
      		var location_lat = $("#location_lat").val();
	        var location_lng = $("#location_lng").val();
	        var sysCode = $("#sysCode").val(); 
	        if("" == location_lat && "" == location_lng && "" == sysCode ){
	        	location.reload();
	        }	
	        if("" != sysCode){
	        	console.log("==========sysCode:Search==========");
		        platQuery("","",sysCode);
	        }else {
	        	console.log("==========lat and lng Search==========");
	        	platQuery(location_lat,location_lng,"");
	        }
	        
      }
      
      
    </script>
    
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=${googleJsKye!}&libraries=places&callback=initMap" async defer></script>
    
  </body>
</html>