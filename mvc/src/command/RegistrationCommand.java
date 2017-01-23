package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class RegistrationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");

		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("password_confirm : " + password_confirm);

		Dao dao = new Dao();
		dao.registration(username, password);
		System.out.println("registration worked well");
		request.setAttribute("regi_completed", username);

	}
}
