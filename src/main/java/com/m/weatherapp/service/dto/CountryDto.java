package com.m.weatherapp.service.dto;

public class CountryDto {

	private Float message;
	private String country;
	private Long sunrise;
	private Long sunset;
	
	public Float getMessage() {
		return message;
	}
	public void setMessage(Float message) {
		this.message = message;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getSunrise() {
		return sunrise;
	}
	public void setSunrise(Long sunrise) {
		this.sunrise = sunrise;
	}
	public Long getSunset() {
		return sunset;
	}
	public void setSunset(Long sunset) {
		this.sunset = sunset;
	}
	
	
}
