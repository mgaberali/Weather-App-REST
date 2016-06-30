package com.m.weatherapp.service.dto;

import java.util.Date;

public class NoteDto {
	
	private Integer noteId;
	private Date noteDate;
	private CityDto cityDto;
	private String adminUserEmail;
	private String noteText;
	private Float temperature;
	
	
	
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	public Date getNoteDate() {
		return new Date(noteDate.getTime());
	}
	public void setNoteDate(Date noteDate) {
		this.noteDate = new Date(noteDate.getTime());
	}
	public CityDto getCityDto() {
		return cityDto;
	}
	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}
	public String getAdminUserEmail() {
		return adminUserEmail;
	}
	public void setAdminUserEmail(String adminUserEmail) {
		this.adminUserEmail = adminUserEmail;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public Float getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	

}
