package com.m.weatherapp.rest.dto.response;

public class ResponseDTO {
	
	private String responseMessage;

	public ResponseDTO() {
	}
	
	public ResponseDTO(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
}
