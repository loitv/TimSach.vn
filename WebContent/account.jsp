<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String[] userInfo = (String[]) request.getAttribute("userInfo");
%>
<title><%=userInfo[0]%></title>
<style type="text/css">
.userpro {
font-weight:bold;
text-align: right;
}
</style>
<script>
function changeType() {
	document.getElementById("change2").setAttribute("type", "text");;
	document.getElementById("info2").textContent="";
	document.getElementById("change3").setAttribute("type", "text");;
	document.getElementById("info3").textContent="";
	document.getElementById("change4").setAttribute("type", "text");;
	document.getElementById("info4").textContent="";
	document.getElementById("change5").setAttribute("type", "text");;
	document.getElementById("info5").textContent="";
	document.getElementById("change6").setAttribute("type", "text");;
	document.getElementById("info6").textContent="";
	document.getElementById("change7").setAttribute("type", "text");;
	document.getElementById("info7").textContent="";
	document.getElementById("submit").setAttribute("type", "submit");;
	
}
</script>

</head>
<body>
	<h2>Your personal Information</h2>
	<form action="SavePersonalInfo" method="POST">
	<table>
		<tr>
			<td class="userpro">ID: </td>
			<td><input type="hidden" name="id" value="<%=userInfo[0]%>" id="change1"><div id="info1"><%=userInfo[0]%></div></td>
		</tr>
		<tr>
			<td class="userpro">Full Name: </td>
			<td><input type="hidden" name="name" value="<%=userInfo[1]%>" id="change2"><div id="info2"><%=userInfo[1]%></div></td>
		</tr>
		<tr>
			
			<td class="userpro">Gender: </td>
			<td><input type="hidden" name="gender" value="<%=userInfo[2]%>" id="change3"><div id="info3"><%=userInfo[2]%></div></td>
		</tr>
		<tr>
			<td class="userpro">Birthday: </td>
			<td><input type="hidden" name="birthday" value="<%=userInfo[3]%>" id="change4"><div id="info4"><%=userInfo[3]%></div></td>
		</tr>
		<tr>
			<td class="userpro">Address: </td>
			<td><input type="hidden" name="addr" value="<%=userInfo[4]%>" id="change5"><div id="info5"><%=userInfo[4]%></div></td>
		</tr>
		<tr>
			<td class="userpro">Phone: </td>
			<td><input type="hidden" name="phone" value="<%=userInfo[5]%>" id="change6"><div id="info6"><%=userInfo[5]%></div></td>
		</tr>
		<tr>
			<td class="userpro">Email: </td>
			<td><input type="hidden" name="email" value="<%=userInfo[6]%>" id="change7"><div id="info7"><%=userInfo[6]%></div></td>
		</tr>
	</table><br>
	<input id="submit" type="hidden" value="Save" onclick="window.location.reload(true);">
	</form>
	<input type="submit" value="Edit Information" onclick="changeType();">
	
	
</body>
</html>