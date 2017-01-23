<%@page import="java.sql.Timestamp"%>
<%@page import="dto.Dto_Topics"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<!-- css link -->
<link rel="stylesheet" href="css/forum.css">

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/jQuery.js"></script>


</head>
<body>

	<%!String topic_index = null;%>

	<%
		topic_index = (String) request.getParameter("topic_id");
		if (topic_index != null) {
	%>
	<%
		Enumeration<String> enumeration = session.getAttributeNames();

			String sessionName = null;
			String sessionValue = null;

			while (enumeration.hasMoreElements()) {

				sessionName = enumeration.nextElement().toString();
				sessionValue = (String) session.getAttribute(sessionName);
			}
	%>

	<%
		try {
				Dto_Topics dto2 = new Dto_Topics();
				dto2 = (Dto_Topics) request.getAttribute("contentView");
				String author = dto2.getAuthors().toString();
				Timestamp date = dto2.getTopicDate();
				String title = dto2.getTitles().toString();
				int topic_id = dto2.getTopic_id();
				String text = dto2.getTexts().toString();
				
				
				
	%>

	<div class="container">

		<table class="forum_table">
			<tr class="head">
				<td class="image_box" rowspan="2"><img class="profile_img"
					src='profile_img/default.jpg'></td>
				<td class="posted_box">Posted by</td>
				<td class="author_box"><%=author%></td>
				<td class="date_box"><%=date%></td>
				<td class="back_box"><a href="message_view.do"><span
						class="lnr lnr-cross"></span></a></td>

			</tr>

			<tr class="head">
				<td class="title_box" colspan="2"><strong><%=title%> </strong></td>
				<td class="likes_box" colspan="2">
					<%
						if (author.equals(sessionValue)) {
					%> <span class='lnr lnr-pencil'></span>&nbsp;&nbsp; <span
					class='lnr lnr-trash'></span> <%
 	} else {
 %> <a href="#"><span class='lnr lnr-thumbs-up blue'></span></a> <%
 	}
 %>
				</td>
			</tr>
			<tr class="head">
				<td></td>
				<td class="content_box" colspan="4"><%=text%></td>
			</tr>
			<tr>
				<td class="image_box"><img class="profile_img2"
					src="profile_img/default.jpg"></td>

				<td class="comment_input" colspan="4">
					<form name="reply_box"
						action="reply_create.do?topic_id=<%=topic_id%>" method="POST">
						<input type="text" name="reply" placeholder="Add a comment">
						<input type="button" value="Add"
							onclick="reply_submit(document.reply_box.reply)">
					</form>
				</td>
			</tr>
			</div>

			<c:forEach items="${replyView}" var="">
				<table>
					<tr class="reply">
						<td class="image_box" rowspan="2"><img class="profile_img2"
							src="profile_img/default.img"></td>
						<td class="comment_author" colspan="2">${dto.authors}</td>
						<td class="comment_date" colspan="2">${dto.topicDate}</td>
					</tr>
					<tr>
						<td class="comment_text" colspan="4">${dto.texts}</td>
					</tr>
				</table>
			</c:forEach>


			<div id="message_edit_popup">
				<div class="container">
					<div class="box">
						<form name="messageEdit"
							action="message_edit.do?topic_id=<%=topic_id%>" method="POST">
							<table>
								<tr>
									<td class="title">
										<h2>Edit Your Messages</h2>
									</td>
									<td class="back"><a href="?topic_id=<%=topic_id%>"> <span
											class="lnr lnr-cross"></span>
									</a></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="content">
											<input type="text" name="title" value="<%=title%>">
											<textarea rows="4" cols="40" name="textarea"><%=text%></textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td class="submit" colspan="2"><input type="button"
										value="EDIT"
										onclick="edit_submit(document.messageEdit.title, document.messageEdit.textarea)"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>



			<div id="delete_popup">
				<div class="container">
					<table class="box">
						<tr>
							<td class="title"><h2>Delete</h2></td>
							<td class="back"><a
								href="contentView.do?topic_id=<%=topic_id%>"><span
									class="lnr lnr-cross"></span></a></td>
						</tr>
						<tr>
							<td class="content" colspan="2"><h2>
									<a href="message_delete.do?topic_id=<%=topic_id%>">OK</a>
								</h2></td>
						</tr>
					</table>
				</div>
			</div>
			<%
				} catch (Exception e) {
						e.printStackTrace();
					}
				}
			%>
		
</body>

<script type="text/javascript">
	function edit_submit(title, textarea) {
		if (title == 0 || textarea == 0) {
			alert("Fill in all fields");
		} else {
			document.messageEdit.submit();
		}
	}
	function reply_submit(reply) {
		if (reply == 0) {
			alert("Fill in all fields");
		} else {
			document.reply_box.submit();
		}
	}
</script>
</html>