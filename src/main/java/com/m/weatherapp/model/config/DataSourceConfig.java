package com.m.weatherapp.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource jdbcDriverDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/weatherdb");
		ds.setUsername("weatherdb_admin");
		ds.setPassword("123456");
		return ds;
	}
	
}
