package com.m.weatherapp.model.repository;

;
import com.m.weatherapp.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Integer>{
	
	City findByCityName(String cityName);
	List findByCityNameLike(String cityName);
}
