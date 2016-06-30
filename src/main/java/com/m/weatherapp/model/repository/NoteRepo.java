package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.entity.City;
import com.m.weatherapp.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface NoteRepo extends JpaRepository<Note, Integer>{

	Note findByNoteDateAndCity(Date noteDate, City city);
}
