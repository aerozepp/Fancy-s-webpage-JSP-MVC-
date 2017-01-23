package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto_Topics;

public class MessageViewCommand implements Command {

		
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String cur_page = (String)request.getParameter("cur_page");
		
		System.out.println("cur_page : " + cur_page);
		
		Dao dao = new Dao();
	
		ArrayList<Dto_Topics> dtos = dao.messageView(cur_page);
		int topicNum = dtos.size();
		
		System.out.println(topicNum);
		System.out.println("message Dto created");
		
		request.setAttribute("messageView", dtos);
		request.setAttribute("topicNum", topicNum);
		
	}

}
