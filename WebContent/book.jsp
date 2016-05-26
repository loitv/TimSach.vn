<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page import="control.*"%>
	<%@ page import="function.*"%>
	<%
		String isbn = (String) request.getAttribute("isbn");
		String[] bookInfo = (String[]) request.getAttribute("bookInfo");
		String[] libStatus = (String[]) request.getAttribute("libStatus");
		String[] authors = (String[]) request.getAttribute("authors");
	%>

<title><%=bookInfo[0] %></title>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script type="text/javascript">
	window.onload = function() {
		var mapOptions = {
				center: new google.maps.LatLng()
		}
	}
</script>
<style type="text/css">
body {
align: center;
}
h2, h3 {
	margin-left: 188px;
}
.h {
color:Black;
font-family:Tahoma;
font-size:8pt;
}
#table1 {
width: 70%;
border-collapse:collapse;
border-width:1px;
border-style:solid;
border-color:Silver;
padding:3px;
}
#table2 {
border-collapse:collapse;
border-width:1px;
border-style:solid;
border-color:white;
padding:3px;
}
#table3 {
margin-left: 188px;
width: 60%;
border-collapse:collapse;
border-width:1px;
border-style:solid;
border-color:black;
padding:3px;
}
td {
border-width:1px;
border-style:solid;
border-color:white;
padding:3px;
}
.rh {
background-color:White;
vertical-align:Top;
color:Black;
font-family:Tahoma;
font-size:8pt;
text-align:Left;
}
.rt {
background-color:White;
vertical-align:Top;
color:Black;
font-family:Tahoma;
font-size:10.5pt;
text-align:Left;
}
.item {
text-align:right;
font-weight: bold

}
.trt {
background: #34495E;
color: White;
font-weight: bold;
}
.inner-button {
margin:auto;
  display:block;
}
</style>
</head>
<body>
	<h2><%=bookInfo[0] %></h2>
	<table id="table1" align="center">
		<tr class="rt">
			<td>
				<img src = "<%=bookInfo[8]%>" height="231" width="200"></img>
			</td>
			<td>
				<table id="table2">
					<tr class="rt">
						<td class = "item">Description: </td>
						<td class="itempro"><%=bookInfo[7]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Authors: </td>
						<td class="itempro"><%=authors[0] + " " + authors[1]%>
						<%if(authors.length > 2) {
						for (int i = 2; i < authors.length; i+=2) {
							%>
							, <%=authors[i] + " " + authors[i+1]%>
							<%
						}
							}%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Publisher: </td>
						<td class="itempro"><%=bookInfo[2]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Year: </td>
						<td class="itempro"><%=bookInfo[3]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Price: </td>
						<td class="itempro"><%=bookInfo[4]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Pages: </td>
						<td class="itempro"><%=bookInfo[5]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Language: </td>
						<td class="itempro"><%=bookInfo[6]%></td> 
					</tr>
					<tr class="rt">
						<td class = "item">Category: </td>
						<td class="itempro"><%=bookInfo[1]%></td> 
					</tr>
				</table>			
			</td>
		</tr>
	</table>
	
	<h3>Available in: </h3>
	
	<form method='get' action='Order'>
	<table id="table3">
		<tr class="trt">
			<td>Library</td>
			<td>Address</td>
			<td>Status</td>
			<td></td>
		</tr>
		<%
			for (int i = 0; i < libStatus.length; i+=4) {
		%>
		<tr class="trc">
			<td><%=libStatus[i+1]%></td>
			<td><%=libStatus[i + 2]%></td>
			<td><%=libStatus[i + 3]%></td>
			<td><input class="inner-button" type='checkbox' name='id' value='<%=libStatus[i]%>'/></td>
		</tr>
		<%
			}
		%>
		<tr class="trc">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr class="trc">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr class="trc">
			<td></td>
			<td></td>
			<td></td>
			<td><input class="inner-button" type='submit' value='BORROW'/></td>
		</tr>
	</table>
	</form>
	

</body>
</html>