package com.m.weatherapp.rest.dto.request;

public class GetWeatherRequestDTO extends RequestDTO {
	
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
}
