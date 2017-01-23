package dto;

import java.sql.Timestamp;

public class Dto_Topics {

	int topic_id;
	String authors;
	String titles;
	String texts;
	Timestamp topicDate;
	int likes;
	int replies;
	int topic_total_number;
	int cur_page;
	int start_topic;
	
	
	
	public Dto_Topics(int topic_id, String authors, String titles, String texts, Timestamp topicDate, int likes,
			int replies, int cur_page, int start_topic) {
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
	}
	
	public Dto_Topics(){
		super();
	}
	
	public Dto_Topics(String titles, String texts){
		this.titles = titles;
		this.texts = texts;
	}

	public Dto_Topics(int topic_id, String authors, String texts, Timestamp topicDate) {
		super();
		this.topic_id = topic_id;
		this.authors = authors;
		this.texts = texts;
		this.topicDate = topicDate;
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
	public Timestamp getTopicDate() {
		return topicDate;
	}
	public void setTopicDate(Timestamp topicDate) {
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

	
}
