package com.m.weatherapp.service.service;

import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.WeatherTodayDto;
import com.m.weatherapp.service.dto.response.LoginResponseDto;
import com.m.weatherapp.service.dto.response.LogoutResponseDto;

import java.util.List;

public interface UserService {
	
	/**
	 * to login with email and password and get user info
	 * @param email
	 * @param password
	 * @return login response (USER_FOUND, USER_NOT_FOUND, INVALID_EMAIL_AND_PASSWORD) and user info
	 */
	LoginResponseDto login(String email, String password);
	
	/**
	 * to logout
	 * @param sessionId
	 * @return LogoutResponseDto
	 */
	LogoutResponseDto logout(String sessionId);
	
	/**
	 * to search all city by char or name
	 * @param searchName
	 * @return list of CityDto
	 */
	List<CityDto> searchCitiesByName(String searchName);
	
	/**
	 * to get weather today status and if there is any note was set by admin 
	 * @param cityDto
	 * @return WeatherTodayDto 
	 */
	WeatherTodayDto checkWeatherToday(CityDto cityDto);
	
	/**
	 * to find city by name
	 * @param searchName
	 * @return CityDto
	 */
	CityDto findCityByName(String searchName);
	
	/**
	 * check user is still in session
	 * @param sessionId
	 * @return true or false
	 */
	boolean checkUserHasSession(String sessionId);

}
