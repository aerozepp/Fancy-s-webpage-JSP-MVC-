package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class MessageDeleteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		String topic_id = (String)request.getParameter("topic_id");
		Dao dao = new Dao();
		dao.messageDelete(topic_id);
		
		request.setAttribute("cur_page", 1);
	}	
}
