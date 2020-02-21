package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
	private String from;
	private String to;
	private String content;
	private String date;

	public Message() {
		date = new SimpleDateFormat("HH:mm").format(new Date());
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}