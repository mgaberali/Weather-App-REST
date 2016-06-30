package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.config.ModelConfig;
import com.m.weatherapp.model.entity.AdminUser;
import com.m.weatherapp.model.entity.City;
import com.m.weatherapp.model.entity.Note;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ModelConfig.class)
@Transactional
public class NoteRepoTest {
	
	@Autowired
	private NoteRepo noteDao;
	
	@Autowired
	private CityRepo cityDao;
	
	@Autowired
	private UserRepo userDao;
	
	@Test
	public void testPersistAndDeleteNote() {
		
		// persist admin user
		AdminUser adminUser = generateAdminUser();
		userDao.save(adminUser);
		
		// persist city
		City city = new City("Alaska");
		cityDao.save(city);
		
		// select inserted city
		City insertedCity = cityDao.findByCityName(city.getCityName());
		
		// persist note
		Note toBeInsertedNote = generateNote(adminUser, insertedCity);
		noteDao.save(toBeInsertedNote);
		
		// select inserted note
		Note insertedNote = noteDao.findByNoteDateAndCity(toBeInsertedNote.getNoteDate(), insertedCity);
		
		// assert not null
		assertNotNull(insertedNote);
		
		// delete note
		noteDao.delete(insertedNote);
		
		// select supposed to be deleted note
		Note supposedToBeDeletedNote = noteDao.findByNoteDateAndCity(toBeInsertedNote.getNoteDate(), insertedCity);
		
		// assert null
		assertNull(supposedToBeDeletedNote);
		
	}
	
	@Ignore
	private Note generateNote(AdminUser user, City city){
		
		Note note = new Note();
		note.setAdminUser(user);
		note.setCity(city);
		note.setNoteDate(Calendar.getInstance().getTime());
		note.setNoteText("The Weather is fine");
		note.setTemperature(20.0f);
		
		return note;
	}
	
	@Ignore
	private AdminUser generateAdminUser(){
		
		AdminUser user = new AdminUser();
		user.setUserEmail("admin.test@yahoo.com");
		user.setUserName("Admin Test User");
		user.setUserPassword("admin");
		
		return user;
	}

}
