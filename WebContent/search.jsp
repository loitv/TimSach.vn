<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="control.*"%>
	<%@ page import="function.*"%>
	<%
	request.setCharacterEncoding("UTF-8");
	String search = request.getParameter("q");
	String type = request.getParameter("type");
	
	Search.getResult(type, search);
	String[] isbns = Search.getIsbns2();
	String[] titles = Search.getTitles2();
	String[] images = Search.getImages();
	String[] descs = Search.getDescs2();
	%>
<title>Search results</title>
<style type="text/css">


table {
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
	width: 65%;
}

td {
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
}

.heading3 {
margin: auto;
width: 65%;
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
	
	
	<%@ include file="header.jsp" %>
	<br><br>
	<div class="heading3"><h3>Search for : <i><%= search %></i></h3></div>
	

	<!----------------------------------------------------------------------------------------------->
	<%
		if (titles.length == 0) {
	%>
	<h3>NOT FOUND!</h3>
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