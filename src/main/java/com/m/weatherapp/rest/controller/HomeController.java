package com.m.weatherapp.rest.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.m.weatherapp.rest.dto.request.AddNoteRequestDTO;
import com.m.weatherapp.rest.dto.request.GetWeatherRequestDTO;
import com.m.weatherapp.rest.dto.response.GetWeahterResponseDTO;
import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.NoteDto;
import com.m.weatherapp.service.dto.WeatherTodayDto;
import com.m.weatherapp.service.service.AdminUserService;
import com.m.weatherapp.service.service.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@RestController
public class HomeController {

	@Autowired
	private EmployeeUserService userService;

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(value = "/api/searchCities", method = RequestMethod.GET)
	public String searchCitiesByName(@RequestParam(name = "term") String term, HttpSession session) {

		List<CityDto> resultCitiesList = userService.searchCitiesByName(term);

		JsonArray jsonArray = new JsonArray();

		for (CityDto cityDto : resultCitiesList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("value", cityDto.getCityName());
			jsonObject.addProperty("data", cityDto.getCityId().toString());
			jsonArray.add(jsonObject);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("suggestions", jsonArray);

		return jsonObject.toString();
	}

	@RequestMapping(value = "api/weather", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetWeahterResponseDTO> checkWeather(@RequestBody GetWeatherRequestDTO getWeatherRequestDTO) {

		// get city info by name
		CityDto cityDto = userService.findCityByName(getWeatherRequestDTO.getCityName());
		
		// check if city not found
		if(cityDto == null)
			return new ResponseEntity<GetWeahterResponseDTO>(HttpStatus.BAD_REQUEST);

		// call check weather service
		WeatherTodayDto weatherTodayDto = userService.checkWeatherToday(cityDto);
		
		// check if weather info not found 
		if(weatherTodayDto == null)
			return new ResponseEntity<GetWeahterResponseDTO>(HttpStatus.BAD_REQUEST);
		
		// create model and view
		GetWeahterResponseDTO responseDTO = new GetWeahterResponseDTO();
		responseDTO.setTemperature((int) weatherTodayDto.getTemp());
		responseDTO.setCity(weatherTodayDto.getCityDto().getCityName());
		responseDTO.setNote(weatherTodayDto.getNote());
		responseDTO.setPredefinedNote(weatherTodayDto.isPredefinedNote());
		
		return new ResponseEntity<GetWeahterResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addNote", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNote(@RequestBody AddNoteRequestDTO requestDTO) {

		// get admin user email
		String adminUserEmail = requestDTO.getAdminUserEmail();

		// get city info by name
		CityDto cityDto = userService.findCityByName(requestDTO.getCity());

		// create Note Dto
		NoteDto noteDto = new NoteDto();
		noteDto.setAdminUserEmail(adminUserEmail);
		noteDto.setCityDto(cityDto);
		noteDto.setNoteDate(Calendar.getInstance().getTime());
		noteDto.setNoteText(requestDTO.getNote());
		noteDto.setTemperature(new Float(requestDTO.getTemp()));

		// add note
		adminUserService.setNoteOnWeatherToday(noteDto);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
