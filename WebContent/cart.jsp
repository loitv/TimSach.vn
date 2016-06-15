<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Cart</title>
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

	<%@ include file="header.jsp"%>
	<br>

	<%@ page import="control.*"%>
	<%@ page import="function.*"%>
	<%
		String[] userInfo = (String[]) session.getAttribute("userInfo");
		String[] date = (String[]) session.getAttribute("date");
		Double deposit = (Double) request.getAttribute("deposit");
		//String[] date = Cart.getDate();
		if (user == null) {
	%>
	
	<div class="result">
		<h3>You must login to enter this section!</h3>
	</div>
	<%
		} else {
			String[] orderArray = (String[]) request.getAttribute("orderArray");
			if (orderArray == null) {
	%>
	
	<div class="result">
		<h3>Your cart is empty!</h3>
	</div>
	<%
		} else {
	%>
	<div class="result">
		<h2>Your cart</h2>
		<table id="userInfo">
			<tr>
				<td><b>User ID: </b></td>
				<td><i><%=user%></i></td>
			</tr>
			<tr>
				<td><b>User Name: </b></td>
				<td><i><%=userInfo[1]%></i></td>
			</tr>
			<tr>
				<td><b>Duration: </b></td>
				<td><i>From <%=date[0]%> to <%=date[1]%></i></td>
			</tr>
			<tr>
				<td><b>Deposit: </b></td>
				<td><i><%=deposit%> đ</i></td>
			</tr>
		</table>
	</div>
	<br>
	<br><br><br><br><br><br><br>
	
		<table id="bookInCart">
		<tr class="trt">
			<td>ISBN</td>
			<td>Title</td>
			<td>Price</td>
			<td>Library ID</td>
			<td>Remove</td>
		</tr>
		<%
			for (int i = 0; i < orderArray.length; i += 4) {
		%>
		<tr>
			<td><a target="_blank" href="Book?isbn=<%=orderArray[i]%>"><%=orderArray[i]%></a></td>
			<td><%=orderArray[i + 1]%></td>
			<td><%=orderArray[i + 2]%></td>
			<td><%=orderArray[i + 3]%></td>
			<td align="center"><a href="RemoveItem?isbn=<%=orderArray[i]%>"><img
					style="height: 25px;" src="images/icon/remove.png"></a></td>
		</tr>
		<%
			}
		%>
	</table>
	
	<br>
	<form action="Order" method="Post">
		<div class="result">
			<input style="float: right;" type='submit' value='Check out' />
		</div>
		<input type="hidden" name="s" value="yes">
	</form>
	<%
		}
		}
	%>
	<br><br><br>
	<div class="result">
	<h3><a href="historyCart.jsp">History carts...</a></h3>
	</div>
	
</body>
</html>