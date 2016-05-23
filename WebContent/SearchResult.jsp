<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
</head>
<body>
	<%
		String search = request.getParameter("searchString");
	%>
	<p>
		You search for: <em><%=search%></em>
	</p>
	<hr />

	<%@ page import="control.*"%>
	<%
		String input = "%".concat(search).concat("%");
		String query = String.format("select * from song where title like '%s' ORDER by title", input);
		QueryDb queryDb = new QueryDb(query);
		String[] results = queryDb.getResult();
		if (results.length == 0) {
			%>
			No result!
			<%
		} else {
			for (int i = 0; i < results.length; i++) {
				%>
				<p><%=results[i]%></p>
				<%
			}
		}
	%>



</body>
</html>