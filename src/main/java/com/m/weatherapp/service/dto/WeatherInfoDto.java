package com.m.weatherapp.service.dto;

import java.util.Arrays;

public class WeatherInfoDto {

	private CoordinatesDto coord;
	private WeatherDto [] weather;
	private String base;
	private WeatherMainDto main;
	private WindDto wind;
	private CloudsDto clouds;
	private Long dt;
	private CountryDto sys;
	private Long id;
	private String name;
	private Integer cod;
	
	public CoordinatesDto getCoord() {
		return coord;
	}
	public void setCoord(CoordinatesDto coord) {
		this.coord = coord;
	}
	public WeatherDto[] getWeather() {
		return Arrays.copyOf(weather, 0);
	}
	public void setWeather(WeatherDto[] weather) {
		this.weather = Arrays.copyOf(weather, 0);

	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public WeatherMainDto getMain() {
		return main;
	}
	public void setMain(WeatherMainDto main) {
		this.main = main;
	}
	public WindDto getWind() {
		return wind;
	}
	public void setWind(WindDto wind) {
		this.wind = wind;
	}
	public CloudsDto getClouds() {
		return clouds;
	}
	public void setClouds(CloudsDto clouds) {
		this.clouds = clouds;
	}
	public Long getDt() {
		return dt;
	}
	public void setDt(Long dt) {
		this.dt = dt;
	}
	public CountryDto getSys() {
		return sys;
	}
	public void setSys(CountryDto sys) {
		this.sys = sys;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	
	
	
}
