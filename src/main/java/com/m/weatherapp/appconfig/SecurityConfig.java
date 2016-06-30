package com.m.weatherapp.appconfig;

import com.m.weatherapp.model.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepo userRepo;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserService(userRepo));
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
        .authorizeRequests()
        .antMatchers("/api/login", "/api/searchCities").permitAll()
        .antMatchers(HttpMethod.POST, "/api/user").permitAll()
        .antMatchers("/api/logout").authenticated()
        .antMatchers(HttpMethod.POST, "/api/weather").authenticated()
        .antMatchers(HttpMethod.POST, "/api/addNote").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/api/cities", "/api/notes", "/api/predefinedNotes").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/api/cities", "/api/notes", "/api/predefinedNotes").hasRole("ADMIN").and()
        .csrf().disable();
    }
    
}