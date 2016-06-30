package com.m.weatherapp.rest.dto.response;

public class LogoutResponseDTO extends ResponseDTO {
	
	private boolean logout;

	public boolean isLogout() {
		return logout;
	}

	public void setLogout(boolean logout) {
		this.logout = logout;
	}
	
	
}
