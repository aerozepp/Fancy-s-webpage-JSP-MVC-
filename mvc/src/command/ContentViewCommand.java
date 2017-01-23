package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto_Topics;

public class ContentViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	String topic_id = request.getParameter("topic_id");	
		
	Dao dao = new Dao();
	
	ArrayList<Dto_Topics> dto_reply = dao.replyView(topic_id);
	
	Dto_Topics dto = dao.contentView(topic_id);
	System.out.println("contentView dao");
	
	System.out.println("Content viewed set up");

	request.setAttribute("contentView", dto);
	request.setAttribute("replyView", dto_reply);
		
	}

}
