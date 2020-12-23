<!DOCTYPE html>
<#include "/include/taglib.ftl" >
<html>
<head>
	<style>
		.BMap_cpyCtrl, .anchorBL{display:none;}
	</style>
</head>
<body>
	<div class="page-container-custom">
        
        <div class="portlet light portlet-fit portlet-datatable ">
			
        	<div class="breadcrumb form-search">
					<label><@spring.message code="merchant.cabinet.address"></@spring.message>：</label>
					<div style="position: relative; display: inline-block;">
						<input name="lastLocationCity" id="pac-input" class="form-control input-large input-inline" type="text"  placeholder="Enter a location">
					</div>
					&nbsp;&nbsp
			</div>
			<div id="map" style="height:300px;"></div>
			<div id="google_location">
				<input type="hidden" id="lat-lon" name="lat-lon" value=""/>
			</div>
			
        </div>
    </div>
    
<script type="text/javascript">

	 
	var map ;
	
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

	          //autoInfoWindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
	          //autoInfoWindow.open(map, marker);
	          
	          console.log(place.geometry.location.lat());
	          console.log(place.geometry.location.lng());
	          
	          $("#lat-lon").val(place.geometry.location.lat()+":"+place.geometry.location.lng()+":" + $("#pac-input").val());
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
      
	
</script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=${googleJsKye!}&libraries=places&callback=initMap" async defer></script>


</body>
</html>

	