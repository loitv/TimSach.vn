<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="control.*"%>
<%
	QueryDb db = new QueryDb();

	String query = request.getParameter("q");
	
	List<String> tenSachs = db.getData(query);

	Iterator<String> iterator = tenSachs.iterator();
	while(iterator.hasNext()) {
		String country = (String)iterator.next();
		out.println(country);
	}
%>