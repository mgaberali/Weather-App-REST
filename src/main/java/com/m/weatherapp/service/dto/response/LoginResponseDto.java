package com.m.weatherapp.service.dto.response;

import com.m.weatherapp.service.dto.user.UserDto;

public class LoginResponseDto {
	
	public enum LoginResponse{
		USER_FOUND,
		USER_NOT_FOUND,
		INVALID_EMAIL_OR_PASSWORD
	}

	private LoginResponse response;
	private UserDto usDto;
	
	public LoginResponse getResponse() {
		return response;
	}
	public void setResponse(LoginResponse response) {
		this.response = response;
	}
	public UserDto getUsDto() {
		return usDto;
	}
	public void setUserDto(UserDto usDto) {
		this.usDto = usDto;
	}
	
	
	
}
