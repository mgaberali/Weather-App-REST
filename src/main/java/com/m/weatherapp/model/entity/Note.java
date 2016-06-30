package com.m.weatherapp.model.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "note", catalog = "weatherdb")
public class Note implements java.io.Serializable {

	private Integer noteId;
	private Date noteDate;
	private AdminUser adminUser;
	private City city;
	private String noteText;
	private Float temperature;

	public Note() {
	}

	public Note(Date noteDate, AdminUser adminUser, City city, String noteText) {
		this.noteDate = new Date(noteDate.getTime());
		this.adminUser = adminUser;
		this.city = city;
		this.noteText = noteText;
	}

	public Note(Date noteDate, AdminUser adminUser, City city, String noteText, Float temperature) {
		this.noteDate = new Date(noteDate.getTime());
		this.adminUser = adminUser;
		this.city = city;
		this.noteText = noteText;
		this.temperature = temperature;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id", unique = true, nullable = false, length = 9)
	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "note_date", unique = true, nullable = false, length = 10)
	public Date getNoteDate() {
		return new Date(this.noteDate.getTime());
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = new Date(noteDate.getTime());
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_user_email", nullable = false)
	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "note_text", nullable = false, length = 30)
	public String getNoteText() {
		return this.noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	@Column(name = "temperature", precision = 12, scale = 0)
	public Float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

}
