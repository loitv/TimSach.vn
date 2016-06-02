<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
<link rel="stylesheet" type="text/css" href="js/slick/slick.css">
<link rel="stylesheet" type="text/css" href="js/slick/slick-theme.css">
<link rel="stylesheet" type="text/css" href="style/menubar.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="http://www.red-team-design.com/wp-content/uploads/2011/02/modernizr-1.6.min_.js"></script>
<script>
	$(document)
			.ready(
					function() {
						if (!Modernizr.input.placeholder) {

							var placeholderText = $('#search').attr(
									'placeholder');

							$('#search').attr('value', placeholderText);
							$('#search').addClass('placeholder');

							$('#search').focus(function() {
								if (($('#search').val() == placeholderText)) {
									$('#search').attr('value', '');
									$('#search').removeClass('placeholder');
								}
							});

							$('#search')
									.blur(
											function() {
												if (($('#search').val() == placeholderText)
														|| (($('#search').val() == ''))) {
													$('#search').addClass(
															'placeholder');
													$('#search').attr('value',
															placeholderText);
												}
											});
						}
					});
</script>

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

#searchmenubar input[type="text"] {
	background: url(images/search-dark.png) no-repeat 10px 6px white;
	border: 0 none;
	font: 12px Arial, Helvetica, Sans-serif;
	color: black;
	width: 150px;
	padding: 6px 15px 6px 35px;
	border-radius: 20px;
	margin-top: 15px;
	float: right;
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
	<header class="main-header">
		<a class="logo" href="index.jsp"> <img src="images/library1.png" />
		</a>
		<ul class="main-nav">

			
			<li class="dropdown"><a href="#">Category</a>
				<ul class="drop-nav">
					<li class="flyout"><a href="#">IT Books</a>
						<ul class="flyout-nav">
							<li><a href="#">Web Development</a></li>
							<li><a href="Search?q=programming&type=category">Programming</a></li>
							<li><a href="#">Database</a></li>
							<li><a href="#">Operating System</a></li>
						</ul></li>
					<li class="flyout"><a href="#">ET Books</a>
						<ul class="flyout-nav">
							<li><a href="Search?q=digital+design&type=category">Digital
									Design</a></li>
							<li><a href="#">Circuits</a></li>
							<li><a href="#">Network</a></li>
							<li><a href="#">Signal Processing</a></li>
						</ul></li>
					<li><a href="Search?q=vanhoc&type=category">Literature</a></li>

					<li><a href="#">Magazine</a></li>
				</ul></li>
			<li><a href="#">Management</a></li>
			<li><a href="#">Contact</a></li>
			<li><a id="account" href="login.jsp">Login</a></li>
		</ul>
		<form id="searchmenubar" action="Search" method='get'
			accept-charset="UTF-8">
			<div>
				<input type="text" name="q" placeholder="Search..."><input
					type="hidden" name="type" value="title">
			</div>
		</form>
	</header>
	<div id="accountfield">
	<p id="accounttext">Welcome  <a id="accurl" href="#"></a></p>
	</div>
	<%String user = (String) session.getAttribute("user");
	
	if (user == null) {
		
	} else {
		%>
		
		<script>
		document.getElementById("account").textContent="Logout";
		document.getElementById("accurl").innerHTML="<%= user%>";
		document.getElementById("accurl").setAttribute("href", "Account?id=<%= user%>");
		document.getElementById("account").setAttribute("href", "Logout");
		document.getElementById("account").setAttribute("onclick", "window.location.reload(true);")
		</script>
	<%
	}
	%>
	
	<div>
	<a href="Logout" onclick="window.location.reload(true);">Kill session</a>
	</div>
	
	
	<div>
		<form action="Search" method='get' accept-charset="UTF-8">
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
			<a href="Book?isbn=8934974119814"> <img
				src="images/8934974119814.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=8934974125778"> <img
				src="images/8934974125778.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=8934974111788"> <img
				src="images/8934974111788.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=8934974099208"> <img
				src="images/8934974099208.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=978-1-4493-9330-4"> <img
				src="images/978-1-4493-9330-4.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=978-1-4493-9321-2"> <img
				src="images/978-1-4493-9321-2.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=978-1-4493-8267-4"> <img
				src="images/978-1-4493-8267-4.jpg">
			</a>
		</div>
		<div>
			<a target="_blank" href="Book?isbn=978-1-4493-9991-7"> <img
				src="images/978-1-4493-9991-7.jpg">
			</a>
		</div>
	</section>

</body>
</html>