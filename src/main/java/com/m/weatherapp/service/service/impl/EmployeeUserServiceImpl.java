package com.m.weatherapp.service.service.impl;

import com.m.weatherapp.model.entity.EmployeeUser;
import com.m.weatherapp.model.entity.User;
import com.m.weatherapp.model.repository.UserRepo;
import com.m.weatherapp.service.dto.response.RegisterResponseDto;
import com.m.weatherapp.service.dto.response.RegisterResponseDto.RegisterResponse;
import com.m.weatherapp.service.dto.user.EmployeeUserDto;
import com.m.weatherapp.service.service.EmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("employeeUserService")
@Transactional
public class EmployeeUserServiceImpl extends UserServiceImpl implements EmployeeUserService{

	@Autowired
	private UserRepo userRepo;
	
	public RegisterResponseDto register(EmployeeUserDto employeeUserDto) {
		
		// create response
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		
		// search by email
		User user = userRepo.findByUserEmail(employeeUserDto.getUserEmail());
		
		// if user is exists return email is already exists response
		if(user != null){
			registerResponseDto.setResponse(RegisterResponse.EMAIL_IS_ALREADY_EXISTS);
			return registerResponseDto;
		}
			
		// create user entity
		EmployeeUser employeeUser = new EmployeeUser();
		employeeUser.setUserEmail(employeeUserDto.getUserEmail());
		employeeUser.setUserMobile(employeeUserDto.getUserMobile());
		employeeUser.setUserName(employeeUserDto.getUserName());
		employeeUser.setUserPassword(employeeUserDto.getPassword());
		
		userRepo.save(employeeUser);
		
		// set done response
		registerResponseDto.setResponse(RegisterResponse.DONE);
		
		return registerResponseDto;
	}

}
