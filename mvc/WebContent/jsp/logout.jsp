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
	
			Enumeration<String> enumeration = session.getAttributeNames();

			while (enumeration.hasMoreElements()) {

				String session_name = enumeration.nextElement().toString();
				String session_value = (String) session.getAttribute(session_name);
				
				session.removeAttribute(session_name);
				
			}
			response.sendRedirect("../main.jsp");
			
	%>


</body>
</html>