package dto;

import java.sql.Date;

public class Dto_Topics {

	int topic_id;
	String authors;
	String titles;
	String texts;
	Date topicDate;
	int likes;
	int replies;
	int topic_total_number;
	int cur_page;
	int start_topic;
	boolean is_liked;
	boolean is_replied;
	String imgName;
	
	
	
	
	public Dto_Topics(int topic_id, String authors, String titles, String texts, Date topicDate, int likes, int replies,
			int topic_total_number, int cur_page, int start_topic, boolean is_liked, boolean is_replied,
			String imgName) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.titles = titles;
		this.texts = texts;
		this.topicDate = topicDate;
		this.likes = likes;
		this.replies = replies;
		this.topic_total_number = topic_total_number;
		this.cur_page = cur_page;
		this.start_topic = start_topic;
		this.is_liked = is_liked;
		this.is_replied = is_replied;
		this.imgName = imgName;
	}

	public Dto_Topics(int topic_id, String authors, String titles, String texts, Date topicDate, int likes,
			int replies, int topic_total, int cur_page, int start_topic, String imgName) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.titles = titles;
		this.texts = texts;
		this.topicDate = topicDate;
		this.likes = likes;
		this.replies = replies;
		this.topic_total_number = topic_total;
		this.cur_page = cur_page;
		this.start_topic = start_topic;
		this.imgName = imgName;
	}
	
	public Dto_Topics(int topic_id, String authors, String titles, String texts, Date topicDate, int likes,
			int replies, int topic_total, int cur_page, int start_topic) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.titles = titles;
		this.texts = texts;
		this.topicDate = topicDate;
		this.likes = likes;
		this.replies = replies;
		this.topic_total_number = topic_total;
		this.cur_page = cur_page;
		this.start_topic = start_topic;
	}

	public Dto_Topics(int topic_id, String authors, String titles, String texts, Date topicDate, int likes,
			int replies, int cur_page, int start_topic, String imgName) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.titles = titles;
		this.texts = texts;
		this.topicDate = topicDate;
		this.likes = likes;
		this.replies = replies;
		this.cur_page = cur_page;
		this.start_topic = start_topic;
		this.imgName = imgName;
	}

	public Dto_Topics(){
		super();
	}
	
	public Dto_Topics(String titles, String texts){
		this.titles = titles;
		this.texts = texts;
	}

	public Dto_Topics(int topic_id, String authors, String texts, Date topicDate, String imgName) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.texts = texts;
		this.topicDate = topicDate;
		this.imgName = imgName;
	}

	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getTexts() {
		return texts;
	}
	public void setTexts(String texts) {
		this.texts = texts;
	}
	public Date getTopicDate() {
		return topicDate;
	}
	public void setTopicDate(Date topicDate) {
		this.topicDate = topicDate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getReplies() {
		return replies;
	}
	public void setReplies(int replies) {
		this.replies = replies;
	}
	public int getTopic_total_number() {
		return topic_total_number;
	}
	public void setTopic_total_number(int topic_total_number) {
		this.topic_total_number = topic_total_number;
	}

	public boolean isIs_liked() {
		return is_liked;
	}

	public void setIs_liked(boolean is_liked) {
		this.is_liked = is_liked;
	}

	public boolean isIs_replied() {
		return is_replied;
	}

	public void setIs_replied(boolean is_replied) {
		this.is_replied = is_replied;
	}
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	

	
}
