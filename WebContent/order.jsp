<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>
</head>
<body>
	<%
		String isbn = (String) request.getAttribute("isbn");
	%>
	<%
		String libID = (String) request.getAttribute("libID");
	%>
	<%
		String user = (String) session.getAttribute("user");
	%>

	<h1>Your order</h1>
	<h3>
		Name:
		<%=user%></h3>
	<h3>From .... to....</h3>
	<h3>Deposit:</h3>

	<table>
		<tr>
			<td>ISBN</td>
			<td>Title</td>
			<td>Price</td>
		</tr>
		<tr>
			<td><%=isbn%></td>
			<td></td>
			<td></td>
		</tr>
	</table>

</body>
</html>