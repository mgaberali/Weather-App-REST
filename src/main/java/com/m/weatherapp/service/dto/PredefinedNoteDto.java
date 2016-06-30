package com.m.weatherapp.service.dto;

public class PredefinedNoteDto {

	private Integer id;
	private Float minTemp;
	private Float maxTemp;
	private String predefinedNoteText;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
	}
	public Float getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getPredefinedNoteText() {
		return predefinedNoteText;
	}
	public void setPredefinedNoteText(String predefinedNoteText) {
		this.predefinedNoteText = predefinedNoteText;
	}
	
	
	
}
