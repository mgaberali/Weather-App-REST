package com.m.weatherapp.rest.dto.request;

public class AddCityRequestDTO extends RequestDTO {

	private Integer cityId;
	private String CityName;

	public AddCityRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public AddCityRequestDTO(String cityName) {
		super();
		CityName = cityName;
	}

	public AddCityRequestDTO(Integer cityId, String cityName) {
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
