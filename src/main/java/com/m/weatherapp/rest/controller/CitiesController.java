package com.m.weatherapp.rest.controller;

import com.m.weatherapp.rest.dto.request.AddCityRequestDTO;
import com.m.weatherapp.rest.dto.response.GetAllCitiesResponseDTO;
import com.m.weatherapp.rest.dto.response.ResponseDTO;
import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetAllCitiesResponseDTO> viewCities() {

		// get all cities
		List<CityDto> citiesList = adminUserService.getAllCities();
		
		// create response dto
		GetAllCitiesResponseDTO responseDTO = new GetAllCitiesResponseDTO();
		responseDTO.setCities(citiesList);
		
		// create response entity with list of cities
		ResponseEntity<GetAllCitiesResponseDTO> responseEntity = new ResponseEntity<GetAllCitiesResponseDTO>(responseDTO, HttpStatus.OK);

		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO> addCitY(@RequestBody AddCityRequestDTO addCityRequestDTO) {
		
		// validate
		if (addCityRequestDTO.getCityName().equals(""))
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("City name is required"), HttpStatus.BAD_REQUEST);

		// add city
		boolean cityIsAdded = adminUserService.addCity(new CityDto(addCityRequestDTO.getCityName()));

		if (!cityIsAdded) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("City is already exists"), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}

}