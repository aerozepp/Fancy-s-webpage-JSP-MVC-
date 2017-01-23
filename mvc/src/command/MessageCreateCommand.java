package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

public class MessageCreateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub	
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String textarea = request.getParameter("textarea");
		String author = (String)session.getAttribute("loggedInuser");
		
		System.out.println("user creating: " + author);
		
		Dao dao = new Dao();
		dao.createMessage(author, title, textarea);	
		
		
	}
}
