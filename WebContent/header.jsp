<link rel="stylesheet" type="text/css" href="style/menubar.css">
<style type="text/css">
#searchmenubar input[type="text"] {
	background: url(images/search-dark.png) no-repeat 10px 6px white;
	border: 0 none;
	font: 12px Arial, Helvetica, Sans-serif;
	color: black;
	width: 170px;
	padding: 6px 15px 6px 35px;
	border-radius: 20px;
	margin-top: 15px;
	float: right;
}
</style>
<% 
	String uri = request.getScheme() + "://" +request.getServerName() +":" +request.getServerPort() + request.getRequestURI() + "?" +request.getQueryString();
	String url = request.getRequestURL().toString();
	request.getSession().setAttribute("uri", uri);		
%>

<header class="main-header">
		<a class="logo" href="index.jsp"> <img src="images/library1.png" />
		</a>
		<ul class="main-nav">

			
			<li class="dropdown"><a href="#">Category</a>
				<ul class="drop-nav">
					<li class="flyout"><a href="search.jsp?q=it&type=category">IT Books</a>
						<ul class="flyout-nav">
							<li><a href="search.jsp?q=web&type=category">Web Development</a></li>
							<li><a href="search.jsp?q=programming&type=category">Programming</a></li>
							<li><a href="search.jsp?q=database&type=category">Database</a></li>
							<li><a href="search.jsp?q=operating+systems&type=category">Operating System</a></li>
						</ul></li>
					<li class="flyout"><a href="search.jsp?q=et&type=category">ET Books</a>
						<ul class="flyout-nav">
							<li><a href="search.jsp?q=digital+design&type=category">Digital
									Design</a></li>
							<li><a href="#">Circuits</a></li>
							<li><a href="#">Network</a></li>
							<li><a href="#">Signal Processing</a></li>
						</ul></li>
					<li><a href="search.jsp?q=vanhoc&type=category">Literature</a></li>

					<li><a href="#">Magazine</a></li>
				</ul></li>
			<li><a href="Cart">Cart</a></li>
			<li><a href="contact.jsp">Contact</a></li>
			<li><a id="account" href="login.jsp">Login</a></li>
		</ul>
		
		<form id="searchmenubar" action="search.jsp" method='get'
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
		document.getElementById("accurl").setAttribute("href", "account.jsp");
		document.getElementById("account").setAttribute("href", "Logout");
		document.getElementById("account").setAttribute("onclick", "window.location.reload(true);")
		</script>
	<%
	}
	%>