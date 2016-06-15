<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your History Carts</title>
<style type="text/css">
body {
	margin: auto;
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
		String[][] cart = QueryDb.queryCart(user);
	%>
	<div class="result">
		<h2>History Carts</h2>
	</div>
	<%
		if (cart != null) {
			for (int i = 0; i < cart.length; i++) {
	%>
	<div class="result">
		<table id="userInfo">
			<tr>
				<td><b>Cart ID: </b></td>
				<td><i></i><%=cart[i][0]%></td>
			</tr>
			<tr>
				<td><b>Library: </b></td>
				<td><i></i><%=cart[i][1]%></td>
			</tr>
			<tr>
				<td><b>Duration: </b></td>
				<td><i>From <%=cart[i][2]%> to <%=cart[i][3]%></i></td>
			</tr>
			<tr>
				<td><b>Deposit: </b></td>
				<td><i><%=cart[i][4]%> đ</i></td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%
		String[][] bookCart = QueryDb.queryBookCart(cart[i][0]);
	%>
	<table id="bookInCart">
		<tr class="trt">
			<td>ISBN</td>
			<td>Title</td>
			<td>Price</td>
		</tr>
		<%
			for (int j = 0; j < bookCart.length; j++) {
		%>

		<tr>
			<td><a target="_blank" href="Book?isbn=<%=bookCart[j][0]%>"><%=bookCart[j][0]%></a></td>
			<td><%=bookCart[j][1]%></td>
			<td><%=bookCart[j][2]%></td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<div class="result">
		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<br>
		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	</div>
	<br>
	<br>

	<%
		}
		}
	%>

</body>
</html>