package com.climbup.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;

	private LocalDate dueDate;
	
	private boolean completed;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Task() {
		
	}
	
	public Task(String title, LocalDate dueDate, boolean completed, User user) {
		this.title = title;
		this.dueDate = dueDate;
		this.completed = completed;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate localDate) {
		this.dueDate = localDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

}
