package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.config.ModelConfig;
import com.m.weatherapp.model.entity.EmployeeUser;
import com.m.weatherapp.model.entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ModelConfig.class)
@Transactional
public class UserRepoTest {

	@Autowired
	private UserRepo userDao;

	@Test
	public void testPersistUser() {

		EmployeeUser toBeInsertedUser = (EmployeeUser) generateUser();

		// persist user
		userDao.save(toBeInsertedUser);

		// find user by email
		EmployeeUser insertedUser = (EmployeeUser) userDao.findByUserEmail(toBeInsertedUser.getUserEmail());

		// assert selected user not null
		assertNotNull(insertedUser);

		// assert fields are equal
		assertEquals(toBeInsertedUser.getUserName(), insertedUser.getUserName());
		assertEquals(toBeInsertedUser.getUserMobile(), insertedUser.getUserMobile());
		assertEquals(toBeInsertedUser.getUserPassword(), insertedUser.getUserPassword());

	}

	@Test
	public void testUpdateUser() {

		EmployeeUser toBeInsertedUser = (EmployeeUser) generateUser();

		// persist user
		userDao.save(toBeInsertedUser);

		// find user by email to be updated
		EmployeeUser toBeUpdatedUser = (EmployeeUser) userDao.findByUserEmail(toBeInsertedUser.getUserEmail());
		toBeUpdatedUser.setUserName("testemp22");
		toBeUpdatedUser.setUserMobile("12345");
		toBeUpdatedUser.setUserPassword("888888");



		// select updated user
		EmployeeUser updatedUser = (EmployeeUser) userDao.findByUserEmail(toBeInsertedUser.getUserEmail());

		// assert not null
		assertNotNull(updatedUser);

		// assert fields are equal
		assertEquals(toBeUpdatedUser.getUserName(), updatedUser.getUserName());
		assertEquals(toBeUpdatedUser.getUserMobile(), updatedUser.getUserMobile());
		assertEquals(toBeUpdatedUser.getUserPassword(), updatedUser.getUserPassword());

	}

	@Test
	public void testDeleteUser() {

		EmployeeUser toBeInsertedUser = (EmployeeUser) generateUser();

		// persist user
		userDao.save(toBeInsertedUser);

		// select to be deleted user
		EmployeeUser toBeDeletedUser = (EmployeeUser) userDao.findByUserEmail(toBeInsertedUser.getUserEmail());

		// delete user
		userDao.delete(toBeDeletedUser);

		// select that supposed to be deleted user
		EmployeeUser supposedToBeDeletedUser = (EmployeeUser) userDao.findByUserEmail(toBeInsertedUser.getUserEmail());
		
		// assert null
		assertNull(supposedToBeDeletedUser);

	}

	@Ignore
	private User generateUser() {

		// create employer user
		EmployeeUser user = new EmployeeUser();
		user.setUserEmail("testemp@yahoo.com");
		user.setUserName("testemp1");
		user.setUserMobile("0123434343");
		user.setUserPassword("123456");

		return user;

	}
}
