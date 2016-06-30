package com.m.weatherapp.service.service;

import com.m.weatherapp.service.dto.response.RegisterResponseDto;
import com.m.weatherapp.service.dto.user.EmployeeUserDto;

public interface EmployeeUserService extends UserService{
	
	/**
	 * to register employee user
	 * @param employeeUserDto
	 * @return registration response (DONE or EMAIL_IS_ALREADY_EXISTS)
	 */
	RegisterResponseDto register(EmployeeUserDto employeeUserDto);

}
