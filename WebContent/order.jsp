<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order status</title>
<style type="text/css">
body {
	margin: auto;
}
a {
text-decoration: none;
}

.result {
	margin: auto;
	width: 65%;
}

td {
	border-width: 1px;
	border-style: solid;
	border-color: white;
	padding: 3px;
}

.trt {
	background: #34495E;
	color: White;
	font-weight: bold;
}

.id {
	text-align: center;
}

.inner-button {
	margin: auto;
	display: block;
}

#userInfo {
	float: left;
}
#bookInCart {
	margin: 0 auto;
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: black;
	padding: 3px;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<br><br>

<%
//String[] userInfo = (String[]) session.getAttribute("userInfo"); 
String[] date = (String[]) session.getAttribute("date");
String IDPM = (String) request.getAttribute("IDPM");
String[] orderBookArray = (String[])request.getAttribute("orderBookArray");
String[] orderArray = (String[])request.getAttribute("orderArray");

%>

<div class="result"><h3>Your order has been sent successfully!</h3>

<table id="userInfo">
		<tr>
			<td><b>Cart ID: </b></td>
			<td><i></i><%= IDPM%></td>
		</tr>
		<tr>
			<td><b>Library: </b></td>
			<td><i></i><%=orderArray[1]%></td>
		</tr>
		<tr>
			<td><b>Duration: </b></td>
			<td><i>From <%=orderArray[2]%> to <%=orderArray[3]%></i></td>
		</tr>
		<tr>
			<td><b>Deposit: </b></td>
			<td><i><%=orderArray[4]%> đ</i></td>
		</tr>
	</table>
</div>
<br><br><br><br><br><br><br><br>
<table id="bookInCart">
	<tr class="trt">
		<td>ISBN</td>
		<td>Title</td>
		<td>Price</td>
	</tr>
	<%
		for (int j = 0; j < orderBookArray.length; j+=4) {
	%>

	<tr>
		<td><a target="_blank" href="Book?isbn=<%=orderBookArray[j]%>"><%=orderBookArray[j]%></a></td>
		<td><%=orderBookArray[j+1]%></td>
		<td><%=orderBookArray[j+2]%></td>
	</tr>
	<%
		}
	%>
</table>
</body>
</html>