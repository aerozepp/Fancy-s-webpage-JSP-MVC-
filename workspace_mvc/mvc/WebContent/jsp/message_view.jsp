
<%@ page import="java.lang.ClassCastException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<%!int page_show;
	int total_topic = 0;
	int numPages;
	int per_page = 6;
	int start_topic;%>

	<%
	 	total_topic = (int)request.getAttribute("topicNum");
		String get_page = (String) request.getParameter("cur_page");

		if (get_page == null) {
			page_show = 1;
		} else if (get_page != null) {
			page_show = Integer.parseInt(get_page);
		}
	%>

	<%
		numPages = Math.floorDiv(total_topic, per_page) + 1;
	%>

	<%
		if (page_show > 1)
			start_topic = (page_show * per_page) - per_page;
		else
			start_topic = 0;
	%>
	<div class="body">
		<c:forEach items="${messageView}" var="dto">	
			<a class="content"
				href="contentView.do?topic_id=${dto.topic_id}&cur_page=<%=page_show%>">
				<table class="box">
					<tr>
						<td class="image" rowspan="2"><img class="profile_img"
							src="profile_img/default.jpg"></td>
						<td class="title"><strong>${dto.titles} </strong></td>
						<td class="date">${dto.topicDate}</td>
					</tr>
					<tr>

						<td class="posted_by" colspan="2">
							<li>Posted by&nbsp</li>
							<li class="author">${dto.authors}</li>
						</td>

					</tr>
				</table>
			</a>
		</c:forEach>
	</div>


	<div class="pagination">
		<div class="pagination_box">
			<a
				href="message_view.do?cur_page=<%if (page_show > 1) {
				out.println(page_show - 1);
			} else {
				out.println(page_show);
			}%>">
				[prev]</a>

			<%
				for (int i = 1; i <= numPages; i++) {
			%>

			<a href="message_view.do?cur_page=<%=i%>"><%=i%></a>
			<%
				}
			%>
			<a
				href="message_view.do?cur_page=<%if (page_show < numPages) {
				out.println((page_show + 1));
			} else {
				out.println(page_show);
			}%>">
				[next]</a>

		</div>

	</div>
</body>
</html>