package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class MessageEditCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String topic_id = (String)request.getParameter("topic_id");
		String title = (String)request.getParameter("title");
		String textarea = (String)request.getParameter("textarea");
		
		Dao dao = new Dao();
		dao.messageEdit(topic_id, title, textarea);

	}
}
