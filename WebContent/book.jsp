<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<%@ page import="control.*"%>
<%@ page import="function.*"%>
<%
	String isbn = (String) request.getAttribute("isbn");
	String[] bookInfo = (String[]) request.getAttribute("bookInfo");
	String[] libStatus = (String[]) request.getAttribute("libStatus");
	String[] authors = (String[]) request.getAttribute("authors");
%>

<title><%=bookInfo[0]%></title>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script type="text/javascript">
	var points = [ {}, {} ];
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

		// get destination position
		var localAddress = "31 Trang Thi, Hoan Kiem, Ha Noi".replace(" ", "+");
		var xmlhttpAddr = new XMLHttpRequest();
		var url = "https://maps.googleapis.com/maps/api/geocode/json?address="
				+ localAddress;
		xmlhttpAddr.open("GET", url, false);
		xmlhttpAddr.send();
		if (xmlhttpAddr.readyState == 4 && xmlhttpAddr.status == 200) {
			var result = xmlhttpAddr.responseText;
			var jsResult = eval("(" + result + ")");
			points[1].lat = jsResult.results[0].geometry.location.lat;
			points[1].long = jsResult.results[0].geometry.location.lng;

		}
		var mapOptions = {
			center : new google.maps.LatLng(points[0].lat, points[0].long),
			zoom : 10,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("mapArea"),
				mapOptions);
		var latlngbounds = new google.maps.LatLngBounds();
		for (var i = 0; i < points.length; i++) {
			var marker = new google.maps.Marker(
					{
						position : new google.maps.LatLng(points[i].lat,
								points[i].long),
						map : map
					});
			latlngbounds.extend(marker.position);
		}
		map.fitBounds(latlngbounds);
		drawPath();
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
</script>
<style type="text/css">
body {
	align: center;
}

.h {
	color: Black;
	font-family: Tahoma;
	font-size: 8pt;
}

#table1 {
	margin: 0 auto;
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
}

#table2 {
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: white;
	padding: 3px;
}

#table3 {
	margin: 0 auto;
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: black;
	padding: 3px;
	width: 65%;
}

#mapArea {
	margin: 0 auto;
	width: 65%;
}

td {
	border-width: 1px;
	border-style: solid;
	border-color: white;
	padding: 3px;
}

.text1 {
	margin: 0 auto;
	width: 65%;
}

.rh {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 8pt;
	text-align: Left;
}

.rt {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 10.5pt;
	text-align: Left;
}

.item {
	text-align: right;
	font-weight: bold
}

.trt {
	background: #34495E;
	color: White;
	font-weight: bold;
}

.inner-button {
	margin: auto;
	display: block;
}
</style>
</head>
<body>
	<h2 class="text1"><%=bookInfo[0]%></h2>
	<br>

	<table id="table1">
		<tr class="rt">
			<td><img src="<%=bookInfo[8]%>" height="231" width="200"></img>
			</td>
			<td>
				<table id="table2">
					<tr class="rt">
						<td class="item">Description:</td>
						<td class="itempro"><%=bookInfo[7]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Authors:</td>
						<td class="itempro"><%=authors[0] + " " + authors[1]%> <%
 	if (authors.length > 2) {
 		for (int i = 2; i < authors.length; i += 2) {
 %> , <%=authors[i] + " " + authors[i + 1]%> <%
 	}
 	}
 %></td>
					</tr>
					<tr class="rt">
						<td class="item">Publisher:</td>
						<td class="itempro"><%=bookInfo[2]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Year:</td>
						<td class="itempro"><%=bookInfo[3]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Price:</td>
						<td class="itempro"><%=bookInfo[4]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Pages:</td>
						<td class="itempro"><%=bookInfo[5]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Language:</td>
						<td class="itempro"><%=bookInfo[6]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Category:</td>
						<td class="itempro"><%=bookInfo[1]%></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<h3 class="text1">Available in:</h3>
	<br>
	<form method='get' action='Order'>
		<table id="table3">
			<tr class="trt">
				<td>Library</td>
				<td>Address</td>
				<td>Phone</td>
				<td>Status</td>
				<td></td>
			</tr>
			<%
				for (int i = 0; i < libStatus.length; i += 6) {
			%>
			<tr class="trc">
				<td><%=libStatus[i + 1]%></td>
				<td><%=libStatus[i + 2]%></td>
				<td><%=libStatus[i + 3]%></td>
				<td><%=libStatus[i + 5]%></td>
				<td><input class="inner-button" type='checkbox' name='libID'
					value='<%=libStatus[i]%>' /></td>
			</tr>
			<%
				}
			%>
			<tr class="trc">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><p></p> <input class="inner-button" type='submit'
					value='BORROW' /></td>
			</tr>
		</table>
		<input type="hidden" name="isbn" value="<%= isbn%>">
	</form>
	<br />
	<br />
	<br />


	<div id="mapArea" style="height: 500px;"></div>



</body>
</html>