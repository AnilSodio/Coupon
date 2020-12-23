<#include "/include/taglib.ftl" >
<!DOCTYPE html>
<html>
  <head>
  </head>
  <body>
    <div id="map" style="height:600px"></div>
    <script>
      var map ;
      
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 15
        });
        
        var infoWindow = new google.maps.InfoWindow({map: map});
        
        if (navigator.geolocation) {
          console.log("==========w3c==========");
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('我的位置.');
            map.setCenter(pos);
            map.setZoom(15);
            mapQuery();
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
      
      
      var image = 'http://www.160.com/uploadfiles/20161229/1482998637131139.png';
      
      function mapQuery(){
      	
      	top.$.ajax({
			url:"${ctx}/sys/googleMap/mapQuery",
			dataType:"json",
			type:"POST",
			success:function(data){
				
				var beachMarker ;
				for(var i=0; i<data.length; i++){  
				
					console.log(data[i].lat + " ," + data[i].lng);
					
			    	beachMarker = new google.maps.Marker({
					    position: {lat: data[i].lat, lng: data[i].lng},
					    map: map,
					    icon: image
					});
				}
			}	
		});
      	
      }
      
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=${googleJsKye!}&callback=initMap"></script>
    
  </body>
</html>