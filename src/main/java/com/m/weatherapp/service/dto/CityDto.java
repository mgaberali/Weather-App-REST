package com.m.weatherapp.service.dto;

public class CityDto {
	
	private Integer cityId;
	private String CityName;

	
	public CityDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CityDto(String cityName) {
		super();
		CityName = cityName;
	}

	
	
	public CityDto(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		CityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	
	
}
