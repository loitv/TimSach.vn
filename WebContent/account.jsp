<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String[] userInfo = (String[]) session.getAttribute("userInfo");
%>
<title><%=userInfo[0]%></title>
<style type="text/css">
.userpro {
font-weight:bold;
text-align: right;
}
.text1 {
	margin: 0 auto;
	width: 65%;
}
.rt {
	background-color: White;
	vertical-align: Top;
	color: Black;
	font-family: Tahoma;
	font-size: 10.5pt;
	text-align: Left;
	
}
.userpro {
height: 25px;
}

#table {
	
	width: 65%;
	border-collapse: collapse;
	border-width: 1px;
	border-style: solid;
	border-color: white;
	
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

	<%@ include file="header.jsp" %>
<br><br>

	<h2 class="text1">Your personal Information</h2><br><br>
	<form action="SavePersonalInfo" method="POST">
	<table id="table">
		<tr class="rt">
			<td class="userpro">ID:&nbsp;</td>
			<td><input type="hidden" name="id" value="<%=userInfo[0]%>" id="change1"><div id="info1"><%=userInfo[0]%></div></td>
		</tr>
		<tr class="rt">
			<td class="userpro">Full Name:&nbsp;</td>
			<td><input type="hidden" name="name" value="<%=userInfo[1]%>" id="change2"><div id="info2"><%=userInfo[1]%></div></td>
		</tr>
		<tr class="rt">
			
			<td class="userpro">Gender:&nbsp; </td>
			<td><input type="hidden" name="gender" value="<%=userInfo[2]%>" id="change3"><div id="info3"><%=userInfo[2]%></div></td>
		</tr>
		<tr class="rt">
			<td class="userpro">Birthday:&nbsp; </td>
			<td><input type="hidden" name="birthday" value="<%=userInfo[3]%>" id="change4"><div id="info4"><%=userInfo[3]%></div></td>
		</tr>
		<tr class="rt">
			<td class="userpro">Address:&nbsp; </td>
			<td><input type="hidden" name="addr" value="<%=userInfo[4]%>" id="change5"><div id="info5"><%=userInfo[4]%></div></td>
		</tr>
		<tr class="rt">
			<td class="userpro">Phone:&nbsp; </td>
			<td><input type="hidden" name="phone" value="<%=userInfo[5]%>" id="change6"><div id="info6"><%=userInfo[5]%></div></td>
		</tr>
		<tr class="rt">
			<td class="userpro">Email:&nbsp;</td>
			<td><input type="hidden" name="email" value="<%=userInfo[6]%>" id="change7"><div id="info7"><%=userInfo[6]%></div></td>
		</tr>
	</table><br>
	<div  class="text1"><input id="submit" type="hidden" value="Save" onclick="window.location.reload(true);"></div>
	</form><br>
	<div  class="text1">
	<input type="submit" value="Edit Information" onclick="changeType();" style="width: 130px;">
	</div>
	
	
</body>
</html>