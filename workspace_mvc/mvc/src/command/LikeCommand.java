package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

public class LikeCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		String author = (String)session.getAttribute("loggedInuser");
		String topic_id = (String)request.getParameter("topic_id");

		System.out.println("author : "+author);
		System.out.println("topic_id : "+ topic_id);
		Dao dao = new Dao();
		dao.like(topic_id, author);
	}
}
