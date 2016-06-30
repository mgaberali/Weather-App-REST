package com.m.weatherapp.service.services;

import com.m.weatherapp.service.config.ServiceConfig;
import com.m.weatherapp.service.dto.response.LoginResponseDto;
import com.m.weatherapp.service.dto.response.LoginResponseDto.LoginResponse;
import com.m.weatherapp.service.dto.response.RegisterResponseDto;
import com.m.weatherapp.service.dto.response.RegisterResponseDto.RegisterResponse;
import com.m.weatherapp.service.dto.user.EmployeeUserDto;
import com.m.weatherapp.service.service.EmployeeUserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ServiceConfig.class)
@Transactional
public class EmployeeUserServiceTest {

	@Autowired
	private EmployeeUserService employeeUserService;

	@Test
	public void testRegister() {

		// create user dto
		EmployeeUserDto employeeUserDto = generateEmpUserDto();

		// 1. register the user dto
		RegisterResponseDto registerResponseDto = employeeUserService.register(employeeUserDto);

		// 1.1 assert response is done
		assertEquals(RegisterResponse.DONE, registerResponseDto.getResponse());

		// 2. re-register user dto
		RegisterResponseDto registerResponseDto2 = employeeUserService.register(employeeUserDto);

		// 2.1 assert response is email is already exists
		assertEquals(RegisterResponse.EMAIL_IS_ALREADY_EXISTS, registerResponseDto2.getResponse());

	}

	@Test
	public void testLogin() {

		// create user dto
		EmployeeUserDto employeeUserDto = generateEmpUserDto();

		// register the user dto
		RegisterResponseDto registerResponseDto = employeeUserService.register(employeeUserDto);

		// 1. login with valid email and password
		LoginResponseDto loginResponseDto = employeeUserService.login("emp_user_test@yahoo.com", "emp123");

		// 1.1 assert response is found
		assertEquals(LoginResponse.USER_FOUND, loginResponseDto.getResponse());

		// 1.2 assert that returned user id employee user
		assertThat(loginResponseDto.getUsDto(), instanceOf(EmployeeUserDto.class));

		// 2. login with invalid email
		LoginResponseDto loginResponseDto2 = employeeUserService.login("test99@yahoo.com", "emp123");

		// 2.1 assert response is user not found
		assertEquals(LoginResponse.USER_NOT_FOUND, loginResponseDto2.getResponse());

		// 3. login with invalid email
		LoginResponseDto loginResponseDto3 = employeeUserService.login("emp_user_test@yahoo.com", "eeee55");

		// 3.1 assert response is invalid email or password
		assertEquals(LoginResponse.INVALID_EMAIL_OR_PASSWORD, loginResponseDto3.getResponse());

	}

	@Ignore
	private EmployeeUserDto generateEmpUserDto() {

		EmployeeUserDto employeeUserDto = new EmployeeUserDto();
		employeeUserDto.setUserEmail("emp_user_test@yahoo.com");
		employeeUserDto.setUserMobile("01277447733");
		employeeUserDto.setUserName("Emp User test");
		employeeUserDto.setPassword("emp123");

		return employeeUserDto;

	}

}
