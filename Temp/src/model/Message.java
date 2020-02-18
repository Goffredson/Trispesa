package model;

import java.time.LocalDate;

public class Message {
    private String from;
    private String to;
    private String content;
    private LocalDate date;
    
    public LocalDate getDate() {
		return date;
	}
    public void setDate(LocalDate date) {
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