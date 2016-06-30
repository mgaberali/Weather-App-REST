package com.m.weatherapp.service.service.impl;

import com.m.weatherapp.model.entity.AdminUser;
import com.m.weatherapp.model.entity.City;
import com.m.weatherapp.model.entity.Note;
import com.m.weatherapp.model.entity.PredefinedNote;
import com.m.weatherapp.model.repository.CityRepo;
import com.m.weatherapp.model.repository.NoteRepo;
import com.m.weatherapp.model.repository.PredefinedNoteRepo;
import com.m.weatherapp.model.repository.UserRepo;
import com.m.weatherapp.service.dto.CityDto;
import com.m.weatherapp.service.dto.NoteDto;
import com.m.weatherapp.service.dto.PredefinedNoteDto;
import com.m.weatherapp.service.dto.response.SetNoteOnWeatherTodayResponseDto;
import com.m.weatherapp.service.dto.response.SetNoteOnWeatherTodayResponseDto.SetNoteOnWeatherResponse;
import com.m.weatherapp.service.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl extends UserServiceImpl implements AdminUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private NoteRepo noteRepo;
	
	@Autowired
	private PredefinedNoteRepo predefinedNoteDao;
	
	@Secured("ROLE_ADMIN")
	public SetNoteOnWeatherTodayResponseDto setNoteOnWeatherToday(NoteDto noteDto) {

		// create response dto
		SetNoteOnWeatherTodayResponseDto responseDto = new SetNoteOnWeatherTodayResponseDto();
		
		// create city entity from city dto inside note dto
		City city = new City(noteDto.getCityDto().getCityId(), noteDto.getCityDto().getCityName());
		
		// check if there is weather today note for this city
		Note resultSearchForNote = noteRepo.findByNoteDateAndCity(Calendar.getInstance().getTime(), city);
		
		// if note found for today and for this city return note is already setted response
		if(resultSearchForNote != null){
			responseDto.setResponse(SetNoteOnWeatherResponse.NOTE_IS_ALREADY_SETTED_TODAY_FOR_THIS_CITY
					);
			
			return responseDto;
		}
		
		// create note entity
		Note note = new Note();
		note.setNoteDate(noteDto.getNoteDate());
		note.setNoteText(noteDto.getNoteText());
		note.setTemperature(noteDto.getTemperature());

		// create admin user entity and add it to note entity
		AdminUser adminUser = new AdminUser();
		adminUser.setUserEmail(noteDto.getAdminUserEmail());
		note.setAdminUser(adminUser);

		// add city entity to note entity
		note.setCity(city);

		// save note
		noteRepo.save(note);
		
		// set response by done
		responseDto.setResponse(SetNoteOnWeatherResponse.DONE);

		return responseDto;
	}
	
	@Secured("ROLE_ADMIN")
	public List<PredefinedNoteDto> getAllPredefinedNotes() {
		
		// get all predefined notes entities
		List predefinedNotesEntities = predefinedNoteDao.findAll();
		
		// create list of predefined notes dto
		List<PredefinedNoteDto> predefinedNotesList = new ArrayList<PredefinedNoteDto>();
		
		for(Object obj : predefinedNotesEntities){
			
			PredefinedNote predefinedNoteEntity = (PredefinedNote) obj;
			
			// convert predefined note dto to entity
			PredefinedNoteDto predefinedNoteDto = new PredefinedNoteDto();
			predefinedNoteDto.setId(predefinedNoteEntity.getId());
			predefinedNoteDto.setMaxTemp(predefinedNoteEntity.getMaxTemp());
			predefinedNoteDto.setMinTemp(predefinedNoteEntity.getMinTemp());
			predefinedNoteDto.setPredefinedNoteText(predefinedNoteEntity.getPredefinedNoteText());
			
			// add predefined note to list
			predefinedNotesList.add(predefinedNoteDto);
			
		}
		
		return predefinedNotesList;
	}

	@Secured("ROLE_ADMIN")
	public boolean setPredefinedNotesList(List<PredefinedNoteDto> list) {
		
		for(PredefinedNoteDto predefinedNoteDto : list){
			
			// create predefined note entity
			PredefinedNote predefinedNote = new PredefinedNote();
			predefinedNote.setId(predefinedNoteDto.getId());
			predefinedNote.setMaxTemp(predefinedNoteDto.getMaxTemp());
			predefinedNote.setMinTemp(predefinedNoteDto.getMinTemp());
			predefinedNote.setPredefinedNoteText(predefinedNoteDto.getPredefinedNoteText());
			
			// update predefined note entity
			predefinedNoteDao.save(predefinedNote);
			
		}
		
		return true;
	}
	
	@Secured("ROLE_ADMIN")
	public List<NoteDto> getAllNotes(String adminUserEmail) {
		
		List<NoteDto> allNotes = new ArrayList<NoteDto>();
		
		AdminUser adminUser = (AdminUser) userRepo.findByUserEmail(adminUserEmail);
		
		Set notesList = adminUser.getNotes();
		
		for(Object obj : notesList){
			
			Note note = (Note) obj;
			
			// convert note entity to note dto
			NoteDto noteDto = new NoteDto();
			noteDto.setAdminUserEmail(adminUserEmail);
			noteDto.setCityDto(new CityDto(note.getCity().getCityId(), note.getCity().getCityName()));
			noteDto.setNoteDate(note.getNoteDate());
			noteDto.setNoteId(note.getNoteId());
			noteDto.setNoteText(note.getNoteText());
			noteDto.setTemperature(note.getTemperature());
			
			// add note dto to allNotes list
			allNotes.add(noteDto);
			
		}
		
		return allNotes	;
	}
	
	@Secured("ROLE_ADMIN")
	public boolean addCity(CityDto cityDto) {

		City city = cityRepo.findByCityName(cityDto.getCityName());

		// if there is a city with this name should return false
		if (city != null)
			return false;

		// create city entity
		City newCity = new City(cityDto.getCityName());

		// persist new city
		cityRepo.save(newCity);

		return true;
	}
	
	@Secured("ROLE_ADMIN")
	public boolean deleteCity(CityDto cityDto) {

		// create city entity
		City city = new City();
		city.setCityId(cityDto.getCityId());
		city.setCityName(cityDto.getCityName());

		// delete city
		cityRepo.delete(city);

		return true;
	}
	
	@Secured("ROLE_ADMIN")
	public List<CityDto> getAllCities() {

		List<CityDto> citiesList = new ArrayList<CityDto>();

		// get all city entities
		List cities = cityRepo.findAll();

		for (Object obj : cities) {

			City city = (City) obj;

			// create city dto and add it to cities list
			CityDto cityDto = new CityDto();
			cityDto.setCityId(city.getCityId());
			cityDto.setCityName(city.getCityName());
			citiesList.add(cityDto);
		}

		return citiesList;
	}

}
