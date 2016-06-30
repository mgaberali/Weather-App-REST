package com.m.weatherapp.service.dto;

public class WeatherTodayDto {
	
	private float temp;
	private CityDto cityDto;
	private String note;
	private boolean predefinedNote;
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public CityDto getCityDto() {
		return cityDto;
	}
	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isPredefinedNote() {
		return predefinedNote;
	}
	public void setPredefinedNote(boolean predefinedNote) {
		this.predefinedNote = predefinedNote;
	}
	
	
	
}
