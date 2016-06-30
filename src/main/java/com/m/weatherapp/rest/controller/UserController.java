package com.m.weatherapp.rest.controller;

import com.m.weatherapp.service.dto.response.LoginResponseDto;
import com.m.weatherapp.service.dto.response.LoginResponseDto.LoginResponse;
import com.m.weatherapp.service.dto.response.RegisterResponseDto;
import com.m.weatherapp.service.dto.user.AdminUserDto;
import com.m.weatherapp.service.dto.user.EmployeeUserDto;
import com.m.weatherapp.service.dto.user.UserDto.UserRole;
import com.m.weatherapp.service.service.EmployeeUserService;
import com.m.weatherapp.rest.dto.request.LoginRequestDTO;
import com.m.weatherapp.rest.dto.request.RequestDTO;
import com.m.weatherapp.rest.dto.response.LogoutResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private EmployeeUserService userService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		
		// user login service
		LoginResponseDto loginResponseDto = userService.login(loginRequestDTO.getEmail(),
				loginRequestDTO.getPassword());

		if (loginResponseDto.getResponse() == LoginResponse.USER_FOUND) {

			// determine user type
			if (loginResponseDto.getUsDto() instanceof EmployeeUserDto)
				loginResponseDto.getUsDto().setRole(UserRole.EMPLOYEE);
			else if (loginResponseDto.getUsDto() instanceof AdminUserDto)
				loginResponseDto.getUsDto().setRole(UserRole.ADMIN);

		}

		ResponseEntity<LoginResponseDto> responseEntity = new ResponseEntity<LoginResponseDto>(loginResponseDto,
				HttpStatus.OK);

		return responseEntity;
	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogoutResponseDTO> logout(@RequestBody RequestDTO requestDTO) {

		userService.logout("");
		
		ResponseEntity<LogoutResponseDTO> responseEntity = new ResponseEntity<LogoutResponseDTO>(HttpStatus.OK);
		
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisterResponseDto> signUp(@RequestBody EmployeeUserDto employeeUserDto) {
		// register employee user
		RegisterResponseDto registerResponseDto = userService.register(employeeUserDto);

		return new ResponseEntity<RegisterResponseDto>(registerResponseDto, HttpStatus.OK);
	}

	
}
