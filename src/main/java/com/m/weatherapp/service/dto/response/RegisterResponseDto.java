package com.m.weatherapp.service.dto.response;

public class RegisterResponseDto {
	
	public enum RegisterResponse{
		DONE,
		EMAIL_IS_ALREADY_EXISTS
	}
	
	private RegisterResponse response;

	public RegisterResponse getResponse() {
		return response;
	}

	public void setResponse(RegisterResponse response) {
		this.response = response;
	}
	

}
