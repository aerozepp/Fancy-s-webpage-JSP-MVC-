package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	} /////////////////////////////////////////////////////////////// login

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

	} //////////////////////////////////////////////////////////// message create

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
		int topic_total = 0;

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
				Date topicDate = resultSet.getDate("topicDate");
				int likes = resultSet.getInt("likes");
				int replies = resultSet.getInt("replies");
				topic_total++;

				Dto_Topics dto = new Dto_Topics(topic_id, authors, titles, texts, topicDate, likes, replies,
						topic_total, current_page, start_topic);
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
	} /////////////////////////////////////////////////////////////// message_view

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
				Date topicDate = resultSet.getDate("topicDate");
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
	} ////////////////////////////////////////////////////////////////// contentView

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
	}/////////////////////////////////////////////////////////////////// messageEdit

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

	}/////////////////////////////////////////////////////////////// message_delete

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
				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}/////////////////////////////////////////////////////////////// reply_create

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
				Date rep_date = resultSet.getDate("repdate");

				System.out.println("rep_id : " + rep_id);
				System.out.println("authors : " + authors);
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
	} // ///////////////////////////////////////////////////////////////////reply_view

	public void like(String topic_id, String author) {

		System.out.println("Dao like()");
		int topic_index = Integer.parseInt(topic_id);
		int is_started = 0;
		int is_liked = 0;

		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {
			String query_check = "SELECT * FROM likes WHERE topic_id = ? AND author = ?";

			preparedStatement = connection.prepareStatement(query_check);
			preparedStatement.setInt(1, topic_index);
			preparedStatement.setString(2, author);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				is_started = resultSet.getInt("is_started");
				is_liked = resultSet.getInt("is_liked");
			}

			System.out.println("is_started : " + is_started);
			String query = null;
			String query_count = null;

			if (is_started == 1) {

				if (is_liked == 1) {
					query = "UPDATE likes SET is_liked = 0 WHERE topic_id = ? AND author = ?";
					query_count = "UPDATE topics SET likes = likes - 1 WHERE topic_id = ?";
				} else {
					query = "UPDATE likes SET is_liked = 1 WHERE topic_id = ? AND author = ?";
					query_count = "UPDATE topics SET likes = likes + 1 WHERE topic_id = ?";
				}
			} else {
				query = "INSERT INTO likes VALUES(likes_seq.nextval, ?, ?, 1, 1)";
				query_count = "UPDATE topics SET likes = likes + 1 WHERE topic_id = ?";
			}

			System.out.println(query);

			preparedStatement2 = connection.prepareStatement(query);
			preparedStatement2.setInt(1, topic_index);
			preparedStatement2.setString(2, author);
			preparedStatement2.executeUpdate();
			System.out.println("query executed");

			preparedStatement3 = connection.prepareStatement(query_count);
			preparedStatement3.setInt(1, topic_index);
			preparedStatement3.executeUpdate();

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

	} /////////////////////////////////////////////////////////////////////////like

	public String likeCheck(String topic_id, String author) {

		int topic_index = Integer.parseInt(topic_id);
		String is_liked = "false";

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();

		try {
			String query = "SELECT * FROM likes WHERE topic_id = ? AND author = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, topic_index);
			preparedStatement.setString(2, author);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int is_like = resultSet.getInt("is_liked");

				if (is_like == 1) {
					is_liked = "true";
				} else
					is_liked = "false";
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

		System.out.println("is_liked : " + is_liked);
		return is_liked;
	} ///////////////////////////////////////////////////////////////////like check
	
	public String replyCheck(String topic_id, String author){
		
		int topic_index = Integer.parseInt(topic_id);
		String is_replied = "false";

		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;

		ConnectionToSql cts = new ConnectionToSql();
		connection = cts.getConnection();
		
		try {
			String query = "SELECT * FROM replies WHERE topic_id = ? AND authors = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, topic_index);
			preparedStatement.setString(2, author);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				is_replied = "true";
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
		
		return is_replied;
	}
}
