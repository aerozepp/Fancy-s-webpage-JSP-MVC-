<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="dto.Dto_Topics"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Fancy's Forum</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- css link -->
<link rel="stylesheet" href="css/forum.css">

<!-- responsive link -->
<link rel="stylesheet" href="css/responsive.css">

<!-- icon pack -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Questrial"
	rel="stylesheet">
<script src="https://use.fontawesome.com/639da023b9.js"></script>

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/jQuery.js"></script>


</head>
<body>
	<%
		String session_name = null;
		String session_value = null;

		Enumeration<String> enumerate = session.getAttributeNames();

		while (enumerate.hasMoreElements()) {

			session_name = enumerate.nextElement().toString();
			session_value = (String) session.getAttribute(session_name);
		}
	%>

	<section id="forum_menu">
	<div class="container">
		<table>
			<tr>
				<td>
					<div class="logo">
						<a href="main.jsp"> <img src="img/fancys_logo_1-2.jpg">
						</a>
					</div>
				</td>
				<td class="create">
					<p>+ create message</p>

				</td>
			<tr>
				<td class="title" colspan="2">
					<p>
						<a href="message_view.do">FORUM</a> &nbsp;&nbsp;<%=session_value%>
					</p>
				</td>
				<td></td>
			</tr>
		</table>
	</div>
	</section>

	<section id="message_popup">
	<div class="container">
		<div class="box">
			<form name="message_create" action="message_create.do" method="POST">
				<table>
					<tr>
						<td class="title">
							<h2>Create Your Message</h2>
						</td>
						<td class="back"><a href="message_view.do"> <span
								class="lnr lnr-cross"></span>
						</a></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="content">
								<input type="text" name="title" placeholder="title">
								<textarea rows="4" cols="40" name="textarea"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td class="submit" colspan="2"><input type="button"
							value="SUBMIT"
							onclick="message_check(document.message_create.title, document.message_create.textarea)"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</section>

	<section id="forum_page">
	<div class="container">
		<%!int page_show;
	int total_topic = 0;
	int numPages;
	int per_page = 6;
	int start_topic;%>

		<%
			total_topic = Integer.parseInt(String.valueOf(request.getAttribute("topicNum")));
			String get_page = (String) request.getParameter("cur_page");

			if (get_page == null) {
				page_show = 1;
			} else if (get_page != null) {
				page_show = Integer.parseInt(get_page);
			}

			numPages = Math.floorDiv(total_topic, per_page) + 1;

			if (page_show > 1)
				start_topic = (page_show * per_page) - per_page;
			else
				start_topic = 0;
			
			int count = 1;
		%>
		<div class="body">
			<c:forEach items="${messageView}" var="dto">

				<a class="content"
					href="contentView.do?topic_id=${dto.topic_id}&cur_page=<%=page_show%>">
					<%
						if (count % 2 == 0) {
					%>
					<table class="box grey">
						<%
							} else {
						%><table class="box white">
							<%
								}
							%>
							<tr>
								<td class="image" rowspan="2"><img class="profile_img"
									src="profile_img/default.jpg"></td>
								<td class="title"><strong>${dto.titles} </strong></td>
								<td class="date">${dto.topicDate}</td>
							</tr>
							<tr>

								<td class="posted_by" colspan="2">

									<li class="replies"><span class="lnr lnr-bubble blue"></span>&nbsp;${dto.replies}</li>
									<li class="likes"><span class="lnr lnr-thumbs-up blue"></span>&nbsp;${dto.likes}</li>
									<li class="posted">Posted by</li>
									<li class="author">${dto.authors}</li>

								</td>

							</tr>
						</table>
				</a>
				<%
					count++;
				%>
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

	</div>
	</section>
	<%!String topic_index = null;%>
	<%
		topic_index = (String) request.getParameter("topic_id");
		if (topic_index != null) {
	%>
	<section id="comment_box"> <%
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
						if (author.equals(session_value)) {
					%> <span class='lnr lnr-pencil'></span>&nbsp;&nbsp; <span
					class='lnr lnr-trash'></span> <%
 	} else {
 %> <a href="like.do?topic_id=<%=topic_id%>"><span
						class='lnr lnr-thumbs-up blue'></span></a> <%
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
			<tr>
			<tr>
				<td></td>
			</tr>
			<c:forEach items="${replyView}" var="dto_reply">
				<tr class="reply">
					<td class="image_box" rowspan="2"><img class="profile_img2"
						src="profile_img/default.jpg"></td>
					<td class="comment_author" colspan="2">${dto_reply.authors}</td>
					<td class="comment_date" colspan="2">${dto_reply.topicDate}</td>
				</tr>
				<tr>
					<td class="comment_text" colspan="2">${dto_reply.texts}</td>
					<td class="comment_likes_box" colspan="3">
						<%
							if (author.equals(session_value)) {
						%> <span class='lnr lnr-pencil'></span>&nbsp;&nbsp; <span
						class='lnr lnr-trash'></span> <%
 	} else {
 %> <a href="#"><span class='lnr lnr-thumbs-up blue'></span></a> <%
 	}
 %>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	</section>

	<section id="reply_delete_popup">
	<div class="container">
		<div class="box">
			<c:forEach items="${replyView}" var="dto_reply_edit">
				<form name="replyEdit"
					action="reply_edit.do?reply_id=${dto_reply_edit.reply_id}"
					method="POST">
					<table>
						<tr>
							<td class="title">
								<h2>Edit Your Reply</h2>
							</td>
							<td class="back"><a href="#"> <span
									class="lnr lnr-cross"></span>
							</a></td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="content">
									<input type="text" name="reply" value="">
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
			</c:forEach>
		</div>
	</div>
	</section>




	<section id="message_edit_popup">
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
	</section>



	<section id="delete_popup">
	<div class="container">
		<table class="box">
			<tr>
				<td class="title"><h2>Delete</h2></td>
				<td class="back"><a href="message_view.do"><span
						class="lnr lnr-cross"></span></a></td>
			</tr>
			<tr>
				<td class="content" colspan="2"><h2>
						<a href="message_delete.do?topic_id=<%=topic_id%>">OK</a>
					</h2></td>
			</tr>
		</table>
	</div>
	</section>
	<%
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>
	<footer id='footer'> <%@ include file="footer.jsp"%>
	</footer>
</body>



<script type="text/javascript">
	function message_check(title, text) {
		if (title.value == 0 || text.value == 0) {
			alert("Fill in all fields");
			return;
		} else {
			alert("message created");
			document.message_create.submit();
		}
	}
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
