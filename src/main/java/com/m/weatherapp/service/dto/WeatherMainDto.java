package com.m.weatherapp.service.dto;

public class WeatherMainDto {

	private Float temp;
	private Float pressure;
	private Float humidity;
	private Float temp_min;
	private Float temp_max;
	private Float sea_level;
	private Float grnd_level;
	
	public Float getTemp() {
		return temp;
	}
	public void setTemp(Float temp) {
		this.temp = temp;
	}
	public Float getPressure() {
		return pressure;
	}
	public void setPressure(Float pressure) {
		this.pressure = pressure;
	}
	public Float getHumidity() {
		return humidity;
	}
	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}
	public Float getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Float temp_min) {
		this.temp_min = temp_min;
	}
	public Float getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Float temp_max) {
		this.temp_max = temp_max;
	}
	public Float getSea_level() {
		return sea_level;
	}
	public void setSea_level(Float sea_level) {
		this.sea_level = sea_level;
	}
	public Float getGrnd_level() {
		return grnd_level;
	}
	public void setGrnd_level(Float grnd_level) {
		this.grnd_level = grnd_level;
	}
	
	
}
