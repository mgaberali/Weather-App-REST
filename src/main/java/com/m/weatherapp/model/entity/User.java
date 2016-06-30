package com.m.weatherapp.model.entity;

import javax.persistence.*;


@Entity
@Table(name = "user", catalog = "weatherdb")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements java.io.Serializable {

	private String userEmail;
	private String userName;
	private String userPassword;
	private String sessionId;
	
	public User() {
	}

	public User(String userEmail, String userName, String userPassword) {
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	@Id
	@Column(name = "user_email", unique = true, nullable = false, length = 50)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_password", nullable = false, length = 100)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Column(name = "user_session_id", unique = true, nullable = true, length = 50)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

}
