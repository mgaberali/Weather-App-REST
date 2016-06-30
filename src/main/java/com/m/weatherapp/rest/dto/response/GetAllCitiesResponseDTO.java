package com.m.weatherapp.rest.dto.response;

import com.m.weatherapp.service.dto.CityDto;

import java.util.List;

public class GetAllCitiesResponseDTO extends ResponseDTO {
	
	private List<CityDto> cities;

	public List<CityDto> getCities() {
		return cities;
	}

	public void setCities(List<CityDto> cities) {
		this.cities = cities;
	}
	
	
}
