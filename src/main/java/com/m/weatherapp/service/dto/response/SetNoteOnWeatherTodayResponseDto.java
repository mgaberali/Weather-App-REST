package com.m.weatherapp.service.dto.response;

public class SetNoteOnWeatherTodayResponseDto {
	
	public enum SetNoteOnWeatherResponse{
		DONE,
		NOTE_IS_ALREADY_SETTED_TODAY_FOR_THIS_CITY
	}

	private SetNoteOnWeatherResponse response;

	public SetNoteOnWeatherResponse getResponse() {
		return response;
	}

	public void setResponse(SetNoteOnWeatherResponse response) {
		this.response = response;
	}
	
	
	
}
