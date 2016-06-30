package com.m.weatherapp.model.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "admin_user", catalog = "weatherdb")
@PrimaryKeyJoinColumn(name="user_email")  
public class AdminUser extends User implements java.io.Serializable {

	private Set<Note> notes;

	public AdminUser() {
	}
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adminUser", targetEntity = Note.class)
	public Set getNotes() {
		return this.notes;
	}

	public void setNotes(Set notes) {
		this.notes = notes;
	}

}
