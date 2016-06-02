<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order</title>
<style type="text/css">
body {
margin: auto;
width: 66%;
}
table {
	width: 85%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: black;
	padding: 3px;
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
text-align:center;
}
.inner-button {
	margin: auto;
	display: block;
}
</style>
</head>
<body>
	<%
	
		String[] orderArray = (String[]) request.getAttribute("orderArray");
		String[] userInfo = (String[]) session.getAttribute("userInfo"); 
		String[] date = (String[]) session.getAttribute("date"); 
		String user = (String) session.getAttribute("user");
		 if (userInfo == null) {
			 %>
			 null
			 <%
		 } else {
			 %>
			 <h2>Your order</h2>
			<h4>UserID: <%=user%></h4>
			<h4>User Name: <%=userInfo[1] %></h4>
			<h4>From <%=date[0] %> to <%=date[1] %></h4>
			<h4>Deposit:</h4>
			 <%
		 }
	%>
	<form action="RemoveItem" method="get">
	<table>
		<tr class="trt">
			<td>ISBN</td>
			<td>Title</td>
			<td>Price</td>
			<td>Library ID</td>
			<td></td>
		</tr>
		<% for (int i = 0; i < orderArray.length; i += 4) {
			%>
		<tr>
			<td><%=orderArray[i]%></td>
			<td><%=orderArray[i + 1]%></td>
			<td><%=orderArray[i + 2]%></td>
			<td ><%=orderArray[i + 3]%></td>
			<td><input class="inner-button" type='checkbox' name='isbn'
					value='<%=orderArray[i]%>' /></td>
		</tr>
			<%
		}
		%>
		<tr class="trc">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><br><input class="inner-button" type='submit'
					value='Remove'/></td>
		</tr>
	</table></form>

</body>
</html>