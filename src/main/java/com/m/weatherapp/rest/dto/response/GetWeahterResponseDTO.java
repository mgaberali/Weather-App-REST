package com.m.weatherapp.rest.dto.response;

public class GetWeahterResponseDTO extends ResponseDTO {
	
	private int temperature;
	private String city;
	private String note;
	private boolean isPredefinedNote;
	
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isPredefinedNote() {
		return isPredefinedNote;
	}
	public void setPredefinedNote(boolean isPredefinedNote) {
		this.isPredefinedNote = isPredefinedNote;
	}
	
	
	
}
