package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		String input_username = request.getParameter("username");
		String input_password = request.getParameter("password");
		String user_password;
		
		System.out.println("input username : " + input_username);
		System.out.println("input password : " + input_password);
		
		Dao dao = new Dao();
		user_password = dao.login(input_username, input_password);
		
		System.out.println("requested password : " + user_password);
		
		if(input_password.equals(user_password)){
			System.out.println("login worked well");
			session.setAttribute("loggedInuser", input_username);
			
		}else{
			System.out.println("password unmatched");
			request.setAttribute("wrong_pass", input_password);
		}
		
		
	}

}
