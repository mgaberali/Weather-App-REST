package com.m.weatherapp.service.services;

import com.m.weatherapp.model.entity.City;
import com.m.weatherapp.model.repository.PredefinedNoteRepo;
import com.m.weatherapp.service.config.ServiceConfig;
import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.NoteDto;
import com.m.weatherapp.service.dto.PredefinedNoteDto;
import com.m.weatherapp.service.dto.response.SetNoteOnWeatherTodayResponseDto;
import com.m.weatherapp.service.dto.response.SetNoteOnWeatherTodayResponseDto.SetNoteOnWeatherResponse;
import com.m.weatherapp.service.service.AdminUserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ServiceConfig.class)
@Transactional
public class AdminUserServiceTest {

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private com.m.weatherapp.model.repository.CityRepo CityRepo;

	@Autowired
	private PredefinedNoteRepo PredefinedNoteDao;
	
	@Test
	public void testAddCity() {

		// create city dto
		CityDto cityDto = new CityDto();
		cityDto.setCityName("CityForTestName");

		// add city
		adminUserService.addCity(cityDto);

		// 1. find inserted city by name
		City selectedCity = CityRepo.findByCityName(cityDto.getCityName());

		// 1.1 assert selected city is not null
		assertNotNull(selectedCity);
	}

	@Test
	public void testGetAllCities() {

		String[] citiesNames = { "test city1", "test city2", "test city3", "test city4", "test city5" };

		for (String cityName : citiesNames) {

			// create city Dto
			CityDto cityDto = new CityDto();
			cityDto.setCityName(cityName);

			// add city
			adminUserService.addCity(cityDto);
		}

		// 1. get all cities
		List list = adminUserService.getAllCities();

		// 1.1 create toBeEqualCount
		int toBeEqualCount = 5;

		// 1.2 search
		for (Object obj : list) {

			CityDto cityDto = (CityDto) obj;

			for (String cityName : citiesNames) {

				if (cityDto.getCityName().equals(cityName))
					toBeEqualCount--;

			}

		}

		// 1.3 assert that toBeEqualCount is 0 that is refer to all inserted
		// cities are found
		assertEquals(0, toBeEqualCount);

	}

	@Test
	public void testsearchCitiesByName() {

		String[] citiesNames = { "haz09htest city1", "test haz09h city2", "test city3haz09h", "tehaz09hst city4",
				"tehaz09hst city5" };

		for (String cityName : citiesNames) {

			// create city Dto
			CityDto cityDto = new CityDto();
			cityDto.setCityName(cityName);

			// add city
			adminUserService.addCity(cityDto);
		}

		// 1. search cities by haz09h
		List list = adminUserService.searchCitiesByName("haz09h");

		// 1.1 create toBeEqualCount
		int toBeEqualCount = 5;

		// 1.2 search
		for (Object obj : list) {

			CityDto cityDto = (CityDto) obj;

			for (String cityName : citiesNames) {

				if (cityDto.getCityName().equals(cityName))
					toBeEqualCount--;

			}

		}

		// 1.3 assert that toBeEqualCount is 0 that is refer to all inserted
		// cities are found
		assertEquals(0, toBeEqualCount);

	}

	@Test
	public void setNoteOnWeatherToday() {

		// generate Note Dto
		NoteDto noteDto = generateNoteDto("Test City 99", "admin@gmail.com");

		// 1. set note on weather today for test city 99
		SetNoteOnWeatherTodayResponseDto responseDto = adminUserService.setNoteOnWeatherToday(noteDto);

		// 1.1 assert response is DONE
		assertEquals(SetNoteOnWeatherResponse.DONE, responseDto.getResponse());

		// 2. set another note on weather today for test city 99
		SetNoteOnWeatherTodayResponseDto responseDto2 = adminUserService.setNoteOnWeatherToday(noteDto);

		// 2.1 assert response is NOTE_IS_ALREADY_SETTED_TODAY_FOR_THIS_CITY
		assertEquals(SetNoteOnWeatherResponse.NOTE_IS_ALREADY_SETTED_TODAY_FOR_THIS_CITY, responseDto2.getResponse());

		// 3. check weahter today for test city 99
		// WeatherTodayDto weatherTodayDto =
		// adminUserService.checkWeatherToday(addedCityDto);

		// 3.1 assert noteText is equal
		// assertEquals(noteDto.getNoteText(), weatherTodayDto.getNote());

	}

	@Test
	public void testGetAllNotes() {

		// generate Note Dtos
		NoteDto noteDto1 = generateNoteDto("Test City 99", "admin@gmail.com");
		NoteDto noteDto2 = generateNoteDto("Test City 1002", "admin@gmail.com");
		NoteDto noteDto3 = generateNoteDto("Test City xx54", "admin@gmail.com");
		
		// add all notes
		adminUserService.setNoteOnWeatherToday(noteDto1);
		adminUserService.setNoteOnWeatherToday(noteDto2);
		adminUserService.setNoteOnWeatherToday(noteDto3);
		
		// 1. get all admin notes
		List<NoteDto> notesList = adminUserService.getAllNotes("admin@gmail.com");
		
		// 1.1 assert notes list size is 3
		assertEquals(3, notesList.size());
		
	}
	
	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void testGetAndUpdateAllPredefinedNotes() {
		
		// 1. get all predefined notes
		List<PredefinedNoteDto> predefinedNotesList =  adminUserService.getAllPredefinedNotes();
		
		// 1.1 assert list size is 4
		assertEquals(4, predefinedNotesList.size());
		
		String [] notes = {"test pre-def note 1", "test pre-def note 2", "test pre-def note 3", "test pre-def note 4"};
		
		// update all predefined notes in list
		for(int i = 0; i < notes.length; i++){
			predefinedNotesList.get(i).setPredefinedNoteText(notes[i]);
		}
		
		// 2. update predefined notes list
		boolean status = adminUserService.setPredefinedNotesList(predefinedNotesList);
		
		// 2.1 assert status is true
		assertTrue(status);
		
		
	}

	@Ignore
	private NoteDto generateNoteDto(String cityName, String adminUserEmail) {

		// create city dto
		CityDto cityDto = new CityDto(cityName);

		// add city
		adminUserService.addCity(cityDto);

		// select added city
		City cityEntity = CityRepo.findByCityName(cityDto.getCityName());
		CityDto addedCityDto = new CityDto(cityEntity.getCityId(), cityEntity.getCityName());

		// create noteDto
		NoteDto noteDto = new NoteDto();
		noteDto.setAdminUserEmail(adminUserEmail);
		noteDto.setCityDto(addedCityDto);
		noteDto.setNoteDate(Calendar.getInstance().getTime());
		noteDto.setNoteText("Today weather note text 99");
		noteDto.setTemperature(15.0f);

		return noteDto;

	}

}
