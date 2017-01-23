package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.Dto_Topics;

public class Dao {

	public void registration(String username, String password) {

		PreparedStatement preparedStatement = null;
		Connection connection = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		String query = "INSERT INTO users VALUES(users_seq.nextval, ?, ?, SYSDATE, '')";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	} // registration

	public String login(String input_username, String input_password) {

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		String user_password = null;
		int number_of_row = 0;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {

			String query = "SELECT passwords FROM users WHERE usernames = ?";
			String access_update_query = "UPDATE users SET lastaccess = SYSDATE WHERE usernames = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, input_username);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				number_of_row++;

				if (number_of_row != 0) {
					System.out.println("row found");
					user_password = resultSet.getString("passwords");
					System.out.println("found password : " + user_password);

					preparedStatement = connection.prepareStatement(access_update_query);
					preparedStatement.setString(1, input_username);
					preparedStatement.executeUpdate();

				} else {
					user_password = null;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return user_password;
	} // login

	public void createMessage(String author, String title, String text) {

		PreparedStatement preparedStatement = null;
		Connection connection = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		System.out.println("SQL connected");

		try {

			String query = "INSERT INTO topics VALUES(topics_seq.nextval, ?, ?, ?, SYSDATE, 0, 0)";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, author);
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, text);
			preparedStatement.executeUpdate();

			System.out.println("query executed");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	} // message create

	public ArrayList<Dto_Topics> messageView(String cur_page) {

		ArrayList<Dto_Topics> dtos = new ArrayList<Dto_Topics>();

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

	
		String page_index = cur_page;
		int current_page;
		int start_topic;
		int end_topic;

		if (page_index == null) {
			current_page = 1;
			System.out.println("cur_page: " + current_page);
		} else {
			current_page = Integer.parseInt(page_index);
			System.out.println("cur_page(int): " + current_page);
		}

		if (current_page > 1) {
			start_topic = (current_page * 6) - 6;

			System.out.println("start_topic: " + start_topic);
		} else {
			start_topic = 0;
			System.out.println("start_topic: " + start_topic);
		}

		end_topic = start_topic + 6;

		try {

			String query = "Select * from (select rownum numrow, aa.* from (select * from topics order by topic_id desc) aa ) where numrow > ? and numrow <= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, start_topic);
			preparedStatement.setInt(2, end_topic);
			resultSet = preparedStatement.executeQuery();

			System.out.println("query executed");

			while (resultSet.next()) {

				int topic_id = resultSet.getInt("topic_id");
				String authors = resultSet.getString("authors");
				String titles = resultSet.getString("titles");
				String texts = resultSet.getString("texts");
				Timestamp topicDate = resultSet.getTimestamp("topicDate");
				int likes = resultSet.getInt("likes");
				int replies = resultSet.getInt("replies");

				Dto_Topics dto = new Dto_Topics(topic_id, authors, titles, texts, topicDate, likes, replies, current_page, start_topic);
				dtos.add(dto);
				System.out.println("message viewed");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				System.out.println("resultset closed");
				if (preparedStatement != null)
					preparedStatement.close();
				System.out.println("preparedStatement closed");
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("returning dtos");
		return dtos;
	} // message_view
	
	public Dto_Topics contentView(String topic_id) {

		System.out.println("============contectView");
		Dto_Topics dto = new Dto_Topics();

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		int topic_index = Integer.parseInt(topic_id);

		try {
			String query = "SELECT * FROM topics WHERE topic_id = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, topic_index);
			resultSet = preparedStatement.executeQuery();
			System.out.println("query executed");

			while (resultSet.next()) {

				int topics_id = resultSet.getInt("topic_id");
				String authors = resultSet.getString("authors");
				String titles = resultSet.getString("titles");
				String texts = resultSet.getString("texts");
				Timestamp topicDate = resultSet.getTimestamp("topicDate");
				int likes = resultSet.getInt("likes");
				int replies = resultSet.getInt("replies");

				dto = new Dto_Topics(topics_id, authors, titles, texts, topicDate, likes, replies, 0, 0);

				System.out.println("content viewed");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				System.out.println("resultset closed");
				if (preparedStatement != null)
					preparedStatement.close();
				System.out.println("preparedStatement closed");
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return dto;
	} // contentView

	public void messageEdit(String topic_id, String title, String textarea) {

		int topic_index = Integer.parseInt(topic_id);
		String titles = title;
		String texts = textarea;

		PreparedStatement preparedStatement = null;
		Connection connection = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {
			String query = "UPDATE topics SET titles= ?, texts=? WHERE topic_id= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, titles);
			preparedStatement.setString(2, texts);
			preparedStatement.setInt(3, topic_index);
			preparedStatement.executeUpdate();
			System.out.println("query updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// messageEdit

	public void messageDelete(String topic_id) {

		int topic_index = Integer.parseInt(topic_id);

		PreparedStatement preparedStatement = null;
		Connection connection = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {
			String query = "DELETE FROM topics WHERE topic_id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, topic_index);
			preparedStatement.executeUpdate();
			System.out.println("query updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}// message_delete

	public void replyCreate(String topic_id, String author, String reply) {

		int topic_index = Integer.parseInt(topic_id);
		String authors = author;
		String replies = reply;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		Connection connection = null;
	

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		
		System.out.println(topic_index);
		System.out.println(replies);
		System.out.println(authors);
		

		try {
			String query = "INSERT INTO replies VALUES(replies_seq.nextval, ?, ?, SYSDATE, ?)";
			
				String query_replyCount = "UPDATE topics SET replies = replies +1 WHERE topic_id = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, authors);
			preparedStatement.setString(2, replies);
			preparedStatement.setInt(3, topic_index);
			preparedStatement.executeUpdate();
			System.out.println("query executed");

			preparedStatement2 = connection.prepareStatement(query_replyCount);
			preparedStatement2.setInt(1, topic_index);
			preparedStatement2.executeUpdate();
			System.out.println("query2 updated");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			/*	if (preparedStatement2 != null)
					preparedStatement2.close();*/
				if (connection != null)
					connection.close();
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// reply_create

	public ArrayList<Dto_Topics> replyView(String topic_id) {

		System.out.println("============replyView");
		Dto_Topics dto = new Dto_Topics();
		ArrayList<Dto_Topics> dtos = new ArrayList<Dto_Topics>();

		int topic_index = Integer.parseInt(topic_id);

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {
			String query_view = "SELECT * FROM (select rownum numrow, aa.* from (select * from replies order by reply_id desc) aa ) WHERE topic_id = ?";
			preparedStatement = connection.prepareStatement(query_view);
			preparedStatement.setInt(1, topic_index);
			resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {

				int rep_id = resultSet.getInt("reply_id");
				String authors = resultSet.getString("authors");
				String texts = resultSet.getString("texts");
				Timestamp rep_date = resultSet.getTimestamp("repdate");

				System.out.println("rep_id : "+rep_id);
				System.out.println("authors : "+authors);
				System.out.println("texts : " + texts);
				
				dto = new Dto_Topics(rep_id, authors, texts, rep_date);
				dtos.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
}
