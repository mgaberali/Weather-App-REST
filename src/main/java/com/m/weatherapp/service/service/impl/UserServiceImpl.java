package com.m.weatherapp.service.service.impl;

import com.m.weatherapp.model.entity.*;
import com.m.weatherapp.model.repository.CityRepo;
import com.m.weatherapp.model.repository.NoteRepo;
import com.m.weatherapp.model.repository.PredefinedNoteRepo;
import com.m.weatherapp.model.repository.UserRepo;
import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.WeatherInfoDto;
import com.m.weatherapp.service.dto.WeatherTodayDto;
import com.m.weatherapp.service.dto.response.LoginResponseDto;
import com.m.weatherapp.service.dto.response.LoginResponseDto.LoginResponse;
import com.m.weatherapp.service.dto.response.LogoutResponseDto;
import com.m.weatherapp.service.dto.user.AdminUserDto;
import com.m.weatherapp.service.dto.user.EmployeeUserDto;
import com.m.weatherapp.service.service.UserService;
import com.m.weatherapp.service.util.WeatherApiConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private NoteRepo noteRepo;

	@Autowired
	private PredefinedNoteRepo predefinedNoteRepo;

	@Autowired
	private WeatherApiConf weatherApiConf;
	
	public LoginResponseDto login(String email, String password) {

		// create response dto
		LoginResponseDto loginResponseDto = new LoginResponseDto();

		// find user by email
		User user = userRepo.findByUserEmail(email);

		// if user not found return user not found response
		if (user == null) {
			loginResponseDto.setResponse(LoginResponse.USER_NOT_FOUND);
			return loginResponseDto;
		}
		
		
		// if password is invalid return invalid email or password response
		if (!password.equals(user.getUserPassword())) {
			loginResponseDto.setResponse(LoginResponse.INVALID_EMAIL_OR_PASSWORD);
			return loginResponseDto;
		}
		
		// create session id
		String sessionId = UUID.randomUUID().toString();
		
		// set user session id
		user.setSessionId(sessionId);
		
		// create user dto based on user type
		if (user instanceof EmployeeUser) {

			EmployeeUserDto employeeUserDto = new EmployeeUserDto();
			employeeUserDto.setUserEmail(email);
			employeeUserDto.setUserName(user.getUserName());
			employeeUserDto.setUserMobile(((EmployeeUser) user).getUserMobile());
			employeeUserDto.setSessionId(sessionId);
			
			loginResponseDto.setUserDto(employeeUserDto);

		} else if (user instanceof AdminUser) {

			AdminUserDto adminUserDto = new AdminUserDto();
			adminUserDto.setUserEmail(email);
			adminUserDto.setUserName(user.getUserName());
			adminUserDto.setSessionId(sessionId);
			
			loginResponseDto.setUserDto(adminUserDto);
		}

		// set response is USER_FOUND
		loginResponseDto.setResponse(LoginResponse.USER_FOUND);

		return loginResponseDto;
	}
	
	@Secured("ROLE_USER")
	public LogoutResponseDto logout(String sessionId) {
		User user = userRepo.findBySessionId(sessionId);
		user.setSessionId(null);
		
		return null;
	}
	
	public List<CityDto> searchCitiesByName(String searchName) {

		List<CityDto> citiesList = new ArrayList<CityDto>();

		// get all city entities
		List resultCities = cityRepo.findByCityNameLike("%"+searchName+"%");

		for (Object obj : resultCities) {

			City city = (City) obj;

			// create city dto and add it to cities list
			CityDto cityDto = new CityDto();
			cityDto.setCityId(city.getCityId());
			cityDto.setCityName(city.getCityName());
			citiesList.add(cityDto);
		}

		return citiesList;

	}
	
	@Secured("ROLE_USER")
	public WeatherTodayDto checkWeatherToday(CityDto cityDto) {

		// create response
		WeatherTodayDto weatherTodayDto = new WeatherTodayDto();

		// get Temp from Weather API
		float temp = getWeatherTempForCity(cityDto.getCityName());
		
		// if temp not found return null
		if(temp == 99.99f)
			return null;

		// create city entity
		City city = new City();
		city.setCityId(cityDto.getCityId());
		city.setCityName(cityDto.getCityName());

		// get weather today note for this city
		Note note = noteRepo.findByNoteDateAndCity(Calendar.getInstance().getTime(), city);

		if (note != null) { // if note found on this city today it will be set
							// to response

			weatherTodayDto.setNote(note.getNoteText());
			weatherTodayDto.setPredefinedNote(false);

		} else if(temp >= 1) { // user the predefined notes

			PredefinedNote predefinedNote = predefinedNoteRepo.findByMinTempLessThanAndMaxTempGreaterThan(temp);
			weatherTodayDto.setNote(predefinedNote.getPredefinedNoteText());
			weatherTodayDto.setPredefinedNote(true);
		}

		// set weather dto the rest of data
		weatherTodayDto.setCityDto(cityDto);
		weatherTodayDto.setTemp(temp);

		return weatherTodayDto;
	}
	
	@Secured("ROLE_USER")
	private float getWeatherTempForCity(String cityName) {
		
		RestTemplate rest = new RestTemplate();
		
		WeatherInfoDto weatherInfoDto = null;		
		
		try {
			weatherInfoDto = rest.getForObject("http://api.openweathermap.org/data/2.5/weather?q={city}&units={unit}&appid={appid}",
					WeatherInfoDto.class, cityName, "metric", weatherApiConf.getApiKey());
			
		} catch (RestClientException e) {
			return 99.99f;
		}
		
		return weatherInfoDto.getMain().getTemp();
	}
	
	@Secured("ROLE_USER")
	public CityDto findCityByName(String searchName) {

		City city = cityRepo.findByCityName(searchName);

		// if city not found
		if (city == null)
			return null;

		// create city dto
		CityDto cityDto = new CityDto();
		cityDto.setCityId(city.getCityId());
		cityDto.setCityName(city.getCityName());

		return cityDto;
	}

	public boolean checkUserHasSession(String sessionId) {
		
		User user = userRepo.findBySessionId(sessionId);
		
		if(user != null)
			return true;
		else
			return false;
		
	}

}
