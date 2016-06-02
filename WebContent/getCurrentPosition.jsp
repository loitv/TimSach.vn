<!DOCTYPE html>

<html>
<head>
	<%@ page import="control.*"%>
	<%@ page import="function.*"%>
	<%
		String libID = (String) request.getAttribute("libID");
	%>
    <title>Get the Location</title>
    <script type="text/javascript">
        function getLocation()
        {
            if (navigator.geolocation)
            {
                navigator.geolocation.getCurrentPosition(successfulPosition);
            } else
                document.getElementById("result").innerHTML = "Your browser does not support HTML5 Geolocation";
        }
        function successfulPosition(location)
        {
            var lat = location.coords.latitude;
            var long = location.coords.longitude;
            document.getElementById("result").innerHTML = "Latitude: " + lat +
                "<br />Longitude: " + long;
            alert("Altitude: " + location.coords.altitude);
            alert("Accuracy: " + location.coords.accuracy);
            alert("Altitude Accuracy: " + location.coords.altitudeAccuracy);
            alert("Direction: " + location.coords.heading);
            alert("Speed: " + location.coords.speed);
            alert("Time: " + location.timestamp);
        }
    </script>
</head>
<body>
	<h1><%=libID %></h1>
    <div id="result"></div>
    <button id="getPosition" onclick="getLocation();">Get Position</button>
</body>
</html>