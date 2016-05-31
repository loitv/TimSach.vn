<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="style/login.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="register-form" method="post" action="Register">
				<input type="text" placeholder="name" name="id" required/> <input type="password"
					placeholder="password" name="pwd" required/>
				<button>create</button>
				<p class="message">
					Already registered? <a href="#">Sign In</a>
				</p>
			</form>
			<form class="login-form" method="post" action="Login">
				<input type="text" placeholder="username" name="id" required/> <input type="password"
					placeholder="password" name="pwd" required/>
				<button>login</button>
				<p class="message">
					Not registered? <a href="#">Create an account</a>
				</p>
			</form>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/login.js"></script>
</body>
</html>