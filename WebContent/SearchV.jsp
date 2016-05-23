<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
border-collapse:collapse;
border-width:1px;
border-style:solid;
border-color:Silver;
padding:3px;
}
td {
border-width:1px;
border-style:solid;
border-color:Silver;
padding:3px;
}
.rh {
background-color: #003b71;
vertical-align:Top;
color:White;
font-family:Tahoma;
font-size:10pt;
font-weight: bold;
text-align:Left;
}
.rt {
background-color:White;
vertical-align:Top;
color:Black;
font-family:Tahoma;
font-size:8pt;
text-align:Left;
}
</style>
</head>
<body>
	<%@ page import="control.*"%>
	<%
		String[] results = (String[]) request.getAttribute("result");
		String[] ISBN = (String[]) request.getAttribute("ISBN");
		String[][] info = (String[][]) request.getAttribute("Info");
	%>
	<!--  
	<%if (results.length == 0) {%>
	No result!
	<%} else {
				for (int i = 0; i < results.length; i++) {%>
	<p><%=results[i]%></p>
	<%for (int j = 0; j < 6; j++) {%>
	<p><%=info[i][j]%></p>
	<%}
				}
			}%>
	-->

	<h2>Kết quả tìm kiếm</h2>
	<%
		if (results.length == 0) {
	%>
	Khong tim thay!
	<%
		} else {
	%>
	<form method='get' action='Order'>
	<table class="rwd-table">
		<tr class = "rh">
			<td>ISBN</td>
			<td>Ten Sach</td>
			<td>Linh vuc</td>
			<td>Nha xuat ban</td>
			<td>Nam phat hanh</td>
			<td>Gia bia</td>
			<td>Tai ban</td>
			<td>So trang</td>
			<td></td>
		</tr>
		<%
			for (int i = 0; i < results.length; i++) {
		%>
		<tr class = "rt">
			<td><%=ISBN[i]%></td>
			<td><%=results[i]%></td>
			<%
				for (int j = 0; j < 6; j++) {
			%>
			<td><%=info[i][j]%></td>
			<%
				}
			%>
			<td><input type='checkbox' name='id' value='<%=ISBN[i]%>'/></td>
		</tr>
		<%
			}
		%>
	</table>
	<p><input type='submit' value='ORDER' /></form>
	<%
		}
	%>

</body>
</html>