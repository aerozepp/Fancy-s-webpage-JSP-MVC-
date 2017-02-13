package command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;


public class ReplyCreateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("command running");
		HttpSession session = request.getSession();
		
		String topic_id = (String)request.getParameter("topic_id");
		String reply = (String)request.getParameter("reply");
		String author = (String)session.getAttribute("loggedInuser");
		
		System.out.println("getting done");
		
		Dao dao = new Dao();
		dao.replyCreate(topic_id, author, reply);
		
		System.out.println("reply created");
		
	}

}
