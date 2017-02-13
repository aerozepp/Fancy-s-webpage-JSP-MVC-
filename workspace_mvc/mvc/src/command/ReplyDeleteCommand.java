package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class ReplyDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String reply_id = (String)request.getParameter("reply_id");
		String topic_id = (String)request.getParameter("topic_id");
		
		Dao dao = new Dao();
		
		dao.replyDelete(reply_id, topic_id);
		
	}

}
