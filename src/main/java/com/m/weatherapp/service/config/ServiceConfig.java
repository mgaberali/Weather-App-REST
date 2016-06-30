package com.m.weatherapp.service.config;

import com.m.weatherapp.service.service.ServicesMarker;
import com.m.weatherapp.service.util.WeatherApiConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses=ServicesMarker.class)
public class ServiceConfig {
	
	@Bean
	public WeatherApiConf weatherApiConf(){
		WeatherApiConf weatherApiConf = new WeatherApiConf();
		weatherApiConf.setApiKey("a4ae2fd944bec7f3f981ea7c91afd72f");
		return weatherApiConf;
	}
	
}
