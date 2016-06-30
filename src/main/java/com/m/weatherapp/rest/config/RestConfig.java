package com.m.weatherapp.rest.config;


import com.m.weatherapp.rest.controller.ControllerMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ControllerMarker.class)
public class RestConfig {
}
