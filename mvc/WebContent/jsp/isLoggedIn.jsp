<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
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
			<td><%=session_value%> <%-- <img class="profile_img" src="profile_img/<?php echo $imgName;?>"> --%>
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
</body>
</html>