package command;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.Dto_Topics;

public class MessageViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String cur_page = (String) request.getParameter("cur_page");
		String user = (String) session.getAttribute("loggedInuser");
		String topic_id = null;
		String is_liked = null;
		String is_replied = null;
		Dao dao = new Dao();

		Dto_Topics dto;
		ArrayList<Dto_Topics> dtos = dao.messageView(cur_page);
		
		ArrayList<Dto_Topics> dtos2 = new ArrayList<>();
		int topicNum = dtos.size();

		Iterator<Dto_Topics> itr = dtos.iterator();

		while (itr.hasNext()) {

			dto = (Dto_Topics) itr.next();

			topic_id = String.valueOf(dto.getTopic_id());

			is_liked = dao.likeCheck(topic_id, user);
			is_replied = dao.replyCheck(topic_id, user);

			if (is_liked.equals("true"))
				dto.setIs_liked(true);
			else
				dto.setIs_liked(false);

			if (is_replied.equals("true"))
				dto.setIs_replied(true);
			else
				dto.setIs_replied(false);

			System.out.println("dto.topic_id : " + dto.getTopic_id());
			System.out.println("dto.is_replied : " + dto.isIs_replied());
			System.out.println("dto.is_liked : " + dto.isIs_liked());
			
			
			dtos2.add(dto);

		}

		System.out.println(topicNum);
		System.out.println("message Dto created");

		request.setAttribute("messageView", dtos2);
		request.setAttribute("topicNum", topicNum);
		request.setAttribute("cur_page", cur_page);

	}

}
