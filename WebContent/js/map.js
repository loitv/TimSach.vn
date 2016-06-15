var points = [ {}, {}, {}, {} ];
	var map;
	window.onload = function() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(onSuccess, onError, {
				maximumAge : 60 * 1000,
				timeout : 5 * 60 * 1000,
				enableHighAccuracy : true
			});
		} else
			document.getElementById("mapArea").innerHTML = "Your browser does not support HTML5 Geolocation!!!";
	}

	function onSuccess(position) {
		// get my position - source position
		
		points[0].lat = position.coords.latitude;
		points[0].long = position.coords.longitude;

		// get number of available library
		//var numberOfLib = document.getElementById("addrNumber").value;
		
		// get library1 position
		var lib1Address = "1 Dai Co Viet, Hai Ba Trung, Ha Noi".replace(" ", "+");
		var xmlhttpAddr = new XMLHttpRequest();
		var url = "https://maps.googleapis.com/maps/api/geocode/json?address="
				+ lib1Address;
		xmlhttpAddr.open("GET", url, false);
		xmlhttpAddr.send();
		if (xmlhttpAddr.readyState == 4 && xmlhttpAddr.status == 200) {
			var result = xmlhttpAddr.responseText;
			var jsResult = eval("(" + result + ")");
			points[1].lat = jsResult.results[0].geometry.location.lat;
			points[1].long = jsResult.results[0].geometry.location.lng;

		}
		
		// get library 2 position
		var lib2Address = "31 Trang Thi, Hoan Kiem, Ha Noi".replace(" ", "+");
		var xmlhttpAddr = new XMLHttpRequest();
		var url2 = "https://maps.googleapis.com/maps/api/geocode/json?address="
			+ lib2Address;
		xmlhttpAddr.open("GET", url2, false);
		xmlhttpAddr.send();
		if (xmlhttpAddr.readyState == 4 && xmlhttpAddr.status == 200) {
			var result = xmlhttpAddr.responseText;
			var jsResult = eval("(" + result + ")");
			points[2].lat = jsResult.results[0].geometry.location.lat;
			points[2].long = jsResult.results[0].geometry.location.lng;

		}
		
		// get library 3 position
		var lib3Address = "47 Ba Trieu, Hoan Kiem, Ha Noi".replace(" ", "+");
		var xmlhttpAddr = new XMLHttpRequest();
		var url3 = "https://maps.googleapis.com/maps/api/geocode/json?address="
			+ lib3Address;
		xmlhttpAddr.open("GET", url3, false);
		xmlhttpAddr.send();
		if (xmlhttpAddr.readyState == 4 && xmlhttpAddr.status == 200) {
			var result = xmlhttpAddr.responseText;
			var jsResult = eval("(" + result + ")");
			points[3].lat = jsResult.results[0].geometry.location.lat;
			points[3].long = jsResult.results[0].geometry.location.lng;

		}
		
		var mapOptions = {
			center : new google.maps.LatLng(points[0].lat, points[0].long),
			zoom : 10,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("mapArea"),
				mapOptions);
		
		// Create marker
		var latlngbounds = new google.maps.LatLngBounds();
		var marker1 = new google.maps.Marker(
				{
					position : new google.maps.LatLng(points[1].lat,
							points[1].long),
					map : map,
					title: "Ta Quang Buu Library",
					icon: "images/icon/maker.png"
				});
		var info1 = new google.maps.InfoWindow({
			content: "<b>Thư viện Tạ Quang Bửu</b><br><br><table><tr><td><b>SĐT: </b></td><td>0221213123</td></tr><tr><td><b>Email: </b></td><td>taquangbuu@lib.hust.edu.vn</td></tr><tr><td><b>Địa chỉ: </b></td><td>1 Đại Cồ Việt, Hai Bà Trưng, Hà Nội</td></tr></table>"
		});
		google.maps.event.addListener(marker1, "click", function(){
			info1.open(map, marker1);
		})
		latlngbounds.extend(marker1.position);
		
		var marker2 = new google.maps.Marker(
				{
					position : new google.maps.LatLng(points[2].lat,
							points[2].long),
					map : map,
					title: "Ha Noi Library",
					icon: "images/icon/maker.png"
				});
		var info2 = new google.maps.InfoWindow({
			content: "<b>Thư viện Quốc gia Hà Nội</b><br><br><table><tr><td><b>SĐT: </b></td><td>0221213123</td></tr><tr><td><b>Email: </b></td><td>thuvienquocgia@lib.vn</td></tr><tr><td><b>Địa chỉ: </b></td><td>31 Tràng Thi, Hoàn Kiếm Hà Nội</td></tr></table>"
		});
		google.maps.event.addListener(marker2, "click", function(){
			info2.open(map, marker2);
		})
		latlngbounds.extend(marker2.position);
		
		var marker3 = new google.maps.Marker(
				{
					position : new google.maps.LatLng(points[3].lat,
							points[3].long),
					map : map,
					title: "National Library",
					icon: "images/icon/maker.png"
				});
		var info3 = new google.maps.InfoWindow({
			content: "<b>Thư viện Hà Nội</b><br><br><table><tr><td><b>SĐT: </b></td><td>0221213123</td></tr><tr><td><b>Email: </b></td><td>thuvienhanoi@lib.vn</td></tr><tr><td><b>Địa chỉ: </b></td><td>47 Bà Triệu, Hoàn Kiếm Hà Nội</td></tr></table>"
		});
		google.maps.event.addListener(marker3, "click", function(){
			info3.open(map, marker3);
		})
		latlngbounds.extend(marker3.position);
		
		map.fitBounds(latlngbounds);
		
		var numberOfLib = document.getElementById("addrNumber").value;
		if (numberOfLib == 1) {
			var libID = document.getElementById("libAddr1").value;
			if (libID === "TV001") {
				drawPath();
			} else if (libID === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
		} else if (numberOfLib == 2) {
			var libID = document.getElementById("libAddr1").value;
			if (libID === "TV001") {
				drawPath();
			} else if (libID === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
			var libID2 = document.getElementById("libAddr2").value;
			if (libID2 === "TV001") {
				drawPath();
			} else if (libID2 === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
		} else if (numberOfLib == 3) {
			var libID = document.getElementById("libAddr1").value;
			if (libID === "TV001") {
				drawPath();
			} else if (libID === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
			var libID2 = document.getElementById("libAddr2").value;
			if (libID2 === "TV001") {
				drawPath();
			} else if (libID2 === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
			var libID3 = document.getElementById("libAddr3").value;
			if (libID3 === "TV001") {
				drawPath();
			} else if (libID3 === "TV002") {
				drawPath2();
			} else {
				drawPath3();
			}
		}
	}

	function drawPath() {
		var directionsService = new google.maps.DirectionsService();
		var poly = new google.maps.Polyline({
			strokeColor : "#FF0000",
			strokeWeight : 4
		});
		var request = {
			origin : new google.maps.LatLng(points[0].lat, points[0].long),
			destination : new google.maps.LatLng(points[1].lat, points[1].long),
			travelMode : google.maps.DirectionsTravelMode.DRIVING
		};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				new google.maps.DirectionsRenderer({
					map : map,
					polylineOptions : poly,
					directions : response
				});
			}
		});
	}
	
	
	function drawPath2() {
		var directionsService = new google.maps.DirectionsService();
		var poly = new google.maps.Polyline({
			strokeColor : "#00cc00",
			strokeWeight : 4
		});
		var request = {
			origin : new google.maps.LatLng(points[0].lat, points[0].long),
			destination : new google.maps.LatLng(points[2].lat, points[2].long),
			travelMode : google.maps.DirectionsTravelMode.DRIVING
		};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				new google.maps.DirectionsRenderer({
					map : map,
					polylineOptions : poly,
					directions : response
				});
			}
		});
	}
	
	function drawPath3() {
		var directionsService = new google.maps.DirectionsService();
		var poly = new google.maps.Polyline({
			strokeColor : "#0000ff",
			strokeWeight : 4
		});
		var request = {
			origin : new google.maps.LatLng(points[0].lat, points[0].long),
			destination : new google.maps.LatLng(points[3].lat, points[3].long),
			travelMode : google.maps.DirectionsTravelMode.DRIVING
		};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				new google.maps.DirectionsRenderer({
					map : map,
					polylineOptions : poly,
					directions : response
				});
			}
		});
	}
	
	
	function onError(error) {
		switch (error.code) {
		case PERMISSION_DENIED:
			alert("User denied permission");
			break;
		case TIMEOUT:
			alert("Geolocation timed out");
			break;
		case POSITION_UNAVAILABLE:
			alert("Geolocation information is not available");
			break;
		default:
			alert("Unknown error");
			break;
		}
	}