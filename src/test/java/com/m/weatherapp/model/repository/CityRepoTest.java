package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.config.DataSourceConfig;
import com.m.weatherapp.model.config.JPAConfig;
import com.m.weatherapp.model.config.ModelConfig;
import com.m.weatherapp.model.entity.City;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataSourceConfig.class, JPAConfig.class, ModelConfig.class})
@Transactional
public class CityRepoTest {

	@Autowired
	private CityRepo cityDao;

	@Test
	public void testPersistCity() {

		City toBeInsertedCity = generateCity();

		// insert
		cityDao.save(toBeInsertedCity);

		// select inserted city
		City insertedCity = cityDao.findByCityName(toBeInsertedCity.getCityName());

		// assert not null
		assertNotNull(insertedCity);

	}

	@Test
	public void testDeleteCity() {

		City toBeInsertedCity = generateCity();

		// insert
		cityDao.save(toBeInsertedCity);

		// select inserted city
		City insertedCity = cityDao.findByCityName(toBeInsertedCity.getCityName());

		// delete city
		cityDao.delete(insertedCity);

		// select supposed to be deleted city
		City supposedToBeCity = cityDao.findByCityName(toBeInsertedCity.getCityName());
		
		// assert null
		assertNull(supposedToBeCity);
	}

	@Test
	public void testSearchCitiesByName() {
		
		City [] citiesToBeInserted = {new City("azcairo"), new City("cairoaz"), new City("caaziro")};
		
		// insert cities
		for(City city : citiesToBeInserted)
			cityDao.save(city);
		
		// insert other cities
		cityDao.save(new City("Alex"));
		cityDao.save(new City("Aswan"));
		
		// select inserted cities
		List insertedCities = cityDao.findByCityNameLike("%az%");
		
		// assert number of selected cities to be 3
		assertEquals(3, insertedCities.size());
			
		
	}
	
	@Ignore
	private City generateCity() {

		City city = new City("Cairo2222");

		return city;
	}

}
