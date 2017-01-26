package command;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Dto_Topics;

public class ContentViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	HttpSession session = request.getSession();
	
	String author = (String)session.getAttribute("loggedInuser");
	String topic_id = request.getParameter("topic_id");	
	
	String is_liked = null;
	
	Dao dao = new Dao();
	
	ArrayList<Dto_Topics> dto_reply = dao.replyView(topic_id);
	is_liked = dao.likeCheck(topic_id, author);
	
	Dto_Topics dto = dao.contentView(topic_id);
	
	request.setAttribute("contentView", dto);
	request.setAttribute("replyView", dto_reply);
	request.setAttribute("is_liked", is_liked);
	
	}

}
