package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.entity.PredefinedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PredefinedNoteRepo extends JpaRepository<PredefinedNote, Integer>{

	@Query("select p from PredefinedNote p where p.minTemp < :temp and p.maxTemp > :temp")
	PredefinedNote findByMinTempLessThanAndMaxTempGreaterThan(@Param("temp") Float temp);
	PredefinedNote findById(Integer predefinedNoteId);
}
