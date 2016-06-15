<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#contact-form {
	margin: 0 auto;
	width: 65%;
}

td {
height: 28px;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<div id="contact-form">
<h4>Contact us via:</h4>
<b>Phone: </b>(+84)1677836282<br>
<b>Email: </b>loitran1763@gmail.com
<br><br>
<h4>If you have any idea to help us better. Please let us know: </h4>
<form action="Contact" method="post">
	<table>
	<!-- 
	<tr>
			<td align="right"><b>Your name: </b></td>
			<td><input type="text" name="name" size="75"/></td>
		</tr>
	 -->
		
		<tr>
			<td align="right"><b>Your mail: </b></td>
			<td><input type="text" name="email" size="75"/></td>
		</tr>
		<tr>
			<td align="right"><b>Subject: </b></td>
			<td><input type="text" name="subject" size="75"/></td>
		</tr>
		<tr>
			<td align="right"><b>Message: </b></td>
			<td>
			<textarea name = "message"rows="6" cols="57"></textarea>
		</tr>	
		<tr>
			<td></td>
			<td><br><input type="submit" name="send" value="Send"/></td>
		</tr>	
	</table>
</form>
</div>

</body>
</html>