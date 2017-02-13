<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<meta name="apple-mobile-web-app-capable" content="yes">

<title>Fancy's Website</title>

<!-- css link -->
<link rel="stylesheet" href="css/style.css">

<!-- responsive link -->
<link rel="stylesheet" href="css/responsive.css">

<!-- icon pack -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Questrial"
	rel="stylesheet">

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/jQuery.js"></script>


</head>
<body>
	<%
		String imgName = (String) request.getAttribute("imgName");
	%>
	<div id="cover">
		<div class="container">
			<div class="img">
				<a href="login.do"><img id="logo" src="img/fancys_logo.jpg"></a>
			</div>
			<div class="burger-bar">
				<span class="lnr lnr-menu"></span>
			</div>
		</div>
	</div>


	<div id="signin_box">
		<div class="signin_btn">
			<%
				if (session == null || session.getAttribute("loggedInuser") == null) {
			%>
			<table>
				<tr>
					<td class='signin_popup'><a href='#'>sign in</a></td>
				</tr>
			</table>

			<%
				} else {

					Enumeration<String> enumeration = session.getAttributeNames();

					while (enumeration.hasMoreElements()) {

						String session_name = enumeration.nextElement().toString();
						String session_value = (String) session.getAttribute(session_name);
			%>
			<table>
				<tr>
					<td><img class="profile_img" src="profile_img/<%=imgName%>">
					</td>
				</tr>
				<tr>
					<td class="signout_popup"><a href="jsp/logout.jsp">sign
							out</a></td>
				</tr>
			</table>
			<%
				}
				}
			%>
		</div>
	</div>



	<div id="menu">
		<div class="container">
			<nav>
			<ul>
				<li><a href="#music">Music</a></li>
				<li><a href="#tour">Tour</a></li>
				<%
					if (session == null || session.getAttribute("loggedInuser") == null) {
				%>
				<li><a class='forum' href='#'>Forum</a></li>
				<%
					} else {
				%>
				<li><a href='message_view.do'>Forum</a></li>
				<%
					}
				%>
				<li><a href="#contact">Contact</a></li>
			</ul>
			</nav>
		</div>

		<div class="responsive_menu">
			<ul>
				<li><a href="#music">Music</a></li>
				<li><a href="#tour">Tour</a></li>

				<%
					if (session == null || session.getAttribute("loggedInuser") == null) {
				%>
				<li><a class='forum' href='#'>Forum</a></li>
				<%
					} else {
				%>
				<li><a href='message_view.do'>Forum</a></li>
				<%
					}
				%>
				<li><a href="#contact">Contact</a></li>
			</ul>
		</div>
	</div>

	<div id="forum_popup">
		<div class="container">
			<div class="box">
				<table class="table">
					<tr>
						<td class="ghost"></td>
						<td class="title"><h2>ALERT</h2></td>
						<td class="back"><a href="main.jsp"><span
								class="lnr lnr-cross"></span></a></td>
					</tr>
					<tr>
						<td class="content" colspan="3">
							<h3>Sign-in required</h3>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div id="signin_popup">
		<div class="container">
			<div class="box">
				<form name="login" action="login.do" method="POST">
					<table class="table">
						<tr>
							<td class="ghost"></td>
							<td class="title">
								<h2>Sign In</h2>
							</td>
							<td class="back"><a href="main.jsp"> <span
									class="lnr lnr-cross"></span>
							</a></td>
						</tr>
						<tr>
							<td class="content">username</td>
							<td class="input" colspan="2"><input type="text"
								name="username" placeholder="username(6~10char)" maxlength="10">
							</td>
						</tr>
						<tr>
							<td class="content">password</td>
							<td class="input" colspan="2"><input type="password"
								name="password" placeholder="password" maxlength="10"></td>

						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td class="up_forgot" colspan="2">
								<ul>
									<li><a id="signup_btn" href="#">Sign Up</a></li>

									<li><a href="forgot_password.php">Forgot Password</a></li>
								</ul>
							</td>
							<td class="submit"><input type="button" value="SUBMIT"
								onclick="login_validation(document.login.username, document.login.password)">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

	<div id="signup_popup">
		<div class="container">
			<div class="box">
				<form name="registration" action="registration.do" method="POST"
					enctype="multipart/form-data">
					<table class="table">
						<tr>
							<td class="ghost"></td>
							<td class="title">
								<h2>Sign Up</h2>
							</td>
							<td class="back"><a href="main.jsp"> <span
									class="lnr lnr-cross"></span>
							</a></td>
						</tr>
						<tr>
							<td class="content">username</td>
							<td class="input" colspan="2"><input type="text"
								name="username" placeholder="username(6~10)" maxlength="10"></td>
						</tr>
						<tr>
							<td class="content">password</td>
							<td class="input" colspan="2"><input type="password"
								name="password" placeholder="password(6~10)" maxlength="10"></td>
						</tr>
						<tr>
							<td class="content">password confirm</td>
							<td class="input" colspan="2"><input type="password"
								name="password_confirm" maxlength="10"></td>
						</tr>
						<tr>
							<td class="content">profile image upload</td>
							<td class="profile" colspan="2"><input type="file"
								name="profile"></td>
						</tr>
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td class="up_forgot" colspan="2"></td>
							<td class="submit"><input type="button" value="submit"
								onclick="registration_validation(document.registration.username, document.registration.password, document.registration.password_confirm)"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>



	<div id="music">
		<div class="container">
			<div class="img">
				<img id="album_img" src="img/fancys_cd_cover_2000.jpeg">
			</div>
			<div class="buying">
				<p>
					<a href="http://music.naver.com/album/index.nhn?albumId=370864">AVAILABLE
						NOW</a>
				</P>
			</div>
		</div>
	</div>


	<div id="tour">
		<div class="container">
			<div class="heading">
				<h1>TOUR</h1>
			</div>

			<div class="content">
				<h2>TBA</h2>
			</div>
		</div>
	</div>

	<footer id="footer"> <%@ include file="footer.jsp"%>
	</footer>
</body>


<script type="text/javascript">
	function registration_validation(username, password, password_confirm) {

		if (username.value == 0 || password.value == 0 || password_confirm == 0) {
			alert("Fill in all fields");
			return;
		} else {
			if (username.value.length <= 5) {
				alert("Username should be at least over 6 characters");
				username.focus();
				return;
			} else if (password.value.length <= 5) {
				alert("Password should be at least over 6 characters");
				password.focus();
				return;
			} else {
				if (password.value == password_confirm.value) {
					alert("Registration completed");
					document.registration.submit();
				} else {
					alert("Password unmatched");
					password.focus();
					return;
				}
			}
		}
	}

	function login_validation(username, password) {
		if (username.value == 0 || password.value == 0) {
			alert("Fill in all fields");
			return;
		} else {
			alert("login completed");
			document.login.submit();
		}

	}
</script>


</html>
