package frontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.ContentViewCommand;
import command.LoginCommand;
import command.MessageCreateCommand;
import command.MessageDeleteCommand;
import command.MessageEditCommand;
import command.MessageViewCommand;
import command.RegistrationCommand;
import command.ReplyCreateCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * response.getWriter().append("Served at: ").append(request.
		 * getContextPath());
		 */
		System.out.println("==============================");
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* doGet(request, response); */
		System.out.println("==============================");
		System.out.println("doPost");
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("actionDo");

		Command command = null;
		String viewPage = null;

		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);

		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);

		String[] arrUri = uri.split("/");
		String realCommand = arrUri[arrUri.length - 1];

		System.out.println("Real Command : " + realCommand);

		if (realCommand.equals("registration.do")) {

			System.out.println("RegistrationCommand started");
			command = new RegistrationCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		} else if (realCommand.equals("login.do")) {
			System.out.println("login do started");
			command = new LoginCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		} else if (realCommand.equals("message_create.do")) {
			System.out.println("message creating started");
			command = new MessageCreateCommand();
			command.execute(request, response);
			viewPage = "message_view.do";
		} else if (realCommand.equals("message_view.do")) {
			System.out.println("message viewing started");
			command = new MessageViewCommand();
			command.execute(request, response);
			System.out.println("before transfer");
			viewPage = "forum.jsp";
		} else if (realCommand.equals("contentView.do")) {
			command = new ContentViewCommand();
			command.execute(request, response);
			viewPage = "message_view.do";
		} else if (realCommand.equals("message_edit.do")) {
			System.out.println("editing");
			command = new MessageEditCommand();
			command.execute(request, response);
			viewPage = "message_view.do";
		} else if (realCommand.equals("message_delete.do")) {
			System.out.println("deleting");
			command = new MessageDeleteCommand();
			command.execute(request, response);
			System.out.println("before tranfer");
			viewPage = "message_view.do";
		} else if (realCommand.equals("reply_create.do")) {
			System.out.println("reply_creating");
			command = new ReplyCreateCommand();
			command.execute(request, response);
			viewPage = "contentView.do";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
