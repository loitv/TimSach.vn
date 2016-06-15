<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
<link rel="stylesheet" type="text/css" href="js/slick/slick.css">
<link rel="stylesheet" type="text/css" href="js/slick/slick-theme.css">
<script src="js/jquery-latest.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/slick/slick.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).on('ready', function() {
		$(".regular").slick({
			dots : true,
			infinite : true,
			slidesToShow : 4,
			slidesToScroll : 4
		});
	});
</script>

<style>
body {
	background: #f1f1f1;
	text-align: center;
	
}

.slider {
	width: 50%;
	margin: 100px auto;
}

.slick-slide {
	margin: 0px 20px;
}

.slick-slide img {
	width: 100%;
}

.slick-prev:before, .slick-next:before {
	color: black;
}

.book-title {
	font-weight: bold;
	text-align: Center;
}



#searchbox {
	background: #eaf8fc;
	background-image: -moz-linear-gradient(#fff, #d4e8ec);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #d4e8ec),
		color-stop(1, #fff));
	-moz-border-radius: 35px;
	border-radius: 35px;
	border-width: 1px;
	border-style: solid;
	border-color: #c4d9df #a4c3ca #83afb7;
	width: 500px;
	height: 35px;
	padding: 10px;
	margin: 80px auto 10px;
	overflow: hidden;
}

#searchBy {
	color: black;
}

#search, #submit {
	float: left;
}

#search {
	padding: 5px 9px;
	height: 23px;
	width: 380px;
	border: 1px solid #a4c3ca;
	font: normal 13px 'trebuchet MS', arial, helvetica;
	background: #f1f1f1;
	-moz-border-radius: 50px 3px 3px 50px;
	border-radius: 50px 3px 3px 50px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0
		rgba(255, 255, 255, 1);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0
		rgba(255, 255, 255, 1);
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25) inset, 0 1px 0
		rgba(255, 255, 255, 1);
}

/* ----------------------- */
#submit {
	background: #6cbb6b;
	background-image: -moz-linear-gradient(#95d788, #6cbb6b);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #6cbb6b),
		color-stop(1, #95d788));
	-moz-border-radius: 3px 50px 50px 3px;
	border-radius: 3px 50px 50px 3px;
	border-width: 1px;
	border-style: solid;
	border-color: #7eba7c #578e57 #447d43;
	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	-webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3)
		inset;
	height: 35px;
	margin: 0 0 0 10px;
	padding: 0;
	width: 90px;
	cursor: pointer;
	font: bold 14px Arial, Helvetica;
	color: #23441e;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}

#submit:hover {
	background: #95d788;
	background-image: -moz-linear-gradient(#6cbb6b, #95d788);
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #95d788),
		color-stop(1, #6cbb6b));
}

#submit:active {
	background: #95d788;
	outline: none;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
}

#submit::-moz-focus-inner {
	border: none;
}

/* ----------------------- */
#search::-webkit-input-placeholder {
	color: #9c9c9c;
	font-style: italic;
}

#search:-moz-placeholder {
	color: #9c9c9c;
	font-style: italic;
}

#search.placeholder {
	color: #9c9c9c !important;
	font-style: italic;
}

#search:focus {
	border-color: #8badb4;
	background: #fff;
	outline: none;
}
</style>

</head>
<body>
	<%@ include file="header.jsp" %>
	<br><br>
	
	<!--  
	<div>
	<a href="Logout" onclick="window.location.reload(true);">Kill session</a>
	</div>
	-->
	
	<div>
		<form action="search.jsp" method='get' accept-charset="UTF-8">
			<div id="searchbox">
				<input id="search" type="text" name="q" placeholder="Type here">
				<input id="submit" type="submit" value="Search">
			</div>
			<div>
				<p id="searchBy">
					<b>Search book by:</b> <input type="radio" name="type"
						value="title" checked /> Title <input type="radio" name="type"
						value="author" /> Author <input type="radio" name="type"
						value="isbn" /> ISBN
				</p>
			</div>
		</form>
	</div>

	<section class="regular slider">
		<div>
			<a href="book.jsp?isbn=8934974119814"> <img
				src="images/8934974119814.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=8934974125778"> <img
				src="images/8934974125778.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=8934974111788"> <img
				src="images/8934974111788.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=8934974099208"> <img
				src="images/8934974099208.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=978-1-4493-9330-4"> <img
				src="images/978-1-4493-9330-4.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=978-1-4493-9321-2"> <img
				src="images/978-1-4493-9321-2.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=978-1-4493-8267-4"> <img
				src="images/978-1-4493-8267-4.jpg">
			</a>
		</div>
		<div>
			<a href="book.jsp?isbn=978-1-4493-9991-7"> <img
				src="images/978-1-4493-9991-7.jpg">
			</a>
		</div>
	</section>

</body>
</html>