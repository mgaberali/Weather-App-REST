package com.m.weatherapp.rest.dto.response;

public class GetPredefinedNotesResponseDTO extends ResponseDTO {

	private String from1To10;
	private String from10To15;
	private String from15To20;
	private String above20;

	public String getFrom1To10() {
		return from1To10;
	}

	public void setFrom1To10(String from1To10) {
		this.from1To10 = from1To10;
	}

	public String getFrom10To15() {
		return from10To15;
	}

	public void setFrom10To15(String from10To15) {
		this.from10To15 = from10To15;
	}

	public String getFrom15To20() {
		return from15To20;
	}

	public void setFrom15To20(String from15To20) {
		this.from15To20 = from15To20;
	}

	public String getAbove20() {
		return above20;
	}

	public void setAbove20(String above20) {
		this.above20 = above20;
	}

}
