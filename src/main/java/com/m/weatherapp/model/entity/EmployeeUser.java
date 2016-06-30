package com.m.weatherapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "employee_user", catalog = "weatherdb")
@PrimaryKeyJoinColumn(name="user_email")  
public class EmployeeUser extends User implements java.io.Serializable {

	private String userMobile;

	public EmployeeUser() {
	}

	@Column(name = "user_mobile", length = 20)
	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

}
