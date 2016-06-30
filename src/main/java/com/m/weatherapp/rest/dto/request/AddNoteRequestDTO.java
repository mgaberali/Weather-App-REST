package com.m.weatherapp.rest.dto.request;

public class AddNoteRequestDTO extends RequestDTO {

	private String note;
	private String city;
	private int temp;
	private String adminUserEmail;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public String getAdminUserEmail() {
		return adminUserEmail;
	}

	public void setAdminUserEmail(String adminUserEmail) {
		this.adminUserEmail = adminUserEmail;
	}
	

}
