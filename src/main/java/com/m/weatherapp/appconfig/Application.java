package com.m.weatherapp.appconfig;

import com.m.weatherapp.aspect.AspectPackageMarker;
import com.m.weatherapp.model.config.ModelConfig;
import com.m.weatherapp.rest.config.RestConfig;
import com.m.weatherapp.service.config.ServiceConfig;
import com.m.weatherapp.rest.controller.ControllerMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({ ServiceConfig.class, SecurityConfig.class, ModelConfig.class, RestConfig.class})
@ComponentScan(basePackageClasses = {AspectPackageMarker.class})
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost/WeatherApp");
			}
		};
	}

}
