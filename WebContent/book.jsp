<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<%@ page import="control.*"%>
<%@ page import="function.*"%>
<%
	request.setCharacterEncoding("UTF-8");
		String isbn = request.getParameter("isbn");
		new QueryDb(isbn);
		String[] bookInfo = QueryDb.getBookInfoss();
		String[] libStatus = QueryDb.getLibstatuss();
		String[] authors = QueryDb.getAuthorss();
		UnAccent ua = new UnAccent();
		String[] addrs = new String[libStatus.length];
		for (int i = 0; i < addrs.length; i ++) {
			addrs[i] = ua.unAccent(libStatus[i]);
		}
		//String addr = ua.unAccent(libStatus[2]);
%>

<title><%=bookInfo[0]%></title>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="js/map.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
body {
	align: center;
}

.h {
	color: Black;
	font-family: Tahoma;
	font-size: 8pt;
}

#table1 {
	margin: 0 auto;
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: Silver;
	padding: 3px;
}

#table2 {
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: white;
	padding: 3px;
}

#table3 {
	margin: 0 auto;
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: black;
	padding: 3px;
	width: 65%;
}

#mapArea {
	margin: 0 auto;
	width: 65%;
}

td {
	border-width: 1px;
	border-style: solid;
	border-color: white;
	padding: 3px;
}

a {
text-decoration: none;
}

.text1 {
	margin: 0 auto;
	width: 65%;
}

.rh {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 8pt;
	text-align: Left;
}

.rt {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 10.5pt;
	text-align: Left;
}

.item {
	text-align: right;
	font-weight: bold
}

.trt {
	background: #34495E;
	color: White;
	font-weight: bold;
}

.inner-button {
	margin: auto;
	display: block;
}
</style>
</head>
<body>

<%@ include file="header.jsp" %>
<br><br>
	<h2 class="text1"><%=bookInfo[0]%></h2>
	<br>

	<table id="table1">
		<tr class="rt">
			<td><img src="<%=bookInfo[8]%>" height="231" width="200"></img>
			</td>
			<td>
				<table id="table2">
					<tr class="rt">
						<td class="item">Description:</td>
						<td class="itempro"><%=bookInfo[7]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Authors:</td>
						<td class="itempro"><a href="search.jsp?q=<%=authors[0] + " " + authors[1]%>&type=author"><%=authors[0] + " " + authors[1]%></a> <%
						 	if (authors.length > 2) {
						 		for (int i = 2; i < authors.length; i += 2) {
						 		%> , <a href="search.jsp?q=<%=authors[i] + " " + authors[i + 1]%>&type=author"><%=authors[i] + " " + authors[i + 1]%></a> <%
						 		}
						 	}
						 %></td>
					</tr>
					<tr class="rt">
						<td class="item">Publisher:</td>
						<td class="itempro"><a href="search.jsp?q=<%=bookInfo[2]%>&type=publisher"><%=bookInfo[2]%></a></td>
					</tr>
					<tr class="rt">
						<td class="item">Year:</td>
						<td class="itempro"><%=bookInfo[3]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Price:</td>
						<td class="itempro"><%=bookInfo[4]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Pages:</td>
						<td class="itempro"><%=bookInfo[5]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Language:</td>
						<td class="itempro"><%=bookInfo[6]%></td>
					</tr>
					<tr class="rt">
						<td class="item">Category:</td>
						<td class="itempro"><%=bookInfo[1]%></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<h3 class="text1">Available in:</h3>
	<br>
	<!-- 
	<form method='get' action='Cart'>
	 -->
	
		<table id="table3">
			<tr class="trt">
				<td>Library</td>
				<td>Address</td>
				<td>Phone</td>
				<td>Status</td>
				<td></td>
			</tr>
			<%
				for (int i = 0; i < libStatus.length; i += 6) {
			%>
			<tr class="trc">
				<td><%=libStatus[i + 1]%></td>
				<td><%=libStatus[i + 2]%></td>
				<td><%=libStatus[i + 3]%></td>
				<td><%=libStatus[i + 5]%></td>
				<td align="center">
					<a href="Cart?isbn=<%= isbn%>&libID=<%=libStatus[i]%>"><img style="height:25px;" src="images/icon/addtocart.png"></a></td>
				<!-- 
				<td><input class="inner-button" type='checkbox' name='libID'
					value='<%=libStatus[i]%>' /></td>
				 -->
				
			</tr>
			<%
				}
			%>
			<!-- 
			<tr class="trc">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><p></p> <input class="inner-button" type='submit'
					value='Add to Cart' /></td>
			</tr>
			 -->
			
		</table>
		<!-- 
		<input type="hidden" name="isbn" value="<%= isbn%>">
	</form>
		 -->
		
	<br />
	<br />
	<br />
	<% 
	int count = 0;
	for (int i = 0; i < libStatus.length; i += 6) {
		count++;
		%>
		<input type="hidden" id="libAddr<%=count %>" value="<%=libStatus[i]%>"/>
		<%
		
	}
	%>
	<input type="hidden" id="addrNumber" value="<%=count %>"/>
	<%
	%>
	

	<div id="mapArea" style="height: 500px;"></div>



</body>
</html>