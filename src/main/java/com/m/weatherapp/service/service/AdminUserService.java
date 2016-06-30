package com.m.weatherapp.service.service;

import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.NoteDto;
import com.m.weatherapp.service.dto.PredefinedNoteDto;
import com.m.weatherapp.service.dto.response.SetNoteOnWeatherTodayResponseDto;

import java.util.List;

public interface AdminUserService extends UserService{

	/**
	 * to set note for a certian city today
	 * @param noteDto
	 * @return response (DONE, NOTE_IS_ALREADY_SETTED_TODAY)
	 */
	SetNoteOnWeatherTodayResponseDto setNoteOnWeatherToday(NoteDto noteDto);
	
	/**
	 * to get all predefined notes
	 * @return list of PredefinedNoteDto
	 */
	List<PredefinedNoteDto> getAllPredefinedNotes();
	
	/**
	 * to update the list of predefined notes
	 * @param list of PredefinedNoteDto
	 * @return true or false
	 */
	boolean setPredefinedNotesList(List<PredefinedNoteDto> list);
	
	/**
	 * to get all notes set by admin
	 * @param adminUserEmail
	 * @return list of NoteDto
	 */
	List<NoteDto> getAllNotes(String adminUserEmail);
	
	/**
	 * to add new city
	 * @param cityDto
	 * @return true or false 
	 */
	boolean addCity(CityDto cityDto);
	
	/**
	 * to delete city
	 * @param cityDto
	 * @return true or false
	 */
	boolean deleteCity(CityDto cityDto);
	
	/**
	 * to get all cities list
	 * @return list of CityDto
	 */
	List<CityDto> getAllCities();
	
}
