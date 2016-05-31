<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
h2 {
	margin-left: 187px;
}

table {
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
	width: 70%;
}

td {
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
}

.rh {
	background-color: #003b71;
	vertical-align: Top;
	color: White;
	font-family: Tahoma;
	font-size: 10pt;
	font-weight: bold;
	text-align: Left;
}

.rt {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 8pt;
	text-align: Left;
}
</style>
</head>
<body>
	<%@ page import="control.*"%>
	<%@ page import="function.*"%>
	<%
		String[] isbns = (String[]) request.getAttribute("isbn");
		String[] titles = (String[]) request.getAttribute("title");
		String[] images = (String[]) request.getAttribute("image");
		String[] descs = (String[]) request.getAttribute("desc");
	%>

	<h2>Kết quả tìm kiếm</h2>

	<!----------------------------------------------------------------------------------------------->
	<%
		if (titles.length == 0) {
	%>
	<h2>NOT FOUND!</h2>
	<%
		} else {
	%>

	<table class="rwd-table" cellspacing="5" align="center">
		<%
			for (int i = 0; i < titles.length; i++) {
		%>
		<tr class="rt">
			<td><img src="<%=images[i]%>" height="200" width="173" /></td>
			<td><a href="Book?isbn=<%=isbns[i]%>"><b><%=titles[i]%></b></a><br />
				<p><%=descs[i]%></p></td>
		</tr>
		<%
			}
		%>
	</table>

	<%
		}
	%>


</body>
</html>